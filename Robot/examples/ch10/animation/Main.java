import javax.swing.*;

/** Create an animaged image. 
 *
 * @author Byron Weber Becker */
public class Main extends Object
{
   public static void main(String[] args)
   {  // Create two animated components.
      AnimateImage anim1 = new AnimateImage("img", 6, ".gif", 1);
      AnimateImage anim2 = new AnimateImage("img", 6, ".gif", -1);
      
      // Put the components in a panel and then in a frame.
      JPanel contents = new JPanel();
      contents.add(anim1);
      contents.add(anim2);
   
      JFrame f = new JFrame("Animations");
      f.setContentPane(contents);
      f.pack();
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setVisible(true);
      
		// Run each animation in its own thread.
      Thread t1 = new Thread(anim1);
      t1.start();
      Thread t2 = new Thread(anim2);
      t2.start();
   }
}
