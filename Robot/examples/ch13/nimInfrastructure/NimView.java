import javax.swing.JPanel;
import becker.util.IView;

/** Provide a view of the game of Nim to a user.   
 *
 * @author Byron Weber Becker */
public class NimView extends JPanel implements IView
{  private NimModel model;

   /** Construct the view. 
    * @param aModel The model we will be displaying. */
   public NimView(NimModel aModel)
   {  super();
      this.model = aModel;
      this.model.addView(this);
      this.updateView();
   }
   
   /** Called by the model when it changes.  Update the information this
    * displays. */
   public void updateView()
   {
   }
}
