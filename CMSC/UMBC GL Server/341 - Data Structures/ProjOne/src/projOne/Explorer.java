/**
 * 
 */
package projOne;

/**
* File:    Explorer.java
* Project: CMSC 341 Project 1, Fall 2008
* @author  Chris Mai
* Date:    09/08/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. has coordinates, consistent damage, unlimited health, and bag space
*/
public class Explorer implements Navigate{
	private int X;
	private int Y;
	private final int FORCE = 10;
	private int DAMAGE;
	private String INVENTORY;
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	public Explorer()
	{
		this.X = 0;
		this.Y = 0;
		this.INVENTORY = "";
		this.DAMAGE = 0;
	}
	
	public int getX() {
		return X;
	}
	public void setX(int x) {
		X = x;
	}
	public int getY() {
		return Y;
	}
	public void setY(int y) {
		Y = y;
	}
	
	/**
	 * returns a compiled inventory list of treasures gathered
	 * @return string of inventory
	 */
	public String getInventory()
	{
		return INVENTORY;
	}
	
	/**
	 * adds a new treasure to the inventory
	 * @param newTreasure description plus a value in a string
	 */
	public void addToInventory(String newTreasure)
	{
		INVENTORY += (newTreasure);
	}
	
	/**
	 * increases damage counter for the invincible explorer
	 * @param damage int value of damage dealt by a monster
	 */
	public void takeDamage(int damage)
	{
		DAMAGE += damage;
	}
	
	public int getForce()
	{
		return FORCE;
	}
	
	public int getDamage()
	{
		return DAMAGE;
	}
	
	/**
	 * only compareTo really used. compares objects of type navigate to include
	 * monsters and treasures
	 * @param n for use in this program will either be a treasure or monster
	 * @return an int which tells you whether or not locations are the same
	 */
	public int compareTo(Navigate n) {
		if(X == n.getX())
		{
			if(Y == n.getY())
			{
				return 0;
			}
			return 1;
		}
		return -1;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * here because it has to be to make compiler happy
	 */
	
	
}
