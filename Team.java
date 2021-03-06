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
import java.util.ArrayList;

import be.kuleuven.cs.som.annotate.*;



/**
 * A help class of teams for worms, involving a name, a membershiplist and an index which contains 
 * 		whose turn it currently is.
 * 
 * @invar 	The name of each Team must be a valid name for a Team.
 * 			| isValidName(this.getName())
 *
 * @author 	Nicolas Hoppenbrouwers
 * 			Bram Lust
 * @version 2.0
 *
 */



public class Team {
	//IN ORDE
	/**
	 * Initialize this new team with the given world and name.
	 * 
	 * @param 	world
	 * 			The world of this new team.
	 * @param 	name
	 * 			The name of this new team.
	 * @effect	The world of this new team is equal to the given team.
	 * 			| new.getWorld() == world
	 * @effect	The name of this new team is equal to the given name.
	 * 			| new.getName() == name
	 * @effect	The membership list of this new team is initialized as an empty array.
	 * 			| new.getTeamMembers() == ArrayList<Worm>();
	 * @effect	The index of the current worm of this new team is initialized as zero.
	 * 			| new.getNbCurrentWorm() == 0;
	 */
	@Raw
	public Team(World world, String name){
		this.setWorld(world);
		this.setName(name);
		this.listOfWorms = new ArrayList<Worm>();
		this.setNbCurrentWorm(0);
	}
	
	//IN ORDE
	/**
	 * Initialize this new team with the given world, name and list of members (Worms).
	 * 
	 * @param	world
	 * 			The world of this new team.
	 * @param 	name
	 * 			The name of this new team.
	 * @param 	team
	 * 			The worms that this team should contain as members.
	 * @effect	The world of this new team is equal to the given world.
	 * 			| new.getWorld() == world
	 * @effect  The name of this new team is equal to the given name.
	 * 			| new.getName() == name
	 * @effect	The membership list of this new team is equal to the given list of worms.
	 * 			| new.getTeamMembers() == team
	 * @effect	The index of the current worm of this new team is initialized as zero.
	 * 			| new.getNbCurrentWorm() == 0;
	 */
	@Raw
	public Team(World world, String name, ArrayList<Worm> team){
		this.setWorld(world);
		this.setName(name);
		this.setTeamMembers(team);
		this.setNbCurrentWorm(0);
	}
	
	
	
	
	
	
	
	//TERMINAT
	/**
	 * Check whether this team is terminated.
	 */
	@Basic
	public boolean isTerminated(){
		return (! this.hasWorld());
	}
	
	
	//NOG DOEN
	public void terminate(){
		if (this.getWorld().getTeams().indexOf(this) <= this.getWorld().getNbCurrentTeam())
			 this.getWorld().setNbCurrentTeam(this.getWorld().getNbCurrentTeam() - 1);
		this.unsetWorld();
	}
	
	
	
	
	

	
	
	//NOG DOEN
	/**
	 * Return the world of this team.
	 */
	@Basic
	public World getWorld(){
		return this.world;
	}
	
	//NOG DOEN
	public void setWorld(World world){
		//De eerste voorwaarde moet er bij anders krijg je een NullPointerException op de tweede voorwaarde.
		if ((world != null) && (world.amountOfTeamsExceeded()) )
			throw new IllegalStateException("This world already has 10 teams!");
		this.world = world;
	}

	/**
	 * Unset the world, if any, from this team.
	 *
	 * @post    This team no longer has a world.
	 *        	| ! new.hasTeam()
	 * @effect  The former world of this team, if any, no longer
	 *          has this team as one of its teams.
	 *        	|    (getTeam() == null)
	 *          |     || (! (new getTeam()).hasAsTeam(team))
	 */
	public void unsetWorld(){
		if (this.hasWorld()){
			World formerWorld = this.getWorld();
			this.setWorld(null);
			formerWorld.removeTeam(this);
		}
	}
	
	/**
	 * Check whether this team has a world.
	 * 
	 * @return	True if and only if the world of this team is effective.
	 * 			| (this.getWorld() != null)
	 */
	public boolean hasWorld(){
		return (this.getWorld() != null);
	}
	
	/**
	 * Variable registering the world of this team.
	 */
	private World world;
	
	
	
	
	
	// IN ORDE.
	/**
	 * Returns the name of this team.
	 */
	@Basic
	public String getName(){
		return this.name;
	}
	
