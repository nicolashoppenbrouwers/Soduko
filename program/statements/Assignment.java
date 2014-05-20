package worms.model.program.statements;

import worms.model.Worm;
import worms.model.program.Program;
import worms.model.program.expressions.Expression;
import worms.model.program.expressions.Literal;
import worms.model.program.types.Type;

public class Assignment extends Statement{

	public Assignment(int line, int column, String variableName, Expression expression) {
		super(line, column);
		this.variableName = variableName;
		this.expression = expression;
	}
	
	private String getVariableName(){
		return this.variableName;
	}
	
	private String variableName;
	
	private Expression getExpression(){
		return this.expression;
	}
	
	private Expression expression;

	@Override
	public boolean containsActionStatement() {
		return false;
	}

	@Override
	public boolean isWellFormed() {
		return true;
	}

	@Override
	public boolean executeStatement(Program program) {
		program.getGlobals().put(this.getVariableName(),this.getExpression().evaluate(program).getType(program));
		return true;
	}

}
