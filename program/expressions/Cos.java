package worms.model.program.expressions;

import worms.model.Program;
import worms.model.program.types.Double;

public class Cos extends Expression {
	
	public Cos(int line, int column, Expression e) {
		super(line,column);
		this.expression = e;
	}
	
	public Expression getExpr() {
		return this.expression;
	}

	private final Expression expression;
	
	public Double getResult(Program program){
		return new Double (Math.cos( ((DoubleLiteral) getExpr().evaluate(program)).getDoubleValue()));
	}

	
	@Override
	public DoubleLiteral evaluate(Program program) {
		return new DoubleLiteral(getLine(),getColumn(),getResult(program));
	}

}
