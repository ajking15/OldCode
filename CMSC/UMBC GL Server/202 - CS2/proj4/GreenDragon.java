/**
 * 
 */
package proj4;

/**
* File:    GreenDragon.java
* Project: CMSC 202 Project 4, Spring 2008
* @author  Chris Mai
* Date:    04/10/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. Dragon works fine
*/
public class GreenDragon extends Dragon{
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
	
	GreenDragon(int attackStrength, int defensiveStrength)
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
		return "Smaug";
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
	* Name: surpriseAttack
	* PreCondition:  none
	* PostCondition: boolean of wether or not GreenDragon ability happens
	*/
	public boolean surpriseAttack()
	{
		special = false;//boolean for use of overlapping abilities
		if(CMSC202Math.nextRandomInt(100) <= 5)
		{
			special = true;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	* Name: theFightIsOn
	* PreCondition:  none
	* PostCondition: attackScore
	*/
	public int theFightIsOn()
	{
		//checks for surpriseAttack
		if(surpriseAttack() == true)
			return theFightIsOn(CMSC202Math.nextRandomInt(attackStrength) + BONUS);
			//adds bonus if true
		
		return theFightIsOn(CMSC202Math.nextRandomInt(attackStrength));
		
	}
	
	/**
	* Name: toArms
	* PreCondition:  none
	* PostCondition: defensiveStrength
	*/
	public int toArms()
	{
		//checks for a fly evasion
		if(flyEvade() == true)
			return toArms(defensiveStrength);
		
		return toArms(CMSC202Math.nextRandomInt(defensiveStrength));
	}
}
