package worms.model.program.expressions;

import worms.model.program.Program;
import worms.model.program.types.Double;

public class Division extends Expression {
	
	public Division(int line, int column, Expression e1, Expression e2) {
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

	public Double getResult(Program program) throws IllegalStateException{
		//Division by zero.
		//Het kan zijn dat JAVA zelf al NaN teruggeeft bij deling door 0.
		if ( ((DoubleLiteral)getExpressionRight().evaluate(program)).getDoubleValue() == 0 )
			throw new IllegalStateException("Line: " + getLine() + " - Column: " + getColumn());
		return new Double( ((DoubleLiteral) getExpressionLeft().evaluate(program)).getDoubleValue() / 
						   ((DoubleLiteral) getExpressionRight().evaluate(program)).getDoubleValue() );
	}
	
	@Override
	public DoubleLiteral evaluate(Program program) throws IllegalStateException{
		return new DoubleLiteral(getLine(),getColumn(),getResult(program));
	}

}