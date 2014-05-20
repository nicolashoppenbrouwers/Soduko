package worms.model.program.expressions;

import worms.model.program.Program;
import worms.util.Util;

public class Equality extends Expression {
	
	public Equality(int line, int column, Expression e1, Expression e2) {
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
		return new Boolean(Util.fuzzyEquals(((DoubleLiteral)getExpressionLeft().evaluate(program)).getDoubleValue(), 
				((DoubleLiteral)getExpressionRight().evaluate(program)).getDoubleValue()));
	}
	
	@Override
	public BooleanLiteral evaluate(Program program) {
		return new BooleanLiteral(getLine(),getColumn(),getResult(program));
	}

}