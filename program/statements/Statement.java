package worms.model.program.statements;
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
	 * 	Deze functie geeft false terug als er al meer dan 1000 statements zijn opgeroepen
	 *	Anders voert deze functie de normale executeStatement() uit
	 */
	public boolean execute(Program program){
		program.setAmountOfStatementsExecuted(program.getAmountOfStatementsExecuted()+1);
		if (program.tooManyStatementsExecuted()){
			return false;
		}
		else{
			return this.executeStatement(program);
		}
	}
	
	// Elk Statement moet over deze functies beschikken, om al de nodige functionaliteiten van een programma mogelijk te maken
	// Als je ervoor zorgt dat elk statement aan deze functies voldoen zal je deze functies ook kunnen oproepen op je mainstatement
	// door de recursie
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
	public abstract boolean executeStatement(Program program);

	
	/**
	 * Deze functie geeft weer of dat het statement op expliciete of impliciete wijze een actionstatemnt bevat
	 * (Deze functie heb je nodig in voor forEach();
	 */
	public abstract boolean containsActionStatement();
	
	/**
	 * Deze functie geeft weer of dit Statement een welformed statement is
	 */
	public abstract boolean isWellFormed();
}
