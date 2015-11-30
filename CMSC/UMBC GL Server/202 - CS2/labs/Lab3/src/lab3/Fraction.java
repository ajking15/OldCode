/**
 * 
 */
package lab3;

import java.util.Scanner;
/**
 * @author chrmai1
 *
 */
public class Fraction {
	
	public static final String FRACTION_SYMBOL = "/";
	
	private int numerator;
	private int denominator;
	
	public Fraction(int numerator, int denominator){
		this.numerator = numerator;
		this.denominator = denominator;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int numerator = 1;
		int denominator = 1;
		
		System.out.println("Enter the numerator of the first fraction ");
		numerator = scanner.nextInt();
		System.out.println("Enter the denominator of the first fraction ");
		denominator = scanner.nextInt();
		
		Fraction firstFraction = new Fraction(numerator, denominator);
		
		System.out.println("Enter the numerator of the second fraction ");
		numerator = scanner.nextInt();
		System.out.println("Enter the denominator of the second fraction ");
		denominator = scanner.nextInt();
		
		Fraction secondFraction = new Fraction(numerator, denominator);
		
		System.out.println("The first fraction is " + firstFraction.toString());
		System.out.println("The second fraction is " + secondFraction.toString());
		
		System.out.printf("The decimal value of the first fraction is %.2f\n", firstFraction.decimalValue());
		System.out.printf("The decimal value of the second fraction is %.2f\n", secondFraction.decimalValue());
	
		Fraction reciprocalFirstFracton = firstFraction.reciprocal();
		System.out.println("The reciprocal fo first fraction is " + reciprocalFirstFracton.toString());
		Fraction reciprocalSecondFraction = secondFraction.reciprocal();
		System.out.println("The reciprocal of the second fraction is " + reciprocalSecondFraction.toString());
		
		System.out.println("The product of the two fractions is " + firstFraction.multiply(secondFraction).toString());
		
		System.out.println("Dividing first fraction by the second fractio leads to " + firstFraction.divideBy(secondFraction));
		
		System.out.println("Are both fractions equal ? " + firstFraction.equals(secondFraction));
	}
	
	public int getNumerator(){
		return this.numerator;
	}
	
	public int getDenominator(){
		return this.denominator;
	}

	public String toString(){
		String fractionString = Integer.toString(numerator)
								+ FRACTION_SYMBOL
								+ Integer.toString(denominator);
		
		return fractionString;
	}
	
	public double decimalValue(){
		return((double)this.getNumerator())/((double)this.getDenominator());
	}
	
	public Fraction reciprocal(){
		Fraction reciprocalFraction = new Fraction(this.getDenominator(), this.getNumerator());
		return reciprocalFraction;
	}
	
	public Fraction multiply (Fraction multipliedWith){
		int resultNumerator = this.getNumerator() * multipliedWith.getNumerator();
		int resultDenominator = this.getDenominator() * multipliedWith.getDenominator();
		Fraction resultFraction = new Fraction(resultNumerator, resultDenominator);
		
		return resultFraction;
	}
	
	public Fraction divideBy(Fraction divideBy){
		Fraction resultFraction = this.multiply(divideBy.reciprocal());
		
		return resultFraction;
	}
	
	public boolean equals(Fraction compareWith){
		double result = this.divideBy(compareWith).decimalValue();
		if(result == 1.0){
			return true;
		}
		return false;
	}
}
