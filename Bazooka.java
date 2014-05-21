/*
 * Assignment 'Worms' Object-Oriented Programming
 * University:		KU Leuven
 * Study:			Bachelor Ingenieurswetenschappen
 * Course:			Objectgericht Programmeren (H01P1A)
 * Year:			2013 - 2014
 * Authors: 		Nicolas Hoppenbrouwers 	(Computerwetenschappen - Werktuigkunde)
 * 					Bram Lust 				(Computerwetenschappen - Elektrotechniek)
 * Git: 			https://github.com/nicolashoppenbrouwers/Soduko.git
 */

package worms.model;
import be.kuleuven.cs.som.annotate.*;

// canJump?
//KLASSEINVARIANT DYNAMISCHE BINDING!!!
/**
 * A final class of bazookas as a special kind of Projectiles, involving a world,
 *	 position, direction, radius and a propulsion yield.
 * 
 * @invar	The propulsion yield of each rifle should be a valid propulsion yield for a rifle.
 * 			| isValidPropulsionYield(this.getName())
 * 
 * @author 	Nicolas Hoppenbrouwers
 * 			Bram Lust
 * @version 2.0
 */
public final class Bazooka extends Projectile {

	//KLAAR
	/**
     * Initialize this new bazooka with given world, position and direction.
     *
     * @param  	world
     *         	The world to contain the new bazooka.
     * @param  	positionX
     *         	The x-coordinate of the position of the new bazooka (meters).
     * @param  	positionY
     *         	The y-coordinate of the position of the new bazooka (meters).
     * @param   direction
     * 			The direction which the new bazooka is facing (radians).
     * @param 	propulsionYield
     * 			The propulsion yield of the new bazooka.
     * @effect 	This new bazooka is initialized as a new projectile with
     *         	given world, positionX, positionY and direction, and calculated radius.
     *       	| super(world, positionX, positionY, direction);
     * @effect 	The propulsion yield of this new bazooka is set to the calculated radius.
     *       	| new.getPropulsionYield() = propulsionYield
     */
	@Raw
	public Bazooka(World world, double positionX, double positionY,
			double direction, int propulsionYield) {
		super(world, positionX, positionY, direction);
		this.setPropulsionYield(propulsionYield);
	}
	
	
	
	
	
	//KLAAR
	/**
	 * Return the propulsion yield of this bazooka.
	 */
	@Basic
	public int getPropulsionYield(){
		return this.propulsionYield;
	}
	
	/**
	 * Set the propulsion yield of this bazooka to the given value.
	 * 
	 * @param 	propulsionYield
	 * 			The propulsion yield to set this bazooka's propulsionYield to.
	 * @post	This bazooka's new propulsion yield is equal to the given propulsion yield.
	 * 			| 	new.getPropulsionYield() = propulsionYield
	 * @throws 	IllegalArgumentException
	 * 			The given propulsion yield is an invalid propulsion yield.
	 * 			| (!isValidPropulsionYield(propulsionYield))
	 */
	public void setPropulsionYield(int propulsionYield) throws IllegalArgumentException{
		if (! isValidPropulsionYield(propulsionYield))
			throw new IllegalArgumentException("Non effective propulsion yield!");
		this.propulsionYield = propulsionYield;
	}

	/**
	 * Check whether the given propulsion yield is a valid propulsion yield for any bazooka.
	 * 
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
	
	
	
	
	
	
	//KLAAR
	/**
 	 * Return the mass of this bazooka (in kilograms).
	 */
	@Basic @Override
	public double getMass(){
		return 0.300;
	}
	
	//KLAAR
	/**
	 * Return the force of this bazooka (in Newton).
	 * 
	 * @return	The force of this rifle, depending on the propulsion yield.
	 * 			| result == (2.5 + this.getPropulsionYield() / 100.0 * 7.0)
	 * 
	 * 
	 */
	@Basic @Override
	public double getForce(){
		return 2.5 + this.getPropulsionYield() / 100.0 * 7.0;
	}
	
	
	
	
	
	
	//KLAAR
	/**
	 * Return the amount of hit points this bazooka subtracts.
	 */
	@Basic @Override
	public int getHitPointsDamage(){
		return 80;
	}
	
	/**
	 * Return the amount of action points it costs to use this bazooka.
	 */
	@Basic @Override
	public int getActionPointsCost(){
		return 50;
	}

	
	
	
	
	
	//WAT DOET DEZE FUNCTIE HIER?
	@Override
	public boolean canJump() {
		// TODO Auto-generated method stub
		return false;
	}

}
