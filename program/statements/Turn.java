package worms.model.program.statements;

import worms.model.program.Program;
import worms.model.program.expressions.DoubleLiteral;
import worms.model.program.expressions.Expression;

public class Turn extends Statement {

	public Turn(int line, int column,Expression angle) {
		super(line, column);
		this.angle = angle;
	}
	
	private Expression getAngle() {
		return angle;
	}

	private final  Expression angle;

	@Override
	public boolean executeStatement(Program program) {
		if (program.getWorm().canTurn(((DoubleLiteral)this.getAngle().evaluate(program)).getDoubleValue())){
			program.getHandler().turn(program.getWorm(), ((DoubleLiteral)this.getAngle().evaluate(program)).getDoubleValue());
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean containsActionStatement() {
		return true;
	}

	@Override
	public boolean isWellFormed() {
		return true;
	}

}
