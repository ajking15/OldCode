/**
 * 
 */
package proj4;

/**
* File:    PoisonFrog.java
* Project: CMSC 202 Project 4, Spring 2008
* @author  Chris Mai
* Date:    04/10/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. Frog is deadly
*/
public class PoisonFrog extends OtherCreature{
	private int attackStrength;
	private int defensiveStrength;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PoisonFrog toad = new PoisonFrog(200, 400);
		System.out.println("AttackStrength: " + toad.getAttackStrength());
		System.out.println("DefenseStrength: " + toad.getDefensiveStrength());
		System.out.println("Health: " + toad.getHealth());
		System.out.println("Name: " + toad.getName());
		System.out.println("AttackScore: " + toad.theFightIsOn());
		System.out.println("DefenseScore: " + toad.toArms());
	}
	
	PoisonFrog(int attackStrength, int defensiveStrength)
	{
		this.attackStrength = attackStrength;
		this.defensiveStrength = defensiveStrength;
	}
	
	/**
	* Name: getHealth
	* PreCondition:  none
	* PostCondition: health
	*/
	public int getHealth()
	{
		return health;
	}
	
	/**
	* Name: getName
	* PreCondition:  none
	* PostCondition: string name
	*/
	public String getName()
	{
		return "DoomToad";
	}
	
	/**
	* Name: getAttackStrength
	* PreCondition:  none
	* PostCondition: attackStrength
	*/
	public int getAttackStrength()
	{
		return attackStrength;
	}
	
	/**
	* Name: getDefensiveStrength
	* PreCondition:  none
	* PostCondition: defensiveStrength
	*/
	public int getDefensiveStrength()
	{
		return defensiveStrength;
	}
	
	/**
	* Name: agileDefense
	* PreCondition:  none
	* PostCondition: boolean for agile defense
	*/
	public boolean agileDefense()
	{
		//percent chance for agile defense
		if(CMSC202Math.nextRandomInt(100) <= 8)
			return true;
		
		return false;
	}
	
	/**
	* Name: theFightIsOn
	* PreCondition:  none
	* PostCondition: attackScore
	*/
	public int theFightIsOn() {
		//sends to random ints to theFightIsOn for frogs special ability
		return theFightIsOn(CMSC202Math.nextRandomInt(attackStrength) 
				+ CMSC202Math.nextRandomInt(attackStrength));
	}
	
	/**
	* Name: toArms
	* PreCondition:  none
	* PostCondition: defensiveScore
	*/
	public int toArms() {
		//checks if PoisonFrog specific ability is true
		if(agileDefense() == true)
			return toArms(CMSC202Math.nextRandomInt(defensiveStrength) + BONUS);
		return toArms(CMSC202Math.nextRandomInt(defensiveStrength));
	}
}
