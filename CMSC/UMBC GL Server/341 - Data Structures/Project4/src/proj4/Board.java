package proj4;

import java.io.PrintWriter;
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
* File:    Board.java
* Project: CMSC 341 Project 4, Fall 2008
* @author  Chris Mai
* Date:    11/23/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. HashTable is generic
*/

public class Board {
	private Cell[][] board;
	private String   freeSpace = " ";
	private String   playerX         = "X";
	private String   playerO         = "O";
	private int      primeM;
	private int      boardLength     =  3;
	private int      xModVal         =  3;
	private int      oModVal         =  7;
	private int      eModVal         =  1;
	private List<Hash<Board>> movesMade;
	private PrintWriter printer;
	
	Board(int primeM, PrintWriter printer){
		this.primeM = primeM;
		this.printer = printer;
		board = new Cell[boardLength][boardLength];
		movesMade = new ArrayList<Hash<Board>>();
		//board = copyBoard();
		for(int x = 0; x < boardLength; x++)
		{
			for(int y = 0; y < boardLength; y++)
			{
				board[x][y] = new Cell();
			}
		}
	}
	
	/**
	 * 
	 * @return a deep copy of the board
	 */
	public Board copyBoard(){
		//new board object
		Board nuevo = new Board(primeM, printer);
		
		//iterate over copying the cells
		for(int x = 0; x < boardLength; x++)
		{
			for(int y = 0; y < boardLength; y++)
			{
				nuevo.board[x][y].setCell(board[x][y].getCell());
			}
		}
		return nuevo;
	}

	/**
	 * 
	 * @return returns a string detailing the condition of the board
	 *         ie, X wins, O wins, and tie
	 */
	public String gameOver(){
		for(int x = 0; x < board.length; x++)
		{
			//checks horizontal win
			String compare = board[x][0].getCell();
			int count = 0;
			for(int y = 0; y < board[x].length; y++)
			{
				if(compare.equals(board[x][y].getCell()))
				{
					count++;
				}
			}
			if(count == board.length)
			{
				//check for empty row
				if(!compare.equals(freeSpace)){
					return compare;
				}
				
			}
		}
		//checking vertical win
		for(int x = 0; x < board.length; x++)
		{
			String compare = board[0][x].getCell();
			int count = 0;
			for(int y = 0; y < board[x].length; y++)
			{
				if(compare.equals(board[y][x].getCell()))
				{
					count++;
				}
			}
			//check for empty row
			if(count == board.length)
			{
				//check for empty row
				if(!compare.equals(freeSpace)){
					return compare;
				}
			}
		}
		
		//check first diagonal \
			String compare = board[0][0].getCell();
			int count = 0;
			    //first diagonal
			for(int y = 0; y < board[0].length; y++)
			{
				if(compare.equals(board[y][y].getCell()))
				{
					count++;
				}
				//check for empty row
				if(count == board.length)
				{
					//check for empty row
					if(!compare.equals(freeSpace)){
						return compare;
					}
				}
			}
		//second diagonal /
		    int x = 0;
			//String compare = board[x][2].getCell();
			compare = board[x][2].getCell();
			count = 0;
			for(int y = board[x].length - 1; y > -1; y--)
			{
				
				if(compare.equals(board[x][y].getCell()))
				{
					count++;
				}
				x++;
			}
			//check for empty row
			if(count == board.length)
			{
				//check for empty row
				if(!compare.equals(freeSpace)){
					return compare;
				}
				
			}
			
			//check for full board
			count = 0;
			for(x = 0; x < board.length; x++)
			{
				for(int y = 0; y < board[x].length; y++)
				{
					if(!board[x][y].getCell().equals(freeSpace))
					{
						count++;
					}
				}
			}
			
			if(count == 9)
			{
				return "full";
			}
			
		return "continue";
	}
	
	/**
	 * 
	 * @param hash         table of hashed moves
	 * @param hashedMove   a boolean of wether or not the move is hashed
	 */
	public void move(HashTable<Board> hash, boolean hashedMove){
		//hash table is for comparison
		if(hashedMove == true)
		{
			//player X is hashed
			hashedMove(findFree(playerX), hash);
		} else {
			//player O is unhashed movement
			randomMove(findFree(playerO));
		}
		
	}
	
	//returns a list of type hash of possible boards
	private List<Hash<Board>> findFree(String player){
		//List<Board> free = new ArrayList<Board>();
		List<Hash<Board>> choice = new ArrayList<Hash<Board>>();
		//iterate over the board
		for(int x = 0; x < board.length; x++){
			for(int y = 0; y < board[x].length; y++)
			{
				if(board[x][y].getCell().equals(freeSpace))
				{
					Board temp = copyBoard();
					if(player.equals(playerX))
					{
						temp.board[x][y].setX();
						Hash<Board> test = new Hash<Board>(temp); 
						choice.add(test);
					} else {
						temp.board[x][y].setO();
						Hash<Board> test = new Hash<Board>(temp); 
						choice.add(test);
					}
				}
			}
		}
		
		return choice;
	}
	
