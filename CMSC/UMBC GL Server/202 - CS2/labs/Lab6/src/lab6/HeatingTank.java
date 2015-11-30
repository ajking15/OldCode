/**
 * 
 */
package lab6;

/**
 * @author chrmai1
 *
 */
public class HeatingTank extends Tank{

	private int temperatureChange;
	private int expansionFactor;
	
	/**
	 * @param args
	 */
	public HeatingTank(int capacity, int volume)
	{
		super(capacity, volume);
		temperatureChange = 0;
		expansionFactor = 0;
	}
	
	public HeatingTank(int volume, int capacity, int temperatureChange, int expansionFactor)
	{
		super(capacity, volume);
		this.temperatureChange = temperatureChange;
		this.expansionFactor = expansionFactor;
	}
	
	public int getTemperatureChange( )
	{
		return temperatureChange;
	}
	
	 public void setTemperatureChange(int temperatureChange)
	 {
		 this.temperatureChange = temperatureChange;
	 }
	
	 public int getExpansionFactor()
	 {
		 return this.expansionFactor;
	 }
	 
	 public void setExpansionFactor(int expansionFactor)
	 {
		 this.expansionFactor = expansionFactor;
	 }
	 
	 public int getVolume()
	 {
		 return super.getVolume() + temperatureChange * expansionFactor;
	 }
}
