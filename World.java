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

import be.kuleuven.cs.som.annotate.*;
import worms.util.Util;




//KLASSEINVARIANT DYNAMISCHE BINDING!!!

/**
 * A class of worlds, containing a width, height, passablemap, random, a list of worms,
 * 	teams and foods, an active projectile and an index of the current team.
 * 
 * @invar 	The width of each world should be a valid width for a world.
 * 			| isValidWidth(this.getWidth())
 * @invar 	The height of each world should be a valid height for a world.
 * 			| isValidHeight(this.getHeight())
 * @invar	The world should never contain more than 10 teams.
 * 			| (!amountOfTeamsExceeded())
 * @invar	Each team in the list of teams of a world should be part of that world.
 * 			| for (Team team: this.getTeams())
 * 			| 	team.getWorld() == this
 * @invar 	Each worm in the list of worms of a world should be part of that world.
 * 			| for (Worm team: this.getTeams())
 * 			|	worm.getWorld() == this
 * 
 * @author 	Nicolas Hoppenbrouwers
 * 			Bram Lust
 * @version	2.0
 *
 */
public class World {
	/**
	 * Initialize this new world with given width, height, passablemap and random.
	 * 
	 * @param	width
	 * 			The new width of this world.
	 * @param	height
	 * 			The new height of this world.
	 * @param	passableMap
	 * 			The new passable map of this world, which contains which pixels are passable and which are not.
	 * @param	random
	 * 			The new random of this world.
	 * @effect	The new width of this world is equal to the given width.
	 * 			| new.getWidth() == width
	 * @effect	The new height of this world is equal to the given height.
	 * 			| new.getHeight() == height
	 * @effect	The new passable map of this world is equal to the given passable map.
	 * 			| new.getPassableMap() == passableMap
	 * @effect	The new random of this world is equal to the given random.
	 * 			| new.getRandom() == random
	 * @effect	The list of worms of this world is initialized as an empty array.
	 * 			| new.getWorms() == ArrayList<Worm>()
	 * @effect	The list of teams of this world contains the empty team 'Independent'.
	 * 			| new.getTeams() == Team(this, 'Independent')
	 * @effect	The list of foods of this world is initialized as an empty array.
	 * 			| new.getFood() == ArrayList<Food>()
	 * @effect	The index of the current team is initialized as zero.
	 * 			| new.getNbCurrentTeam() == 0
	 * @effect	The active projectile of this world is null.
	 * 			| new.getActiveProjectile() == null	 *
	 */
	public World (double width, double height, boolean[][] passableMap, Random random) 
			throws IllegalArgumentException, IllegalStateException{
		this.setWidth(width);
		this.setHeight(height);
		this.setPassableMap(passableMap);
		this.setRandom(random);
		this.listOfWorms = new ArrayList<Worm>();
		this.listOfTeams = new ArrayList<Team>();
		this.addEmptyTeam("Independent");
		this.listOfFood = new ArrayList<Food>();
		setNbCurrentTeam(0);
		this.projectile = null;
	}
	

	
	
	
	
	//IN ORDE
	/**
	 * Returns the width of this world.
	 */
	@Basic
	public double getWidth(){
		return this.width;
	}
	
	/**
	 * Sets the width of this world to the given value.
	 * 
	 * @param 	width
	 * 			The new width of this world.
	 * @post	The new width of this world is equal to the given width.
	 * 			| new.getWidth() == width
	 * @throws 	IllegalArgumentException
	 * 			The width is not a valid width for a world.
	 * 			| (!isValidWidth(width))
	 */
	public void setWidth(double width) throws IllegalArgumentException{
		if (!isValidWidth(width))
			throw new IllegalArgumentException("Impossible width!"); 
		this.width = width;
	}
	
	/**
	 * Checks whether the given width is a valid width for a world.
	 * 
	 * @param 	width
	 * 			The width to be checked.
	 * @return	True if and only if the given width is not equal to Double.NaN, and
	 * 			is smaller than Double.MAX_VALUE and bigger than zero.
	 * 			| '!(Double.isNaN(width)) && (width > 0) && (width <= Double.MAX_VALUE)
	 */
	public static boolean isValidWidth(double width){
		return  (!(Double.isNaN(width)) && (width > 0) && (width <= Double.MAX_VALUE));
	}
	
