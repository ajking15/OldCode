package proj2;

import java.util.LinkedList;

/**
* File:    Stack.java
* Project: CMSC 341 Project 2, Fall 2008
* @author  Chris Mai
* Date:    09/029/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. objects must be of type MazeCell
*/

public class Stack implements Agenda<MazeCell>{
	private LinkedList<MazeCell> stack;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	Stack(){
		stack = new LinkedList<MazeCell>();
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public MazeCell peek() {
		return stack.getLast();
	}

	@Override
	public MazeCell pop() {
		return stack.removeLast();
	}

	@Override
	public void push(MazeCell cell) {
		stack.addLast(cell);
	}

	
}
