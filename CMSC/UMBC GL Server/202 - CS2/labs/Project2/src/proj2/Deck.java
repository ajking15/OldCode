/**
 * 
 */
package proj2;

import java.util.Random;
/**
 * @author chrmai1
 * 
 */
public class Deck {
	private final int DECK_SIZE = 52;
	public Card deck[] = new Card[DECK_SIZE];
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
	}	
	
	Deck() 
	{
		// Build initial deck of 52
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
	
	//shuffle deck needs some help
	public void shuffleDeck(long gameNr)
	{
		Random m = new Random();
		m.setSeed(gameNr);
		
		int k = 0;
		
		//the shuffle algorithim
		int N = 51;
		
		while(N != 0)
		{
			k = Math.abs(m.nextInt()) % (DECK_SIZE);
			
			Card temp = new Card(1,1);
			temp = deck[k];
			deck[k] = deck[N];
			deck[N] = temp;
			
			N--;
		}
	}
	
}
