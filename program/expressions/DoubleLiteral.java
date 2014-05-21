package worms.model.program.expressions;

import worms.model.Program;
import worms.model.program.types.Double;

//HERBEKIJKEN!!!! --> worms.model.program.types.Double meegeven ipv double

public class DoubleLiteral extends Expression {

	public DoubleLiteral(int line, int column, Double doubleType){
		super(line,column);
		this.type = doubleType;
	}
	
	public DoubleLiteral(int line, int column, double doubleValue) {
		super(line,column);
		this.type = new Double(doubleValue);
	}
	
	public Double getType(){
		return this.type;
	}
	
	//Triviale, onnodige functie maar gewoon voor de consistentie met getIntegerValue.
	public double getDoubleValue() {
		return getType().getDoubleValue();
	}
	
	public int getIntegerValue(){
		return getType().getIntegerValue();
	}
	
	private final Double type;



	//ONNODIGE methode, enkel voor consistentie.
	public Double getResult(Program program){
		return getType();
	}
	
	@Override
	public DoubleLiteral evaluate(Program program) {
		return this;
	}
	

}
