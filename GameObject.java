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

//Alles met dynamische binding: world (en miss terminate)
//documentatie laatste 4 methodes
//KLASSEINVARIANT DYNAMISCHE BINDING!!!
/**
 * An abstract class of GameObjects for playing the game Worms, involving a World, a position and a radius.
 * 
 * @invar	The position of each GameObject must be a valid position for a GameObject.
 * 			| isValidPosition(this.getPositionX())
 * 			| 	&& isValidPosition(this.getPositionY())
 * @invar	The direction of each GameObject must be a valid direction for a GameObject.
 * 			| isValidDirection(this.getDirection())
 * @invar	The radius of each GameObject must be a valid radius for a GameObject.
 * 			| isValidRadius(this.getRadius())
 * 
 * @author 	Nicolas Hoppenbrouwers
 * 			Bram Lust
 * @version 2.0
 */
public abstract class GameObject {
	// IN ORDE
	/**
	 * Initialize this new GameObject with given world, position and radius.
	 * 
	 * @param	world
	 * 			The world of the new GameObject.
	 * @param 	positionX
	 * 			The x-coordinate of the position of the new GameObject (in meters).
	 * @param 	positionY
	 * 			The y-coordinate of the position of the new GameObject (in meters).
	 * @param 	radius
	 * 			The radius of the new GameObject (in meters).
	 * @effect 	The world of this new GameObject is equal to the given world.
	 * 			| new.getWorld() == world
	 * @effect	The x-coordinate of this new GameObject is equal to the given X-coordinate.
	 * 			| new.getPosition().getX() == positionX
	 * @effect	The y-coordinate of this new GameObject is equal to the given Y-coordinate.
	 * 			| new.getPosition().getY() == positionY
	 * @effect	The direction of this new GameObject is equal to the given direction.
	 * 			| new.getDirection() == direction
	 * @effect	The radius of this new GameObject is equal to the given radius.
	 * 			| new.getRadius() == radius
	 */
	@Raw
	public GameObject(World world, double positionX, double positionY, double radius){
		this.setWorld(world);
		this.setPosition(positionX,positionY);
		this.setRadius(radius);
	}
	
	
	
	
	
	
	
	
	
	
	// IN ORDE
	/**
	 * Check whether this GameObject is terminated. 
	 */
	@Basic
	public boolean isTerminated(){
		return (! this.hasWorld());
	}
	
	
	/**
	 * Terminate this GameObject.
	 * @effect	The world, if any, is unset from this GameObject.
	 * 			| this.unsetWorld()
	 */
	public void terminate(){
		this.unsetWorld();
	}
	

	
	
	
	
	
	
	
	
	
	
	

	
	//NOG HELEMAAL NAKIJKEN!!!!
	//ONAFGEWERKT.
	//DEZE METHODE KLOPT NOG NIET HELEMAAL MET OWNABLE. DAAR OOK SETWORLDTO
	//MISSCHIEN NOG TE IMPLEMENTEREN:
		// canHavaAsWorld()
		// hasProperWorld()
	/**
	 * Return the world of this GameObject.
	 */
	@Basic
	public World getWorld() {
		return this.world;
	}


