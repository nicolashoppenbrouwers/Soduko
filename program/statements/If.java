package worms.model.program.statements;

import worms.model.Worm;
import worms.model.program.Program;
import worms.model.program.expressions.BooleanLiteral;
import worms.model.program.expressions.Expression;

public class If extends Statement{

	public If(int line, int column,Expression<BooleanLiteral> conditionExpression, Statement ifStatement,Statement elseStatement) {
		super(line, column);
		this.conditionExpression = conditionExpression;
		this.ifStatement = ifStatement;
		this.elseStatement = elseStatement;
		this.setConditionBoolean(false);
		this.setConditionChecked(false);
		
	}
	
	
	
	private boolean getConditionChecked() {
		return conditionChecked;
	}

	private void setConditionChecked(boolean conditionChecked) {
		this.conditionChecked = conditionChecked;
	}

	private boolean getConditionBoolean() {
		return conditionBoolean;
	}

	private void setConditionBoolean(boolean conditionBoolean) {
		this.conditionBoolean = conditionBoolean;
	}

	private Expression<BooleanLiteral> getConditionExpression() {
		return conditionExpression;
	}

	private Statement getIfStatement() {
		return ifStatement;
	}

	private Statement getElseStatement() {
		return elseStatement;
	}

	private final Expression<BooleanLiteral> conditionExpression;
	private final Statement ifStatement;
	private final Statement elseStatement;
	private boolean conditionChecked;
	private boolean conditionBoolean;
	

	@Override
	public boolean execute(Program program, Worm worm) {
		
		if (this.getConditionChecked() == false){
			this.setConditionChecked(true);
			this.setConditionBoolean(this.getConditionExpression().evaluate(program).getValue());
		}
		
		if(this.getConditionBoolean()){
			if (this.getIfStatement().executeWithCheck(program, worm)){
				this.setConditionChecked(false);
				return  true;
			}
			else {
				return false;
			}
		}
		else {
			if (this.getElseStatement().executeWithCheck(program, worm)){
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
	public boolean welFormedStatement() {
		return this.getElseStatement().containsActionStatement() && this.getIfStatement().containsActionStatement();
	}

}
