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

//canJump?
//KLASSEINVARIANT DYNAMISCHE BINDING!!!

/**
 * A final class of rifles as a special kind of Projectiles, involving a world,
 *	 position, direction and a radius.
 *
 * @invar	The position of each rifle must be a valid position for a rifle.
 * 			| isValidPosition(this.getPositionX())
 * 			| 	&& isValidPosition(this.getPositionY())
 * @invar	The direction of each rifle must be a valid direction for a rifle.
 * 			| isValidDirection(this.getDirection())
 * @invar	The radius of each rifle must be a valid radius for a rifle.
 * 			| isValidRadius(this.getRadius())
 * @invar	The direction of each rifle must be a valid direction for a rifle.
 * 			| isValidDirection(this.getDirection())
 * @invar	The propulsion yield of each rifle should be a valid propulsion yield for a rifle.
 * 			| isValidPropulsionYield(this.getPropulsionYield())
 * 
 * @author 	Nicolas Hoppenbrouwers
 * 			Bram Lust
 * @version 2.0
 */
public final class Rifle extends Projectile {

	//KLAAR
	/**
	 * Initialise this new rifle with given world, position and direction.
	 * @param 	world
	 * 			The world to contain the new rifle.
	 * @param 	positionX
     *         	The x-coordinate of the position of the new rifle (meters).
     * @param  	positionY
     *         	The y-coordinate of the position of the new rifle (meters).
	 * @param 	direction
	 * 			The direction which the new bazooka is facing (radians).
     * @effect 	This new rifle is initialized as a new projectile with
     *         	given world, positionX, positionY and direction, and calculated radius.
     *       	| super(world, positionX, positionY, direction);
	 */
	public Rifle(World world, double positionX, double positionY,
			double direction) {
		super(world, positionX, positionY, direction);
	}
	
	
	//KLAAR
	/**
 	 * Return the mass of this rifle (in kilograms).
	 */
	@Basic @Override
	public double getMass(){
		return 0.010;
	}
	
	/**
	 * Return the force of this rifle (in Newton).
	 */
	@Basic @Override
	public double getForce(){
		return 1.5;
	}
	
	
	
	//KLAAR
	/**
	 * Return the amount of hit points this rifle subtracts.
	 */
	@Basic @Override
	public int getHitPointsDamage(){
		return 20;
	}
	
	/**
	 * Return the amount of action points it costs to use this rifle.
	 */
	@Basic @Override
	public int getActionPointsCost(){
		return 10;
	}

}
