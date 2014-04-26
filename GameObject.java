package worms.model;
import be.kuleuven.cs.som.annotate.*;

/**
 * An abstract class of GameObjects for playing the game Worms, involving a World, a position and a radius.
 * 
 * @invar	The position of each GameObject must be a valid position for a GameObject.
 * 			| isValidPosition(this.getPositionX())
 * 			| && isValidPosition(this.getPositionY())
 * @invar	The direction of each GameObject must be a valid direction for a GameObject.
 * 			| isValidDirection(this.getDirection())
 * @invar	The radius of each GameObject must be a valid radius for a GameObject.
 * 			| isValidRadius(this.getRadius())
 * 
 * @author 	Nicolas Hoppenbrouwers:	Bachelor Ingenieurswetenschappen Computerwetenschappen-Werktuigkunde
 * 			Bram Lust: 				Bachelor Ingenieurswetenschappen Computerwetenschappen-Elektrotechniek
 * 			We didn't work with Git.
 * @version 1.0
 */
public abstract class GameObject {
	
	/**
	 * Initialize this new GameObject with given world, position and radius.
	 * 
	 * @param 	positionX
	 * 			The x-coordinate of the position of the new GameObject (in meters).
	 * @param 	positionY
	 * 			The y-coordinate of the position of the new GameObject (in meters).
	 * @param 	radius
	 * 			The radius of the new GameObject (in meters).
	 * @post	The x-coordinate of this new GameObject is equal to the given X-position.
	 * 			| new.getPositionX() == positionX
	 * @post	The y-coordinate of this new GameObject is equal to the given Y-position.
	 * 			| new.getPositionY() == positionY
	 * @post	The direction of this new GameObject is equal to the given direction.
	 * 			| new.getDirection() == direction
	 * @post	The radius of this new GameObject is equal to the given radius.
	 * 			| new.getRadius() == radius
	 */
	@Raw
	public GameObject(World world, double positionX, double positionY, double radius){
		this.setWorld(world);
		this.setPositionX(positionX);
		this.setPositionY(positionY);
		this.setRadius(radius);
	}
	
	
	
	
	
	
	
	
	
	

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
	

	
	
	
	
	
	
	
	
	
	
	

	
	
	/**
	 * Return the world of this GameObject.
	 */
	@Basic
	public World getWorld() {
		return this.world;
	}

	//ONAFGEWERKT.
	//DEZE METHODE KLOPT NOG NIET HELEMAAL MET OWNABLE. DAAR OOK SETWORLDTO
	//MISSCHIEN NOG TE IMPLEMENTEREN:
		// canHavaAsWorld()
		// hasProperWorld()
	/**
	 * Set the world of this GameObject to the given world.
	 * @param
	 */
	// Moet @Raw bij het argument World?
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
	 * @return	True if and only if the world of this GameObject is effective.
	 * 			| (this.getWorld() != null)
	 */
	//@Raw ?
	public boolean hasWorld(){
		return (this.getWorld() != null);
	}
	
	/**
	 * Variable referencing the world of this GameObject.
	 */
	protected World world;
	
	
	

	
	
	
	
	
	
	
	
	
	
	

	
	//Position eventueel nog in een hulpklasse steken.
	/**
	 * Returns the x-coordinate of the current location of this GameObject (in meters).
	 */
	@Basic
	public double getPositionX(){
		return this.positionX;
	}
	
	/**
	 * Returns the y-coordinate of the current location of this GameObject (in meters).
	 */
	@Basic
	public double getPositionY(){
		return this.positionY;
	}
	
	/**
	 * Sets this GameObject's x-coordinate to the given value in the Cartesian coordinate system.
	 * 
	 * @param	positionX
	 * 			The x-coordinate to set this GameObject's x-coordinate to.
	 * @post	If the new x-position of this GameObject is outside out of this world,
	 * 			the worm will be terminated.
	 * 			| if (outOfWorldX(positionX, this.getWorld()))
	 * 			|	then this.terminate();
	 * 			Other this GameObject's x-position is set to the given position.
	 * 			| else
	 * 			| 	new.getPositionX == positionX
	 * @throws	IllegalArgumentException
	 * 			The given position is an invalid position.
	 * 			| (!isValidPosition(positionX))
	 */
	public void setPositionX(double positionX) throws IllegalArgumentException{
		if (!isValidPosition(positionX))
			throw new IllegalArgumentException("Non effective position!");
		if (this.outOfWorldX(this.getWorld(), positionX, this.getRadius())){
			this.terminate();
		}
		this.positionX = positionX;
	}
	
