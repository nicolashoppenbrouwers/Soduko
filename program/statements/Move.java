package worms.model.program.statements;

import worms.model.program.Program;

public class Move extends Statement {

	public Move(int line, int column) {
		super(line, column);
	}

	@Override
	public boolean executeStatement(Program program) {
			program.getHandler().move(program.getWorm());
			if (program.getWorm().isTerminated()){
				return false;
			}
			else{
				return true;
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
