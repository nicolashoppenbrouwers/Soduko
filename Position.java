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

//1 opmerking
/**
 * A value class of positions, involving an x- and y-coordinate.
 * 
 * @invar	Each position must be a valid position.
 * 			| isValidPosition(this.getX())
 * 			| 	&& isValidPosition(this.getY())
 * 
 * @author 	Nicolas Hoppenbrouwers
 * 			Bram Lust
 * @version 1.0 
 *
 */
@Value
public class Position {
	// IN ORDE
	/**
	 * Initialize this new position with x- and y-coordinate.
	 * 
	 * @param 	positionX
	 * 			The x-coordinate of the new position (in meters).
	 * @param 	positionY
	 * 			The y-coordinate of the new position (in meters).
	 * @effect	The x-coordinate of this new position is equal to the given X-position.
	 * 			| new.getX() == positionX
	 * @effect	The y-coordinate of this new position is equal to the given Y-position.
	 * 			| new.getY() == positionY
	 * @throws	IllegalArgumentException
	 * 			One, or both, of the given coordinates are invalid.
	 * 			| isValidPosition(postionX) || isValidPosition(positionY)
	 */
	@Raw
	public Position(double positionX, double positionY) throws IllegalArgumentException {
		this.setX(positionX);
		this.setY(positionY);
	}
	
	// IN ORDE
	/**
	 * Initialize this new position with the origin.
	 * 
	 * @effect	The x-coordinate of this new position is equal to zero.
	 * 			| new.getX() == 0
	 * @effect	The y-coordinate of this new position is equal to zero.
	 * 			| new.getY() == 0
	 */
	@Raw
	public Position(){
		this.setX(0);
		this.setY(0);
	}
	
	
	
	
	
	
	
	
	
	// IN ORDE
	/**
	 * Returns the current x-coordinate of this position (in meters).
	 */
	@Basic
	public double getX(){
		return this.positionX;
	}
		
	
	/**
	 * Returns the current y-coordinate of this position (in meters).
	 */
	@Basic
	public double getY(){
		return this.positionY;
	}
		
	
	/**
	 * Sets this position's x-coordinate to the given value in the Cartesian coordinate system.
	 * 
	 * @param	positionX
	 * 			The x-coordinate to set this position's x-coordinate to.
	 * @post	The position's x-coordinate is set to the given x-coordinate.
	 * 			| 	new.getPositionX == positionX
	 * @throws	IllegalArgumentException
	 * 			The given coordinate is an invalid position.
	 * 			| (!isValidPosition(positionX))
	 */
	public void setX(double positionX) throws IllegalArgumentException{
		if (!isValidPosition(positionX))
			throw new IllegalArgumentException("Non effective position!");
		this.positionX = positionX;
	}
	
		
	/**
	 * Sets this position's y-coordinate to the given value in the Cartesian coordinate system.
	 * 
	 * @param	positionY
	 * 			The y-coordinate to set this position's y-coordinate to.
	 * @post	The position's y-coordinate is set to the given y-coordinate.
	 * 			| 	new.getPositionY == positionY
	 * @throws	IllegalArgumentException
	 * 			The given coordinate is an invalid position.
	 * 			| (!isValidPosition(positionY))
	 */
	public void setY(double positionY) throws IllegalArgumentException{
		if (!isValidPosition(positionY))
			throw new IllegalArgumentException("Non effective position!");
		this.positionY = positionY;
	}
			
	
	/**
     * Variables registering the X and Y-coordinate of this GameObject.
     */
	private double positionX;
	private double positionY;
		
	
	/**
	 * Checks whether the given position is a valid position.
	 *  
	 * @param  	position
	 * 		   	The position to check.
	 * @return 	True if and only if the given position is not equal to NaN.
	 *       	| result == (position != Double.NaN)	
	 */
	public static boolean isValidPosition(double position){
		return !(Double.isNaN(position));
	}
	
	
	
	
	

	
	
	
	
	
	
	
	// IN ORDE
	/**
	 * Calculates and returns the distance between this position and the given position.
	 * 
	 * @param 	position
	 * 			The position to calculate the distance from.
	 * @return	The distance between this position and the given position.
	 * 			| result ==  ( Math.sqrt(Math.pow( this.getX() - position.getX() , 2) +  Math.pow( this.getY() - position.getY() , 2)) )
	 */
	public double calculateDistance(Position position) {
		/* Geen probleem met negatieve wortel want kwadraten zijn altijd positief*/
		return ( Math.sqrt(Math.pow( this.getX() - position.getX() , 2) +  Math.pow( this.getY() - position.getY() , 2)) );
	}
	
	//IN ORDE
	/**
	 * Calculates and returns the distance between this position and the given position.
	 * 
	 * @param 	positionX
	 * 			The x-coordinate of the position to calculate the distance from.
	 * @param 	positionY
	 * 			The y-coordinate of the position to calculate the distance from.
	 * @return	The distance between this position and the given position.
	 * 			| result ==  ( Math.sqrt(Math.pow( this.getX() - position.getX() , 2) +  Math.pow( this.getY() - position.getY() , 2)) )
	 */
	public double calculateDistance(double positionX, double positionY) {
		/* Geen probleem met negatieve wortel want kwadraten zijn altijd positief*/
		return ( Math.sqrt(Math.pow( this.getX() - positionX , 2) +  Math.pow( this.getY() - positionY , 2)) );
	}
	
	/**
	 * Calculates and returns the distance between two given positions.
	 * 
	 * @param 	position1
	 * 			The first of two positions to calculate the distance between.
	 * @param	position2
	 * 			The second of two positions to calculate the distance between.
	 * @return	The distance between the two given positions.
	 * 			| result ==  ( Math.sqrt(Math.pow( position1.getX() - position2.getX() , 2) +  Math.pow( position1.getY() - position2.getY() , 2)))
	 */
	public static double calculateDistance(Position position1, Position position2){
		/* Geen probleem met negatieve wortel want kwadraten zijn altijd positief*/
		return ( Math.sqrt(Math.pow( position1.getX() - position2.getX() , 2) +  Math.pow( position1.getY() - position2.getY() , 2)));
	}
}
