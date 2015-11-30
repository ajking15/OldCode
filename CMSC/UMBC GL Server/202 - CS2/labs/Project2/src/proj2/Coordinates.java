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
 * @author Christopher Mai
 *
 */
public class Coordinates {
	private final int row;
	private final int column;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//method testing
		Coordinates coord = new Coordinates(4,5);
		
		System.out.println("Row is: " + coord.getRow());
		System.out.println("Column is: " + coord.getColumn());
		System.out.println("Class is: " + coord.getClass());
	}
	//Coordinates constructor
	public Coordinates(int row, int column)
	{
		this.row = row;
		this.column = column;
	}
	
	public int getRow()
	{
		return this.row;
	}

	public int getColumn()
	{
		return this.column;
	}
	
}
