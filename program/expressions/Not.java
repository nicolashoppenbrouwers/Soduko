package worms.model.program.expressions;

import worms.model.program.Program;

public class Not extends Expression<BooleanLiteral>{

	public Not(int line, int column, Expression<BooleanLiteral> e) {
		super(line,column);
		this.expression = e;
	}
	
	public Expression<BooleanLiteral> getExpression() {
		return this.expression;
	}

	private final Expression<BooleanLiteral> expression;

	
	@Override
	public BooleanLiteral evaluate(Program program) {
		Boolean notResult = !getExpression().evaluate(program).getValue();
		return new BooleanLiteral(getLine(),getColumn(),notResult);
	}

}