	/**
	 * Variable registering the width of this world.
	 */
	private double width;
	
	/**
	 * Returns the height of this world.
	 */
	@Basic
	public double getHeight(){
		return this.height;
	}
	
	/**
	 * Sets the height of this world to the given value.
	 * @param 	height
	 * 			The new height of this world.
	 * @post	The new height of this world is equal to the given height.
	 * 			| new.getHeight() == height
	 * @throws 	IllegalArgumentException
	 * 			The height is not a valid height for a world.
	 * 			| (!isValidWidth(width))
	 */
	public void setHeight(double height){
		if (!isValidHeight(height))
			throw new IllegalArgumentException("Impossible height!"); 
		this.height = height;
	}
	
	/* Wij hebben ervoor gekozen om niet een gemeenschappelijke functie isValidBoundary te maken omdat
 	het volgens ons mogelijk is dat de upperbound van height niet gelijk is aan upperbound van width (want breedbeeld enz) */
	/**
	 * Checks whether the given height is a valid height for a world.
	 * 
	 * @param 	height
	 * 			The height to be checked.
	 * @return	True if and only if the given height is not equal to Double.NaN, and
	 * 			is smaller than Double.MAX_VALUE and bigger than zero.
	 * 			| '!(Double.isNaN(height)) && (height > 0) && (height <= Double.MAX_VALUE)
	 */
	public static boolean isValidHeight(double height){
		return !(Double.isNaN(height)) && (height > 0) && (height <= Double.MAX_VALUE);
	}
	
	/**
	 * Variable registering the height of this world.
	 */
	private double height;
	
	
	
	
	
	
	
	
	
	//IN ORDE
	/**
	 * Returns the passable map of this world, which contains which pixels are passable and which are not.
	 */
	@Basic
	public boolean[][] getPassableMap(){
		return this.passableMap;
	}
	
	/**
	 * Sets the passable map of this world to the given passable map.
	 */
	@Basic
	public void setPassableMap(boolean[][] passableMap){
		this.passableMap = passableMap;
	}


	/**
	 * Variable registering the passable map of this world.
	 */
	private boolean[][] passableMap;
	
	
		
	
	
	
	
	
	
	/**
	 * Returns the random of this world.
	 */
	@Basic
	public Random getRandom(){
		return this.random;
	}
	
	/**
	 * Sets the random of this world to the given random.
	 */
	@Basic
	public void setRandom(Random random){
		this.random = random;
	}
	
	/**
	 * Variable registering the random of this world.
	 */
	private Random random;
	
	
	
	
	
	
	
	/**
	 * Return the list of worms of this world.
	 */
	@Basic
	public ArrayList<Worm> getWorms(){
		return this.listOfWorms;
	}
	
	/**
	 * Sets the list of worms of this world to the given list.
	 * 
	 * @param 	wormList
	 * 			The new list of worms of this world.
	 * @post	The list of worms of this world is equal to the given list of worms.
	 * 			| new.getWorms() == wormList
	 * @throws	IllegalArgumentException
	 * 			The given list of worms contains worms that are not part of this world.
	 * 			| (!canHaveAsWormList(wormList)
	 */
	public void setWorms(ArrayList<Worm> wormList) throws IllegalArgumentException{
		if (! canHaveAsWormList(wormList))
			throw new IllegalArgumentException("The given wormlist contains worms that are not part of this world.");
		this.listOfWorms = wormList;
	}
	
	/**
	 * Checks whether all the worms of the given list belong to this world.
	 * 
	 * @param 	wormList
	 * 			The list of worms to check.
	 * @return	True if and only if all the worms of the given list of worms belong to this world.
	 * 			| for (Worm worm: wormList)
	 * 			|	if (!canHaveAsWorm(worm))
	 * 			|		result == false
	 * 			| result == true
	 */
	public boolean canHaveAsWormList(ArrayList<Worm> wormList){
		for (Worm worm: wormList)
			if (!canHaveAsWorm(worm))
				return false;
		return true;
	}
	
