package lab10;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Lab10 {
	
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner fileReader = null;
		Student students[];
		int numStudents = 0;

		//Step a: Perform number of arguments check 
		if (/* Write condition here */)
		{
			System.err.println("Incorrect number of parameters");
			System.exit(-1);
		}
		
		
		try
		{
			/*Step b: Assign the File's input stream to the 
			scanner object. 
			*/

			/*Step c: Read the number of students from the file
			 and store in numStudents 
			*/
	
	
			System.out.println("The number of students is : " + numStudents);
		
			/*Step d: Allocate memory for students array based on 
			numStudents
			*/	
	
	
			int i = 0;
		
			/*Step e: Read from the file line by line using the 
			scanner object and store the values read in the 
			students array.
			*/
		
			while (/* end of file condition */)
			{
				// code to read the tuple [name] [id] from file 
				
			}
		
			/* Step f: close the file reader as you are done 
			reading the file
			*/
		
		
			/*Step g: Print out the information read from the 
			file which is stored in students array in the form
		 	Student name = [name] and id = [id]
		 	*/

		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found " 
							+ e.getMessage());
			System.exit(-1);
		}		
	}	
}
