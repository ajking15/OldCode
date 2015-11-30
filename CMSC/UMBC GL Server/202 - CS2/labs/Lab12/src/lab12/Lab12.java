/**
 * File: Lab9.java
 * Project: CMSC 202 Project lab8
 * @author audumbar
 * Date: Nov 4, 2007
 * Section: 0101
 * E-mail: auduc1@umbc.edu
 *
 */
package lab12;

/**
 * @author audumbar
 *
 */
public class Lab12 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayListDemo listDemo = new ArrayListDemo(); 
		
		listDemo.addElements();
		listDemo.printList();
		
		listDemo.changeElement();
		System.out.println("Modified Array List :");
		listDemo.printList();
		
		listDemo.removeListElement();
		System.out.println("Array List after removing the element: ");
		listDemo.printList();
		
		listDemo.clearList();
	}

}
