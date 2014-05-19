package worms.model.program.expressions;

import worms.model.program.Program;

//HERBEKIJKEN!!!!

public class DoubleLiteral extends Expression<DoubleLiteral> {

	public DoubleLiteral(int line, int column, double doubleValue) {
		super(line,column);
		this.value = doubleValue;
	}
	
	public double getValue(){
		return this.value;
	}
	
	//Triviale, onnodige functie maar gewoon voor de consistentie met getIntegerValue.
	public double getDoubleValue() {
		return this.value;
	}
	
	public int getIntegerValue(){
		return (int) Math.floor(this.getValue());
	}
	
	private final Double value;



	@Override
	public DoubleLiteral evaluate(Program program) {
		return this;
	}
}
