/**
 * 
 */
package proj2;

/**
 * @author chrmai1
 *
 */
public class Card {
	private Rank cardRank;
	private Suit cardSuit;
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//Card card = new Card(2,3);
		//System.out.println("Row is: " + card.getRow());
		//System.out.println("Column is: " + card.getColumn());
		//System.out.println("Rank is: " + card.obRank());
		//System.out.println("Rank name is: " + card.obRankName());
		//System.out.println("Suit is: " + card.obSuit());
		//System.out.println("Suit name is: " + card.obSuitName());
	}
	
	Card(int rank, int suit)
	{
				setRank(rank);
				setSuit(suit);
	}
	
	public void setSuit(int suit){
		switch(suit){
		case 1:
			cardSuit =Suit.CLUBS;
			break;
		case 2:
			cardSuit = Suit.DIAMONDS;
			break;
		case 3:
			cardSuit = Suit.HEARTS;
			break;
		case 4:
			cardSuit = Suit.SPADES;
			break;
		default:
			System.out.println("Massive Fail in setSuit");
			System.exit(-1);
			break;
		}
	}
	
	public void setRank(int rank){
		switch(rank) {
		case 1:
			cardRank = Rank.ACE;
			break;
		case 2:
			cardRank = Rank.TWO;
			break;
		case 3:
			cardRank = Rank.THREE;
			break;
		case 4:
			cardRank = Rank.FOUR;
			break;
		case 5:
			cardRank = Rank.FIVE;
			break;
		case 6:
			cardRank = Rank.SIX;
			break;
		case 7:
			cardRank = Rank.SEVEN;
			break;
		case 8:
			cardRank = Rank.EIGHT;
			break;
		case 9:
			cardRank = Rank.NINE;
			break;
		case 10:
			cardRank = Rank.TEN;
			break;
		case 11:
			cardRank = Rank.JACK;
			break;
		case 12:
			cardRank = Rank.QUEEN;
			break;
		case 13:
			cardRank = Rank.KING;
			break;
		default:
			System.out.println("Massive Fail in setRank");
			System.exit(-1);
			break;
		}
		
	
	}
	
	//returns char symbol of rank
	String obRank() {
		return cardRank.getSymbol();
	}
	
	//returns name of rank
	String obRankName(){
		return cardRank.getName();
	}
	
	//returns char symbol for suit
	String obSuit(){
		return cardSuit.getSymbol();
	}
	
	//returns name of suit
	String obSuitName(){
		return cardSuit.getName();
	}
	
	//returns Suit object representing a suit
	Suit getSuit() {
		return cardSuit;
	}
	
	//Returns Rank object represeneting a rank
	Rank getRank() {
		return cardRank;
	}
	
}
