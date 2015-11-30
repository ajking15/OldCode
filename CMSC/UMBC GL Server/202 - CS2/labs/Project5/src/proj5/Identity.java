package proj5;

/**
* File:    Identity.java
* Project: CMSC 202 Project 5, Spring 2008
* @author  Chris Mai
* Date:    05/13/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. all things implementing the interface have an id
*/

interface Identity<T> extends Comparable<T>{
	public String returnId();
	
	
}
