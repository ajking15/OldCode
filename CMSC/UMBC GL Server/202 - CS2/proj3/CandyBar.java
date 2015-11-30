/**
 * 
 */
package proj3;

/**
* File:    CandyBar.java
* Project: CMSC 202 Project 3, Spring 2008
* @author  Chris Mai
* Date:    04/04/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. candy bar either has nots or not
*/

/**
 * @author chrmai1
 *
 */
public class CandyBar {
	/**
	 * 
	 */
	
	private boolean hasNuts;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		CandyBar candy = new CandyBar(true);
		System.out.println(candy.toString());
		*/
	}
	
	public CandyBar(boolean hasNuts)
	{
		this.hasNuts = hasNuts;
	}
	
	/**
	* Name: toString
	* PreCondition:  hasNuts has a boolean value
	* PostCondition: a string is returned
	*/
	public java.lang.String toString()
	{
		if(hasNuts = true)
		{
		return "Candy Bar with Nuts";
		} else{
			return "Candy Bar without Nuts";
		}
	}
	
}
