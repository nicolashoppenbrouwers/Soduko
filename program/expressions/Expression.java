package worms.model.program.expressions;
import worms.model.program.Program;
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
	
//	public T getValueType(){
//		return this.type();
//	}
	
//	public Type getType(Program program){
//		return this.evaluate(program).getType();
//	}
	//<?> klopt toch he?
	
	public abstract Type<?> getResult(Program program);
	
	//We geven als argument een program mee om aan de globale variabelen te komen?
	public abstract Expression evaluate(Program program);
	// WORDT public abstract Literla evaluate(Program program);


	public abstract String generateString(Program program);
}
