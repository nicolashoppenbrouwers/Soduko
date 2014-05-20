package worms.model.program.expressions;

import worms.model.program.Program;

public class Cos extends Expression {
	
	public Cos(int line, int column, Expression e) {
		super(line,column);
		this.expression = e;
	}
	
	public Expression getExpression() {
		return this.expression;
	}

	private final Expression expression;
	
	public Double getResult(Program program){
		return new Double (Math.cos( ((DoubleLiteral) getExpression().evaluate(program)).getDoubleValue()));
	}

	
	@Override
	public DoubleLiteral evaluate(Program program) {
		return new DoubleLiteral(getLine(),getColumn(),getResult(program));
	}

}
