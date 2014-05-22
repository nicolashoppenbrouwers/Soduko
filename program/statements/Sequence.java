package worms.model.program.statements;

import java.util.List;

import worms.model.Program;

public class Sequence extends Statement{
	
	public Sequence(int line, int column, List <Statement> statements){
		super(line,column);
		this.setIndex(0);
		this.statements = statements;
	}
	
	private int getIndex() {
		return index;
	}

	private void setIndex(int index) {
		this.index = index;
	}
	
	private int index;
	
	

	private List<Statement> getStatements() {
		return statements;
	}
	
	private final List<Statement> statements;
	


	@Override
	public boolean executeStatement(Program program) {
		
		
		if (this.getStatements().size() == 0){
			return true;
		}
		
		while(this.getIndex() <= this.getStatements().size()-1){
			if (this.getStatements().get(index).execute(program)){
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
	public boolean isWellFormed() {
		for(Statement statement : this.getStatements()){
			if (!(statement.isWellFormed())){
				return false;
			}
		}
		return true;
	}
	
	
	
}
