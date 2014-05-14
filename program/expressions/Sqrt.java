package worms.model.program.expressions;

import worms.model.program.Program;

public class Sqrt extends Expression<DoubleLiteral> {
	
	public Sqrt(int line, int column, Expression<DoubleLiteral> e) {
		super(line,column);
		this.expression = e;
	}
	
	public Expression<DoubleLiteral> getExpression() {
		return this.expression;
	}

	private final Expression<DoubleLiteral> expression;

	
	@Override
	public DoubleLiteral evaluate(Program program) {
		//Vierkantswortel van een negatief getal.
		//Het kan zijn dat JAVA zelf al NaN teruggeeft bij sqrt(negatief getal).
		if (getExpression().evaluate(program).getDoubleValue() < 0)
			return new DoubleLiteral(getLine(),getColumn(),Double.NaN);
		double sqrt = Math.sqrt(getExpression().evaluate(program).getDoubleValue());
		return new DoubleLiteral(getLine(),getColumn(),sqrt);
	}

}
