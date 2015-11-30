/**
 * 
 */
package proj3;

/**
* File:    CandyBarDispenser.java
* Project: CMSC 202 Project 3, Spring 2008
* @author  Chris Mai
* Date:    04/04/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. empty linkedlist of type CandyBar is initialized
*/

import java.util.LinkedList;
/**
 * @author chrmai1
 *
 */
public class CandyBarDispenser {
	/**
	 * holds the queue(linked list) of 
	 * candybars nuts or otherwise.
	 * no limit on number of candybars. ie, must use a linked list
	 */
	public LinkedList<CandyBar> candyBar;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		CandyBar candy = new CandyBar(true);
		CandyBarDispenser disp = new CandyBarDispenser();
		System.out.println("Empty? " + disp.empty());
		disp.addCandy(candy);
		System.out.println("Empty? " + disp.empty());
		disp.removeCandy();
		System.out.println("Empty? " + disp.empty());
		*/
	}
	
	public CandyBarDispenser()
	{
		candyBar = new LinkedList<CandyBar>();
	}
	
	/**
	* Name: addCandy
	* PreCondition:  linked list is created 
	* PostCondition: cand is added
	*/
	public void addCandy(CandyBar candy)
	{
		candyBar.addLast(candy);
	}
	
	
	/**
	* Name:  removeCandy 
	* PreCondition:  linked list is not empty
	* PostCondition: first element in linked list is returned
	*/
	public CandyBar removeCandy()
	{
		return candyBar.removeFirst();
	}
	
	/**
	* Name: empty
	* PreCondition:  linked list exists  
	* PostCondition: boolean status of linked list
	*/
	public boolean empty()
	{
		return candyBar.isEmpty();
	}
}
