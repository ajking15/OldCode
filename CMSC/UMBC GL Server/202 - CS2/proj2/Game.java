/**
 * File: Game.java
 * Project: CMSC 202 Project Project2
 * @author Christopher Mai
 * Date: Feb 26, 2008
 * Section: 0401
 * E-mail: chrmai1@umbc.edu
 */
package proj2;

/**
* File:    Game.java
* Project: CMSC 202 Project 2, Spring 2008
* @author  Chris Mai
* Date:    03/09/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. row and column are positive integers
*   2. stack and replay are card[] of size 52
*/

public class Game {
	private int row;
	private int column;
	private Card[][] tableu; 
	private Deck stack;
	private Deck replay;
	private int removedPairs;
	
	public Game(int row, int column) 
	{
		this.row = row;
		this.column = column;
		
		tableu = new Card[this.row][this.column];
	}
	
	/**
	* Name: score
	* PreCondition:  stack.deck[] has already been initialized
	* PostCondition: number of cards that aren't in either the deck or tableau
	*/
	public int score()
	{
		int score = 0;
		for(int i = 0; i < 52; i++)
		{
			if(stack.deck[i] == null)
			{
				++score;
			}
		}
		
		for(int m = 0; m < 5; m++)
		{
			for(int n = 0; n < 5; n++)
			{
				if(tableu[m][n] != null)
				{
					--score;
				}
			}
		}
		return score;
	}
	
