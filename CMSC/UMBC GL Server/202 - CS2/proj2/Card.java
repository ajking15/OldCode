/**
 * 
 */
package proj2;

/**
* File:    Card.java
* Project: CMSC 202 Project 2, Spring 2008
* @author  Chris Mai
* Date:    03/09/08
* Section: 0401
* E-mail:  chrmai1@umbc.edu
* Class Invariant
*	1. cardRank has a rank when created
*   2. cardSuit has a suit when created
*/
public class Card {
	private Rank cardRank;
	private Suit cardSuit;
	
	Card(int rank, int suit)
	{
		setRank(rank);
		setSuit(suit);
	}
	
	/**
	* Name: setSuit
	* PreCondition:  suit is an integer between 1 and 13
	* PostCondition: cardSuit will have a suit of type Suit
	*/
	private void setSuit(int suit){
		switch(suit){
		case 1:
			cardSuit = Suit.CLUBS;
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
	
	/**
	* Name: setRank
	* PreCondition:  rank is an integer between 1 and 4
	* PostCondition: cardSuit will have a suit of type Suit
	*/
	private void setRank(int rank){
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
	
	/**
	* Name: getSuit
	* PreCondition:  whatever calling it is not null
	* PostCondition: the suit will have been returned
	*/
	Suit getSuit() {
		return cardSuit;
	}
	
	/**
	 * Name: getRank
	 * PreCondition:  whatever calling it is not null
	 * PostCondition: the rank will have been returned
	 */
	Rank getRank() {
		return cardRank;
	}
	
}