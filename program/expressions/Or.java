package worms.model.program.expressions;

import worms.model.program.Program;

public class Or extends Expression{

	public Or(int line, int column, Expression e1, Expression e2) {
		super(line,column);
		this.expressionLeft = e1;
		this.expressionRight = e2;
	}
	
	public Expression getExprLeft() {
		return this.expressionLeft;
	}

	public Expression getExprRight() {
		return this.expressionRight;
	}

	private final Expression expressionLeft;
	private final Expression expressionRight;

	public Boolean getResult(Program program){
		return new Boolean( ((BooleanLiteral) getExprLeft().evaluate(program)).getBooleanValue() || 
							((BooleanLiteral) getExprRight().evaluate(program)).getBooleanValue() );
	}
	
	@Override
	public BooleanLiteral evaluate(Program program) {
		return new BooleanLiteral(getLine(),getColumn(),getResult(program));
	}

}
