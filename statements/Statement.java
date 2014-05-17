package worms.model.program.statements;
import worms.model.program.Program;
import be.kuleuven.cs.som.annotate.*;

// een abstracte klasse van statements
// statements kunnen actionstatements bevatten, expressions of statements
public abstract class Statement {
	
	public Statement(Program program){
		setProgram(program);
	}
	
	@Basic
	public Program getProgram() {
		return program;
	}
	
	@Basic
	public void setProgram(Program program) {
		this.program = program;
	}
	
	private Program program;
	
	// Deze functie voert het statement uit
	// Het moet true returnen als het statement volledig is uitgevoerd
	// Indien het statement true returned, en je excute het statement opnieuw,
	// dan moet het zijn als of je het statement nog nooit hebt uitgevoerd
	// (je kan het zelfde statement dus nooit 2 maal opnieuw gebruiken)
	// als het statement niet kan uitgevoerd worden (2 redens)
	// dan moet het false returnen en als je het statement dan opnieuw execute moet het 
	// het statement verder gaan vanaf waar het gebleven was
	public abstract boolean execute();
	
	//Deze functie geeft false terug als er al mee dan 1000 statements zijn opgeroepen
	//Anders voert deze functie de normale excute uit
	public boolean executeWithCheck(){
		if (program.checkAmountOfStatements()){
			return false;
		}
		else{
			return this.execute();
		}
	}
	
	public abstract boolean containsActionStatement();
	
	
}
