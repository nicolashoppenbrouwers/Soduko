package worms.model.program.statements; 

import worms.model.Program;
import worms.model.program.expressions.BooleanLiteral;
import worms.model.program.expressions.Expression;
import worms.model.program.statements.Statement;
import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * @author Bram,Nicolas
 * Dit is een klasse van while statements
 *
 */
public class While extends Statement{

	public While(int line,int column,Expression condition, Statement body){
		super(line,column);
		this.condition =  condition;
		this.body = body;
		this.setBooleanCondition(false);
		this.setConditionChecked(false);
	}
	
	
	//Setters en getters, allemaal private want je mag deze niet gebruiken buiten deze klasse

	@Basic
	private Expression getCondition() {
		return condition;
	}
	
	//Dit is de condition van de while lus
	private final Expression condition;

	
	
	
	
	@Basic
	private Statement getBody() {
		return body;
	}
	
	// Dit is de body, het statement dat de while lus zal uitvoeren indien aan de condition voldaan is
	private final Statement body;
	
	
	
	
	@Basic
	private boolean getConditionChecked() {
		return this.conditionChecked;
	}
	
	@Basic
	private void setConditionChecked(boolean coditionChecked) {
		this.conditionChecked = coditionChecked;
	}
	
	// Deze variabele houdt bij of we de conditie al hebben gecontroleerd.
	private boolean conditionChecked;
	
	
	
	

	@Basic
	private boolean getBooleanCondition() {
		return booleanCondition;
	}
	@Basic
	private void setBooleanCondition(boolean booleanCondition) {
		this.booleanCondition = booleanCondition;
	}
	
	// We slagen het condition op, want wanneer je het programma stopt halverwege de body, mag je niet de condition opnieuw
	// controleren
	private boolean booleanCondition;
	
	
	
	//De 3 programmas die bij alle statements moeten aanwezig zijn.
	@Override
	public boolean executeStatement(Program program){
		
		if( this.getConditionChecked() == false){
			this.setConditionChecked(true);
			// De expression true en false zijn nog niet klaar
			this.setBooleanCondition(((BooleanLiteral)this.getCondition().evaluate(program)).getBooleanValue());
		}
		
		while(this.getConditionChecked()){
			// Er is nog iets mis met de generische klassen Expression<BooleanLiteral>
			if (!this.body.execute(program)){
				return false;
			}
			else{
				// Er is nog iets mis met de generische klassen Expression<BooleanLiteral>
				this.setBooleanCondition(((BooleanLiteral)this.getCondition().evaluate(program)).getBooleanValue());
			}
		}
		this.setConditionChecked(false);
		return true;
	}
	
	@Override
	public boolean containsActionStatement() {
		return (this.getBody().containsActionStatement());
	}

	@Override
	public boolean isWellFormed() {
		return (this.getBody().isWellFormed());
	}
}
