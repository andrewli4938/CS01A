import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;

/** Provide a view of the game of Nim to a user.   
 *
 * @author Byron Weber Becker */
public class NimView extends JPanel
{
   /** Construct the view. 
    * @param aModel The model we will be displaying. */
   public NimView(NimModel aModel)
   {  super();
      this.layoutView(aModel);
   }
   
   private void layoutView(NimModel aModel)
   {  // create the sub-views
      NimPlayerView red = new NimPlayerView(aModel, Player.RED);
      NimPlayerView black = new NimPlayerView(aModel, Player.BLACK);
      NimPileView pile = new NimPileView(aModel);
      
      // put a title on each sub-view
      red.setBorder(BorderFactory.createTitledBorder("Red"));
      black.setBorder(BorderFactory.createTitledBorder("Black"));
      pile.setBorder(BorderFactory.createTitledBorder("Pile Size"));
      
      // group the red and black views
      JPanel center = new JPanel();
      center.setLayout(new GridLayout(1, 2));
      center.add(red);
      center.add(black);
      
      // lay out the pieces in this view
      this.setLayout(new BorderLayout());
      this.add(center, BorderLayout.CENTER);
      this.add(pile, BorderLayout.SOUTH);
      //this.add(new NimPileView2(aModel), BorderLayout.EAST);
   }
}
