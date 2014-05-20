package worms.model.program.statements;

import worms.model.program.Program;

public class ToggleWeap extends Statement {

	public ToggleWeap(int line, int column) {
		super(line, column);
	}

	@Override
	public boolean executeStatement(Program program) {
		program.getHandler().toggleWeapon(program.getWorm());
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
