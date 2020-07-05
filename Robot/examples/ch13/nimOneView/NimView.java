import javax.swing.JPanel;
import becker.util.IView;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;


/** Provide a view of the game of Nim to a user.   
 *
 * @author Byron Weber Becker */
public class NimView extends JPanel implements IView
{
   private NimModel model;

   // get how many tokens to remove
   private JTextField redRemoves = new JTextField(5);
   private JTextField blackRemoves = new JTextField(5);
   
   // info to display
   private JLabel pileSize = new JLabel();
   private JLabel redWins = new JLabel("Winner!");
   private JLabel blackWins = new JLabel("Winner!");

   /** Construct the view. 
    * @param aModel The model we will be displaying. */
   public NimView(NimModel aModel)
   {  super();
      this.model = aModel;
      
      this.layoutView();
      this.registerControllers();
      
      this.model.addView(this);
      this.updateView();
   }
      
   /** Called by the model when it changes.  Update the information this
    * view displays. */
   public void updateView()
	{	// Update the size of the pile.
		this.pileSize.setText("" + this.model.getPileSize());
		
		// Enable and disable the text fields for each player.
		this.redRemoves.setEnabled(
						this.model.getWhoseTurn() == Player.RED);
		this.blackRemoves.setEnabled(
						this.model.getWhoseTurn() == Player.BLACK);

		// Proclaim the winner, if there is one.
		this.redWins.setVisible(
						this.model.getWinner() == Player.RED);
		this.blackWins.setVisible(
						this.model.getWinner() == Player.BLACK);
      
      // Keyboard focus on current player's component.
      if (this.model.getWhoseTurn() == Player.RED)
      {  this.redRemoves.requestFocusInWindow();
         this.redRemoves.setText("");
      } else if (this.model.getWhoseTurn() == Player.BLACK)
      {  this.blackRemoves.requestFocusInWindow();
         this.blackRemoves.setText("");
      }
   }

   /** Layout the view */
   private void layoutView()
   {  // A panel for the red player
      JPanel red = new JPanel();
      red.add(this.redRemoves);
      red.add(this.redWins);
      red.setBorder(BorderFactory.createTitledBorder("Red"));
      
      // A panel for the black player
      JPanel black = new JPanel();
      black.add(this.blackRemoves);
      black.add(this.blackWins);
      black.setBorder(BorderFactory.createTitledBorder("Black"));
      
      // pilesize info
      JPanel pSize = new JPanel();
      pSize.add(this.pileSize);
      pSize.setBorder(BorderFactory.createTitledBorder("Pile Size"));
      
      // group the red and black panels
      JPanel center = new JPanel();
      center.setLayout(new GridLayout(1, 2));
      center.add(red);
      center.add(black);
      
      // lay out the pieces in this view
      this.setLayout(new BorderLayout());
      this.add(center, BorderLayout.CENTER);
      this.add(pSize, BorderLayout.SOUTH);
      
      // enlarge the fonts
      Font font = new Font("Serif", Font.PLAIN, 24);
      this.redRemoves.setFont(font);
      this.blackRemoves.setFont(font);
      this.redWins.setFont(font);
      this.blackWins.setFont(font);
      this.pileSize.setFont(font);
   }
   
   /** Register controllers for the components the user can manipulate. */
   private void registerControllers()
   {  this.redRemoves.addActionListener(
            new RemovesController(this.model, this.redRemoves));
      this.blackRemoves.addActionListener(
            new RemovesController(this.model, this.blackRemoves));
   }
}