package projOne;


import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
* File:    Project1.java
* Project: CMSC 341 Project 1, Fall 2008
* @author  Chris Mai
* Date:    09/08/08
* Section: 0201
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. no grid size
*/

public class Proj1 {

	/**
	 * @param args
	 */
	private final static String TREASURE = "treasure";
	private final static String MONSTER = "monster";
	private final static String NORTH = "north";
	private final static int    N_MOVE = 1;
	private final static String SOUTH = "south";
	private final static int    S_MOVE = -1;
	private final static String EAST = "east";
	private final static int    E_MOVE = 1;
	private final static String WEST = "west";
	private final static int    W_MOVE = -1;
	private final static String GRAB = "grab";
	private final static String ATTACK = "attack";
	private final static String LOOK = "look";
	private final static String INVENTORY = "inventory";
	private final static int NUM_CMD_ARGS = 1;
	private static Scanner keyboard;
	private static String action;
	private static List<Monster> monList = new ArrayList<Monster>();
	private static List<Treasure> treList = new ArrayList<Treasure>();
	private static Explorer explorer;
	
	public static void main(String[] args) {
		/**
		 * Command Line Arguments
		 * -Command file
		 */
		try{
			//checks to see if there exists the correct number of cmd line args
			if(args.length != NUM_CMD_ARGS)
			{
					throw new Exception("Invalid number of command line arguments");
			}

			//opens new scanner objects
			keyboard = new Scanner(new FileInputStream(args[0]));			
			
			explorer = new Explorer();
			action = keyboard.next();
			int patchFix = 0;
			int bandaid = 0;
			do{
				//this really is a retarded band aid fix to my dilemma
				// of last line in a file not being read
				if(patchFix > 0)
				{
					if(bandaid > 0)
					{
						action = keyboard.next();
					}
					bandaid++;
				}
				
				//adds treasure
				if(action.compareToIgnoreCase(TREASURE) == 0)
				{
					int x = keyboard.nextInt();
					int y = keyboard.nextInt();
					int value = keyboard.nextInt();
					String description = keyboard.nextLine();
					Treasure treasure = new Treasure(x, y, value, description);
					//adds to an array list
					treList.add(treasure);
				}
				
				//adds a monster
				if(action.compareToIgnoreCase(MONSTER) == 0)
				{
					//variables stored first for easier reading
					int x = keyboard.nextInt();
					int y = keyboard.nextInt();
					int strength = keyboard.nextInt();
					int force = keyboard.nextInt();
					String desc = keyboard.nextLine();
					Monster monster = new Monster(x, y, strength, force, desc);
					//adds to an array list
					monList.add(monster);
				}
				
				//moves north
				if(action.compareToIgnoreCase(NORTH) == 0)
				{
					explorer.setY(explorer.getY() + N_MOVE);
				}
				
				//moves south
				if(action.compareToIgnoreCase(SOUTH) == 0)
				{
					explorer.setY(explorer.getY() + S_MOVE);
				}
				
				//moves east
				if(action.compareToIgnoreCase(EAST) == 0)
				{
					explorer.setX(explorer.getX() + E_MOVE);
				} 
				
				//moves west
				if(action.compareToIgnoreCase(WEST) == 0)
				{
					explorer.setX(explorer.getX() + W_MOVE);
				}
				
				//tries to grab treasure
				if(action.compareToIgnoreCase(GRAB) == 0)
				{
					/*
					 * the way this is implemented has the player attacked
					 * when trying to grab a treasure and there is a monster, also 
					 * when trying to grab a treasure that isn't there and a monster
					 * is there
					 */
					Iterator<Monster> rator = monList.iterator();
					Iterator<Treasure> iter = treList.iterator();
					System.out.println("Explorer is at: " + explorer.getX() + ", " + explorer.getY());
					System.out.println("The Explorer tries to grab treasure...");
					int counter = 0;
					
					//monster iterator
					while(rator.hasNext())
					{
						Monster mon = rator.next();
						//monster at location
						if(explorer.compareTo(mon) == 0)
						{
							explorer.takeDamage(mon.getForce());
							System.out.println(mon.getDescription() + " attacks!");
							System.out.println(mon.getDescription() + " hits for: " + mon.getForce());
							counter++;
						}
					}
					
					//treasure iterator
					while(iter.hasNext())
					{
						Treasure tres = iter.next();
						//treasure at location
						if(explorer.compareTo(tres) == 0)
						{
							//adds description to inventory
							explorer.addToInventory(tres.toString());
							System.out.print("Treasure found!" + tres.toString());
							counter++;
						}
					}
					
					//flavor text
					if(counter == 0)
					{
						System.out.println("and finds nothing");
					}
				}
				
				//attacks any and all monsters at explorers loc
				if(action.compareToIgnoreCase(ATTACK) == 0)
				{
					System.out.println("Explorer looks for something to attack...");
					int counter = 0;
					//create an iterator
					Iterator<Monster> rator = monList.iterator();
					List<Monster> tempList = new ArrayList<Monster>();

					//iterate over the list
					while(rator.hasNext())
					{
						//pull out the monster for comparison
						Monster temp = rator.next();
						//list to hold monsters pulled out for comparison
												
						//compare locations
						if(explorer.compareTo(temp) == 0)
						{
							//remove monster from list
							rator.remove();
							//monster takes damage
							temp.takeDamage(explorer.getForce());
							System.out.println("Explorer attacks " + temp.getDescription()
									+ " and hits for 10 damage");
							//checks if monster is still alive
							if(temp.getStrength() > 0)
							{
								explorer.takeDamage(temp.getForce());
								System.out.println(temp.getDescription() + " attacks"
										+ " and hits for " + temp.getForce());
								//stores monsters that attacked and lived to avoid repeat
								tempList.add(temp);
							}  else {
								System.out.println(temp.getDescription() + " has died.");
							}
							counter++;
						}
						
						
					}
					
					//because i was that bored
					if(counter == 0)
					{
						System.out.println("Explorer attacks a rock *clang*");
					}
					
					Iterator<Monster> monst = tempList.iterator();
					//puts the monsters back after checking the collection
					while(monst.hasNext())
					{
						Monster tempTwo = monst.next();
						monList.add(tempTwo);
					}
				}
				
				//checks current position for monsters and treasure
				if(action.compareToIgnoreCase(LOOK) == 0)
				{
					Iterator<Monster> rator = monList.iterator();
					Iterator<Treasure> iter = treList.iterator();
					System.out.println("Explorer is at: " + explorer.getX() + ", " + explorer.getY());
					System.out.println("The Explorer looks around and finds...");
					int counter = 0;
				
					//monster iterator
					while(rator.hasNext())
					{
						Monster mon = rator.next();
						//checks for monsters
						if(explorer.compareTo(mon) == 0)
						{
							System.out.print(mon.toString());
							counter++;
						}
					}
					
					//treasure iterator
					while(iter.hasNext())
					{
						Treasure tres = iter.next();
						//checks for treasure
						if(explorer.compareTo(tres) == 0)
						{
							System.out.print(tres.toString());
							counter++;
						}
					}
					
					//counter for flavor text
					if(counter == 0)
					{
						System.out.println("Nothing");
					}
				}
				
				//prints out contents of inventory
				if(action.compareToIgnoreCase(INVENTORY) == 0)
				{
					System.out.println("Explorers location: " + explorer.getX() + ", " + explorer.getY());
					System.out.println("Explorer has taken " + explorer.getDamage() + " damage");
					System.out.println("The explorer checks his backpack and finds:");
					System.out.print(explorer.getInventory());
					System.out.println("done checking the backpack");
				}
				
				if(patchFix == 0)
				{
					action = keyboard.next();
					patchFix++;
				}

			}while(keyboard.hasNext());
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		
	}

}
