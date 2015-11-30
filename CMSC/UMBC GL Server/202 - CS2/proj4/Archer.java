/**
 * 
 */
package proj4;

/**
* File:    Archer.java
* Project: CMSC 202 Project 4, Spring 2008
* @author  Chris Mai
* Date:    04/10/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. Base classes are working fine
*/
public class Archer extends OtherCreature{
	private int attackStrength;
	private int defensiveStrength;
	private int FAIL = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Archer arch = new Archer(300, 200);
		System.out.println("AttackStrength: " + arch.getAttackStrength());
		System.out.println("DefenseStrength: " + arch.getDefensiveStrength());
		System.out.println("Health: " + arch.getHealth());
		System.out.println("Name: " + arch.getName());
		System.out.println("AttackScore: " + arch.theFightIsOn());
		System.out.println("DefenseScore: " + arch.toArms());
	}
	
	Archer(int attackStrength, int defensiveStrength)
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
	* PostCondition: string Name
	*/
	public String getName()
	{
		return "Bard";
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
	* Name: epicFail
	* PreCondition:  none
	* PostCondition: boolean of missing shot
	*/
	public boolean epicFail()
	{
		if(CMSC202Math.nextRandomInt(100) <= 15)
			return true;
		
		return false;
	}
	
	/**
	* Name: failMoreEpicThanLast
	* PreCondition:  none
	* PostCondition: boolean of wether or not archer fails at defense
	*/
	public boolean failMoreEpicThanLast()
	{
		//checks probablility
		if(CMSC202Math.nextRandomInt(100) <= 5)
			return true;
		
		return false;
	}
	
	/**
	* Name: theFightIsOn
	* PreCondition:  none
	* PostCondition: attackScore
	*/
	public int theFightIsOn() {
		//checks for missed shot
		if(epicFail() == true)
			return FAIL;
		
		return theFightIsOn(CMSC202Math.nextRandomInt(attackStrength));
	}
	
	/**
	* Name: toArms
	* PreCondition:  none
	* PostCondition: defensiveScore
	*/
	public int toArms() {
		//checks for failed defense
		if(failMoreEpicThanLast() == true)
			return FAIL;
		
		return toArms(CMSC202Math.nextRandomInt(defensiveStrength));
	}
}
