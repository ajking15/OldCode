package proj2;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class ClickableImageGrid extends JPanel {

	private final int gRows, gCols;	// nr rows, columns
	private ClickableImage[ ][ ] images;
	private GridBagLayout gbag = new GridBagLayout( );
	private GridBagConstraints gbc = new GridBagConstraints( );
	
	public ClickableImageGrid( int rows, int cols)
	{
		gRows = rows;
		gCols = cols;
		images = new ClickableImage[rows][ cols];
		setLayout(gbag);
		gbc.insets = new Insets(4,4,4,4);  // spacing around images
	}

	// put image into box at specified (x, y)
	public void addImage( ClickableImage image, int x, int y, Border border)
	{
		image.setRow( x );
		image.setColumn(y);
		image.setBorder( border );
		gbc.gridx = y; 	// ?? why backwards
		gbc.gridy = x;
		gbag.setConstraints( image, gbc);
		add(image);
		images[x][y] = image;
	}


	public void removeImage( int x, int y )
	{
		if (images[x][y] != null)
			images[x][y].setVisible( false );
	}
	
	public void clearImages( )
	{
		for (int r = 0; r < gRows; r++)
		for (int c = 0; c < gCols; c++)
			removeImage(r, c);
	}

	public void setBorder( int r, int c, Border border)
	{
		images[r][c].setBorder(border);
	}
}
	
	