import java.awt.*;
import javax.swing.*;

/** Draw a Rectangle.
 *
 * @author Byron Weber Becker */
public class RectComponent extends JComponent
{
	public RectComponent()
	{	super ();
		Dimension prefSize = new Dimension(190, 170);
		this.setPreferredSize(prefSize);
	}

	public void paintComponent(Graphics g)
	{  super.paintComponent(g);

      g.setColor(Color.BLACK);
      g.drawRect(30, 50, 140, 100);
   }
}
