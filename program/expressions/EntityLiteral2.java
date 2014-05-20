package worms.model.program.expressions;
import worms.model.program.Program;
import worms.model.program.types.Entity;

public class EntityLiteral2 extends Literal<EntityLiteral2,Entity>{

	EntityLiteral2(Entity entityValue,int line, int column) {
		super(line, column);
		this.value = entityValue;
	}

	@Override
	public Entity getValue() {
		return this.value;
	}
	
	private Entity value;

	@Override
	public EntityLiteral2 evaluate(Program program) {
		return this;
	}

}
