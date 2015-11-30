package proj4;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
* File:    Project4.java
* Project: CMSC 341 Project 4, Fall 2008
* @author  Chris Mai
* Date:    11/23/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. X goes first
*   2. X is the hashedMove
*   3. O is a random move
*   4. size of hash table is a prime number
*/

public class Project4 {
	private static HashTable<Board> hashTable;
	//private static int    prime = 104729;
	//private static int      prime = 12391;
	//private static int      prime = 241;
	//private static int      prime = 311;
	private static int         prime = 149;
	private static PrintWriter printer;
	private static boolean     S;
	private static boolean     D;
	private static boolean     H;
	private static int         numGames;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
         //HashTable<Integer,Integer> test;
		//TODO check everything
		//TODO update win/loss record for each game played/lost
		//TODO comment code and javadocs
		//numGames = 0 in case nothing is passed in
		numGames = 0;
		//args[0] defaults to "${args}"?
		if(args.length > 0 && args[0].equals("${args}") == false)
		{
			for(int x = 0; x < args.length; x++)
			{
				//check for s flag
				if(args[x].equalsIgnoreCase("-S"))
				{
					S = true;
				} else
					if(args[x].equalsIgnoreCase("-D"))
					{
						//check for D flag
						D = true;
					} else 
						if(args[x].equalsIgnoreCase("-H"))
						{
							//check for H flag
							H = true;
						} else {
							//otherwise assume it's an integer
							numGames = Integer.parseInt(args[x]);
						}
			}
		}
	
		//Open a printWriter object
		try {
			printer = new PrintWriter("Project4Output.txt");
		} catch (FileNotFoundException e) {
			System.out.println("could not open output file");
		}
		//create a hashTable with a prime number as it's size
		hashTable = new HashTable<Board>(prime);
		//new game object
		Game game = new Game(prime,hashTable, printer);
		
		//play number of games specified
		for(int x = 0; x < numGames; x++)
		{
			game.play();
			game.newGame(printer);
			hashTable.stats(printer);
		}
		//print final stats
		game.printStats();
		//close printer object
		printer.close();
		//print hash table stats
		hashTable.stats(printer);
	}
}
