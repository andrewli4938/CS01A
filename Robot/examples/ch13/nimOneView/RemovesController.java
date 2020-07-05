import javax.swing.JTextField;
import java.awt.event.*;


/** A controller for the game of Nim that informs the game how many
 * tokens a player wants to remove.
 *
 * @author Byron Weber Becker */
class RemovesController extends Object implements ActionListener
{
   private NimModel model;
   private JTextField textfield;
   	
   public RemovesController(NimModel aModel, JTextField aTextfield)
   {  super();
      this.model = aModel;
      this.textfield = aTextfield;
   }
   
   public void actionPerformed(ActionEvent e)
   {  try 
      {  int remove = Integer.parseInt(this.textfield.getText());
         this.model.removeTokens(remove);
      } catch (NumberFormatException ex) 
      {  this.textfield.selectAll();
      }
   }
}
