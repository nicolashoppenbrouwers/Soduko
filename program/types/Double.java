package worms.model.program.types;

import worms.model.program.expressions.DoubleLiteral;
import worms.model.program.expressions.Expression;

public class Double extends Type<java.lang.Double> {
	
	//Default constructor, staat volgens ons ook wel gewoon in java.lang.Double
	public Double() {
		super(0.0);
	}
	
	//Triviale, onnodige functie maar gewoon voor de consistentie met getIntegerValue.
	//Deze methode voert hetzelfde uit als getValue().
	public double getDoubleValue() {
		return (double) this.getValue();
	}
	
	public int getIntegerValue(){
		return (int) Math.floor(this.getValue());
	}

	
	//?
	@Override
	public Expression<DoubleLiteral> convertToExpression(int line, int column) {
		return new DoubleLiteral(line,column,this.getValue());
	}
}
