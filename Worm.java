package worms.model;

import be.kuleuven.cs.som.annotate.*;
import worms.model.program.Program;
import worms.util.Util;

import java.util.HashSet;
import java.util.Set;
// GEEN PROTECTED!!!!! overal nagaan!!!

// Waarom private waarom public? 

// boolean canFall(Worm worm)
// boolean canMove(Worm worm);
// boolean canTurn(Worm worm, double angle);
// DONE 		fall(Worm worm)	 ---> gewoon de kolom onder uw huidige position checken tot je FALSE tegenkomt en dan stop je en anders stop je niet en ben je dood
// 							 	 ---> altijd de functie fall laten uitvoeren voor als je met je kop tegen het plafond zou hangen.
// fallStep???
// 			DONE 	int getHitPoints(Worm worm);
// 			DONE	int getMaxHitPoints(Worm worm);
// String getSelectedWeapon(Worm worm);
// String getTeamName(Worm worm);
// boolean isAlive(Worm worm);
// void selectNextWeapon(Worm worm);
// void shoot(Worm worm, int yield);

//Bij terminated: overal exceptions toevoegen zoals bij Dog. IllegalStateExceptions.

//KLASSEINVARIANT DYNAMISCHE BINDING!!!
/**
 * A class of worms for playing the game Worms, involving a position, a direction, a radius, an amount of
 * 			action points, an amount of maximum action points and a name.
 * 
 * @invar	The position of each worm must be a valid position for a worm.
 * 			| isValidPosition(this.getPositionX())
 * 			| && isValidPosition(this.getPositionY())
 * @invar	The direction of each worm must be a valid direction for a worm.
 * 			| isValidDirection(this.getDirection())
 * @invar	The radius of each worm must be a valid radius for a worm.
 * 			| isValidRadius(this.getRadius())
 * @invar	The maximum amount of action points of each worm must be 
 * 			a valid amount of maximum action points for a worm.
 * 			| this.getMaximumActionPoints() = (int)Math.round(this.getMass())
 * @invar	The amount of action points of each worm must be 
 * 			a valid amount of action points for a worm.
 * 			| 0 <= this.getActionPoints() && this.getActionPoints() <= this.getMaximumActionPoints()
 * @invar	The name of each worm must be a valid name for a worm.
 * 			| isValidName(this.getName())
 * 
 * @author 	Nicolas Hoppenbrouwers
 * 			Bram Lust
 * @version 3.0
 *
 */
public class Worm extends MovableGameObject{
	
	/**
	 * Initialize this new worm with given position, direction, radius and name.
	 * 
	 * @param 	positionX
	 * 			The x-coordinate of the position of the new worm (in meters).
	 * @param 	positionY
	 * 			The y-coordinate of the position of the new worm (in meters).
	 * @param 	direction
	 * 			The direction which the new worm is facing (in radians).
	 * @param 	radius
	 * 			The radius of the new worm (in meters).
	 * @param 	name
	 * 			The name of the new worm.
	 * @effect	The x-coordinate of this new worm is equal to the given X-position.
	 * 			| new.getPositionX() == positionX
	 * @effect	The y-coordinate of this new worm is equal to the given Y-position.
	 * 			| new.getPositionY() == positionY
	 * @effect	The direction of this new worm is equal to the given direction.
	 * 			| new.getDirection() == direction
	 * @effect	The radius of this new worm is equal to the given radius.
	 * 			| new.getRadius() == radius
	 * @effect	The maximum amount of action points of this new worm are initialized to the worm's mass.
	 * 			| new.getMaximumActionPoints() == (int)Math.round(getMass())
	 * @effect	The current number of action points of this new worm are initialized to 
	 * 			the maximum amount of action points.
	 * 			| new.getActionPoints == this.getMaximumActionPoints()
	 * @effect	The maximum amount of hit points of this new worm are initialized to the worm's mass.
	 * 			| new.getMaximumHitPoints() == (int)Math.round(getMass())
	 * @effect	The current number of hit points of this new worm are initialized to 
	 * 			the maximum amount of hit points.
	 * 			| new.getHitPoints == this.getMaximumHitPoints()
	 * @effect	The name of this new worm is equal to the given name.
	 * 			| new.getName() == name
	 */
	// Use the @effect tag
	@Raw 
	public Worm(World world,double positionX, double positionY, double direction, double radius, String name, Program program){
		super(world, positionX, positionY, direction, radius);
		this.setMaximumActionPoints();
		this.setActionPoints(this.getMaximumActionPoints());
		this.setMaximumHitPoints();
		this.setHitPoints(this.getMaximumHitPoints());
		this.setName(name);
		this.setTeam(world.getLastTeamAdded());
		this.setIndexActiveWeapon(0);
		this.program = program;
}
	
	
	
	
	