	private void hashedMove(List<Hash<Board>> free, HashTable<Board> hash)
	{
		//free will have a list of all the boards
		//hash each board in free.
		//updates goodness for each move in the free moves list
		//System.out.println("possible moves");
		printer.println("Possible Moves:");
		Hash<Board> best;
		best = free.get(0);
		for(int x = 0; x < free.size(); x++)
		{
			hash.hash(free.get(x));
			if(best.getSetGoodness() < free.get(x).getSetGoodness())
			{
				best = free.get(x);
			}
			free.get(x).getKey().toString();
		}
		printer.println("Move made:");
		best.getKey().toString();
		printer.println("Reason: ");
		printer.println("Goodness: " + best.getSetGoodness());
//		System.out.println("move made:");
//		best.getKey().toString();
//		System.out.println("Reason:");
//		System.out.println("Goodness:" + best.getSetGoodness());
		//alter the board
		setMove(best);
		//add move to movesMade list
		movesMade.add(best);
	}
	/**
	 * 
	 * @param hash       sends a hashTable to be updated
	 * @param condition  win/loss
	 */
	public void updateHash(HashTable<Board> hash, String condition){
		hash.addHashs(movesMade, condition);
	}
	
	private void setMove(Hash<Board> move){
		//tempBoard should equal the rebuilt board
		//rebuildHash constructs board to the hashed specificatiosn
		//System.out.println("move made");
		//System.out.println("Goodness: " + move.getSetGoodness());
		//move.getKey().toString();
		rebuildHash(move);
	}
	
	private void randomMove(List<Hash<Board>> free)
	{
		Random rand = new Random();
		if(free.isEmpty())
		{
			
		} else {
		int choice = rand.nextInt(free.size());
		//get a number that's in ther array
		//choice %= free.size();
		//Math.abs(choice);
		//getting the board the should be based off of previous
		Hash<Board> temp = free.get(choice);
		setMove(temp);
		//setMove(free.get(choice));
		//tempBoard should equal the rebuilt board
		//need to convert from hash to a cell[][] board
		
		//toString();
		}
	}
	
	/**
	 * overwritten hash code function
	 */
	public int hashCode(){
		return getHashValue();
	}
	
	private void rebuildHash(Hash<Board> hash){
		int tempHash = hash.hashCode();
		
		//rebuilds the table from the hashCode
		for(int x = 0; x < board.length; x++)
		{
			for(int y = 0; y < board.length; y++)
			{
				board[x][y] = new Cell();
				int mod = tempHash % 10;
				tempHash /= 10;
				//it's an X
				if(mod == xModVal)
				{
					board[x][y].setX();
				} else
					//it's an O
					if(mod == oModVal)
					{
						board[x][y].setO();
					} else {
						//it's a space
						board[x][y].setEmpty();
					}
			}
		}
	}
	
	//creates a 9 digit number that correlates to the original board
	private int getHashValue(){
		int hashValue = 0;
		int multiplicand = 1;
		for(int x = 0; x < board.length; x++)
		{
			for(int y = 0; y < board[x].length; y++)
			{
				if(board[x][y].getCell().equals(playerX))
				{
					hashValue += xModVal * multiplicand;
				} else 
					if(board[x][y].getCell().equals(playerO))
					{
						hashValue += oModVal * multiplicand;
					} else {
						hashValue += eModVal * multiplicand;
					}
				multiplicand *= 10;
			}
		}
		return hashValue;
	}
	
	/**
	 * overwritten toString() methodd prints out the board
	 */
	public String toString(){
		for(int x = 0; x < board.length; x++)
		{
			for(int y = 0; y < board[x].length; y++)
			{
			//	System.out.print(" " + board[x][y].getCell());
				printer.print(" " + board[x][y].getCell()); 
				if(y < 2)
				{
					//System.out.print(" |");
					printer.print(" | ");
				}
			}
			printer.println("");
			//System.out.println("");
			if(x < 2)
			{
			//	System.out.println("------------");
				printer.println("------------");
			}
		}
		printer.println("");
		//System.out.println("");
		return "maybe convert above to a string";
	}
	//private inner class of board. a 3x3 array of it
	//holds info like full and whether or not it's an X or O
	private class Cell {
		private String  cell;
		//give cells coordinates?
		
		Cell(){
		    cell = freeSpace;
		}
		
		//use only for copy constructor
		private void setCell(String cell){
			if(cell.equals(playerX))
			{
				this.cell = playerX;
			} else 
				if(cell.equals(playerO))
				{
					this.cell = playerO;
				} else {
					this.cell = freeSpace;
				}
		}
		//prevent overwriting through skillful programming
		private void setX(){
				cell = playerX;
		}
		
		private void setO(){
				cell = playerO;
		}
		
		private void setEmpty(){
			cell = freeSpace;
		}
		
		private String getCell(){
		    return cell;
		}
	}
}
