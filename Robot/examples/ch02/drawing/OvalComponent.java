import java.awt.*;
import javax.swing.*;

/** Draw a Oval.
 *
 * @author Byron Weber Becker */
public class OvalComponent extends JComponent
{
	public OvalComponent()
	{	super ();
		Dimension prefSize = new Dimension(190, 170);
		this.setPreferredSize(prefSize);
	}

	public void paintComponent(Graphics g)
	{  super.paintComponent(g);

      g.setColor(Color.BLACK);
      g.drawOval(30, 50, 140, 100);
   }
}
