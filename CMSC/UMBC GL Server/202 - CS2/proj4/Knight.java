package proj4;

/**
* File:    Knight.java
* Project: CMSC 202 Project 4, Spring 2008
* @author  Chris Mai
* Date:    04/10/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. has an attack/defensiveStrength
*   2. health of 100
*/

public class Knight extends OtherCreature{
	private int attackStrength;
	private int defensiveStrength;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Wizard gandalf = new Wizard(600, 400);
		System.out.println("AttackStrength: " + gandalf.getAttackStrength());
		System.out.println("DefenseStrength: " + gandalf.getDefensiveStrength());
		System.out.println("Health: " + gandalf.getHealth());
		System.out.println("Name: " + gandalf.getName());
		System.out.println("AttackScore: " + gandalf.theFightIsOn());
		System.out.println("DefenseScore: " + gandalf.toArms());
	}
	
	Knight(int attackStrength, int defensiveStrength)
	{
		this.attackStrength = attackStrength;
		this.defensiveStrength = defensiveStrength;
	}
	
	/**
	* Name: getHealth
	* PreCondition:  none
	* PostCondition: int health
	*/
	public int getHealth()
	{
		return health;
	}
	
	/**
	* Name: getName
	* PreCondition:  none
	* PostCondition: name
	*/
	public String getName()
	{
		return "King Arthur";
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
	* Name: theFightIsOn
	* PreCondition:  none
	* PostCondition: attackScore
	*/
	public int theFightIsOn() {
		// TODO Auto-generated method stub
		return theFightIsOn(CMSC202Math.nextRandomInt(attackStrength));
	}

	/**
	* Name: toArms
	* PreCondition:  none
	* PostCondition: defensiveStrength
	*/
	public int toArms() {
		// TODO Auto-generated method stub
		return toArms(CMSC202Math.nextRandomInt(defensiveStrength));
	}
}
