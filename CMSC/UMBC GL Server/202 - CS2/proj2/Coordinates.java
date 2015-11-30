/**
 * File: Coordinates.java
 * Project: CMSC 202 Project Project2
 * @author Christopher Mai
 * Date: Feb 26, 2008
 * Section: 0401
 * E-mail: chrmai1@umbc.edu
 */
package proj2;

/**
* File:    Coordinates.java
* Project: CMSC 202 Project 2, Spring 2008
* @author  Chris Mai
* Date:    03/09/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. row is a positive integer within the bounds on the array objects
*   2. column is a positive integer within the bounds on the array objects
*/
public class Coordinates {
	private final int row;
	private final int column;
	
	public Coordinates(int row, int column)
	{
		this.row = row;
		this.column = column;
	}
	
	/**
	 * Name:  getRow
	 * Precondition:  object has a row value
	 * PostCondition: row integer
	 */
	public int getRow()
	{
		return this.row;
	}

	/**
	 * Name:  getColumn
	 * Precondition:  object has a column value
	 * PostCondition: column integer
	 */
	public int getColumn()
	{
		return this.column;
	}
	
}
