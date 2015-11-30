package proj2;

/**
 * The Rank class defines each of the 13 ranks of a
 * standard deck of playing cards.  Using this class assures that all
 * code refers to the ranks in the same manner.
 * i.e Rank.ACE, Rank.TWO, ... Rank.QUEEN, Rank.KING
 * with no possibility of conflicting representations.
 *<P> 
 * Class Invariant:
 * <br>The Rank's symbol is a single alpha-numeric character
 * <br>The Ranks's name is a non-empty string
 * <br>Rank objects are immutable and unique
 * 
 * @author Dennis Frey
 *
 */
public class Rank {

	// private final instance variables that define a rank
	// make the Rank objects immutable
	// the 1-char symbol is used in generating the filename of a card's image file
	private final char symbol;
	private final String name;

	// Rank objects for each of the 13 ranks
	
	/**
	 * The rank ACE
	 */
	public static final Rank ACE =   new Rank( 'a', "ace"   );
	
	/**
	 * The rank TWO (2)
	 */
	public static final Rank TWO =   new Rank( '2', "two"   );
	
	/**
	 * The rank THREE (3)
	 */
	public static final Rank THREE = new Rank( '3', "three" );
	
	/**
	 * The rank FOUR (4)
	 */
	public static final Rank FOUR =  new Rank( '4', "four"  );
	
	/**
	 * The rank FIVE (5)
	 */
	public static final Rank FIVE =  new Rank( '5', "five"  );
	
	/**
	 * The rank SIX (6)
	 */
	public static final Rank SIX =   new Rank( '6', "six"   );
	
	/**
	 * The rank SEVEN (7)
	 */
	public static final Rank SEVEN = new Rank( '7', "seven" );
	
	/**
	 * The rank EIGHT (8)
	 */
	public static final Rank EIGHT = new Rank( '8', "eight" );
	
	/**
	 * The rank NINE (9)
	 */
	public static final Rank NINE =  new Rank( '9', "nine"  );
	
	/**
	 * The rank TEN (10)
	 */
	public static final Rank TEN = 	 new Rank( 't', "ten"   );
	
	/**
	 * The rank JACK
	 */
	public static final Rank JACK =  new Rank( 'j', "jack"  );
	
	/**
	 * The rank QUEEN
	 */
	public static final Rank QUEEN = new Rank( 'q', "queen" );
	
	/**
	 * The rank KING
	 */
	public static final Rank KING =  new Rank( 'k', "king"  );
	
	/**
	 * VALUES
	 * 	A static method to return all ranks
	 * 
	 * @return an array of Rank objects suitable for looping
	 */
	public static Rank[ ] VALUES ( )
	{
		Rank[ ] ranks = {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN,
				EIGHT, NINE, TEN, JACK, QUEEN, KING };
		return ranks;
	}
	

	/**
	 * Accessor the Rank's symbol
	 * 
	 * @return the 1-char symbol as a String
	 */
	public String getSymbol( )
	{
		return new String( "" + symbol );
	}
	
	/**
	 * Accessor for the Rank's name
	 * 
	 * @return the rank's name as a String
	 */
	public String getName() {
		return name;
	}


	/**
	 * Determines if two Rank objects are equal
	 * 
	 * @param otherRank to be compared with
	 * @return true if equal, false if not
	 */
	public boolean equals (Rank otherRank )
	{
		return symbol == otherRank.symbol;
	}

	/**
	 * Private constructor so that only this class and no other code
	 * can instantiate a Rank object
	 * 
	 * @param symbol the 1-char symbol for the Rank
	 * @param name a longer String name for the Rank
	 */
	private Rank( char symbol, String name )
	{
		this.symbol = symbol;
		this.name = name;
	}
}
