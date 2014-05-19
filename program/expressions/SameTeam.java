package worms.model.program.expressions;

import worms.model.Worm;
import worms.model.program.Program;
import worms.model.program.types.Entity;

public class SameTeam extends Expression<BooleanLiteral>{

	public SameTeam(int line, int column, Expression<Entity> e){
		super(line,column);
		this.entityExpression = e;
	}
	
	public Expression<Entity> getEntityExpression(){
		return this.entityExpression;
	}
	
	private final Expression<Entity> entityExpression;

	@Override
	public BooleanLiteral evaluate(Program program) {
		//Als de entity geen Worm is, heeft het geen team.
		if ((! (getEntityExpression().evaluate(program).getValue() instanceof worms.model.Worm)) || (getEntityExpression().evaluate(program).getValue() == null) || (program.getWorm() == null))
			return new BooleanLiteral(getLine(),getColumn(),false);
		//PROGRAMMA MOET STOPPEN

		Worm worm = (Worm) getEntityExpression().evaluate(program).getValue();
		boolean sameTeamResult = (worm.getTeam() == program.getWorm().getTeam());
		//OF alternatief, als de operator == niet werkt bij Team, met de teamnamen, aangezien deze toch uniek moeten zijn.
		//boolean sameTeamResult = (worm.getTeam().getName().equals(program.getWorm().getTeam().getName()));
		if (worm.getTeam().getName().equals("Indepentent") && program.getWorm().getTeam().getName().equals("Independent"))
			sameTeamResult = false;
		return new BooleanLiteral(getLine(),getColumn(),sameTeamResult);
	}
}
