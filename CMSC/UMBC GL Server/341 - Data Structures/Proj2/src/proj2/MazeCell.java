package proj2;

/**
* File:    MazeCell.java
* Project: CMSC 341 Project 2, Fall 2008
* @author  Chris Mai
* Date:    09/029/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. none
*/


public class MazeCell {
	private boolean visited;
	private String  type;
	private int     X;
	private int     Y;
	private int     prevX;
	private int     prevY;
	
	//records type of spot
	MazeCell(String type, int x, int y){
		visited = false;
		this.type = type;
		this.X = x;
		this.Y = y;
		prevX = 0;
		prevY = 0;
	}
	
	/**
	 * 
	 * @return String with symbol description for maze
	 */
	public String getType(){
		return type.trim();
	}
	
	/**
	 * 
	 * @return X coordinate of this cell
	 */
	public int getX(){
		return X;
	}
	
	/**
	 * 
	 * @return Y coordinate of this cell
	 */
	public int getY(){
		return Y;
	}
	
	/**
	 * 
	 * @param type used to change the representation in the maze
	 */
	public void setType(String type){
		this.type = type;
	}
	
	/**
	 * 
	 * @return boolean value of wether or not the cell has been through the agenda
	 */
	public boolean getVisited(){
		return visited;
	}
	
	/**
	 *  changes visited status to true
	 */
	public void visit(){
		visited = true;
	}
	
	/**
	 * used when placing cells in the stack. records which cell put you in the stack
	 * so that it is possible to remember the path after determining if the solution 
	 * is possible
	 * @param x int value of previous cell
	 * @param y int value of previous cell
	 */
	public void setPrev(int x, int y){
		prevX = x;
		prevY = y;
	}
	
	/**
	 * 
	 * @return x coord of the prev cell
	 */
	public int getPrevX(){
		return prevX;
	}
	
	/**
	 * 
	 * @return y coord of the prev cell
	 */
	public int getPrevY(){
		return prevY;
	}
}
