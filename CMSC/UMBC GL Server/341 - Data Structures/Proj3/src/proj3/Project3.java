package proj3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
* File:    Project3.java
* Project: CMSC 341 Project 3, Fall 2008
* @author  Chris Mai
* Date:    10/30/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. 4 cmd line args
*   2. args 1 and 2 are numbers
*   3. args 3 and 4 are names of text files
*/

public class Project3 {
	private static FileInputStream input;
	private static int             depth;
	private static int             numPrint;
	private static Trie<Character> tree1;
	private static Trie<Character> tree2;
	private static List<Character> list1;
	private static List<Character> list2;
	private static List<PrintNode<Character>> printList;
	private static Stack<PrintNode<Character>> printStack;
	public static String newline = System.getProperty("line.separator");
	
	public static void main(String[] args){
		//first depth
		//second number of lines to print
		//third 1st input file
		//fourth 2nd input file
	
		list1 = new LinkedList<Character>();
		list2 = new LinkedList<Character>();
		if(args.length != 4)
		{
			try {
				throw new Exception("invalid number of cmd line args");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.exit(-1);
			}
		}
		depth = Integer.parseInt(args[0]);
		numPrint = Integer.parseInt(args[1]);
		if(depth <= 0 || numPrint <= 0)
		{
			try {
				throw new Exception("Invalid value for depth or number to be printed");
			} catch(Exception e){
				System.out.println(e.getMessage());
				System.exit(-1);
			}
		}
			try{
				tree1 = new Trie<Character>(depth, numPrint);
				tree2 = new Trie<Character>(depth, numPrint);
		
				tree1 = buildTrie(tree1, args[2], list1);
		
				tree2 = buildTrie(tree2, args[3], list2);
			} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}
			//printList = list of PrintNode's that hold max counts
			printList = tree1.findMaxCounts(numPrint);
			
			Iterator<PrintNode<Character>> printIter = printList.iterator();
			while(printIter.hasNext())
			{
				PrintNode<Character> printNode = printIter.next();
				printNode.setCount2(tree2.findSequence(printNode.getList()));
			}
			//prints final table
			printTrieCounts();
	}
	
	private static void printTrieCounts(){
		System.out.print("Sequence");
		for(int x = 0; x < (depth * 4) + 2; x++)
		{
			System.out.print(" ");
		}
		System.out.println("Count1               Count2");
		for(int y = 0; y < 35 + (depth * 5); y++)
		{
		System.out.print("-");
		}
		System.out.println("");
		//sorts list with smallest at the bottom
		Collections.sort(printList);
		Iterator<PrintNode<Character>> printIter = printList.iterator();
		//stack reverses order so largest count is at top
		printStack = new Stack<PrintNode<Character>>();
		while(printIter.hasNext())
		{
			printStack.add(printIter.next());
		}
		while(printStack.isEmpty() == false)
		{
			PrintNode<Character> temp = printStack.pop();
			List<Character>      orary = temp.getList();
			for(int x = 0; x < orary.size(); x++)
			{
				if(Character.isWhitespace(orary.get(x)))
				{
					if(orary.get(x).equals(' '))
					{
						System.out.printf("\'%2s\' ",orary.get(x));
					} else 
					if(orary.get(x).equals('\n')){
						String line = "\\n";
						System.out.printf("\'%2s\' ", line);
					} else
						if(orary.get(x).equals('\r'))
						{
							String carriage = "\\r";
							System.out.printf("\'%2s\' ", carriage);
						} else
							if(orary.get(x).equals('\t'))
							{
								String tab = "\\t";
								System.out.printf("\'%2s\' ", tab);
							}
					
				} else {
					System.out.printf("\'%2s\' ",orary.get(x));
				}
			}
			
			System.out.printf("%10d %20d \n", temp.getCount1(), temp.getCount2());
		}
	}
	
	private static Trie<Character> buildTrie(Trie<Character> tree, String fileName, List<Character> list) throws Exception{
		//opens new input buffer
		input = new FileInputStream(fileName);
		//loop while there's still data
		while(input.available() > 0)
		{
			//reads in entire text file
			Character temp = (char)input.read();
			list.add(temp);
		}
		
		//code breaks out if the wanted depth is larger than the number of chars
		if(list.size() < depth)
		{
			throw new Exception("not enough characters");
		}
		//inserts the characters into the trie
		tree.insert(list);
		
		//returns the tree
		return tree;
	}
}
