/**
 * 
 */
package lab4;

/**
 * @author chrmai1
 *
 */
public class Rectangle {
	private Point upperLeft;
	private Point lowerLeft;
	private Point lowerRight;
	private Point upperRight;
	
	public Rectangle (Point upperLeft, Point lowerLeft, Point lowerRight, Point upperRight)
	{
		this.upperLeft = upperLeft;
		this.lowerLeft = lowerLeft;
		this.lowerRight = lowerRight;
		this.upperRight = upperRight;
	}
	
	public double getLength()
	{
		double d1 = Lab4.distance(upperLeft, upperRight);
		double d2 = Lab4.distance(upperLeft, lowerLeft);
		
		if(d1 >= d2)
		{
			return d1;
		} else {
			return d2;
		}
	}
	
	public double getWidth()
	{
		double d1 = Lab4.distance(upperLeft, upperRight);
		double d2 = Lab4.distance(upperRight, lowerRight);
		
		if(d1 <= d2)
		{
			return d1;
		} else {
			return d2;
		}
	}
	
	public double getArea(){
		return getLength() * getWidth();
	}
	
	public double getPerimeter(){
		return (2 * getLength() + 2 * getWidth());
	}
	
}
