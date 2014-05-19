package worms.model.program.statements; 

import worms.model.Worm;
import worms.model.program.expressions.BooleanLiteral;
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

	While(int line,int column,Expression<BooleanLiteral> condition, Statement body){
		super(line,column);
		this.condition =  condition;
		this.body = body;
		this.booleanCondition = false;
		this.setConditionChecked(false);
	}
	
	
	//Setters en getters, allemaal privat want je mag deze niet gebruiken buiten deze klasse

	@Basic
	private Expression<BooleanLiteral> getCondition() {
		return condition;
	}

	@Basic
	private Statement getBody() {
		return body;
	}
	
	@Basic
	private void setConditionChecked(boolean coditionChecked) {
		this.conditionChecked = coditionChecked;
	}
	
	@Basic
	private boolean getConditionChecked() {
		return this.conditionChecked;
	}

	@Basic
	private boolean getBooleanCondition() {
		return booleanCondition;
	}
	@Basic
	private void setBooleanCondition(boolean booleanCondition) {
		this.booleanCondition = booleanCondition;
	}
	
	
	//de Variabelen
	
	//Dit is de condition van de while lus
	final private Expression<BooleanLiteral> condition;
	
	// Dit is de body, het statement dat de while lus zal uitvoeren indien aan de condition voldaan is
	final private Statement body;
	
	// Deze variabele houdt bij of we de conditie al hebben gecontroleerd.
	private boolean conditionChecked;

	// We slagen het condition op, want wanneer je het programma stopt halverwege de body, mag je niet de condition opnieuw
	// controleren
	private boolean booleanCondition;
	
	
	//De 3 programmas die bij alle statements moeten aanwezig zijn.
	
	@Override
	public boolean execute(Program program,Worm worm){
		
		if( this.getConditionChecked() == false){
			this.setConditionChecked(true);
			// De expression true en false zijn nog niet klaar
			this.setBooleanCondition(this.getCondition().evaluate(program).getValue());
		}
		
		while(this.getConditionChecked()){
			// Er is nog iets mis met de generische klassen Expression<BooleanLiteral>
			if (!this.body.executeWithCheck(program,worm)){
				return false;
			}
			else{
				// Er is nog iets mis met de generische klassen Expression<BooleanLiteral>
				this.setBooleanCondition(this.getCondition().evaluate(program).getValue());
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
	public boolean welFormedStatement() {
		return (this.getBody().welFormedStatement());
	}
}