	/**
	 * Check whether this worm is terminated.
	 * @result 	True if and only if the worm's hitpoints are zero or the worm has no world.
	 * 			| result == ((this.getHitPoints() == 0) || (!this.hasWorld()) )
	 */
	@Override
	public boolean isTerminated(){
		return ((this.getHitPoints() == 0) || (!this.hasWorld()) );
	}
	
	
	/**
	 * Terminate this worm.
	 * @post	This worm's hitpoints are set to zero.
	 *          | new.getHitPoints == 0
	 * @effect	The world, if any, is unset from this worm.
	 * 			| this.unsetWorld()     
	 * @effect	The team, if any, is unset from this worm.
	 * 			| this.unsetTeam()
	 */
	 @Override
	public void terminate() {
		 // Als het de current worm is die moet worden geterminatet:
		 if (this.getWorld().getCurrentTeam().getCurrentWorm() == this){
			 this.getWorld().getCurrentTeam().setNbCurrentWorm(this.getWorld().getCurrentTeam().getNbCurrentWorm() - 1);
			 World formerWorld = this.getWorld();
			 this.unsetTeam();
			 this.unsetWorld();
			 formerWorld.startNextTurn();
		 }
		 // Als het niet de current worm is die moet worden geterminatet:
		 else{
			 if (this.getTeam().getTeamMembers().indexOf(this) < this.getTeam().getNbCurrentWorm())
				 this.getTeam().setNbCurrentWorm(this.getTeam().getNbCurrentWorm() - 1);
			 this.unsetWorld();
			 this.unsetTeam();
		 }
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 //NOG ONAFGEWERKTE DOCUMENTATIE.
	 /**
	  * Unset the world, if any, from this GameObject.
	  * @post	This worm no longer has a world.
	  * 		| ! new.hasWorld()
	  * @post	
	  */
	 @Override
	 public void unsetWorld(){
			if (this.hasWorld()){
				World formerWorld = this.getWorld();
				setWorld(null);
				formerWorld.removeWorm(this);
			}
		}
	

	
	
	
	
	
	
	
	
	
	
	

	

	//DEZE FUNCTIE IS VOLGENS MIJ NIET MEER BASIC.
	/**
	 * Sets the radius of this worm to the given value.
	 */
	@Basic @Override
	public void setRadius(double radius) throws IllegalArgumentException{
		// OUDE CODE:		
		//		if (!isValidRadius(radius))
		//			throw new IllegalArgumentException("Non effective radius!"); 
		//		this.radius = radius;
		super.setRadius(radius);
		this.setMaximumActionPoints();
		this.setMaximumHitPoints();
	}
	
	/**
	 * Returns the minimal radius of this worm.
	 *
	 * @return	Return the minimal radius of the worm.
	 * 			| result == 0.25
	 */
	@Immutable @Override
	public double getMinimalRadius(){
		return 0.25;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * Returns the mass of this worm.
	 * 
	 * @return	The mass of this worm, calculated with following formula:
	 * 			mass = density * 4/3 * Pi * radius^3
	 * 			| result = density * 4.0/3.0 * Math.PI * Math.pow(this.getRadius(),3)
	 */
	@Override
	public double getMass(){
		double mass = getDensity() * 4.0/3.0 * Math.PI * Math.pow(this.getRadius(),3);
		return mass;
	}
	
	@Override
	public double getDensity(){
		return density;
	}
	
	/**
	 * Variable registering the density of any worm (final value).
	 */
	public final double density = 1062.0; 
	
	
	
	
	
	
	
	
	
	
	/**
	 * Returns the maximum number of action points of this worm.
	 */
	@Basic
	public int getMaximumActionPoints(){
		return this.maximumActionPoints;
	}
	
	/**
	 * Sets the maximum number of action points of this worm to the mass of this worm, rounded to the nearest integer.
	 */
	//NIET MEER BASIC. DOCUMENTATIE.
	//@Basic
	public void setMaximumActionPoints(){
		this.maximumActionPoints = (int)Math.round(getMass());
		//this.setActionPoints(this.getActionPoints());
	}
	
	/**
	 * Variable registering the maximum number of action points of this worm.
	 */
	private int maximumActionPoints;
	
	
	
	/**
	 * Returns the current number of action points of this worm.
	 */
	@Basic
	public int getActionPoints(){
		return this.actionPoints;
	}
	
	/**
	 * Sets the current number of action points of this worm to the given value.
	 */
	@Basic
	//NIET BASIC VOLGENS MIJ.
	public void setActionPoints(int actionPoints){
		if (actionPoints > this.getMaximumActionPoints()){
			this.actionPoints = this.getMaximumActionPoints();
		}
		else if (actionPoints <= 0){
			this.actionPoints = 0;
			this.getWorld().startNextTurn();
		}
		else{
			this.actionPoints = actionPoints;
		}
	}
	
	/**
	 * Variable registering the number of action points of this worm.
	 */
	private int actionPoints;
	
	
	
	
	
	
	
	
	/**
	 * Returns the current number of hit points of this worm.
	 */
	@Basic
	public int getHitPoints(){
		return this.hitPoints;
	}
	
	/**
	 * Sets the current number of hit points of this worm to the given value.
	 */
	@Basic
	//NIET BASIC VOLGENS MIJ.
	public void setHitPoints(int hitPoints){
		if (hitPoints > this.getMaximumHitPoints()){
			this.hitPoints = this.getMaximumHitPoints();
		}
		else if (hitPoints <= 0){
			this.terminate();
		}
		else{
			this.hitPoints = hitPoints;
		}
	}

	/**
	 * Variable registering the hit points of this worm. 
	 */
	private int hitPoints;
	
	/**
	 * Returns the maximum number of hit points of this worm.
	 */
	@Basic
	public int getMaximumHitPoints(){
		return this.maximumHitPoints;
	}
	
	/**
	 * Sets the maximum number of hit points of this worm to the mass of this worm, rounded to the nearest integer.
	 */
	@Basic
	public void setMaximumHitPoints(){
		this.maximumHitPoints = (int)Math.round(getMass());
		//this.setHitPoints(this.getHitPoints());
	}
	
	/**
	 * Variable registering the maximum number of hit points of this worm.
	 */
	private int maximumHitPoints;
		
		
		
		
		
	
	
	

	

	
	
	
	
	
	
	

	
	
	/**
	 * Returns the current name of this worm.
	 */
	@Basic
	public String getName(){
		return this.name;
	}
	
	/**
	 * Sets the name of this worm to the given string.
	 */
	@Basic
	public void setName(String name) throws IllegalArgumentException{
		if (!isValidName(name))
			throw new IllegalArgumentException("Invalid name!");
		this.name = name;
	}
	
	/**
	 * Variable registering the name of this worm.
	 */
	private String name;
	
	public static String getRandomName(){
		int randomIndex = (int) Math.floor(Math.random() *randomWormNames.length);
		return randomWormNames[randomIndex];
	}
	
	//Zijn er nog andere variabele ndie ook static zijn?
	//Nog aanvullen
	private final static String[] randomWormNames = { "Bram", "Nicolas", "Erik", "Professor Steegmans", "Professor Vandewalle", 
		"Professor Creemers", "Rector Torfs", "Professor Vander Sloten", "Professor Vandepitte", 
		"Professor Dutree", "Professor Rijmen", "Professor Beernaert", "Professor Rummens", "Professor Baelmans",
		"Professor Houssa", "Professor Verlinden", "Professor Blanpain", "Professor Dehaene", "Professor Smet",
		"Professor Meerbergen", "Professor Roose", "Professor Nauwelaers", "Professor Dhaene", "Professor De Laet",
		"Professor Preneel", "Professor Berendt", "Professor Van Leeuwen", "Voormalig Rector Waes", "Voormalig Rector Vervenne"};
	
	/**
	 * Checks whether the given string is a valid name for any worm.
	 * @param 	name
	 * 			The name which has to be checked. 
	 * @return	True if and only if the worm's name meets the following requirements
	 * 			1) at least two characters long
	 * 			2) start with an uppercase letter
	 * 			3) only use letters, spaces, quotes (both single and double), and numbers
	 * 			| (name.length() < 2)
	 * 			|  	&& (Character.isLetter(name.charAt(0))) || !(Character.isUpperCase(name.charAt(0)))
	 * 			|  	&& (containsValidCharacters(name))
	 */
	public static boolean isValidName(String name){
		if (name.length() < 2)
			return false;
		if (!(Character.isLetter(name.charAt(0))) || !(Character.isUpperCase(name.charAt(0))))
			return false;
		if (!containsValidCharacters(name))
			return false;
		return true;
	}
	
	
	//DOCUMENTATIE INCORRECT.
	/**
	 * Checks whether the given string contains valid characters
	 * @param 	name
	 * 			The name to check.
	 * @return	True if and only if the given name contains only letters, spaces or quotes (single and double)
	 * 			| for c in name
	 * 			| 		((Character.isLetter(c)) || (c.isSpaceOrQuote()) || (c.isDigit()))
	 */
	public static boolean containsValidCharacters(String name){
		Set<Character> validCharacters = new HashSet<>();
		validCharacters.add('"');
		validCharacters.add('\'');
		validCharacters.add(' ');
		
		char[] charactersName = name.toCharArray();
		for (char c : charactersName) {
			if ( (Character.isLetter(c)) || (validCharacters.contains(c)) || (Character.isDigit(c)) ) {
	            return true;
	            }
		}
	    return false;
	    }
	
	
	
	
	
	
	
	/**
	 * Returns the current team of this worm.
	 */
	@Basic
	public Team getTeam(){
		return this.team;
	}
	
	//MISSCHIEN EXCEPTION THROWEN ALS WORM AL EEN TEAM HEEFT.
	/**
	 * Sets the current team of this worm to the given team.
	 */
	@Basic
	public void setTeam(Team team) throws IllegalStateException {
		if (this.hasTeam())
			throw new IllegalStateException("This worm already has a team! A worm can only be part of one team!");
		this.team = team;
		team.addNewWorm(this);
	}
	
	//DOCUMENTATIE
	/**
	 * Unset the team, if any, from this worm.
	 *
	 * @post    This worm no longer has a team.
	 *        	| ! new.hasTeam()
	 * @effect  The former team of this worm, if any, no longer
	 *          has this worm as one of its team members.
	 *        	|    (getTeam() == null)
	 *          |     || (! (new getTeam()).hasAsOwning(owning))
	 * @effect   All ownings registered beyond the position at which
	 *          this owning was registered shift one position to the left.
	 *        	| (getOwner() == null) ||
	 *        	| (for each index in
	 *        	|        getOwner().getIndexOfOwning(owning)+1..getOwner().getNbOwnings():
	 *        	|    (new getOwner()).getOwningAt(index-1) == getOwner().getOwningAt(index) ) 
	 */
	public void unsetTeam() {
		if (this.hasTeam()) 
		{
			Team formerTeam = this.getTeam();
			this.team = null;
			formerTeam.removeWorm(this);
			if (formerTeam.getTeamMembers().size() == 0)
				formerTeam.terminate();
		}
	}
	
	/**
	 * Check whether this worm has a team.
	 * @return 	True if and only if the team of this worm is effective.
	 * 			| (this.getTeam != null)
	 */
	@Raw
	public boolean hasTeam() {
		return (this.getTeam() != null);
	}
	
	//DEZE METHODE KLOPT NOG NIET HELEMAAL MET OWNABLE. DAAR OOK SETWORLDTO
	//MISSCHIEN NOG TE IMPLEMENTEREN:
		// canHavaAsTeam()
		// hasProperTeam()
	//public boolean hasProperTeam(){
	//	return true;
	//}
	
	/**
	 * Variable registering the team of this worm.
	 */
	private Team team;
	
	
	
	
	
	public int getIndexActiveWeapon(){
		return this.indexActiveWeapon;
	}
	
	public void setIndexActiveWeapon(int index){
		if (! isValidIndexActiveWeapon(index))
			throw new IllegalArgumentException("The given index was negative or bigger than the amount of weapons!");
		this.indexActiveWeapon = index; 
	}
	
	public boolean isValidIndexActiveWeapon(int index){
		return ( (index >= 0) && (index <=  listOfWeapons.length - 1 ) );
	}
	
	private int indexActiveWeapon;
	
	String listOfWeapons[] = {"Bazooka", "Rifle"}; 

	
	
	
	public String getActiveWeapon(){
		return listOfWeapons[getIndexActiveWeapon()];
	}
	
	public void selectNextWeapon(){
		if (getIndexActiveWeapon() == listOfWeapons.length - 1 )
			setIndexActiveWeapon(0);
		else {
			setIndexActiveWeapon( getIndexActiveWeapon() + 1 );
		}
	}
	
	
	
	//ALLES VAN PROGRAM NOG
	
	public Program getProgram(){
		return this.program;
	}
	
	private final Program program;
	//canHaveAsProgram en alle dynamische binding shit
	
	public boolean hasProgram(){
		return (this.getProgram() != null);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Turns this worm by the given angle.
	 * 
	 * @param  	angle
	 * 		   	The angle (in radians) which the given worm should turn.
	 * @pre    	The current amount of action points of this worm should be higher than 
	 * 			the amount of action points it costs to turn this worm over the given angle.
	 *        	| canTurn(angle)
	 *        //effect
	 * @post 	The worm has turned over a certain angle.
	 * 			| new.getDirection() == this.getDirection() + angle
	 * @post	The amount of action points of this worm has been decreased with (Math.abs(angle)/(2*Math.PI))*60
	 *          | new.getActionPoints() == (int) Math.ceil(this.getActionPoints() - (Math.abs(angle)/(2*Math.PI))*60)
	 */
	public void turn(double angle){
		assert canTurn(angle);
		this.setDirection((this.getDirection() + angle));
		this.setActionPoints((int) Math.ceil( 
				this.getActionPoints() - (Math.abs(angle)/(2*Math.PI))*60 ));
	}
	
	/**
	 * Checks whether or not this worm can perform a worm by the given angle.
	 * @param	angle
	 * 			The angle for which must be checked if this worm can perform this turn.
	 * @return 	True if and only if the worm has enough action points left to perform a turn by the given angle.
				| (this.getActionPoints() - (angle/(2*Math.PI))*60 >= 0)
	 */
	public boolean canTurn(double angle){
		return (this.getActionPoints() - (Math.abs(angle)/(2*Math.PI))*60) >= 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Moves this worm by the given number of steps.
	 * @param 	nbSteps
	 * 			The amount of steps the worm should perform.
	 * @post	This worm has moved by the given amount of steps.
	 * 			| new.getPositionX() == this.getPositionX() + nbSteps * this.getRadius() * Math.cos(this.getDirection())
	 * 			| new.getPositionY() == this.getPositionY() + nbSteps * this.getRadius() * Math.sin(this.getDirection())
	 * @post	The amount of action points of this worm has been decreased with nbSteps*(|cos(theta)| + 4*|sin(theta)|
	 * 			where theta is the direction of this worm.
	 * 			| new.getActionPoints() == this.getActionPoints() 
								- (int)Math.ceil( 	(Math.abs( Math.cos( this.getDirection() ) ) * nbSteps * this.getRadius()
								+ 4 *Math.abs( Math.sin( this.getDirection() ) ) * nbSteps * this.getRadius())))
	 * @throws 	IllegalArgumentException
	 * 			The given number of steps is not possible.
	 * 			| (nbSteps <= 0)
	 * @throws	IllegalStateException
	 * 			This worm does not have enough action points left to perform this move.
	 * 			| (! canMove(nbSteps))
	 */
	public void move() throws IllegalArgumentException, IllegalStateException {
		// OUD:
//		if (!isValidAmountOfSteps(nbSteps))
//		throw new IllegalArgumentException("The number of steps should be larger than zero!");
//		this.setPositionX(this.getPositionX() + nbSteps * this.getRadius() * Math.cos(this.getDirection()));
//		this.setPositionY(this.getPositionY() + nbSteps * this.getRadius() * Math.sin(this.getDirection()));
//		if (!this.isTerminated()){
//			this.setActionPoints(this.getActionPoints() 
//					- (int)Math.ceil( 	(Math.abs( Math.cos( this.getDirection() ) ) * nbSteps * this.getRadius()
//									+ 4 *Math.abs( Math.sin( this.getDirection() ) ) * nbSteps * this.getRadius())));
//			this.Eat();
//		}
//		if (!canMove())
//			throw new IllegalStateException("This worm does not have enough action points left to perform this move!");
		double[] newPosition = findOptimalMovePosition();
		this.setActionPoints(getActionPoints() - getMoveActionPointsCost(newPosition) );
		
		//OUD:setPositionX(newPosition[0]);
		//OUD:setPositionY(newPosition[1]);
		this.setPosition(newPosition[0],newPosition[1]);
		if (! this.isTerminated())
			this.fall();
			this.Eat();
	}
			
		// 1. POSITIE ZOEKEN
		//		a) minimaliseren divergence
		//		b) maximaliseren afstand
		// 2. POSITIE VERZETTEN
		// Als nog niet geterminated. 
		// 3. ACTIONPOINTS VERZETTEN
		// 4. ETEN
		// Niet vergeten worm terminaten als hij buiten de wereld movet.
		
	
	
	public double[] findOptimalMovePosition(){
		double[] optimalPosition = new double[] {this.getPosition().getX(), this.getPosition().getY()};
		double optimalFactor = -1;
		double divergence = -0.7875;
		while (divergence <= 0.7875) {
			double[] positionSoFar = findPositionForGivenDirection(getDirection()+divergence,0.1*getRadius());
			double distanceSoFar = this.getPosition().calculateDistance(positionSoFar[0],positionSoFar[1]);
			// Kleinste kwadraten optimalisatie.
			double factorSoFar = Math.pow(distanceSoFar / getRadius(),2) - Math.pow(Math.abs(divergence / 0.7875),2);
			//Distance covered must be atleast 0.1 meters.
			if ( (distanceSoFar > 0.1) && (factorSoFar > optimalFactor) && (canMove(positionSoFar)) ){
				optimalFactor = factorSoFar;
				optimalPosition[0] = positionSoFar[0];
				optimalPosition[1] = positionSoFar[1];
			}			
			divergence = divergence + 0.0175;
		}
		return optimalPosition;
	}
	
	//Later in position klasse?
	//private?
	public double[] findPositionForGivenDirection(double direction, double stepSize){
		double[] positionFound = new double[] {getPosition().getX(),getPosition().getY()};		
		double x = getPosition().getX();
		double y = getPosition().getY();
		// We are aware we calculate too many points. 
		//--> OPTIMALISEREN. tussen 0 en 0.1 moet eiglk niet echt gecontroleerd worden, 
		//dus als hier later nog een idee voor komt dan moet dit nog geïmplementeerd.
		double step = 0;
		while (step <= getRadius() && getWorld().isPassable(x,y,getRadius())){
			x = getPosition().getX() + step * Math.cos(direction);
			y = getPosition().getY() + step * Math.sin(direction);
			step += this.getRadius()*0.01;
		}
		if (this.getWorld().isPassable(x, y, getRadius())){
			positionFound[0] = x;
			positionFound[1] = y;
			return positionFound;
		}
		else{
			positionFound[0] = this.getPosition().getX();
			positionFound[1] = this.getPosition().getY();
			return positionFound;
		}
	}
	
	//DUMMY
	public int getMoveActionPointsCost(double[] newPosition){
		double distanceMoved = this.getPosition().calculateDistance(newPosition[0],newPosition[1]);
		double cost = Math.abs(distanceMoved * Math.cos(getDirection())) + Math.abs(4 * distanceMoved * Math.sin(getDirection()));
		return (int)Math.ceil(cost);
	}
	
	//Misschien onnodig geworden.
//	public boolean isValidAmountOfSteps(int nbSteps){
//		return (nbSteps >= 0) && (! Double.isNaN(nbSteps));
//	}
	
	
	/**
	 * Checks whether or not this worm can perform this move.
	 * @param 	nbSteps
	 * 			The amount of steps for which must be checked if this worm can perform this turn.
	 * @return	True if and only if the worm has enough action points left to perform a move of the given number of steps.
	 * 			| ((this.getActionPoints() 
	 *			|		- (Math.abs(Math.cos(this.getDirection()))*nbSteps*this.getRadius()
     *					+ 4*Math.abs(Math.sin(this.getDirection()))*nbSteps*this.getRadius())) >= 0)
	 */
	//DUMMY!!!!
	public boolean canMove(double[] position){
		return (this.getActionPoints() >= getMoveActionPointsCost(position));
		//OUD
//		return (this.getActionPoints() 
//				- (Math.abs(Math.cos(this.getDirection()))*nbSteps*this.getRadius()
//						+4*Math.abs(Math.sin(this.getDirection()))*nbSteps*this.getRadius())) >= 0;
	}
	
	


	
	
	
	
	
	public void jump() throws IllegalStateException{
		if ( ! this.canJump() )
			//"The worm is facing downwards or it has no action points left.
			throw new IllegalStateException("The worm has no action points left.");
		super.jump();
		if  (! this.isTerminated())	{
				this.setActionPoints(0);
				this.Eat();
		}
	}


	
	
	
	
	
	
	@Override
	public double getForce(){
		return 5.0 * this.getActionPoints() + getMass() * g;
	}
	
	/**
	 * Checks whether the given worm is able to jump.
	 * 
	 * @return 	True if and only if the worm has enough action points left to perform a jump
	 * 			|return !(this.getDirection() > Math.PI)
	 * 
	 */
	@Override
	public boolean canJump(){
		return !( Util.fuzzyEquals(this.getActionPoints(), 0));
	}
	
	
	
	
	
	
	
	
	
	
	
// fall(Worm worm)	 ---> gewoon de kolom onder uw huidige position checken tot je FALSE tegenkomt en dan stop je en anders stop je niet en ben je dood
//	 --> altijd de functie fall laten uitvoeren voor als je met je kop tegen het plafond zou hangen.
//fallStep???
	

	public void fall() throws IllegalStateException, NullPointerException{
		//if (! canFall())
			//throw new IllegalStateException();
		//Je moet enkel fall uitvoeren als je niet adjacent bent. Dit kan eigenlijk ook wel gewoon bij de methode canFall();
		if (! getWorld().isAdjacent(getPosition().getX(), getPosition().getY(), getRadius())){
			double fallPositionYSoFar = getPosition().getY();
			boolean fallCompleted = false;
			double i = 0;
			while (!fallCompleted){
				fallPositionYSoFar = fallPositionYSoFar - 0.01 * getRadius();
				i++;
				if (i == 10000){
					System.out.println("a");
					break;
				}
				
				// 0.05 misschien nog aanpassen? Dit is gekozen als de helft van 0.1 
				//if (outOfWorld(getWorld(), getPositionX(), fallPositionYSoFar, getRadius())){
					//Dit is eigenlijk niet correct
					//throw new NullPointerException();
					//fallCompleted = true;
					//this.terminate();
				//}
				else if (getWorld().isAdjacent(getPosition().getX(), fallPositionYSoFar, getRadius())){
					setHitPoints( getHitPoints() - getFallHitPoints(fallPositionYSoFar) );
					fallCompleted = true;
					if (! isTerminated()){
						//OUD: setPositionY(fallPositionYSoFar);
						this.setPosition(this.getPosition().getX(), fallPositionYSoFar);
						Eat();
					}
				}
			}
		}
	}
		//OF
		//korter maar minder efficiënt
		//setDirection(3.0*Math.PI / 2.0);
		//jump();

		//OUDE CODE
//		boolean jumpCompleted = false;
//		double x = this.getPositionX();
//		double y = this.getPositionY()-this.getRadius();
//		while (! jumpCompleted){
//			if (this.outOfWorld(this.getWorld(),x,y,this.getRadius())) {
//			this.setPositionX(x);
//			this.setPositionY(y);
//			jumpCompleted = true;
//		}
//		else if ( this.getWorld().isImpassable(x,y,1.1*this.getRadius()) ){
//			if (this.getWorld().isPassable(x,y, this.getRadius()) ){
//				this.setPositionX(x);
//				this.setPositionY(y);
//				jumpCompleted = true;
//			}
//			else {
//				throw new IllegalStateException("This fall is not possible.");
//			}
//		}
//		y = y-5*this.getWorld().getPixelHeight();
//	}
	
//			//throws IndexOutOfBoundsException MOET DIT ERBIJ?
//	{
//		//Veronderstelling controleren: linksonder is (0,0), rechtsboven is (width,height)
//		try{
//		boolean notYetLanded = true;
//		double y = this.getPositionY();
//		while (y >= 0 && notYetLanded){
//			if (! world.isPassableHalfCircleEdge(this.getPositionX(), y, this.getRadius() )){
//				this.setPositionY(y);
//				notYetLanded = false;
//				this.Eat();
//				}
//				
//			// 2 of 3 pixels overslagen??
//			else { y = y - this.getWorld().getPixelHeight();
//			}
//		
//		}}
//		catch (IndexOutOfBoundsException exc){
//			// Terminate worm.
//		}
//	}

	//DUMMY
	public boolean canFall(){
		//OUD
		//Als je niet met je kop tegen het plafond mag hangen
		//return (world.isPassableHalfCircleEdge(this.getPositionX(), this.getPositionY(), this.getRadius()));	
		//Als je wel met je kop tegen het plafond mag hangen
		//(this.getWorld().isAdjacent(this.getPositionX(), this.getPositionY(), this.getRadius()));
		return ( (!isTerminated()) && 
				(getWorld().isPassableRectangular(
					getPosition().getX()-getRadius()/2.0,getPosition().getY(),getPosition().getX()+getRadius()/2.0, getPosition().getY()-getRadius()/2.0 - getWorld().getPixelHeight())));

	}
	
	public int getFallHitPoints(double newY){
		double fallDistance = getPosition().getY() - newY;
		return ((int)Math.round(3*fallDistance));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void Eat(){
		while (this.isCloseToFood()){
			this.getNearbyFood().terminate();
			int formerMaximumHitPoints = this.getMaximumHitPoints();
			this.setRadius(this.getRadius()*1.1);
			this.setHitPoints(this.getHitPoints() + this.getMaximumHitPoints() - formerMaximumHitPoints);
		}
		
	}
	

	
	
	// void selectNextWeapon(Worm worm);
	// void shoot(Worm worm, int yield);
	// canShoot
	// Weapon wordt attribuut van worm.
	// getWeapon en setWeapon enzovoort
	// listOfWeapons.
	//Alles met projectile.
	
	public void shoot(int yield){
		Projectile projectile = createProjectile(yield);
		if (!canShoot(yield))
			throw new IllegalStateException("The worm does not have enough action points left to shoot!");
		this.getWorld().setProjectile(projectile);
		this.setActionPoints( this.getActionPoints() - projectile.getActionPointsCost());
	}
	
	public boolean canShoot(int yield){
		Projectile projectile = createProjectile(yield);
		return (this.getActionPoints() - projectile.getActionPointsCost() >= 0);
	}
		
	
	public Projectile createProjectile(int yield){
		Projectile projectile = null;
		double projectileX = this.getPosition().getX() + Math.cos( this.getDirection() ) * getRadius();
		double projectileY = this.getPosition().getY() + Math.sin( this.getDirection() ) * getRadius();
		if (this.getActiveWeapon().equals("Bazooka"))
			projectile = new Bazooka(getWorld(), projectileX, projectileY, getDirection(), yield);
		if (this.getActiveWeapon().equals("Rifle"))
			projectile = new Rifle(getWorld(), projectileX, projectileY, getDirection());
		return projectile;
	}

}
