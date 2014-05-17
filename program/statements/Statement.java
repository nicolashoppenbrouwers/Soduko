package worms.model.program.statements;
import worms.model.Worm;
import worms.model.program.Program;
import be.kuleuven.cs.som.annotate.*;

/**
 * @author Bram, Nicolas
 * 	een abstracte klasse van statements
 *	statements kunnen actionstatements bevatten, expressions of statements
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
	 * 	Deze functie voert het statement uit
	 *	Het moet true returnen als het statement volledig is uitgevoerd
	 *	Indien het statement true returned, en je excute het statement opnieuw,
	 *	dan moet het zijn als of je het statement nog nooit hebt uitgevoerd
	 *	(je kan het zelfde statement dus nooit 2 maal opnieuw gebruiken)
	 *	als het statement niet kan uitgevoerd worden (2 redens)
	 *	dan moet het false returnen en als je het statement dan opnieuw execute moet het 
	 *	het statement verder gaan vanaf waar het gebleven was
	 */
	public abstract boolean execute(Program program,Worm worm);
	
	/**
	 * 	Deze functie geeft false terug als er al mee dan 1000 statements zijn opgeroepen
	 *	Anders voert deze functie de normale excute uit
	 */
	public boolean executeWithCheck(Program program,Worm worm){
		if (program.checkAmountOfStatements()){
			return false;
		}
		else{
			return this.execute(program,worm);
		}
	}
	
	/**
	 * Deze functie geeft weer ofdat het statement op expliciete of impliciete wijze een statemnt bevat
	 */
	public abstract boolean containsActionStatement();
	
	
}
