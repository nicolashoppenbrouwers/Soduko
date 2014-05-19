package worms.model.program.expressions;

import worms.model.Worm;
import worms.model.program.Program;
import worms.model.program.types.Entity;

public class SearchObj extends Expression<Entity>{

	public SearchObj(int line, int column, Expression<Entity> e){
		super(line,column);
		this.entityExpression = e;
	}
	
	public Expression<Entity> getEntityExpression(){
		return this.entityExpression;
	}
	
	private final Expression<Entity> entityExpression;

	@Override
	public Expression<Entity> evaluate(Program program) {
		//Als de entity geen Worm is, heeft het geen APs.
		if ((getEntityExpression().evaluate(program).getValue() == null) || (! (getEntityExpression().evaluate(program).getValue() instanceof worms.model.Worm)))
			return new DoubleLiteral(getLine(),getColumn(),Double.NaN);
		//Je moet hier casten naar Worm, anders lukt het niet.
		Worm worm = (Worm) getEntityExpression().evaluate(program).getValue();
		return new DoubleLiteral(getLine(),getColumn(),worm.getActionPoints());
	}
	

	// itereren over alle wormen
	// kijken of uw huidige worm binnen de gewilde band ligt
	// dit doe je door arctan(y-y' / x-x' ) en kijken of dit ligt binnen theta +- e
	// en dan checken of dit kleiner is dan de minimal direction so far

}
