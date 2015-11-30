package proj5;

/**
* File:    IllegalCommandException.java
* Project: CMSC 202 Project 5, Spring 2008
* @author  Chris Mai
* Date:    05/13/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. none
*/

public class IllegalCommandException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public IllegalCommandException()
	{
		message = "Command entered is invalid";
	}
	
	
	public IllegalCommandException(String message)
	{
		this.message = message;
	}
	
	/**
	* Name: 		 getMessage
	* PreCondition:  there is a message
	* PostCondition: String of message
	*/
	public String getMessage()
	{
		return message;
	}
}
