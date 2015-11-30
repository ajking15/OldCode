package proj2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


/**
 * The GUI for the Monte Carlo Solitaire Project
 * 
 * Requires ClicableImage.java and ClickableImageGrid.java which
 * are also provided.
 * 
 * Requires Game.java and its supporting classes written by students
 * 
 * @author Dennis L. Frey
 */

// ignore serialization warnings, we're not serializing this class
@SuppressWarnings("serial")

public class Project2
{

	// Solitaire parameters
	private final int tableauRows = 5;
	private final int tableauCols = 5;
	private Game game = new Game(tableauRows, tableauCols);
	private long gameNr = 12345;	// used as rng Seed; 1st game always the same
	private String gameName = "Monte Carlo Solitaire";
	
	// GUI components for Monte Carlo Solitaire Game
	private JFrame mainFrame;
	private JFrame jfHelp;
	private JButton jbHelp, jbReplay, jbNewDeal, jbShift, jbHint;
    private JTextField jtfCardsLeft, jtfScore;
    
    // for managing clicking/removing cards
	private int cardsClicked = 0;
	private ClickableImage firstCardClicked;
	
	// tableau containing Card images
	ClickableImageGrid tableau = null;
	
	// card border definitions
	Border cardBorder = BorderFactory.createLineBorder(Color.BLACK, 3);
	Border clickedCardBorder = BorderFactory.createLineBorder(Color.RED, 3);
	
	// Constructor creates all GUI components
	public Project2 ( ) 
	{
		mainFrame = new JFrame( );
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setSize(700, 700);
		mainFrame.setLocation(200, 10);
		
		// rows x cols grid for card images in the CENTER
		// init the image grid and images
		game.newGame( gameNr );
		tableau = new ClickableImageGrid(tableauRows, tableauCols);
		populateTableau( );
		mainFrame.add(tableau, BorderLayout.CENTER);

		// create the panel that holds the buttons
		JPanel jpButtons = new JPanel( );
		jpButtons.setLayout( new GridLayout(1, 5));
		Border btnBorder = BorderFactory.createRaisedBevelBorder();
		Dimension btnDimension = new Dimension(50, 50);
		Font btnFont = new Font("Times New Roman", Font.BOLD, 24);
		
		// Consolidate button shifts cards left and up
		// then deals cards to complete the tableau
		jbShift = new JButton("Consolidate");
		jbShift.setBackground( Color.RED);
		jbShift.setBorder(btnBorder);
		jbShift.setFont(btnFont);
		jbShift.setPreferredSize(btnDimension);
		jbShift.addActionListener( new ShiftBtnListener() );
		jpButtons.add(jbShift);

		// Help button display help text from game
		jbHelp = new JButton ("Help");
		jbHelp.setBackground( Color.GREEN);
		jbHelp.setBorder(btnBorder);
		jbHelp.setFont(btnFont);
		jbHelp.setPreferredSize(btnDimension);
		jbHelp.addActionListener( new HelpBtnListener() );
		jpButtons.add(jbHelp);
		
		// Hint button highlights two cards that can be removed (if any)
		jbHint = new JButton("Hint");
		jbHint.setBackground( Color.YELLOW);
		jbHint.setBorder(btnBorder);
		jbHint.setFont(btnFont);
		jbHint.setPreferredSize(btnDimension);
		jbHint.addActionListener( new HintBtnListener() );
		jpButtons.add(jbHint);

		// New Deal button starts a new game
		jbNewDeal = new JButton("New Deal");
		jbNewDeal.setBackground( Color.GRAY);
		jbNewDeal.setBorder(btnBorder);
		jbNewDeal.setFont(btnFont);
		jbNewDeal.setPreferredSize(btnDimension);
		jbNewDeal.addActionListener( new NewDealBtnListener() );
		jpButtons.add(jbNewDeal);

		// Replay button starts the current game over again
		jbReplay = new JButton ("Replay");
		jbReplay.setBackground( Color.ORANGE);
		jbReplay.setBorder(btnBorder);
		jbReplay.setFont(btnFont);
		jbReplay.setPreferredSize(btnDimension);
		jbReplay.addActionListener( new ReplayBtnListener() );
		jpButtons.add(jbReplay);
		mainFrame.add(jpButtons, BorderLayout.NORTH);
			
		// create label and text field to display the
		// number of cards left in the deck
		JLabel jlCardsLeft = new JLabel("Cards Left:");
		jlCardsLeft.setFont(new Font("Times New Roman", Font.BOLD, 24));
		jtfCardsLeft = new JTextField( );
		jtfCardsLeft.setFont(new Font("Times New Roman", Font.BOLD, 24));
		jtfCardsLeft.setText("" + game.cardsLeft());
		jtfCardsLeft.setBorder(null);
		
		// create label and text field for the players score
		JLabel jlScore = new JLabel("   Score:");
		jlScore.setFont(new Font("Times New Roman", Font.BOLD, 24));
		jtfScore = new JTextField( );
		jtfScore.setFont(new Font("Times New Roman", Font.BOLD, 24));
		jtfScore.setText("" + game.score());
		jtfScore.setBorder(null);
		
		// create and add panel that holds the player info
		JPanel jpInfo = new JPanel( );
		jpInfo.add(jlCardsLeft);
		jpInfo.add(jtfCardsLeft);
		jpInfo.add(jlScore);
		jpInfo.add(jtfScore);
		mainFrame.add(jpInfo, BorderLayout.SOUTH);

		// make teh GUI visible
		mainFrame.setTitle(gameName + " Game #" + gameNr);
		mainFrame.setVisible(true);

	}
	
