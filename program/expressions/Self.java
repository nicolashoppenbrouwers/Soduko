package worms.model.program.expressions;
import worms.model.types.*;
import worms.model.program.*;
//Zit nog een fout in.
import worms.model.program.types.Entity;

public class Self extends Expression<Entity> {
	
	public Self(int line, int column){
		super(line,column);
	}

	@Override
	public Entity evaluate(Program program) {
		return program.getWorm();
	}

}
