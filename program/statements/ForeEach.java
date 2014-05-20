package worms.model.program.statements;

import worms.model.Worm;
import worms.model.program.Program;
import worms.model.program.types.Type;

public class ForeEach extends Statement{

	public ForeEach(int line, int column,Fore type,String variableName,Statement body ) {
		super(line, column);
		this.type = type;
		this.variableName = variableName;
		this.body = body;
	}
	
	private Type getTyp(){
		return this.type;
	}
	
	private Type type;
	
	private String getVariableName(){
		return this.variableName;
	}
	
	private String variableName;
	
	private Statement getBody(){
		return this.body;
	}
	
	private Statement body;

	@Override
	public boolean executeStatement(Program program) {
		teturn ;
	}

	@Override
	public boolean containsActionStatement() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWellFormed() {
		// TODO Auto-generated method stub
		return false;
	}
}
