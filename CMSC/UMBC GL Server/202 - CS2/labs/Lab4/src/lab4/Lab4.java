/**
 *File: Lab4.java 
 */
package lab4;
import java.util.Scanner;
/**
 * @author chrmai1
 *
 */
public class Lab4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		int x = 1;
		int y = 1;
		
		System.out.print("Enter the x-coordinate of the upperLeft :");
		x = scanner.nextInt();
		System.out.print("Enter the y-coordinate of the upperLeft :");
		y = scanner.nextInt();
		
		Point upperLeft = new Point(x, y);
		
		System.out.print("Enter the x-coordinate of the lowerLeft :");
		x = scanner.nextInt();
		System.out.print("Enter the y-coordinate of the lowerLeft :");
		y = scanner.nextInt();
		
		Point lowerLeft = new Point(x, y);
		
		System.out.print("Enter the x-coordinate of the lowerRight :");
		x = scanner.nextInt();
		System.out.print("Enter the y-coordinate of the lowerRight :");
		y = scanner.nextInt();
		
		Point lowerRight = new Point(x, y);
		
		System.out.print("Enter the x-coordinate of the upperRight:");
		x = scanner.nextInt();
		System.out.print("Enter the y-coordinate of the upperRight:");
		y = scanner.nextInt();
		
		Point upperRight = new Point(x, y);
		
		Rectangle rectangle = new Rectangle(upperLeft, lowerLeft, lowerRight, upperRight);
		
		System.out.printf("Length of rectangle : %.2f\n", rectangle.getLength());
		System.out.printf("Width of rectangle : %.2f\n", rectangle.getWidth());
		System.out.printf("Area of rectangle : %.2f\n", rectangle.getArea());
		System.out.printf("Perimeter of rectangle : %.2f\n", rectangle.getPerimeter());
		
	}

	public static double distance(Point point1, Point point2) {
		
		int xDiff = point2.getX() - point1.getX();
		int yDiff = point2.getY() - point1.getY();
		double distance = Math.sqrt(xDiff*xDiff + yDiff*yDiff);
		
		return distance;
		
	}
	 

	
}

