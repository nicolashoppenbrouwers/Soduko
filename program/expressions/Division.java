package worms.model.program.expressions;

import worms.model.program.Program;
import worms.model.program.types.Double;

//EXCEPTION
public class Division extends Expression {
	
	public Division(int line, int column, Expression e1, Expression e2) {
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

	public Double getResult(Program program) throws IllegalStateException{
		//Division by zero.
		if ( ( (DoubleLiteral) getExprRight().evaluate(program)).getDoubleValue() == 0 )
			throw new IllegalStateException("Line: " + getLine() + " - Column: " + getColumn());
		return new Double( ((DoubleLiteral) getExprLeft().evaluate(program)).getDoubleValue() / 
						   ((DoubleLiteral) getExprRight().evaluate(program)).getDoubleValue() );
	}
	
	@Override
	public DoubleLiteral evaluate(Program program) throws IllegalStateException{
		return new DoubleLiteral(getLine(),getColumn(),getResult(program));
	}

	@Override
	public String generateString(Program program) throws IllegalStateException {
		return getResult(program).toString();
	}
}