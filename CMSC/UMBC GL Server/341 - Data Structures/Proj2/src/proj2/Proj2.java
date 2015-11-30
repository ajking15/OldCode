package proj2;

import java.io.FileNotFoundException;

/**
* File:    Proj2.java
* Project: CMSC 341 Project 2, Fall 2008
* @author  Chris Mai
* Date:    09/029/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. 2 cmd line args 
*/

public class Proj2 {
	private static int     NUM_CMD_ARGS = 2;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// check for correct cmd line args
			if(args.length != NUM_CMD_ARGS)
			{
				throw new Exception("Invalid number of command line arguments. Enter filename and stack/queue");
			}
			
			// make maze object
			Maze maze = new Maze();
			// load the maze from the given file
			maze.load(args[0]);

			// make an agenda
			Agenda<MazeCell> agenda;
			// check for stack or queue
			if (args[1].equalsIgnoreCase("queue"))
			{
				// agenda of type queue
				agenda = new Queue();
			} else { 
				// agenda of type stack
				agenda = new Stack();
			}
			// start solve method
			if (maze.solve(agenda)) {
				// maze is solvable
				System.out.println("Maze is solvable");
				System.out.println(maze.toString());
			} else {
				// maze not solved
				System.out.println("Maze is NOT solvable");
				System.out.println(maze.toString());
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

}
