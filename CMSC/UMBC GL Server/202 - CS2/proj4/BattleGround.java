/**
 * 
 */
package proj4;

/**
* File:    BattleGround.java
* Project: CMSC 202 Project 4, Spring 2008
* @author  Chris Mai
* Date:    04/10/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. creatures are of a type found in the game
*/
public class BattleGround {
	private Creature creature1;
	private Creature creature2;
	private boolean creature1AttackTurn;
	private final int ZERO = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Creature one = new GreenDragon(500,500);
		Creature two = new Siren(600,400);
		
		BattleGround fight = new BattleGround(one, two);
		
		while(one.getHealth() > 0 && two.getHealth() > 0)
		System.out.println(fight.skirmish());
	}
	
	BattleGround(Creature creature1, Creature creature2)
	{
		this.creature1 = creature1;
		this.creature2 = creature2;
		creature1AttackTurn = true;
	}
	
	/**
	* Name: skirmish
	* PreCondition:  none
	* PostCondition: string of battle results
	*/
	public String skirmish()
	{
		//ints to store attackers score, defenders score, and total damage
		int attackScore, defenseScore, damage;
		//string to record battle report
		String battleLog;
		//checks for creature1's attack turn
		if(creature1AttackTurn == true)
		{
			//gets attackScore and defenseScore
			attackScore = creature1.theFightIsOn();
			defenseScore = creature2.toArms();
			//checks if attackScore beats defenseScore
			if(attackScore > defenseScore)
			{
				damage = (attackScore - defenseScore) / 5;
			} else {
				damage = ZERO;
			}
			
			//changes defender's health
			creature2.damage(damage);
			
			//fills out battleLog
			battleLog = creature1.getName() + " has attacked " + creature2.getName()
						+ "\nAttacker's Score: " + attackScore
						+ "\nDefender's Score: " + defenseScore
						+ "\nDamage Taken: " + damage
						+ "\n";
			
			//shows change of turns for next skirmish
			creature1AttackTurn = false;
			
			return battleLog;
		} else {
			//gets attack score and defense score
			attackScore = creature2.theFightIsOn();
			defenseScore = creature1.toArms();
			
			//checks for damage
			if(attackScore > defenseScore)
			{
				damage = (attackScore - defenseScore) / 5;
			} else {
				damage = ZERO;
			}
			//alters health based on damage
			creature1.damage(damage);
			
			//fills out battleLog
			battleLog = creature2.getName() + " has attacked " + creature1.getName()
						+ "\nAttacker's Score: " + attackScore
						+ "\nDefender's Score: " + defenseScore
						+ "\nDamage Taken: " + damage
						+ "\n";
			
			//changes turn boolean
			creature1AttackTurn = true;
			
			return battleLog;
		}
		
	}
	
}
