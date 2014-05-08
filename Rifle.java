package worms.model;
import be.kuleuven.cs.som.annotate.*;
//NOG DOEN ----> NICOLAS
/**
 * @author Nicolas
 *
 */
public final class Rifle extends Projectile {

	/**
	 * @param world
	 * @param positionX
	 * @param positionY
	 * @param direction
	 * @param radius
	 * @param damage
	 */
	public Rifle(World world, double positionX, double positionY,
			double direction) {
		super(world, positionX, positionY, direction);
		this.setRadius(this.calculateRadius());
	}
	
	
	/**
 	 * Return the mass of this Rifle (in kilograms).
	 */
	@Basic @Override
	public double getMass(){
		return 0.010;
	}
	
	/**
	 * Return the force of this Rifle (in Newton).
	 */
	@Basic @Override
	public double getForce(){
		return 1.5;
	}
	
	/**
	 * Return the amount of hit points this Rifle subtracts.
	 */
	@Basic @Override
	public int getHitPointsDamage(){
		return 20;
	}
	
	/**
	 * Return the amount of action points it costs to use this Rifle.
	 */
	@Basic @Override
	public int getActionPointsCost(){
		return 10;
	}


	@Override
	public boolean canJump() {
		// TODO Auto-generated method stub
		return false;
	}

}
