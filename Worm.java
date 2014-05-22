package worms.model;

import be.kuleuven.cs.som.annotate.*;
import worms.util.Util;

import java.util.HashSet;
import java.util.Set;
// Te Doen: jump,jumptime
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
	
	// NOG DOEN
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
	
	
	
	
	//NOG DOEN jump, getjumptime

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
	

	
	
	
	
	
	
	
	
	
	
	

	

	 // IN ORDE.
	/**
	 * Sets the radius of this worm to the given value.
	 * 
	 * @param	radius
	 * 			The new radius of this worm
	 * @post	The new radius of this worm is equal to the given radius.
	 * 			| new.getRadius() == radius
	 * @effect	The maximum action and hit points are changed accordingly.
	 * 			| setMaximumActionPoints()
	 * 			| 	&& setMaximumHitPoints()
	 * @throws	IllegalArgumentException
	 * 			The radius is an invalid radius for this GameObject.
	 * 			| (!isValidRadius(radius))
	 */
	@Override
	public void setRadius(double radius) throws IllegalArgumentException{
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

	
	
	
	
	
	
	
	
	
	
	// IN ORDE.
	/**
	 * Returns the mass of this worm.
	 * 
	 * @return	The mass of this worm, calculated with following formula:
	 * 			mass = density * 4/3 * Pi * radius^3
	 * 			| result == density * 4.0/3.0 * Math.PI * Math.pow(this.getRadius(),3)
	 */
	@Override
	public double getMass(){
		double mass = getDensity() * 4.0/3.0 * Math.PI * Math.pow(this.getRadius(),3);
		return mass;
	}
	
	@Override @Immutable
	public double getDensity(){
		return density;
	}
	
	/**
	 * Variable registering the density of any worm (final value).
	 */
	public final double density = 1062.0; 
	
	
	
	
	
	
	
	
	
	
	//IN ORDE
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
	@Basic
	public void setMaximumActionPoints(){
		this.maximumActionPoints = (int) Math.round(getMass());
	}
	
	/**
	 * Variable registering the maximum number of action points of this worm.
	 */
	private int maximumActionPoints;
	
	
	
	
	
	
	
	// IN ORDE.
	/**
	 * Returns the current number of action points of this worm.
	 */
	@Basic
	public int getActionPoints(){
		return this.actionPoints;
	}
	
	/**
	 * Sets the current number of action points of this worm to the given value.
	 * 
	 * @param	actionPoints
	 * 			The new amount of action points of this worm.
	 * @post	If the given amount of action points is larger than the maximum amount of action points of this worm,
	 * 			the new amount of action points of this worm is equal to the maximum amount of action points of this worm.
	 *			| if (actionPoints > this.getMaximumActionPoints())
	 * 			|   new.getActionPoints() == this.getMaximumActionPoints()
	 * @post	If the given amount of action points is smaller than or equal to zero, 
	 * 			the new amount of action points of this worm is equal to zero, and the next turn is started.
	 * 			| else if (actionPoints <= 0)
	 * 			| 	new.getActionPoints() == 0
	 * 			| 	this.getWorld().startNextTurn()
	 * @post	Otherwise, the new amount of action points of this worm is equal to the given amount of action points.
	 * 			| else
	 * 			|	new.getActionPoints() == actionPoints
	 */
	public void setActionPoints(int actionPoints){
		if (actionPoints > this.getMaximumActionPoints())
			this.actionPoints = this.getMaximumActionPoints();
		else if (actionPoints <= 0){
			this.actionPoints = 0;
			this.getWorld().startNextTurn();
		}
		else
			this.actionPoints = actionPoints;
	}
	
	/**
	 * Variable registering the number of action points of this worm.
	 */
	private int actionPoints;
	
	
	
	
	
	// IN ORDE
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
	}
	
	/**
	 * Variable registering the maximum number of hit points of this worm.
	 */
	private int maximumHitPoints;
	
	
	
	
	
	
	
	
	
	
	
	// IN ORDE
	/**
	 * Returns the current number of hit points of this worm.
	 */
	@Basic
	public int getHitPoints(){
		return this.hitPoints;
	}
	
	/**
	 * Sets the current number of hit points of this worm to the given value.
	 * 
	 * @param	hitPoints
	 * 			The new amount of hit points for this worm.
	 * @post	If the given amount of hit points is larger than the maximum amount of hit points of this worm,
	 * 			the new amount of hit points of this worm is equal to the maximum amount of action points of this worm.
	 *			| if (hitPoints > this.getMaximumHitPoints())
	 * 			|   new.getHitPoints() == this.getMaximumHitPoints()
	 * @effect	If the given amount of hit points is smaller than or equal to zero, 
	 * 			this worm is terminated.
	 * 			| else if (actionPoints <= 0)
	 * 			| 	this.isTerminated() == true
	 * @post	Otherwise, the new amount of hit points of this worm is equal to the given amount of hit points.
	 * 			| else
	 * 			|	new.getActionPoints() == actionPoints
	 * 
	 */
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
	
	
	
	
	
	
	
	
	

		
		
		
		
	
	
	
	

	
	// IN ORDE
	/**
	 * Returns the current name of this worm.
	 */
	@Basic
	public String getName(){
		return this.name;
	}
	
	/**
	 * Sets the name of this worm to the given string.
	 * 
	 * @param	name
	 * 			The new name for this worm.
	 * @post	If the given name is a valid name for a worm,
	 * 			the new name of this worm is equal to the given name.
	 * 			| new.getName() == name
	 * @throws	IllegalArgumentException
	 * 			The given name is an invalid name for a worm.
	 * 			| (!isValidName(name))
	 */
	public void setName(String name) throws IllegalArgumentException{
		if (!isValidName(name))
			throw new IllegalArgumentException("Invalid name!");
		this.name = name;
	}
	
	/**
	 * Variable registering the name of this worm.
	 */
	private String name;
	
	
	/**
	 * Checks whether the given string is a valid name for any worm.
	 * 
	 * @param 	name
	 * 			The name which has to be checked. 
	 * @return	True if and only if the worm's name meets the following requirements
	 * 			1) It is at least two characters long
	 * 			2) It starts with an uppercase letter
	 * 			3) It only uses letters, spaces, quotes (both single and double), and numbers
	 * 			| (name.length() > 2)
	 * 			|  	&& (Character.isLetter(name.charAt(0))) || (Character.isUpperCase(name.charAt(0)))
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
	


	/**
	 * Checks whether the given string contains valid characters
	 * @param 	name
	 * 			The name to check.
	 * @return	True if and only if the given name contains only letters, spaces or quotes (single and double).
	 * 			| validCharacters = { '"', '\'', ' '}
	 * 			| for (char c: name){
	 * 			|	if ((Character.isLetter(c)) || (validCharacters.contains(c)) || (c.isDigit()))
	 * 			| 		result == true}
	 * 			| result == false
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
	 * Returns a random name of a professor of the KU Leuven, as an example name for a worm.
	 * 
	 * @return 	A random name for a worm.
	 * 			| randomIndex = (int) Math.floor(Math.random() * randomWormNames.length)
	 * 			| result == randomWormNames[randomIndex]
	 */
	public static String getRandomName(){
		int randomIndex = (int) Math.floor(Math.random() *randomWormNames.length);
		return randomWormNames[randomIndex];
	}
	
	/**
	 * A list of random names as examples for a worm.
	 */
	private final static String[] randomWormNames = { "Bram", "Nicolas", "Erik", "Professor Steegmans", "Professor Vandewalle", 
		"Professor Creemers", "Rector Torfs", "Professor Vander Sloten", "Professor Vandepitte", 
		"Professor Dutree", "Professor Rijmen", "Professor Beernaert", "Professor Rummens", "Professor Baelmans",
		"Professor Houssa", "Professor Verlinden", "Professor Blanpain", "Professor Dehaene", "Professor Smet",
		"Professor Meerbergen", "Professor Roose", "Professor Nauwelaers", "Professor Dhaene", "Professor De Laet",
		"Professor Preneel", "Professor Berendt", "Professor Van Leeuwen", "Voormalig Rector Waes", "Voormalig Rector Vervenne"};
	
	
	
	
	
	
	
	
	
	
	
	
	// DYNAMISCHE BINDING!!!
	/**
	 * Returns the current team of this worm.
	 */
	@Basic
	public Team getTeam(){
		return this.team;
	}


	/**
	 * Sets the current team of this worm to the given team.
	 * 
	 * @param	team
	 * 			The new team of this worm
	 * @post	The new team of this worm is equal to the given team, if it is a valid team.
	 * 			| new.getTeam() == team
	 * @effect	This worm is added to the given team
	 * 			| team.addNewWorm()
	 * @throws	IllegalStateException
	 * 			This worm is terminated.
	 * 			| (this.isTerminated())
	 * @throws	IllegalStateException
	 * 			The worm already has a team. It can only be part of one team.
	 * 			| (this.hasTeam())
	 * @throws	IllegalArgumentException(
	 * 			The worm cannot have this team as its team.
	 * 			| (!canHaveAsTeam(team))
	*/
	public void setTeam(Team team) throws IllegalStateException, IllegalArgumentException {
		if (this.isTerminated())
			throw new IllegalStateException("This worm is terminated.");
		if (this.hasTeam())
			throw new IllegalStateException("This worm already has a team! A worm can only be part of one team!");
		if (!canHaveAsTeam(team))
			throw new IllegalArgumentException("This worm cannot have the given team as its team!");
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
	 * 
	 * @return 	True if and only if the team of this worm is effective.
	 * 			| (this.getTeam != null)
	 */
	//@Raw
	public boolean hasTeam() {
		return (this.getTeam() != null);
	}
	
	/**
	 * Checks whether the given worm can be part of the given team.
	 * 
	 * @return	True if and only if the given team is not null and
	 * 			this worm and the team belong to the same world.
	 * 			| (team != null) && (team.getWorld == this.getWorld())
	 */
	public boolean canHaveAsTeam(Team team){
		if (team == null)
			return false;
		if (team.getWorld() != this.getWorld())
			return false;
		return true;
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
	
	
	
	
	
	
	// IN ORDE
	/**
	 * Returns the current index of the active weapon of this worm.
	 */
	@Basic
	public int getIndexActiveWeapon(){
		return this.indexActiveWeapon;
	}
	
	/**
	 * Returns the string of the current weapon of this worm.
	 */
	@Basic
	public String getActiveWeapon(){
		return listOfWeapons[getIndexActiveWeapon()];
	}
	
	/**
	 * Sets the index of the active weapon of this worm to the given value.
	 * 
	 * @param 	index
	 * 			The new index of the active weapon for this worm.
	 * @post	The new index of the active weapon of this worm is equal to the given index.
	 * 			| new.getIndexActiveWeapon() == index
	 * @throws	IllegalArgumentException
	 * 			The given index is not a valid weapon index.
	 * 			| (!isValidIndexActiveWeapon(weapon))
	 */
	public void setIndexActiveWeapon(int index){
		if (! isValidIndexActiveWeapon(index))
			throw new IllegalArgumentException("The given index was negative or bigger than the amount of weapons!");
		this.indexActiveWeapon = index; 
	}
	
	/**
	 * Checks whether the given weapon index is a valid weapon index.
	 *  
	 * @param 	index
	 * 			The weapon index to check.
	 * @return	True if and only if the given index is not equal to NaN,
	 * 			bigger or equal to zero and smaller or equal to the amount of weapons.
	 * 			| result == ( (index >= 0) && (index <=  listOfWeapons.length - 1 ) && (Double.isNaN(index)) )
	 */
	public boolean isValidIndexActiveWeapon(int index){
		return ( (index >= 0) && (index <=  listOfWeapons.length - 1 ) && (!Double.isNaN(index)) );
	}
	
	/**
	 * Variable registering the index of the active weapon of this game.
	 */
	private int indexActiveWeapon;
	
	/**
	 * List of all possible weapons that are available in this game.
	 */
	private final static String listOfWeapons[] = {"Bazooka", "Rifle"}; 

	
	/**
	 * Sets the index of the active weapon of this worm to the next weapon.
	 * 
	 * @effect	If the current active weapon is the last weapon in the list of weapons,
	 * 			the new index of the active weapon of this worm is equal to zero.
	 * 			| if (getIndexActiveWeapon() == listOfWeapons.length - 1)
	 * 			|	new.getIndexActiveWeapon() == 0
	 * 			Otherwise, the new index of the active weapon of this worm is equal to the current index + 1.
	 * 			| else
	 * 			|	new.getIndexActiveWeapon() == this.getActiveWeapon() + 1
	 */
	public void selectNextWeapon(){
		if (getIndexActiveWeapon() == listOfWeapons.length - 1 )
			setIndexActiveWeapon(0);
		else
			setIndexActiveWeapon( getIndexActiveWeapon() + 1 );
	}
	
	
	
	
	
	
	
	
	//ALLES VAN PROGRAM NOG
	// DYNAMISCHE BINDING
	public Program getProgram(){
		return this.program;
	}
	
	private final Program program;
	//canHaveAsProgram en alle dynamische binding shit
	
	public boolean hasProgram(){
		return (this.getProgram() != null);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//IN ORDE
	/**
	 * Turns this worm by the given angle.
	 * 
	 * @param  	angle
	 * 		   	The angle (in radians) which the given worm should turn.
	 * @pre    	The current amount of action points of this worm should be higher than 
	 * 			the amount of action points it costs to turn this worm over the given angle.
	 *        	| canTurn(angle)
	 * @effect 	The worm has turned over the given angle.
	 * 			| new.getDirection() == this.getDirection() + angle
	 * @effect	The amount of action points of this worm has been decreased with (Math.abs(angle)/(2*Math.PI))*60
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
	 * 
	 * @param	angle
	 * 			The angle for which must be checked if this worm can perform this turn.
	 * @return 	True if and only if the worm has enough action points left to perform a turn by the given angle.
				| result == (this.getActionPoints() - (angle/(2*Math.PI))*60 >= 0)
	 */
	public boolean canTurn(double angle){
		return (Util.fuzzyGreaterThanOrEqualTo((this.getActionPoints() - (Math.abs(angle)/(2*Math.PI))*60) , 0));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Moves this worm to the most optimal position nearby, if any.
	 */
	public void move() throws IllegalArgumentException, IllegalStateException {
		try{
			double[] newPosition = findOptimalMovePosition();
			this.setActionPoints(getActionPoints() - getMoveActionPointsCost(newPosition) );
			this.setPosition(newPosition[0],newPosition[1]);
			if (! this.isTerminated())
				this.fall();
				this.Eat();
		}
		catch (NullPointerException exc){
		}
	}
	
	/**
	 * Returns the most optimal position for this worm to move to. 
	 * More specifically, the position that this worm can move to which maximizes the distance covered, while minimizing the divergence.
	 */
	private double[] findOptimalMovePosition(){
		double[] optimalPosition = new double[] {this.getPosition().getX(), this.getPosition().getY()};
		double optimalFactor = -1;
		double divergence = -0.7875;
		while (Util.fuzzyLessThanOrEqualTo(divergence, 0.7875)) {
			double[] positionSoFar = findPositionForGivenDirection(getDirection()+divergence,0.1*getRadius());
			double distanceSoFar = this.getPosition().calculateDistance(positionSoFar[0],positionSoFar[1]);
			/* Kleinste kwadraten optimalisatie. */
			double factorSoFar = Math.pow(distanceSoFar / getRadius(),2) - Math.pow(Math.abs(divergence / 0.7875),2);
			/* Distance covered must be atleast 0.1 meters. */
			if ( (distanceSoFar > 0.1) && (factorSoFar > optimalFactor) && (canMove(positionSoFar)) ){
				optimalFactor = factorSoFar;
				optimalPosition[0] = positionSoFar[0];
				optimalPosition[1] = positionSoFar[1];
			}			
			divergence = divergence + 0.0175;
		}
		return optimalPosition;
	}
	
	/**
	 * Returns the best position to move to for a given direction. 
	 * That is, the most far-away position, that is less than this worm's radius away from this worm,
	 * 	and that is still adjacent.
	 */
	private double[] findPositionForGivenDirection(double direction, double stepSize){
		double[] positionFound = new double[] {getPosition().getX(),getPosition().getY()};		
		double x = getPosition().getX();
		double y = getPosition().getY();
		/* We are aware we calculate too many points. Tussen 0 en 1 moeten niet echt gecontroleerd worden -> optimaliseren */
		double step = 0;
		while (Util.fuzzyLessThanOrEqualTo(step,getRadius()) && getWorld().isPassable(x,y,getRadius())){
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
	
	/**
	 * Returns the action points it costs to move this worm to the given position.
	 * 
	 * @param 	newPosition
	 * 			The new position of this worm if it would perform this move.
	 * @return	The amount of action points it costs to perform a move to the given position.
	 * 			| distanceMoved = this.getPosition().calculateDistance(newPosition[0],newPosition[1])
	 * 			| cost = Math.abs(distanceMoved * Math.cos(getDirection())) + Math.abs(4 * distanceMoved * Math.sin(getDirection()))
	 * 			| result == (int) Math.ceil(cost)
	 */
	public int getMoveActionPointsCost(double[] newPosition){
		double distanceMoved = this.getPosition().calculateDistance(newPosition[0],newPosition[1]);
		double cost = Math.abs(distanceMoved * Math.cos(getDirection())) + Math.abs(4 * distanceMoved * Math.sin(getDirection()));
		return (int) Math.ceil(cost);
	}
	
	
	/**
	 * Checks whether or not this worm can perform this move.
	 * 
	 * @param 	position
	 * 			The new position of this worm if it would perform this move.
	 * @return	True if and only if the worm has enough action points left to perform a move to the given position.
	 * 			| ((this.getActionPoints() 
	 *			|		- (Math.abs(Math.cos(this.getDirection()))*nbSteps*this.getRadius()
     *					+ 4*Math.abs(Math.sin(this.getDirection()))*nbSteps*this.getRadius())) >= 0)
	 */
	public boolean canMove(double[] position){
		return (this.getActionPoints() >= getMoveActionPointsCost(position));
	}
	
	
	
	
	
	
	
	
	
	//DOCUMENTATIE & METHODE
	/**
	 * Makes this worm jump
	 * 
	 * @post 	The worm has jumped over a certain distance.
	 * 			| new.getPositionX() = this.getPositionX() + Math.pow(initialVelocity, 2) * Math.sin( 2 * this.getDirection() ) / g
	 * @post 	The jumping has consumed all the remaining action points.
	 * 			| new.getActionPoints() = 0
	 * @throws  IllegalStateException
	 * 			The worm is facing downwards (the direction should be between 0 and 2*PI) or it has no action points left.
	 * 			| (! canJump())
	 */
	
	public void jump() throws IllegalStateException{
		if ( ! this.canJump() )
			throw new IllegalStateException("The worm has no action points left.");
		double t = 0.01;
		boolean jumpCompleted = false;
		double x = this.getPosition().getX();
		double y = this.getPosition().getY();
		while (! jumpCompleted){
			double[] step = jumpStep(t);
			x = step[0];
			y = step[1];
			if (this.getWorld().outOfWorld(x,y,this.getRadius())) {
				//Eigenlijk niet correct!
				throw new NullPointerException();
			}
			else if ( !this.getWorld().isPassable(x,y,1.1*this.getRadius()) ){
				if (this.getWorld().isPassable(x,y, this.getRadius()) ){
					this.setPosition(x,y);
					jumpCompleted = true;
				}
				else {
					throw new IllegalStateException("This jump is not possible.");
				}
			}
			t = t + 0.001;
		}
		if  (! this.isTerminated())	{
				this.setActionPoints(0);
				this.Eat();
		}
	}
	
	//Oude commentaar, moet nog aangepast worden
	/**
	 * Returns the total amount of time (in seconds) that a
	 * jump of this worm would take.
	 * 
	 * @return 	Return the total amount of time that a jump of a given worm would take
	 * 			| time = distance / ( initialVelocity * Math.cos( this.getDirection() ));
	 */
	public double getJumpTime(double timeStep){
		double t = 0.01;
		boolean jumpCompleted = false;
		double x = this.getPosition().getX();
		double y = this.getPosition().getY();
		while (!jumpCompleted){
			double[] step = jumpStep(t);
			x = step[0];
			y = step[1];
			if (this.getWorld().outOfWorld(x,y,this.getRadius())) {
				jumpCompleted = true;
				return t;
			}
			if ( this.getWorld().isImpassable(x,y,1.1*this.getRadius()) ){
				if (this.getWorld().isPassable(x,y, this.getRadius()) ){
					jumpCompleted = true;
					return t;
				}
				else {
					return t;
				}
			}
			t = t + timeStep * 10;
		}
		return 0.0;
	}
	
	//DONE
	/**
	 * Returns the force a worm exerts to jump.
	 * 
	 * @return	The force this worm exerts to jump;
	 * 			| result == 5.0 * this.getActionPoints() + this.getMass() * g
	 */
	@Override
	public double getForce(){
		return 5.0 * this.getActionPoints() + this.getMass() * g;
	}
	
	//DONE
	/**
	 * Checks whether the given worm is able to jump.
	 * 
	 * @return 	True if and only if the worm has enough action points left to perform a jump.
	 * 			| result == (this.getActionPoints() > 0)
	 */
	@Override
	public boolean canJump(){
		return !(Util.fuzzyEquals(this.getActionPoints(), 0));
	}
	
	
	
	
	
	
	
	
	
	
	
// fall(Worm worm)	 ---> gewoon de kolom onder uw huidige position checken tot je FALSE tegenkomt en dan stop je en anders stop je niet en ben je dood
//	 --> altijd de functie fall laten uitvoeren voor als je met je kop tegen het plafond zou hangen.
//fallStep???
	//ALLES VAN FALL 

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
					this.terminate();
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
//		return ( (!isTerminated()) && 
//				(getWorld().isPassableRectangular(
//					getPosition().getX()-getRadius()/2.0,getPosition().getY(),getPosition().getX()+getRadius()/2.0, getPosition().getY()-getRadius()/2.0 - getWorld().getPixelHeight())));
		return(!isTerminated() && this.getWorld().isPassable(this.getPosition().getX(), this.getPosition().getY(), this.getRadius()));

	}
	
	/**
	 * Returns the amount of hit points the worm loses when it falls to the given position.
	 * 
	 * @param 	newY
	 * 			The new y-coordinate of the position of this worm if it would perform the fall.
	 * @return	The amount of hit points the worms loses when it falls to the given position.
	 * 			| result == (int) Math.round(3*(getPosition().getY() - newY))
	 */
	public int getFallHitPoints(double newY){
		return ( (int) Math.round(3*(getPosition().getY() - newY)));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	
	

	/**
	 * Shoots the active projectile of this worm in the direction the worm is currently facing with a given propulsion yield.
	 * @param 	yield
	 * 			The propulsion yield this worm's active projectile should be shot with.
	 * @effect	The projectile that should be shot is created.
	 * 			| createProjectile(yield)
	 * @effect	The new projectile of this world is equal to the projectile that will be shot.
	 * 			| this.getWorld().getProjectile() = createProjectile(yield)
	 * @effect	
	 */
	public void shoot(int yield){
		Projectile projectile = createProjectile(yield);
		if (!canShoot(yield))
			throw new IllegalStateException("The worm does not have enough action points left to shoot!");
		this.getWorld().setProjectile(projectile);
		this.setActionPoints( this.getActionPoints() - projectile.getActionPointsCost());
	}
	
	/**
	 * Returns whether or not this worm has enough action points to shoot.
	 * 
	 * @param 	yield
	 * 			The propulsion yield that would be used if the worm performed this shoot.
	 * @return	True if and only if the worm has enough action points to perform this shoot.
	 * 			| projectile = createProjectile(yield)
	 * 			| result == (this.getActionPoints() - projectile.getActionPointsCost() >= 0)
	 */
	public boolean canShoot(int yield){
		Projectile projectile = createProjectile(yield);
		return Util.fuzzyGreaterThanOrEqualTo(this.getActionPoints() - projectile.getActionPointsCost(), 0);
	}
		
	/**
	 * Create a projectile with given yield that belongs to this worm.
	 * 
	 * @param 	yield
	 * 			The propulsion yield of this new projectile.
	 * @return	Returns a new bazooka or rifle with given propulsion yield,
	 * 			depending on the active weapon of this worm.
	 * 			| if (this.getActiveWeapon().equals("Bazooka"))
	 *			| 	result == Bazooka(getWorld(), projectileX, projectileY, getDirection(), yield)
	 *			| if (this.getActiveWeapon().equals("Rifle"))
	 * 			|	result == Rifle(getWorld(), projectileX, projectileY, getDirection())
	 * @note	This method should be adjusted when a new sort of weapons gets added to the game.
	 */
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

	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Eat all the food, if any, that is close to this worm.
	 * 
	 * @effect	As long as the worm is close to food, the worm eats the food and terminates it.
	 * 			The worm's new radius is equal to the current radius times 1.1.
	 * 			The worm's maximum hit points are changed accordingly and the worms new amount of hit points
	 * 			are equal to the current amount of hit points added with the amount of maximum hit points it gained by eating this food.
	 * 			The worm's new position is equal to the position closest to the current position, but no longer overlapping with impassable terrain.
	 * 			| while (this.isCloseToFood())
	 * 			| 	(new (this.getNearbyFood())).isTerminated == true
	 * 			| 	new.getRadius() == this.getRadius() * 1.1
	 * 			| 	new.getHitPoints() == this.getHitPoints() + new.getMaximumHitPoints() - this.getMaximumHitPoints()
	 * 			| 	new.getPosition() == this.getWorld().searchClosestAdjacentPosition(this))
	 * 			
	 */
	public void Eat(){
		while (this.isCloseToFood()){
			this.getNearbyFood().terminate();
			int formerMaximumHitPoints = this.getMaximumHitPoints();
			this.setRadius(this.getRadius()*1.1);
			this.setHitPoints(this.getHitPoints() + this.getMaximumHitPoints() - formerMaximumHitPoints);
			/* Dit moet erbij, anders kan de worm niet moven na te eten */
			this.setPosition(this.getWorld().searchClosestAdjacentPosition(this));
		}
		
	}
}
