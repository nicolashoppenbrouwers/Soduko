package worms.model.program.expressions;

import worms.model.Program;
import worms.model.program.types.Double;

public class Sin extends Expression {
	
	public Sin(int line, int column, Expression e) {
		super(line,column);
		this.expression = e;
	}
	
	public Expression getExpression() {
		return this.expression;
	}

	private final Expression expression;

	public Double getResult(Program program){
		return new Double( Math.sin(((DoubleLiteral) getExpression().evaluate(program)).getDoubleValue()) );
	}
	
	@Override
	public DoubleLiteral evaluate(Program program) {
		return new DoubleLiteral(getLine(),getColumn(),getResult(program));
	}
	



}
