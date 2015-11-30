/**
 * 
 */
package proj3;

/**
* File:    SnackMachine.java
* Project: CMSC 202 Project 3, Spring 2008
* @author  Chris Mai
* Date:    04/04/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. All instance variables have values when object is created
*/

import java.text.DecimalFormat;
/**
 * @author chrmai1
 *
 */
public class SnackMachine {
	private CandyBarDispenser candyDispenser;
	private GumballDispenser gumDispenser;
	private CashRegister register;
	private GumBallTx gumTx;
	private CandyBarTx candyTx;
	private Money change;
	private int ZERO = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		SnackMachine snack = new SnackMachine();
		Gumball gum = new Gumball(null);
		CandyBar candy = new CandyBar(true);
		Money funds = new Money(1,3,2,4);		
		
		snack.addGumballs(gum, 3);
		snack.addCandyBars(candy, 4);
		
		System.out.println("number of gumballs: " + snack.getNrGumballs());
		System.out.println("number of candyBars: " + snack.getNrCandyBars());
		
		System.out.println("Contents of register: " + snack.register.cashDrawer.nrNickels
				+ "/" + snack.register.cashDrawer.nrDimes
				+ "/" + snack.register.cashDrawer.nrQuarters
				+ "/" + snack.register.cashDrawer.nrBills);
		
		snack.addFunds(funds);
		
		System.out.println("Contents of register: " + snack.register.cashDrawer.nrNickels
				+ "/" + snack.register.cashDrawer.nrDimes
				+ "/" + snack.register.cashDrawer.nrQuarters
				+ "/" + snack.register.cashDrawer.nrBills);
		
		snack.removeExcessFunds();
		
