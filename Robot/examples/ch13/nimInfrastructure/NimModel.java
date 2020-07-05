import becker.util.IModel;
import becker.util.IView;
import java.util.ArrayList;

/** A class implementing a version of "Nim."  There is a (virtual) pile of
 * tokens.  Two players take turns removing 1, 2 or 3 tokens.  The player
 * who takes the last token wins the game.
 *
 * @author Byron Weber Becker */
public class NimModel extends Object implements IModel
{  private ArrayList<IView> views = new ArrayList<IView>();

   /** Construct a new instance of the game of Nim. */
   public NimModel()
   {  super();
   }
   
   /** Add a view to display information about this model.
    * @param view The view to add. */
   public void addView(IView view)
   {  this.views.add(view);
   }
   
   /** Remove a view that has been displaying information about this model.
    * @param view The view to remove. */
   public void removeView(IView view)
   {  this.views.remove(view);
   }
   
   /** Inform all the views currently displaying information about this model
    * that the model has changed and their display may need changing too. */
   public void updateAllViews()
   {  for(IView view : this.views)
      {  view.updateView();
      }
   }
}
