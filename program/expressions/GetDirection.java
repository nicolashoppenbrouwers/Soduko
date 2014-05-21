package worms.model.program.expressions;

import worms.model.GameObject;
import worms.model.MovableGameObject;
import worms.model.Program;
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
		GameObject gameObject = ((EntityLiteral) getEntityExpression().evaluate(program)).getGameObjectValue();
		if (gameObject == null)
			throw new NullPointerException("Line: " + getLine() + " - Column: " + getColumn());
		if (! (gameObject instanceof worms.model.MovableGameObject))
			throw new IllegalArgumentException("Line: " + getLine() + " - Column: " + getColumn());
		MovableGameObject movableGameObject = (MovableGameObject) gameObject;
		return new Double(movableGameObject.getDirection());
	}

	@Override
	public DoubleLiteral evaluate(Program program) throws NullPointerException,IllegalArgumentException {
		return new DoubleLiteral(getLine(),getColumn(),getResult(program));
	}
	


}
