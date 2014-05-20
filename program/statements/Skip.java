package worms.model.program.statements;

import worms.model.program.Program;

public class Skip extends Statement {

	public Skip(int line, int column) {
		super(line, column);
	}

	@Override
	public boolean executeStatement(Program program) {
		return true;
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
