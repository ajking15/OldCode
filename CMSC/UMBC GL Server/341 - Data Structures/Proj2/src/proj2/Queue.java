package proj2;

import java.util.LinkedList;

/**
* File:    Queue.java
* Project: CMSC 341 Project 2, Fall 2008
* @author  Chris Mai
* Date:    09/029/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. objects must be of type MazeCell
*/


public class Queue implements Agenda<MazeCell>{
	private LinkedList<MazeCell> queue;
	
	Queue (){
		queue = new LinkedList<MazeCell>();
	}
		
	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public MazeCell peek() {
		return queue.getFirst();
	}

	@Override
	public MazeCell pop() {
		return queue.removeFirst();
	}

	@Override
	public void push(MazeCell cell) {
		queue.addLast(cell);
	}

}
