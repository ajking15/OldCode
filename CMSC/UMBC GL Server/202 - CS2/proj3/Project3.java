package proj3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project3 {

	public static void main (String [] args)
	{
		Project3 p3 = new Project3( );
	}

	private SnackMachine snackMachine;
	private JSpinner spinNickels, spinDimes, spinQrts, spinBills;
	private JFrame mainFrame;
	
	public Project3 ( )
	{
		snackMachine = new SnackMachine( );
		
		// spinners for coins inserted

		
		mainFrame = new JFrame("CMSC 202 Project 3");
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(1000, 500);
		mainFrame.setLocation(100, 50);

		// Maintenance Menus 
		JMenuBar jmb = new JMenuBar( );
		
		// Create the Candy Maintenance Menu System
		JMenu jmCandy = new JMenu("Candy Maintenance");
		JMenuItem jmiAddBarsWithNuts = new JMenuItem("Add Candy Bars with Nuts");
		JMenuItem jmiAddBarsNoNuts = new JMenuItem("Add Candy Bars without Nuts");
		JMenuItem jmiAddGumballs = new JMenuItem("Add Gumballs");
		JMenuItem jmiNrGumballs = new JMenuItem("Gumball Count");
		JMenuItem jmiNrCandyBars = new JMenuItem("Candybar Count");
		
		// attach action listeners for each menu choice
		jmiAddBarsWithNuts.addActionListener(new AddBarsWithNuts());
		jmiAddBarsNoNuts.addActionListener(new AddBarsNoNuts());
		jmiAddGumballs.addActionListener(new AddGumballs());
		jmiNrGumballs.addActionListener(new NrGumballs());
		jmiNrCandyBars.addActionListener(new NrCandyBars());
		
		// add menu items to CandyMaintenance Menu
		jmCandy.add( jmiAddBarsWithNuts);
		jmCandy.add( jmiAddBarsNoNuts);
		jmCandy.add( jmiAddGumballs);
		jmCandy.add( jmiNrGumballs);
		jmCandy.add( jmiNrCandyBars);
		
		// Register Maintenance Menu
		JMenu jmRegister = new JMenu("Cash Register Maintenance");
		JMenuItem jmiCashOnHand = new JMenuItem("Report Cash on Hand");
		JMenuItem jmiAddFunds = new JMenuItem("Add Funds");
		JMenuItem jmiRemoveFunds = new JMenuItem("Remove Funds");
		
		// add action listeners for menu items
		jmiCashOnHand.addActionListener( new CashOnHand());
		jmiAddFunds.addActionListener( new AddFunds());
		jmiRemoveFunds.addActionListener( new RemoveFunds( ));
		
		jmRegister.add(jmiCashOnHand);
		jmRegister.add(jmiAddFunds);
		jmRegister.add(jmiRemoveFunds);
		
		// add menu lists to menu bar
		jmb.add(jmCandy);		
		jmb.add(jmRegister);
		
		// add menu bar to frame
		mainFrame.setJMenuBar(jmb);
		
		// Marque in the NORTH
		JLabel jlMarquee = new JLabel( "CMSC 202 Mystery Snack Machine");
		jlMarquee.setFont(new Font("Times New Roman", Font.BOLD, 24));
		jlMarquee.setHorizontalAlignment(JTextField.CENTER);
		mainFrame.add(jlMarquee, BorderLayout.NORTH);
		
		// Buttons for purchases in the CENTER of the frame
		JPanel jpButtons = new JPanel( );
		jpButtons.setLayout(new GridLayout(1, 2));
		JButton jbGumballs = new JButton( "<html><center>Buy Gumball<p>35 cents</center></html>");
		jbGumballs.setBackground(Color.ORANGE);
		jbGumballs.setFont( new Font("Times New Roman", Font.BOLD, 42));
		jbGumballs.setFocusPainted(false);
		jbGumballs.addActionListener(new BuyGumball());
		
		JButton jbCandybar = new JButton( "<html><center>Buy Candy Bar<p>65 cents</center></html>");
		jbCandybar.setBackground(Color.MAGENTA);
		jbCandybar.setFont( new Font("Times New Roman", Font.BOLD, 42));
		jbCandybar.setFocusPainted(false);
		jbCandybar.addActionListener(new BuyCandybar());
		jpButtons.add( jbGumballs);
		jpButtons.add( jbCandybar);
		
		// Spinners for coin insertion in the SOUTH
		JPanel jpCoins = new JPanel( ); // to encapsulate labels and spinners
		Dimension spinnerDim = new Dimension(50, 20);
		Font labelFont = new Font("Times New Roman", Font.BOLD, 24);

		SpinnerNumberModel spnmNickels = new SpinnerNumberModel(0, 0, 20, 1);
		spinNickels = new JSpinner( spnmNickels );
		spinNickels.setPreferredSize(spinnerDim);
		SpinnerNumberModel spnmDimess = new SpinnerNumberModel(0, 0, 10, 1);
		spinDimes = new JSpinner( spnmDimess );
		spinDimes.setPreferredSize(spinnerDim);
		SpinnerNumberModel spnmQtrs = new SpinnerNumberModel(0, 0, 4, 1);
		spinQrts = new JSpinner( spnmQtrs );
		spinQrts.setPreferredSize(spinnerDim);
		SpinnerNumberModel spnmBills= new SpinnerNumberModel(0, 0, 1, 1);
		spinBills = new JSpinner( spnmBills );
		spinBills.setPreferredSize(spinnerDim);
		
		JLabel jlInsertCoins = new JLabel("Insert Coins Here              ");
		jlInsertCoins.setFont(new Font("Times New Roman", Font.BOLD, 24));
		jpCoins.add(jlInsertCoins);
		JLabel jlNickels = new JLabel("Nickels");
		jlNickels.setFont(labelFont);
		jlNickels.setHorizontalAlignment(JLabel.RIGHT);
		jpCoins.add(jlNickels);
		jpCoins.add(spinNickels);
		JLabel jlDimes = new JLabel("Dimes");
		jlDimes.setFont(labelFont);
		jlDimes.setHorizontalAlignment(JLabel.RIGHT);
		jpCoins.add(jlDimes);
		jpCoins.add(spinDimes);
		JLabel jlQrts = new JLabel("Quarters");
		jlQrts.setFont(labelFont);
		jlQrts.setHorizontalAlignment(JLabel.RIGHT);
		jpCoins.add(jlQrts);
		jpCoins.add(spinQrts);
		JLabel jlBills = new JLabel("$1Bills");
		jlBills.setFont(labelFont);
		jlBills.setHorizontalAlignment(JLabel.RIGHT);
		jpCoins.add(jlBills);
		jpCoins.add(spinBills);
		
		// populate the main frame
		mainFrame.add(jpButtons, BorderLayout.CENTER);
		mainFrame.add(jpCoins, BorderLayout.SOUTH);
		mainFrame.setVisible( true );
	}

	// -- helper methods
	private void resetSpinners( )
	{
		spinNickels.setValue(0);
		spinDimes.setValue(0);
		spinQrts.setValue(0);
		spinBills.setValue(0);
	}


	//----  event handlers ---  //
	
	// Buy Gumball Button
	private class BuyGumball implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			// read the coins inserted
			int nrNickels, nrDimes, nrQrts, nrBills;
			//nrNickels = (Integer)spinNickels.getValue();
			nrNickels = ((Integer)spinNickels.getValue()).intValue();
			nrDimes = ((Integer)spinDimes.getValue()).intValue();
			nrQrts = ((Integer)spinQrts.getValue()).intValue();
			nrBills = ((Integer)spinBills.getValue()).intValue();
			
			// buy the Gumball, get tx in return with status/change
			GumBallTx gbTx;
			gbTx = snackMachine.buyGumball(new Money(nrNickels, nrDimes, nrQrts, nrBills));
			String change = gbTx.getChange().toString();			
			if (gbTx.gumballsSoldOut())
				JOptionPane.showMessageDialog(mainFrame,
						"<html>Gumballs are Sold Out<p><P>Change Returned<p><P>" + change);
			else if (gbTx.insufficientFundsTendered())
				JOptionPane.showMessageDialog(mainFrame,
						"<html>Insuffficent Funds Deposited<p><P>Change Returned<p><P>" + change);
			else if(gbTx.noChange())
				JOptionPane.showMessageDialog(mainFrame,
						"<html>No Change Available<p><P>Change Returned<p><P>" + change);
			else { // purchase successful
				// check for failed tx... if so, pop an error frame
				// if no error, pop a success frame
				RoundIcon gumball = new RoundIcon(gbTx.getGumball().getColor(), 100);

				String msg =	"<html>Congratulation! You bought this Gumball<P>Your change is<P><P>" + change;
				JOptionPane.showMessageDialog(null, msg, 
						"Gumball Purchase", JOptionPane.INFORMATION_MESSAGE, gumball);
			}
			
			// reset coin spinners
			resetSpinners( );
		}
	}

	// Buy Candy Bar Button
	private class BuyCandybar implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			// read the coins inserted
			int nrNickels, nrDimes, nrQrts, nrBills;
			nrNickels = ((Integer)spinNickels.getValue()).intValue();
			nrDimes = ((Integer)spinDimes.getValue()).intValue();
			nrQrts = ((Integer)spinQrts.getValue()).intValue();
			nrBills = ((Integer)spinBills.getValue()).intValue();
			
			// buy the Candybar, get tx in return with status/change
			CandyBarTx cbTx;
			cbTx = snackMachine.buyCandyBar(new Money(nrNickels, nrDimes, nrQrts, nrBills));
			String change = cbTx.getChange().toString();			
			if (cbTx.candyBarsSoldOut())
				JOptionPane.showMessageDialog(mainFrame,
						"<html>Candy Bars are Sold Out<p><P>Change Returned<p><P>" + change);
			else if (cbTx.insufficientFundsTendered())
				JOptionPane.showMessageDialog(mainFrame,
						"<html>Insuffficent Funds Deposited<p><P>Change Returned<p><P>" + change);
			else if(cbTx.noChange())
				JOptionPane.showMessageDialog(mainFrame,
						"<html>No Change Available<p><P>Change Returned<p><P>" + change);
			else { // purchase successful
				String cbar = "<html><P>" + cbTx.getBar().toString();
				String msg1 ="<html>Congratulations! You bought " + cbar;
				String msg2 = "<html><P>Your change is<P><P>" + change;
				String msg = msg1 + msg2;
				JOptionPane.showMessageDialog(null, msg); 
			}
			
			// reset coin spinners
			resetSpinners( );
		}
	}
	
	// Candy Maintenance Menu -- add candy bars with nuts
	private class AddBarsWithNuts implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			AddCandyBars( true );
		}
	}

	// Candy Maintenance Menu -- add candy bars with no nuts
	private class AddBarsNoNuts implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			AddCandyBars( false );
		}
	}
	
	// common method for adding candy bars with or w/o nuts
	// some stuff marked "final" so they van be used within anonymous ActionListener
	private void AddCandyBars(final boolean hasNuts)
	{
		// pop a frame and ask how many
		final JFrame frame = new JFrame( );
		frame.setResizable(false);
		frame.setLocation(300, 300);
		frame.setSize(400, 150);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		SpinnerNumberModel spnmNrBars = new SpinnerNumberModel(1, 1, 50, 1);
		final JSpinner spinNrBars = new JSpinner( spnmNrBars );
		spinNrBars.setPreferredSize(new Dimension(50, 20));
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int nrBars = ((Integer)spinNrBars.getValue()).intValue();
				snackMachine.addCandyBars(new CandyBar(hasNuts), nrBars);
				JOptionPane.showMessageDialog(frame,
						nrBars + " candy bars WITH" + (hasNuts? "" : "OUT") + " nuts added to the machine");
			}
		});
	
		JPanel subPanel1 = new JPanel();
		subPanel1.add( new JLabel("Input Number of BARS WITH" + (hasNuts ? "" : "OUT") + " NUTS to Add"));
		subPanel1.add(spinNrBars);
		JPanel subPanel2 = new JPanel();
		subPanel2.add(ok);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add( subPanel1, BorderLayout.NORTH );
		panel.add( subPanel2, BorderLayout.CENTER);
		panel.setBackground(Color.MAGENTA);
		frame.add(panel, BorderLayout.CENTER);
		frame.setVisible(true);

	}
	
	// Candy Maintenance Menu -- add gumballs
	private class AddGumballs implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			// pop a frame and ask how many
			final JFrame frame = new JFrame( );
			frame.setResizable(false);
			frame.setLocation(200, 200);
			frame.setSize(500, 500);
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			
			// color chooser

			JPanel subPanel1 = new JPanel();
			JLabel jlCount =new JLabel("Input Number of Gumballs Add");
			jlCount.setFont(new Font("Times New Roman", Font.BOLD, 18));
			SpinnerNumberModel spnmNrGBalls = new SpinnerNumberModel(1, 1, 50, 1);
			final JSpinner spinNrGBalls = new JSpinner( spnmNrGBalls );
			spinNrGBalls.setPreferredSize(new Dimension(50, 20));
			subPanel1.add(jlCount);
			subPanel1.add(spinNrGBalls);
			
			JPanel subPanel2 = new JPanel();
			JLabel jlColor = new JLabel("Choose gumball color");
			jlColor.setFont(new Font("Times New Roman", Font.BOLD, 18));
			final JColorChooser jccGumballColor = new JColorChooser( );
			subPanel2.add (jlColor);
			subPanel2.add(jccGumballColor);

			JPanel subPanel3 = new JPanel();
			JButton ok = new JButton("OK");
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					int nrGBalls = ((Integer)spinNrGBalls.getValue()).intValue();
					Color gbColor = jccGumballColor.getColor();
					snackMachine.addGumballs(new Gumball(gbColor), nrGBalls);
					JOptionPane.showMessageDialog(frame, nrGBalls + " Gumballs added to the machine");
				}
			});
			subPanel3.add(ok);
			
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.add( subPanel1, BorderLayout.NORTH );
			panel.add( subPanel2, BorderLayout.CENTER);
			panel.add( subPanel3, BorderLayout.SOUTH);
			panel.setBackground(Color.MAGENTA);
			
			frame.add(panel, BorderLayout.CENTER);
			frame.setVisible(true);
		}
	}

	// Candy Maintenance Menu -- how many gumballs in the machine?
	private class NrGumballs implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			// create a frame to display the number of gumballs
			int nrGumBalls = snackMachine.getNrGumballs();
			String info = "The snack machine currently contains " + nrGumBalls + " gumballs";
			JOptionPane.showMessageDialog(mainFrame, info);
		}
	}

	// Candy Maintenance Menu -- how many candybars in the machine?
	private class NrCandyBars implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			// create a frame to display the number of candy bars
			int nrCandybars = snackMachine.getNrCandyBars();
			String info = "The snack machine currently contains " + nrCandybars + " candy bars";
			JOptionPane.showMessageDialog(mainFrame, info);
		}
	}

	// Cash Maintenance Menu -- add Funds
	private class AddFunds implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			final JFrame jfAddFunds = new JFrame("Add Funds to Cash Register");
			jfAddFunds.setResizable(false);
			jfAddFunds.setLocation(300, 300);
			jfAddFunds.setSize(200, 200);
			jfAddFunds.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			
			JPanel jpAddFunds = new JPanel( );
			jpAddFunds.setLayout(new BorderLayout());
			Dimension spinnerDim = new Dimension(50, 20);
			Font labelFont = new Font("Times New Roman", Font.BOLD, 18);

			JPanel jpSpinners = new JPanel();
			jpSpinners.setLayout(new GridLayout(4,4));
			SpinnerNumberModel spnmNickels = new SpinnerNumberModel(0, 0, 20, 1);
			final JSpinner spinNickels = new JSpinner( spnmNickels );
			spinNickels.setPreferredSize(spinnerDim);
			SpinnerNumberModel spnmDimess = new SpinnerNumberModel(0, 0, 20, 1);
			final JSpinner spinDimes = new JSpinner( spnmDimess );
			spinDimes.setPreferredSize(spinnerDim);
			SpinnerNumberModel spnmQtrs = new SpinnerNumberModel(0, 0, 20, 1);
			final JSpinner spinQrts = new JSpinner( spnmQtrs );
			spinQrts.setPreferredSize(spinnerDim);
			SpinnerNumberModel spnmBills= new SpinnerNumberModel(0, 0, 20, 1);
			final JSpinner spinBills = new JSpinner( spnmBills );
			spinBills.setPreferredSize(spinnerDim);
			
			JLabel jlNickels = new JLabel("Nickels");
			jlNickels.setFont(labelFont);
			jlNickels.setHorizontalAlignment(JLabel.CENTER);
			jpSpinners.add(jlNickels);
			jpSpinners.add(spinNickels);
			JLabel jlDimes = new JLabel("Dimes");
			jlDimes.setFont(labelFont);
			jlDimes.setHorizontalAlignment(JLabel.CENTER);
			jpSpinners.add(jlDimes);
			jpSpinners.add(spinDimes);
			JLabel jlQrts = new JLabel("Quarters");
			jlQrts.setFont(labelFont);
			jlQrts.setHorizontalAlignment(JLabel.CENTER);
			jpSpinners.add(jlQrts);
			jpSpinners.add(spinQrts);
			JLabel jlBills = new JLabel("$1Bills");
			jlBills.setFont(labelFont);
			jlBills.setHorizontalAlignment(JLabel.CENTER);
			jpSpinners.add(jlBills);
			jpSpinners.add(spinBills);

			jpAddFunds.add(jpSpinners, BorderLayout.NORTH);
			JPanel jpOk = new JPanel( );
			JButton jbOk = new JButton("OK");
			jbOk.addActionListener( new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					int nickels = ((Integer)spinNickels.getValue()).intValue();
					int dimes = ((Integer)spinDimes.getValue()).intValue();
					int quarters = ((Integer)spinQrts.getValue()).intValue();
					int bills = ((Integer)spinBills.getValue()).intValue();
					Money fundsAdded =new Money(nickels, dimes, quarters, bills);
					snackMachine.addFunds(fundsAdded);
					JOptionPane.showMessageDialog(jfAddFunds, "Funds Added\n" + fundsAdded.toString());
				}
			});
			jpOk.add(jbOk);
			jpAddFunds.add(jpOk, BorderLayout.CENTER);
			jfAddFunds.add(jpAddFunds, BorderLayout.CENTER);
			jfAddFunds.setVisible(true);
		}
	}
	
	// Cash Maintenance Menu -- remove excess funds
	private class RemoveFunds implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			Money removed = snackMachine.removeExcessFunds( );
			String info = "Excess Funds Removed\n" + removed.toString();
			JOptionPane.showMessageDialog(mainFrame, info);
		}
	}
	
	// Cash Maintenance Menu -- report cash on hand
	private class CashOnHand implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			// call snack machine method
			// report coins in machine -- share with RemoveFunds
			Money cashOnHand = snackMachine.getCashOnHand();
			String info = "The Cash Register contains\n" + cashOnHand.toString();
			JOptionPane.showMessageDialog(mainFrame, info);
		}
	}

	/**
	 * a round Icon with transparent corners
	 * Used to draw a gumball
	 */
	public static class RoundIcon implements Icon {
		private Color color;
		private int diameter;
 
		public RoundIcon(Color color, int diameter) {
			this.color = color;
			this.diameter = diameter;
		}
		public int getIconHeight() {
			return diameter;
		}
		public int getIconWidth() {
			return diameter;
		}
		public void paintIcon(Component c, Graphics g, int x, int y) {
			g.setColor(color);
			g.fillArc(x, y, diameter, diameter, 0, 360);
		}
	}


}
