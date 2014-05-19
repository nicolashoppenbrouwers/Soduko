package worms.model.program.statements;

import worms.model.Worm;
import worms.model.program.Program;
import worms.model.program.statements.Statement;
import be.kuleuven.cs.som.annotate.*;

import java.util.List;

public class Sequence extends Statement{
	
	Sequence(int line, int column, List <Statement> statements){
		super(line,column);
		this.setIndex(0);
		this.statements = statements;
		}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Basic
	public List<Statement> getStatements() {
		return statements;
	}
	
	private int index;
	
	private final List<Statement> statements;
	

	// misschien doet het moeilijk omdat java denkt dat ik misschien niet uit de while lus ga geraken
	@Override
	public boolean execute(Program program, Worm worm) {
		
		
		if (this.getStatements().size() == 0){
			return true;
		}
		
		while(this.getIndex() <= this.getStatements().size()-1){
			if (this.getStatements().get(index).executeWithCheck(program,worm)){
			this.setIndex(this.getIndex()+1);
			}
			else{
				return false;
			}
		}
		this.setIndex(0);
		return true;
	}

	@Override
	public boolean containsActionStatement() {
		for(Statement statement : this.getStatements()){
			if (statement.containsActionStatement()){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean welFormedStatement() {
		for(Statement statement : this.getStatements()){
			if (!(statement.welFormedStatement())){
				return false;
			}
		}
		return true;
	}
	
	
	
}
