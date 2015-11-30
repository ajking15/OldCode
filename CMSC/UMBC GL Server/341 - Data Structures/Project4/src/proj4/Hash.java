package proj4;

/**
* File:    Hash.java
* Project: CMSC 341 Project 4, Fall 2008
* @author  Chris Mai
* Date:    11/23/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. K is a Board object
*   2. hash code is overwritten
*/

public class Hash<K>{
	
	private String winString = "X";
	private String lossString = "O";
	private K hashKey;
	private double goodness;
	private double tieRatio;
	private double wins;
	private double losses;
	private double ties;
	//come up with a good prime for hashing
	/**
	 * @param args
	 */
	

	Hash(K key){
		//hashKey is of type board
		hashKey = key;
		//these start as one as goodness will be a ratio
		wins = 1;
		losses = 1;
		ties = 1;
		goodness = 1;
		//tieRatio = ties/(wins + ties + losses);
		// makes ties a ratio of total games and when it hits a certain point
		//it overtakes goodness and returns a 1?
		//number 1 is neutral goodness
	}
	
	/**
	 * 
	 * @return generic K which is a board
	 */
	public K getKey(){
		//hashKey.hashCode();
		return hashKey;
	}
	
	/**
	 * 
	 * @param goodness the update status in board form
	 */
	public void updateGoodness(String goodness){
		//way to check win, loss, tie
		if(goodness.equals(winString))
		{
			wins++;
		} else { 
			if(goodness.equals(lossString))
			{
				losses++;
			} else {
				ties++;
				//maybe tie can count as a win and a loss?
			}
		}
	}
	//losing games are negative, winning games are positive > 1
	//random games and ties are 0
	/**
	 *  @return  a double which is the goodness of a board
	 */
	public double getGoodness(){
		//goodness = wins/losses;
		goodness = wins - losses;
		tieRatio = ties/(wins + ties + losses);
		if(tieRatio > .5)
		{
			return 0;
		}
		return goodness;
	}
	
	/**
	 * 
	 * @param good updates goodness via a double
	 */
	public void setGoodness(double good){
		goodness = good;
	}
	
	/**
	 * 
	 * @return returns goodness when you don't want it recalculated
	 */
	public double getSetGoodness()
	{
		return goodness;
	}

	/**
	 * @return returns hash code for the board which is generic
	 */
	public int hashCode(){
		//will call hashCode in board
		return hashKey.hashCode();
	}
	
	/**
	 * prints the board via the overwritten toString() method
	 */
	public void printBoard(){
		hashKey.toString();
	}
}