	/**
	 * Set the world of this GameObject to the given world.
	 */
	// Moet @Raw bij het argument World?
	// Voorlopig @Basic, tenzij er een checker bijkomt
	@Basic
	public void setWorld(@Raw World world) {
		//if ! CanHaveAsWorld(world)
		// throw blabla..???
		//if (this.hasWorld())
		//	throw new IllegalStateException("This GameObject already has a world!");
		this.world = world;
		//world.addWorm(this) ofzo...???
	}
	
	
	/**
	 * Unset the world, if any, from this GameObject.
	 */
	public abstract void unsetWorld();
	
	
	/**
	 * Check whether this GameObject has a world.
	 * 
	 * @return	True if and only if the world of this GameObject is effective.
	 * 			| (this.getWorld() != null)
	 */
	//@Raw ?
	public boolean hasWorld(){
		return (this.getWorld() != null);
	}
	
	
//	/**
//	 * Check whether this ownable can have the given owner
//	 * as its owner.
//	 *
//	 * @param   owner
//	 *          The owner to check.
//	 * @return  If this ownable is terminated, true if and only if
//	 *          the given owner is not effective.
//	 *        | if (this.isTerminated())
//	 *        |   then result == (owner == null)
//	 *          Otherwise, true if and only if the given owner is
//	 *          either not effective or not terminated.
//	 *        | else result ==
//	 *        |   (owner == null) || (! owner.isTerminated())
//	 */
//	@Raw
//	public boolean canHaveAsWorld(World world) {
//		if (this.isTerminated())
//			return (world == null);
//		return (world == null) || (!world.isTerminated());
//	}
	//OOK NOG WORLD TERMINATE FUNCTIE SCHRIJVEN WRS!
	
	
	/**
	 * Variable registering the world of this GameObject.
	 */
	protected World world;
	
	
	

	
	
	
	
	
	
	
	
	
	
	

	
	// Documentatie setPosition
	/**
	 * Returns the current position of this GameObject.
	 */
	@Basic
	public Position getPosition(){
		return this.position;
	}
	
	public void setPosition(Position position) throws IllegalArgumentException{
		setPosition(position.getX(),position.getY());
	}
	
	/**
	 * Sets this GameObject's position to the given position.
	 * 
	 * @param 	positionX
	 * 			The x-coordinate to set this GameObject's x-position to.
	 * @param 	positionY
	 * 			The y-coordinate to set this GameObject's y-position to.
	 * @post	If the new position of this GameObject is outside out of this world,
	 * 			the worm will be terminated.
	 * 			| if (this.getWorld().outOfWorld(this.getPosition().getX(), this.getPosition().getY(), this.getRadius()))
	 * 			| 	this.terminate();
	 * 			Otherwise, this GameObject's new position is equal to the given position.
	 * 			| 	new.getPosition() = Position(positionX,positionY)
	 * @throws 	IllegalArgumentException
	 * 			The given position is an invalid position.
	 * 			| (!isValidPosition(positionX))
	 * 			| 	|| (!isValidPosition(positionY))
	 */
	public void setPosition(double positionX, double positionY) throws IllegalArgumentException{
		if (this.getWorld().outOfWorld(positionX, positionY, this.getRadius()))
			this.terminate();
		this.position = new Position(positionX,positionY);
	}
		
	
    /**
     * Variable registering position of this GameObject.
     */
	private Position position;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// IN ORDE
	/**
	 * Returns the radius of this GameObject (meters).
	 */
	@Basic
	public double getRadius(){
		return this.radius;
	}
	
	
	/**
	 * Sets the radius of this GameObject to the given value.
	 * 
	 * @param 	radius
	 * 			The radius to set this GameObject's radius to.
	 * @post	The new radius of this GameObject is equal to the given radius.
	 * 			| 	new.getRadius() = radius
	 * @throws 	IllegalArgumentException
	 * 			The given radius is an invalid radius for any GameObject.
	 * 			| (!isValidRadius(radius))
	 */
	public void setRadius(double radius) throws IllegalArgumentException{
		if (!isValidRadius(radius))
			throw new IllegalArgumentException("Non effective radius!"); 
		this.radius = radius;
	}
	
	
	/**
	 * Variable registering the radius of this GameObject.
	 */
	private double radius;

	
	/**
	 * Checks whether the given radius is a valid radius for any GameObject.
	 * 
	 * @param 	radius
	 * 			The radius to check.
	 * @return	True if and only if the given radius is not equal to NaN and the given radius is larger or equal to the minimal radius.
	 * 			| result == ((radius != Double.NaN) && (radius >= this.getMinimalRadius()))
	 */
	public boolean isValidRadius(double radius){
		return !(Double.isNaN(radius)) && (radius >= getMinimalRadius());
	}
	