	/**
	 * Checks whether the given worm belongs to this world.
	 * @param 	worm
	 * 			The worm to check.
	 * @return	True if and only if the world of the given worm is equal to this world.
	 * 			| result == (worm.getWorld() == this)
	 */
	public boolean canHaveAsWorm(Worm worm){
		return (worm.getWorld() == this);
	}
	
	/**
	 * Adds a default worm with a given program to this world.
	 * 
	 * @param 	program
	 * 			The program of the worm that is added to this world.
	 * @effect	Creates a new worm with this as world, a random starting position, zero as direction,
	 * 			a radius of 0.40, a random name and a given program.
	 * 			|  Worm worm = new Worm(this,searchAdjacentStartingPos(0.40)[0],searchAdjacentStartingPos(0.40)[1],0.0,0.40,Worm.getRandomName(),program)
	 * @effect	The new worm is added to the list of worms.
	 * 			| hasAsWorm(worm)
	 */
	public void addNewWorm(Program program){
		double radius = 0.40;
		double[] startPos = this.searchAdjacentStartingPos(radius);
		Worm worm = new Worm(this,startPos[0],startPos[1],0.0,radius,Worm.getRandomName(),program);
		this.getWorms().add(worm);
	}
	
	/**
	 * A list of worms registering the worms that are part of this world.
	 */
	private ArrayList<Worm> listOfWorms;
	
	/**
	 * Removes the given worm from this world.
	 * 
	 * @param 	worm
	 * 			The worm which has to be removed from this world.
	 * @effect	The worm is removed from the list of worms.
	 * 			| ! hasAsWorm(worm)
	 * @throws	IllegalArgumentException
	 * 			The given worm is null.
	 * 			| (worm == null)
	 */
	public void removeWorm(Worm worm){
		if (worm == null)
			throw new IllegalArgumentException("The given worm shouldn't be null.");
		/* Als de worm geen deel is van de list of worms van deze world gebeurt er niets. 
		 * Dit is zo dankzij de voorgedefineerde methode remove van JAVA. */
		this.getWorms().remove(worm);
	}
	
	/**
	 * Returns whether this world has the given worm as one of its worms.
	 * 
	 * @return 	True if and only if the worm has this worm as one of its worms.
	 *			| for (Worm worm: this.getWorms())
	 *			| 	if (worm == wormToCheck)
	 *			|		result == true
	 *			| result == false
	 */
	public boolean hasAsWorm(Worm wormToCheck){
		for (Worm worm: this.getWorms())
			if (worm == wormToCheck)
				return true;
		return false;
	}
	
	/**
	 * Returns the number of the current team of this world.
	 */
	@Basic
	public int getNbCurrentTeam(){
		return this.nbCurrentTeam;
	}
	
	/**
	 * Retuns the current team of this world.
	 * 
	 * @return 	If the world has no teams, returns null.
	 * 			| if (this.getTeams().size() == 0)
	 * 			|	result == null
	 * 			Otherwise, return the current team.
	 * 			| result == this.getTeams().get(this.getNbCurrentTeam())
	 */
	public Team getCurrentTeam(){
		if (this.getTeams().size() == 0)
			return null;
		return this.getTeams().get(this.getNbCurrentTeam());
	}
	
	/**
	 * Returns the current worm of this world.
	 * 
	 * @return	Returns the current worm of the current team of this world.
	 * 			| result == this.getCurrentTeam().getCurrentWorm()
	 * @throws	NullPointerException
	 * 			The current team is null.
	 * 			| this.getCurrentTeam() == null
	 */
	public Worm getCurrentWorm() throws NullPointerException{
		if (this.getCurrentTeam() == null)
			throw new NullPointerException();
		return this.getCurrentTeam().getCurrentWorm();
	}
	
	/**
	 * Sets the number of the current team of this world to the given value.
	 * 
	 * @param 	numberTeam
	 * 			The new index of the current team of this world
	 * @effect	The new index of the current team of this world is equal to the given index.
	 * 			| new.getNbCurrentTeam() == team
	 * @throws	IllegalArgumentException
	 * 			The given index is larger than the amount of teams - 1 or smaller than zero.
	 * 			| ((numberTeam > this.getTeams().size() - 1) && (numberTeam < 0))
	 */
	public void setNbCurrentTeam(int numberTeam){
		if ((numberTeam > this.getTeams().size() - 1) && (numberTeam < 0))
			throw new IllegalArgumentException("The given index is too large or smaller than zero.");
		this.nbCurrentTeam = numberTeam;
	}
	

