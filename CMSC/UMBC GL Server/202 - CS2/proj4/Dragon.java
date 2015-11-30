/**
 * 
 */
package proj4;
	
/**
* File:    Dragon.java
* Project: CMSC 202 Project 4, Spring 2008
* @author  Chris Mai
* Date:    04/10/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. creature has an attack/defensiveScore
*/
public abstract class Dragon extends Creature{
	private int attackScore;
	private int defensiveScore;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dragon drag = new RedDragon(200, 3000);
		System.out.println("AttackStrength: " + drag.getAttackStrength());
		System.out.println("DefenseStrength: " + drag.getDefensiveStrength());
		System.out.println("AttackScore: " + drag.theFightIsOn(drag.getAttackStrength()));
		System.out.println("DefenseScore: " + drag.toArms(drag.getDefensiveStrength()));
		System.out.println("flyEvade: " + drag.flyEvade());
	}
	
	Dragon()
	{
		super();
		attackScore = 0;
		defensiveScore = 0;
	}
	
	/**
	* Name: flyEvade
	* PreCondition:  none
	* PostCondition: boolean of validity
	*/
	public boolean flyEvade()
	{
		//checks probability
		if(CMSC202Math.nextRandomInt(100) <= 7)
			return true;
		
		return false;
	}
	
	/**
	* Name: theFightIsOn
	* PreCondition:  none
	* PostCondition: attackScore
	*/
	public int theFightIsOn(int attacker)
	{
		//stores raw attackScore
		attackScore = attacker;
		
		//checks for devastatingAttack
		if(devastatingAttack() == true)
			attackScore *= SPECIALMULTIPLIER;
		
		return attackScore;
	}
	
	/**
	* Name: toArms
	* PreCondition:  none
	* PostCondition: defensiveStrength
	*/
	public int toArms(int defender)
	{	
		//stores raw defensiveScore
		defensiveScore = defender;
		
		//checks for formidableDefense
		if(formidableDefense() == true)
			defensiveScore *= SPECIALMULTIPLIER;
		
		return defensiveScore;
	}
}
