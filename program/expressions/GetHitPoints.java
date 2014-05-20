package worms.model.program.expressions;

import worms.model.Worm;
import worms.model.program.Program;
import worms.model.program.types.Double;

public class GetHitPoints extends Expression{
	//Exception

	public GetHitPoints(int line, int column, Expression e){
		super(line,column);
		this.entityExpression = e;
	}
	
	public Expression getEntityExpression(){
		return this.entityExpression;
	}
	
	private final Expression entityExpression;
	
	public Double getResult(Program program) throws NullPointerException,IllegalArgumentException {
		if (((EntityLiteral) getEntityExpression().evaluate(program)).getGameObjectValue() == null)
			throw new NullPointerException("Line: " + getLine() + " - Column: " + getColumn());
		if (! (((EntityLiteral) getEntityExpression().evaluate(program)).getGameObjectValue() instanceof worms.model.Worm))
			throw new IllegalArgumentException("Line: " + getLine() + " - Column: " + getColumn());
		Worm worm = (Worm) ((EntityLiteral) getEntityExpression().evaluate(program)).getGameObjectValue();
		return new Double(worm.getHitPoints());
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

