/**
 * File: ArrayListDemo.java
 * Project: CMSC 202 Project lab9
 * @author audumbar
 * Date: Nov 4, 2007
 * Section: 0101
 * E-mail: auduc1@umbc.edu
 */
package lab12;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author audumbar
 *
 */
public class ArrayListDemo {

	ArrayList<Integer> list = null;
	public static final int MIN_CAPACITY = 10;
	Scanner keyboard;
	
	public ArrayListDemo() {
	  //add code to initialize the list, set the minimum capacity of list using constructor
	  //Also initialize the scanner variable
		list = new ArrayList<Integer>(MIN_CAPACITY);
		list.ensureCapacity(MIN_CAPACITY);
		keyboard = new Scanner(System.in);
	}
	
	/**
	 * @return the list
	 */
	public ArrayList<Integer> getList() {
		return list;
	}

	/**
	 * This method adds random integer elements to the list 
	 */
	public void addElements()
	{
		Integer listElement;
		Random randGenerator = new Random(); 
	
		//write a for loop here to generate random integers using randGenerator object
		//and put them in list arraylist using put add method
		for(listElement = 0; listElement < MIN_CAPACITY; listElement++)
		{
			list.add(randGenerator.nextInt(50));
		}
	}
	/**
	 * This method prints the given list 
	 */
	public void printList() {

		System.out.println("Given Array List: ");
		
    //use for loop to iterate through the list and print each integer, make use of 'size' method
		for(int i = 0; i < list.size(); i++)
		{
			System.out.print(list.get(i) + ", ");
		}
		System.out.println("");
	}
	/**
	 * This method replaces replaces the given integer with new integer.
	 */
	public void changeElement() {
		System.out.println("Enter the number to be changed:");
		int oldNum = keyboard.nextInt();
		Integer oldInteger = new Integer(oldNum);
		System.out.println("Enter the new number:");
		int newNum = keyboard.nextInt();
		Integer newInteger = new Integer(newNum);
		
		//use for loop to iterate through the list and print each integer, make use of 'size' method
		//compare the ith element with oldInteger, and if matches then replace it with the newInteger
		for(int i = 0; i < list.size(); i++)
		{
			Integer temp = new Integer(list.get(i));
			
			if(temp.compareTo(oldInteger) == 0)
			{
				list.set(i, newInteger);
			}
		}
	}
	
	
	/**
	 * This method accepts the number from the user and removes it from the list.
	 */
	public void removeListElement() {

		System.out.println("Enter the number to be removed:");
		int numberTobeRemoved = keyboard.nextInt();
		Integer elem = new Integer(numberTobeRemoved);
		//remove the number without iterating through the list, use 'indexOf' method
		if(list.indexOf(elem) != -1)
			list.remove(list.indexOf(elem));
		else
			System.out.println("Element not found");
	}
	/**
	 * This method removes all the elements from the list.
	 */
	public void clearList() {
		//remove all the elements from the list 
		list.clear();

		// after removing all elements, print the size of the list
		System.out.println("List Cleared... \nArray size: " + list.size());

	}
}
