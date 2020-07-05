import javax.swing.JFrame;

/** Run the game of "Nim."  There is a (virtual) pile of tokens.  Two players take turns
 *	removing 1, 2 or 3 tokens.  The player who takes the last token wins the game.
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
