package proj5;

/**
* File:    Cargo.java
* Project: CMSC 202 Project 5, Spring 2008
* @author  Chris Mai
* Date:    05/13/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. identity implements comparable
*/

public class Cargo implements Identity<Cargo>{
	private String id;
	private int weight;
	private int height;
	private int width;
	private int length;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public Cargo(String id, int weight, int height, int width, int length)
	{
		this.id = id;
		this.weight = weight;
		this.height = height;
		this.width = width;
		this.length = length;
	}
	
	/**
	* Name: 		 returnId
	* PreCondition:  id has a value
	* PostCondition: id
	*/
	public String returnId()
	{
		return id;
	}
	
	/**
	* Name: 		 toString
	* PreCondition:  none
	* PostCondition: none
	*/
	public String toString()
	{
		return id + " " + weight + "   " + height + " x " + width + " x " + length;
	}
	
	/**
	* Name: 		 compareTo
	* PreCondition:  none
	* PostCondition: int value of the compare to of id
	*/
	public int compareTo(Cargo c) {
		// TODO Auto-generated method stub
		return id.compareTo(c.returnId());
	}
}
