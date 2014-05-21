package worms.model.program.statements;

import worms.model.Program;
import worms.model.program.expressions.DoubleLiteral;
import worms.model.program.expressions.Expression;

public class Fire extends Statement {
	// Exceptions

	public Fire(int line, int column, Expression yield) {
		super(line, column);
		this.yield = yield;
	}
	
	public Expression getYield() {
		return yield;
	}

	private final Expression yield;

	@Override
	public boolean executeStatement(Program program) throws IllegalStateException{
		if (program.getWorm().canShoot(((DoubleLiteral)this.getYield().evaluate(program)).getIntegerValue()) ){
			program.getHandler().fire(program.getWorm(),((DoubleLiteral)this.getYield().evaluate(program)).getIntegerValue());
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
