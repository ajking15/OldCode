/**
 * 
 */
package proj3;

/**
* File:    Money.java
* Project: CMSC 202 Project 3, Spring 2008
* @author  Chris Mai
* Date:    04/04/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. number of coins/bills is greater or equal to 0
*   2. nickels, dimes, quarters, and bills are all initialized 
*/

import java.text.DecimalFormat;

/**
 * @author chrmai1
 *
 */
public class Money {
	
	public int pennies;
	public int nrNickels;
	public int nrDimes;
	public int nrQuarters;
	public int nrBills;
	 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		Money money = new Money(3,3,3,3);
		
		System.out.println("coins in change: " + money.nrNickels
				+ "/" + money.nrDimes
				+ "/" + money.nrQuarters
				+ "/" + money.nrBills);	
		
		money.addNickel();
		money.addDime();
		money.addQuarter();
		money.addBill();
		
		System.out.println("coins in change: " + money.nrNickels
				+ "/" + money.nrDimes
				+ "/" + money.nrQuarters
				+ "/" + money.nrBills);
		
		money.removeNickel();
		money.removeDime();
		money.removeQuarter();
		money.removeBill();
		
		System.out.println("coins in change: " + money.nrNickels
				+ "/" + money.nrDimes
				+ "/" + money.nrQuarters
				+ "/" + money.nrBills);
		*/
	}

	Money(int pennies)
	{
		this.pennies = pennies; 
	}
	
	public Money(int nrNickels, int nrDimes, int nrQuarters, int nrBills)
	{
			
		this.nrNickels = nrNickels;
		this.nrDimes = nrDimes;
		this.nrQuarters = nrQuarters;
		this.nrBills = nrBills;
	}
	
	/**
	* Name: addNickel 
	* PreCondition:  none
	* PostCondition: number of nickels goes up by one
	*/
	public void addNickel()
	{
		nrNickels++;
	}
	
	/**
	* Name: removeNickel 
	* PreCondition:  number of nickels is at least 1
	* PostCondition: number of nickels goes down by one
	*/
	public void removeNickel()
	{
		nrNickels--;
	}
	
	/**
	* Name: addDime
	* PreCondition:  none  
	* PostCondition: number of dimes goes up by one
	*/
	public void addDime()
	{
		nrDimes++;
	}
	
	/**
	* Name: removeDime 
	* PreCondition:  number of dimes is at least 1
	* PostCondition: number of dimes goes down by 1
	*/
	public void removeDime()
	{
		nrDimes--;
	}
	
	/**
	* Name: addQuarter 
	* PreCondition:  none
	* PostCondition: nrQuarters goes up by one
	*/
	public void addQuarter()
	{
		nrQuarters++;
	}
	
	/**
	* Name: removeQuarter 
	* PreCondition:  nrQuarter at least 1
	* PostCondition: nrQuarter goes down by one
	*/
	public void removeQuarter()
	{
		nrQuarters--;
	}
	
	/**
	* Name: addBill 
	* PreCondition:  none  
	* PostCondition: nrBills goes up by one
	*/
	public void addBill()
	{
		nrBills++;
	}
	
	/**
	* Name: removeBill 
	* PreCondition:  nrBills at least one
	* PostCondition: nrBills goes down by one
	*/
	public void removeBill()
	{
		nrBills--;
	}
	
	/**
	* Name: toCents 
	* PreCondition:  none  
	* PostCondition: pennies
	*/
	public int toCents()
	{
		/*
		 * since pennies is a multiple of five cents
		 * nrNickels is added normally while the rest of the
		 * coins/bill are added as they are represented in nickels
		 */
		pennies = 0;
		pennies += (nrNickels);
		pennies += (nrDimes * 2);
		pennies += (nrQuarters * 5);
		pennies += (nrBills * 20);
		
		return pennies;
	}

	/**
	* Name: toString
	* PreCondition:  pennies has a value 
	* PostCondition: a string is returned
	*/
	public java.lang.String toString()
	{
		String cash = "";
		
		toCents();
		
		DecimalFormat df2 = new DecimalFormat( "0.00" );
		
		//this is done to round the total value in cents to the 0.00 decimal
		//places
		double	penny = new Double(df2.format(pennies * 0.05)).doubleValue(); 	
		
		cash = "Number of Nickels: " + nrNickels
				+ "\nNumber of Dimes: " + nrDimes +
				"\nNumber of Quarters: " + nrQuarters +
				"\nNumber of Bills: " + nrBills +
				"\nTotal Value in cents: " + (penny * 100) ;
		
		 return cash;
	}
	
}
