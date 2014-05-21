package worms.model.program.expressions;

import worms.model.GameObject;
import worms.model.program.Program;
import worms.model.program.types.Entity;

public class EntityLiteral extends Expression{

	public EntityLiteral(int line, int column, Entity entity){
		super(line,column);
		this.type = entity;
	}
	
	public EntityLiteral(int line, int column, GameObject gameObject) {
		super(line,column);
		this.type = new Entity(gameObject);
	}
	
	public Entity getType(){
		return this.type;
	}
	
	private final Entity type;
	
	public GameObject getGameObjectValue(){
		return this.getType().getValue();
	}

	//ONNODIGE methode, enkel voor consistentie.
	public Entity getResult(Program program){
		return getType();
	}
	
	@Override
	public EntityLiteral evaluate(Program program) {
		return this;
	}
	

}

