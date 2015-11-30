/**
 * 
 */
package proj4;

/**
* File:    Creature.java
* Project: CMSC 202 Project 4, Spring 2008
* @author  Chris Mai
* Date:    04/10/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. creature has 100 health
*/
public abstract class Creature {
	public int health;
	private final int MAXHEALTH = 100;
	public final int SPECIALMULTIPLIER = 2;
	public boolean special;
	public final int BONUS = 20;
	private final int ZERO = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Creature creat = new Wizard(200, 3000);
		System.out.println("Health: " + creat.getHealth());
		creat.damage(50);
		System.out.println("Health: " + creat.getHealth());
		System.out.println("AttackStrength: " + creat.getAttackStrength());
		System.out.println("DefenseStrength: " + creat.getDefensiveStrength());
		System.out.println("formidableDefense: " + creat.formidableDefense());
		System.out.println("devastatingAttack: " + creat.devastatingAttack());
	}
	
	Creature()
	{
		health = MAXHEALTH;
		special = false;
	}
	
	/**
	* Name: devastatingAttack
	* PreCondition:  none
	* PostCondition: boolean of devastatingAttack
	*/
	public boolean devastatingAttack()
	{
		if(CMSC202Math.nextRandomInt(100) <= 10)
			return true;
		
		return false;
	}
	
	/**
	* Name: formidableDefense
	* PreCondition:  none
	* PostCondition: boolean of formidableDefense
	*/
	public boolean formidableDefense()
	{
		if(CMSC202Math.nextRandomInt(100) <= 8)
			return true;
		
		return false;
	}
	
	/**
	* Name: damage
	* PreCondition:  none
	* PostCondition: health is change by damage sent it
	*/
	public void damage(int damage)
	{
		//checks if health would go negative
		if(health - damage <= ZERO)
			health = 0;
		else
			health -= damage;
	}
	
	public abstract int getHealth();
	
	public abstract String getName();
	
	public abstract int getAttackStrength();
	
	public abstract int getDefensiveStrength();
	
	public abstract int theFightIsOn();
	
	public abstract int toArms();
}