	/**
	 * Variable registering the index of the current team of this world.
	 */
	private int nbCurrentTeam;

	


	
	
	
	
	/**
	 * Returns the list of teams of this world.
	 */
	@Basic
	public ArrayList<Team> getTeams(){
		return this.listOfTeams;
	}
	
	/**
	 * Sets the list of teams of this world to the given list of teams.
	 * 
	 * @param 	teams
	 * 			The new list of teams of this world.
	 * @post	The new list of teams of this world is equal to the given list.
	 * 			| new.getTeams() == teams;
	 * @throws	IllegalArgumentException
	 * 			The given list contains too many teams.
	 * 			| (teams.size() > 10)
	 */
	public void setTeams(ArrayList<Team> teams){
		if (teams.size() > 10)
			throw new IllegalArgumentException("Too many teams.");		
		this.listOfTeams = teams;
	}
	
	/**
	 * Add an empty team with given name to this world.
	 * 
	 * @param 	newName
	 * 			The name of the team that is added to this world.
	 * @effect	A new team is created with the given name.
	 * 			| Team team = new Team(this.newName)
	 * @effect	The new team is added to the list of teams of this world.
	 * 			| hasAsTeam(team)
	 * @effect	The last team added of this world is set to this team.
	 * 			| getLastTeamAdded() == team
	 * @effect	The new number of current team of this world is equal 
	 * 			to the current number of current team of this world + 1.
	 * 			| new.getNbCurrentTeam() == this.getNbCurrentTeam() + 1 
	 */
	public void addEmptyTeam(String newName){
		if (amountOfTeamsExceeded())
			throw new IllegalStateException("This world already has 10 teams.");
		Team team  = new Team(this, newName);
		this.getTeams().add(team);
		setLastTeamAdded(team);
		setNbCurrentTeam(getNbCurrentTeam() + 1);
	}
	
	/**
	 * Checks whether this world can have the given team as its team.
	 * 
	 * @param 	team
	 * 			The team to be checked.
	 * @return	True if and only if the world if the given team is equal to this world.
	 * 			| result == (team.getWorld() == this)
	 */
	public boolean canHaveAsTeam(Team team){
		return (team.getWorld() == this);
	}
	
	/**
	 * Checks whether the amount of teams of this world has been exceeded.
	 * 
	 * @return	True if and only if the amount of teams of this world is larger than 10.
	 * 			| result == (this.getTeams().size() > 10)
	 */
	public boolean amountOfTeamsExceeded(){
		/* Lege teams worden hier ook nog meegerekend. Dit moet eruit.*/
		return (this.getTeams().size() > 10);
	}
	
	/**
	 * Removes the given team from this world.
	 * 
	 * @param 	team
	 * 			The team which has to be removed from this world.
	 * @effect	The team is removed from the list of worms.
	 * 			| !world.hasAsTeam(team)
	 * @throws	IllegalArgumentException
	 * 			The given team is null.
	 * 			| (team == null)
	 */
	public void removeTeam(Team team) throws IllegalArgumentException{
		if (team == null)
			throw new IllegalArgumentException("The given team shouldn't be null.");
		/* Als het team geen deel is van de list of teams van deze world gebeurt er niets. 
		 * Dit is zo dankzij de voorgedefineerde methode remove van JAVA. */
		this.getTeams().remove(team);
	}
	
	/**
	 * List registering the list of teams of this world.
	 */
	private ArrayList<Team> listOfTeams;

	/**
	 * Returns whether this world has the given team as one of its teams.
	 * 
	 * @return 	True if and only if the worm has this team as one of its teams.
	 *			| for (Team team: this.getTeams())
	 *			| 	if (team == teamToCheck)
	 *			|		result == true
	 *			| result == false
	 */
	public boolean hasAsTeam(Team teamToCheck){
		for (Team team: this.getTeams())
			if (team == teamToCheck)
				return true;
		return false;
	}
	
	
	
