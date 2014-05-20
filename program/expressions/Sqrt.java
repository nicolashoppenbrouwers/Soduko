package worms.model.program.expressions;

import worms.model.program.Program;

//EXCEPTION
public class Sqrt extends Expression {
	
	public Sqrt(int line, int column, Expression e) {
		super(line,column);
		this.expression = e;
	}
	
	public Expression getExpression() {
		return this.expression;
	}

	private final Expression expression;
	
	public Double getResult(Program program) throws IllegalStateException{
		// Vierkantswortel van een negatief getal.
		if ( ((DoubleLiteral) getExpression().evaluate(program)).getDoubleValue() < 0)
			throw new IllegalStateException("Line: " + getLine() + " - Column: " + getColumn());
		return new Double( Math.sqrt(((DoubleLiteral) getExpression().evaluate(program)).getDoubleValue()) );
	}

	@Override
	public DoubleLiteral evaluate(Program program) throws IllegalStateException {
		return new DoubleLiteral(getLine(),getColumn(),getResult(program));
	}

	@Override
	public String generateString(Program program) throws IllegalStateException {
		return getResult(program).toString();
	}

}
