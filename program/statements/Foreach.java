package worms.model.program.statements;

import worms.model.Worm;
import worms.model.program.Program;

public class Foreach extends Statement{

	public Foreach(int line, int column ) {
		super(line, column);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Program program, Worm worm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsActionStatement() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean wellFormedStatement() {
		// TODO Auto-generated method stub
		return false;
	}

}
