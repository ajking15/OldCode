/**
 * 
 */

package proj3;

/**
* File:    GumballDispenser.java
* Project: CMSC 202 Project 3, Spring 2008
* @author  Chris Mai
* Date:    04/04/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. linked list of type Gumball is correctly initalized
*/

import java.util.LinkedList;
/**
 * @author chrmai1
 *
 */
public class GumballDispenser {
	public LinkedList<Gumball> gumball;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		GumballDispenser disp = new GumballDispenser();
		Gumball gum = new Gumball(null);
		
		System.out.println("Empty? " + disp.empty());
		disp.addGumball(gum);
		System.out.println("Empty? " + disp.empty());
		disp.removeGumball();
		System.out.println("Empty? " + disp.empty());
		*/
	}

	public GumballDispenser()
	{
		gumball = new LinkedList<Gumball>();
	}
	
	/**
	* Name: addGumball
	* PreCondition:  gum is error free and linked list works fine
	* PostCondition: linked list has another gumball
	*/
	public void addGumball(Gumball gum)
	{
		gumball.addLast(gum);
	}
	
	/**
	* Name: removeGumball
	* PreCondition:  linked list has a gumball
	* PostCondition: first object in linked list is removed
	*/
	public Gumball removeGumball()
	{
		return gumball.removeFirst();
	}
	
	/**
	* Name: empty
	* PreCondition:  linked list exist
	* PostCondition: boolean status of linked list's emptiness
	*/
	public boolean empty()
	{
		return gumball.isEmpty();
	}
}
