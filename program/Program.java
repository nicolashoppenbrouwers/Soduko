package worms.model.program;
import be.kuleuven.cs.som.annotate.*;


public class Program {

	public Program() {
	}

	
	// Deze functie controleerd of er nog niet te veel statements zijn uitgevoerd deze beurt
	//indien dit het duizendste statement is dat werdt uitgevoerd sinds de functie resetAmountStatements
	// geeft deze functie true terug, anders geeft deze functie false terug.
	public boolean checkAmountOfStatements(){
		amountOfStatements = this.amountOfStatements +1;
		return (this.amountOfStatements >= 1000);
	}
	
	//zet de teller voor het aantal gebruikte statements terug op 0
	private void resetAmountOfStatements(){
		this.amountOfStatements = 0;
	}
	
	// Deze variabele heeft geen getters en setters want je mag er enkel aankomen via de functiesresetAmountStatements()
	private int amountOfStatements;
}
