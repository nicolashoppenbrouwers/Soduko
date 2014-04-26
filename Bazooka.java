/**
 * 
 */
package worms.model;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * @author Nicolas
 *
 */
public final class Bazooka extends Projectile {

	/**
	 * @param world
	 * @param positionX
	 * @param positionY
	 * @param direction
	 * @param radius
	 * @param propulsionYield
	 */
	public Bazooka(World world, double positionX, double positionY,
			double direction, int propulsionYield) {
		super(world, positionX, positionY, direction);
		this.setRadius(calculateRadius());
		this.setPropulsionYield(propulsionYield);
	}
	
	/**
	 * Return the propulsion yield of this projectile.
	 */
	public int getPropulsionYield(){
		return this.propulsionYield;
	}
	
	/**
	 * Set the propulsion yield of this projectile to the given value.
	 * @param 	propulsionYield
	 * 			The propulsion yield to set this projectile's propulsionYield to.
	 * 
	 */
	public void setPropulsionYield(int propulsionYield) throws IllegalArgumentException{
		if (! isValidPropulsionYield(propulsionYield))
			throw new IllegalArgumentException("Non effective propulsion yield!");
		this.propulsionYield = propulsionYield;
	}

	/**
	 * Check whether the given propulsion yield is a valid propulsion yield for any projectile.
	 * @param 	propulsion yield
	 * 			The propulsion yield to check.
	 * @return 	True if and only if the given propulsion yield is not equal to NaN,
	 * 			is bigger or equal to zero and is smaller or equal to 100.
	 */
	public boolean isValidPropulsionYield(int propulsionYield){
		return (propulsionYield >= 0) && (propulsionYield <= 100) && (! Double.isNaN(propulsionYield));
	}
	
	/**
	 * Variable registering the propulsion yield of this projectile. 
	 */
	public int propulsionYield;
	
	/**
 	 * Return the mass of this Rifle (in kilograms).
	 */
	@Basic @Override
	public double getMass(){
		return 0.300;
	}
	
	/**
	 * Return the force of this Rifle (in Newton).
	 * 
	 * 
	 */
	@Basic @Override
	public double getForce(){
			//throws IllegalArgumentException{
		//if (! isValidPropulsionYield(propulsionYield))
		//	throw new IllegalArgumentException("Non effective propulsion yield!");
		return 2.5 + this.getPropulsionYield() / 100.0 * 7.0;
	}
	
	/**
	 * Return the amount of hit points this Rifle subtracts.
	 */
	@Basic @Override
	public int getHitPointsDamage(){
		return 80;
	}
	
	/**
	 * Return the amount of action points it costs to use this Rifle.
	 */
	@Basic @Override
	public int getActionPointsCost(){
		return 50;
	}

	@Override
	public boolean canJump() {
		// TODO Auto-generated method stub
		return false;
	}

}
