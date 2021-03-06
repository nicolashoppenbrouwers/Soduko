package worms.model.program.expressions;

import worms.model.Program;
import worms.model.program.types.Boolean;


public class BooleanLiteral extends Expression {
	public BooleanLiteral(int line, int column, Boolean bool){
		super(line,column);
		this.type = bool;
	}
	
	public BooleanLiteral(int line, int column, boolean booleanValue) {
		super(line,column);
		this.type = new Boolean(booleanValue);
	}
	
	public Boolean getType(){
		return this.type;
	}
	
	private final Boolean type;
	
	public boolean getBooleanValue(){
		return this.getType().getValue();
	}
	
	//ONNODIGE methode, enkel voor consistentie.
	public Boolean getResult(Program program){
		return getType();
	}

	@Override
	public BooleanLiteral evaluate(Program program) {
		return this;
	}
}
