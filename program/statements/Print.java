package worms.model.program.statements;


import worms.model.program.Program;
import worms.model.program.expressions.Expression;



public class Print extends Statement{

	public Print(int line, int column,Expression expression) {
		super(line, column);
		this.expression = expression;
		}
	
	public Expression getExpression() {
		return expression;
	}
	
	private final Expression  expression;
	
	
	@Override
	public boolean executeStatement(Program program){
		System.out.println(this.getExpression().generateString(program));
		return true;
	}

	@Override
	public boolean containsActionStatement() {
		return false;
	}

	@Override
	public boolean isWellFormed() {
		return true;
	}

}
