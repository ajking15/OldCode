package lab11;
import java.util.Scanner;


public class Lab11 {
	
	public static void main(String []args){
		Scanner keyboard = new Scanner(System.in);
		
		Patron patron1 = null;
		Patron patron2 = null;
		int age = 0;
		
		String firstName = null;
		String secondName = null;
		System.out.println("Enter firstName of patron 1");
		firstName = keyboard.next();
		System.out.println("Enter secondName of patron 1");
		secondName = keyboard.next();
		System.out.println("Enter age of patron 1");
		age = keyboard.nextInt();
		patron1 = new Patron(firstName, secondName, age);
		
		System.out.println("Enter firstName of patron 2");
		firstName = keyboard.next();
		System.out.println("Enter secondName of patron 2");
		secondName = keyboard.next();
		System.out.println("Enter age of patron 2");
		age = keyboard.nextInt();
    
		patron2 = new Patron(firstName, secondName, age);
		
		System.out.println("Comapring Patron 1 and Patron 2 : " + patron1.compareTo(patron2));
		
	} 

}
