/**
 * 
 */
package worms.model;

import java.util.*;

//Methoden:
// DONE addEmptyTeam(World world, String newName)
// DONE addNewFood(World world)
// DONE addNewWorm(World world)

// DONE 	public World createWorld(double width, double height,
// DONE					boolean[][] passableMap, Random random);

// DONE Worm getCurrentWorm(World world)
// DONE Collection<Food> getFood(World world);
// DONE String getWinner(World world);
// DONE Collection<Worm> getWorms(World world);
// DONE Projectile getActiveProjectile(World world);
// DONE		boolean isAdjacent(World world, double x, double y, double radius);
// DONE boolean isGameFinished(World world);
// DONE		boolean isImpassable(World world, double x, double y, double radius);
// void startGame(World world);
// DONE void startNextTurn(World world);




/**
 * @author 	Nicolas Hoppenbrouwers:	Bachelor Ingenieurswetenschappen Computerwetenschappen-Werktuigkunde
 * 			Bram Lust: Bachelor Ingenieurswetenschappen Computerwetenschappen-Elektrotechniek
 * 			We didn't work with Git.
 * @version 1.0
 */
public class World {
	public World (double width, double height, boolean[][] passableMap, Random random){
		this.setWidth(width);
		this.setHeight(height);
		this.setPassableMap(passableMap);
		this.setRandom(random);
		this.listOfWorms = new ArrayList<Worm>();
		this.listOfTeams = new ArrayList<Team>();
		this.addEmptyTeam("Independent");
		this.listOfFood = new ArrayList<Food>();
		this.nbCurrentWorm = 0;
		this.projectile = null;
	}
	

	
	
	
	
	//WIDTH EN HEIGHT
	public double getWidth(){
		return this.width;
	}
	
	public void setWidth(double width) throws IllegalArgumentException{
		if (!isValidWidth(width))
			throw new IllegalArgumentException("Impossible width!"); 
		this.width = width;
	}
	
	public boolean isValidWidth(double width){
		return  !(Double.isNaN(width)) && (width > 0) && (width <= Double.MAX_VALUE);
	}
	
	private double width;
	
	public double getHeight(){
		return this.height;
	}
	
	public void setHeight(double height){
		if (!isValidHeight(height))
			throw new IllegalArgumentException("Impossible height!"); 
		this.height = height;
	}
	
	// Wij hebben ervoor gekozen om niet een gemeenschappelijke functie isValidBoundary te maken omdat
	// het volgens ons mogelijk is dat de upperbound van height niet gelijk is aan upperbound van width (want breedbeeld enz)
	public boolean isValidHeight(double height){
		return !(Double.isNaN(height)) && (height > 0) && (height <= Double.MAX_VALUE);
	}
	
	private double height;
	
	
	
	
	
	
	
	
	
	//PASSABLEMAP
	public boolean[][] getPassableMap(){
		return this.passableMap;
	}
	
	public void setPassableMap(boolean[][] passableMap){
		this.passableMap = passableMap;
	}
	
	//dummy
	public boolean isValidPassableMap(){
		return true;
	}
	
	private boolean[][] passableMap;
	
	
		
	
	
	
	
	
	
	
	//RANDOM
	//Geen getters en checkers voor Random?
	public void setRandom(Random random){
		this.random = random;
	}
	
	private Random random;
	
	
	
	
	
	
	
	//WORM
	public ArrayList<Worm> getWorms(){
		return this.listOfWorms;
	}
	
	public void setWorms(ArrayList<Worm> wormList){
		this.listOfWorms = wormList;
	}
	
	public void addNewWorm(){
		//?????.....................
		double radius = 0.40;
		double[] startPos= this.searchAdjacentStartingPos(radius);
		//Nog exception throwen als hij geen positie kan vinden.
		//Hier nog functie schrijven dat hij een echte naam pakt ipv Worm.
		Worm worm = new worms.model.Worm(this,startPos[0],startPos[1],0.0,radius,Worm.getRandomName());
		this.listOfWorms.add(worm);
	}
	
