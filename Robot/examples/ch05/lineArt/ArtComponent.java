import javax.swing.*;
import java.awt.*;

/** Create a component that paints our "art".
 * @author Byron Weber Becker */
public class ArtComponent extends JComponent
{
   public ArtComponent()
   {  super();
      this.setPreferredSize(new Dimension(300,300));
   }

   /** Paint the component with our "art". */
   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
   
   	// Standard stuff to scale the image.
      Graphics2D g2 = (Graphics2D) g;
      g2.scale(this.getWidth()/11, this.getHeight()/11);
      g2.setStroke(new BasicStroke(1.0F/this.getWidth()));
      
      // draw our "art"
      g.drawLine(1, 1, 10, 10);
   }
}
