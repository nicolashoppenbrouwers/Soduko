package worms.model.program.expressions;

import worms.model.program.Program;
import worms.model.program.types.Type;
//?????
public class VariableAccess extends Expression<DoubleLiteral or BooleanLiteral> {

	public VariableAccess(int line, int column, String name, Type T) {
		super(line,column);
		this.name = name;
		this.type = T;
	}
	
	public String getName(){
		return this.name;
	}
	
	private final String name;
	
	public Type getType(){
		return this.type;
	}
	
	private final Type type;
	
	
	//?
	@Override
	public Expression<?> evaluate(Program program) {
		return program.getGlobals().get(getName()).convertToExpression();
	}

}
