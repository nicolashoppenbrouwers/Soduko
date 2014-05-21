package worms.model.program.expressions;

import worms.model.program.Program;
import worms.model.program.types.Entity;

public class Null extends Expression{

	public Null(int line, int column) {
		super(line,column);
	}

	@Override
	public Entity getResult(Program program) {
		return new Entity(null);
	}

	@Override
	public EntityLiteral evaluate(Program program) {
		return new EntityLiteral(getLine(),getColumn(),getResult(program));
	}

}
