/**
 * 
 */
package lab8;

/**
 * @author chrmai1
 *
 */
public class  Triangle extends Shape{
	private int height;
	private int width;
	
	Triangle(int height, int width, String color)
	{
		super(color);
		this.height = height;
		this.width = width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public double getArea()
	{
		return (getHeight() * getWidth()) * .5;
	}
	
	public void draw()
	{
		System.out.println("Drawing a triangle with color " + super.getColor());
	}
}
