/**
 * 
 */
package proj3;

/**
* File:    GumBallTx.java
* Project: CMSC 202 Project 3, Spring 2008
* @author  Chris Mai
* Date:    04/04/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. All instance variables have values when object is created
*/
public class GumBallTx {
	private Gumball gumball; 
	private Money change;  
	private boolean soldOut; 
	private boolean insufficientFunds; 
	private boolean noChange; 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		Money money = new Money(5, 5, 5, 5);
		Gumball gum = new Gumball(null);
		GumBallTx gumTx = new GumBallTx(gum, money, true, false, true);
		System.out.println("Gumball: " + gumTx.getGumball());
		System.out.println("nickel: " + money.nrNickels);
		System.out.println("Sold out? " + gumTx.gumballsSoldOut());
		System.out.println("Funds? " + gumTx.insufficientFundsTendered());
		System.out.println("Change? " + gumTx.noChange());
		*/
	}

	GumBallTx(Gumball gumball, Money change, boolean soldOut, boolean insufficientFunds, boolean noChange) 
	{
		this.gumball = gumball;
		this.change = change;
		this.soldOut = soldOut;
		this.insufficientFunds = insufficientFunds;
		this.noChange = noChange;
	}
	
	/**
	* Name: getChange
	* PreCondition:  change has a value
	* PostCondition: change has been returned
	*/
	public Money getChange()
	{
		return change;
	}
	
	/**
	* Name: getGumball
	* PreCondition: none  
	* PostCondition: gumball returned, even if it's null
	*/
	public Gumball getGumball()
	{
		return gumball;
	}
	
	/**
	* Name: gumballsSoldOut
	* PreCondition:  none
	* PostCondition: boolean status of soldOut
	*/
	public boolean gumballsSoldOut()
	{
		return soldOut;
	}
	
	/**
	* Name: insufficientFundsTendered
	* PreCondition:  none
	* PostCondition: boolean status of insufficient funds
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

