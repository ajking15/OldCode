package proj5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/**
* File:    Aircraft.java
* Project: CMSC 202 Project 5, Spring 2008
* @author  Chris Mai
* Date:    05/13/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. T extends Identity which extends Comparable
*/
public class Aircraft<T extends Identity<T>>{
	/**
	 * @param args
	 */
	private ArrayList<T> transport;
	private int MIN_ALTITUDE;
	private int MAX_ALTITUDE;
	private int currentAlt;
	private int CARGO_LIMIT;
	private int ZERO = 0;
	private String currentPosition;
	private String destination;
	private final String AIR = "Air";
	private final String NONE = "None";
	private final String separator = System.getProperty("line.separator");
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
		
			Aircraft<People> craft = new Aircraft<People>("UMBC",4, 35, 5);
			People stuff1 = new People("bob", 10, "35");
			craft.Load(stuff1);
			
			People stuff2 = new People("bob", 10, "45");
			craft.Load(stuff2);
			
			People stuff3 = new People("bob", 10, "2");
			craft.Load(stuff3);
			
			System.out.println(craft.Print());
			
			craft.Unload("35");
		
			System.out.println(craft.Print());
			
			craft.Takeoff("Japan");
		
			System.out.println(craft.Print());
			craft.Land();
			craft.Unload("45");
			System.out.println(craft.Print());
			craft.Climb(45);
			System.out.println(craft.Print());
			craft.Descend(10);
			System.out.println(craft.Print());
			
		}
		catch(IllegalCommandException e){
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public Aircraft(String currentPos, int minAlt, int maxAlt, int cargoLim)
	{
		MIN_ALTITUDE = minAlt;
		MAX_ALTITUDE = maxAlt;
		CARGO_LIMIT = cargoLim;
		currentAlt = ZERO;
		transport = new ArrayList<T>();
		currentPosition = currentPos;
		destination = NONE;
	}
	
	/**
	* Name: 		 Print
	* PreCondition:  none
	* PostCondition: String of Prints output
	*/@SuppressWarnings("unchecked")
	public String Print()
	{
		String output = separator + separator + "---Transport Status---" +
						separator + "Max Altitude: " + MAX_ALTITUDE + 
						separator + "Min Altitude: " + MIN_ALTITUDE +
						separator + "Max Capacity: " + CARGO_LIMIT +
						separator + "Current Altitude: " + currentAlt +
						separator + "Current Position: " + currentPosition +
						separator + "Destination: " + destination +
						separator + separator + "---Contents Status---";
		
		Sort();
		//if statement to display separate message incase transport is empty
		if(transport.isEmpty() == true)
		{
			output += separator + "Transport is currently empty";
			return output;
		} else {
			for(int i = 0; i < transport.size(); i++)
			{
				output += separator + transport.get(i).toString();
			}
			return output;
		}
	}
		
	/**
	* Name: 		 Climb
	* PreCondition:  nrFeet positive
	* PostCondition: plane altitude elevated
	*/
	public String Climb(int nrFeet)
	{
		//checks if your not in the air
		if(currentPosition != AIR)
		{
			String output = separator + "Plane is grounded, climbing impossible. Takeoff first";
			return output;
		} else
			//checks if cmds attempt to push plane above maximum altitude
			if(currentAlt + nrFeet > MAX_ALTITUDE)
			{
				//plane levels off at max altitude
				currentAlt = MAX_ALTITUDE;	
				String output = separator + "Cannont Climb above max altitude " + MAX_ALTITUDE + ", leveling off at " + MAX_ALTITUDE;
				return output;
			} else {
				currentAlt += nrFeet;
				//increases altitude
				String output =  separator + "Current altitude increased by " + nrFeet + " current altitude: " + currentAlt;
				return output;
			}
	}
	
	
	/**
	* Name: 		 Descend
	* PreCondition:  nrFeet is positive
	* PostCondition: current altitude of plane is corrected
	*/
	public String Descend(int nrFeet)
	{
		//checks if your flying
		if(currentPosition != AIR)
		{
			return separator + "Plane is grounded, descent impossible";
		} else
			//checks if you're being pushed below min altitude
			if(currentAlt - nrFeet < MIN_ALTITUDE)
			{
				currentAlt = MIN_ALTITUDE;
				return separator + "Cannot descend below min altitude " + MIN_ALTITUDE + ", leveling off at " + MIN_ALTITUDE;
				//message about altitude and log
			} else {
				currentAlt -= nrFeet;
				return separator + "Current altitude decreased by " + nrFeet + " current altitude: " + currentAlt;
			}
	}
	
	/**
	* Name: 		 Land
	* PreCondition:  none
	* PostCondition: current position updated
	*/
	public String Land()
	{
		//checks if your actually flying
		if(currentPosition == AIR)
		{
			String temp = destination;
			currentPosition = destination;
			currentAlt = 0;
			destination = "None";
		
			return separator + "Plane has landed at " + temp;
		} else {
			return "ERROR: Cannont land, you are not flying.";
		}
	}

	/**
	* Name: 		 Takeoff
	* PreCondition:  none
	* PostCondition: Plane take's off if grounded or error is thrown
	*/
	public String Takeoff(String destination) throws IllegalCommandException
	{
		//checks if the plane is grounded
		if(currentPosition != AIR)
		{
			//moves variables around
			String temp = currentPosition;
			currentPosition = AIR;
			this.destination = destination;
			currentAlt = MIN_ALTITUDE;

			String output =  separator + "The plane is leaving " + temp + " and flying to " + this.destination; 
			return output;
		} else {
			//throws an exception for complete random
			throw new IllegalCommandException("ERROR: Already flying, takeoff impossible");
		}
	}
	
	/**
	* Name: 		 Load
	* PreCondition:  item is of a proper Generic type
	* PostCondition: current altitude of plane is corrected
	*/
	public String Load(T item) throws IllegalCommandException
	{
		//checks if your flying
		if(currentPosition == AIR)
		{
			//throws a you cant do that exception
			throw new IllegalCommandException("Cannont load, the plane is flying");
			//do something about being in the air and log
		} else {
			//checks if the transport is full
			if(transport.size() < CARGO_LIMIT)
			{
				transport.add(item);
				return "Load: " + item.toString();
			} else {
				return "Transport is full, cannot load: " + item.toString();
			}
		}
	}
	
	/**
	* Name: 		 Unload
	* PreCondition:  none
	* PostCondition: item unloaded if possible
	*/
	public String Unload(String label) throws IllegalCommandException
	{
		//checks if your in the air
		if(currentPosition == AIR)
		{
			throw new IllegalCommandException("Plane currently in flight, unload impossible");
		} else {
			//checks if the transport is empty
			if(transport.isEmpty() == false)
			{
				//Iterator to check the elements
				Iterator<T> rator = transport.iterator();
				while(rator.hasNext())
				{
					//compares the ids
					if(rator.next().returnId().compareToIgnoreCase(label) == ZERO){
						rator.remove();
						return "Item: " + label + " has been removed.";
					}
					
				}
					return "Item: " + label + " has not been found";
			} else {
				return "Cannot unload, the plane is empty";
			}
			
		}
	}
	
	/**
	* Name: 		 Sort
	* PreCondition:  none
	* PostCondition: list is sorted in ascending order
	*/
	private void Sort()
	{
		Collections.sort(transport);
	}
	
}