		System.out.println("Contents of register: " + snack.register.cashDrawer.nrNickels
				+ "/" + snack.register.cashDrawer.nrDimes
				+ "/" + snack.register.cashDrawer.nrQuarters
				+ "/" + snack.register.cashDrawer.nrBills);
		*/
	}

	public SnackMachine()
	{
		candyDispenser = new CandyBarDispenser();
		gumDispenser = new GumballDispenser();
		register = new CashRegister();
	}
	
	/**
	* Name: addGumballs
	* PreCondition:  linked list exists and functional
	* PostCondition: linked list has more gumballs
	*/
	public void addGumballs(Gumball gBall, int count)
	{
		for(int i = 0; i < count; i++)
		{
			gumDispenser.addGumball(gBall);
		}
	}
	
	/**
	* Name: buyGumball
	* PreCondition:  none
	* PostCondition: status of attempted transaction
	*/
	public GumBallTx buyGumball(Money money)
	{
		//temporary money object copy of money sent in
		Money temp = new Money(money.nrNickels, money.nrDimes, money.nrQuarters, money.nrBills);
	
		DecimalFormat df2 = new DecimalFormat( "0.00" );
		
		//calculates money that is sent in
		double cash = ZERO;
		cash += money.nrNickels * register.nickelValue;
		cash += money.nrDimes * register.dimeValue;
		cash += money.nrQuarters * register.quarterValue;
		cash += money.nrBills;
		
		//calculates change necessary and formats it
		double cashTemp = cash - register.priceGumball;
		cashTemp = new Double(df2.format(cashTemp)).doubleValue(); 
	
		if(cash < register.priceGumball)
		{
			//checks for sufficient funds
			Gumball gum = null;
			gumTx = new GumBallTx(gum, temp, false, true, false);
			return gumTx;
		} else 
			if(register.greedyAlgorithim(cashTemp) == null)
			{
				//checks if change is possible
				Gumball gum = null;
				gumTx = new GumBallTx(gum, temp, false, false, true);
				return gumTx;
			} else 
				if(gumDispenser.empty()){
				//checks if there is a gumball in the linkedlist
				Gumball gum = null;
				gumTx = new GumBallTx(gum, temp, true, false, false);
				return gumTx;
			}
		
		cash -= register.priceGumball;
		
		cash = new Double(df2.format(cash)).doubleValue();

		//creates change object to reflect change as divined by greedyAlgorithim
		change = new Money(register.greedyAlgorithim(cash).nrNickels,
						   register.greedyAlgorithim(cash).nrDimes,
						   register.greedyAlgorithim(cash).nrQuarters,
						   register.greedyAlgorithim(cash).nrBills);
		
		//alters cashDrawer to reflect removed coins
		for(int i = 0; i < change.nrNickels; i++)
		{	register.cashDrawer.removeNickel();}
		for(int i = 0; i < change.nrDimes; i++)
		{	register.cashDrawer.removeDime();}
		for(int i = 0; i < change.nrQuarters; i++)
		{	register.cashDrawer.removeQuarter();}
		for(int i = 0; i < change.nrBills; i++)
		{	register.cashDrawer.removeBill();}
		
		//changes cashDrawer to reflect added money
		for(int i = 0; i < money.nrNickels; i++)
		{	register.cashDrawer.addNickel();}
		for(int i = 0; i < money.nrDimes; i++)
		{	register.cashDrawer.addDime();}
		for(int i = 0; i < money.nrQuarters; i++)
		{	register.cashDrawer.addQuarter();}
		for(int i = 0; i < money.nrBills; i++)
		{	register.cashDrawer.addBill();}
		
		gumTx = new GumBallTx(gumDispenser.removeGumball(), change, false, false, false);
	
		return gumTx;
	}
	
	/**
	* Name: getNrGumballs
	* PreCondition:  none
	* PostCondition: size of gumballs linked lists
	*/
	public int getNrGumballs()
	{
		return gumDispenser.gumball.size();
	}
	
	/**
	* Name: addCandyBars
	* PreCondition:  none
	* PostCondition: linked list will have more candy
	*/
	public void addCandyBars(CandyBar bar, int count)
	{
		for(int i = ZERO; i < count; i++)
		{
			candyDispenser.addCandy(bar);
		}
	}
	
	/**
	* Name: getNrCandyBars
	* PreCondition:  none
	* PostCondition: size of the candybar linkedlist
	*/
	public int getNrCandyBars()
	{
		return candyDispenser.candyBar.size();
	}
	
	/**
	* Name: buyCandyBar
	* PreCondition:  none
	* PostCondition: state of attempted transaction
	*/
	public CandyBarTx buyCandyBar(Money money)
	{
		//for formatting the pesky type double subtractions
		DecimalFormat df2 = new DecimalFormat( "0.00" );
	
		//a duplicate of the money object sent in
		Money temp = new Money(money.nrNickels, money.nrDimes, money.nrQuarters, money.nrBills);
		
		//double object cash is given total value of money sent in
		double cash = ZERO;
		cash += money.nrNickels * register.nickelValue;
		cash += money.nrDimes * register.dimeValue;
		cash += money.nrQuarters * register.quarterValue;
		cash += money.nrBills;
		
		//cashTemp is the amount of money that must be returned as change
		double cashTemp = cash - register.priceCandyBar;

		//format
		cashTemp = new Double(df2.format(cashTemp)).doubleValue(); 
		
		//checks for sufficient funds
		if(cash < register.priceCandyBar)
		{
			CandyBar candy = null;
			candyTx = new CandyBarTx(candy, temp, false, true, false);
			return candyTx;
		} else 
			if(register.greedyAlgorithim(cashTemp) == null)
			{
				//checks if change is possible
				CandyBar candy = null;
				candyTx = new CandyBarTx(candy, temp, false, false, true);
				return candyTx;
			} else 
				if(candyDispenser.empty()){
				//checks for a gumball
				CandyBar candy = null;
				candyTx = new CandyBarTx(candy, temp, true, false, false);
				return candyTx;
			}
		
		
		cash -= register.priceCandyBar;
		cash = new Double(df2.format(cash)).doubleValue(); 

		//creates change object with results of greedy algorithim
		change = new Money(register.greedyAlgorithim(cash).nrNickels,
						   register.greedyAlgorithim(cash).nrDimes,
						   register.greedyAlgorithim(cash).nrQuarters,
						   register.greedyAlgorithim(cash).nrBills);
	
		//alters the contents of the cashDrawer to reflect removed coins
		for(int i = 0; i < change.nrNickels; i++)
		{	register.cashDrawer.removeNickel();}
		for(int i = 0; i < change.nrDimes; i++)
		{	register.cashDrawer.removeDime();}
		for(int i = 0; i < change.nrQuarters; i++)
		{	register.cashDrawer.removeQuarter();}
		for(int i = 0; i < change.nrBills; i++)
		{	register.cashDrawer.removeBill();}
		
		//alters the contents of the cashDrawer to reflect added coins
		for(int i = 0; i < money.nrNickels; i++)
		{	register.cashDrawer.addNickel();}
		for(int i = 0; i < money.nrDimes; i++)
		{	register.cashDrawer.addDime();}
		for(int i = 0; i < money.nrQuarters; i++)
		{	register.cashDrawer.addQuarter();}
		for(int i = 0; i < money.nrBills; i++)
		{	register.cashDrawer.addBill();}
		
		candyTx = new CandyBarTx(candyDispenser.removeCandy(), change, false, false, false);
		
		return candyTx;
		
	}
	
	/**
	* Name: addFunds
	* PreCondition:  none
	* PostCondition: funds to be added were added
	*/
	public void addFunds(Money money)
	{
		register.addNickels(money.nrNickels);
		register.addDimes(money.nrDimes);
		register.addQuarters(money.nrQuarters);
		register.addBills(money.nrBills);
	}

	/**
	* Name: getCashOnHand
	* PreCondition:  cashDrawer exists
	* PostCondition: money object with the money status of the register is returned
	*/
	public Money getCashOnHand()
	{
		return register.cashDrawer;
	}
	
	/**
	* Name: removeExcessFunds
	* PreCondition:  register exists
	* PostCondition: excess funds removed
	*/
	public Money removeExcessFunds()
	{
		return register.rmExcessFunds();
	}
}
