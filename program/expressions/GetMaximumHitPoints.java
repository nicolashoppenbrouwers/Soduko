package worms.model.program.expressions;

import worms.model.GameObject;
import worms.model.Program;
import worms.model.Worm;
import worms.model.program.types.Double;

//Exception

public class GetMaximumHitPoints extends Expression{

	public GetMaximumHitPoints(int line, int column, Expression e){
		super(line,column);
		this.entityExpression = e;
	}
	
	public Expression getEntityExpression(){
		return this.entityExpression;
	}
	
	private final Expression entityExpression;
	
	public Double getResult(Program program) throws NullPointerException, IllegalArgumentException{
		GameObject gameObject = ((EntityLiteral) getEntityExpression().evaluate(program)).getGameObjectValue();
		if (gameObject == null) 
			throw new NullPointerException("Line: " + getLine() + " - Column: " + getColumn());
		if (! (gameObject instanceof worms.model.Worm))
			throw new IllegalArgumentException("Line: " + getLine() + " - Column: " + getColumn());
		Worm worm = (Worm) gameObject;
		return new Double(worm.getMaximumHitPoints());
	}

	@Override
	public DoubleLiteral evaluate(Program program) throws NullPointerException, IllegalArgumentException{
		return new DoubleLiteral(getLine(),getColumn(),getResult(program));
	}
	

}