	/**
	 * Sets this GameObject's y-coordinate to the given value in the Cartesian coordinate system.
	 * 
	 * @param	positionY
	 * 			The y-coordinate to set this GameObject's y-coordinate to.
	 * @post	If the new y-position of this GameObject is outside out of this world,
	 * 			the worm will be terminated.
	 * 			| if (outOfWorldY(positionY, this.getWorld()))
	 * 			|	then this.terminate();
	 * 			Other this GameObject's y-position is set to the given position.
	 * 			| else
	 * 			| 	new.getPositionY == positionY
	 * @throws	IllegalArgumentException
	 * 			The given position is an invalid position.
	 * 			| (!isValidPosition(positionY))
	 */
	public void setPositionY(double positionY) throws IllegalArgumentException{
		if (!isValidPosition(positionY))
			throw new IllegalArgumentException("Non effective position!");
		if (this.outOfWorldY(this.getWorld(), positionY, this.getRadius() )){
			this.terminate();
		}
		this.positionY = positionY;
	}
	
	/**
	 * Checks whether the given position is a valid position for any GameObject.
     *  
     * @param  	position
     * 		   	The position to check.
     * @return 	True if and only if the given position is not equal to NaN.
     *       	| result == (position != Double.NaN)	
     */
	public boolean isValidPosition(double position){
		return !(Double.isNaN(position));
	}
	
	public boolean outOfWorld(World world, double positionX, double positionY, double radius){
		return (outOfWorldX(world,positionX,radius) || outOfWorldY(world,positionY,radius));
	}
	
	/**
	 * Checks whether the given x-position of this GameObject is still inside this world.
	 * 
	 * @param 	positionX
	 * 			The position to check.
	 * @param 	world
	 * 			The world whose boundaries are of importance.
	 * @return	True if and only if the given x-position is outside of this world.
	 * 			| ( (positionX - this.getRadius() < 0 ) 
				|	|| (positionX + this.getRadius() > world.getWidth()) )
	 */
	public boolean outOfWorldX(World world, double positionX, double radius){
		return ( (positionX - radius < 0 ) 
				|| (positionX + radius > world.getWidth()) );
	}
	
	/**
	 * Checks whether the given y-position of this GameObject is still inside this world.
	 * 
	 * @param 	positionY
	 * 			The position to check.
	 * @param 	world
	 * 			The world whose boundaries are of importance.
	 * @return	True if and only if the given y-position is outside of this world.
	 * 			| ( (positionY - this.getRadius() < 0 ) 
				|	|| (positionY + this.getRadius() > world.getHeight()) )
	 */
	public boolean outOfWorldY(World world, double positionY, double radius){
		return ( (positionY - radius < 0 )
				|| (positionY + radius > world.getHeight()) );
	}
	
    /**
     * Variables registering the X and Y-coordinate of this GameObject.
     */
	protected double positionX;
	protected double positionY;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Returns the radius of this GameObject (meters).
	 */
	
	@Basic
	public double getRadius(){
		return this.radius;
	}
	
	/**
	 * Sets the radius of this GameObject to the given value.
	 */
	@Basic
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
	 * @return	True if and only if the given radius is not equal to NaN and the given radius is at least larger or equal to the minimal radius.
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
	
	public double calculateDistance(double x, double y) {
		return ( Math.sqrt(Math.pow( this.getPositionX() - x , 2) +  Math.pow( this.getPositionY() - y , 2)) );
	}
	
//	public double calculateDistanceBetweenTwoPositions(double x1, double y1, double x2, double y2){
//		return ( Math.sqrt(Math.pow( x1-x2 , 2) +  Math.pow( y1-y2 , 2)) );
//	}
	
	public boolean closeToWorm(double x,double y){
		for(Worm worm:this.getWorld().getWorms()){
			if (Math.pow(x - worm.getPositionX(), 2) + Math.pow(y - worm.getPositionY(), 2) <=
					Math.pow(this.getRadius()+worm.getRadius(), 2)){
				return true;
			}
		}
		
		return false;
	}
	
	public Worm closeToWhichWorm(double x,double y){
		for (Worm worm:this.getWorld().getWorms()){
			if (Math.pow(x - worm.getPositionX(), 2) + Math.pow(y - worm.getPositionY(), 2) <
					Math.pow(this.getRadius()+worm.getRadius(), 2)){
				return worm;
			}
		}
		return null;
	}
	
	public boolean closeToFood(){
		for (Food food:this.getWorld().getFood()){
			if (Math.pow(this.getPositionX() - food.getPositionX(), 2) + Math.pow(this.getPositionY() - food.getPositionY(), 2) <=
					Math.pow(this.getRadius()+food.getRadius(), 2)){
				return true;
			}
		}
		return false;
	}
	
	public Food closeToWhichFood(){
		for (Food food:this.getWorld().getFood()){
			if (Math.pow(this.getPositionX() - food.getPositionX(), 2) + Math.pow(this.getPositionY() - food.getPositionY(), 2) <
					Math.pow(this.getRadius()+food.getRadius(), 2)){
				return food;
			}
		}
		return null;
	}
}
