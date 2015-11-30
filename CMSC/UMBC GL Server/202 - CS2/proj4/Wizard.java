/**
 * 
 */
package proj4;

/**
* File:    Wizard.java
* Project: CMSC 202 Project 4, Spring 2008
* @author  Chris Mai
* Date:    04/10/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. Base classes are working fine
*/
public class Wizard extends MagicalCreature{
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

	Wizard(int attackStrength, int defensiveStrength)
	{
		this.attackStrength = attackStrength;
		this.defensiveStrength = defensiveStrength;
	}

	/**
	* Name: getHealth
	* PreCondition:  none
	* PostCondition: health has been returned
	*/
	public int getHealth()
	{
		return health;
	}
	
	/**
	* Name: getName
	* PreCondition:  none
	* PostCondition: Name is returned
	*/
	public String getName()
	{
		return "Gandalf";
	}
	
	/**
	* Name: getAttackStrength
	* PreCondition:  initialized
	* PostCondition: base attack strength;
	*/
	public int getAttackStrength()
	{
		return attackStrength;
	}
	
	/**
	* Name: getDefensiveStrength
	* PreCondition:  initialized
	* PostCondition: base defensive strength
	*/
	public int getDefensiveStrength()
	{
		return defensiveStrength;
	}
	
	/**
	* Name: wizardBarrier
	* PreCondition:  none
	* PostCondition: boolean status of wizardBarrier activation
	*/
	public boolean wizardBarrier()
	{
		//checks for 6% chance of barrier
		if(CMSC202Math.nextRandomInt(100) <= 6)
			return true;
		
		return false;
	}

	/**
	* Name: theFightIsOn
	* PreCondition:  none
	* PostCondition: attack score
	*/
	public int theFightIsOn() {
		// TODO Auto-generated method stub
		//checks for application of magicalStrength available to MagicalCreatures
		if(magicalStrength() == true)
			return theFightIsOn(attackStrength);
		
		return theFightIsOn(CMSC202Math.nextRandomInt(attackStrength));
	}

	/**
	* Name: toArms
	* PreCondition:  none
	* PostCondition: defensiveScore
	*/
	public int toArms() {
		// TODO Auto-generated method stub
		special = false;
		//checks for wizard specific defense skill
		if(wizardBarrier() == true)
		{
			special = true;
			return toArms(defensiveStrength);
		} else {
		return toArms(CMSC202Math.nextRandomInt(defensiveStrength));
		}
	}
}