	// Place the card images into the tableau
	private void populateTableau( )
	{
		tableau.clearImages();
		for (int r = 0; r < tableauRows; r++)
		for (int c = 0; c < tableauCols; c++)  
		{
			// get rank and suit of card at [r][c]
			// generate file name, read file, create image, add to grid
			Suit suit = game.getSuit(new Coordinates(r, c));
			Rank rank = game.getRank(new Coordinates(r, c));
			String cardFile;
			if (suit == null || rank == null)
				cardFile = "cards//b.gif";	// back of the card
			else
				cardFile =  "cards//" + rank.getSymbol() + suit.getSymbol() + ".gif";
			
			// create the clickable card image and listen for it to be clicked
			ImageIcon cardIcon = new ImageIcon( cardFile );
			ClickableImage cImage = new ClickableImage( cardIcon );
			cImage.addActionListener(new CardListener());
			tableau.addImage(cImage, r, c, cardBorder);	
		}
	}
	
	// main just creates the GUI
	// the GUI constructor does all the work
	public static void main( String [] args)
	{
		Project2 gui = new Project2 ( );
	}
	
// **** inner class listeners for buttons and card clicks  ****
	
	// this code runs when the Help button is pushed
	private class HelpBtnListener implements ActionListener
	{
		public void actionPerformed( ActionEvent ae )
		{
			JButton jbHelpOK = new JButton("OK");
			jbHelpOK.setFont( new Font("Times New Roman", Font.BOLD, 24));
			jbHelpOK.setBackground(Color.GREEN);
			jbHelpOK.addActionListener( new HelpOKListener() );

			JTextArea jtaHelp = new JTextArea( );
			jtaHelp.setText( game.help( ));
			jtaHelp.setFont( new Font("Times New Roman", Font.TRUETYPE_FONT, 16));

			jfHelp = new JFrame( "Monte Carlo Help");
			jfHelp.add(jtaHelp, BorderLayout.CENTER);
			jfHelp.add(jbHelpOK, BorderLayout.SOUTH);
			jfHelp.setSize( 500, 500);
			jfHelp.setLocation(300, 300);
			jfHelp.setVisible( true );
		}
	}
	
	// this code runs when the "OK" button on the Help screen is pushed
	private class HelpOKListener implements ActionListener
	{
		public void actionPerformed( ActionEvent ae )
		{
			jfHelp.setVisible( false );
		}
	}
	