	/**
	 * Returns the last team that has been added to this world.
	 */
	@Basic
	public Team getLastTeamAdded(){
		return this.lastTeamAdded;
	}
	
	/**
	 * Sets the last team added of this world to the given team.
	 * @param 	lastTeam
	 * 			The new last team of this world.
	 * @post	The new last team of this world is equal to the given team.
	 * 			| new.getLastTeamAdded() == lastTeam
	 * @throws 	IllegalArgumentException
	 * 			The given team does not belong to this world.
	 * 			| (!canHaveAsTeam(lastTeam))
	 */
	public void setLastTeamAdded(Team lastTeam) throws IllegalArgumentException{
		if (!canHaveAsTeam(lastTeam))
			throw new IllegalArgumentException("The given team does not belong to this world.");
		this.lastTeamAdded = lastTeam;
	}
	
	/**
	 * Variable registering the last team that has been added to the world.
	 */
	private Team lastTeamAdded;
	
	
	

	/**
	 * Returns the list of food of this world.
	 */
	public ArrayList<Food> getFood(){
		return this.listOfFood;
	}
	
	/**
	 * Sets the list of food of this world to the given list.
	 * 
	 * @param 	foodList
	 * 			The new list of food of this world.
	 * @post	The list of food of this world is equal to the given list of food.
	 * 			| new.getFood() == foodList
	 * @throws	IllegalArgumentException
	 * 			The given list of food contains food that are not part of this world.
	 * 			| (!canHaveAsFoodList(foodList)
	 */
	public void setFood(ArrayList<Food> foodList) throws IllegalArgumentException{
		if (! canHaveAsFoodList(foodList))
			throw new IllegalArgumentException("The given foodlist contains food that are not part of this world.");
		this.listOfFood = foodList;
	}
	
	/**
	 * Checks whether all the food of the given list belong to this world.
	 * 
	 * @param 	foodList
	 * 			The list of food to check.
	 * @return	True if and only if all the food of the given list of food belong to this world.
	 * 			| for (Food food: foodList)
	 * 			|	if (!canHaveAsFood(food))
	 * 			|		result == false
	 * 			| result == true
	 */
	public boolean canHaveAsFoodList(ArrayList<Food> foodList){
		for (Food food: foodList)
			if (!canHaveAsFood(food))
				return false;
		return true;
	}
	
	/**
	 * Checks whether the given worm belongs to this world.
	 * 
	 * @param 	food
	 * 			The food to check.
	 * @return	True if and only if the world of the given food is equal to this world.
	 * 			| result == (food.getWorld() == this)
	 */
	public boolean canHaveAsFood(Food food){
		return (food.getWorld() == this);
	}
	
	/**
	 * Returns whether this world has the given food as one of its foods.
	 * 
	 * @return 	True if and only if the worm has this food as one of its foods.
	 *			| for (Food food: this.getFood())
	 *			| 	if (food == foodToCheck)
	 *			|		result == true
	 *			| result == false
	 */
	public boolean hasAsFood(Food foodToCheck){
		for (Food food: this.getFood())
			if (food == foodToCheck)
				return true;
		return false;
	}
	
	
	
	/**
	 * Adds a default food to this world.
	 * 
	 * @effect	Creates a new food with this as world and a random starting position.
	 * 			|  Food food = new Food(this,searchAdjacentStartingPos(0.20)[0],searchAdjacentStartingPos(0.20)[1])
	 * @effect	The new food is added to the list of foods.
	 * 			| hasAsFood(food)
	 */
	public void addNewFood(){
		double radius = 0.2;
		double[] startPos= this.searchAdjacentStartingPos(radius);
		Food food  = new Food(this,startPos[0],startPos[1]);
		this.listOfFood.add(food);
	}
	
	/**
	 * Removes the given food from this world.
	 * 
	 * @param 	food
	 * 			The food which has to be removed from this world.
	 * @effect	The food is removed from the list of food.
	 * 			| (!hasAsFood(food))
	 * @throws	IllegalArgumentException
	 * 			The given food is null.
	 * 			| (food == null)
	 */
	public void removeFood(Food food){
		if (food == null)
			throw new IllegalArgumentException("The given food shouldn't be null.");
		/* Als dit food geen deel is van de list of food van deze world gebeurt er niets. 
		 * Dit is zo dankzij de voorgedefineerde methode remove van JAVA. */
		this.listOfFood.remove(food);
	}
		
