package proj5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
* File:    Project5.java
* Project: CMSC 202 Project 5, Spring 2008
* @author  Chris Mai
* Date:    05/13/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. The type of plane is either Cargo or Commuter
*/

public class Project5{
	private final static int NUM_CMD_ARGS = 7;
	private final static String CARGO = "cargo";
	private final static String PEOPLE = "commuter";
	private final static int ZERO = 0;
	private final static String PRINT = "print";
	private final static String LAND = "land";
	private final static String QUIT = "quit";
	private final static String TAKEOFF = "takeoff";
	private final static String CLIMB = "climb";
	private final static String DESCEND = "descend";
	private final static String LOAD = "load";
	private final static String UNLOAD = "unload";
	private static Scanner keyboard;
	private static PrintWriter printer;
	private static String action;
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
	/** CommandLineArguments
	 *  1. the type of transport will be flown -- "cargo" or "commuter" (without the quotes)
   	 *	2. the name of the transport's city of origin
   	 *	3. the transport's minimum flying altitude
   	 *	4. the transport's maximum flying altitude
   	 *	5. the maximum number of items the transport can hold (regardless of any attributes)
   	 *	6. the name of the command file
   	 *	7. the name of the file to which command output and error messages are logged
   	 ** Check if args make sense ie, positive, max>min, etc;
	 */
		//try block which contains the cmd line args exceptions
		try{
			//checks for correct number of cmd line arguments
			if(args.length != NUM_CMD_ARGS)
			{
				throw new IllegalCommandLineArgumentsException("Invalid number of command line arguments");
			}
			
			//converts the cmd line args meant to be ints to ints
			int minAlt = Integer.parseInt(args[2]);
			int maxAlt = Integer.parseInt(args[3]);
			int cargoLim = Integer.parseInt(args[4]);
			
			//opens new scanner and printwriter objects
			keyboard = new Scanner(new FileInputStream(args[5]));
			printer = new PrintWriter(new FileOutputStream(args[6]));
			//file not found exception is thrown if opening of either file fails
			
			//checks for appropriate values for min and max altitude and cargo lim
			if(minAlt >= ZERO && maxAlt >= ZERO && maxAlt > minAlt && cargoLim > ZERO)
			{
				//checks if we are to make a Cargo plane
				if(args[0].compareToIgnoreCase(CARGO) == 0)
				{
					//creates a generic aircraft object of type cargo
					Aircraft<Cargo> craft = new Aircraft<Cargo>(args[1], minAlt, maxAlt, cargoLim);
					//reads in the first command
					action = keyboard.nextLine();
					
					//loops until the QUIT command is read
					do
					{
						//checks if the cmd is to PRINT status
						if(action.compareToIgnoreCase(PRINT) == ZERO)
						{
							String output = craft.Print();
							printer.println(output);
						}
		
						//checks if the plane is ordered to land
						if(action.compareToIgnoreCase(LAND) == ZERO)
						{
							String output = craft.Land();
							printer.println(output);
						}
						
						//checks if the plane is ordered to TAKEOFF
						if(action.compareToIgnoreCase(TAKEOFF) == ZERO)
						{
							String destination = keyboard.nextLine();
							//try-catch block for IllegalCommandException which may get thrown
							try {
								String output = craft.Takeoff(destination);
								printer.println(output);
							} catch (IllegalCommandException e) {
								// TODO Auto-generated catch block
								printer.println(e.getMessage());
								System.out.println(e.getMessage());
							}
							catch(Exception e)
							{
								printer.println(e.getMessage());
								System.out.println(e.getMessage());
							}
						}
							
						//checks for climb command
						if(action.compareToIgnoreCase(CLIMB) == ZERO)
						{
							int nrFeet = keyboard.nextInt();
							String output = craft.Climb(nrFeet);
							printer.println(output);
						}
							
						//checks for descend command
						if(action.compareToIgnoreCase(DESCEND) == ZERO)
						{
							int nrFeet = keyboard.nextInt();
							String output = craft.Descend(nrFeet);
							printer.println(output);							
						}
							
						//checks for Load command
						if(action.compareToIgnoreCase(LOAD) ==  ZERO)
						{
							/*
							 *reads in necessary info in order to create new 
							 *cargo object in order to load it on the plane
							 */
							String id = keyboard.nextLine();
							int weight = keyboard.nextInt();
							int height = keyboard.nextInt();
							int width  = keyboard.nextInt();
							int length = keyboard.nextInt();
							Cargo cargo = new Cargo(id, weight, height, width, length);
								
							try {
								String output = craft.Load(cargo);
								printer.println(output);
							} catch (IllegalCommandException e) {
								// TODO Auto-generated catch block
								printer.println(e.getMessage());
								System.out.println(e.getMessage());
							} catch(Exception e){
								printer.println(e.getMessage());
								System.out.println(e.getMessage());
							}
						}
						
						//checks for unload command
						if(action.compareToIgnoreCase(UNLOAD) == ZERO)
						{
							//reads in necessary info necessary to unload
							String id = keyboard.nextLine();
							try {
								String output = craft.Unload(id);
								printer.println(output);
							}
							catch (IllegalCommandException e) {
								// TODO Auto-generated catch block
								printer.println(e.getMessage());
								System.out.println(e.getMessage());
							}
							catch(Exception e)
							{
								printer.println(e.getMessage());
								System.out.println(e.getMessage());
							}
						}
						//reads in next command
						action = keyboard.nextLine();
					}while(action.compareToIgnoreCase(QUIT) != ZERO);
				
					//closes the document
					printer.close();
					
				} else 
					//checks if it is a People plane
					if(args[0].compareToIgnoreCase(PEOPLE) == 0){
						//creates a generic aircraft of type people
						Aircraft<People> craft = new Aircraft<People>(args[1], minAlt, maxAlt, cargoLim);
						action = keyboard.nextLine();
						
						//same checks as for the cargo plane
						do
						{
								if(action.compareToIgnoreCase(PRINT) == ZERO)
								{
									String output = craft.Print();
									printer.println(output);
								}
			
								if(action.compareToIgnoreCase(LAND) == ZERO)
								{
										String output = craft.Land();
										printer.println(output);
								}
								
								if(action.compareToIgnoreCase(TAKEOFF) == ZERO)
								{
									String destination = keyboard.nextLine();
									try {
										String output = craft.Takeoff(destination);
										printer.println(output);
									} catch (IllegalCommandException e) {
										// TODO Auto-generated catch block
										printer.println(e.getMessage());
										System.out.println(e.getMessage());
									} catch (Exception e) {
										printer.println(e.getMessage());
										System.out.println(e.getMessage());
									}
								}
								
								if(action.compareToIgnoreCase(CLIMB) == ZERO)
								{
									int nrFeet = keyboard.nextInt();
									String output = craft.Climb(nrFeet);
									printer.println(output);
								}
								
								if(action.compareToIgnoreCase(DESCEND) == ZERO)
								{
									int nrFeet = keyboard.nextInt();
									String output = craft.Descend(nrFeet);
									printer.println(output);
									
								}
								
								if(action.compareToIgnoreCase(LOAD) ==  ZERO)
								{
									String name = keyboard.nextLine();
									int age = keyboard.nextInt();
									keyboard.nextLine();
									String id = keyboard.nextLine();
									People people = new People(name, age , id);
									
									try {
										String output = craft.Load(people);
										printer.println(output);
									} catch (IllegalCommandException e) {
										// TODO Auto-generated catch block
										printer.println(e.getMessage());
										System.out.println(e.getMessage());
									} catch (Exception e) {
										printer.println(e.getMessage());
										System.out.println(e.getMessage());
									}
								}
								
								if(action.compareToIgnoreCase(UNLOAD) == ZERO)
								{
									String id = keyboard.nextLine();
									try {
										String output = craft.Unload(id);
										printer.println(output);
									}
									catch (IllegalCommandException e) {
										// TODO Auto-generated catch block
										printer.println(e.getMessage());
										System.out.println(e.getMessage());
									}
									catch(Exception e)
									{
										printer.println(e.getMessage());
										System.out.println(e.getMessage());
									}
								}
							
							action = keyboard.nextLine();
						}while(action.compareToIgnoreCase(QUIT) != ZERO);
					
						//closes the file
						printer.close();
						
						
					} else {
						//in case type input is neither Cargo or People
						throw new IllegalCommandLineArgumentsException("Error: Invalid type of transport");
					}
				
				
			} else {
				throw new IllegalCommandLineArgumentsException("Error: invalid input for type ints");
			}
			}
			catch(FileNotFoundException e)
			{
			   	if(printer != null)
				{	
					printer.println(e.getMessage());
				}
				System.out.println(e.getMessage());
			}
			catch(IllegalCommandLineArgumentsException e)
			{
				if(printer != null)
				{
					printer.println(e.getMessage());
				}
				System.out.println(e.getMessage());
			}
			catch(Exception e)
			{
				//all exception catch blocks in case of random uncaught errors
				if(printer != null)
				{	
					printer.println(e.getMessage());
				}
				System.out.println(e.getMessage());
			}
			
		}

}
