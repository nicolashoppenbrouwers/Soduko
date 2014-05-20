package worms.model.program.expressions;

import worms.model.MovableGameObject;
import worms.model.program.Program;
import worms.model.program.types.Double;

//exceptions

public class GetDirection extends Expression{

	public GetDirection(int line, int column, Expression e){
		super(line,column);
		this.entityExpression = e;
	}
	
	public Expression getEntityExpression(){
		return this.entityExpression;
	}
	
	private final Expression entityExpression;
	
	public Double getResult(Program program) throws NullPointerException,IllegalArgumentException {
		if ( ((EntityLiteral) getEntityExpression().evaluate(program)).getGameObjectValue() == null)
			throw new NullPointerException("Line: " + getLine() + " - Column: " + getColumn());
		if (! (((EntityLiteral) getEntityExpression().evaluate(program)).getGameObjectValue() instanceof worms.model.MovableGameObject))
			throw new IllegalArgumentException("Line: " + getLine() + " - Column: " + getColumn());
		MovableGameObject movableGameObject = (MovableGameObject) ((EntityLiteral) getEntityExpression().evaluate(program)).getGameObjectValue();
		return new Double(movableGameObject.getDirection());
	}

	@Override
	public DoubleLiteral evaluate(Program program) throws NullPointerException,IllegalArgumentException {
		return new DoubleLiteral(getLine(),getColumn(),getResult(program));
	}
	
	@Override
	public String generateString(Program program) throws NullPointerException, IllegalArgumentException {
		return getResult(program).toString();
	}

}