	/**
	 * Sets the name of this team to the given team name.
	 * 
	 * @param	teamName
	 * 			The name to set this team's name to.
	 * @post	If the given name is a valid name, this team's new name is equal to the given name.
	 * 			| new.getName() == teamName
	 * @throws	IllegalArgumentException
	 * 			The given name is an invalid name for a team.
	 * 			| (!isValidName())
	 */
	public void setName(String teamName) throws IllegalArgumentException{
		if (!isValidName(this.getWorld(), teamName))
			throw new IllegalArgumentException("Invalid team name!");
		this.name = teamName;
	}	
	
	
	/**
	 * Checks whether the given string is a valid name for any team.
	 * 
	 * @param	world
	 * 			The world of the team.
	 * @param 	teamName
	 * 			The team name which has to be checked. 
	 * @return	True if and only if the team's name meets the following requirements:
	 * 			1) the name is a unique name for this world
	 * 			2) the name is at least two characters long
	 * 			3) the name starts with an uppercase letter
	 * 			4) the name only uses letters
	 * 			| ((isUniqueTeamName(world,teamName)) 
	 * 			|  && ((teamName.length() > 2)
	 * 			|  && ((Character.isLetter(name.charAt(0))) || (Character.isUpperCase(name.charAt(0))))
	 * 			|  && (for (char c: teamName){
	 * 			|      		c.isLetter(c)
	 * 			|		} )
	 */
	public static boolean isValidName(World world, String teamName){
		if (! isUniqueTeamName(world,teamName))
			return false;
		if (teamName.length() < 2)
			return false;
		if (!(Character.isLetter(teamName.charAt(0))) || !(Character.isUpperCase(teamName.charAt(0))))
			return false;
		
		char[] charactersName = teamName.toCharArray();
		for (char c : charactersName){
			if (!Character.isLetter(c))
				return false;
		}
		
		return true;
	}
	
	/**
	 * Checks whether the given name is a unique team name for the given world.
	 * 
	 * @param	world
	 * 			The world of the team.
	 * @param 	teamName
	 * 			The team name which has to be checked. 
	 * @return	True if and only if the given name is a unique team name in the world of that team.
	 * 			| for (Team team: world.getTeams() ){
	 *			|	if (team.getName().equals(teamName))
	 *			|		result == false;
	 * 			|	}
	 * 			| result == true;
	 */
	public static boolean isUniqueTeamName(World world, String teamName){
		for (Team team: world.getTeams() ){
			if (team.getName().equals(teamName))
				return false;
		}
		return true;
	}
	
	/**
	 * Variable registering the name of this worm.
	 */
	private String name;
	
	
	
	

	
	
	
	
	// IN ORDE.
	/**
	 * Returns the list of worms that are in this team.
	 */
	@Basic
	public ArrayList<Worm> getTeamMembers(){
		return this.listOfWorms;
	}
	
	/**
	 * Sets the list of worms of this team to the given list of worms.
	 * 
	 * @param	team
	 * 			The worms that this team's list of members should be set to.
	 * @post	If the team can have this list of worms as its team, 
	 * 			this team's new list of members is equal to the given list of members.
	 * 			| new.getTeamMembers() == team
	 * @throws	IllegalArgumentException
	 * 			The given list of members contains one or more worms that do not belong
	 * 			to the same world as this team.
	 * 			| (!canHaveAsTeam(team))
	 */
	public void setTeamMembers(ArrayList<Worm> team) throws IllegalArgumentException{
		if (!canHaveAsTeam(team))
			throw new IllegalArgumentException(
					"The given list of members contains one or more worms that do not belong to the same world as this team.");
		this.listOfWorms = team;
	}
	
	/**
	 * Checks whether this team can have the given list of worms as its list of team members.
	 * @param 	team
	 * 			The list of worms to check.
	 * @return	True if and only if every worm of the given team belongs to the same world as this team.
	 * 			| if {for (Worm worm: team)
	 * 			|		worm.getWorld() == this.getWorld() }
	 * 			| 	result == true
	 * 			| else
	 * 			| 	result == false
	 */
	public boolean canHaveAsTeam(ArrayList<Worm> team){
		for (Worm worm: team){
			if (! (canHaveAsWorm(worm)))
				return false;
		}
		return true;
	}
	
	/**
	 * Variable registering the list of worms that are member of this team.
	 */
	private ArrayList<Worm> listOfWorms;
	
	
	/**
	 * Returns whether this team has the given worm as one of its worms.
	 * 
	 * @return 	True if and only if this team has this worm as one of its worms.
	 *			| for (Worm worm: this.getWorms())
	 *			| 	if (team == teamToCheck)
	 *			|		result == true
	 *			| result == false
	 */
	public boolean hasAsTeam(Worm wormToCheck){
		for (Worm worm: this.getTeamMembers())
			if (worm == wormToCheck)
				return true;
		return false;
	}
	
	
	
	
	
	
	
	
	//1 OPMERKING.
	/**
	 * Returns the index of the current worm.
	 */
	@Basic
	public int getNbCurrentWorm(){
		return this.nbCurrentWorm;
	}
	
