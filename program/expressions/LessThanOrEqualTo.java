package worms.model.program.expressions;

import worms.model.program.Program;
import worms.util.Util;
import worms.model.program.types.Boolean;

public class LessThanOrEqualTo extends Expression {
	
	public LessThanOrEqualTo(int line, int column, Expression e1, Expression e2) {
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
		return new Boolean( Util.fuzzyLessThanOrEqualTo( ((DoubleLiteral) getExprLeft().evaluate(program)).getDoubleValue(), 
														 ((DoubleLiteral) getExprRight().evaluate(program)).getDoubleValue() ) ) ;
	}
	
	@Override
	public BooleanLiteral evaluate(Program program) {
		return new BooleanLiteral(getLine(),getColumn(),getResult(program));
	}

	@Override
	public String generateString(Program program) {
		return getResult(program).toString();
	}
}