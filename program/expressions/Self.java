package worms.model.program.expressions;

import worms.model.Program;
import worms.model.program.types.Entity;

public class Self extends Expression {
	
	public Self(int line, int column){
		super(line,column);
	}


	@Override
	public Entity getResult(Program program) {
		return new Entity(program.getWorm());
	}

	@Override
	public EntityLiteral evaluate(Program program) {
		return new EntityLiteral( getLine(), getColumn(), getResult(program) );
	}


}