	/**
	 * Returns the worm associated with the index of the current worm of this team.
	 */
	@Basic
	public Worm getCurrentWorm(){
		if (this.getTeamMembers().size() == 0)
			return null;
		return this.listOfWorms.get(this.getNbCurrentWorm());
	}
	

	/**
	 * Sets the index of the current worm to the given index.
	 *
	 * @param 	numberWorm
	 * 			The index to set this team's current worm to.
	 * @post	The new index of the current worm of this team, if not empty, is equal to the given index.
	 * 			| new.getNbCurrentWorm() == indexWorm
	 * @throws	IllegalStateException
	 * 			The given index is larger than the size of the membershiplist of this team or negative.
	 * 			| ((indexWorm > this.getTeamMembers().size() -1) && (indexWorm < 0))
	 * 
	 */
	public void setNbCurrentWorm(int indexWorm) throws IllegalStateException{
		if ((indexWorm > this.getTeamMembers().size() -1) && (indexWorm < 0))
			throw new IllegalStateException("The given index is larger than the size of the membershiplist of this team.");
		this.nbCurrentWorm = indexWorm;
	}
	
	/**
	 * Variable registering the index of the worm whose turn it currently is in the game.
	 */
	private int nbCurrentWorm;
	
	
	
	
	
	
	
	//IN ORDE
	/**
	 * Add the given worm to this team.
	 * 
	 * @param 	worm
	 * 			The worm that should be added to this team.
	 * @effect	The given worm is added to the list of worms which are a member of this team.
	 * 			| new.getTeamMembers() == this.getTeamMembers() U ('union') worm
	 * @throws 	IllegalArgumentException
	 * 			The given worm is terminated.
	 * 			| (worm.isTerminated())
	 * @throws 	IllegalArgumentException
	 * 			The given worm does not belong to the same world as this team.
	 * 			| (! canHaveAsWorm(worm))
	 */
	public void addNewWorm(Worm worm) throws IllegalArgumentException, IllegalStateException{
		if (worm.isTerminated())
			throw new IllegalArgumentException("This worm is terminated. Cannot add worm to team.");
		if (! canHaveAsWorm(worm))
			throw new IllegalStateException("The given worm does not belong to the same world as this team.");
		this.listOfWorms.add(worm);
	}


	/**
	 * Remove the given worm, if it is part of this team, from this team.
	 * 
	 * @param 	worm
	 * 			The worm that should be removed from this team.
	 * @effect	The given worm, if is part of this team, is removed from the list of worms that are a member of this team.
	 * 			| new.getTeamMembers() == this.getTeamMembers() / ("without") worm
	 */
	/*Als je een worm removet die niet in dit team zit, is er geen probleem. Dan gebeurt er niets.*/
	public void removeWorm(Worm worm){
		this.listOfWorms.remove(worm);		
	}
	
	/**
	 * Checks whether the given worm's world is equal to the world of this team.
	 * 
	 * @param 	worm
	 * 			The worm to check.
	 * @return	True if and only if the given worm's world is equal to the world of this team and the worm is not null.
	 * 			| result == (worm != null) && (this.getWorld() == worm.getWorld())
	 */
	public boolean canHaveAsWorm(Worm worm){
		return (worm != null) && (this.getWorld() == worm.getWorld());
	}
	
	
	
	
	// IN ORDE
	/**
	 * Set the index of the current worm of this team so that it references the next worm in this team.
	 * 
	 * @effect	If the current worm was the last worm in the team, the index of the current worm of this team is set to zero.
	 * 			Otherwise, the index is equal to the previous index + 1.
	 * 			| if (this.getNbCurrentWorm() == this.getTeamMembers().size() - 1)
	 * 			|	new.getNbCurrentWorm() == 0
	 * 			| else if (this.getNbCurrentWorm() != this.getTeamMembers().size() )
	 * 			| 	new.getNbCurrentWorm() == this.getNbCurrentWorm() + 1
	 */
	public void nextWorm(){
		if (this.getNbCurrentWorm() == this.getTeamMembers().size() - 1)
			this.setNbCurrentWorm(0);
		else if (this.getNbCurrentWorm() != this.getTeamMembers().size() )
			this.setNbCurrentWorm(this.getNbCurrentWorm() + 1);
	}
}
