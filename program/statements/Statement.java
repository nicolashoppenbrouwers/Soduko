package worms.model.program.statements;
import worms.model.Worm;
import worms.model.program.Program;
import be.kuleuven.cs.som.annotate.*;

/**
 * @author Bram, Nicolas
 * 	een abstracte klasse van statements
 */
public abstract class Statement {
	
	public Statement(int line, int column){
		this.line = line;
		this.column = column;
	}
	
	@Basic
	public int getLine() {
		return line;
	}
	
	@Basic
	public int getColumn() {
		return column;
	}
	
	private final int line;
	
	private final int column;
	
	
	/**
	 * 	Deze functie geeft false terug als er al mee dan 1000 statements zijn opgeroepen
	 *	Anders voert deze functie de normale excute() uit
	 */
	public boolean executeWithCheck(Program program,Worm worm){
		if (program.checkAmountOfStatements()){
			return false;
		}
		else{
			return this.execute(program,worm);
		}
	}
	
	// Elk Statement moet over deze functies beschikken, om al de nodige functionaliteiten van een programma mogelijk te maken
	// Als je ervoor zorgt dat elk statement aan deze functies voldoen zal je deze functies kunnen oproepen op je mainstatement.
	/**
	 * 	Deze functie voert het statement uit
	 *	Het moet true returnen als het statement volledig is uitgevoerd
	 *	Indien het statement true returned, en je excute het statement opnieuw,
	 *	dan moet het zijn als of je het statement nog nooit hebt uitgevoerd
	 *	(je kan het zelfde statement dus nooit 2 maal opnieuw gebruiken in het zelfde programma)
	 *	als het statement niet kan uitgevoerd worden (2 redens)
	 *	dan moet het false returnen en als je het statement dan opnieuw execute moet het 
	 *	het statement verder gaan vanaf waar het gebleven was
	 *	(je moet geen rekening meer houden met het aantal statements een programma al heeft uitevoerd in een gegeven beurt)
	 */
	public abstract boolean execute(Program program,Worm worm);

	
	/**
	 * Deze functie geeft weer of dat het statement op expliciete of impliciete wijze een actionstatemnt bevat
	 * (Deze functie heb je nodig in voor forEach();
	 */
	public abstract boolean containsActionStatement();
	
	/**
	 * Deze functie geeft weer of dit Statement een welformed statement is
	 */
	public abstract boolean welFormedStatement();
}
