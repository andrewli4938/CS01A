import java.awt.event.*;


/** A controller for the game of Nim that informs the game how many
 * tokens a player wants to remove.
 *
 * @author Byron Weber Becker */
class RemoveButtonController extends Object implements ActionListener
{
   private NimModel model;
   private int numRemove;
   	
   /** @param aModel the model this controls
    *  @param howMany how many tokens to remove when the button is clicked */
   public RemoveButtonController(NimModel aModel, int howMany)
   {  super();
      this.model = aModel;
      this.numRemove = howMany;
   }
   
   /** Remove the right number of tokens from the model. */
   public void actionPerformed(ActionEvent e)
   {  this.model.removeTokens(this.numRemove);
   }
}
