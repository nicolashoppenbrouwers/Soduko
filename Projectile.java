/**
 * 
 */
package worms.model;


import be.kuleuven.cs.som.annotate.*;
// Projectile getActiveProjectile(World world); ---> moet in World zitten.
// 	void jump(Projectile projectile, double timeStep);   		 --> OVERERVING? --> KLASSE POSITION
// 	double[] getJumpStep(Projectile projectile, double t);       --> OVERERVING? --> KLASSE POSITION
// 	double getJumpTime(Projectile projectile, double timeStep);  --> OVERERVING? --> KLASSE POSITION
// 	public double getRadius(Projectile projectile);				 
// 	public double getX(Projectile projectile);					 --> POSITION
// 	public double getY(Projectile projectile);				     --> POSITION
// 	boolean isActive(Projectile projectile);


/**
 * An abstract class of projectiles as a special kind of movable GameObjects. In addition to the world,
 * position, direction, and radius, projectiles have an amount of damage they deal.
 * 
 * 
 * @invar	The damage of each projectile must be a valid amount of damage that they deal
 * 			| this.getDamage() > 0
 * 
 * @author 	Nicolas Hoppenbrouwers:	Bachelor Ingenieurswetenschappen Computerwetenschappen-Werktuigkunde
 * 			Bram Lust: Bachelor Ingenieurswetenschappen Computerwetenschappen-Elektrotechniek
 * 			We didn't work with Git.
 * @version 1.0
 */
public abstract class Projectile extends MovableGameObject{
	
	/**
     *Initialize this new projectile with given world, position, direction and damage.
     *
     * @param  	World
     *         	The world which contains the new projectile.
     * @param  	positionX
     *         	The x-coordinate of the position of the new projectile (meters).
     * @param  	positionY
     *         	The y-coordinate of the position of the new projectile (meters).
     * @param   direction
     * 			The direction which the new projectile is facing (radians).
     * @param	radius
     * 			The given radius of the new projectile (meters).
     * @param	propulsionYield
     * 			The propulsionYield of the new weapon.
     * 
     * @effect 	This new projectile is initialized as a new movableGameObject with
     *         	given world, positionX, positionY, direction and radius.
     *       	| super(world,positionX, positionY, radius, direction)
     * @post   	The damage of this new projectile is equal to the given damage.
     *       	| new.getDamage() = damage
     */
	@Raw
	public Projectile(World world, double positionX, double positionY, double direction){
		super(world, positionX, positionY, direction, 0);
		this.setRadius(calculateRadius());
		}
	
	
	


	
	
	

	//DOCUMENTATIE
	@Override
	public void unsetWorld(){
		if (this.hasWorld()){
			World formerWorld = this.getWorld();
			setWorld(null);
			formerWorld.removeProjectile();
		}
	}
	 
	 
	 

		
	/**
	 * Returns the minimal radius of this projectile.
	 * 
	 * @return 	Return the minimal radius of the projectile, more specifically zero.
	 * 			|  result == 0.0
	 */
	@Override
	public double getMinimalRadius(){
		return 0.0;
	}
	
	public double calculateRadius(){
		return (Math.pow(3.0/4.0 * getMass() / getDensity() / Math.PI, 1.0/3.0));
	}
	
	
	
	
	
	
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
	public abstract double getMass();
	
	/**
	 * Return the force of this Projectile (in Newton).
	 */
	public abstract double getForce();
	
	/**
	 * Return the amount of hit points this Projectile subtracts.
	 */
	public abstract int getHitPointsDamage();
	
	/**
	 * Return the amount of action points it costs to use this Projectile
	 */
	public abstract int getActionPointsCost();

	
	public void jump(double timeStep) throws IllegalStateException{
		double time = this.getJumpTime(timeStep);
		double[] step = this.jumpStep(time);
		this.setPositionX(step[0]);
		this.setPositionY(step[1]);
		if (this.closeToWorm(this.getPositionX(),this.getPositionY())){
			Worm worm = this.closeToWhichWorm(this.getPositionX(),this.getPositionY());
			this.closeToWhichWorm(this.getPositionX(),this.getPositionY()).setHitPoints(worm.getHitPoints()-this.getHitPointsDamage());
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
