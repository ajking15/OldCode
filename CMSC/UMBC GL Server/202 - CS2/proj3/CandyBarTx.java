/**
 * 
 */
package proj3;

/**
* File:    CandyBarTx.java
* Project: CMSC 202 Project 3, Spring 2008
* @author  Chris Mai
* Date:    04/04/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. all fields in the constructor are intialized
*/

/**
 * @author chrmai1
 *
 */
public class CandyBarTx {
	/**
	 * This class takes care of candybar transactions
	 * includes price of candy bar
	 */
	
	private CandyBar candyBar;
	private Money change;
	private boolean soldOut;
	private boolean insufficientFunds;
	private boolean noChange;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		CandyBar candy = new CandyBar(true);
		Money money = new Money(5,5,5,5);
		CandyBarTx candyTx = new CandyBarTx(candy, money, true, true, false);
		
		System.out.println("Bar: " + candyTx.getBar().toString());
		System.out.println("part of change: " + candyTx.getChange().nrBills);
		System.out.println("Sold out? " + candyTx.candyBarsSoldOut());
		System.out.println("Funds? " + candyTx.insufficientFundsTendered());
		System.out.println("Change? " + candyTx.noChange());
		*/
	}
	
	CandyBarTx(CandyBar candyBar, Money change, boolean soldOut, boolean insufficientFunds, boolean noChange) 
	{
		this.candyBar = candyBar;
		this.change = change;
		this.soldOut = soldOut;
		this.insufficientFunds = insufficientFunds;
		this.noChange = noChange;
	}
	
	/**
	* Name: getBar
	* PreCondition:  none  
	* PostCondition: the candybar and if nothing is there null
	*/
	public CandyBar getBar()
	{
		return candyBar;
	}
	
	/**
	* Name: getChange
	* PreCondition:  none
	* PostCondition: change is returned
	*/
	public Money getChange()
	{
		return change;
	}
	
	/**
	* Name: candyBarsSoldOut 
	* PreCondition:  none
	* PostCondition: boolean status of soldOut
	*/
	public boolean candyBarsSoldOut()
	{
		return soldOut;
	}
	
	/**
	* Name: insufficientFundsTendered
	* PreCondition:  none
	* PostCondition: boolean status of insufficientFunds
	*/
	public boolean insufficientFundsTendered()
	{
		return insufficientFunds;
	}
	
	/**
	* Name: noChange
	* PreCondition:  none 
	* PostCondition: boolean status of noChange
	*/
	public boolean noChange()
	{
		return noChange;
	}
	
}
