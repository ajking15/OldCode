/**
 * File: Lab8.java
 * Project: CMSC 202 Project Lab6
 * @author deepakc1
 * Date: Mar 27, 2008
 * Section: 0101
 * E-mail: deepakc1@umbc.edu
 *
 */
package lab8;

import java.util.Scanner;

/**
 * @author srini
 *
 */
public class Lab8 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int rectangleLength = 0;
		int rectangleWidth = 0;
		String rectangleColor = "";
		int triangleHeight = 0;
		int triangleWidth = 0;
		String triangleColor = "";
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter length of rectangle");
		rectangleLength = keyboard.nextInt();
		System.out.println("Enter width of rectangle");
		rectangleWidth = keyboard.nextInt();
		System.out.println("Enter color of rectangle");
		rectangleColor = keyboard.next();
		
		
		System.out.println("Enter the height of triangle");
		triangleHeight = keyboard.nextInt();
		System.out.println("Enter the width of triangle");
		triangleWidth = keyboard.nextInt();
		System.out.println("Enter the color of triangle");
		triangleColor = keyboard.next();
		
		
		//Create rectangle and triangle objects and assign to Shape references	
		//Invoke getArea and draw method on two Shape references
		
		/* Step a> Create an object of Rectangle class but which is stored in 
		the reference variable for base class Shape. Lets call it shape1.
		*/
		Shape shape1 = new Rectangle(rectangleLength, rectangleWidth, rectangleColor);


		/* Step b> Create an object of Triangle class but which is stored in 
		the reference variable for base class Shape. Lets call it shape2.
		*/
		Shape shape2 = new Triangle(triangleHeight, triangleWidth, triangleColor);



		/* Step c> Print the area of rectangle object given by shape1
		*/
		System.out.println("\nThe area of rectangle is " + shape1.getArea());


		/* Step d> Print the area of triangle object given by shape2
		*/
		System.out.println("The area of triangle is " + shape2.getArea());

	
		/* Step e> Invoke the draw function for rectangle object given by shape1 
		*/
 		shape1.draw();


		/* Step f> Invoke the draw function for triangle object given by shape2 
		*/
 		shape2.draw();
	}

}