	/**
	 * Name: playerWins
	 * Precondition:  game has started and score() works
	 * PostCondition: true or false
	 */
	public boolean playerWins()
	{
		if(score() == 52) 
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Name: newGame
	 * Precondition:  gameNr is a positive long
	 * PostCondition: everything necessary for a game is correctly initialized
	 */
	public void newGame(long gameNr)
	{
		stack = new Deck();
		replay = new Deck();
		stack.shuffleDeck(gameNr);
		deepCopy();
		removedPairs = 0;
		fillTableu();
		
	}
	
	/**
	 * Name: removeCards
	 * Precondition:  card1 and card2 are properly made Coordinate objects
	 * PostCondition: true or false
	 */
	public boolean removeCards(proj2.Coordinates card1, proj2.Coordinates card2)
	{
		if(tableu[card1.getRow()][card1.getColumn()].getRank().equals(tableu[card2.getRow()][card2.getColumn()].getRank()))
		{
			int horizontal, vertical;
			horizontal = card1.getRow() - card2.getRow();
			vertical = card1.getColumn() - card2.getColumn();
			
			if(Math.abs(horizontal) < 2 && Math.abs(vertical) < 2)
			{
				tableu[card1.getRow()][card1.getColumn()] = null;
				tableu[card2.getRow()][card2.getColumn()] = null;
				removedPairs++;
			
				return true;
			}
			return false;
		}
		return false; 
	}
	
	/**
	 * Name: getSuit
	 * Precondition:  coord exists
	 * PostCondition: null or Suit object
	 */
	public proj2.Suit getSuit(proj2.Coordinates coord)
	{
		if(tableu[coord.getRow()][coord.getColumn()] == null)
		{
			return null;
		} else {
			return tableu[coord.getRow()][coord.getColumn()].getSuit();
		}
		
	}
	
	/**
	 * Name:  getRank
	 * Precondition:  coord exists
	 * PostCondition: null or Rank object
	 */
	public proj2.Rank getRank(proj2.Coordinates coord)
	{
		if(tableu[coord.getRow()][coord.getColumn()] == null)
		{
			return null;
		} else {
			return tableu[coord.getRow()][coord.getColumn()].getRank();
		}
		
	}

	/**
	 * Name:  help
	 * Precondition:  none
	 * PostCondition: a string with instructions for the user
	 */
	public java.lang.String help()
	{
		String help = "Rule 1: Monte Carlo Solitaire is played with a Standard 52 card Deck.\n" +
				      "Rule 2: The tableu is the 5x5 card grid.\n" +
				      "Rule 3: The object of the game is to remove all cards from the tableu.\n" +
				      "        This includes eliminating all cards in the deck.\n" +
				      "Rule 4: A card is eliminated when you click on a corresponding\n" +
				      "        pair of cards that are connected beside each other or \n" +
				      "        along a diagnol.\n" +
				      "                  Finally, Good Luck!";
		return help;
	}
	
	/**
	 * Name:  replay
	 * Precondition:  a new game has been declared
	 * PostCondition: game has been restarted exactly as before
	 */
	public void replay()
	{
		deepCopyReverse();
		fillTableu();
	}
	
	/**
	 * Name:  hint
	 * Precondition:  tableu has cards in it
	 * PostCondition: an object of type Coordinates will have been returned
	 */
	public proj2.Coordinates[] hint()
	{
		Coordinates coordArray[] = new Coordinates[2];
		int i, j, m = 0, n = 0;
		
		for(i = 0; i < 5; i++)
		{
			for(j = 0; j < 5; j++)
			{
				for(m = 0; m < 5; m++)
				{
					for(n = 0; n < 5; n++)
					{
						if(tableu[i][j] != null && tableu[m][n] != null)
						{
							if(tableu[i][j].getRank().equals(tableu[m][n].getRank()))
							{
								if(tableu[i][j].getSuit().equals(tableu[m][n].getSuit()))
								{
					
								} else {
									int horizontal, vertical;
									horizontal = i - m;
									vertical = j - n;
									
									if(Math.abs(horizontal) < 2 && Math.abs(vertical) < 2)
									{
										Coordinates coord1 = new Coordinates(i,j);
										Coordinates coord2 = new Coordinates(m, n);
										coordArray[0] = coord1;
										coordArray[1] = coord2;
						
										return coordArray;
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Name:  consolidate
	 * Precondition:  there are cards in the tableau
	 * PostCondition: cards will have been moved up and to the left filling
	 *                empty spots in the tableau
	 */
	public void consolidate()
	{
		if(tableuFull() == true)
		{
			while(findNextCard(findNull().getRow(), findNull().getColumn()) != null)
			{
				Coordinates nullCoord = findNull();
				if(nullCoord == null)
				{
					System.out.println("nullCoord is null");
				} else {
					Coordinates nextCard = findNextCard(nullCoord.getRow(), nullCoord.getColumn());
			
					if(nextCard != null)
					{
						tableu[nullCoord.getRow()][nullCoord.getColumn()] = 
							tableu[nextCard.getRow()][nextCard.getColumn()];
						
						tableu[nextCard.getRow()][nextCard.getColumn()] = null;
					}				
				}
			}
	
			if(findNull() != null)
			{
				reorderDeck();		
				System.out.println("Null row: " + findNull().getRow());
				System.out.println("Null column: " + findNull().getColumn());
				refillTableu(findNull().getRow(), findNull().getColumn());
				removedPairs = 0;
			} 
		}		
	}
	
	/**
	 * Name:  cardsLeft
	 * Precondition:  deck exists
	 * PostCondition: number of cards left in the deck will have been counted
	 */
	public int cardsLeft()
	{
		int left = 0;
		
		for(int i = 0; i < 52; i++)
		{
			if(stack.deck[i] != null)
			{
				left++;
			}
		}
		return left;
	}
	
	/**
	 * Name:  deepCopy
	 * Precondition:  stack.deck[] exits
	 * PostCondition: replay.deck[] will be an exact copy of stack.deck[] after shuffled
	 */
	private void deepCopy()
	{
		int i;
		
		for(i = 0; i < 52; i++)
		{
			replay.deck[i] = stack.deck[i];
		}
	}
	
	/**
	 * Name:  deepCopyReverse
	 * Precondition:  deepCopy has been performed
	 * PostCondition: stack.deck will have been restored by replay.deck
	 */
	private void deepCopyReverse()
	{
		int i;
		
		for(i = 0; i < 52; i++)
		{
			stack.deck[i] = replay.deck[i];
		}
	}
	
	/**
	 * Name:  fillTableu
	 * Precondition:  tableu exists and is empty
	 * PostCondition: tableu holds 25 cards
	 */
	private void fillTableu()
	{
		if(deckEmpty() == false)
		{
			int m = 0, i, j;
	
			for(i = 0; i < this.row; i++)
			{
				for(j = 0; j < this.column; j++)
				{
					tableu[i][j] = stack.deck[m];
					stack.deck[m] = null;
					m++;
				}
			}
			reorderDeck();
		}
	}
	
	/**
	 * Name:  findNull
	 * Precondition:  none
	 * PostCondition: null will have been found and its coordinates returned
	 *                or null will have been returned
	 */
	private Coordinates findNull()
	{
		int i, j;
		for(i = 0; i < row; i++)
		{
			for(j = 0; j < column; j++)
			{
				if(tableu[i][j] == null)
				{
					Coordinates nullCard = new Coordinates(i, j);
					
					return nullCard;
				}
			}
		}
		return null;
		
	}
	
	/**
	 * Name:  findNextCard
	 * Precondition:  row and column are ints within the bounds of the array
	 * PostCondition: coordinates of next card if it exists else null
	 */
	private Coordinates findNextCard(int row, int column)
	{
		int i, j;
	
		for(j = column; j < this.column; j++)
		{
			if(tableu[row][j] != null)
			{
				Coordinates nextCard = new Coordinates(row,j);
			
				return nextCard;
			}
		}
		
		row++;

		for(i = row; row < this.row; row++)
		{
			for(j = 0; j < this.column; j++)
			{
				if(tableu[i][j] != null)
				{
					Coordinates nextCard = new Coordinates(i,j);
					
					return nextCard;
				}
			}
		
		}
	
		if(removedPairs > 2)
		{
			row = 3;
			if(row == 3)
			{
				row++;
				for(i = row; row < this.row; row++)
				{
					for(j = 0; j < this.column; j++)
					{
						if(tableu[i][j] != null)
						{
							Coordinates nextCard = new Coordinates(i,j);
							
							return nextCard;
						}
					}
				}
			}
		}
		
		return null;
		
	}
	
	/**
	 * Name:  refillTableu
	 * Precondition:  tableu has cards; row and column are positive ints
	 *                within the bounds of the array
	 * PostCondition: tableu is full, unless deck is empty
	 */
	private void refillTableu(int row, int column)
	{
		int i, j, index = 0;
				
		i = row;
		for(j = column; j < this.column; j++)
		{
			tableu[i][j] = stack.deck[index];
			stack.deck[index] = null;
			index++;	
		}
		
		row++;
		for(i = row; row < this.row; row++)
		{
			for(j = 0; j < this.column; j++)
			{
				tableu[i][j] = stack.deck[index];
				stack.deck[index] = null;
				index++;	
			}
		}
		
		reorderDeck();
		
	}
	
	/**
	 * Name:  reorderDeck
	 * Precondition:  stack.deck exists
	 * PostCondition: all cards have been moved to the front of the stack
	 */
	private void reorderDeck()
	{
		Deck temp = new Deck();
		int i, m = 0;
		
		for(i = 0; i < 52; i++)
		{
			temp.deck[i] = stack.deck[i];
			stack.deck[i] = null; 
		}
		
		for(i = 0; i < 52; i++)
		{
			if(temp.deck[i] != null)
			{
				stack.deck[m] = temp.deck[i];
				m++;
			}
		}
		
	}
	
	/**
	 * Name:  deckEmpty
	 * Precondition:  deck exists
	 * PostCondition: true/false dependant on decks status
	 */
	private boolean deckEmpty()
	{
		int i, j = 0;
		for(i = 0; i < 52; i++)
		{
			if(stack.deck[i] == null)
			{
				j++;
			}
		}
		if(j == 52)
		{
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Name:  tableuFull
	 * Precondition:  tableu exists
	 * PostCondition: true/false dependant on decks status
	 */
	private boolean tableuFull()
	{
		int i, j, m = 0;
		for(i = 0; i < this.row; i++)
		{
			for(j = 0; j < this.column; j++)
			{
				if(stack.deck[i] != null)
				{
					m++;
				}
			}
		}
		if(m == 52)
		{
			return true;
		} else {
			return false;
		}
	}
}
