package worms.model.program.types;

//Eigenlijk volledig gelijk aan java.lang.Boolean. Deze klasse is niet echt nodig,
// staat hier enkel voor de volledigheid omdat we ook Double en Entity hebben aangemaakt.
public class Boolean extends Type<java.lang.Boolean> {

	//Default constructor, staat volgens ons ook wel gewoon in java.lang.Double
	public Boolean() {
		super(false);
	}
	
}
