package worms.model.program.statements;

import worms.model.Worm;
import worms.model.program.Program;
import worms.model.program.expressions.DoubleLiteral;
import worms.model.program.expressions.BooleanLiteral;
import worms.model.program.expressions.Expression;


public class Print extends Statement{

	public Print(int line, int column,Expression<DoubleLiteral,BooleanLiteral> expression) {
		super(line, column);
		this.expression = expression;
		}
	
	public Expression<DoubleLiteral,BooleanLiteral> getExpression() {
		return expression;
	}
	
	private final Expression<DoubleLiteral,BooleanLiteral>  expression;
	
	
	// Ik zit hier nog wat in de knoei met die generische klassen
	@Override
	public boolean execute(Program program, Worm worm) {
		System.out.println((this.getExpression().evaluate(program)).getValue());
		return true;
	}

	@Override
	public boolean containsActionStatement() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean wellFormedStatement() {
		return true;
	}

}
