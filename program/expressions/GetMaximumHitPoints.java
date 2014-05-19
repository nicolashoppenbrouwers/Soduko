package worms.model.program.expressions;

import worms.model.Worm;
import worms.model.program.Program;
import worms.model.program.types.Entity;

public class GetMaximumHitPoints extends Expression<DoubleLiteral>{

	public GetMaximumHitPoints(int line, int column, Expression<Entity> e){
		super(line,column);
		this.entityExpression = e;
	}
	
	public Expression<Entity> getEntityExpression(){
		return this.entityExpression;
	}
	
	private final Expression<Entity> entityExpression;

	@Override
	public DoubleLiteral evaluate(Program program) {
		//Als de entity geen Worm is, heeft het geen HPs.
		if ((getEntityExpression().evaluate(program).getValue() == null) || (! (getEntityExpression().evaluate(program).getValue() instanceof worms.model.Worm)))
			return new DoubleLiteral(getLine(),getColumn(),Double.NaN);
		//PROGRAMMA MOET STOPPEN

		Worm worm = (Worm) getEntityExpression().evaluate(program).getValue();
		return new DoubleLiteral(getLine(),getColumn(),worm.getMaximumHitPoints());
	}
}
