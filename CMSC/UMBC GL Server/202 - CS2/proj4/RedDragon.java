package proj4;

/**
* File:    RedDragon.java
* Project: CMSC 202 Project 4, Spring 2008
* @author  Chris Mai
* Date:    04/10/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. Base classes are working fine
*/
public class RedDragon extends Dragon{
	private int attackStrength;
	private int defensiveStrength; //these will be base stats unchanged
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RedDragon red = new RedDragon(600, 600);
		System.out.println("AttackStrength: " + red.getAttackStrength());
		System.out.println("DefenseStrength: " + red.getDefensiveStrength());
		System.out.println("Health: " + red.getHealth());
		System.out.println("Name: " + red.getName());
		System.out.println("AttackScore: " + red.theFightIsOn());
		System.out.println("DefenseScore: " + red.toArms());
	}
	
	RedDragon(int attackStrength, int defensiveStrength)
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
		return "Bahamut";
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
	* Name: intenseFlames
	* PreCondition:  none
	* PostCondition: boolean of intenseFlames
	*/
	private boolean intenseFlames()
	{
		special = false;//boolean for specific power
		if(CMSC202Math.nextRandomInt(100) <= 7)
		{
			special = true;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	* Name: toArms
	* PreCondition:  none
	* PostCondition: attackScore
	*/
	public int theFightIsOn() {
		//checks for RedDragon specific ability
		if(intenseFlames() == true)
			return theFightIsOn(attackStrength);
		
		return theFightIsOn(CMSC202Math.nextRandomInt(attackStrength));
	}
	
	/**
	* Name: toArms
	* PreCondition:  none
	* PostCondition: defensiveScore
	*/
	public int toArms() {
		//checks for Dragon specific ability
		if(flyEvade() == true)
			return toArms(defensiveStrength);
		
		return toArms(CMSC202Math.nextRandomInt(defensiveStrength));
	}
}
