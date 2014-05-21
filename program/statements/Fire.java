package worms.model.program.statements;

import worms.model.program.Program;
import worms.model.program.expressions.DoubleLiteral;

public class Fire extends Statement {
	// Exceptions

	public Fire(int line, int column, DoubleLiteral yield) {
		super(line, column);
		this.yield = yield;
	}
	
	public DoubleLiteral getYield() {
		return yield;
	}

	private final DoubleLiteral yield;

	@Override
	public boolean executeStatement(Program program) throws IllegalStateException{
		if (program.getWorm().canShoot( this.getYield().getIntegerValue()) ){
			program.getHandler().fire(program.getWorm(), this.getYield().getIntegerValue());
			// stel dat worm zich zelf dood schiet
			if (program.getWorm().isTerminated()){
				return false;
			}
			else {
				return true;
			}
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
