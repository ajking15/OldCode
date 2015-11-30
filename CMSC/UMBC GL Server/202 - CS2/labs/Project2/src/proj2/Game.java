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
 * @author Christopher Mai
 *
 */
public class Game {
	private int row;
	private int column;
	private Card[][] tableu; //makes a 2d array for the tableu
	private Deck stack;
	private Deck replay;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game(5,5);
		game.newGame(12345);	 
	}
	
	public Game(int row, int column) //Constructs a new monte carlo solitaire object
	{
		//this makes a new tableu for the game
		//row is the number of rows in the tableu and column numbers of columns
		this.row = row;
		this.column = column;
		
		tableu = new Card[this.row][this.column];
	}
	
	public int score() //returns players score
	{
		//1 point for each card removed for maximum of 52
		int score = 0;
		for(int i = 0; i < 52; i++)
		{
			if(stack.deck[i] == null)
			{
				score++;
			}
		}
		
		for(int m = 0; m < 5; m++)
		{
			for(int n = 0; n < 5; n++)
			{
				if(tableu[m][n] != null)
				{
					score--;
				}
			}
		}
		return score;
	}
	
	public boolean playerWins()
	{
		if(tableu[0][0] == null) 
		{
			return true;
		}
		//player wins if tableu is empty
		// and the deck is empty
		return false;
	}
	
	public void newGame(long gameNr)
	{
		//new game. 
		/*for(int i = 0; i < 52; i++)
		{
			System.out.println(stack.deck[i].obRankName() + " " + stack.deck[i].obSuitName());
		}*/
		stack = new Deck();
		replay = new Deck();
		stack.shuffleDeck(gameNr);//deck has to be shuffled here
		deepCopy();
		clearTableu();
		/*for(int i = 0; i < 52; i++)
		{
			System.out.println(stack.deck[i].obRankName() + " " + stack.deck[i].obSuitName());
		}*/
		
		fillTableu();
		/*for(int i = 0; i < 52; i++)
		{
		   System.out.println(stack.deck[i].obRankName() + " " + stack.deck[i].obSuitName());
		}*/
		   //deck fails after fill tableu
		//change how deck is affected
	}
	
	public boolean removeCards(proj2.Coordinates card1, proj2.Coordinates card2)
	{
		if(tableu[card1.getRow()][card1.getColumn()].getRank().equals(tableu[card2.getRow()][card2.getColumn()].getRank()))
		{
			int horizontal, vertical;
			horizontal = card1.getRow() - card2.getRow();
			vertical = card1.getColumn() - card2.getColumn();
			if(Math.abs(horizontal) < 2 && Math.abs(vertical) < 2)
			{// I think this works
				return true;
			}
			return false;
			//make something to check for proximity. will probably be really long
			/*
			 * Cases to check for(true). Possibly make methods to check each one
			 * -if card is directly right 
			 * -if card is directly left
			 * -if card is directly up
			 * -if card is directly down
			 * -if card is up-left
			 * -if card is down-left
			 * -if card is up-right
			 * -if card is down-right
			 */
		}
		return false; 
	}
	
	public proj2.Suit getSuit(proj2.Coordinates coord)
	{
		//apparently there are no cards in the tableu. so this fails
		//return tableu[coord.getRow()][coord.getColumn()].getSuit();
		return Suit.CLUBS;
	}
	
	//this doesn't work either
	public proj2.Rank getRank(proj2.Coordinates coord)
	{
		//return tableu[coord.getRow()][coord.getColumn()].getRank();
		return Rank.ACE;
	}
	
	public java.lang.String help()
	{
		//explains the rules of the game;
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
	
	public void replay()
	{
		//plays the exact same game initially played
		/*
		 * Im thinking create a second type deck object that hard copies the original
		 * deck after it is shuffled and a new game is called. It will require 
		 * a clear tableu function(Hell, I need one of those anyway). then give it
		 * it's own fillTableu Function. Basically a copy and paste with a different name
		 */
		deepCopyReverse();
		clearTableu();
		fillTableu();
	}
	
	public proj2.Coordinates[] hint()
	{
		/*
		 * Check first card. then cards around it. then second and cards around it. etc.
		 * have checks for cards at the very top  and edges so they dont search 
		 * outside of the array bounds
		 */
		/*
		 * Or you can check first card then the rest of the tableu based on Rank 
		 * and for each card found check for proximity. Do this until a pair is found;
		 * And fill the coord[] with two coord objects of their locations
		 */
		Coordinates coordArray[] = new Coordinates[2];
		int i, j, m = 0, n = 0;
		for(i = 0; i < 5; i++)
		{
			for(j = 0; j < 5; j++)
			{
				if(i != m && j != n)
				{
					if(tableu[i][j].getRank().equals(tableu[m][n]))
					{
						int horizontal, vertical;
						horizontal = i - m;
						vertical = j - n;
						if(Math.abs(horizontal) < 2 && Math.abs(vertical) < 2)
						{// I think this works
							Coordinates coord1 = new Coordinates(i,j);
							Coordinates coord2 = new Coordinates(m, n);
							coordArray[0] = coord1;
							coordArray[1] = coord2;
							
							return coordArray;
						}
					}
				}
				
				n++;
			}
			n = 0;
			m++;
		}
		
		
		return null;
	}
	
	public void consolidate()
	{
		int i, j, m, n, o = 0;
		//only needs to take care of moving the cards to the left and up. no redeal
		/*
		 * Traverse the tableu until null is found. then traverse the tableu until the 
		 * next card is found and place it in the null space. Repeat until
		 * the second loop searching for a new card cannont find one in the tableu
		 * I really don't want to do this function
		 */
		while(o < 25)
		{
			for(i = 0; i < 5; i++)
			{
				for(j = 0; j < 5; j++)
				{
					if(tableu[i][j] == null)
					{
						for(m = 0; m < 5; m++)
						{
							for(n = 0; n < 5; n++)
							{
								if(m > i && n > j)
								{
									if(tableu[m][n] != null)
									{
										tableu[i][j] = tableu[m][n];
										tableu[m][n] = null;
									}
								}
							}
						}
						//null is empty space
						//then you must traverse the deck until tableu is not null
					}
				}
				o++;
			}
		}
	}
	
	public int cardsLeft()
	{
		int left = 0;
		//estimates cards left in the deck only
		for(int i = 0; i < 52; i++)
		{
			if(stack.deck[i] != null)
			{
				left++;
			}
		}
		return left;
	}
	
	private void deepCopy()
	{
		int i;
		
		for(i = 0; i < 52; i++)
		{
			replay.deck[i] = stack.deck[i];
		}
	}
	
	private void deepCopyReverse()
	{
		int i;
		
		for(i = 0; i < 52; i++)
		{
			stack.deck[i] = replay.deck[i];
		}
	}
	
	//still has a few kinks? Maybe not
	private void fillTableu()
	{
		int m = 0, i, j;
		for(i = 0; i < row; i++)
		{
			for(j = 0; j < column; j++)
			{
				tableu[i][j] = stack.deck[m];
				stack.deck[m] = null;
				m++;
			}
		}
		
		Deck temp = new Deck();
		j = 51;
		for(i = 0; i < 52; i++)
		{
			temp.deck[i] = stack.deck[j];
			j--;
		}
		
		for(i = 0; i < 52; i++)
		{
			stack.deck[i] = temp.deck[i];
		}
		
	}
	
	private void clearTableu()
	{
		int i, j;
		
		for(i = 0; i < 5; i++)
		{
			for(j = 0; j < 5; j++)
			{
				tableu[i][j] = null;
			}
		}
	}
	
}
