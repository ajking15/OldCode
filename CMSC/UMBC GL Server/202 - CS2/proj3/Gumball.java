/**
 * 
 */
package proj3;

/**
* File:    Gumball.java
* Project: CMSC 202 Project 3, Spring 2008
* @author  Chris Mai
* Date:    04/04/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. Gumball has a color
*/

import java.awt.color.*;
/**
 * @author chrmai1
 *
 */
public class Gumball {
	private java.awt.Color color;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		Gumball gum = new Gumball(null);
		System.out.println("color: " + gum.getColor());
		*/
	}
	
	Gumball(java.awt.Color color) 
	{
		this.color = color;
	}
	
	/**
	* Name: getColor
	* PreCondition:  color has a color
	* PostCondition: color has been returned
	*/
	public java.awt.Color getColor()
	{
		return color;
	}
	
}
