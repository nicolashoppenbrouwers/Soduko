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


// KLASSEINVARIANT DYNAMISCHE BINDING!!!
/**
 * A help class of teams for worms, involving a name, a membershiplist and an index which contains 
 * 		whose turn it currently is.
 * 
 * @invar 	The name of each Team must be a valid name for a Team.
 * 			| isValidName(this.getName())
 *
 * @invar	...
 * @author 	Nicolas Hoppenbrouwers
 * 			Bram Lust
 * @version 2.0
 *
 */

// Moet een team ook kunnen geterminate worden op het einde van elk spel?
// Het spel crasht wanneer je een team wilt toevoegen en independent heeft geen wormen

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
	 * 			| new.getTeamMembers() == null;
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
		if ((world != null) && (!world.isValidAmountOfTeams()) )
			throw new IllegalStateException("This world already has 10 teams!");
		this.world = world;
	}
	
	//NOG DOEN
	public void unsetWorld(){
		if (this.hasWorld()){
			World formerWorld = this.getWorld();
			this.setWorld(null);
			formerWorld.removeTeam(this);
		}
	}
	
	/**
	 * Check whether this team has a world.
	 * @return	True if and only if the world of this team is effective.
	 * 			| (this.getWorld() != null)
	 */
	//@Raw?
	public boolean hasWorld(){
		return (this.getWorld() != null);
	}
	
	/**
	 * Variable registering the world of this team.
	 */
	private World world;
	
	
	
	
	//SETNAME
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
	
	//NOG DOEN
	/**
	 * Checks whether the given string is a valid name for any team.
	 * 
	 * @param 	name
	 * 			The name which has to be checked. 
	 * @return	True if and only if the team's name meets the following requirements
	 * 			1) at least two characters long
	 * 			2) start with an uppercase letter
	 * 			3) only use letters
	 * 			| ((name.length() < 2)
	 * 			|  && (Character.isLetter(name.charAt(0))) || !(Character.isUpperCase(name.charAt(0)))
	 * 			|  && (for c in name:
	 * 			|      		c.isLetter(c) ))
	 */
	public static boolean isValidName(World world, String teamName){
		/* We willen dat de TeamNaam een unieke naam is. */
		for (Team team: world.getTeams() ){
			if (team.getName().equals(teamName))
				return false;
		}
		if (teamName.length() < 2)
			return false;
		//OPMERKING: als je deze if na de for-lus zet, dan je moet enkel nog checken op Uppercase.
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
		return this.listOfWorms.get(this.getNbCurrentWorm());
	}
	
	//MISSCHIEN TOCH NIET DEZE EXCEPTION THROWEN WANT DAN GA JE UW CONSTRUCTOR NIET KUNNEN GEBRUIKEN.
	//Documentatie dan wel aanpassen.
	/**
	 * Sets the index of the current worm to the given index.
	 *
	 * @param 	numberWorm
	 * 			The index to set this team's current worm to.
	 * @post	The new index of the current worm of this team, if not empty, is equal to the given index.
	 * 			| new.getNbCurrentWorm() == indexWorm
	 * @throws	IllegalStateException
	 * 			The team is empty.
	 * 			| (this.getTeamMembers().size() == 0)
	 * 
	 */
	public void setNbCurrentWorm(int indexWorm) throws IllegalStateException{
//		if (this.getTeamMembers().size() == 0)
//			throw new IllegalStateException("Team is empty.");
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
	 * @post	The given worm is added to the list of worms which are a member of this team.
	 * 			| new.getTeamMembers() == this.getTeamMembers() U ('union') worm
	 * @throws 	IllegalArgumentException
	 * 			The given worm is terminated.
	 * 			| (worm.isTerminated())
	 * @throws 	IllegalArgumentException
	 * 			The given worm does not belong to the same world as this team.
	 * 			| (! canHaveAsWorm(worm))
	 */
	public void addNewWorm(Worm worm) throws IllegalArgumentException{
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
	 * @post	The given worm, if is part of this team, is removed from the list of worms that are a member of this team.
	 * 			| new.getTeamMembers() == this.getTeamMembers() / ("without") worm
	 */
	/*Als je een worm removet die niet in dit team zit, is er geen probleem. Dan gebeurt er niets.*/
	public void removeWorm(Worm worm){
		this.listOfWorms.remove(worm);		
	}
	
	/**
	 * Checks whether the given worm's world is equal to the world of this team.
	 * @param 	worm
	 * 			The worm to check.
	 * @return	True if and only if the given worm's world is equal to the world of this team.
	 * 			| result == (this.getWorld() == worm.getWorld())
	 */
	public boolean canHaveAsWorm(Worm worm){
		return (this.getWorld() == worm.getWorld());
	}
	
	
	public void nextWorm(){
		if (this.getNbCurrentWorm() == this.getTeamMembers().size() - 1)
			this.setNbCurrentWorm(0);
		else if (this.getNbCurrentWorm() != this.getTeamMembers().size() )
			this.setNbCurrentWorm(this.getNbCurrentWorm() + 1);
	}
}
