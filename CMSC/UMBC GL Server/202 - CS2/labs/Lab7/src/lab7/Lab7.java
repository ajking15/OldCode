package lab7;

import java.util.Scanner;

public class Lab7 {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("Enter your pet's name: ");
		String name = keyboard.next();
		System.out.print("Enter your pet's age: ");
		int age = keyboard.nextInt();
		System.out.print("Enter pet type (Dog or Cat): ");
		String type = keyboard.next();
		
		Pet pet;
		
		if (type.equalsIgnoreCase("Dog")) {
			//Step 2A: Create new Dog object and assign to dog variable
			Dog dog = new Dog(name, age);
			//Step 2B: Make the dog object speak
			dog.speak();
			//Step 2C:  Call Lab7 class method printNameAge using object referenced by the dog variable
			printNameAge(dog);
			//Step 2D: Assign the dog object to the pet variable
			//pet = dog or pet = (Pet)dog
			pet = dog;
		}
		else {
			//Step 3A: Create new Cat object and assign to cat variable
			Cat cat = new Cat(name, age);
			//Step 3B: Make the cat object speak
			cat.speak();
			//Step 3C:  Call Lab7 class method printNameAge using object referenced by the cat variable
			printNameAge(cat);
			//Step 3D: Assign the cat object to the pet variable
			//pet = cat or pet = (Pet)cat
			pet = cat;
		}
		
		//Step 4:  Call Lab7 class method printNameAge using object referenced by the pet variable
		printNameAge(pet);
	}
	
	public static void printNameAge(Pet pet) {
		System.out.println(pet.getName() + ": " + pet.getAge());
	}
	
	// Step5: Implement public static void petSpeak(Pet pet) which calls the speak method for the pet
	public static void petSpeak(Pet pet)
	{
	//	pet.speak();   Doesn't work because speak is only defined for derived classes
	}
}
