/**
 * 
 */
package lab6;

/**
 * @author chrmai1
 *
 */
public class Tank {
	
	private int capacity;
	private int volume;
	/**
	 * @param args
	 */
	
	public Tank()
	{
		capacity = 0;
		volume = 0;
	}
	
	public Tank(int capacity, int volume)
	{
		this.capacity = capacity;
		this.volume = volume;
	}
	
	public int getVolume()
	{
		return volume;
	}
	
	public int getCapacity()
	{
		return capacity;
	}
}
