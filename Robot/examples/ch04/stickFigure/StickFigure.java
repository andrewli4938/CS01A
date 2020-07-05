import java.awt.*;
import javax.swing.*;

/** A stick figure that adjusts its size.
 *
 * @author Byron Weber Becker */
public class StickFigure extends JComponent
{
	/** Construct a stick figure.*/
	public StickFigure()
	{	super();
		Dimension prefSize = new Dimension(180, 270);
		this.setPreferredSize(prefSize);
	}

	// Draw a stick figure.
	public void paintComponent(Graphics g)
	{  super.paintComponent(g);
	
		// standard stuff to scale the image
      Graphics2D g2 = (Graphics2D)g;
      g2.scale(this.getWidth()/6, this.getHeight()/9);
      g2.setStroke(new BasicStroke(1.0F/this.getWidth()));

      //this.drawGrid(g2);

		// head
      g2.setColor(Color.YELLOW.darker());
      g2.fillOval(2, 0, 2, 2);
      
      // shirt
      g2.setColor(Color.RED);
      g2.fillRect(0, 2, 6, 1);
      g2.fillRect(2, 2, 2, 3);
      
      // pants
      g2.setColor(Color.BLUE);
      g2.fillRect(2, 5, 2, 4);
      g2.setColor(Color.BLACK);
      g2.drawLine(3, 6, 3, 9);
	}

	// Optional method not shown in the text to draw the grid shown in
	// one of the figures.
   private void drawGrid(Graphics2D g2)
   {  g2.setColor(Color.BLACK);
      for(int i=0; i<8; i++)
      {  g2.drawLine(i, 0, i, 10);
      }
      for(int i=0; i<10; i++)
      {  g2.drawLine(0, i, 8, i);
      }

   }
}
