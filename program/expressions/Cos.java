package worms.model.program.expressions;

import worms.model.program.Program;

public class Cos extends Expression<DoubleLiteral> {
	
	public Cos(int line, int column, Expression<DoubleLiteral> e) {
		super(line,column);
		this.expression = e;
	}
	
	public Expression<DoubleLiteral> getExpression() {
		return this.expression;
	}

	private final Expression<DoubleLiteral> expression;

	
	@Override
	public DoubleLiteral evaluate(Program program) {
		double cos = Math.cos(getExpression().evaluate(program).getDoubleValue());
		return new DoubleLiteral(getLine(),getColumn(),cos);
	}

}
