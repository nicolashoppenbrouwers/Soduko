package worms.model.program.expressions;

import worms.model.GameObject;
import worms.model.Food;
import worms.model.program.Program;
import worms.model.program.types.Boolean;

public class IsFood extends Expression{

	//Exception
	public IsFood(int line, int column, Expression e) {
		super(line,column);
		this.entityExpression = e;
	}
	
	public Expression getEntityExpression(){
		return this.entityExpression;
	}
	
	private final Expression entityExpression;

	@Override
	public Boolean getResult(Program program) throws NullPointerException {
		GameObject gameObject = ((EntityLiteral) getEntityExpression().evaluate(program)).getGameObjectValue();
		if (gameObject == null)
			throw new NullPointerException("Line: " + getLine() + " - Column: " + getColumn());
		boolean isFood = false;		
		if (gameObject instanceof Food)
			isFood = true;
		return new Boolean(isFood);
	}

	@Override
	public BooleanLiteral evaluate(Program program) throws NullPointerException {
		return new BooleanLiteral(getLine(),getColumn(),getResult(program));
	}

}
