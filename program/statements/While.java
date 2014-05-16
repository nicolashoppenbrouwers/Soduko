package worms.model.program.statements; 

import worms.model.program.expressions.Expression;
import worms.model.program.Program;
import worms.model.program.statements.Statement;
import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * @author Bram,Nicolas
 * Dit is een klasse van while statements
 *
 */
public class While extends Statement{

	While(Program program ,Expression condition, Statement body){
		super(program);
		this.condition =  condition;
		this.body = body;
		this.booleanCondition = false;
		this.setConditionChecked(false);
	}

	@Basic
	private Expression getCondition() {
		return condition;
	}

	@Basic
	private Statement getBody() {
		return body;
	}
	
	@Basic
	public void setConditionChecked(boolean coditionChecked) {
		this.conditionChecked = coditionChecked;
	}
	
	@Basic
	public boolean getConditionChecked() {
		return this.conditionChecked;
	}

	@Basic
	public boolean getBooleanCondition() {
		return booleanCondition;
	}
	@Basic
	public void setBooleanCondition(boolean booleanCondition) {
		this.booleanCondition = booleanCondition;
	}
	
	
	
	final private Expression condition;
	
	final private Statement body;
	
	// Deze variabele houdt bij of we de conditie al hebben gecontroleerd.
	private boolean conditionChecked;

	// We slagen het condition op, want wanneer je het programma stopt halverwege de body, mag je niet de condition opnieuw
	// controleren
	private boolean booleanCondition;
	
	
	
	
	public boolean execute(){
		
		if( this.getConditionChecked() == false){
			this.setConditionChecked(true);
			// True en false zijn nog niet klaar
			this.setBooleanCondition(this.getCondition().evaluate(this.getProgram()).getValue());
		}
		
		while(this.getConditionChecked()){
			// in dit if statement wordt de body uitgevoerd
			if (!this.body.executeWithCheck()){
				return false;
			}
			else{
				// True en false zijn nog niet klaar
				this.setBooleanCondition(this.getCondition().evaluate(this.getProgram()).getValue());
			}
		}
		this.setConditionChecked(false);
		return true;
	}
		
	// java doet weer lastig
	public boolean containsActionStatement() {
		return (this.getBody().containsActionStatement());
	}
}
