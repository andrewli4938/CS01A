import java.awt.*;			// Graphics, Dimension, Color
import javax.swing.*;		// JComponent

public class StickFigurePair extends JComponent
{
   public StickFigurePair()
   {  super ();
      Dimension prefSize = new Dimension(2*180+5, 270);
      this.setPreferredSize(prefSize);
   }

   /** Paint two stick figures
    *  @param g The graphics context to do the painting. */
   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      this.paintStickFig(g, 0, 0);
      this.paintStickFig(g, 182, 0); 
      //this.paintStickFig(g, 80, 20);
   }

   
   /** Paint one stick figure at the given location.
    * @param g2 The graphics context to do the painting.
    * @param x The x coordinate of the upper left corner of the figure.
    * @param y The y coordinate of the upper left corner of the figure. */
	private void paintStickFig(Graphics g2, int x, int y)
	{  // Paint the head.
      g2.setColor(Color.YELLOW.darker());
      g2.fillOval(x+60, y+0, 60, 60);
      
      // Paint the shirt.
      g2.setColor(Color.RED);
      g2.fillRect(x+0, y+60, 180, 30);
      g2.fillRect(x+60, y+60, 60, 90);
      
      // Paint the pants.
      g2.setColor(Color.BLUE);
      g2.fillRect(x+60, y+150, 60, 120);
      g2.setColor(Color.BLACK);
      g2.drawLine(x+90, y+180, x+90, y+270);
      
      //g2.drawRect(x,y,180,270);
   }
}
