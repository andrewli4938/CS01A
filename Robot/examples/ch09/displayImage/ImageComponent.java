
import java.awt.*;
import javax.swing.*;

/** A component that paints an image stored in a file.
 *
 * @author Byron Weber Becker */
public class ImageComponent extends JComponent
{
   private ImageIcon image;
   
   /** Construct the new component.
    * @param fileName The file where the image is stored. */
   public ImageComponent(String fileName)
   {  super();
      this.image = new ImageIcon(fileName);
      this.setPreferredSize(new Dimension(this.image.getIconWidth(), this.image.getIconHeight()));

   }

   /** Paint this component, including its image. */
   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      g.drawImage(this.image.getImage(), 0, 0, null);
   }
}
