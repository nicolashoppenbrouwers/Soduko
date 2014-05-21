package worms.model.program.expressions;

import java.util.ArrayList;


import worms.model.GameObject;
import worms.model.Worm;
import worms.model.program.Program;
import worms.model.program.types.Entity;
import worms.util.Util;

public class SearchObj extends Expression{

	public SearchObj(int line, int column, Expression e){
		super(line,column);
		this.expression = e;
	}
	
	public Expression getEntityExpression(){
		return this.expression;
	}
	
	private final Expression expression;
	
	private double getRelativeDirection(Worm worm,GameObject gameObject){
		double differenceX = gameObject.getPosition().getX() - worm.getPosition().getX();
		double differenceY = gameObject.getPosition().getY() - worm.getPosition().getY();
		if (Util.fuzzyEquals(differenceX, 0.0)){
			if (Util.fuzzyEquals(differenceY, 0.0)){
				return Double.NaN;
			}
			if (differenceY > 0)
				return Math.PI *0.5;
			else
				return Math.PI *1.5;
		}
		else{
			double alpha = Math.atan(differenceY/differenceX);
			if (differenceX < 0 )
				alpha = alpha + Math.PI;
			if (alpha < 0)
				alpha = alpha + Math.PI * 2;
			return alpha;
		}
	}
	
	private boolean isInDirectionOfWorm(double direction,double e){
		if ((e > Math.PI) ||(Double.isNaN(direction)))
			return true;
		double lowerBound = direction - Math.abs(e);
		double upperBound = direction + Math.abs(e);
		if (upperBound > 2*Math.PI){
			 upperBound = upperBound - 2*Math.PI;
			 return (direction <= upperBound) || (direction >= lowerBound);
		}
		if (lowerBound < 0){
			 lowerBound = lowerBound + 2*Math.PI;
			 return (direction <= upperBound) || (direction >= lowerBound);
		}
		return (direction >= lowerBound) && (direction <= upperBound);
	}

	@Override
	public Entity getResult(Program program){
		GameObject closestGameObjectSoFar = null;
		double smallestDistance = Double.MAX_VALUE;
		ArrayList<GameObject> allGameObjects= new ArrayList<GameObject>();
		allGameObjects.addAll(program.getWorld().getWorms());
		allGameObjects.addAll(program.getWorld().getFood());
		double e = ((DoubleLiteral)this.getEntityExpression().evaluate(program)).getDoubleValue();
		for (GameObject gameObject: allGameObjects) {
			double direction = this.getRelativeDirection(program.getWorm(), gameObject);
			if (this.isInDirectionOfWorm(direction, e)) {
				double distance = program.getWorm().getPosition().calculateDistance(gameObject.getPosition());
				if(distance < smallestDistance){
					smallestDistance = distance;
					closestGameObjectSoFar = gameObject;
				}
			}
		}
		return new Entity(closestGameObjectSoFar);
	}

	@Override
	public Expression evaluate(Program program) {
		return new EntityLiteral(getLine(),getColumn(),getResult(program));
	}

}
