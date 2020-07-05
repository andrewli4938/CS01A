import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import becker.util.IView;


/** A view showing the current pile size for the game of Nim.
 *
 * @author Byron Weber Becker */
public class NimPileView extends JPanel implements IView
{
   private NimModel model;
   private JLabel pileSize = new JLabel();
   
   /** Construct the view. */
   public NimPileView(NimModel aModel)
   {  super();
      this.model = aModel;
      
      this.layoutView();
      
      this.model.addView(this);
      this.updateView();
   }
   
   /** Update the view.  Called by the model when its state changes. */
   public void updateView()
   {  this.pileSize.setText("" + this.model.getPileSize());
   }
   
   /** Layout the view.  */
   private void layoutView()
   {  this.pileSize.setFont(new Font("Serif", Font.PLAIN, 24));
      this.add(this.pileSize);
   }
}
