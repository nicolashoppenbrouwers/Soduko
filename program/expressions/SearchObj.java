package worms.model.program.expressions;

import worms.model.GameObject;
import worms.model.Worm;
import worms.model.program.Program;
import worms.model.program.types.Entity;
import worms.model.program.types.Type;
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
		if(Util.fuzzyEquals(differenceX, 0.0)){
			if(Util.fuzzyEquals(differenceY, 0.0)){
				return Double.NaN;
			}
			if(differenceY > 0)
				return Math.PI *0.5;
			else
				return Math.PI *1.5;
		}
		else{
			double alpha = Math.atan(differenceY/differenceX);
			if(differenceX < 0 )
				alpha = alpha + Math.PI;
			if(alpha < 0)
				alpha = alpha + Math.PI * 2;
			return alpha;
		}
	}
	
	private boolean

	@Override
	public Type<?> getResult(Program program) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression evaluate(Program program) {
		// TODO Auto-generated method stub
		return null;
	}

}
