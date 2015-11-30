package lab5;

public class MyVector {
	private double[] vector;
	//instance methods - all functions will invoked by objects
	MyVector(double vector[]) {
		// Make a  deep copy
		this.vector = new double[vector.length];
		
		for(int i = 0; i < vector.length; i++)
		{
			this.vector[i] = vector[i];
		}
	}
	
	/**
	* Name: dotProduct
	* PreCondition:  v is not null
	*                vectors within this and v are the same size
	* PostCondition: Returns the calculated dot product of this and v's vector
	* @params v - A MyVector object which contains a vector 
	*/
	public double dotProduct(MyVector v) {
	    // the dot product of two vectors v1 and v2 is calculated as
	    // v1[0] * v2[0] + v1[1] * v2[1] + ...
		double sum = 0;
		
		for(int i = 0; i < v.vector.length; i++)
		{
			sum += this.vector[i] * v.vector[i];
		}
		return sum;
	}	
	/**
	* Name: getArray
	* PreCondition:  none
	* PostCondition: Returns copy of the array
	*/
	public double[] getArray() {
		// Return deep copy
		double array[] = new double[vector.length];
		
		for(int i = 0; i < vector.length; i++)
		{
			array[i] = vector[i];
		}
		
		return array;
	}
}
