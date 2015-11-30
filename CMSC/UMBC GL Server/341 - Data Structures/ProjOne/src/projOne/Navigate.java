package projOne;

/**
* File:    Navigate.java
* Project: CMSC 341 Project 1, Fall 2008
* @author  Chris Mai
* Date:    09/08/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. all things implementing the interface have a location
*/

public interface Navigate<T> extends Comparable<T> {
		public int getX();
		public int getY();
}
