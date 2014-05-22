package worms.model.program.expressions;
import worms.model.Program;
import worms.model.program.types.Type;
public abstract class Expression{

	public Expression(int line, int column) {
		this.line = line;
		this.column = column;
	}
	
	public int getLine() {
		return this.line;
	}
 
	public int getColumn() {
		return this.column;
	}
	
	private final int line;
	private final int column;
	
	public abstract Type<?> getResult(Program program);
	
	public abstract Expression evaluate(Program program);


	public String generateString(Program program){
		return (getResult(program).toString());
	}
}
