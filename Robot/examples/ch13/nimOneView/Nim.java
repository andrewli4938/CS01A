import javax.swing.JFrame;


/** Run the game of Nim in which two players take turns removing between 
 * one and three tokens from a pile.  The last player to take a token wins.
 *
 * @author Byron Weber Becker */
public class Nim extends Object
{
   public static void main(String[] args)
   {  NimModel model = new NimModel();
      NimView view = new NimView(model);
            
      
      JFrame f = new JFrame("Nim");
      f.setSize(250, 200);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setContentPane(view);
      f.setVisible(true);
   }
}
