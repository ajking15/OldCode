package proj5;

/**
* File:    People.java
* Project: CMSC 202 Project 5, Spring 2008
* @author  Chris Mai
* Date:    05/13/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. Identity extends Comparable
*/

public class People implements Identity<People>{
	private int age;
	private String name;
	private String id;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public People(String name, int age, String id)
	{
		this.id = id;
		this.age = age;
		this.name = name;
	}
	
	/**
	* Name: 		 returnId
	* PreCondition:  none
	* PostCondition: string of id
	*/
	public String returnId()
	{
		return id;
	}
	
	/**
	* Name: 		 toString
	* PreCondition:  none
	* PostCondition: String of output
	*/
	public String toString()
	{
		return "Name: " + name + " Age: " + age + " ID: " + id;
	}

	/**
	* Name: 		 compareTo
	* PreCondition:  none
	* PostCondition: int of string comparison
	*/
	public int compareTo(People p) {
		// TODO Auto-generated method stub
		return id.compareTo(p.returnId());
	}
}
