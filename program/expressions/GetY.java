package worms.model.program.expressions;

import worms.model.program.Program;
import worms.model.program.types.Entity;

public class GetY extends Expression<DoubleLiteral>{

	public GetY(int line, int column, Expression<Entity> e){
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
		double yCoordinate = getEntityExpression().evaluate(program).getValue().getPosition().getY();
		return new DoubleLiteral(getLine(),getColumn(),yCoordinate);
	}
	


}