	/**
	 * List registering the list of food of this world.
	 */
	private ArrayList<Food> listOfFood;


	
	

	/**
	 * Return the active projectile of this world.
	 */
	@Basic
	public Projectile getProjectile(){
		return this.projectile;
	}
	
	/**
	 * Set the active projectile of this world to the given projectile.
	 */
	@Basic
	public void setProjectile(Projectile projectile){
		this.projectile = projectile;
	}
	
	/**
	 * Remove the active projectile of this world.
	 */
	@Basic
	public void removeProjectile(){
		this.setProjectile(null);
	}
	
	/**
	 * Variable registering the active projectile of this world.
	 */
	private Projectile projectile;
	
	
	
	
	
	
	
	
	
	

		

	/**
	 * Returns whether a circle with given centre and radius is passable in this world.
	 */
	public boolean isPassable(double centerX, double centerYOmgekeerd, double radius){
		
		try{
			double centerY = this.getHeight()-centerYOmgekeerd;
			double lowestY= centerY - radius;
			double highestY = centerY + radius; 
			double y = lowestY;
			double pixelHeight = this.getPixelHeight();
			if (this.getPassableMap()[(int)Math.floor(lowestY/pixelHeight)][(int)Math.floor(centerX/this.getPixelHeight())] == false)
				return false;
			if (this.getPassableMap()[(int)Math.floor(highestY/this.getPixelHeight()-0.001)][(int)Math.floor(centerX/this.getPixelHeight())] == false)
				return false;
			while (Util.fuzzyLessThanOrEqualTo(y, highestY)) {
				double lowestX = centerX - Math.sqrt(Math.pow(radius,2) - Math.pow( centerY - y , 2 ));
				double highestX = centerX + Math.sqrt(Math.pow(radius,2) - Math.pow( centerY - y , 2 ));
				if (Double.isNaN(lowestX))
					lowestX = centerX;
				if(Double.isNaN(highestX))
					highestX = centerX;
				if (this.getPassableMap()[(int)Math.floor(y/this.getPixelHeight())][(int)Math.floor(lowestX/this.getPixelHeight())] == false)
					return false;
				if (this.getPassableMap()[(int)Math.floor(y/this.getPixelHeight())][(int)Math.floor(highestX/this.getPixelHeight())] == false)
					return false;	
				y = y + radius / 10;
			}
			return true;
		}
		catch (ArrayIndexOutOfBoundsException exc){
			return true;
		}
		
	}
	
	/**
	 * Returns whether a circle with given centre and radius is impassable in this world.
	 */
	public boolean isImpassable(double centerX, double centerYOmgekeerd, double radius){
		return (! isPassable(centerX,centerYOmgekeerd,radius));
	}


	/**
	 * Returns whether a rectangular with given upper left position and lower right position is passable.
	 */
	public boolean isPassableRectangular(double upperLeftX, double upperLeftY, double lowerRightX, double lowerRightY){
		int lowestPixelX  = (int) Math.floor( upperLeftX  / getPixelWidth() );
		int highestPixelX = (int) Math.floor( lowerRightX / getPixelWidth() );
		int lowestPixelY  = (int) Math.floor( lowerRightY / getPixelHeight() );
		int highestPixelY = (int) Math.floor( upperLeftX  / getPixelHeight() );
		int y = lowestPixelY;
		while (y <= highestPixelY){
			int x = lowestPixelX;
			while (x <= highestPixelX){
				if (getPassableMap()[y][x] == false)
					return false;
				x++;
			}
			y++;
		}
		return true; 
	}
	
	/**
	 * Returns whether a circle with given center and radius is adjacent in this world.
	 */
	public boolean isAdjacent(Position position, double radius){
		return isAdjacent(position.getX(), position.getY(), radius);
	}
	
