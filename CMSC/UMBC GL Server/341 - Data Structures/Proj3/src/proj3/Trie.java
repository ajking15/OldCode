package proj3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
/**
* File:    Trie.java
* Project: CMSC 341 Project 3, Fall 2008
* @author  Chris Mai
* Date:    10/30/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. Trie implements iterable over <PrintNode<T>>
*   2. uses private inner classes TrieNode<T> and TreeIterator<T>
*/
public class Trie<T> implements Iterable<PrintNode<T>>{
	private TrieNode<T>             root;
	private int                     depth;
	private int						numPrint;
	private List<TrieNode<T>>       childList;
	
	Trie(int depth, int numPrint) throws FileNotFoundException{
	    childList = new LinkedList<TrieNode<T>>();
		root = new TrieNode<T>(null,  null, 0); //no storage, no parent, depth = 0
		this.depth = depth;
		this.numPrint = numPrint;
	}
	
	/**
	 * 
	 * @param t list containing all the chars in a file
	 */
	public void insert(List<T> t){
		//should stop when the size of the list is the same as the depth
		while(t.size() >= depth) //this works
		{
			//first node's parent will be root
			TrieNode<T> parent = root;
			root.count++;
		
			//print the string section
			for(int y = 0; y < depth; y++)
			{
				int z = y + 1;
				//new node char stored, parent, and depth
				TrieNode<T> node = new TrieNode<T>(t.get(y), parent, z);
			    //insert returns the parent
				parent = insert(node, parent);
			}
			//this moves everything down 1
			t = t.subList(1, t.size());
		}
	}
	
	private TrieNode<T> insert(TrieNode<T> node, TrieNode<T> parent)
	{
		//check node against parents children
		if(parent.children.isEmpty())
		{
			//parent has no child, node added
			if(node.depth == depth)
			{
				addChildList(node);
			}
			parent.children.add(node);
			node.count++;
			//returns the child to become the next parent
			return node;
		} else {
			//node has children
			//iterate over those children
			Iterator<TrieNode<T>> iter = parent.children.iterator();
			//search parent's children for similar node
			while(iter.hasNext())
			{
				TrieNode<T> orary = iter.next();
				//if the storages are the same
				if(orary.storage.equals(node.storage))
				{
					//checks if it is the lowest node
					if(node.depth == depth)
					{
						addChildList(orary);
					}
					//parent.count++;
					orary.count++;
					//returns child to become the parent
					return orary;
				}
			}
		}
		//not a current child, so added
		if(node.depth == depth)
		{
			addChildList(node);
		}
		node.count++;
		parent.children.add(node);
		// return child to become the next parent
		return node;
	}

	private boolean addChildList(TrieNode<T> node){
			//boolean returns are only to break loop
			if(childList.isEmpty())
			{
				//can't be a duplicate node
				childList.add(node);
				return true;
			} else {
				//iterate over current list
				Iterator<TrieNode<T>> iter = childList.iterator();
				while(iter.hasNext())
				{
					TrieNode<T> temp = iter.next();
					if(node.storage.equals(temp.storage))
					{
						if(checkParents(temp, node))
						{
							return true;
						} else {
							childList.add(node);
							return true;
						}
					}
				}
			}
			childList.add(node);
			return true;
	}
	
