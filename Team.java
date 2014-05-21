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
	
	
	
	
	//IN ORDE
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
	 * Variable registering the name of this worm.
	 */
	private String name;
	
	
	//NOG DOEN
	/**
	 * Checks whether the given string is a valid name for any team.
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
		// We willen dat de TeamNaam een unieke naam is.
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
	
	
	
	
	

	
	
	
	
	//Is het niet beter om met een getter en setter te werken? 
	//En dan in de documentatie vermelden dat enkel levende wormen worden teruggegeven?
	// public ArrayList <Worm> returnWormsAlive(){
	//	  return this.listOfWorms;
	//}
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
	 * Variable registering the list of worms that are member of this team.
	 */
	private ArrayList<Worm> listOfWorms;
	
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
	
	
	
	
	public int getNbCurrentWorm(){
		return this.nbCurrentWorm;
	}
	
	public void setNbCurrentWorm(int numberWorm){
		//Hier een checker bij zetten dat de wormenlijst van uw team niet leeg mag zijn!
		this.nbCurrentWorm = numberWorm;
	}
	
	public Worm getCurrentWorm(){
		return this.listOfWorms.get(this.getNbCurrentWorm());
	}
	
	private int nbCurrentWorm;
	
	
	//Postconditie formeel: +teken klopt niet echt denk ik.
	/**
	 * Add the given worm to this team.
	 * @param 	worm
	 * 			The worm that should be added to this team.
	 * @post	The given worm is added to the list of worms that are member of this team.
	 * 			| new.getTeamMembers() == this.getTeamMembers() + worm
	 * @throws 	IllegalStateException
	 * 			The given worm that should be added to this team is terminated.
	 * 			| (worm.isTerminated())
	 */
	public void addNewWorm(Worm worm) throws IllegalStateException, IllegalArgumentException{
		//VRAAG: moet dit erbij?
		if (worm.isTerminated())
			throw new IllegalStateException("This worm is terminated. Cannot add worm to team.");
		if (! canHaveAsWorm(worm))
			throw new IllegalStateException("This worm does not belong to the same world as this team.");
		this.listOfWorms.add(worm);
	}
	
	//Postconditie formeel: -teken klopt niet echt denk ik.
	/**
	 * Remove the given worm from this team.
	 * @param 	worm
	 * 			The worm that should be removed from this team.
	 * @post	The given worm is removed from the list of worms that are member of this team.
	 * 			| new.getTeamMembers() == this.getTeamMembers() - worm
	 */
	public void removeWorm(Worm worm){
		this.listOfWorms.remove(worm);		
	}
	
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
