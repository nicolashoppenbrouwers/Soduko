package worms.model.program.expressions;

import worms.model.program.Program;

public abstract class Literal<T,S> extends Expression<T>{

	Literal(int line, int column) {
		super(line,column);
	}
	
//	@Override
//	public T evaluate(Program program){
//		return this.evaluate(program);
//	}
//	
//	public abstract T evaluateLiteral(Program program);
	
	public abstract S getValue();
}
