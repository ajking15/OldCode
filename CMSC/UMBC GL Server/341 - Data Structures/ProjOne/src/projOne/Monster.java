package projOne;

/**
* File:    Monster.java
* Project: CMSC 341 Project 1, Fall 2008
* @author  Chris Mai
* Date:    09/08/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. coordinates, health, damage, description
*/
public class Monster implements Navigate<Monster>{
	private int X;
	private int Y;
	private int STRENGTH; //health
	private int FORCE;     //damage
	private String DESCRIPTION;
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	
	public Monster(){
		X = 0;
		Y = 0;
		STRENGTH = 0; 
		FORCE = 0;   
		DESCRIPTION = "";		
	}
	
	public Monster(int x, int y){
		X = x;
		Y = y;
		STRENGTH = 0; 
		FORCE = 0;   
		DESCRIPTION = "";
	}
	
	public Monster(int x, int y, int strength, int force, String description){
		this.X = x;
		this.Y = y;
		this.STRENGTH = strength;
		this.FORCE = force;
		this.DESCRIPTION = description;
	}

	/**
	 * returns x coordinate
	 * @return int value for x coordinate
	 */
	public int getX() {
		return X;
	}

	/**
	 * returns y coordinate
	 * @return int value for y
	 */
	public int getY() {
		return Y;
	}

	/**
	 * overrides objects toString and returns
	 * a description of the monster and it's health
	 * @return string with description and health
	 */
	public String toString(){
		return DESCRIPTION + " Strength: " + STRENGTH + "\n";
	}
	
	/**
	 * a function designed to return monsters current health
	 * @return the monsters health
	 */
	public int getStrength(){
		return STRENGTH;
	}
	
	/**
	 * returns damage ability
	 * @return int for damage power
	 */
	public int getForce(){
		return FORCE;
	}
	
	/**
	 * reduces monsters current health by int sent in 
	 * @param damage health the monster loses
	 */
	public void takeDamage(int damage){
		STRENGTH -= damage;
	}
	
	/**
	 * returns just the description of the monster
	 * @return string description
	 */
	public String getDescription(){
		return DESCRIPTION;
	}
	
	/**
	 * not really used but there for the comparable implementation in the project
	 * @return int based off comparison of coordinates
	 */
	public int compareTo(Monster m) {
		if(this.X == m.getX())
		{
			if(this.Y == m.getY())
			{
				return 0;
			}
		} 
			return -1;	
	}


}
