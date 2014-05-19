package worms.model.program.statements;

import worms.model.Worm;
import worms.model.program.Program;
import worms.model.program.expressions.Expression;

public class Print extends Statement{

	public Print(int line, int column,Expression<? extends Expression> expression) {
		super(line, column);
		this.expression = expression;
		}
	
	public Expression<? extends Expression> getExpression() {
		return expression;
	}
	
	private final Expression<? extends Expression> expression;
	
	
	// Ik zit hier nog wat in de knoei met die generische klassen
	@Override
	public boolean execute(Program program, Worm worm) {
		System.out.println((Expression)(this.getExpression().evaluate(program)).getValue());
		return true;
	}

	@Override
	public boolean containsActionStatement() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean welFormedStatement() {
		return true;
	}

}
