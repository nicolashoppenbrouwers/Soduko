package worms.model.program.expressions;

import worms.model.program.Program;

public class Division extends Expression<DoubleLiteral> {
	
	public Division(int line, int column, Expression<DoubleLiteral> e1, Expression<DoubleLiteral> e2) {
		super(line,column);
		this.expressionLeft = e1;
		this.expressionRight = e2;
	}
	
	public Expression<DoubleLiteral> getExpressionLeft() {
		return this.expressionLeft;
	}

	public Expression<DoubleLiteral> getExpressionRight() {
		return this.expressionRight;
	}

	private final Expression<DoubleLiteral> expressionLeft;
	private final Expression<DoubleLiteral> expressionRight;

	
	@Override
	public DoubleLiteral evaluate(Program program){
		//Division by zero.
		//Het kan zijn dat JAVA zelf al NaN teruggeeft bij deling door 0.
		if (getExpressionRight().evaluate(program).getDoubleValue() == 0)
			//PROGRAMMA MOET STOPPEN: program.getWorm().getWorld().startNextTurn();
			return new DoubleLiteral(getLine(),getColumn(),Double.NaN);
		
		double division = getExpressionLeft().evaluate(program).getDoubleValue() / getExpressionRight().evaluate(program).getDoubleValue();
		return new DoubleLiteral(getLine(),getColumn(),division);
	}

}