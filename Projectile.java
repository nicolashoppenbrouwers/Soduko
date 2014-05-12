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

//Alles met JUMP
// Unsetworld: Dynamische binding shit.
// Paar opmerkingen.
/**
 * An abstract class of projectiles as a special kind of movable GameObjects, involving a world,
 *	 position, direction, and radius.
 * 
 * @author 	Nicolas Hoppenbrouwers
 * 			Bram Lust
 * @version 2.0
 */
public abstract class Projectile extends MovableGameObject{
	
	// IN ORDE
	/**
     *Initialize this new projectile with given world, position and direction.
     *
     * @param  	world
     *         	The world to contain the new projectile.
     * @param  	positionX
     *         	The x-coordinate of the position of the new projectile (meters).
     * @param  	positionY
     *         	The y-coordinate of the position of the new projectile (meters).
     * @param   direction
     * 			The direction which the new projectile is facing (radians).
     * @effect 	This new projectile is initialized as a new movableGameObject with
     *         	given world, positionX, positionY and direction, and zero as radius.
     *       	| super(world,positionX, positionY, direction, 0)
     * @effect 	The radius of this new projectile is equal to the calculated radius.
     *       	| new.getRadius() == calculateRadius()
     */
	@Raw
	public Projectile(World world, double positionX, double positionY, double direction){
		super(world, positionX, positionY, direction, 0);
		this.setRadius(calculateRadius());
		}
	
	
	


	
	
	

	//CONTROLEREN
	/**
	 * Unset the world, if any, from this projectile.
	 *
	 * @post    This projectile no longer has a world.
	 *        	| ! new.hasWorld()
	 * @post    The former world of this projectile, if any, no longer
	 *          has a projectile as its active projectile.
	 *        	| (this.getWorld() == null)
	 *        	| 	|| (new this.getWorld()).getProjectile() == null)
	 */
	@Override
	public void unsetWorld(){
		if (this.hasWorld()){
			World formerWorld = this.getWorld();
			setWorld(null);
			formerWorld.removeProjectile();
		}
	}
	 
	 
	 

		
	
	
	// IN ORDE
	/**
	 * Returns the minimal radius of this projectile.
	 * 
	 * @return 	The minimal radius of the projectile, more specifically zero.
	 * 			|  result == 0.0
	 */
	@Override
	public double getMinimalRadius(){
		return 0.0;
	}
	
	
	/**
	 * Calculates and returns the radius of this projectile.
	 * @return	The radius of this projectile.
	 * 			| result == (Math.pow(3.0/4.0 * getMass() / getDensity() / Math.PI, 1.0/3.0))
	 */
	public double calculateRadius(){
		return (Math.pow(3.0/4.0 * getMass() / getDensity() / Math.PI, 1.0/3.0));
	}
	
	
	
	
	//DIT MAG ALLEMAAL WEG NORMAAL.
	
//	/**
//	 * Return the propulsion yield of this projectile.
//	 */
//	public int getPropulsionYield(){
//		return this.propulsionYield;
//	}
//	
//	/**
//	 * Set the propulsion yield of this projectile to the given value.
//	 * @param 	propulsionYield
//	 * 			The propulsion yield to set this projectile's propulsionYield to.
//	 * 
//	 */
//	public void setPropulsionYield(int propulsionYield) throws IllegalArgumentException{
//		if (! isValidPropulsionYield(propulsionYield))
//			throw new IllegalArgumentException("Non effective propulsion yield!");
//		this.propulsionYield = propulsionYield;
//	}
//
//	/**
//	 * Check whether the given propulsion yield is a valid propulsion yield for any projectile.
//	 * @param 	propulsion yield
//	 * 			The propulsion yield to check.
//	 * @return 	True if and only if the given propulsion yield is not equal to NaN,
//	 * 			is bigger or equal to zero and is smaller or equal to 100.
//	 */
//	public boolean isValidPropulsionYield(int propulsionYield){
//		return (propulsionYield >= 0) && (propulsionYield <= 100) && (! Double.isNaN(propulsionYield));
//	}
//	
//	/**
//	 * Variable registering the propulsion yield of this projectile. 
//	 */
//	public int propulsionYield;
	
	
	// IN ORDE
	/**
	 * Return the density of this Projectile.
	 */
	@Override
	public double getDensity(){
		return density;
	}
	
	
	/**
	 * Variable registering the density of any projectile (final value).
	 */
	public final double density = 7800.0; 
	
	
	/**
 	 * Return the mass of this Projectile (in kilograms).
	 */
	@Override
	public abstract double getMass();
	
	
	/**
	 * Return the force of this Projectile (in Newton).
	 */
	@Override
	public abstract double getForce();
	
	
	
	
	
	
	
	
	
	
	//IN ORDE
	/**
	 * Return the amount of hit points this Projectile subtracts.
	 */
	public abstract int getHitPointsDamage();
	
	/**
	 * Return the amount of action points it costs to use this Projectile
	 */
	public abstract int getActionPointsCost();

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// DOCUMENTATIE EN METHODE.
	public void jump(double timeStep) throws IllegalStateException{
		double time = this.getJumpTime(timeStep);
		double[] step = this.jumpStep(time);
		//OUD: this.setPositionX(step[0]);
		//OUD: this.setPositionY(step[1]);
		this.setPosition(step[0],step[1]);
		if (this.closeToWorm(this.getPosition().getX(),this.getPosition().getY())){
			Worm worm = this.closeToWhichWorm(this.getPosition().getX(),this.getPosition().getY());
			this.closeToWhichWorm(this.getPosition().getX(),this.getPosition().getY()).setHitPoints(worm.getHitPoints()-this.getHitPointsDamage());
		}
	}
		//double initialVelocity = getForce() / getMass() * 0.5;
		//HIER NOG minstens sigma meters. 
//		// Opmerking Bram notities niet vergeten!
//		double t = 0.05;
//		boolean jumpCompleted = false;
//		double x = this.getPositionX();
//		double y = this.getPositionY();
//		while (! jumpCompleted){
//			double[] step = jumpStep(t);
//			x = step[0];
//			y = step[1];
//			if (this.outOfWorld(this.getWorld(),x,y,this.getRadius())) {
//				//this.setPositionX(x);
//				this.setPositionY(y);
//				jumpCompleted = true;
//			}
//			else if ( this.getWorld().isImpassable(x,y,1.1*this.getRadius()) ){
//				if (this.getWorld().isPassable(x,y, this.getRadius()) ){
//					this.setPositionX(x);
//					this.setPositionY(y);
//					jumpCompleted = true;
//				}
//				else {
//					throw new IllegalArgumentException("This jump is not possible.");
//				}
//			}
//			t = t + 0.001;
//		}
//		
//	}
	@Override
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
		double t = 0.01;
		boolean jumpCompleted = false;
		double x = this.getPosition().getX();
		double y = this.getPosition().getY();
		while (!jumpCompleted){
			double[] step = jumpStep(t);
			x = step[0];
			y = step[1];
			if (this.getWorld().outOfWorld(x,y,this.getRadius())) {
				jumpCompleted = true;
				return t;
			}
			if ( this.getWorld().isImpassableForShoot(x,y,this.getRadius()) ){
//				if (this.getWorld().isPassable(x,y, this.getRadius()) ){
					jumpCompleted = true;
					return t;
//				}
//				else {
//					return t;
//				}
			}
			if (this.closeToWorm(x,y)){
				return t;
			}
			t = t + timeStep;
		}
		return 0.0;
	}
}
