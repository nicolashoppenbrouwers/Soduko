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

//JUMPSTEP herbekijken
//VRAAG: moeten bij subklassen ook nog de KlasseInvarianten gekopieerd worden van de superklasse of niet?
// Zo ja, dan moet ook overal (bij alle klassen!) de documentatie van de constructor worden aangepast!
//KLASSEINVARIANT DYNAMISCHE BINDING!!!
/**
 * An abstract class of Movable Game Objects as a special kind of GameObjects. In addition to the world,
 * position and radius, Movable GameObjects also have a direction they face.
 * 
 * @invar	The position of each moveable GameObject must be a valid position for a movable GameObject.
 * 			| isValidPosition(this.getPositionX())
 * 			| 	&& isValidPosition(this.getPositionY())
 * @invar	The direction of each movable GameObject must be a valid direction for a movable GameObject.
 * 			| isValidDirection(this.getDirection())
 * @invar	The radius of each movable GameObject must be a valid radius for a movable GameObject.
 * 			| isValidRadius(this.getRadius())
 * @invar	The direction of each movable GameObject must be a valid direction for a movable GameObject.
 * 			| isValidDirection(this.getDirection())
 * 
 * @author 	Nicolas Hoppenbrouwers
 * 			Bram Lust
 * @version 2.0
 */
public abstract class MovableGameObject extends GameObject {

	// IN ORDE
	/**
     *Initialize this new movable game object with given world, position, direction and damage.
     *
     * @param  	World
     *         	The world which contains the new MovableGameObject.
     * @param  	positionX
     *         	The x-coordinate of the position of the new MovableGameObject (meters).
     * @param  	positionY
     *         	The y-coordinate of the position of the new MovableGameObject (meters).
     * @param   direction
     * 			The direction which the new MovableGameObject is facing (radians).
     * @param	radius
     * 			The given radius of the new MovableGameObject (meters).
     * 
     * @effect 	This new MovableGameObject is initialized as a new GameObject with
     *         	given world, positionX, positionY and radius.
     *       	| super(world,positionX, positionY, radius)
     * @post   	The direction of this new MovableGameObject is equal to the given direction.
	 * 			| new.getDirection() == direction
     */
	@Raw
	public MovableGameObject(World world, double positionX, double positionY, double direction, double radius) {
		super(world,positionX, positionY, radius);
		this.setDirection(direction);
	}
	
	
	
	
	
	
	//IN ORDE
	/**
	 * Returns the minimal radius of this MovableGameObject.
	 */
	@Override
	public abstract double getMinimalRadius();
	
	
	
	
	
	
	
	//IN ORDE
	/**
	 * Returns the current direction this MovableGameObject is currently facing. 
	 * The direction is expressed as an angle in radians.
	 */
	@Basic
	public double getDirection(){
		return this.direction;
	}
	
	/**
	 * Turns this worm to let it face the given direction.
	 * 
	 * @param	The new direction for this MovableGameObject.
	 * @post	If the direction is a valid direction, 
	 * 			the direction of this GameObject will be equal to the given direction modulo 2 pi.
	 * 			| new.getDirection() == direction % (2*Math.PI)
	 * 			If new direction is negative, 2 pi will be added to make it positive.
	 * 			| if (direction % (2*Math.PI) < 0)
				| 	new.getDirection() = direction % (2*Math.PI) + 2*Math.PI
	 */
	public void setDirection(double direction){
		assert isValidDirection(direction);
		this.direction = direction % (2*Math.PI);
		if (this.getDirection() < 0)
			this.direction = this.getDirection() + 2*Math.PI;
	}
	
	/**
	 * Variable registering the direction of this MovableGameObject.
	 */
	private double direction;
	
	/**
	 * Checks whether the given direction is a valid direction for any MovableGameObject.
     *  
     * @param  	direction
     * 		   	The direction to check.
     * @return 	True if and only if the given direction is not equal to NaN.
     *       	| result == (position != Double.NaN)	
     */
	public static boolean isValidDirection(double direction){
		return !(Double.isNaN(direction));
	}
	
	
	
	
	
	
	
	// IN ORDE
	/**
	 * Return the mass of this MovableGameObject.
	 */
	public abstract double getMass();
	
	/**
	 * Return the density of this MovableGameObject.
	 */
	public abstract double getDensity();
	
	/**
	 * Return the force of this MovableGameObject.
	 */
	public abstract double getForce();
	
	
	/**
	 * Variable registering the standard acceleration.
	 */
	public final double g = 9.80665; 

	
	
	
	
	
	//JUMPPPPPPPPP
	
	public abstract double getJumpTime(double timeStep);
	
	public double[] jumpStep(double time){
		double initialVelocity = this.getInitialVelocity();
		double initialVelocityX = initialVelocity * Math.cos( this.getDirection() );
		double initialVelocityY = initialVelocity * Math.sin( this.getDirection() );
		double stepX = this.getPosition().getX() + initialVelocityX * time;
		double stepY = this.getPosition().getY() + initialVelocityY * time - 0.5 * g * Math.pow(time,2);
		return new double[] {stepX, stepY};
	}
	
	//DOCUMENTATIE EN METHODE
	/**
	 * Checks whether the given worm is able to jump.
	 * 
	 * @return 	True if and only if the worm has enough action points left to perform a jump
	 * 			and if the worm is not facing downwards.
	 * 			|return !((this.getDirection() > Math.PI) && (this.getDirection() < 2*Math.PI))
	 * 
	 */
	public abstract boolean canJump();	//((this.getDirection() > 0) && (this.getDirection() < Math.PI) && !(this.getActionPoints() == 0));
	
	//DOCUMENTATIE
	public double getInitialVelocity(){
		return (getForce() / getMass() * 0.5);
	}
}