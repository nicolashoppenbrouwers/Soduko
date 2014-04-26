package worms.model;
import be.kuleuven.cs.som.annotate.*;


//VRAAG: moeten bij subklassen ook nog de KlasseInvarianten gekopieerd worden van de superklasse of niet?
// Zo ja, dan moet ook overal (bij alle klassen!) de documentatie van de constructor worden aangepast!
/**
 * An abstract class of Movable Game Objects as a special kind of GameObjects. In addition to the world,
 * position and radius, Movable GameObjects also have a direction they face.
 * 
 * @invar	The direction of each worm must be a valid direction for a worm.
 * 			| isValidDirection(this.getDirection())
 * 
 * @author 	Nicolas Hoppenbrouwers:	Bachelor Ingenieurswetenschappen Computerwetenschappen-Werktuigkunde
 * 			Bram Lust: 				Bachelor Ingenieurswetenschappen Computerwetenschappen-Elektrotechniek
 * 			We didn't work with Git.
 * @version 1.0
 */
public abstract class MovableGameObject extends GameObject {

	
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
	
	
	
	
	
	
	
	/**
	 * Returns the minimal radius of this MovableGameObject.
	 */
	@Override
	public abstract double getMinimalRadius();
	
	
	
	
	
	
	
	
	/**
	 * Returns the current this MovableGameObject is currently facing. 
	 * The direction is expressed as an angle in radians.
	 */
	@Basic
	public double getDirection(){
		return this.direction;
	}
	
	/**
	 * Turns this worm to let it face the given direction.
	 * 
	 */
	public void setDirection(double direction){
		assert isValidDirection(direction);
		this.direction = direction % (2*Math.PI);
		if (this.getDirection() < 0)
			this.direction = this.getDirection() + 2*Math.PI;
	}
	
	/**
	 * Variable registering the direction of this worm.
	 */
	private double direction;
	
	/**
	 * Checks whether the given direction is a valid direction for any worm.
     *  
     * @param  	direction
     * 		   	The direction to check.
     * @return 	True if and only if the given direction is not equal to NaN.
     *       	| result == (position != Double.NaN)	
     */
	public static boolean isValidDirection(double direction){
		return !(Double.isNaN(direction));
	}
	
	
	
	
	
	
	
	
	public abstract double getMass();
	public abstract double getDensity();
	public abstract double getForce();
	
	
	/**
	 * Variable registering the standard acceleration.
	 */
	public final double g = 9.80665; 

	/**
	 * Makes this worm jump
	 * 
	 * @post 	The worm has jumped over a certain distance.
	 * 			| new.getPositionX() = this.getPositionX() + Math.pow(initialVelocity, 2) * Math.sin( 2 * this.getDirection() ) / g
	 * @post 	The jumping has consumed all the remaining action points.
	 * 			| new.getActionPoints() = 0
	 * @throws  IllegalStateException
	 * 			The worm is facing downwards (the direction should be between 0 and 2*PI) or it has no action points left.
	 * 			| (! canJump())
	 */
	public void jump() throws IllegalStateException{
		//double initialVelocity = getForce() / getMass() * 0.5;
		//HIER NOG minstens sigma meters. 
		// Opmerking Bram notities niet vergeten!
		double t = 0.05;
		boolean jumpCompleted = false;
		double x = this.getPositionX();
		double y = this.getPositionY();
		while (! jumpCompleted){
			double[] step = jumpStep(t);
			x = step[0];
			y = step[1];
			if (this.outOfWorld(this.getWorld(),x,y,this.getRadius())) {
				//Eigenlijk niet correct!
				throw new NullPointerException();
			}
			else if ( !this.getWorld().isPassable(x,y,1.1*this.getRadius()) ){
				if (this.getWorld().isPassable(x,y, this.getRadius()) ){
					this.setPositionX(x);
					this.setPositionY(y);
					jumpCompleted = true;
				}
				else {
					throw new IllegalStateException("This jump is not possible.");
				}
			}
			t = t + 0.001;
		}
		
	}
	
	/**
	 * Returns the total amount of time (in seconds) that a
	 * jump of this worm would take.
	 * 
	 * @return 	Return the total amount of time that a jump of a given worm would take
	 * 			| time = distance / ( initialVelocity * Math.cos( this.getDirection() ));
	 */
	public double getJumpTime(double timeStep){
		//double initialVelocity = getForce() / getMass() * 0.5;
/*		double distance = Math.pow(initialVelocity, 2) * Math.sin( 2 * this.getDirection() ) / g;
		if (Math.cos(this.getDirection()) == 0){
			//DUMMY IMPLEMENTATIE
			double time = 2.0; 
			return time;
		}
		double time = distance / ( initialVelocity * Math.cos( this.getDirection() ));
		return time;
	}*/
		double t = 0.05;
		boolean jumpCompleted = false;
		double x = this.getPositionX();
		double y = this.getPositionY();
		while (!jumpCompleted){
			double[] step = jumpStep(t);
			x = step[0];
			y = step[1];
			if (this.outOfWorld(this.getWorld(),x,y,this.getRadius())) {
				jumpCompleted = true;
				return t;
			}
			if ( this.getWorld().isImpassable(x,y,1.1*this.getRadius()) ){
				if (this.getWorld().isPassable(x,y, this.getRadius()) ){
					jumpCompleted = true;
					return t;
				}
				else {
					return t;
				}
			}
			t = t + timeStep * 10;
		}
		return 0.0;
	}
	
	/**
	 * Returns the location on the jump trajectory of the given worm
	 * after a time t
	 * 
	 * @param 	time
	 * 			The time this worm has already followed his trajectory.
	 * @return 	An array with two elements,
	 *  		with the first element being the x-coordinate and
	 *  		the second element the y-coordinate
	 *  		| new double[] {stepX, stepY}
	 */
	public double[] jumpStep(double time){
		double initialVelocity = this.getInitialVelocity();
		double initialVelocityX = initialVelocity * Math.cos( this.getDirection() );
		double initialVelocityY = initialVelocity * Math.sin( this.getDirection() );
		double stepX = this.getPositionX() + initialVelocityX * time;
		double stepY = this.getPositionY() + initialVelocityY * time - 0.5 * g * Math.pow(time,2);
		return new double[] {stepX, stepY};
	}
	
	/**
	 * Checks whether the given worm is able to jump.
	 * 
	 * @return 	True if and only if the worm has enough action points left to perform a jump
	 * 			and if the worm is not facing downwards.
	 * 			|return !((this.getDirection() > Math.PI) && (this.getDirection() < 2*Math.PI))
	 * 
	 */
	public abstract boolean canJump();	//((this.getDirection() > 0) && (this.getDirection() < Math.PI) && !(this.getActionPoints() == 0));
	
	public double getInitialVelocity(){
		return (getForce() / getMass() * 0.5);
	}
}