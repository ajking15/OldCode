/**
 * 
 */
package lab8;

/**
 * @author chrmai1
 *
 */
public class Rectangle extends Shape{
	private int length;
	private int width;
	
	Rectangle(int length, int width, String color)
	{
		super(color);
		this.length = length;
		this.width = width;
	}
	
	public int getLength()
	{
		return length;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public double getArea()
	{
		return getLength() * getWidth();
	}
	
	public void draw()
	{
		System.out.println("Drawing a rectangle with color " + super.getColor());
	}
}
