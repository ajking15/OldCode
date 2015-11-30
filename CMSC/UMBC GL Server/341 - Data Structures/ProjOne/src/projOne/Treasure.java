/**
 * 
 */
package projOne;

/**
* File:    Treasure.java
* Project: CMSC 341 Project 1, Fall 2008
* @author  Chris Mai
* Date:    09/08/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. coordinates worth and a description
*/
public class Treasure implements Navigate<Treasure>{
	private int X;
	private int Y;
	private int VALUE;          //put these in
	private String DESCRIPTION; //a toString method
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	public Treasure(){
		X = 0;
		Y = 0;
		VALUE = 0;
		DESCRIPTION = "";
	}
	
	public Treasure(int x, int y){
		X = x;
		Y = y;
		VALUE = 0;
		DESCRIPTION = "";
	}
	
	public Treasure(int x, int y, int value, String description){
		X = x;
		Y = y;
		VALUE = value;
		DESCRIPTION = description;
	}

	@Override
	/**
	 * returns x coord
	 * @return int value for x
	 */
	public int getX() {
		return X;
	}

	@Override
	/**
	 * returns y coord
	 * @return int value for y
	 */
	public int getY() {
		return Y;
	}

	/**
	 * not really used in this project but implemented for comparable interface
	 * @return int based off of coords
	 */
	public int compareTo(Treasure t) {
		if(this.X == t.getX())
		{
			if(this.Y == t.getY())
			{
				return 0;
			}
		} 
			return -1;		
	}

	/**
	 * overwrites toString in object for a specific use
	 * @return description plus int value
	 */
	public String toString()
	{
		return DESCRIPTION + " Value: " + VALUE + "\n";
	}
	
}
