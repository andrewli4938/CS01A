import becker.util.IView;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/** A view of the model via menus.  This extension of JMenuBar is added
 * to the JFrame in DemoMain.java.
 *
 * @author Byron Weber Becker */
public class MenuView extends JMenuBar implements IView
{
   private DemoModel model;

   private JMenuItem miNew = new JMenuItem("New...");
   private JMenuItem miOpen = new JMenuItem("Open...");
   private JMenuItem miClose = new JMenuItem("Close");

   /** Construct the view.
    * @param aModel The model logging the events. */
   public MenuView(DemoModel aModel)
   {  super();
      this.model = aModel;
      this.layoutView();
      this.registerControllers();
      this.model.addView(this);
      this.updateView();
   }

   /** Update the view with information from the model.  These updates are
    * really pretty meaningless; it's just to show the principle.  */
   public void updateView()
   {  this.miNew.setEnabled(this.model.getItemCount() < 50);
      this.miOpen.setEnabled(this.model.getItemCount() >= 50);
   }	

   /** Lay out the menu items in the menus and the menu bar. */
   private void layoutView()
   {  JMenu file = new JMenu("File");
      file.add(this.miNew);
      file.add(this.miOpen);
      file.add(this.miClose);
		
      this.add(file); // recall that this is a JMenuBar, not a JPanel
   }

   /** Register controllers on the items. */
   private void registerControllers()
   {  MenuController mc = new MenuController();
      this.miNew.addActionListener(mc);
      this.miOpen.addActionListener(mc);
      this.miClose.addActionListener(mc);
   }
	
   /** A controller that logs the choice with the model. */
   private class MenuController extends Object implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {  MenuView.this.model.addHistory("MenuView", this, e.getSource(),
               "actionPerformed", "");
      }
   }
}
