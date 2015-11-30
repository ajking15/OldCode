package proj5;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
* File:    HuffmanNode.java
* Project: CMSC 341 Project 5, Fall 2008
* @author  Chris Mai
* Date:    11/25/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1.holds letter, count, and associated binary position
*/

public class HuffmanNode implements Comparable<HuffmanNode>{
	private Character storage;
	private int       count;
	private HuffmanNode left;
	private HuffmanNode right;
	private List<Integer> binary;
	private StringBuffer binString;
	
	//take in a storage and a count or just storage and update count
	HuffmanNode(char storage){
		this(storage, null, null);
	}
	
	HuffmanNode(char storage, HuffmanNode left, HuffmanNode right) {
		this.storage = storage;
        this.left = left;
        this.right = right;
        binary = new LinkedList<Integer>();
        count = 1;
	}
	
	/**
	 * returns letter
	 */
	public char getStorage(){
		return storage;
	}
	
	/**
	 * 
	 * @return returns count of node
	 */
	public int getCount(){
		return count;
	}
	
	/**
	 * 
	 * @return returns nodes left child
	 */
	public HuffmanNode getLeft(){
		return left;
	}
	
	/**
	 * 
	 * @return returns nodes right child
	 */
	public HuffmanNode getRight(){
		return right;
	}
	
	
	/**
	 * 
	 * @param compare a node for comparing storage
	 * @return whether or not the nodes hold the same char
	 */
	public boolean compareStorage(HuffmanNode compare){
		return storage.equals(compare.storage);
	}
	
	/**
	 * increases count of node if duplicate char is found
	 */
	public void countPlus(){
		count++;
	}
	
	/**
	 * 
	 * @param x sets count if necessary
	 */
	public void setcount(int x){
		count = x;
	}
	
	/**
	 * 
	 * @return returns binary position held in a list
	 */
	public List<Integer> getBinary(){
		//System.out.println(binary);
		return binary;
	}
	
	/**
	 * 
	 * @param binary sets binary position by deep copying a list
	 */
	public void setBinary(List<Integer> binary)
	{
		//this.binary = binary;
		for(int x = 0; x < binary.size(); x++)
		{
			this.binary.add(binary.get(x));
		}
		binaryString();
	}
	
	/**
	 * 
	 * @return returns a StringBuffer representation of the binary code
	 */
	public StringBuffer binString(){
		return binString;
	}
	
	private void binaryString(){
	//	StringBuffer temp = new StringBuffer();
		binString = new StringBuffer();
		Iterator<Integer> iter = binary.iterator();
		while(iter.hasNext())
		{
			int x = iter.next();
			binString.append(x);
		}
	}
	
	/**
	 * 
	 * @param value char for comparison
	 * @return equal to to what's stored or not
	 */
	public int compareChar(char value){
		return storage.compareTo(value);
	}
	
	@Override
	public int compareTo(HuffmanNode node) {
		return count - node.count;
	}
}
