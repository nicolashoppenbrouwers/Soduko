package worms.model;
import java.util.HashMap;

import worms.gui.game.IActionHandler;
import worms.model.program.statements.Statement;
import worms.model.program.types.Type;



public class Program {

	public Program(Worm worm, Statement mainStatement, HashMap<String,Type<?>> globals, IActionHandler handler) {
		this.worm = worm;
		this.mainStatement = mainStatement;
		this.setGlobals(globals);
		this.handler = handler;
		this.setAmountOfStatementsExecuted(0);
		this.setIsCorrupted(false);
	}
	

	//Dynamische binding controleren
	public Worm getWorm(){
		return this.worm;
	}
	
	private final Worm worm;
	
	public World getWorld(){
		return this.getWorm().getWorld();
	}
	
	public IActionHandler getHandler(){
		return this.handler;
	}
	
	private final IActionHandler handler;
	
	public Statement getMainStatement(){
		return this.mainStatement;
	}
	
	private final Statement mainStatement;

	
	
	public int getAmountOfStatementsExecuted(){
		return this.amountOfStatementsExecuted;
	}
	
	public void setAmountOfStatementsExecuted(int amountOfStatementsExecuted){
		this.amountOfStatementsExecuted = amountOfStatementsExecuted;
	}
	
	private int amountOfStatementsExecuted;
	
	//Nagaan of de values inderdaad Expressions moeten zijn, anders moeten het types zijn.
	public HashMap<String,Type<?>> getGlobals(){
		return this.globals;
	}
	
	public void setGlobals(HashMap<String,Type<?>> globals) {
		this.globals = globals;
	}
	
	private HashMap<String,Type<?>> globals; 
	
	// Deze functie controleerd of er nog niet te veel statements zijn uitgevoerd deze beurt
	//indien dit het duizendste statement is dat werdt uitgevoerd sinds de functie resetAmountStatements
	// geeft deze functie true terug, anders geeft deze functie false terug.
	public boolean tooManyStatementsExecuted(){
		return (this.getAmountOfStatementsExecuted() >= 1000);
	}
	
	//zet de teller voor het aantal gebruikte statements terug op 0
	private void resetAmountOfStatementsExecuted(){
		setAmountOfStatementsExecuted(0);
	}
	
	// Deze variabele heeft geen getters en setters want je mag er enkel aankomen via de functiesresetAmountStatements()
	
	
	public boolean isCorrupted(){
		return this.isCorrupted;
	}
	
	public void setIsCorrupted(Boolean value){
		this.isCorrupted = value;
	}
	
	private boolean isCorrupted;
	
	public boolean isWellFormed(){
		return mainStatement.isWellFormed();
	}
	
	
	
	
	
	
	
	public void run(){
		if ((isCorrupted() == true) || (! isWellFormed()))
			this.getWorld().startNextTurn();
			//Exceptions catchen!!
			//Je moet hier niet checken of je worm al geterminated is, want die worm komt niet aan de beurt.
		mainStatement.execute(this);
		this.resetAmountOfStatementsExecuted();
		this.getWorld().startNextTurn();
	}
	
	public static ParseOutcome<?> parseProgram(String programText,
            IActionHandler handler) {
	
	ProgramFactoryImpl factory = new ProgramFactoryImpl();
    ProgramParser<Expression<?>, Statement, Variable<?>> parser = new ProgramParser<>(factory);
    factory.setProgramParser(parser); //NullPointerException
    Program program;

    parser.parse(programText);
}
