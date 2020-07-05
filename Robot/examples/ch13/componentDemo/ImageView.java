import becker.util.IView;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.Box;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.net.URL;
import java.util.ArrayList;


/** A view showing images.  The image to show is determined by how many
 * events have been logged with the model.  It may be manually overridden
 * by the user using buttons in the view.  
 *
 * @author Byron Weber Becker */
public class ImageView extends JPanel implements IView
{
   private DemoModel model;

   // a list of all the possible images
   private ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();
   private int current = 0; // which image to display
   private JLabel imageLabel = new JLabel();
   private JButton next = new JButton("Next");
   private JButton prev = new JButton("Previous");
   
   private static final int NUM_IMAGES = 2;

   /** Construct the view.
    * @param aModel The model we consult to determine which image to show. */
   public ImageView(DemoModel aModel)
   {  super();
      this.model = aModel;
		
      this.loadImages();
  
      this.layoutView();
      this.registerControllers();
      this.model.addView(this);
      this.updateView();
   }
	
   /** Load all of the images into an arraylist for later display. */
   private void loadImages()
   {  for (int i = 0; i < ImageView.NUM_IMAGES; i++)
      {  String imgPath = "images/PeggysCove" + i + ".jpg";
         URL imageURL = ImageView.class.getResource(imgPath);
         if (imageURL != null)
         {  this.images.add(new ImageIcon(imageURL));  
         } else
         {  System.out.println("Couldn't find image '" + imgPath + "'.");
         }
      }
   }

   /** Update the image after every 20th event is logged with the model. */
   public void updateView()
   {  int itemCount = this.model.getItemCount();
	
      if (itemCount % 20 == 0)
      {  this.nextPix();
      }
   }	

   /** Layout the view. */
   private void layoutView()
   {  // first image to show
      this.imageLabel.setIcon(this.images.get(this.current));
	   
      // put label in a scroll pane, add it to the view
      this.setLayout(new BorderLayout());
      JScrollPane scroller = new JScrollPane(this.imageLabel);
      this.add(scroller, BorderLayout.CENTER);
	   
      // Adjust the preferred and max dimensions so the buttons appear
      // the same size in the view.
      Dimension pref = this.next.getPreferredSize();
      pref.width = Math.max(this.next.getPreferredSize().width,
            this.prev.getPreferredSize().width);
      this.next.setPreferredSize(pref);
      this.prev.setPreferredSize(pref);
      this.next.setMaximumSize(pref);
      this.prev.setMaximumSize(pref);
    
      // add buttons to control which picture to see
      Box controls = Box.createVerticalBox();
      controls.add(this.prev);
      controls.add(Box.createVerticalStrut(5));
      controls.add(this.next);
      
      this.add(controls, BorderLayout.EAST);
   }

   /** Register controllers. */
   private void registerControllers()
   {  ButtonController bc = new ButtonController();
      this.next.addActionListener(bc);
      this.prev.addActionListener(bc);
   }

   /** Advance to the next picture */
   private void nextPix()
   {  this.current = (this.current + 1) % this.images.size();
      this.imageLabel.setIcon(this.images.get(this.current));
   }
   
   /** Return to the previous picture. */
   private void prevPix()
   {  this.current = this.current - 1;
      if (this.current == -1)
      {  this.current += this.images.size();
      }
      this.imageLabel.setIcon(this.images.get(this.current));
   }
 
   // A controller used by the next and previous buttons.
   private class ButtonController extends Object implements ActionListener
   {
      public void actionPerformed(ActionEvent evt)
      {  Object src = evt.getSource();
         if (src == ImageView.this.next)
         {  ImageView.this.nextPix();
         } else if (src == ImageView.this.prev)
         {  ImageView.this.prevPix();
         } else
         {  assert false;
         }
      }
   }
}
