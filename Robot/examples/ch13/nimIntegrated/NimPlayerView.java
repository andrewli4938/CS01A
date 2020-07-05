import becker.util.IView;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/** Provide a view of the game of Nim focused on one particular player to a user.   
 *
 * @author Byron Weber Becker */
public class NimPlayerView extends JPanel implements IView, ActionListener
{	
   private NimModel model;
   private Player player;
	
   private JButton[] removeButtons = new JButton[] {
      new JButton("Remove 1 Token"), new JButton("Remove 2 Tokens"),
      new JButton("Remove 3 Tokens")
   };
   private JLabel winner = new JLabel("Winner!");
	
   /** Construct a view for one player.
    * @param aModel the game's model
    * @param player The player for which this is the view */
   public NimPlayerView(NimModel aModel, Player aPlayer)
   {  super();
      this.model = aModel;
      this.player = aPlayer;
		
      this.layoutView();
      this.registerControllers();
		
      this.model.addView(this);
      this.updateView();
   }
	
   /** Update the view to reflect recent changes in the model's state. */
   public void updateView()
   {  Player whoseTurn = this.model.getWhoseTurn();
      int pSize = this.model.getPileSize();
      // enable buttons if it's my player's turn and there are enough tokens on the pile
      for (int i = 0; i < this.removeButtons.length; i++)
      {  this.removeButtons[i].setEnabled( whoseTurn == this.player
               && i + 1 <= pSize);
      }
      this.winner.setVisible(this.model.getWinner() == this.player);
   }
	
   /** Lay out the components for this view. */
   private void layoutView()
   {  GridLayout grid = new GridLayout(4, 1, 5, 5);
      this.setLayout(grid);
		
      Font font = new Font("Serif", Font.PLAIN, 24);

      for (JButton b : this.removeButtons)
      {  this.add(b);
         b.setFont(font);
      }
		
      this.winner.setFont(font);
      this.winner.setHorizontalAlignment(SwingConstants.CENTER);
      this.add(this.winner);
   }
	
   /** Register controllers for this view's components. */
   private void registerControllers()
   {  for (int i = 0; i < this.removeButtons.length; i++)
      {  this.removeButtons[i].addActionListener(this);
      }
   }
   
   public void actionPerformed(ActionEvent evt)
   {  JButton src = (JButton) evt.getSource();
   		
      int i = 0;
      while (removeButtons[i] != src)
      {  i++;
      }
      assert removeButtons[i] == src;
      model.removeTokens(i+1);
   }
  
}
