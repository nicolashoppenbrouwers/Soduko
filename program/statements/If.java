package worms.model.program.statements;

import worms.model.program.Program;
import worms.model.program.expressions.BooleanLiteral;
import worms.model.program.expressions.Expression;

public class If extends Statement{

	public If(int line, int column,Expression conditionExpression, Statement ifStatement,Statement elseStatement) {
		super(line, column);
		this.conditionExpression = conditionExpression;
		this.ifStatement = ifStatement;
		this.elseStatement = elseStatement;
		this.setConditionBoolean(false);
		this.setConditionChecked(false);
		
	}
	
	private Expression getConditionExpression() {
		return conditionExpression;
	}
	
	private final Expression conditionExpression;
	
	
	
	private Statement getIfStatement() {
		return ifStatement;
	}

	private final Statement ifStatement;
	
	
	
	private Statement getElseStatement() {
		return elseStatement;
	}
	
	private final Statement elseStatement;
	
	
	
	
	private boolean getConditionChecked() {
		return conditionChecked;
	}

	private void setConditionChecked(boolean conditionChecked) {
		this.conditionChecked = conditionChecked;
	}	

	private boolean conditionChecked;
	
	
	
	

	private boolean getConditionBoolean() {
		return conditionBoolean;
	}

	private void setConditionBoolean(boolean conditionBoolean) {
		this.conditionBoolean = conditionBoolean;
	}

	private boolean conditionBoolean;
	

	
	
	@Override
	public boolean executeStatement(Program program) {
		
		if (this.getConditionChecked() == false){
			this.setConditionChecked(true);
			this.setConditionBoolean(((BooleanLiteral)this.getConditionExpression().evaluate(program)).getType().getValue());
		}
		
		if(this.getConditionBoolean()){
			if (this.getIfStatement().execute(program)){
				this.setConditionChecked(false);
				return  true;
			}
			else {
				return false;
			}
		}
		else {
			if (this.getElseStatement().execute(program)){
				this.setConditionChecked(false);
				return  true;
			}
			else {
				return false;
			} 
		}
		
	}

	@Override
	public boolean containsActionStatement() {
		return this.getElseStatement().containsActionStatement() || this.getIfStatement().containsActionStatement();
	}

	@Override
	public boolean isWellFormed() {
		return this.getElseStatement().containsActionStatement() && this.getIfStatement().containsActionStatement();
	}

}
