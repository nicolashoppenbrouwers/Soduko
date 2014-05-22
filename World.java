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
	 * @param 	program
	 * 			The program of the worm that is added to this world.
	 * @effect	
	 */
	public void addNewWorm(Program program){
		double radius = 0.40;
		double[] startPos = this.searchAdjacentStartingPos(radius);
		Worm worm = new worms.model.Worm(this,startPos[0],startPos[1],0.0,radius,Worm.getRandomName(),program);
		this.getWorms().add(worm);
	}
	
	private ArrayList<Worm> listOfWorms;
	
	//Dynamische binding shit controleren -> NICOLAS
	public void removeWorm(Worm worm){
		this.getWorms().remove(worm);
		// Je komt in een loop.
		//worm.terminate();
	}
	
	public int getNbCurrentTeam(){
		return this.nbCurrentTeam;
	}
	
	public void setNbCurrentTeam(int numberTeam){
		this.nbCurrentTeam = numberTeam;
	}
	
	public Team getCurrentTeam(){
		if (this.getTeams().size() == 0)
			return null;
		return this.getTeams().get(this.getNbCurrentTeam());
	}
	
	private int nbCurrentTeam;

	
	public Worm getCurrentWorm(){
		return this.getCurrentTeam().getCurrentWorm();
	}
	
	
	
	
	//TEAM
	public ArrayList<Team> getTeams(){
		return this.listOfTeams;
	}
	
	public void setTeams(ArrayList<Team> teams){
		if (teams.size() > 10)
			throw new IllegalArgumentException("Too many teams.");		
		this.listOfTeams = teams;
	}
	
	public void addEmptyTeam(String newName){
		if (amountOfTeamsExceeded()){
			throw new IllegalStateException("This world already has 10 teams.");
		}
		Team team  = new Team(this, newName);
		this.getTeams().add(team);
		setLastTeamAdded(team);
		setNbCurrentTeam(getNbCurrentTeam() + 1);
	}
	
	public boolean canHaveAsTeam(Team team){
		return (team.getWorld() == this);
	}
	public boolean amountOfTeamsExceeded(){
		/* Lege teams worden hier ook nog meegerekend. Dit moet eruit.*/
		return (this.getTeams().size() > 10);
	}
	
	public void removeTeam(Team team){
		this.getTeams().remove(team);
		// Je komt in een loop.
		//team.terminate();
	}
	
	private ArrayList<Team> listOfTeams;

	
	
	
	
	public Team getLastTeamAdded(){
		return this.lastTeamAdded;
	}
	
	public void setLastTeamAdded(Team lastTeam){
		this.lastTeamAdded = lastTeam;
	}
	
	private Team lastTeamAdded;
	
	
	

	//FOOD
	public ArrayList<Food> getFood(){
		return this.listOfFood;
	}
	
	public void setFood(ArrayList<Food> food){
		this.listOfFood = food;
	}
	
	public void addNewFood(){
		double radius = 0.2;
		double[] startPos= this.searchAdjacentStartingPos(radius);
		Food food  = new Food(this,startPos[0],startPos[1]);
		this.listOfFood.add(food);
	}
	
	public void removeFood(Food food){
		this.listOfFood.remove(food);
		//food.terminate();
	}
		
	private ArrayList<Food> listOfFood;


	
	
	//PROJECTILE.
	/**
	 *@post		This projectile don't longer belongs to any World.
	 *			| new.getWorld() == null
	 *@post		The World that contained this Projectile, no longer contains any projectile
	 *			| new.getWorld().projectile == null;
	 *
	 */
	public Projectile getProjectile(){
		return this.projectile;
	}
	
	public void setProjectile(Projectile projectile){
		this.projectile = projectile;
	}
	
	public void removeProjectile(){
		this.projectile = null;
	}
	
	private Projectile projectile;
	
	
	
	
	
	
	
	
	
	

		