	/**
	 * Returns whether a circle with given center and radius is adjacent in this world.
	 */
	public boolean isAdjacent(double x, double y, double radius){
		// Opmerking: Je checkt bij is Passable al een gebied van grootte radius. Bij isImpassable controleeer je dit gebied helemaal nog eens opnieuw.
		if ((isPassable(x,y,radius)) && (!isPassable(x,y,1.1*radius))){
			return true;
		}
		else
			return false;
	}

	
	/**
	 * Searches the adjacent position that is closest to the given gameobject.
	 */
	public Position searchClosestAdjacentPosition(GameObject gameObject){
		double angle = 0;
		double distance = 0;
		boolean adjacentPositionFound = false;
		Position adjacentPosition = new Position(0,0);
		while(!adjacentPositionFound){
			while((angle < 2*Math.PI) && (!adjacentPositionFound)){
				adjacentPosition.setX(gameObject.getPosition().getX()+distance*Math.cos(angle));
				adjacentPosition.setY(gameObject.getPosition().getY()+distance*Math.sin(angle));
				if (this.isAdjacent(adjacentPosition, gameObject.getRadius()))
					adjacentPositionFound = true;
				angle = angle + 0.1*Math.PI;
			}
			angle = 0;
			distance = distance + 0.05*gameObject.getRadius();
		}
		return adjacentPosition;
	}


	
	
	
	
	

	

	/**
	 * Returns an random adjacent starting position for a circle with given radius.
	 */
	/* position returnen ipv double[] is beter! */
	public double[] searchAdjacentStartingPos(double radius){
		double randX = this.random.nextDouble()*this.getWidth();
		double randY = this.random.nextDouble()*this.getHeight();
		while (true) {
			if (this.isAdjacent(randX,randY,radius)) {
				double[] randomStartPos = new double[]{randX,randY};
				return randomStartPos;
			}			
			else{
				randX = randX + 0.1*radius;
				if (Util.fuzzyGreaterThanOrEqualTo(randX, this.getWidth()) ){
					randX = 1.01*radius;
					randY = randY + 0.1*radius;
					if (randY > this.getHeight())
						randY = 1.01*radius;
				}
			}
		}
	}
	
	/**
	 * Returns whether the game is finished or not.
	 */
	public boolean isGameFinished(){
		// Voor als je een game zou starten zonder dat je wormen toevoegt.
		if ( this.getWorms().size() == 0)
			return true;
		if ( (this.getTeams().size() == 1) && (this.getTeams().get(0).getName().equals("Independent")) ) {
			if (this.getWorms().size() == 1)
				return true;
			return false;
		}
		else if (this.getTeams().size() == 1)
			return true;
		return false;
	}
	
	/**
	 * Returns the winner of this game.
	 */
	public String getWinner(){
		if (!(this.isGameFinished()))
			return null;
		else {
			if ( this.getWorms().size() == 0)
				return "nobody, since no worms were added before this game was started.";
			if ( (this.getTeams().size() == 1) && (this.getTeams().get(0).getName().equals("Independent")) )
				return this.getWorms().get(0).getName();
			else
				return this.getTeams().get(0).getName();
		}
	}
	
	/**
	 * Starts the next turn of the game.
	 */
	public void startNextTurn(){
		if (this.getNbCurrentTeam() == this.getTeams().size() - 1){
			this.setNbCurrentTeam(0);
			this.getCurrentTeam().nextWorm();
		}
		else if (this.getNbCurrentTeam() != this.getTeams().size() ){
			this.setNbCurrentTeam(this.getNbCurrentTeam() + 1);
			this.getCurrentTeam().nextWorm();
		}
		this.getCurrentTeam().getCurrentWorm().setActionPoints(this.getCurrentTeam().getCurrentWorm().getMaximumActionPoints());
		this.getCurrentTeam().getCurrentWorm().setHitPoints   (this.getCurrentTeam().getCurrentWorm().getHitPoints() + 10);
	}
	
	/**
	 * Returns the active projectile of this game.
	 */
	@Basic
	public Projectile getActiveProjectile(){
		return this.projectile;
	}
	
