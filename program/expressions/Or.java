package worms.model.program.expressions;

import worms.model.program.Program;

public class Or extends Expression<BooleanLiteral>{

	public Or(int line, int column, Expression<BooleanLiteral> e1, Expression<BooleanLiteral> e2) {
		super(line,column);
		this.expressionLeft = e1;
		this.expressionRight = e2;
	}
	
	public Expression<BooleanLiteral> getExpressionLeft() {
		return this.expressionLeft;
	}

	public Expression<BooleanLiteral> getExpressionRight() {
		return this.expressionRight;
	}

	private final Expression<BooleanLiteral> expressionLeft;
	private final Expression<BooleanLiteral> expressionRight;

	
	@Override
	public BooleanLiteral evaluate(Program program) {
		Boolean orResult = (getExpressionLeft().evaluate(program).getValue() || getExpressionRight().evaluate(program).getValue());
		return new BooleanLiteral(getLine(),getColumn(),orResult);
	}

}
