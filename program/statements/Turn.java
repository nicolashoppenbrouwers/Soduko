package worms.model.program.statements;

import worms.model.program.Program;
import worms.model.program.expressions.DoubleLiteral;

public class Turn extends Statement {

	public Turn(int line, int column,DoubleLiteral angle) {
		super(line, column);
		this.angle = angle;
	}
	
	private DoubleLiteral getAngle() {
		return angle;
	}

	private final  DoubleLiteral angle;

	@Override
	public boolean executeStatement(Program program) {
		if (program.getWorm().canTurn(this.getAngle().getDoubleValue())){
			program.getHandler().turn(program.getWorm(), this.getAngle().getDoubleValue());
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
