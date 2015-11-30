/**
 * 
 */
package proj2;

import java.util.Random;

/**
* File:    Deck.java
* Project: CMSC 202 Project 2, Spring 2008
* @author  Chris Mai
* Date:    03/09/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. deck is a 52 card deck
*/

public class Deck {
	private final int DECK_SIZE = 52;
	public Card deck[] = new Card[DECK_SIZE];
	
	Deck() 
	{
		int index = 0;

		for (int i = 1; i < 5; i++)
		{
			for (int j = 1; j < 14; j++) 
			{
				Card temp = new Card(j, i);
				deck[index] = temp;				
				index++;
			}
		}
	}
	
	/**
	 * Name:  shuffleDeck
	 * Precondition:  gameNr is of type long
	 * PostCondition: deck is rearranged
	 */
	public void shuffleDeck(long gameNr)
	{
		Random rng = new Random(gameNr);
		//rng.setSeed(gameNr);
	    
	    int i, k;
	    
	    for(i = 51; i >= 0; i--)
	    {
	    	Card temp = new Card(1,1);
	        k = rng.nextInt(i + 1); 
	        temp = deck[i];
	        deck[i] = deck[k];
	        deck[k] = temp;
	    }
	}
}