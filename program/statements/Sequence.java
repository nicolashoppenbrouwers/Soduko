package worms.model.program.statements;

import worms.model.program.expressions.Expression;
import worms.model.program.Program;
import worms.model.program.statements.Statement;
import be.kuleuven.cs.som.annotate.*;

import java.util.List;

public class Sequence extends Statement{
	
	Sequence(int line, int column, List<Statement> statements){
		super(line,column);
		this.statements = statements;
		}
	
	@Basic
	public List<Statement> getStatements() {
		return statements;
	}

	private final List<Statement> statements;
	
	
	
}
