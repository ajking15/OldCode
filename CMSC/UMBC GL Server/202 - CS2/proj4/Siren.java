/**
 * 
 */
package proj4;

/**
* File:    Siren.java
* Project: CMSC 202 Project 4, Spring 2008
* @author  Chris Mai
* Date:    04/10/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. Base classes are working fine
*/
public class Siren extends MagicalCreature{
	private int attackStrength;
	private int defensiveStrength;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Siren seaHag = new Siren(200, 300);
		System.out.println("AttackStrength: " + seaHag.getAttackStrength());
		System.out.println("DefenseStrength: " + seaHag.getDefensiveStrength());
		System.out.println("Health: " + seaHag.getHealth());
		System.out.println("Name: " + seaHag.getName());
		System.out.println("AttackScore: " + seaHag.theFightIsOn());
		System.out.println("DefenseScore: " + seaHag.toArms());
	}
	
	Siren(int attackStrength, int defensiveStrength)
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
	* PostCondition: name
	*/
	public String getName()
	{
		return "SeaHag";
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
	* Name: theFightIsOne
	* PreCondition:  none
	* PostCondition: offensiveScore
	*/
	public int theFightIsOn() {
		//checks for MagicalCreature specific ability
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
		return toArms(CMSC202Math.nextRandomInt(defensiveStrength));
	}
}
