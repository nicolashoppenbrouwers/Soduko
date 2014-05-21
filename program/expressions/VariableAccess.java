package worms.model.program.expressions;

import worms.model.program.Program;
import worms.model.program.types.Boolean;
import worms.model.program.types.Double;
import worms.model.program.types.*;
//?????
public class VariableAccess extends Expression {

	public VariableAccess(int line, int column, String name) {
		super(line,column);
		this.variableName = name;
	}
	
	private String getVariableName() {
		return this.variableName;
	}
	
	private final String variableName;

	@Override
	public Expression evaluate(Program program) {
		if (this.getResult(program) instanceof Boolean)
			return new BooleanLiteral(getLine(),getColumn(),(Boolean)getResult(program));
		if (this.getResult(program) instanceof Double)
			return new DoubleLiteral(getLine(),getColumn(),(Double)getResult(program));
		else
			return new EntityLiteral(getLine(),getColumn(),(Entity)getResult(program));
	}

	@Override
	public Type<?> getResult(Program program) {
		return program.getGlobals().get(this.getVariableName());
	}

}
