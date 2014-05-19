package worms.model.program.expressions;
import worms.model.program.Program;
public abstract class Expression<T>{

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
	
	//We geven als argument een program mee om aan de globale variabelen te komen?
	public abstract T evaluate(Program program);


	// een methode toString voor alle expressies?
}
