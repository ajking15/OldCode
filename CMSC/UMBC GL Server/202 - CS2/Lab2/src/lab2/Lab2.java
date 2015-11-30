/**
 * file: lab2.java
 * project: cmsc 202 project lab2
 * author: Christopher Mai
 * Date: Feb 11, 2008
 * section: 0401
 * e-mail: chrmai1@umbc.edu
 */
package lab2;

import java.util.Scanner;

/**
 * @author chrmai1
 *
 */
public class Lab2 {
	public static final String FRACTION_SEPARATOR = "/";
	/**
	 * Name: main
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numerator = 0;
		int denominator = 0;
		
		System.out.println("Enter the numerator of the Fraction");
		numerator = readInt();
		System.out.println("Enter the denominator of the Fraction");
		denominator = readInt();
		
		print(numerator, denominator);
		double value = decimalValue(numerator, denominator);
		System.out.println("Decimal value of the fraction is " + value);
	}

	
	/**
	 * name: print
	 * @param numerator - Numerator of the fraction
	 * @param denominator - Denominator of the fraction
	 */
	public static void print(int numerator, int denominator){
		
		System.out.println("The numerator is " + numerator
				+ FRACTION_SEPARATOR + denominator);
		
	}
	
	/**
	 * Name: decimalValue
	 * @param numerator - Numerator of the Fraction
	 * @param denominator - Denominator of the Fraction
	 * @return Decimal Value of the fraction
	 */
	public static double decimalValue(int numerator, int denominator){
		double value = ((double)numerator)/((double)denominator);
		return value;
	}
	
	/**
	 * Name: readInt
	 * @return return an integer from the standard inputStream
	 */
	public static int readInt(){
		Scanner keyboard = new Scanner(System.in);
		int value = 0;
		boolean validInt = false;
		
		while(!validInt){
			if(keyboard.hasNextInt()){
				value = keyboard.nextInt();
				validInt = true;
			} else {
				keyboard.nextLine();
				System.out.println("Previous input was not an integer. " +
						"Enter an integer");
			}
		}
		return value;
	}
}

