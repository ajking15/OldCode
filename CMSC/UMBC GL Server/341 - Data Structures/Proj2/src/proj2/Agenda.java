package proj2;

/**
* File:    Agenda.java
* Project: CMSC 341 Project 2, Fall 2008
* @author  Chris Mai
* Date:    09/029/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. none
*/


public interface Agenda<T> {
	public void push(MazeCell cell);
	public MazeCell peek();
	public MazeCell pop();
	public boolean isEmpty();
}
