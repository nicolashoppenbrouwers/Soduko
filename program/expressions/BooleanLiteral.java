package worms.model.program.expressions;

import worms.model.program.Program;

//HERBEKIJKEN!!!!

// extends Expression<Boolean>
public class BooleanLiteral extends Expression<BooleanLiteral> {

	public BooleanLiteral(int line, int column, boolean booleanValue) {
		super(line,column);
		this.value = booleanValue;
	}
	
	public boolean getValue(){
		return this.value;
	}
	
	private final boolean value;

	@Override
	public BooleanLiteral evaluate(Program program) {
		return this;
	}
}
