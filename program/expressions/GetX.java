package worms.model.program.expressions;

import worms.model.program.Program;
import worms.model.program.types.Entity;

public class GetX extends Expression<DoubleLiteral>{

	public GetX(int line, int column, Expression<Entity> e){
		super(line,column);
		this.entityExpression = e;
	}
	
	public Expression<Entity> getEntityExpression(){
		return this.entityExpression;
	}
	
	private final Expression<Entity> entityExpression;

	@Override
	public DoubleLiteral evaluate(Program program) {
		if (getEntityExpression().evaluate(program).getValue() == null)
			return new DoubleLiteral(getLine(),getColumn(),Double.NaN);
		double xCoordinate = getEntityExpression().evaluate(program).getValue().getPosition().getX();
		return new DoubleLiteral(getLine(),getColumn(),xCoordinate);
	}
	


}
