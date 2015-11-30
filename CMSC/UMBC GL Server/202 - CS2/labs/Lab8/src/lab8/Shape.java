/**
 * 
 */
package lab8;

/**
 * @author chrmai1
 *
 */
public abstract class Shape {
	private String color;
	
	Shape(String color)
	{
		this.color = color;
	}
	
	public String getColor()
	{
		return color;
	}
	
	public abstract double getArea();
		
	public abstract void draw();
}
