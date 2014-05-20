package worms.model.program.expressions;

import worms.model.program.Program;

public class GreaterThan extends Expression<BooleanLiteral> {
	
	public GreaterThan(int line, int column, Expression e1, Expression e2) {
		super(line,column);
		this.expressionLeft = e1;
		this.expressionRight = e2;
	}
	
	public Expression getExpressionLeft() {
		return this.expressionLeft;
	}

	public Expression getExpressionRight() {
		return this.expressionRight;
	}

	private final Expression expressionLeft;
	private final Expression expressionRight;
	
	public Boolean getResult(Program program){

	
	@Override
	public BooleanLiteral evaluate(Program program) {
		boolean greaterThanResult = (getExpressionLeft().evaluate(program).getDoubleValue() > getExpressionRight().evaluate(program).getDoubleValue());
		return new BooleanLiteral(getLine(),getColumn(),greaterThanResult);
	}

}