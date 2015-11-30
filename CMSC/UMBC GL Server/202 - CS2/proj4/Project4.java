package proj4;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Project4 {

	private JFrame inputFrame;
	private final int RED_DRAGON = 0;
	private final int GREEN_DRAGON = 1;
	private final int WIZARD = 2;
	private final int SIREN = 3;
	private final int KNIGHT = 4;
	private final int ARCHER = 5;
	private final int PFROG = 6;
	private final int NR_CREATURES = PFROG + 1;
	private int fightersChosen = 0;
	private Timer autoPlayTimer;
	private boolean autoPlayOn = false;
	private JButton jbAutoPlay;
	private BattleGround bg;
	private JButton jbSkirmish;
	
	// battle screen
	private final int NR_FIGHTERS = 2;
	private JTextArea jtaBattleInfo;
	private Creature[] creatures= new Creature[NR_FIGHTERS];
	private JProgressBar[] jpbHealth = new JProgressBar[NR_FIGHTERS];
	private JFrame battleFrame;


	// image file names and check boxes for all creatures
	String[] fNames = {"images//RedDragon.gif", "images//GreenDragon.gif",
					   "images//Wizard.gif",	"images//Siren.gif",
					   "images//Knight.gif",	"images//Archer.gif",	   "images//PFrog.gif"};
	ImageIcon[] icons = new ImageIcon[NR_CREATURES];
	private JCheckBox[] jcbBoxes = new JCheckBox[NR_CREATURES];
	private String[] creatureNames = {"Red Dragon", "Green Dragon", "Wizard", "Siren",
							  "Knight", "Archer", "Poisonous Frog"};
	
	Box[] boxes = new Box[NR_CREATURES];
	
	// performs a skirmish
	private void step( )
	{
	   String result = bg.skirmish( );
	   jtaBattleInfo.append("\n" + result);
	   for (int i = 0; i < 2; i++)
	   {
		   jpbHealth[i].setValue(creatures[i].getHealth());
	   }
	   for (int i = 0; i < 2; i++)
		   if (creatures[i].getHealth() == 0)
		   {
			   JOptionPane.showMessageDialog(battleFrame, "Game Over!!");
			   jbSkirmish.setEnabled(false);
			   autoPlayTimer.stop();
		   }
		   
	}

	// constructor creates selection screen
	public Project4 ( )
	{
		// create timer for auto play skirmish
		autoPlayTimer = new Timer(1000, new AbstractAction()
		{
			public void actionPerformed( ActionEvent e)
			{
				step();
			}
		});

		// create the frame for selecting creatures
		inputFrame = new JFrame("CMSC 202 Project 4");
		inputFrame.setLayout(new BorderLayout());
		inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inputFrame.setSize(800, 500);
		inputFrame.setLocation(200, 50);
		inputFrame.setResizable(false);
		
		// icons and check boxes and instructions
		JPanel jpCreatures = new JPanel( );
		for (int i = 0; i < NR_CREATURES; i++)
		{
			icons[i] = new ImageIcon(fNames[i]);
			icons[i] = new ImageIcon(getScaledImage(icons[i].getImage(),80, 80));
			jcbBoxes[i] = new JCheckBox(creatureNames[i]);
			jcbBoxes[i].addItemListener(new CheckBoxListener());
			boxes[i] = makeCheckBoxWithIcon(icons[i], jcbBoxes[i]);
			jpCreatures.add(boxes[i]);
		}
		
		String instructions = "<HTML><P>Choose two combatants<p>Push \"Start the Battle\"</HTML>";
		JLabel jlInstructions = new JLabel();
		jlInstructions.setFont(new Font("Times New Roman", Font.BOLD, 24));
		jlInstructions.setText(instructions);
		jpCreatures.add(jlInstructions);
		inputFrame.add(jpCreatures, BorderLayout.CENTER);
		
		// Start the battle button
		JPanel jpStart = new JPanel();
		JButton jbStart = new JButton("Start the Battle");
		jbStart.setFont(new Font("Times New Roman", Font.BOLD, 24));
		jbStart.addActionListener(new StartButtonListener());
		jpStart.add(jbStart);
		inputFrame.add(jpStart, BorderLayout.SOUTH);
		
		// show the screen
		inputFrame.setVisible( true );
	}

	
	// ---- helper methods
	private Box makeCheckBoxWithIcon(ImageIcon icon, JCheckBox jcb)
	{
		Box b = Box.createVerticalBox();
		b.add(new JLabel(icon));
		b.add(Box.createRigidArea(new Dimension(10, 0)));
		b.add(jcb);
		return b;
	}
	
   private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
   }

   // Event-handling inner classes
   
   private class CheckBoxListener implements ItemListener
   {
	   public void itemStateChanged(ItemEvent e)
	   {
		   JCheckBox checkBox = (JCheckBox)e.getSource();
		   if (checkBox.isSelected())
			   ++fightersChosen;
		   else
			   --fightersChosen;			   
	   }
   }
   
   // when "Start the Battle" is pushed, battle screen is shown
   private class StartButtonListener implements ActionListener
   {
	   private JPanel jpGifs;
	   private ImageIcon[] battleFlags = new ImageIcon[NR_FIGHTERS];
	   private Box[] battleBoxes = new Box[NR_FIGHTERS];
	   
	   public void actionPerformed(ActionEvent ae)
	   {
		   // check that exactly 2 fighters were chosen
		   if (fightersChosen == NR_FIGHTERS)
		   {
			   inputFrame.setVisible(false);
			   int cIndex = 0;
			   for (int i = 0; i < NR_CREATURES && cIndex < NR_FIGHTERS; i++)
			   {
				   // create the creatures and display their pictures
				   if (jcbBoxes[i].isSelected())
				   {
					   switch( i )
					   {
					   		case RED_DRAGON:	creatures[cIndex] = new RedDragon(200, 200); break;
					   		case GREEN_DRAGON:	creatures[cIndex] = new GreenDragon(100, 300); break;
					   		case WIZARD:		creatures[cIndex] = new Wizard(300, 140); break;
					   		case SIREN:			creatures[cIndex] = new Siren(260, 160); break;
					   		case KNIGHT:		creatures[cIndex] = new Knight(75, 230); break;
					   		case PFROG:			creatures[cIndex] = new PoisonFrog(170, 100); break;
					   		case ARCHER:		creatures[cIndex] = new Archer(180, 120); break;
					   }
					   battleFlags[cIndex] = new ImageIcon(getScaledImage(icons[i].getImage(),200, 200));
					   ++cIndex;
				   }
			   }
			   // create the battle ground
			   bg = new BattleGround (creatures[0], creatures[1]);
				
			   // create the GUI for the battle
			   battleFrame = new JFrame("Battle of the Creatures");
			   battleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			   battleFrame.setSize(700, 700);
			   battleFrame.setLocation(200, 10);
	
			   // display the fighter info side-by-side
			   jpGifs = new JPanel( );
			   for (int i = 0; i < NR_FIGHTERS; i++)
			   {
				   battleBoxes[i] = Box.createVerticalBox();
				   battleBoxes[i].add(new JLabel(battleFlags[i]));
				   battleBoxes[i].add(Box.createRigidArea(new Dimension(0, 10)));
				   battleBoxes[i].add(new JLabel(creatures[i].getName()));
				   battleBoxes[i].add(Box.createRigidArea(new Dimension(0, 10)));
				   battleBoxes[i].add(new JLabel("Attack Strength: " + creatures[i].getAttackStrength()));
				   battleBoxes[i].add(Box.createRigidArea(new Dimension(0, 10)));
				   battleBoxes[i].add(new JLabel("Defensive Strength: " + creatures[i].getDefensiveStrength()));
				   battleBoxes[i].add(Box.createRigidArea(new Dimension(0, 10)));
				   jpbHealth[i] = new JProgressBar();
				   jpbHealth[i].setValue(creatures[i].getHealth());
				   jpbHealth[i].setStringPainted(true);
				   battleBoxes[i].add(new JLabel("Health"));
				   battleBoxes[i].add(jpbHealth[i]);
				   jpGifs.add(battleBoxes[i]);
			   }
			   
			   // battle log is scrollable
			   jtaBattleInfo = new JTextArea(10, 200);
			   jtaBattleInfo.setLineWrap(true);
			   jtaBattleInfo.setEditable(false);
			   JScrollPane jspBattleInfo = new JScrollPane(jtaBattleInfo );
			   jspBattleInfo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	
			   // buttons to control skirmishes
			   JPanel jpButtons = new JPanel();
			   jbSkirmish = new JButton("Skirmish One Time");
			   jbSkirmish.addActionListener(new SkirmishButtonListener());
			   jpButtons.add(jbSkirmish);
			   jbAutoPlay = new JButton("Auto Play");
			   jbAutoPlay.addActionListener(new AutoPlayListener());
			   jpButtons.add(jbAutoPlay);
			   battleFrame.add(jspBattleInfo, BorderLayout.NORTH);
			   battleFrame.add(jpGifs, BorderLayout.CENTER);
			   battleFrame.add(jpButtons, BorderLayout.SOUTH);
			   battleFrame.setVisible(true);
		   }
		   else  // 2 fighters were not chosen
			   JOptionPane.showMessageDialog(inputFrame, 
					   fightersChosen + " fighters chosen\nPlease choose exactly 2 fighters");
	   }
	   
	   // when the "Skirmish One Time" button is pushed
	   private class SkirmishButtonListener implements ActionListener
	   {
		   public void actionPerformed(ActionEvent ae)
		   {
			   step( );
		   }
	   }
	   
	   // AutoPlay button pushed
	   private class AutoPlayListener implements ActionListener
	   {
		   public void actionPerformed(ActionEvent ae)
		   {
			   if (!autoPlayOn)
			   {
				   // start auto play
					autoPlayOn = true;
					jbAutoPlay.setText("Pause");
					jbSkirmish.setEnabled(false);
					autoPlayTimer.start();
			   } else {
				   	// pause autoplay
					autoPlayTimer.stop();
					autoPlayOn = false;
					jbAutoPlay.setText("Automatic");
					jbSkirmish.setEnabled(true);	
			   }
		   }
	   }	   
   }
   
   // -- test code ---- //
	public void updateText(String msg)
	{
		jtaBattleInfo.append("\n" + msg);
	}
	
	public static void main( String[] args)
	{
		Project4 p4 = new Project4( );
	}

}
