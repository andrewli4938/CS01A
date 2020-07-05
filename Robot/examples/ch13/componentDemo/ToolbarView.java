import becker.util.IView;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;


/** A view extending JToolBar.  Special care must be taken when adding it 
 * to the larger view.  See notes in DemoView.layoutView().
 *
 * Buttons in the toolbar log events with the model.  The events are then displayed 
 * in another view.
 *
 * @author Byron Weber Becker */
public class ToolbarView extends JToolBar implements IView
{
   private DemoModel model;

   // "navigation" buttons
   private JButton back;
   private JButton up;
   private JButton forward;

   /** Construct the view.
    * @param aModel The model logging the events. */
   public ToolbarView(DemoModel aModel)
   {  super();
      this.model = aModel;
      this.layoutView();
      this.registerControllers();
      this.model.addView(this);
      this.updateView();
   }

   /** Update the view with information from the model.  When the view
    * does not actually display information from the model, updateView may
    * be empty.  */
   public void updateView()
   {}	

   /** Lay out the components in this view. */
   private void layoutView()
   {  this.back = this.makeNavigationButton("navButtonBack", "back", "Back",
            "Back");
      this.forward = this.makeNavigationButton("navButtonForward", "forward",
            "Forward", "Forward");
      this.up = this.makeNavigationButton("navButtonUp", "up", "Up", "Up");
      this.add(this.back);
      this.add(this.up);
      this.add(this.forward);
   }

   /** Register a controller with the buttons. */
   private void registerControllers()
   {  ToolbarController tc = new ToolbarController();
      this.back.addActionListener(tc);
      this.up.addActionListener(tc);
      this.forward.addActionListener(tc);
   }
	
   /** Make a navigation button. */
   private JButton makeNavigationButton(String imageName,
         String actionCommand,
         String toolTipText,
         String altText) 
   {  // Look for the image.
      String imgLocation = "images/" + imageName + ".gif";
      URL imageURL = ToolbarView.class.getResource(imgLocation);

      // Create and initialize the button.
      JButton button = new JButton();
      button.setActionCommand(actionCommand);
      button.setToolTipText(toolTipText);

      if (imageURL != null)
      {  // image found
         button.setIcon(new ImageIcon(imageURL, altText));
      } else
      {  // no image found
         button.setText(altText);
         System.err.println("Resource not found: " + imgLocation);
      }

      return button;
   }

   /** A controller for the buttons. */
   private class ToolbarController extends Object implements ActionListener
   {
      public void actionPerformed(ActionEvent evt)
      {  ToolbarView.this.model.addHistory("ToolbarView", this, evt.getSource(),
               "actionPerformed", evt.getActionCommand());
      }
   }
}
