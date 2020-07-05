import javax.swing.*;
import java.awt.*;

/** Instances of AnimateImage show a sequence of images to produce an 
 * animation.
 *
 * @author Byron Weber Becker */
public class AnimateImage extends JComponent implements Runnable
{
   private ImageIcon[] images;
   private int currentImage = 0;
   private int dir;
   
   /** Construct a new image animation component, loading all the images.
    * The images are read from files whose names have three parts: 
    * a root string, a seqence number, and an extension.
    *
    * @param fileNameRoot The root of the image file names
    * @param numImages The number of images in the animation
    * @param extension The extension used for the images (eg: .gif) 
    * @param dir 1 to animate going forward through the array; -1 to animate going backward through the array*/ 
   public AnimateImage(String fileNameRoot, int numImages, String extension, int dir)
   {  super();
      this.images = new ImageIcon[numImages];
      this.dir = dir;
      
      for (int i=0; i<numImages; i++)
      {  String fileName = fileNameRoot + i + extension;
         this.images[i] = new ImageIcon(fileName);
      }
      
      this.setPreferredSize(new Dimension(this.images[0].getIconWidth(),
            this.images[0].getIconHeight()));
   }
   
   /** Paint the current image on the screen. */
   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      Image img = this.images[this.currentImage].getImage();
      g.drawImage(img, 0, 0, null);
   }
   
	/** Run the animation.  */
	public void run()
	{	while (true)
		{	// Select the next image and call for the system to repaint the component.
			// If this.dir is negative, the remainder operator doesn't work as desired.  Add
			// this.images.length to compensate.
			this.currentImage = (this.currentImage + this.dir + 
						this.images.length) % this.images.length;
			this.repaint();
			try 
			{	Thread.sleep(100);
			} catch (InterruptedException ex) 
			{// ignore
			}
		}
	}
}
