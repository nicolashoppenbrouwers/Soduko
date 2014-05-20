package worms.model.program.expressions;

import worms.model.Worm;
import worms.model.program.Program;

public class SameTeam extends Expression{
	//exceptions

	public SameTeam(int line, int column, Expression e){
		super(line,column);
		this.entityExpression = e;
	}
	
	public Expression getEntityExpression(){
		return this.entityExpression;
	}
	
	private final Expression entityExpression;

	public Boolean getResult(Program program) throws NullPointerException, IllegalArgumentException{
		if (( ((EntityLiteral) getEntityExpression().evaluate(program)).getGameObjectValue() == null) || (program.getWorm() == null))
			throw new NullPointerException("Line: " + getLine() + " - Column: " + getColumn());
		if (! ( ((EntityLiteral) getEntityExpression().evaluate(program)).getGameObjectValue() instanceof worms.model.Worm))
				throw new IllegalArgumentException("Line: " + getLine() + " - Column: " + getColumn());
		Worm worm = (Worm) ((EntityLiteral) getEntityExpression().evaluate(program)).getGameObjectValue();
		boolean sameTeamResult = (worm.getTeam() == program.getWorm().getTeam());
		//OF alternatief, als de operator == niet werkt bij Team, met de teamnamen, aangezien deze toch uniek moeten zijn.
		//boolean sameTeamResult = (worm.getTeam().getName().equals(program.getWorm().getTeam().getName()));
		if (worm.getTeam().getName().equals("Indepentent") && program.getWorm().getTeam().getName().equals("Independent"))
			sameTeamResult = false;
		return new Boolean(sameTeamResult);
	}
	@Override
	public BooleanLiteral evaluate(Program program) throws NullPointerException, IllegalArgumentException {
		return new BooleanLiteral(getLine(),getColumn(),getResult(program));
	}
	
	@Override
	public String generateString(Program program) throws NullPointerException, IllegalArgumentException {
		return getResult(program).toString();
	}

}
