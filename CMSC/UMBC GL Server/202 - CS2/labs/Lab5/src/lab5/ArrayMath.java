package lab5;

public class ArrayMath {
	//static methods - can be invoked without making an object of this class
	/**
	* Name: dotProduct
	* PreCondition:  arrays are not null
	*                arrays are the same size
	* PostCondition: Returns the calculated dot product
	* @params vec1 - contains a vector to perform dot product with
	* @params vec2 - contains a vector to perform dot product with
	*/
	public static double dotProduct(double vec1[], double vec2[]) {
		//v1[0] * v2[0] + v1[1] * v2[1] + ...
		double sum = 0;
		
		for(int i = 0; i < vec1.length; i++)
		{
			sum += vec1[i] * vec2[i];
		}
		
		return sum;
	}
	
	/**
	* Name: copyArray
	* PreCondition:  array is not null
	* PostCondition: Returns copy of the array
	* @params vec - contains a vector to copy
	*/
	public static double[] copyArray(double vec[]) {
		//Do deep copy
		double array[] = new double[vec.length];
		
		for(int i = 0; i < vec.length; i++)
		{
			array[i] = vec[i];
		}
		
		return array;
		
	}
}