//	public boolean isPassable(double centerX, double centerYOmgekeerd, double radius) {
//		//IndexOutofBound error nog catchen!
//		//OPMERKING Assistent feedback (later)
//		// Misschien ervoor zorgen dat je eenvoudigere code krijgt als je niet deelt door pixelheight enz.
//		double centerY = this.getPassableMap().length*this.getPixelHeight()-centerYOmgekeerd;
//		if (outOfWorld(centerX, centerY, radius))
//			return true;
//		double pixelHeight = this.getPixelHeight();
//		double pixelWidth = this.getPixelWidth();
//		int lowestY  = (int) Math.floor( (centerY - radius) / pixelHeight) +2 ;
//		int highestY = (int) Math.ceil(  (centerY + radius) / pixelHeight) -2; 
//		int y = lowestY;
//		while (y <= highestY) {
//			int lowestX =  (int) Math.floor( (centerX - Math.sqrt(Math.pow(radius,2) - Math.pow( centerY - y*pixelHeight , 2 ))) / pixelWidth );
//			int highestX = (int) Math.ceil(  (centerX + Math.sqrt(Math.pow(radius,2) - Math.pow( centerY - y*pixelHeight , 2 ))) / pixelWidth );
//			for (int x = lowestX; x <= highestX; x = x+1){
//				if (this.getPassableMap()[y][x] == false)
//					return false;
//			}	
//			// +1
//			y = y+1;
//		}
//		return true;
//		//IndexOutofBound error nog catchen!
//		//OPMERKING Assistent feedback (later)
//		// Misschien ervoor zorgen dat je eenvoudigere code krijgt als je niet deelt door pixelheight enz.
//		double centerY = this.getPassableMap().length*this.getPixelHeight()-centerYOmgekeerd;
//		if ((outOfWorldX(centerX, radius,this)) || (outOfWorldY(centerY, radius,this)))
//			return true;
//		double pixelHeight = this.getPixelHeight();
//		double pixelWidth = this.getPixelWidth();
//		int lowestY  = (int) Math.floor( (centerY - radius) / pixelHeight);
//		int highestY = (int) Math.ceil(  (centerY + radius) / pixelHeight); 
//		int y = lowestY;
//		int lowestXInt = 0;
//		int highestXInt = 0;
//		while (y <= highestY) {
//		double lowestX = (centerX - Math.sqrt(Math.pow(radius,2) - Math.pow( centerY - y*pixelHeight , 2 ))) / pixelWidth ;
//			double highestX = (centerX + Math.sqrt(Math.pow(radius,2) - Math.pow( centerY - y*pixelHeight , 2 ))) / pixelWidth ;
//			if (Double.isNaN(lowestX))
//			lowestXInt = (int)centerX;
//			else
//				lowestX = (int)Math.floor(lowestX);
//			if(Double.isNaN(highestX))
//			highestXInt = (int)centerX;
//			else
//				if(Double.isNaN(lowestX))
//					highestX = (int)lowestX;
//			for (int x = lowestXInt; x <= highestXInt; x = x+1){
//				if (this.getPassableMap()[y][x] == false)
//					return false;
//			}				
//			y = y + 1;
//		}
//		return true;
//	}
		
//	public boolean isImpassable(double x, double y, double radius){
//		return (! isPassable(x,y,radius) );
//	}
	
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
			// 10
			y = y + radius / 10;
		}
		return true;
		}
		catch (ArrayIndexOutOfBoundsException exc){
			return true;
		}
		
	}
	
	public boolean isImpassable(double centerX, double centerYOmgekeerd, double radius){
		return (! isPassable(centerX,centerYOmgekeerd,radius));
	}


	/*
		double centerYPixel = (this.getPassableMap().length*this.getPixelHeight()-centerYOmgekeerd)*this.getPixelHeight();
		double centerXPixel = (centerX*this.getPixelWidth());
		double radiusPixel = radius*this.getPixelHeight();
		
		double lowestY = centerYPixel - radiusPixel;
		double HighestY = centerYPixel + radiusPixel;
		
		int yInt = (int)lowestY;
		double yDouble = lowestY;
		
		while (yDouble <= HighestY){
			int lowestX =  (int) Math.round( (centerXPixel - Math.sqrt(Math.pow(radiusPixel,2) - Math.pow( centerYPixel - yDouble , 2 ))));
			int HighestX =  (int) Math.round( (centerXPixel - Math.sqrt(Math.pow(radiusPixel,2) + Math.pow( centerYPixel - yDouble , 2 ))));
			if((this.getPassableMap()[yInt][lowestX]||this.getPassableMap()[yInt][HighestX])){
				return true;
			}
			yInt = yInt + 1;
			yDouble = yDouble+1;
		}
		return true;
		}*/
		
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

	public boolean isAdjacent(Position position, double radius){
		return isAdjacent(position.getX(), position.getY(), radius);
	}

	
	// 	boolean isAdjacent(World world, double x, double y, double radius);
