package proj4;

import java.io.PrintWriter;
//import java.util.Iterator;
//import java.util.List;

/**
* File:    Game.java
* Project: CMSC 341 Project 4, Fall 2008
* @author  Chris Mai
* Date:    11/23/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. has a hashtable and main board
*/

public class Game {
	/*
	 * has a board and the hash table
	 */
	private Board mainBoard;
	private HashTable<Board> hashTable;
	private int primeM;
	private int xWins;
	private int oWins;
	private int ties;
	private PrintWriter printer;
	/**
	 * @param args
	 */
	
	Game(int primeNum, HashTable<Board> hashTable, PrintWriter printer){
		primeM = primeNum;
		this.printer = printer;
		this.hashTable = hashTable;
		mainBoard = new Board(primeM, printer);
		xWins = 0;
		oWins = 0;
		ties = 0;
	}
	
	
	/**
	 * plays out one game of tic tac toe
	 */
    public void play(){
    	while(mainBoard.gameOver().equals("continue"))
    	{
    		turnX();
    		if(mainBoard.gameOver().equals("continue"))
    		{
    			turnO();
    		}
    	}
    	//updates the stats at games end
    	if(mainBoard.gameOver().equals("X"))
    	{
    		xWins++;
    		printer.println("X wins!");
    //		System.out.println("X wins!");
    		mainBoard.updateHash(hashTable, "X");
    	} else 
    		if(mainBoard.gameOver().equals("O"))
    		{
    			oWins++;
    			printer.println("O wins!");
    //			System.out.println("O wins!");
    			mainBoard.updateHash(hashTable, "O");
    		} else {
    			ties++;
    			printer.println("Tie Game!");
    //			System.out.println("tie game");
    			mainBoard.updateHash(hashTable, "tie");
    		}
    	
    }
    
    private void turnX(){
    	mainBoard.move(hashTable, true);
    }
	
    private void turnO(){
    	mainBoard.move(hashTable, false);
    }
    
    /**
     * print stats of past games
     */
    public void printStats(){
//    	System.out.println("Games won: " + xWins);
//    	System.out.println("Games lost: " + oWins);
//    	System.out.println("Games tied: " + ties);
    	int total = xWins + oWins + ties;
//    	System.out.println("Total Games: " + total);
    	printer.println("Games won: " + xWins);
    	printer.println("Games lost: " + oWins);
    	printer.println("Games tied: " + ties);
    	printer.println("Total Games: " + total);
    }
    
    /**
     * 
     * @param printer takes in a printer object and creates a new board
     */
    public void newGame(PrintWriter printer){
    	mainBoard = new Board(primeM, printer);
    }
	
}