	//compares bottom nodes for equivalence
	private boolean checkParents(TrieNode<T> current, TrieNode<T> test){
		int check = 0;
		for(int x = 0; x < depth; x++)
		{
			if(current.storage.equals(test.storage))
			{
				check++;
				current = current.parent;
				test    = test.parent;
			}
		}
		
		if(check == depth)
		{
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * 
	 * @param numTries number that is wanted to be printed. will be more in case
	 *                 of ties
	 * @return         A list of type print node that holds all the nodes to be printed
	 */
	public List<PrintNode<T>> findMaxCounts(int numTries){
		//temp is sorted
		TreeIterator<T> iter = new TreeIterator<T>(childList);
		//points to lowest node
		PrintNode<T>    lowest;
		//list to be returned
		List<PrintNode<T>> hold = new ArrayList<PrintNode<T>>();
		while(iter.hasNext())
		{
			//make a temporary PrintNode
			PrintNode<T> tempPrint = iter.next();
			//empty first node must be added
			if(hold.isEmpty())
			{
				//add
				hold.add(tempPrint);
				//it is lowest node
				lowest = tempPrint;
			} else {
				//sort it
				Collections.sort(hold);
				//node lowest points to shortest length
				lowest = hold.get(0); 
				if(hold.size() < numPrint)
				{
					//add node because there's space
					hold.add(tempPrint);
				} else {
					//add because it ties for lowest
					if(tempPrint.getCount1() >= lowest.getCount1())
					{
						hold.add(tempPrint);
					}
				}
			}
		}
		return hold;
	}
	
	/**
	 * 
	 * @param t list holding a sequence of chars
	 * @return  the number of times said sequence appears
	 */
	public int findSequence(List<T> t){
		TreeIterator<T> iter = new TreeIterator<T>(childList);
		while(iter.hasNext())
		{
			PrintNode<T> temp = iter.next();
			List<T> tempList = temp.getList();
			//compares lists for equality
			if(tempList.equals(t))
			{
				return temp.getCount1();
			}
		}
		//went through the list and that sequence isn't there count is 0
		return 0;
	}
	
	
	@Override
	public Iterator<PrintNode<T>> iterator() {
		return new TreeIterator<T>(childList);
	}
	
	private class TrieNode<T>{
		private TrieNode<T>         parent; 
		private T                   storage;
		private int                 depth;
		private int                 count;
		private List<TrieNode<T>>   children;
		
		
		TrieNode(T store, TrieNode<T> parent, int depth){
			storage = store;
			children = new LinkedList<TrieNode<T>>();
			this.parent = parent;
			this.depth = depth;
			count = 0;
		}
	}

	private class TreeIterator<T> implements Iterator<PrintNode<T>>{
		private Iterator<PrintNode<T>>    printIter;
		private List<PrintNode<T>>        printList;
		private Iterator<TrieNode<T>> iter;
		private List<TrieNode<T>> list;	
		private PrintNode<T> printNode;
		private Stack<PrintNode<T>> stack;
		
		TreeIterator(List<TrieNode<T>> childList){
			//trie node list which connects to the child list
			list = childList;
			stack = new Stack<PrintNode<T>>();
			//iterator over childList of type TrieNode<T>
			iter = list.iterator(); //necessary;
			//fills printList
			fillList();
			//sort based off of count1
			Collections.sort(printList);
			//sets up iterator of type PrintNode<T>
			descending();
			printIter = printList.iterator();
		}
		
		//reverses the list
		private void descending()
		{
			printIter = printList.iterator();
			while(printIter.hasNext())
			{
				stack.add(printIter.next());
			}
			printList = new LinkedList<PrintNode<T>>();
			while(!stack.isEmpty())
			{
				printList.add(stack.pop());
			}
		}
		
		private void fillList(){
			printList = new LinkedList<PrintNode<T>>();
			while(iter.hasNext())
			{
				TrieNode<T> orary = iter.next();
				PrintNode<T> temp = new PrintNode<T>(traceNode(orary), orary.count);
				printList.add(temp);
			}
		}
		
		private List<T> traceNode(TrieNode<T> trace){
			List<T> temp = new ArrayList<T>();
			List<T> orary = new ArrayList<T>();
			for(int x = 0; x < depth; x++)
			{
				temp.add(trace.storage);
				if(trace.parent != null)
				{
					trace = trace.parent;
				}
			}
			for(int x = 0; x < depth; x++)
			{
				orary.add(temp.get(depth - 1 - x));
			}
			return orary;
		}
		
		@Override
		public boolean hasNext() {
			return printIter.hasNext();
		}

		@Override
		public PrintNode<T> next() {
			printNode = printIter.next();
			return printNode;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("remove not supported");
		}
		
	}
	
}
