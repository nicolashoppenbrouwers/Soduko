package worms.model.program;

import java.util.List;

import worms.model.programs.ProgramFactory;
import worms.model.programs.ProgramFactory.ForeachType;
import worms.model.program.expressions.*;
import worms.model.program.statements.*;
import worms.model.program.types.*;

@SuppressWarnings("unchecked")
public class ProgramFactoryImpl implements ProgramFactory<Expression<?>, Statement, Type<?>> {

	public ProgramFactoryImpl() {
	}

	/* possible types for a foreach statement */
//WAT IS DIT????
	public enum ForeachType {
		WORM, FOOD, ANY
	}

	
	@Override
	public Expression<DoubleLiteral> createDoubleLiteral(int line, int column, double d) {
		return new DoubleLiteral(line,column,d);
	}

	@Override
	public Expression<BooleanLiteral> createBooleanLiteral(int line, int column, boolean b) {
		return new BooleanLiteral(line,column,b);
	}

	
	@Override
	public Expression<BooleanLiteral> createAnd(int line, int column, Expression<?> e1,
			Expression<?> e2) {
		//JE MOET DE VRAAGTEKENS ERBIJ SCHRIJVEN EN DAN CASTEN OP DE VOLGENDE LIJN, anders werkt het niet precies...
		return new And(line,column,(Expression<BooleanLiteral>) e1,(Expression<BooleanLiteral>) e2);
	}

	@Override
	public Expression<BooleanLiteral> createOr(int line, int column, Expression<?> e1,
			Expression<?> e2) {
		return new Or(line,column,(Expression<BooleanLiteral>) e1,(Expression<BooleanLiteral>) e2);
	}

	@Override
	public Expression<BooleanLiteral> createNot(int line, int column, Expression<?> e) {
		return new Not(line,column,(Expression<BooleanLiteral>) e);
	}

	@Override
	public Expression createNull(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSelf(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<DoubleLiteral> createGetX(int line, int column, Expression<?> e) {
		return new GetX(line,column,(Expression<Entity>) e);
	}

	@Override
	public Expression<DoubleLiteral> createGetY(int line, int column, Expression<?> e) {
		return new GetX(line,column,(Expression<Entity>) e);
	}

	@Override
	public Expression<DoubleLiteral> createGetRadius(int line, int column, Expression<?> e) {
		return new GetRadius(line,column,(Expression<Entity>) e);
	}

	@Override
	public Expression<DoubleLiteral> createGetDir(int line, int column, Expression<?> e) {
		return new GetDirection(line,column,(Expression<Entity>) e);
	}

	@Override
	public Expression<DoubleLiteral> createGetAP(int line, int column, Expression<?> e) {
		return new GetActionPoints(line,column,(Expression<Entity>) e);
	}

	@Override
	public Expression<DoubleLiteral> createGetMaxAP(int line, int column, Expression<?> e) {
		return new GetMaximumActionPoints(line,column,(Expression<Entity>) e);
	}

	@Override
	public Expression<DoubleLiteral> createGetHP(int line, int column, Expression<?> e) {
		return new GetHitPoints(line,column,(Expression<Entity>) e);
	}

	@Override
	public Expression<DoubleLiteral> createGetMaxHP(int line, int column, Expression<?> e) {
		return new GetMaximumHitPoints(line,column,(Expression<Entity>) e);
	}

	@Override
	public Expression<BooleanLiteral> createSameTeam(int line, int column, Expression<?> e) {
		return new SameTeam(line,column,(Expression<Entity>)e);
	}

	@Override
	public Expression createSearchObj(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsWorm(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsFood(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createVariableAccess(int line, int column, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createVariableAccess(int line, int column, String name,
			Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<BooleanLiteral> createLessThan(int line, int column, Expression<?> e1,
			Expression<?> e2) {
		return new LessThan(line,column,(Expression<DoubleLiteral>) e1,(Expression<DoubleLiteral>) e2);
	}

	@Override
	public Expression<BooleanLiteral> createGreaterThan(int line, int column, Expression<?> e1,
			Expression<?> e2) {
		return new GreaterThan(line,column,(Expression<DoubleLiteral>) e1,(Expression<DoubleLiteral>) e2);
	}

	@Override
	public Expression<BooleanLiteral> createLessThanOrEqualTo(int line, int column,
			Expression<?> e1, Expression<?> e2) {
		return new LessThanOrEqualTo(line,column,(Expression<DoubleLiteral>) e1,(Expression<DoubleLiteral>) e2);
	}

	@Override
	public Expression<BooleanLiteral> createGreaterThanOrEqualTo(int line, int column,
			Expression<?> e1, Expression<?> e2) {
		return new GreaterThanOrEqualTo(line,column,(Expression<DoubleLiteral>) e1,(Expression<DoubleLiteral>) e2);
	}

	@Override
	public Expression<BooleanLiteral> createEquality(int line, int column, Expression<?> e1,
			Expression<?> e2) {
		return new Equality(line,column,(Expression<DoubleLiteral>) e1, (Expression<DoubleLiteral>) e2);
	}

	@Override
	public Expression createInequality(int line, int column, Expression e1,
			Expression e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createAdd(int line, int column, Expression e1,
			Expression e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSubtraction(int line, int column, Expression e1,
			Expression e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createMul(int line, int column, Expression e1,
			Expression e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createDivision(int line, int column, Expression e1,
			Expression e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSqrt(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSin(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createCos(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createTurn(int line, int column, Expression angle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createMove(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createJump(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createToggleWeap(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createFire(int line, int column, Expression yield) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createSkip(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createAssignment(int line, int column,
			String variableName, Expression rhs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createIf(int line, int column, Expression condition,
			Statement then, Statement otherwise) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createWhile(int line, int column, Expression condition,
			Statement body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createForeach(int line, int column,
			worms.model.programs.ProgramFactory.ForeachType type,
			String variableName, Statement body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createSequence(int line, int column,
			List<Statement> statements) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createPrint(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type createDoubleType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type createBooleanType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type createEntityType() {
		// TODO Auto-generated method stub
		return null;
	}	
}