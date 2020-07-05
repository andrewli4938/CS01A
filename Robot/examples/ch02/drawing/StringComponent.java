import java.awt.*;
import javax.swing.*;

/** Draw a Rectangle.
 *
 * @author Byron Weber Becker */
public class StringComponent extends JComponent
{
	public StringComponent()
	{	super ();
		Dimension prefSize = new Dimension(190, 170);
		this.setPreferredSize(prefSize);
	}

	public void paintComponent(Graphics g)
	{  super.paintComponent(g);

      g.setColor(Color.BLACK);
      g.drawString("Drawing a String", 30, 50);
   }
}
