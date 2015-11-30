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
		if (args.length < 1)
		{
			System.err.println("Incorrect number of parameters");
			System.exit(-1);
		}
		
		
		try
		{
			/*Step b: Assign the File's input stream to the 
			scanner object. 
			*/
			fileReader = new Scanner(new FileInputStream(args[0]));
			/*Step c: Read the number of students from the file
			 and store in numStudents 
			*/
			numStudents = fileReader.nextInt();
	
			System.out.println("The number of students is : " + numStudents);
		
			/*Step d: Allocate memory for students array based on 
			numStudents
			*/	
			students = new Student[numStudents];
	
			int i = 0;
		
			/*Step e: Read from the file line by line using the 
			scanner object and store the values read in the 
			students array.
			*/
		
			while (fileReader.hasNextLine())
			{
				// code to read the tuple [name] [id] from file 
				students[i] = new Student(fileReader.next(), fileReader.nextInt());
				i++;
			}
		
			/* Step f: close the file reader as you are done 
			reading the file
			*/
			fileReader.close();
		
			/*Step g: Print out the information read from the 
			file which is stored in students array in the form
		 	Student name = [name] and id = [id]
		 	*/
			for(int j = 0; j < i; j++)
			{
				System.out.print("Student Name: " + students[j].getName());
				System.out.println(" and id: " + students[j].getId());
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found " 
							+ e.getMessage());
			System.exit(-1);
		}		
	}	
}
