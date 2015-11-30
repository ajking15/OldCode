/**
 * 
 */
package proj3;

/**
* File:    CashRegister.java
* Project: CMSC 202 Project 3, Spring 2008
* @author  Chris Mai
* Date:    04/04/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. when made number of coins = 5 and bills = 0
*/

import java.text.DecimalFormat;

/**
 * @author chrmai1
 *
 */
public class CashRegister {
	final private int defaultNickels = 5;
	final private int defaultDimes = 5;
	final private int defaultQuarters = 5;
	final private int defaultBills = 0;
	final public double nickelValue = 0.05;
	final public double dimeValue = 0.10;
	final public double quarterValue = 0.25;
	final public double billValue = 1.00;
	final public double priceGumball = 0.35;
	final public double priceCandyBar = 0.65;
	final private double endValue = 0.01;
	final private int ZERO = 0;
	public Money change;
	public Money cashDrawer; //this is basically the cash drawer for the register
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		CashRegister register = new CashRegister();
		Money change;
		
		System.out.println("coins in cashDrawer: " + register.cashDrawer.nrNickels
				+ "/" + register.cashDrawer.nrDimes
				+ "/" + register.cashDrawer.nrQuarters
				+ "/" + register.cashDrawer.nrBills);
		register.addNickels(5);
		register.addDimes(4);
		register.addQuarters(3);
		register.addBills(2);
		
		System.out.println("coins in cashDrawer: " + register.cashDrawer.nrNickels
				+ "/" + register.cashDrawer.nrDimes
				+ "/" + register.cashDrawer.nrQuarters
				+ "/" + register.cashDrawer.nrBills);
		
		register.rmExcessFunds();
		
		System.out.println("coins in cashDrawer: " + register.cashDrawer.nrNickels
				+ "/" + register.cashDrawer.nrDimes
				+ "/" + register.cashDrawer.nrQuarters
				+ "/" + register.cashDrawer.nrBills);
		
		change = register.greedyAlgorithim(0.35);
		
		System.out.println("coins in change: " + change.nrNickels
				+ "/" + change.nrDimes
				+ "/" + change.nrQuarters
				+ "/" + change.nrBills);	
				*/	
	}
	
	public CashRegister() //this will be made in snack machine so made only once
	{
		cashDrawer = new Money(defaultNickels, defaultDimes, defaultQuarters, defaultBills);
	}
	
	/**
	* Name: addNickels
	* PreCondition:  numNickels is positive
	* PostCondition: numNickels has been added
	*/
	public void addNickels(int numNickels)
	{
		cashDrawer.nrNickels += numNickels;
	}
	
	/**
	* Name: addDimes 
	* PreCondition:  numDimes is positive
	* PostCondition: numDimes has been added
	*/
	public void addDimes(int numDimes)
	{
		cashDrawer.nrDimes += numDimes;
	}
	
	/**
	* Name: addQuarters 
	* PreCondition:  numQuarters is positive
	* PostCondition: numQuarters has been added
	*/
	public void addQuarters(int numQuarters)
	{
		cashDrawer.nrQuarters += numQuarters;
	}
	
	/**
	* Name: addBills
	* PreCondition:  numBills is positive 
	* PostCondition: numBills has been added
	*/
	public void addBills(int numBills)
	{
		cashDrawer.nrBills += numBills;
	}
	
	
	/**
	* Name: rmExcessFunds
	* PreCondition:  none
	* PostCondition: number of coins/bills for cashDrawer have been reset
	*/
	public Money rmExcessFunds()
	{
		//money object to hold excess funds
		Money excess = new Money(ZERO, ZERO, ZERO, ZERO);
		
		/*
		 * checks if the number of nickels/dimes/quarters/bills is greater
		 *  than the default number of nickels/dimes/quarters/bills. 
		 *  if yes then excess nickels is calculated
		 * and number in cash drawer are reset
		 */
		if(cashDrawer.nrNickels > defaultNickels)
		{
			excess.nrNickels = cashDrawer.nrNickels - defaultNickels;
			cashDrawer.nrNickels = defaultNickels;
		}
		
		if(cashDrawer.nrDimes > defaultDimes)
		{
			excess.nrDimes = cashDrawer.nrDimes - defaultDimes;
			cashDrawer.nrDimes = defaultDimes;
		}
		
		if(cashDrawer.nrQuarters > defaultQuarters)
		{
			excess.nrQuarters = cashDrawer.nrQuarters - defaultQuarters;
			cashDrawer.nrQuarters = defaultQuarters;
		}
		
		if(cashDrawer.nrBills > defaultBills)
		{
			excess.nrBills = cashDrawer.nrBills - defaultBills;
			cashDrawer.nrBills = defaultBills;
		}
		
		return excess;
	}
	
	/**
	* Name:  greedyAlgorithim 
	* PreCondition:  cash is a positive double 
	* PostCondition: change correctly calculated
	*/
	public Money greedyAlgorithim(double cash)
	{
		//money object to hold change
		change = new Money(ZERO, ZERO, ZERO, ZERO);
		//boolean objects to control the loops
		boolean nickel = true;
		boolean dime = true;
		boolean quarter = true;
		boolean bill = true;
		
		/*
		 *decimal format is used because the subtraction of doubles yields
		 *numbers such as .1500000000002 which messes with everything i do 
		 */
		DecimalFormat df2 = new DecimalFormat( "0.00" );
		
		/*
		 * checks while bill is true and bill is true as long as
		 * change required requires bills
		 */
		while(bill == true)
		{
			if(cash >= billValue)//checks if change requires a dollar
			{
				if(cashDrawer.nrBills > ZERO)//checks the cashDrawer for a dollar
				{
					cash -= billValue; //lowers the amount that needs to be returned
					change.addBill();  //adds a dollar to the change object
					cash = new Double(df2.format(cash)).doubleValue(); //formats cash
				} else {
					bill = false; //if there are no dollar bills loop is closed
				}
			} else {
				bill = false; //if change does not require a dollar loop is closed
			}
		}
		/*
		 * same comments for quarter/dime/nickel loops below as given
		 * in bill above
		 */
		while(quarter == true)
		{
			if(cash >= quarterValue)
			{
				if(cashDrawer.nrQuarters > 0)
				{
					cash -= quarterValue;
					change.addQuarter();
					cash = new Double(df2.format(cash)).doubleValue(); 
				} else {
					quarter = false;
				}
			} else {
				quarter = false;
			}
		}
		
		while(dime == true)
		{
			if(cash >= dimeValue)
			{
				if(cashDrawer.nrDimes > 0)
				{
					cash -= dimeValue;
					change.addDime();
					cash = new Double(df2.format(cash)).doubleValue(); 
				} else {
					dime = false;
				}
			} else {
				dime = false;
			}
		}
		
		while(nickel == true)
		{
			if(cash >= nickelValue)
			{
				if(cashDrawer.nrNickels > 0)
				{
					cash -= nickelValue;
					change.addNickel();
					cash = new Double(df2.format(cash)).doubleValue(); 
				} else {
					nickel = false;
				}
			} else {
				nickel = false;
			}
		}
	
		/*
		 * endValue equals 0.01 because even with decimal format 
		 * the cash value will never equal zero
		 */
		if(cash < endValue) 
		{
			return change;
		} else {
			return null;
		}
	}
	
}
