/**
 * File: Lab6.java
 * Project: CMSC 202 Project lab5
 * @author deepakc1
 * Date: Mar 06, 2008
 * Section: 0101
 * E-mail: deepakc1@umbc.edu
 *
 */
package lab6;

import java.util.Scanner;

/**
 * @see Tank
 * @see HeatingTank
 * @author deepak
 *
 */
public class Lab6 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int capacity, volume, expansionFactor, temperatureChange;
		
		
		System.out.print("Enter the Capacity of the tank :");
		capacity = scanner.nextInt();
		System.out.print("Enter the Volume of the tank :");
		volume = scanner.nextInt();
		
		/* Create an object of class Tank with volume and 
		 * capacity just entered by the user
		 * refer to step 3c
		 */
		Tank tank = new Tank(capacity, volume);
		
		System.out.print("Enter the Capacity of the heatingtank :");
		capacity = scanner.nextInt();
		System.out.print("Enter the Volume of the heatingtank :");
		volume = scanner.nextInt();
		System.out.print("Enter the Exapansion factor of the heatingtank :");
		expansionFactor = scanner.nextInt();
		System.out.print("Enter the temperature change of the heatingtank :");
		temperatureChange = scanner.nextInt();
		
		
		/* Create an object (ex heatTank1) of class HeatingTank with volume and 
		 * capacity just entered by the user
		 * refer to step 3c, use the first constructor signature
		 */
		HeatingTank heatTank1 = new HeatingTank(capacity, volume);
		
		
		/* Set the expansionFactor and temperatureChange for HeatTank object created above using
		 * mutator for them. 
		 * refer to step 4e and 4g
		 */
		heatTank1.setTemperatureChange(temperatureChange);
		heatTank1.setExpansionFactor(expansionFactor);
		
		/* Create an object (ex heatTank2) of class HeatTank with volume and 
		 * capacity, expansion factor and temperature change just entered by the user
		 * refer to step 3c, use the second constructor signature
		 */
		HeatingTank heatTank2 = new HeatingTank(volume, capacity, temperatureChange, expansionFactor);
		
		
		/* Print the volume of first HeatingTank object
		 * refer to step 4f
		 */
		System.out.println("Volume after heating(first object): " + heatTank1.getVolume());
		
		
		
		/* Print the volume of second HeatingTank object
		 * refer to step 4f
		 */		
		System.out.println("Volume after heating(second object): " + heatTank2.getVolume());
	}
	 
}
