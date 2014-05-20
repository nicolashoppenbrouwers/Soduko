package worms.model.program.statements;

import worms.model.Worm;
import worms.model.program.Program;
import worms.model.program.expressions.Expression;
import worms.model.program.expressions.Literal;


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
	public boolean execute(Program program, Worm worm) {
		System.out.println(this.getExpression().getValue());
		return true;
	}

	@Override
	public boolean containsActionStatement() {
		return false;
	}

	@Override
	public boolean wellFormedStatement() {
		return true;
	}

}
