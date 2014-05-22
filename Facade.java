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
import java.util.*;

import worms.gui.game.IActionHandler;
import worms.model.program.expressions.Expression;
import worms.model.program.statements.Statement;
import worms.model.program.types.Type;
import worms.model.programs.ParseOutcome;
import worms.model.programs.ProgramParser;

/**
 * A class for the facade of the worm.
 * 
 * @author 	Nicolas Hoppenbrouwers
 * 			Bram Lust
 * @version 3.0
 */
public class Facade implements IFacade {
	public Facade(){
	}
	
	/**
	 * Create and add an empty team with the given name to the given world.
	 * 
	 * (For single-student groups that do not implement teams, this method should have no effect)
	 */
	public void addEmptyTeam(World world, String newName) throws ModelException {
		try{
			world.addEmptyTeam(newName);
		}
		catch (IllegalArgumentException exc){
			throw new ModelException("Invalid name");
		}
		catch (IllegalStateException exc){
			throw new ModelException("Too many teams.");
		}
	}

	/**
	 * Create and add a new food ration to the given world.
	 * The food must be placed at a random adjacent location.
	 * 
	 * (For single-student groups that do not implement food, this method should have no effect)
	 */
	public void addNewFood(World world){
		world.addNewFood();
	}

	/**
	 * Returns whether or not the given worm can fall down
	 */
	public boolean canFall(Worm worm){
		return worm.canFall();
	}
	
	/**
	 * Returns whether or not the given worm is allowed to move.
	 */
	public boolean canMove(Worm worm){
		return (worm.getActionPoints() > 0);
	}

	
	/**
	 * Returns whether or not the given worm can turn by the given angle.
	 */
	public boolean canTurn(Worm worm, double angle){
		return worm.canTurn(angle);
	}
	
	/**
	 * Create a new food ration that is positioned at the given location in the given world.
	 * 
	 * @param world
	 * The world in which to place the created food ration
	 * @param x
	 * The x-coordinate of the position of the new food ration (in meter)
	 * @param y
	 * The y-coordinate of the position of the new food ration (in meter)
	 * 
	 * (For single-student groups that do not implement food, this method should have no effect)
	 */
	public Food createFood(World world, double x, double y){
		Food food = new Food(world,x,y);
		return food;
	}
	
	
	/**
	 * Creates a new world.
	 * 
	 * @param width The width of the world (in meter)
	 * 
	 * @param height The height of the world (in meter)
	 * 
	 * @param passableMap A rectangular matrix indicating which parts of the terrain are passable and impassable.
	 *  This matrix is derived from the transparency of the pixels in the image file of the terrain.
	 *  passableMap[r][c] is true if the location at row r and column c is passable, and false if that location is impassable.
	 *  The elements in the first row (row 0) represent the pixels at the top of the terrain (i.e., largest y-coordinates).
	 *  The elements in the last row (row passableMap.length-1) represent pixels at the bottom of the terrain (smallest y-coordinates).
	 *  The elements in the first column (column 0) represent the pixels at the left of the terrain (i.e., smallest x-coordinates).
	 *  The elements in the last column (column passableMap[0].length-1) represent the pixels at the right of the terrain (i.e., largest x-coordinates).     
	 * 
	 * @param random A random number generator, seeded with the value obtained from the command line or from GUIOptions,
	 *  that can be used to randomize aspects of the world in a repeatable way.
	 * 
	 * @return The world.
	 */
	public World createWorld(double width, double height,
			boolean[][] passableMap, Random random){
		
		World world = new World(width, height, passableMap, random);
		return world;
	}
	

	/**
	 * Makes the given worm fall down until it rests on impassable terrain again.
	 */
	public void fall(Worm worm){
		try{
			worm.fall();
		}
		catch (NullPointerException exc){
			worm.terminate();
		}
		
	}
	
	/**
	 * Returns the current number of action points of the given worm.
	 */
	public int getActionPoints(Worm worm){
		return worm.getActionPoints();
	}
	
	/**
	 * Returns the active projectile in the world, or null if no active projectile exists.
	 */
	public Projectile getActiveProjectile(World world){
		return world.getProjectile();
	}

	/**
	 * Returns the active worm in the given world (i.e., the worm whose turn it is).
	 */
	public Worm getCurrentWorm(World world){
		return world.getCurrentTeam().getCurrentWorm();
	}
	
	/**
	 * Returns all the food rations in the world
	 * 
	 * (For single-student groups that do not implement food, this method must always return an empty collection)
	 */
	public Collection<Food> getFood(World world){
		return world.getFood();
	}
	
	/**
	 * Returns the current number of hit points of the given worm.
	 */
	public int getHitPoints(Worm worm){
		return worm.getHitPoints();
	}
	
	/**
	 * Moves the given worm by the given number of steps.
	 */
	public void move(Worm worm){
		worm.move();
	}

