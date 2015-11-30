package lab5;

import java.util.Scanner;

public class Lab5 {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		double vector1[] = new double[3];
		double vector2[] = new double[3];
		
		System.out.print("Enter three digits for vector one: ");
		vector1[0] = keyboard.nextDouble();
		vector1[1] = keyboard.nextDouble();
		vector1[2] = keyboard.nextDouble();
		
		System.out.print("Enter three digits for vector two: ");
		vector2[0] = keyboard.nextDouble();
		vector2[1] = keyboard.nextDouble();
		vector2[2] = keyboard.nextDouble();
		
		System.out.println("Using VectorMath class");
		
		/* After implementing the VectorMath class from step 3
		** insert code as indicated below to test your class **/

		// 3a. Create two VectorMath objects from the arrays/vectors above
			MyVector vec1 = new MyVector(vector1);
			MyVector vec2 = new MyVector(vector2);
		// 3b. Calculate and print the dot product of the two VectorMath objects
			System.out.println("Dot Product: " + vec1.dotProduct(vec2));
		// 3c. Use the getArray method to access the vector of one of the VectorMath objects
			double copy1[] = vec1.getArray();
		// 3d. Print out the copy	
			System.out.print("Copy Vector: (");
			
			for(int i = 0; i < copy1.length; i++)
			{
				System.out.print(copy1[i] + ",");
			}
			System.out.println(")");
		System.out.println("Using ArrayMath class");
		
		/** After implementing the ArrayMath class from step 4
		** insert code as indicated below to test your class **/

		// 4a. Calculate and print the dot product of the two arrays/vectors above
		// using the static dotProduct method of the ArrayMath class
		System.out.println("Dot Product: " + ArrayMath.dotProduct(vector1, vector2));
		// 4b. Use the static copyArray of the ArrayMath class to make a copy
		// of one of the arrays/vectors above
		double copy2[] = ArrayMath.copyArray(vector2);
		// 4c. Print out the copy
		System.out.print("Copy Vector: (");
		
		for(int i = 0; i < copy2.length; i++)
		{
			System.out.print(copy2[i] + ",");
		}
		System.out.println(")");
	}
}
