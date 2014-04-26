/**
 * 
 */
package worms.model;
// createFood(World world, double x, double y);
// 	double getX(Food food);
// 	double getY(Food food);
//	double getRadius(Food food);
// 	boolean isActive(Food food);


/**
 * @author 	Nicolas Hoppenbrouwers:	Bachelor Ingenieurswetenschappen Computerwetenschappen-Werktuigkunde
 * 			Bram Lust: Bachelor Ingenieurswetenschappen Computerwetenschappen-Elektrotechniek
 * 			We didn't work with Git.
 * @version 1.0
 */
public class Food extends GameObject {
	public Food(World world,double positionX, double positionY){
		super(world,positionX, positionY, 0.20);
	}

	
	
	
	
	//DOCUMENTATIE
	 @Override
	 public void unsetWorld(){
		if (this.hasWorld()){
			World formerWorld = this.getWorld();
			setWorld(null);
			formerWorld.removeFood(this);
		}
	}
	 
	 
	 
	 
	 
	 
	/**
	 * Returns the minimal radius of this food.
	 *
	 * @return	Return the minimal radius of this food.
	 * 			| result == 0.20
	 */
	@Override
	public double getMinimalRadius() {
		return 0.20;
	}
	
	
	
	
	
	
		
	//EIGENLIJK IS DIT EEN TRIVIALE FUNCTIE EN HOEFT HIER NIET.
	public boolean isActive(){
		return (! this.isTerminated());
	}

	
}