	/**
	 * Returns the location on the jump trajectory of the given projectile after a
	 * time t.
	 * 
	 * @return An array with two elements, with the first element being the
	 *         x-coordinate and the second element the y-coordinate
	 */
	public double[] getJumpStep(Projectile projectile, double t){
		return projectile.jumpStep(t);
	}

	/**
	 * Determine the time that the given projectile can jump until it hits the terrain, hits a worm, or leaves the world.
	 * The time should be determined using the given elementary time interval.
	 * 
	 * @param projectile The projectile for which to calculate the jump time.
	 * 
	 * @param timeStep An elementary time interval during which you may assume
	 *                 that the projectile will not completely move through a piece of impassable terrain.
	 *                 
	 * @return The time duration of the projectile's jump.
	 */
	public double getJumpTime(Projectile projectile, double timeStep){
		return projectile.getJumpTime(timeStep);
	}

	/**
	 * Returns the maximum number of hit points of the given worm.
	 */
	public int getMaxHitPoints(Worm worm){
		return worm.getMaximumActionPoints();
	}

	/**
	 * Returns the radius of the given food ration
	 * 
	 * (For single-student groups that do not implement food, this method may return any value)
	 */
	public double getRadius(Food food){
		return food.getRadius();
	}

	/**
	 * Returns the radius of the given projectile.
	 */
	public double getRadius(Projectile projectile){
		return projectile.getRadius();
	}

	/**
	 * Returns the name of the weapon that is currently active for the given worm,
	 * or null if no weapon is active.
	 */
	public String getSelectedWeapon(Worm worm){
		return worm.getActiveWeapon();
	}

	/**
	 * Returns the name of the team of the given worm, or returns null if this
	 * worm is not part of a team.
	 * 
	 * (For single-student groups that do not implement teams, this method should always return null)
	 */
	public String getTeamName(Worm worm){
		return worm.getTeam().getName();
		
	}

	/**
	 * Returns the name of a single worm if that worm is the winner, or the name
	 * of a team if that team is the winner. This method should null if there is no winner.
	 * 
	 * (For single-student groups that do not implement teams, this method should always return the name of the winning worm, or null if there is no winner)
	 */
	public String getWinner(World world){
		return world.getWinner();
	}

	/**
	 * Returns all the worms in the given world
	 */
	public Collection<Worm> getWorms(World world){
		return world.getWorms();
	}

	/**
	 * Returns the x-coordinate of the given food ration
	 * 
	 * (For single-student groups that do not implement food, this method may return any value)
	 */
	public double getX(Food food){
		return food.getPosition().getX();
	}

	/**
	 * Returns the x-coordinate of the given projectile.
	 */
	public double getX(Projectile projectile){
		return projectile.getPosition().getX();
	}

	/**
	 * Returns the y-coordinate of the given food ration
	 * 
	 * (For single-student groups that do not implement food, this method may return any value)
	 */
	public double getY(Food food){
		return food.getPosition().getY();
	}

	/**
	 * Returns the y-coordinate of the given projectile.
	 */
	public double getY(Projectile projectile){
		return projectile.getPosition().getY();
	}

	/**
	 * Returns whether or not the given food ration is alive (active), i.e., not eaten.
	 * 
	 * (For single-student groups that do not implement food, this method should always return false)
	 */
	public boolean isActive(Food food){
		return (!food.isTerminated());
	}
	
	/**
	 * Returns whether the given projectile is still alive (active).
	 */
	public boolean isActive(Projectile projectile){
		return (!projectile.isTerminated());
	}

	/**
	 * Checks whether the given circular region of the given world,
	 * defined by the given center coordinates and radius,
	 * is passable and adjacent to impassable terrain. 
	 * 
	 * @param world The world in which to check adjacency
	 * @param x The x-coordinate of the center of the circle to check  
	 * @param y The y-coordinate of the center of the circle to check
	 * @param radius The radius of the circle to check
	 * 
	 * @return True if the given region is passable and adjacent to impassable terrain, false otherwise.
	 */
	public boolean isAdjacent(World world, double x, double y, double radius){
		return world.isAdjacent(x, y, radius);
	}

	/**
	 * Returns whether the given worm is alive
	 */
	public boolean isAlive(Worm worm){
		return (!worm.isTerminated());
	}

	/**
	 * Returns whether the game in the given world has finished.
	 */
	public boolean isGameFinished(World world){
		return world.isGameFinished();
	}

	/**
	 * Checks whether the given circular region of the given world,
	 * defined by the given center coordinates and radius,
	 * is impassable. 
	 * 
	 * @param world The world in which to check impassability 
	 * @param x The x-coordinate of the center of the circle to check  
	 * @param y The y-coordinate of the center of the circle to check
	 * @param radius The radius of the circle to check
	 * 
	 * @return True if the given region is impassable, false otherwise.
	 */
	public boolean isImpassable(World world, double x, double y, double radius){
		return world.isImpassable(x, y, radius);
	}