	public void removeWorm(Worm worm){
		this.listOfWorms.remove(worm);
		// Je komt in een loop.
		//worm.terminate();
	}
	
	public int getNbCurrentWorm(){
		return this.nbCurrentWorm;
	}
	
	public void setNbCurrentWorm(int numberWorm){
		this.nbCurrentWorm = numberWorm;
	}
	
	public Worm getCurrentWorm(){
		return this.listOfWorms.get(this.getNbCurrentWorm());
	}
	

	
	private ArrayList<Worm> listOfWorms;
	private int nbCurrentWorm;

	

	
	
	
	
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
		if (!isValidAmountOfTeams()){
			throw new IllegalStateException("This world already has 10 teams.");
		}
		Team team  = new Team(this, newName);
		this.listOfTeams.add(team);
		setLastTeamAdded(team);
	}
	
	public boolean isValidAmountOfTeams(){
		return (this.getTeams().size() <= 11);
	}
	
	public void removeTeam(Team team){
		this.listOfTeams.remove(team);
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
	
	
	
	
	
	
	
	
	
	

		
	public boolean isPassable(double centerX, double centerYOmgekeerd, double radius) {
		//IndexOutofBound error nog catchen!
		//OPMERKING Assistent feedback (later)
		// Misschien ervoor zorgen dat je eenvoudigere code krijgt als je niet deelt door pixelheight enz.
		double centerY = this.getPassableMap().length*this.getPixelHeight()-centerYOmgekeerd;
		if ((outOfWorldX(centerX, radius,this)) || (outOfWorldY(centerY, radius,this)))
			return true;
		double pixelHeight = this.getPixelHeight();
		double pixelWidth = this.getPixelWidth();
		int lowestY  = (int) Math.floor( (centerY - radius) / pixelHeight) +2 ;
		int highestY = (int) Math.ceil(  (centerY + radius) / pixelHeight) -2; 
		int y = lowestY;
		while (y <= highestY) {
			int lowestX =  (int) Math.floor( (centerX - Math.sqrt(Math.pow(radius,2) - Math.pow( centerY - y*pixelHeight , 2 ))) / pixelWidth );
			int highestX = (int) Math.ceil(  (centerX + Math.sqrt(Math.pow(radius,2) - Math.pow( centerY - y*pixelHeight , 2 ))) / pixelWidth );
			for (int x = lowestX; x <= highestX; x = x+1){
				if (this.getPassableMap()[y][x] == false)
					return false;
			}				
			y = y + 1;
		}
		return true;
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
	}
		
	public boolean isImpassable(double x, double y, double radius){
		return (! isPassable(x,y,radius) );
	}
	
	public boolean isPassableForShoot(double centerX, double centerYOmgekeerd, double radius){
		
		double centerY = this.getHeight()-centerYOmgekeerd;
		if ((outOfWorldX(centerX, radius,this)) || (outOfWorldY(centerY, radius,this)))
			return true;
		double lowestY= centerY - radius;
		double highestY = centerY + radius; 
		double y = lowestY;
		if (this.getPassableMap()[(int)Math.round(lowestY/this.getPixelHeight())][(int)Math.round(centerX/this.getPixelHeight())] == false)
			return false;
		if (this.getPassableMap()[(int)Math.round(highestY/this.getPixelHeight())][(int)Math.round(centerX/this.getPixelHeight())] == false)
			return false;
		while (y <= highestY) {
			double lowestX = centerX - Math.sqrt(Math.pow(radius,2) - Math.pow( centerY - y , 2 ));
			double highestX = centerX + Math.sqrt(Math.pow(radius,2) - Math.pow( centerY - y , 2 ));
			if (Double.isNaN(lowestX))
				lowestX = centerX;
			if(Double.isNaN(highestX))
				highestX = centerX;
			if (this.getPassableMap()[(int)Math.round(y/this.getPixelHeight())][(int)Math.round(lowestX/this.getPixelHeight())] == false)
				return false;
			if (this.getPassableMap()[(int)Math.round(y/this.getPixelHeight())][(int)Math.round(highestX/this.getPixelHeight())] == false)
				return false;	
			y = y + radius /10;
		}
		return true;
	}
	
	public boolean isImpassableForShoot(double centerX, double centerYOmgekeerd, double radius){
		return (! isPassableForShoot(centerX,centerYOmgekeerd,radius));
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



	

	
	// 	boolean isAdjacent(World world, double x, double y, double radius);
	public boolean isAdjacent(double x, double y, double radius){
		// Opmerking: Je chekt bij is Passable al een gebied van grootte radius. Bij isImpassable controleeer je dit gebied helemaal nog eens opnieuw.
		if ((isPassable(x,y,radius)) && (!isPassable(x,y,1.1*radius))){
			return true;
		}
		else
			return false;
	}
	
	public boolean isAdjacentForShoot(double x, double y, double radius){
		// Opmerking: Je chekt bij is Passable al een gebied van grootte radius. Bij isImpassable controleeer je dit gebied helemaal nog eens opnieuw.
		if ((isPassableForShoot(x,y,radius)) && (!isPassableForShoot(x,y,1.1*radius))){
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
/*			System.out.println(this.getWidth());
			System.out.println(this.getHeight());
			System.out.println(randX);
			System.out.println(randY);
			System.out.println(isAdjacent(randX,randY,radius));*/
			if (this.isAdjacent(randX,randY,radius)) {
				double[] randomStartPos = new double[]{randX,randY};
				return randomStartPos;
			}			
			else{
				randX = randX + 15 * this.getPixelWidth();
				if (randX >= this.getWidth() ){
					randX = 0.01 * this.getWidth();
					randY = randY + 5 * this.getPixelHeight();
					if (randY > this.getHeight())
						randY = 0.01 * this.getHeight();
				}
			}
		}
	}
	
	public boolean isGameFinished(){
		// Voor als je een game zou starten zonder dat je wormen toevoegt. Nu is dit wel totaal ipv defensief.
		if ( this.getWorms().size() == 0){
			return true;
		}
		if ( (this.listOfTeams.size() == 1) && (this.getTeams().get(0).getName() == "Independent") ) {
			if (this.listOfWorms.size() == 1){
				return true;
			}
			return false;
		}
		else if (this.listOfTeams.size() == 1)
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
			if ( (this.listOfTeams.size() == 1) && (this.getTeams().get(0).getName() == "Independent") ){
				return this.getWorms().get(0).getName();
			}
			else{
				return this.getTeams().get(0).getName();
			}
		}
	}
	
	public void startNextTurn(){
		if (this.getNbCurrentWorm() == this.getWorms().size() - 1)
			this.setNbCurrentWorm(0);
		else if (this.getNbCurrentWorm() != this.getWorms().size() )
			this.setNbCurrentWorm(this.getNbCurrentWorm() + 1);
		this.getCurrentWorm().setActionPoints(this.getCurrentWorm().getMaximumActionPoints());
		this.getCurrentWorm().setHitPoints   (this.getCurrentWorm().getHitPoints() + 10);
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
			this.setNbCurrentWorm(0);
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
	
	
	
	
	
	
	
	public int getAmountOfPixelsInWidth(){
		return this.passableMap[0].length;
	}
	
	public int getAmountOfPixelsInHeight(){
		return this.passableMap.length;
	}
	
	public double getPixelWidth(){
		return (this.getWidth() / (double)getAmountOfPixelsInWidth());
	}
	
	public double getPixelHeight(){
		return (this.getHeight() / (double)getAmountOfPixelsInHeight());
	}
	
	public boolean outOfWorldX(double positionX, double radius, World world){
		return ( (positionX - radius < 0 ) 
				|| (positionX + radius > (world.getWidth()) - this.getPixelWidth()) );
	}
	
	public boolean outOfWorldY(double positionY, double radius, World world){
		return ( (positionY - radius < 0 )
				|| (positionY + radius > (world.getHeight()) - this.getPixelHeight()) );
	}

}