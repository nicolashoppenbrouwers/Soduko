package worms.model.program.statements;

import worms.model.program.Program;
import worms.model.program.ProgramFactoryImpl.ForeachType;
import worms.model.program.types.Type;

public class Foreach extends Statement{

	public Foreach(int line, int column,ForeachType type,String variableName,Statement body ) {
		super(line, column);
		this.type = type;
		this.variableName = variableName;
		this.body = body;
	}
	
	private ForeachType getTyp(){
		return this.type;
	}
	
	private ForeachType type;
	
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
		if this.g
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
