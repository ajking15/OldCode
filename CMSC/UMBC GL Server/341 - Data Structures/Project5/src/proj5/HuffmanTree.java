package proj5;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
* File:    HuffmanTree.java
* Project: CMSC 341 Project 5, Fall 2008
* @author  Chris Mai
* Date:    11/25/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1.holds tree
*/

public class HuffmanTree {
	private List<HuffmanNode> huffman;
	private HuffmanNode root;
	private List<HuffmanNode>          encoding;
	
	HuffmanTree(){
		huffman = new LinkedList<HuffmanNode>();
		encoding = new LinkedList<HuffmanNode>();
		root = null;
	}
	
	/**
	 * 
	 * @param list list of characters in a file
	 */
	public void makeQueue(List<Character> list){
		//list temp
		List<HuffmanNode> listTemp = new LinkedList<HuffmanNode>();
		//iter iterates over the file's  characters
		Iterator<Character> iter = list.iterator();
	//	iterate over the file's characters
		while(iter.hasNext())
		{
			//added equals false
			boolean added = false;
			//make a HuffmanNode based off of the character
			HuffmanNode temp = new HuffmanNode(iter.next());
			//if my priority queue is empty add the node
			if(listTemp.isEmpty())
			{
				listTemp.add(temp);
			} else {
				//otherwise iterate over the queue
				Iterator<HuffmanNode> ator = listTemp.iterator();
				while(ator.hasNext())
				{
					HuffmanNode orary = ator.next();
					//if the characters in the queue and list are equal
					if(orary.compareStorage(temp))
					{
						//update count
						orary.countPlus();
						//added equals true
						added = true;
					}
				}
				//if not found
				if(added == false)
				{
					//add as a new node
					listTemp.add(temp);
					//added = true for no reason really
					added = true;
				}
			}
		}
		//sort is apparently necessary
		//priority queue doesn't seem to work as expected
		Collections.sort(listTemp);
		
		//add to priority queue
		for(int x = 0; x < listTemp.size(); x++)
		{
			huffman.add(listTemp.get(x));
			//huffman.offer(listTemp.get(x));
		}
		
		//build the tree
		buildTree();
		//root node is the only node left in the priority queue
		root = huffman.get(0);
		//root is pointing to the tree
		//build encoding table
		buildTable();
	}
	
	private void buildTree(){
		//iterate until only one element is left
		while(huffman.size() != 1)
		{
			//make a mini tree with the two lowest nodes in the queue
			Collections.sort(huffman);
			HuffmanNode left = huffman.remove(0);
			HuffmanNode right = huffman.remove(0);
			HuffmanNode tempRoot = new HuffmanNode('\0', left, right);
			tempRoot.setcount(left.getCount() + right.getCount());
			huffman.add(tempRoot);
		}
	}
	
	/**
	 * prints out all the information about the nodes, letter,count, and binary position
	 * in the tree
	 */
	public void displayTable(){
		for(int x = 0; x < encoding.size(); x++)
		{
			System.out.print(encoding.get(x).getStorage() + " " + encoding.get(x).getCount()+ " "); 
			
			System.out.println(encoding.get(x).getBinary());
		}
	}
	
	private void buildTable(){
		//make a list of integers
		List<Integer> integ = new LinkedList<Integer>();
		if(root == null)
		{
			System.out.println("tree is empty");
		} else {
			buildTable(root, integ);
		}
	}
	
	private void buildTable(HuffmanNode node,List<Integer> integ){
		if(node.getLeft() != null)
		{
			integ.add(0);
			buildTable(node.getLeft(), integ);
			integ.remove(integ.size() - 1);
		} else {
			//if the child equals null you have found a leaf which has a char
			node.setBinary(integ);
			encoding.add(node);
			//how to keep track of binary code?
		}
		
		if(node.getRight() != null)
		{
			integ.add(1);
			buildTable(node.getRight(),integ);
			integ.remove(integ.size() - 1);
		} else {
			//is null an end value has been found
		}
	}
	
	/**
	 * calls private method to print the binary tree
	 */
	 public void display() {
         if(this.root == null) {
                 System.out.println("Empty");
         } else {
                 display(this.root, 0, "root");
         }
	 }

	 //displays the tree
	 private void display(HuffmanNode node, int depth, String label) {
        
         // print padding
         for(int i = 0; i < depth; i++) {
                 System.out.print("   ");
         }
        
         // print label, value
         System.out.println(label + ": " + node.getStorage());
        
         // print on left subtree
         if(node.getLeft() != null) {
                 display(node.getLeft(), depth + 1, "left");
         }
         // print on right subtree
         if(node.getRight() != null) {
                 display(node.getRight(), depth + 1, "right");
         }
	 }

	 /**
	  * 
	  * @param list  list of integers that is the binary code for finding a letter 
	  *              in the tree
	  * @return      the associated char for printing
	  */
	 public char findLetter(List<Integer> list){
		 Iterator<HuffmanNode> iter = encoding.iterator();
		 HuffmanNode temp;
		 while(iter.hasNext())
		 {
			 temp = iter.next();
			 if(temp.getBinary().equals(list))
			 {
				 return temp.getStorage();
			 }
		 }
		 return '\0';
	 }
	 
	 /**
	  * 
	  * @param value a character that you wish to find in the encoding table
	  * @return      huffman node that corresponds to the value
	  */
	 public HuffmanNode findChar(char value)
	 {
		 for(int x = 0; x < encoding.size(); x++)
		 {
			 if(encoding.get(x).compareChar(value) == 0)
			 {
				// System.out.println("found");
				 return encoding.get(x);
			 }
		 }
		 //not found return a null node
		 return new HuffmanNode('\0',null,null);
	 }
	 
	 public List<HuffmanNode> getEncoding(){
		 return encoding;
	 }
}
