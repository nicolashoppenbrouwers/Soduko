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


/**
 * A class of food as a special kind of GameObjects, involving a world,
 *	 position, direction, and radius.
 *
 * @invar	The radius of each Food must be always equal to 0.20m.
 * 			| (this.getRadius() == 0.20)
 * 
 * @author 	Nicolas Hoppenbrouwers
 * 			Bram Lust
 * @version 2.0
 */
public class Food extends GameObject {
	
	// IN ORDE
	/**
	 * Initialize this new GameObject with given world, position and radius.
	 * @param 	world
	 * 			The world of the new food.
	 * @param 	positionX
	 * 			The x-coordinate of the position of the new food (in meters).
	 * @param 	positionY
	 * 			The y-coordinate of the position of the new food (in meters).
	 * @effect  This new food is initialized as a new GameObject with
     *         	given world, positionX and positionY, and 0.20 as radius.
     *       	| super(world,positionX, positionY, direction, 0.20)
	 */
	@Raw
	public Food(World world,double positionX, double positionY){
		super(world,positionX, positionY, 0.20);
	}

	
	
	
	
	
	/**
	 * Unset the world, if any, from this food.
	 *
	 * @effect  This food no longer has a world.
	 *        	| ! new.hasWorld()
	 * @effect  The former world of this food, if any, no longer
	 *          has this food as one of its foods.
	 *        	| (this.getWorld() == null)
	 *        	| 	|| (! (new this.getWorld()).hasAsFood(food))
	 */
	 @Override
	 public void unsetWorld(){
		if (this.hasWorld()){
			World formerWorld = this.getWorld();
			setWorld(null);
			formerWorld.removeFood(this);
		}
	}
	 
	 
	 
	 
	 
	 //IN ORDE
	/**
	 * Returns the minimal radius of this food.
	 *
	 * @return	Return the minimal radius of this food, more specifically, 0.20.
	 * 			| result == 0.20
	 */
	@Override
	public double getMinimalRadius() {
		return 0.20;
	}
	
	
	
	
	
	
	//IN ORDE
	/**
	 * Returns whether this food is still active in the game.
	 * @return	True if and only if this food is not terminated.
	 * 			| result ==  (! this.isTerminated())
	 */
	public boolean isActive(){
		return (! this.isTerminated());
	}

	
}
