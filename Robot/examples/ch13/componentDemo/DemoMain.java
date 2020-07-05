import javax.swing.JFrame;


/** Run an application that demonstrates swing components and the listeners
 * they use.
 *
 * @author Byron Weber Becker */
public class DemoMain extends Object
{
   public static final int WIDTH = 750;
   public static final int HEIGHT = 600;
	
   public static void main(String[] args)
   {  DemoModel model = new DemoModel();
      DemoView view = new DemoView(model);
      MenuView menus = new MenuView(model);
   	
      JFrame f = new JFrame("Component Demo");
      f.setSize(WIDTH, HEIGHT);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setContentPane(view);
      f.setJMenuBar(menus); // use "setJMenuBar", not "setMenuBar"
      f.setVisible(true);

   }
}
