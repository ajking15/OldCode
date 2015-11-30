package lab9;

import java.util.Scanner;

public class Lab9 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int var;
		/**
		 *  Stack reference should be defined out of the 'try-catch' block, so 
		 *  that the scope of the reference is not limited to try-catch block.
		 */ 
		Stack stack = null;
		
		// Create Try Catch Block Here

			// To demonstrate exceptions thrown from constructor.
			//stack = new Stack(-1);
			
			stack = new Stack(Stack.MAXSIZE);
			
			String action = "";
			
			while ( action.compareToIgnoreCase("Quit") != 0 ) {
				System.out.print("Enter an action of either Push, Pop, or Quit: ");
				action = scanner.next();
				
				if ( action.compareToIgnoreCase("Push") == 0 ) {
					System.out.print("Enter the next element of stack :");
					var = scanner.nextInt();
					stack.push(var);
				}
				else if ( action.compareToIgnoreCase("Pop") == 0 ) {
					System.out.println(stack.pop());
				}
			}


		// This code is outside of try and catch blocks
		System.out.println("This is after the exception even after one is thrown.");
	}
}
