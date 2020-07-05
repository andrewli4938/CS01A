import javax.swing.JFrame;


/** Test program for JList.
 *
 * @author Byron Weber Becker */
public class Main extends Object
{
   public static void main(String[] args)
   {  Object model = new Object();		// just testing!
      View view = new View(model);
            
      JFrame f = new JFrame("Test JList");
      f.setSize(250, 200);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setContentPane(view);
      f.setVisible(true);
   }
}
