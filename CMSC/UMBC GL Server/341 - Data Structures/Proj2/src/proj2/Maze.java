package proj2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
* File:    Maze.java
* Project: CMSC 341 Project 2, Fall 2008
* @author  Chris Mai
* Date:    09/029/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. maze has start and finish
*   2. maze walls are walls
*/

public class Maze {
	private Scanner keyboard;
	private MazeCell[][] maze;
	private MazeCell current;
	private MazeCell end;
	private int length;
	private int width;
	private String 	WALL  = "#";
	private String  START = "o";
	private String  END   = "*";
	private String  PATH  = "+";
	private MazeCell start;
	private boolean  solution;
	
	
	/**
	 * loads array in the .txt file for use as a maze
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public Maze(){
		start = null;
		end = null;
	}
	
	public void load(String fileName) throws FileNotFoundException{
		// first thing is to read in the array size
		keyboard = new Scanner(new FileInputStream(fileName));
		
		// build the MazeCell array
		length = keyboard.nextInt();
		width  = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println(length + " " + width);
		maze = new MazeCell[length][width];

		// read in the maze
		for(int x = 0; x < length; x++)
		{
			//read in the whole line of the maze
			String line = keyboard.nextLine();
			for(int y = 0; y < width; y++)
			{
				//read in one part a time via substring
				String type = line.substring(y, y+1);
				maze[x][y] = new MazeCell(type.trim(), x, y);
			}
		}
	
		System.out.println(toString());
		
		// find the starting and ending location
		for(int x = 0; x < length; x++)
		{
			for(int y = 0; y < width; y++)
			{
				// start point
				if(maze[x][y].getType().equalsIgnoreCase(START))
				{
					//store location
					start = maze[x][y];
				}
				
				// end point
				if(maze[x][y].getType().equalsIgnoreCase(END))
				{
					//store location
					end = maze[x][y];
				}
			}
			
		}
		if(start == null || end == null)
		{
			throw new RuntimeException("Missing start/end of the maze");
		}
	}
	
	/**
	 * solves the maze if possible
	 * @param  agenda the stack/queue which will be used to hold the cells for execution
	 * @return true/false depending if the maze if solvable
	 */
	public boolean solve(Agenda<MazeCell> agenda){
		// TODO check for is empty some other time. program ending prematurely
		agenda.push(start);
		current = agenda.pop();
		solution = false;
		
		while(solution == false)
		{
			// make a bunch of mini functions for going through the array
			pushNorth(agenda);
			pushSouth(agenda);
			pushEast (agenda);
			pushWest (agenda);
			// pop cell, move to current, repeat
			
			/*
			 * when agenda is empty use a nested for loop to find 
			 */
			if(agenda.isEmpty() == false)
			{
				current = agenda.pop();
				
			} else {
				// no solution
				if(end.getVisited())
				{
					paintPath();
					return true;
				} else {
					return false;
				}
				
			}
		}
		return false;
	}
	
	private void paintPath(){
		// set current = to the end of the maze
		current = maze[end.getPrevX()][end.getPrevY()];
		// loop while current != to start
		while(current.getType().equals(START) == false)
		{
			maze[current.getX()][current.getY()].setType(PATH);
			current = maze[current.getPrevX()][current.getPrevY()];
		}
	}
	
	/**
	 * @return string printing out the maze. overwrites toString from object
	 */
	public String toString()
	{
		String maze = "";
		for(int x = 0; x < length; x++)
		{
			for(int y = 0; y < width; y++)
			{
					maze += this.maze[x][y].getType();
			}
			maze += "\n";
		}
		return maze;
	}
	
	private void pushNorth(Agenda<MazeCell> agenda){
		// check if it's a wall
		// check if it's visited
		// push onto agenda
		
		//if it's not a wall
		if(maze[current.getX() - 1][current.getY()].getType().equals(WALL) == false)
		{
			//if it hasn't been visited
			if(maze[current.getX() - 1][current.getY()].getVisited() == false)
			{
				// put it on the stack
				agenda.push(maze[current.getX() - 1][current.getY()]);
				// change status to visited
				maze[current.getX() - 1][current.getY()].visit();
				// set previous coordinates
				maze[current.getX() - 1][current.getY()].setPrev(current.getX(), current.getY());
			}
		}
	}
	
	private void pushSouth(Agenda<MazeCell> agenda){
		// check if it's a wall
		// check if it's visited
		// push onto agenda
		
		//if it's not a wall
		if(maze[current.getX() + 1][current.getY()].getType().equals(WALL) == false)
		{
			//if it hasn't been visited
			if(maze[current.getX() + 1][current.getY()].getVisited() == false)
			{
				// put it on the stack
				agenda.push(maze[current.getX() + 1][current.getY()]);
				// change status to visited
				maze[current.getX() + 1][current.getY()].visit();
				// set previous coordinates
				maze[current.getX() + 1][current.getY()].setPrev(current.getX(), current.getY());
			}
		}
	}
	
	private void pushEast(Agenda<MazeCell> agenda){
		// check if it's a wall
		// check if it's visited
		// push onto agenda
		//if it's not a wall
		if(maze[current.getX()][current.getY() + 1].getType().equals(WALL) == false)
		{
			//if it hasn't been visited
			if(maze[current.getX()][current.getY() + 1].getVisited() == false)
			{
				// put it on the stack
				agenda.push(maze[current.getX()][current.getY() + 1]);
				// change status to visited
				maze[current.getX()][current.getY() + 1].visit();
				// set previous coordinates
				maze[current.getX()][current.getY() + 1].setPrev(current.getX(), current.getY());
			}
		}
	}
	
	private void pushWest(Agenda<MazeCell> agenda){
		// check if it's a wall
		// check if it's visited
		// push onto agenda
		//if it's not a wall
		if(maze[current.getX()][current.getY() - 1].getType().equals(WALL) == false)
		{
			//if it hasn't been visited
			if(maze[current.getX()][current.getY() - 1].getVisited() == false)
			{
				// put it on the stack
				agenda.push(maze[current.getX()][current.getY() - 1]);
				// change status to visited
				maze[current.getX()][current.getY() - 1].visit();
				// set previous coordinates
				maze[current.getX()][current.getY() - 1].setPrev(current.getX(), current.getY());
			}
		}
	}
	
}
