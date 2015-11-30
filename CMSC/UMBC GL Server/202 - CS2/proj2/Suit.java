package proj2;

/**
 * The Suit class defines each of the 4 suits of a
 * standard deck of playing cards.  Using this class assures that all
 * code refers to the suits in the same manner
 * i.e Suit.CLUBS, Suit.DIAMONDS, Suit.HEARTS, and Suit.SPADES
 * with no possibility of conflicting representations
 * <P>
 * Class Invariant:
 * <br>The Suit's symbol is a single alphabetic character
 * <br>The Suit's name is a non-empty string
 * <br>Suit objects are immutable and unique
 * 
 * @author Dennis Frey
 *
 */
public class Suit {

	// private final instance variables make the class immutable
	// the 1-char symbol is used in generating filenames for a card image file
	private final char symbol;
	private final String name;

	// Suit objects for each of the 4 suits
	
	/**
	 * The suit CLUBS
	 */
	public static final Suit CLUBS = new Suit( 'c', "Clubs" );

	/**
	 * The suit DIAMONDS
	 */
	public static final Suit DIAMONDS = new Suit( 'd', "Diamonds" );

	/**
	 * The suit HEARTS
	 */
	public static final Suit HEARTS = new Suit( 'h', "Hearts" );
	
	/**
	 * The suit SPADES
	 */
	public static final Suit SPADES = new Suit( 's', "Spades" );

	/**
	 * VALUES
	 * 	A method to return all suits
	 * 
	 * @return an array of Suit objects useful for looping
	 */
	public static Suit[ ] VALUES ( )
	{
		Suit[ ] suits = { CLUBS, DIAMONDS, HEARTS, SPADES };
		return suits;
	}
	
	/**
	 * Accessor for the suit symbol
	 * 
	 * @return the 1-char symbol for the suit as a String
	 */
	public String getSymbol( )
	{
		return new String( "" + symbol );
	}
	
	/**
	 * Accessor for the long name of the Suit
	 * 
	 * @return the long name of the suit as a String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Determines if two Suits are the same
	 * 
	 * @param otherSuit - the suit to compare for equality
	 * @return true if the Suits are equal, false if not
	 */
	public boolean equals (Suit otherSuit )
	{
		return symbol == otherSuit.symbol;
	}
	
	/**
	 * private constructor so that only this class and no other code can
	 * instantiate a Suit object.
	 * 
	 * @param symbol - the 1-char symbol for the suit
	 * @param name - the long name for the suit
	 */
	private Suit( char symbol, String name)
	{
		this.symbol = symbol;
		this.name = name;
	}
}
