package worms.model.program.expressions;

import worms.model.GameObject;
import worms.model.Program;
import worms.model.program.types.Double;

public class GetY extends Expression{

	//exceptions
	public GetY(int line, int column, Expression e){
		super(line,column);
		this.entityExpression = e;
	}
	
	public Expression getEntityExpression(){
		return this.entityExpression;
	}
	
	private final Expression entityExpression;
	
	public Double getResult(Program program) throws NullPointerException{
		GameObject gameObject = ((EntityLiteral) getEntityExpression().evaluate(program)).getGameObjectValue();
		if (gameObject == null)
			throw new NullPointerException("Line: " + getLine() + " - Column: " + getColumn());
		return new Double(gameObject.getPosition().getY());
	}

	@Override
	public DoubleLiteral evaluate(Program program) throws NullPointerException {
		return new DoubleLiteral(getLine(),getColumn(),getResult(program));
	}
	



}
