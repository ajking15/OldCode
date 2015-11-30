package proj5;

/**
* File:    IllegalCommandLineArgumentsException.java
* Project: CMSC 202 Project 5, Spring 2008
* @author  Chris Mai
* Date:    05/13/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. extends Exception so is exceptional
*/

public class IllegalCommandLineArgumentsException extends Exception{
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
	
	public IllegalCommandLineArgumentsException()
	{
		message = "Command Line argument invalid";
	}
	
	public IllegalCommandLineArgumentsException(String message)
	{
		this.message = message;
	}
	
	/**
	* Name: 		 getMessage
	* PreCondition:  has a message
	* PostCondition: String message
	*/
	public String getMessage()
	{
		return message;
	}
}