	/**
	 * Returns the minimal radius of this GameObject.
	 */
	@Immutable
	public abstract double getMinimalRadius();
	
	
	

	
	//THROWS
	/**
	 * Checks whether the given position is close to a worm.
	 * 
	 * @param 	x
	 * 			The x-coordinate of the given position.
	 * @param 	y
	 * 			The y-coordinate of the given position.
	 * @return	True if and only if the given position is close to a worm.
	 * 			| for (Worm worm: this.getWorld().getWorms()){
	 * 			|	if (Math.pow(x - worm.getPosition().getX(), 2) + Math.pow(y - worm.getPosition().getY(), 2) <=
	 * 			|			Math.pow(this.getRadius() + worm.getRadius(), 2)){
	 * 			| 		result == true;
	 * 			|   }
	 * 			| result == false
	 */
	public boolean isCloseToWorm(double x,double y){
		try{
			for (Worm worm: this.getWorld().getWorms()) {
				if (Math.pow(x - worm.getPosition().getX(), 2) + Math.pow(y - worm.getPosition().getY(), 2) <=
						Math.pow(this.getRadius() + worm.getRadius(), 2)){
					return true;
				}
			}		
			return false;
		}
		catch (NullPointerException exc){
			return false;
		}
	}
	
	/**
	 * Returns a worm that is nearby to the given position, if any.
	 * 
	 * @param	x
	 * 			The x-coordinate of the position.
	 * @param	y 
	 * 			The y-coordinate of the position.
	 * @return 	A worm that is nearby to the given position.
	 * 			| for (Worm worm: this.getWorld().getWorms()){
	 * 			|	if (Math.pow(x - worm.getPosition().getX(), 2) + Math.pow(y - worm.getPosition().getY(), 2) <
	 *			|				Math.pow(this.getRadius()+worm.getRadius(), 2))
	 * 			|		result == worm
	 * 			| result == null
	 */
	public Worm getNearbyWorm(double x,double y){
		for (Worm worm: this.getWorld().getWorms()){
			if (Math.pow(x - worm.getPosition().getX(), 2) + Math.pow(y - worm.getPosition().getY(), 2) <
					Math.pow(this.getRadius()+worm.getRadius(), 2)){
				return worm;
			}
		}
		return null;
	}
	
	/**
	 * Checks whether this game object is close to a food.
	 * 
	 * @return	True if and only if this GameObject is close to a food.
	 * 			| for (Food food: this.getWorld().getWorms()){
	 * 			|	if (Math.pow(this.getPosition().getX() - food.getPosition().getX(), 2) + Math.pow(this.getPosition().getY() - food.getPosition().getY(), 2) <=
	 * 			|			Math.pow(this.getRadius() + food.getRadius(), 2)){
	 * 			| 		result == true;
	 * 			|   }
	 * 			| result == false
	 */
	public boolean isCloseToFood(){
		try{
		for (Food food:this.getWorld().getFood()){
			if (Math.pow(this.getPosition().getX() - food.getPosition().getX(), 2) + Math.pow(this.getPosition().getY() - food.getPosition().getY(), 2) <=
					Math.pow(this.getRadius()+food.getRadius(), 2)){
				return true;
			}
		}
		return false;
		}
		catch(NullPointerException exc){
			return false;
		}
	}
	
	/**
	 * Returns a food that is nearby to this GameObject, if any.
	 * 
	 * @return 	A food that is nearby to the given position.
	 * 			| for (Food food: this.getWorld().getWorms()){
	 * 			|	if (Math.pow(this.getPosition().getX() - food.getPosition().getX(), 2) + Math.pow(this.getPosition().getY() - food.getPosition().getY(), 2) <
	 *			|				Math.pow(this.getRadius()+food.getRadius(), 2)){
	 * 			|		result == food
	 * 			| result == null
	 */ 
	public Food getNearbyFood(){
		for (Food food: this.getWorld().getFood()){
			if (Math.pow(this.getPosition().getX() - food.getPosition().getX(), 2) + Math.pow(this.getPosition().getY() - food.getPosition().getY(), 2) <
					Math.pow(this.getRadius()+food.getRadius(), 2)){
				return food;
			}
		}
		return null;
	}
}