	/**
	 * Make the given projectile jump to its new location.
	 * The new location should be determined using the given elementary time interval. 
	 *  
	 * @param projectile The projectile that needs to jump
	 * 
	 * @param timeStep An elementary time interval during which you may assume
	 *                 that the projectile will not completely move through a piece of impassable terrain.
	 */
	public void jump(Projectile projectile, double timeStep){
		projectile.jump(timeStep);
		projectile.terminate();
		
	}


	/**
	 * Activates the next weapon for the given worm
	 */
	public void selectNextWeapon(Worm worm){
		worm.selectNextWeapon();
	}

	/**
	 * Makes the given worm shoot its active weapon with the given propulsion yield.
	 */
	public void shoot(Worm worm, int yield){
		try{
			worm.shoot(yield);
		}
		catch (IllegalStateException exc){
			throw new ModelException("This worm cannot shoot!");
		}

	}

	/**
	 * Starts a game in the given world.
	 */
	public void startGame(World world){
		world.startGame();
	}

	/**
	 * Starts the next turn in the given world
	 */
	public void startNextTurn(World world){
		world.startNextTurn();
	}

	/**
	 * Turns the given worm by the given angle.
	 */
	public void turn(Worm worm, double angle){
		worm.turn(angle);
	}

	/**
	 * Makes the given worm jump.
	 */
	public void jump(Worm worm,double direction){
		try{
			worm.jump();
		}
		catch (NullPointerException exc){
			worm.terminate();
		}
		catch (IllegalStateException exc){
			throw new ModelException("This worm cannot jump.");
		}
	}

	/**
	 * Returns the total amount of time (in seconds) that a
	 * jump of the given worm would take.
	 */
	public double getJumpTime(Worm worm,double timestep){
		return worm.getJumpTime(timestep);
	}

	/**
	 * Returns the location on the jump trajectory of the given worm
	 * after a time t.
	 *  
	 * @return An array with two elements,
	 *  with the first element being the x-coordinate and
	 *  the second element the y-coordinate
	 */
	public double[] getJumpStep(Worm worm, double t){
		return worm.jumpStep(t);
	}
		
	/**
	 * Returns the x-coordinate of the current location of the given worm.
	 */
	public double getX(Worm worm){
		return worm.getPosition().getX();
	}

	/**
	 * Returns the y-coordinate of the current location of the given worm.
	 */
	public double getY(Worm worm){
		return worm.getPosition().getY();
	}

	/**
	 * Returns the current orientation of the given worm (in radians).
	 */
	public double getOrientation(Worm worm){
		return worm.getDirection();
	}

	/**
	 * Returns the radius of the given worm.
	 */
	public double getRadius(Worm worm){
		return worm.getRadius();
	}
	
	/**
	 * Sets the radius of the given worm to the given value.
	 */
	public void setRadius(Worm worm, double newRadius) throws ModelException{
		try{
		worm.setRadius(newRadius);
		}
		catch (IllegalArgumentException exc){
			throw new ModelException("Cannot shrink/grow worm anymore.");
		}
	}
	
	/**
	 * Returns the minimal radius of the given worm.
	 */
	public double getMinimalRadius(Worm worm){
		return worm.getMinimalRadius();
	}

	
	/**
	 * Returns the maximum number of action points of the given worm.
	 */
	public int getMaxActionPoints(Worm worm){
		return worm.getMaximumActionPoints();
	}
	
	/**
	 * Returns the name the given worm.
	 */
	public String getName(Worm worm){
		return worm.getName();
	}
		
	/**
	 * Renames the given worm.
	 */
	public void rename(Worm worm, String newName) throws ModelException{
		try{
			worm.setName(newName);
		}
		catch (IllegalArgumentException exc){
			throw new ModelException("Invalid name");
			
		}
	}

	/**
	 * Returns the mass of the given worm.
	 */
	public double getMass(Worm worm){
		return worm.getMass();
	}
	
	public ArrayList<Food> getFood(Food food){
		ArrayList<Food> usersArrayList = new ArrayList<Food>();
		return usersArrayList;
	}

	@Override
	public void addNewWorm(World world, worms.model.Program program) {
		world.addNewWorm(program);
	}

	@Override
	public Worm createWorm(World world, double x, double y, double direction,
			double radius, String name, worms.model.Program program) {
		return new Worm(world,x,y,direction,radius,name,program);
	}

	@Override
	public ParseOutcome<?> parseProgram(String programText,
			IActionHandler handler) {
		ProgramParser<Expression,Statement,Type<?>> programParser = new ProgramParser<Expression,Statement,Type<?>>(new ProgramFactoryImpl());
		programParser.parse(programText);
		if (programParser.getErrors().isEmpty())
			return ParseOutcome.failure(programParser.getErrors());
		else{
			Program program = new Program(null, programParser.getStatement(), programParser.getGlobals(), handler);
			return ParseOutcome.success(program);
		}
		
	}

	@Override
	public boolean hasProgram(Worm worm) {
		return worm.hasProgram();
	}

	@Override
	public boolean isWellFormed(worms.model.Program program) {
		return program.isWellFormed();
	}

}
