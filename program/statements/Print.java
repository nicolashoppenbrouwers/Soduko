package worms.model.program.statements;

import worms.model.Worm;
import worms.model.program.Program;
import worms.model.program.expressions.Expression;
import worms.model.program.expressions.Literal;
import worms.model.program.types.Type;


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
		Type typeExpression = this.getExpression().evaluate(program).getType();
		if typeExpression.is
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
