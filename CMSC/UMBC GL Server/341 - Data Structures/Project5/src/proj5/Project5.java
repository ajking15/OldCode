package proj5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;


/**
* File:    Project5.java
* Project: CMSC 341 Project 5, Fall 2008
* @author  Chris Mai
* Date:    11/25/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1.Three files
*   2. second file is incomprehensible nonsense
*/

public class Project5 {
	private static HuffmanTree huffmanTree;
	private static String file1;
	private static String file2;
	private static String file3;
	private static FileInputStream fileOne;
	private static FileOutputStream fileOut;
	private static FileInputStream fileTwo;
	private static FileOutputStream fileThree;
	private static int              limit;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length != 3)
		{
			System.out.println("Invalid number of cmd line args");
			System.exit(-1);
		}
		//store file names
		file1 = args[0];
		file2 = args[1];
		file3 = args[2];
		limit = 0;
		try {
			fileOne = new FileInputStream(file1);
		} catch (FileNotFoundException e) {
			System.out.println(file1 + " not found");
			System.exit(-2);
		}
		
		List<Character> list = new LinkedList<Character>();
		try {
			//read in entire file
			while(fileOne.available() > 0)
			{
				//store characters in a list
				int x = fileOne.read();
				Character temp = (char)x;
				list.add(temp);
				limit++;
			}
		} catch (IOException e) {
			System.out.println("Error reading in " + file1);
		}
		//new huffmanTree object
	

		huffmanTree = new HuffmanTree();
		//add a tilda to the end of the list for EOF
		list.add('^');
		//make a queue from the list, then build's tree and table
		huffmanTree.makeQueue(list);
		//huffmanTree.display();     //displays tree
	    //huffmanTree.displayTable();//displays table-letter,count,and binary
		try {
			//open file2 for output
			fileOut = new FileOutputStream(file2);
		} catch (FileNotFoundException e) {
			System.out.println("couldnt open file " + file2);
			System.exit(-3);
		}
		try {
			writeFileTwo(list);
		} catch (IOException e1) {
			System.out.println("Error writing file two");
		}
		
		try {
			fileOut.close();
		} catch (IOException e) {
			System.out.println("Error clsing file");
			System.exit(-1);
		}
		try {
			//reopen file2 for reding in
			fileTwo = new FileInputStream(file2);
			fileThree = new FileOutputStream(file3);
		
			readFile2Bits();
			
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		} catch (IOException e) {
			System.out.println("error in file i/o");
		}
		TreeMap<Character, Integer> tree = new TreeMap<Character,Integer>();
		//this loop adds things for the tree map
		Iterator<HuffmanNode> iter = huffmanTree.getEncoding().iterator();
		while(iter.hasNext())
		{
			
			HuffmanNode temp = iter.next();
			tree.put(temp.getStorage(), temp.getCount());
		}
		printCharsFreqs(tree);
		printCharsBinary(tree);
	}

	private static void printCharsFreqs(TreeMap<Character, Integer> tree){
		Set<Character> charSet = tree.keySet();
		
		Iterator<Character> ator = charSet.iterator();
		System.out.println("Char   Frequency");
		System.out.println("----------------");
		while(ator.hasNext())
		{
			Character temp = ator.next();
			HuffmanNode orary = huffmanTree.findChar(temp);
			if(!temp.equals('^'))
			{
				if(temp.equals('\n'))
				{
					System.out.println(" \\n" + "   |    " + orary.getCount());
				} else
					if(temp.equals('\r'))
					{
						System.out.println(" \\r" + "   |    " + orary.getCount());
					} else 
						if(temp.equals('\t'))
						{
							System.out.println(" \\t" + "   |    " + orary.getCount());
						} else {
							System.out.println("  " + orary.getStorage() + "   |    " + orary.getCount());
						}
			}
		}
		
	}
	
	private static void writeFileTwo(List<Character> list) throws IOException{
		Iterator<Character> chara = list.iterator();
		StringBuffer byteStream = new StringBuffer();
		//maybe make a list that stores all the nodes
//		long start = new Date().getTime(); 
		while(chara.hasNext())
		{
			Character tempChar = chara.next();
			HuffmanNode temp = huffmanTree.findChar(tempChar);
			byteStream.append(temp.binString());
		}
//		System.out.println("Create byte Stream");
//		System.out.println((new Date().getTime() - start)/1000.0);
		//by this point byteStream should have the full string of bytes
//		start = new Date().getTime(); 
		int byteLength = byteStream.length() / 7;
		for(int x = 0; x < byteLength; x++)
		{
			int y = 7 * x;
		//	System.out.println(y);
			int z = y + 7;
		//	System.out.println(7);
			fileOut.write(Byte.parseByte(byteStream.substring(y,z), 2));
			//byteStream.delete(0, 7);
		}
		
		if(byteStream.length() > 0)
		{
			if(byteStream.length() < 7)
			{
				for(int x = 0; x < 7 - byteStream.length(); x++)
				{
					byteStream.append("0");
				}
				Byte temp = Byte.parseByte(byteStream.toString(),2);
				fileOut.write(temp);
			}
		}
//		System.out.println("Write byte Stream");
//		System.out.println((new Date().getTime() - start)/1000.0);
		
	}
	
	private static void readFile2Bits() throws IOException{
		List<Integer> inte = new LinkedList<Integer>();
		List<Integer> bin = new LinkedList<Integer>();
		//reads in all the bytes
//		long start = new Date().getTime(); 
		while(fileTwo.available() > 0)
		{
			int bit = fileTwo.read();
			inte.add(bit);
		}
//		start = new Date().getTime(); 
		Iterator<Integer> iter = inte.iterator();
		while(iter.hasNext())
		{
			int x = iter.next();
			//convert binary
			Stack<Integer> integ = new Stack<Integer>();
			for(int y = 0; y < 7; y++)
			{
				int mod = x % 2;
				x = x / 2;
				integ.push(mod);
			}
			for(int z = 0; z < 7; z++)
			{
				bin.add(integ.pop());
			}
		}
//		//okay so bin has all of the correct bits
		List<Integer> search = new LinkedList<Integer>();
		Iterator<Integer> binary = bin.iterator();
		int a = 0;
		while(binary.hasNext())
		{
			search.add(binary.next());
		    char letter = '\0';
		    if(huffmanTree.findLetter(search) != '\0')
		    {
		    	//can't be writing out the ^
		    	letter = huffmanTree.findLetter(search);
		    	if(letter != '^' && a < limit)
		    	{
		    		fileThree.write(letter);
		    		search.clear();
		    		a++;
		    	}
		    }
		}
	}
	
	private static void printCharsBinary(TreeMap<Character, Integer> tree){
		Set<Character> charSet = tree.keySet();
		
		Iterator<Character> ator = charSet.iterator();
		System.out.println("Char   Frequency");
		System.out.println("----------------");
		while(ator.hasNext())
		{
			Character temp = ator.next();
			HuffmanNode orary = huffmanTree.findChar(temp);
			
			if(!temp.equals('^'))
			{
				if(temp.equals('\n'))
				{
					System.out.println(" \\n" + "   |    " + orary.getBinary());
				} else
					if(temp.equals('\r'))
					{
						System.out.println(" \\r" + "   |    " + orary.getBinary());
					} else 
						if(temp.equals('\t'))
						{
							System.out.println(" \\t" + "   |    " + orary.getBinary());
						} else {
							System.out.println("  " + orary.getStorage() + "   |    " + orary.getBinary());
						}
			}
		}
	}
}
