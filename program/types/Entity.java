package worms.model.program.types;
import worms.model.GameObject;

public class Entity extends Type<GameObject> {

	//Default constructor
	public Entity() {
		super(null);
	}
	
	public Entity(GameObject gameObject){
		super(gameObject);
	}

	
}
