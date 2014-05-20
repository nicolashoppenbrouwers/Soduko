package worms.model.program.types;
import worms.model.Food;
import worms.model.GameObject;
import worms.model.Projectile;
import worms.model.Worm;

public class Entity extends Type<GameObject> {

	//Default constructor
	public Entity() {
		super(null);
	}
	
	public Entity(GameObject gameObject){
		super(gameObject);
	}
	
	@Override
	public String toString(){
		if (this.getValue() instanceof Worm)
			return "Worm " + ((Worm) this.getValue()).getName() + " (Team " + ((Worm) this.getValue()).getTeam().getName() + ").";	
		else if (this.getValue() instanceof Projectile)
			return "Projectile located at (" + this.getValue().getPosition().getX() + "," + ((Worm) this.getValue()).getPosition().getY() + ").";
		else if (this.getValue() instanceof Food)
			return "Food located at (" + this.getValue().getPosition().getX() + "," + ((Worm) this.getValue()).getPosition().getY() + ").";
		else
			//De default toString()-methode van JAVA Object 
			return this.toString();
	}
}
