package worms.model.program.expressions;

import worms.model.Program;
import worms.model.program.types.Boolean;

public class Not extends Expression{

	public Not(int line, int column, Expression e) {
		super(line,column);
		this.expression = e;
	}
	
	public Expression getExpression() {
		return this.expression;
	}

	private final Expression expression;
	
	public Boolean getResult(Program program){
		return new Boolean( ! ((BooleanLiteral) getExpression().evaluate(program)).getBooleanValue() );
	}

	
	@Override
	public BooleanLiteral evaluate(Program program) {
		return new BooleanLiteral(getLine(),getColumn(),getResult(program));
	}
	



}
