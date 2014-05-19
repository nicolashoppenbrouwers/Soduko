package worms.model.program.types;
import worms.model.GameObject;
import worms.model.program.expressions.Expression;

public class Entity extends Type<GameObject> {

	//Default constructor
	public Entity() {
		super(null);
	}
	
	public Entity(GameObject gameObject){
		super(gameObject);
	}

	@Override
	public Expression<Entity> convertToExpression(int line, int column) {
		return EntityLiteral(line,column,this.getValue());
	}
	
}
