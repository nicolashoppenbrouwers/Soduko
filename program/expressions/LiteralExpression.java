package worms.model.program.expressions;

import worms.model.program.Program;
import worms.model.program.types.Type;


//AFWERKEN!!!
public abstract class LiteralExpression extends Expression {

	public LiteralExpression(int line, int column, Type<?> type) {
		super(line,column);
		this.type = type;
	}
	
	public Type<?> getType(){
		return this.type;
	}
	
	private final Type<?> type;

	@Override
	public abstract Expression evaluate(Program program);
	

}
