package proj3;

import java.util.List;

/**
* File:    PrintNode.java
* Project: CMSC 341 Project 3, Fall 2008
* @author  Chris Mai
* Date:    10/30/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. wrapper class used for comparing two files alone
*/

public class PrintNode<T> implements Comparable<PrintNode<T>>{
	private List<T> list;
	private int     count1;
	private int     count2;
	
	PrintNode(List<T> list, int count){
		this.list = list;
		count1 = count;
	}
	
	/**
	 * 
	 * @param count sets count2 via an outside source
	 */
	public void setCount2(int count){
		count2 = count;
	}
	
	/**
	 * 
	 * @return count for type T sequence for first file
	 */
	public int getCount1(){
		return count1;
	}
	
	/**
	 * 
	 * @return count for type T sequence from second file
	 */
	public int getCount2(){
		return count2;
	}
	
	/**
	 * 
	 * @return returns the list of type T which holds a sequence of chars for the prog
	 */
	public List<T> getList(){
		return list;
	}

	/**
	 * @param  node another print node
	 * @return      int value upon comparions of the two count1's
	 */
	public int compareTo(PrintNode<T> node) {
		return count1 - node.count1;
	}
	
}