	// this code runs when the "New Deal" button is pushed
	private class NewDealBtnListener implements ActionListener
	{
		public void actionPerformed( ActionEvent ae )
		{
			gameNr = System.currentTimeMillis() % 100000;
			mainFrame.setTitle(gameName + " Game #" + gameNr);
			game.newGame(gameNr);
			jtfCardsLeft.setText("" + game.cardsLeft( ));
			jtfScore.setText( "" + game.score());		
			populateTableau();
		}
	}
	
	//this code runs when the "Replay" button is pushed
	private class ReplayBtnListener implements ActionListener
	{
		public void actionPerformed( ActionEvent ae )
		{
			game.replay();
			populateTableau();
			jtfCardsLeft.setText("" + game.cardsLeft( ));
			jtfScore.setText( "" + game.score());		
		}
	}
	
	// this code runs when the "Consolidate button is pushed
	private class ShiftBtnListener implements ActionListener
	{
		public void actionPerformed( ActionEvent ae )
		{
			game.consolidate( );
			populateTableau( );
			jtfCardsLeft.setText("" + game.cardsLeft( ));
		}
	}
	
	//this code runs when the "Hint" button is pushed
	private class HintBtnListener implements ActionListener
	{
		public void actionPerformed( ActionEvent ae )
		{
			Coordinates[] coords =  game.hint( );
			if (coords != null)
			{
				tableau.setBorder(coords[0].getRow(), coords[0].getColumn(), clickedCardBorder);
				tableau.setBorder(coords[1].getRow(), coords[1].getColumn(), clickedCardBorder);
			} else {
				Toolkit.getDefaultToolkit().beep();
			}
		}
	}
	
	// this code runs when any card image is clicked
	private class CardListener implements ActionListener
	{
		public void actionPerformed( ActionEvent e)
		{
			ClickableImage cImage = (ClickableImage) e.getSource();			
			if (! cImage.getClicked() )
			{
				cImage.setClicked(true);
				
				// if this is the first (of two) cards to be clicked,
				// save the card and and change the border
				if (cardsClicked == 0)
				{
					cardsClicked = 1;
					firstCardClicked = cImage;
					firstCardClicked.setBorder(clickedCardBorder);
				} else {
					// if this is the 2nd card clicked, ask the game if they should be removed
					int row1 = firstCardClicked.getRow();
					int col1 = firstCardClicked.getColumn();
					int row2 = cImage.getRow();
					int col2 = cImage.getColumn();
					if (game.removeCards(new Coordinates(row1, col1), new Coordinates(row2, col2) ))
					{
						// remove the cards, update score and see if player wins
						tableau.removeImage(row1, col1);
						tableau.removeImage(row2, col2);
						
						// change the card images to the back of the card to avoid  resizing the tableau
						ImageIcon backOfCardIcon = new ImageIcon( "cards//b.gif");
						ClickableImage bocImage1 = new ClickableImage( backOfCardIcon );
						ClickableImage bocImage2 = new ClickableImage( backOfCardIcon );
						tableau.addImage(bocImage1, row1, col1, cardBorder);
						tableau.addImage(bocImage2, row2, col2, cardBorder);
						
						// update the score on the screen and ask the game if the player has won
						// if player wins, display a new window with message
						jtfScore.setText( "" + game.score());
						if (game.playerWins())
						{
							JFrame jfYouWin = new JFrame( );
							jfYouWin.setLocation(400, 400);
							jfYouWin.setSize(400, 400);
							JLabel jlYouWin = new JLabel("YOU WIN !!");
							jlYouWin.setForeground(Color.magenta);
							jlYouWin.setFont( new Font("Times New Roman", Font.BOLD, 48));
							JPanel jpYouWin = new JPanel();
							jpYouWin.add(jlYouWin);
							jfYouWin.add(jpYouWin);
							jfYouWin.pack();
							jfYouWin.setVisible(true);
						}
						
					} else {
						// the two cards should not be removed, beep and
						// reset clicked status and first card's border 
						Toolkit.getDefaultToolkit().beep();
						cImage.setClicked(false);
						firstCardClicked.setClicked(false);
						firstCardClicked.setBorder(cardBorder);
					}
					
					// whether removed or not, next card is considered firstCardClicked
					cardsClicked = 0;
				}
			}
		} // actionPerformed
	}
}
