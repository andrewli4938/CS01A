import java.awt.*;
import javax.swing.*;

public class StickFigure extends JComponent
{
	public StickFigure ()
	{	super ();
		Dimension prefSize = new Dimension(180, 270);
		this.setPreferredSize(prefSize);
	}


   // Paint a stick figure.
	public void paintComponent(Graphics g)
	{  super.paintComponent(g);

      // Paint the head.
      g.setColor(Color.YELLOW.darker());
      g.fillOval(60, 0, 60, 60);
      
      // Paint the shirt.
      g.setColor(Color.RED);
      g.fillRect(0, 60, 180, 30);
      g.fillRect(60, 60, 60, 90);
      
      // Paint the pants.
      g.setColor(Color.BLUE);
      g.fillRect(60, 150, 60, 120);
      g.setColor(Color.BLACK);
      g.drawLine(90, 180, 90, 270);
   }  
}
