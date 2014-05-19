package worms.model.program.expressions;

import worms.model.MovableGameObject;
import worms.model.program.Program;
import worms.model.program.types.Entity;

public class GetDirection extends Expression<DoubleLiteral>{

	public GetDirection(int line, int column, Expression<Entity> e){
		super(line,column);
		this.entityExpression = e;
	}
	
	public Expression<Entity> getEntityExpression(){
		return this.entityExpression;
	}
	
	private final Expression<Entity> entityExpression;

	@Override
	public DoubleLiteral evaluate(Program program) {
		//Als de entity geen Movable GameObject is, heeft het geen direction.
		if ((getEntityExpression().evaluate(program).getValue() == null) || (! (getEntityExpression().evaluate(program).getValue() instanceof worms.model.MovableGameObject)))
			//Wat returnen we dan? isNaN?
			return new DoubleLiteral(getLine(),getColumn(),Double.NaN);
		//Je moet hier casten naar MovableGameObject, anders lukt het niet.
		MovableGameObject movableGameObject = (MovableGameObject) getEntityExpression().evaluate(program).getValue();
		return new DoubleLiteral(getLine(),getColumn(),movableGameObject.getDirection());
	}
	


}