	/**
	 * Starts the game.
	 */
	public void startGame(){
		//If statement voor als je geen wormen zou hebben toegevoegd voor als je je spel start.
		if (this.getWorms().size() != 0){
			removeEmptyTeamsFromGame();
			for (Worm worm: this.getWorms()){
				worm.setActionPoints(worm.getMaximumActionPoints());
				worm.setHitPoints(worm.getMaximumHitPoints());
			}
			this.setNbCurrentTeam(0);
		}
	}
	
	
	/**
	 * Remove all empty teams from the world.
	 */
	public void removeEmptyTeamsFromGame(){
		int i = 0;
		while (i <= this.getTeams().size() - 1)
			if (this.getTeams().get(i).getTeamMembers().size() == 0){
				this.getTeams().get(i).terminate();
			}
			else{
				i++;
			}
	}
	
	
	
	
	
	// IN ORDE
	/**
	 * Returns the amount of pixels in the width of this world.
	 */
	@Basic
	public int getAmountOfPixelsInWidth(){
		return this.passableMap[0].length;
	}
	
	/**
	 * Returns the amount of pixels in the height of this world.
	 */
	@Basic
	public int getAmountOfPixelsInHeight(){
		return this.passableMap.length;
	}
	
	/**
	 * Returns the width of one pixel of this world.
	 * 
	 * @return	The result is equal to the width of this world divided by the amount of pixels in the width.
	 *			| result == (this.getWidth() / (double) getAmountOfPixelsInWidth());
	 * @throws	IllegalStateException
	 * 			The world has no pixels in the width!
	 * 			| (getAmountOfPixelsInWidth() == 0)
	 */
	public double getPixelWidth() throws IllegalStateException {
		if (getAmountOfPixelsInWidth() == 0)
			throw new IllegalStateException("This world has no pixels!");
		return (this.getWidth() / (double) getAmountOfPixelsInWidth());
	}
	
	/**
	 * Returns the height of one pixel of this world.
	 * 
	 * @return 	The result is equal to the height of this world divided by the amount of pixels in the height.
	 * 			| result == (this.getHeight() / (double) getAmountOfPixelsInHeight())
	 * @throws	IllegalStateException
	 * 			The world has no pixels in the height!
	 * 			| (getAmountOfPixelsInHeight() == 0)
	 */
	public double getPixelHeight() throws IllegalStateException{
		if (getAmountOfPixelsInHeight() == 0)
			throw new IllegalStateException("This world has no pixels!");
		return (this.getHeight() / (double) getAmountOfPixelsInHeight());
	}
	

	//IN ORDE
	/**
	 * Checks whether a circular entity (with given center and radius) is inside of a given world.
	 * 
	 * @param 	positionX
	 * 			The x-coordinate of the center of the circular entity.
	 * @param 	positionY
	 * 			The y-coordinate of the center of the circular entity.
	 * @param 	radius
	 * 			The radius of the circular entity.
	 * @return 	True if and only if the given circular entity does not lie fully within the bounds of this world.
	 * 			| result == ( outOfWorldX(positionX,radius) || outOfWorldY(positionY,radius) )
	 */	
	public boolean outOfWorld(double positionX, double positionY, double radius){
		return (outOfWorldX(positionX,radius)
				|| outOfWorldY(positionY,radius));
	}
	
	/**
	 * Checks whether the given x-position of the circular entity is still inside this world.
	 * 
	 * @param 	positionX
	 * 			The x-coordinate of the center of the circular entity.
	 * @param 	radius
	 * 			The radius of the circular entity.
	 * @return	True if and only if the given x-position is outside of this world.
	 * 			| result == ( (positionX - this.getRadius() < 0 ) 
				|	|| (positionX + this.getRadius() > world.getWidth()) )
	 */
	public boolean outOfWorldX(double positionX, double radius){
		return ( (positionX - radius < 0 ) 
				|| (positionX + radius > this.getWidth()) );
	}
	
	/**
	 * Checks whether the given y-coordinate of this circular entity is still inside this world.
	 * 
	 * @param 	positionY
	 * 			The y-coordinate of the center of the circular entity.
	 * @param 	radius
	 * 			The radius of the circular entity.
	 * @return	True if and only if the given y-position is outside of this world.
	 * 			| result == ( (positionY - this.getRadius() < 0 ) 
	 *			|	|| (positionY + this.getRadius() > world.getHeight()) )
	 */			
	public boolean outOfWorldY(double positionY, double radius){
		return ( (positionY - radius < 0 )
				|| (positionY + radius > this.getHeight()) );
	}

}
