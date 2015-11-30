package proj4;
import java.util.*;

public class CMSC202Math {

	private static Random rng = null;
	private static int seed = 12345;
	
	
	public static int nextRandomInt( )
	{
		if (rng == null)
			rng = new Random(seed);
		return Math.abs(rng.nextInt());
	}
	
	// returns a random integer from 1 - limit inclusive
	public static int nextRandomInt( int limit )
	{
		if (rng == null)
			rng = new Random(seed);
		return Math.abs(rng.nextInt(limit)) + 1;
	}
	
	public static void main(String[] args)
	{
		int x = CMSC202Math.nextRandomInt();
		System.out.println(x);
		x = CMSC202Math.nextRandomInt();
		System.out.println(x);
		x = CMSC202Math.nextRandomInt();
		System.out.println(x);
		x = CMSC202Math.nextRandomInt(10);
		System.out.println(x);
		x = CMSC202Math.nextRandomInt(10);
		System.out.println(x);
		x = CMSC202Math.nextRandomInt(10);
		System.out.println(x);
		x = CMSC202Math.nextRandomInt(10);
		System.out.println(x);

	}
}
