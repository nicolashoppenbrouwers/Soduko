package worms.model.program.types;

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
}