//	public boolean isAdjacent(double x, double y, double radius){
//		// Opmerking: Je chekt bij is Passable al een gebied van grootte radius. Bij isImpassable controleeer je dit gebied helemaal nog eens opnieuw.
//		if ((isPassable(x,y,radius)) && (!isPassable(x,y,1.1*radius))){
//			return true;
//		}
//		else
//			return false;
//	}
	
	public boolean isAdjacent(double x, double y, double radius){
		// Opmerking: Je chekt bij is Passable al een gebied van grootte radius. Bij isImpassable controleeer je dit gebied helemaal nog eens opnieuw.
		if ((isPassable(x,y,radius)) && (!isPassable(x,y,1.1*radius))){
			return true;
		}
		else
			return false;
	}
	
	//Naamgeving
	public boolean isPassableHalfCircleEdge(double centerX, double centerY, double radius) throws IndexOutOfBoundsException{
		//IndexOutofBound error nog catchen!

		//OPMERKING Assistent feedback (later)
		// Misschien ervoor zorgen dat je eenvoudigere code krijgt als je niet deelt door pixelheight enz.
		// Calculate position van POSITION???
		int lowestY = (int) Math.floor( (centerY - radius) / this.getPixelHeight() );
		int highestY = (int) Math.floor( (centerY) / this.getPixelHeight() );
		int y = highestY;
		while ((y >= lowestY) && (y <= highestY)) {
			int lowestX = (int) Math.floor( (centerX - Math.sqrt(Math.pow(radius,2) - Math.pow( centerY - y*this.getPixelHeight() , 2 ))) / this.getPixelWidth() );
			int highestX = (int) Math.ceil( (centerX + Math.sqrt(Math.pow(radius,2) - Math.pow( centerY - y*this.getPixelHeight() , 2 ))) / this.getPixelWidth() );
			if (this.getPassableMap()[y][lowestX] == false) 
				return false;
			if (this.getPassableMap()[y][highestX] == false)
				return false;	
			y = y - 1;
		}
		return true;
	}

	
	
	
	

	

	
	public double[] searchAdjacentStartingPos(double radius){
		// JE KAN NIET MEER UIT DE WERELD GERAKEN. HET ONDERSTE WORDT OOK ALS ADJACENT BESCHOUWD.
		double randX = this.random.nextDouble()*this.getWidth();
		double randY = this.random.nextDouble()*this.getHeight();
		while (true) {
			if (this.isAdjacent(randX,randY,radius)) {
				double[] randomStartPos = new double[]{randX,randY};
				return randomStartPos;
			}			
			else{
				// + 15
				//randX = randX + 15 * this.getPixelWidth();
				randX = randX + 0.1*radius;
				if (Util.fuzzyGreaterThanOrEqualTo(randX, this.getWidth()) ){
					// 0.01
//					randX = 0.01 * this.getWidth();
					randX = 1.01*radius;
					// 5
					//randY = randY + 5 * this.getPixelHeight();
					randY = randY + 0.1*radius;
					if (randY > this.getHeight())
						//0.01
						//randY = 0.01 * this.getHeight();
						randY = 1.01*radius;
				}
			}
		}
	}
	
	public boolean isGameFinished(){
		// Voor als je een game zou starten zonder dat je wormen toevoegt. Nu is dit wel totaal ipv defensief.
		if ( this.getWorms().size() == 0){
			return true;
		}
		if ( (this.getTeams().size() == 1) && (this.getTeams().get(0).getName().equals("Independent")) ) {
			if (this.getWorms().size() == 1){
				return true;
			}
			return false;
		}
		else if (this.getTeams().size() == 1)
			return true;
		return false;
	}
	
	public String getWinner(){
		if (!(this.isGameFinished())){
			return null;
		}
		else {
			if ( this.getWorms().size() == 0)
				return "nobody, since no worms were added before this game was started.";
			if ( (this.getTeams().size() == 1) && (this.getTeams().get(0).getName().equals("Independent")) ){
				return this.getWorms().get(0).getName();
			}
			else{
				return this.getTeams().get(0).getName();
			}
		}
	}
	
	public void startNextTurn(){
		// Next team ook beter een iterator..
		if (this.getNbCurrentTeam() == this.getTeams().size() - 1){
			this.setNbCurrentTeam(0);
			//Next worm moet een iterator worden...
			this.getCurrentTeam().nextWorm();
		}
		else if (this.getNbCurrentTeam() != this.getTeams().size() ){
			this.setNbCurrentTeam(this.getNbCurrentTeam() + 1);
			this.getCurrentTeam().nextWorm();
		}
		this.getCurrentTeam().getCurrentWorm().setActionPoints(this.getCurrentTeam().getCurrentWorm().getMaximumActionPoints());
		this.getCurrentTeam().getCurrentWorm().setHitPoints   (this.getCurrentTeam().getCurrentWorm().getHitPoints() + 10);
	}
	
	public Projectile getActiveProjectile(){
		return this.projectile;
	}
	
	public void startGame(){
		//If statement voor als je geen wormen zou hebben toegevoegd voor als je je spel start. Wel totaal nu ipv defensief
		if (this.getWorms().size() != 0){
			removeEmptyTeamsFromGame();
			for (Worm worm: this.getWorms()){
				worm.setActionPoints(worm.getMaximumActionPoints());
				worm.setHitPoints(worm.getMaximumHitPoints());
			}
			this.setNbCurrentTeam(0);
		}
		//Nakijken of Independent niet leeg is, anders terminaten.
	}
	
	

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
