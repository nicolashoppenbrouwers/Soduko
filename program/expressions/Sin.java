package worms.model.program.expressions;

import worms.model.program.Program;

public class Sin extends Expression<DoubleLiteral> {
	
	public Sin(int line, int column, Expression<DoubleLiteral> e) {
		super(line,column);
		this.expression = e;
	}
	
	public Expression<DoubleLiteral> getExpression() {
		return this.expression;
	}

	private final Expression<DoubleLiteral> expression;

	
	@Override
	public DoubleLiteral evaluate(Program program) {
		double sin = Math.sin(getExpression().evaluate(program).getDoubleValue());
		return new DoubleLiteral(getLine(),getColumn(),sin);
	}

}
