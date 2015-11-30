/**
 * 
 */
package proj4;

/**
* File:    OtherCreature.java
* Project: CMSC 202 Project 4, Spring 2008
* @author  Chris Mai
* Date:    04/10/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. initialized attack/defensiveScore
*/
public abstract class OtherCreature extends Creature{
	private int attackScore;
	private int defensiveScore;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OtherCreature creat = new Archer(20, 30);
		System.out.println("AttackStrength: " + creat.getAttackStrength());
		System.out.println("DefenseStrength: " + creat.getDefensiveStrength());
		System.out.println("AttackScore: " + creat.theFightIsOn(creat.getAttackStrength()));
		System.out.println("DefenseScore: " + creat.toArms(creat.getDefensiveStrength()));
	}

	OtherCreature()
	{
		super();
		attackScore = 0;
		defensiveScore = 0;
	}
	
	/**
	* Name: theFightIsOn
	* PreCondition:  none
	* PostCondition: attackScore
	*/
	public int theFightIsOn(int attacker)
	{
		//stores attackScore
		attackScore = attacker;
		//checks for a devastatingAttack
		if(devastatingAttack() == true)
			attackScore *= SPECIALMULTIPLIER;
		
		return attackScore;
	}
	
	/**
	* Name: toArms
	* PreCondition:  none
	* PostCondition: defensiveScore
	*/
	public int toArms(int defender)
	{
		//stores defensiveScore
		defensiveScore = defender;
		//checks for a formidable defense
		if(formidableDefense() == true)
			defensiveScore *= SPECIALMULTIPLIER;
		
		return defensiveScore;
	}
	
}
