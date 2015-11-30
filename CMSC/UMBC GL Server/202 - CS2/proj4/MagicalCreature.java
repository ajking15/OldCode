/**
 * 
 */
package proj4;

/**
* File:    MagicalCreature.java
* Project: CMSC 202 Project 4, Spring 2008
* @author  Chris Mai
* Date:    04/10/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. has attack/defensiveScore
*/
public abstract class MagicalCreature extends Creature{
	private int attackScore;
	private int defensiveScore;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MagicalCreature creat = new Wizard(200, 3000);
		System.out.println("AttackStrength: " + creat.getAttackStrength());
		System.out.println("DefenseStrength: " + creat.getDefensiveStrength());
		System.out.println("AttackScore: " + creat.theFightIsOn(creat.getAttackStrength()));
		System.out.println("DefenseScore: " + creat.toArms(creat.getDefensiveStrength()));
	}
	
	/**
	* Name: magicalStrength
	* PreCondition:  none
	* PostCondition: boolean of MagicalCreature ability
	*/
	public boolean magicalStrength()
	{
		if(CMSC202Math.nextRandomInt(100) <= 7)
			return true;
		
		return false;
	}
	
	/**
	* Name: cleverDense
	* PreCondition:  none
	* PostCondition: boolean of cleverDefense
	*/
	public boolean cleverDefense()
	{
		if(CMSC202Math.nextRandomInt(100) <= 8)
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
		//checks for a devastating attack
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
		//stores raw defensiveScore
		defensiveScore = defender;
		
		//checks if a derived specific ability is used
		if(special == false)
			if(cleverDefense() == true)
				defensiveScore += BONUS;//if  true adds bonus
		
		//checks for a formidableDefense
		if(formidableDefense() == true)
			defensiveScore *= SPECIALMULTIPLIER;
		
		return defensiveScore;
	}
}
