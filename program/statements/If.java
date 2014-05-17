package worms.model.program.statements;

import worms.model.program.expressions.Expression;

public class If extends Statement {

	public If(int line, int column, Expression<?> condition,Statement then,Statement otherwise) {
		super(line, column);
		this.conditionExpr= condition;
		this.thenStat = then;
		this.otherwiseStat = otherwise;
	}
	
	public Expression<?> getCondition() {
		return this.conditionExpr;
	}
	
	public Statement getThen() {
		return this.thenStat;
	}
	
	public Statement getOtherwise() {
		return this.otherwiseStat;
	}

	private final Expression<?> conditionExpr;
	private final Statement thenStat;
	private final Statement otherwiseStat;

	
	public boolean execute(Program program){
		if (conditionExpr.evaluate(program).getValue()){
			
		}
		else{
			
		}
			
        
        /* Perform the statement, if we're searching for the last statement -> perform both if necessary */
        if(program.isFinished()) { //We're executing like usual
            if((Boolean) condition.getResult()) {
                if(!thenStatement.execute(program))
                    return false;
            } else {
                if(!otherwiseStatement.execute(program))
                   return false;
            }
        } else { //Find the last statement! if not in first, execute second.
            if(!thenStatement.execute(program))
                return false;
            
            if(!program.isFinished()) {
                if(!otherwiseStatement.execute(program))
                    return false;
            }
        }
        
        return true;
    }

    @Override
    public List<Statement> getStatements() {
        ArrayList<Statement> myList = new ArrayList<>();
        myList.add(thenStatement);
        myList.add(otherwiseStatement);
        return myList;
    }
    
}
