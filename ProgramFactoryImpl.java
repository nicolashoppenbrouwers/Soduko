/*
 * Assignment 'Worms' Object-Oriented Programming
 * University:		KU Leuven
 * Study:			Bachelor Ingenieurswetenschappen
 * Course:			Objectgericht Programmeren (H01P1A)
 * Year:			2013 - 2014
 * Authors: 		Nicolas Hoppenbrouwers 	(Computerwetenschappen - Werktuigkunde)
 * 					Bram Lust 				(Computerwetenschappen - Elektrotechniek)
 * Git: 			https://github.com/nicolashoppenbrouwers/Soduko.git
 */

package worms.model;

import java.util.List;

import worms.model.programs.ProgramFactory;
import worms.model.program.expressions.*;
import worms.model.program.statements.*;
import worms.model.program.types.*;
import worms.model.program.types.Boolean;
import worms.model.program.types.Double;


public class ProgramFactoryImpl implements ProgramFactory<Expression, Statement, Type<?>> {

	public ProgramFactoryImpl() {
	}

	public enum ForeachType {
		WORM, FOOD, ANY
	}

	
	@Override
	public DoubleLiteral createDoubleLiteral(int line, int column, double d) {
		return new DoubleLiteral(line,column,d);
	}

	@Override
	public BooleanLiteral createBooleanLiteral(int line, int column, boolean b) {
		return new BooleanLiteral(line,column,b);
	}

	
	@Override
	public And createAnd(int line, int column, Expression e1,
			Expression e2) {
		return new And(line,column,e1,e2);
	}

	@Override
	public Or createOr(int line, int column, Expression e1,
			Expression e2) {
		return new Or(line,column,e1,e2);
	}

	@Override
	public Not createNot(int line, int column, Expression e) {
		return new Not(line,column,e);
	}

	@Override
	public Null createNull(int line, int column) {
		return new Null(line,column);
	}

	@Override
	public Self createSelf(int line, int column) {
		return new Self(line,column);
	}

	@Override
	public GetX createGetX(int line, int column, Expression e) {
		return new GetX(line,column,e);
	}

	@Override
	public GetY createGetY(int line, int column, Expression e) {
		return new GetY(line,column,e);
	}

	@Override
	public GetRadius createGetRadius(int line, int column, Expression e) {
		return new GetRadius(line,column,e);
	}

	@Override
	public GetDirection createGetDir(int line, int column, Expression e) {
		return new GetDirection(line,column,e);
	}

	@Override
	public GetActionPoints createGetAP(int line, int column, Expression e) {
		return new GetActionPoints(line,column,e);
	}

	@Override
	public GetMaximumActionPoints createGetMaxAP(int line, int column, Expression e) {
		return new GetMaximumActionPoints(line,column,e);
	}

	@Override
	public GetHitPoints createGetHP(int line, int column, Expression e) {
		return new GetHitPoints(line,column,e);
	}

	@Override
	public GetMaximumHitPoints createGetMaxHP(int line, int column, Expression e) {
		return new GetMaximumHitPoints(line,column,e);
	}

	@Override
	public SameTeam createSameTeam(int line, int column, Expression e) {
		return new SameTeam(line,column,e);
	}

	@Override
	public Expression createSearchObj(int line, int column, Expression e) {
		return new SameTeam(line,column,e);
	}

	@Override
	public Expression createIsWorm(int line, int column, Expression e) {
		return new IsWorm(line,column,e);
	}

	@Override
	public Expression createIsFood(int line, int column, Expression e) {
		return new IsFood(line,column,e);
	}

	@Override
	public Expression createVariableAccess(int line, int column, String name) {
		return new VariableAccess(line,column,name);
	}

	@Override
	public Expression createVariableAccess(int line, int column, String name,
			Type<?> type) {
		return null;
	}

	@Override
	public LessThan createLessThan(int line, int column, Expression e1,
			Expression e2) {
		return new LessThan(line,column,e1,e2);
	}

	@Override
	public GreaterThan createGreaterThan(int line, int column, Expression e1,
			Expression e2) {
		return new GreaterThan(line,column,e1,e2);
	}

	@Override
	public LessThanOrEqualTo createLessThanOrEqualTo(int line, int column,
			Expression e1, Expression e2) {
		return new LessThanOrEqualTo(line,column,e1,e2);
	}

	@Override
	public GreaterThanOrEqualTo createGreaterThanOrEqualTo(int line, int column,
			Expression e1, Expression e2) {
		return new GreaterThanOrEqualTo(line,column,e1,e2);
	}

	@Override
	public Equality createEquality(int line, int column, Expression e1,
			Expression e2) {
		return new Equality(line,column,e1,e2);
	}

	@Override
	public Inequality createInequality(int line, int column, Expression e1,
			Expression e2) {
		return new Inequality(line,column,e1,e2);
	}

	@Override
	public Add createAdd(int line, int column, Expression e1,
			Expression e2) {
		return new Add(line,column,e1,e2);
	}

	@Override
	public Substraction createSubtraction(int line, int column, Expression e1,
			Expression e2) {
		return new Substraction(line,column,e1,e2);
	}

	@Override
	public Multiplication createMul(int line, int column, Expression e1,
			Expression e2) {
		return new Multiplication(line,column,e1,e2);
	}

	@Override
	public Division createDivision(int line, int column, Expression e1,
			Expression e2) {
		return new Division(line,column,e1,e2);
	}

	@Override
	public Sqrt createSqrt(int line, int column, Expression e) {
		return new Sqrt(line,column,e);
	}

	@Override
	public Sin createSin(int line, int column, Expression e) {
		return new Sin(line,column,e);
	}

	@Override
	public Cos createCos(int line, int column, Expression e) {
		return new Cos(line,column,e);
	}

	@Override
	public Statement createTurn(int line, int column, Expression angle) {
		return new Turn(line,column,angle);
	}

	@Override
	public Statement createMove(int line, int column) {
		return new Move(line,column);
	}

	@Override
	public Statement createJump(int line, int column) {
		return new Jump(line,column);
	}

	@Override
	public Statement createToggleWeap(int line, int column) {
		return new ToggleWeap(line,column);
	}

	@Override
	public Statement createFire(int line, int column, Expression yield) {
		return new Fire(line,column,yield);
	}

	@Override
	public Statement createSkip(int line, int column) {
		return new Skip(line,column);
	}

	@Override
	public Statement createAssignment(int line, int column,
			String variableName, Expression rhs) {
		return new Assignment(line,column,variableName,rhs);
	}

	@Override
	public Statement createIf(int line, int column, Expression condition,
			Statement then, Statement otherwise) {
		return new If(line,column,condition,then,otherwise);
	}

	@Override
	public Statement createWhile(int line, int column, Expression condition,
			Statement body) {
		return new While(line,column,condition,body);
	}

	@Override
	public Statement createForeach(int line, int column,
			worms.model.programs.ProgramFactory.ForeachType type,
			String variableName, Statement body) {
		return new Foreach(line,column,type,variableName,body);
	}

	@Override
	public Statement createSequence(int line, int column,
			List<Statement> statements) {
		return new Sequence(line,column,statements);
	}

	@Override
	public Statement createPrint(int line, int column, Expression e) {
		return new Print(line,column,e);
	}

	@Override
	public Double createDoubleType() {
		return new Double();
	}

	@Override
	public Boolean createBooleanType() {
		return new Boolean();
	}

	@Override
	public Entity createEntityType() {
		return new Entity();
	}	
}