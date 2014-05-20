package worms.model.program.expressions;

import worms.model.GameObject;
import worms.model.program.Program;
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
		if ( ((EntityLiteral) getEntityExpression().evaluate(program)).getGameObjectValue() == null)
			throw new NullPointerException("Line: " + getLine() + " - Column: " + getColumn());
		GameObject gameObject = ((EntityLiteral) getEntityExpression().evaluate(program)).getGameObjectValue();
		return new Double(gameObject.getPosition().getY());
	}

	@Override
	public DoubleLiteral evaluate(Program program) throws NullPointerException {
		return new DoubleLiteral(getLine(),getColumn(),getResult(program));
	}
	

	@Override
	public String generateString(Program program) throws NullPointerException {
		return getResult(program).toString();
	}

}
