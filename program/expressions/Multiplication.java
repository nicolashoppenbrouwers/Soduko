package worms.model.program.expressions;

import worms.model.program.Program;

public class Multiplication extends Expression<DoubleLiteral> {
	
	public Multiplication(int line, int column, Expression<DoubleLiteral> e1, Expression<DoubleLiteral> e2) {
		super(line,column);
		this.expressionLeft = e1;
		this.expressionRight = e2;
	}
	
	public Expression<DoubleLiteral> getExpressionLeft() {
		return this.expressionLeft;
	}

	public Expression<DoubleLiteral> getExpressionRight() {
		return this.expressionRight;
	}

	private final Expression<DoubleLiteral> expressionLeft;
	private final Expression<DoubleLiteral> expressionRight;

	
	@Override
	public DoubleLiteral evaluate(Program program) {
		double multiplication = getExpressionLeft().evaluate(program).getDoubleValue() * getExpressionRight().evaluate(program).getDoubleValue();
		return new DoubleLiteral(getLine(),getColumn(),multiplication);
	}

}