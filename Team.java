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
 * A help class of teams for worms, involving a name and a membershiplist.
 * 
 * @invar	...
 * @invar	...
 * @author 	Nicolas Hoppenbrouwers:	Bachelor Ingenieurswetenschappen Computerwetenschappen-Werktuigkunde
 * 			Bram Lust: Bachelor Ingenieurswetenschappen Computerwetenschappen-Elektrotechniek
 * 			We didn't work with Git.
 * @version 1.0
 *
 */

// Moet een team ook kunnen geterminate worden op het einde van elk spel?

public class Team {
	
	/**
	 * Initialize this new team with the given name.
	 * 
	 * @param 	name
	 * 			The name of this new team.
	 * @post	The name of this new team is equal to the given name.
	 * 			| new.getName() == name
	 * @post	The membership list of this new team is initialized as an empty array.
	 * 			| new.getTeamMembers() == null;
	 */
	public Team(World world, String name){
		this.setWorld(world);
		this.setName(name);
		//		 Deze lijn code is vervangen door een setter.
		//OUD:	 this.listOfWorms = new ArrayList<Worm>();
		//VRAAG: IS DIT WEL DE JUISTE MANIER OM EEN LEGE LIJST IN TE VOEREN? IK VIND NIET METEEN DE JUISTE MANIER!
		this.listOfWorms = new ArrayList<Worm>();
		//OUD: this.setTeamMembers(null)
		//ik heb de vorige versie terug gezet want ik denk dat in de oude een foutje zit
		//bij de this.setTeamMembers(null) sla je
		//null op in de plaats van een lege lijst.
	}
	
	//Eventueel is hier ook nog de mogelijkheid om nog een andere constructor te definiëren, 
	// een waarbij je meteen ook een lijst van wormen kan meegeven als lijst van teamleden, maar
	// dit is misschien overbodig.
	/**
	 * Initialize this new team with the given name and list of members (Worms).
	 * @param 	name
	 * 			The name of this new team.
	 * @param 	team
	 * 			The Worms that this team should contain as members.
	 * @post	The name of this new team is equal to the given name.
	 * 			| new.getName() == name
	 * @post	The membership list of this new team is equal to the given list of worms.
	 * 			| new.getTeamMembers() == team
	 */
	public Team(World world, String name, ArrayList<Worm> team){
		this.setWorld(world);
		this.setName(name);
		this.setTeamMembers(team);
	}
	
	
	public boolean isTerminated(){
		return (! this.hasWorld());
	}
	
	public void terminate(){
		this.unsetWorld();
	}
	
	
		
	/**
	 * Returns the name of this team.
	 */
	@Basic
	public String getName(){
		return this.name;
	}
	
	/**
	 * Sets the name of this team to the given team name.
	 */
	@Basic
	public void setName(String teamName) throws IllegalArgumentException{
		if (!isValidName(this.getWorld(), teamName))
			throw new IllegalArgumentException("Invalid team name!");
		this.name = teamName;
	}
	
	/**
	 * Variable registering the name of this worm.
	 */
	private String name;
	
	
	
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
	
	
	
	
	
	public World getWorld(){
		return this.world;
	}
	
	public void setWorld(World world){
		//De eerste voorwaarde moet er bij anders krijg je een NullPointerException op de tweede voorwaarde.
		if ((world != null) && (!world.isValidAmountOfTeams()) )
			throw new IllegalStateException("This world already has 10 teams!");
		this.world = world;
	}
	
	public void unsetWorld(){
		if (this.hasWorld()){
			World formerWorld = this.getWorld();
			this.setWorld(null);
			formerWorld.removeTeam(this);
		}
	}
	
	public boolean hasWorld(){
		return (this.getWorld() != null);
	}
	
	private World world;
	
	
	
	
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
	 */
	@Basic
	public void setTeamMembers(ArrayList<Worm> team){
		this.listOfWorms = team;
	}
	
	/**
	 * Variable registering the list of worms that are member of this team.
	 */
	private ArrayList<Worm> listOfWorms;
	
	
	
	
	
	
	
	
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
	public void addNewWorm(Worm worm) throws IllegalStateException{
		//VRAAG: moet dit erbij?
		if (worm.isTerminated())
			throw new IllegalStateException("This worm is terminated. Cannot add worm to team.");
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
}
