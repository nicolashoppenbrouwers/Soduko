package worms.model.program.statements;


import worms.model.program.Program;
import worms.model.program.expressions.BooleanLiteral;
import worms.model.program.expressions.Expression;

public class Assignment extends Statement{

	public Assignment(int line, int column, String variableName, Expression expression) {
		super(line, column);
		this.variableName = variableName;
		this.expression = expression;
	}
	
	private String getVariableName(){
		return this.variableName;
	}
	
	private final String variableName;
	
	private Expression getExpression(){
		return this.expression;
	}
	
	private final Expression expression;

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
		program.getGlobals().put(this.getVariableName() , ((BooleanLiteral) this.getExpression().evaluate(program)).getType());
		return true;
	}

}
