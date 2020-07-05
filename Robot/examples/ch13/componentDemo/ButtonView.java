import becker.util.IView;
import becker.gui.FormLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.AbstractButton;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;



/** A view that includes components that are instance of 
 * JButton, JCheckBox, and JRadioButton.  Each component
 * logs events with the model.  The events are then displayed in another
 * view.
 *
 * @author Byron Weber Becker */
public class ButtonView extends JPanel implements IView
{
   private DemoModel model;

   private JButton button = new JButton("Button");
   private JCheckBox checkbox1 = new JCheckBox("Dog");
   private JCheckBox checkbox2 = new JCheckBox("Cat");
   private JCheckBox checkbox3 = new JCheckBox("Rabbit");
   private JRadioButton radio1 = new JRadioButton("Red");
   private JRadioButton radio2 = new JRadioButton("Blue");
   private JRadioButton radio3 = new JRadioButton("Green");
   private JToggleButton toggle = new JToggleButton("Lights");
   
   /** Construct the view.
    * @param aModel The model logging the events. */
   public ButtonView(DemoModel aModel)
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
	
   /** Layout all the components within the view. */
   private void layoutView()
   {  this.setLayout(new FormLayout());
      this.add(new JLabel("JButton:", SwingConstants.RIGHT));
      this.add(this.button);

      this.add(new JLabel("JCheckBox:", SwingConstants.RIGHT));
      Box checks = Box.createVerticalBox();
      checks.add(this.checkbox1);
      checks.add(this.checkbox2);
      checks.add(this.checkbox3);
      this.add(checks);

      this.add(new JLabel("JRadioButton:", SwingConstants.RIGHT));
      Box radios = Box.createVerticalBox();
      radios.add(this.radio1);
      radios.add(this.radio2);
      radios.add(this.radio3);
      ButtonGroup group = new ButtonGroup();
      group.add(this.radio1);
      group.add(this.radio2);
      group.add(this.radio3);
      this.radio1.setSelected(true);
      this.add(radios);
      
      this.add(new JLabel("JToggleButton:", SwingConstants.RIGHT));
      this.add(this.toggle);
   }

   /** Register controllers for the components. */
   private void registerControllers()
   {  ActionController ac = new ActionController();
//      CheckboxController cc = new CheckboxController();
      ItemController ic = new ItemController();
   
      /* All of these buttons take action listeners, change listeners and
       * item listeners for basically the same thing.  Which is used is 
       * largely a matter of personal preference, but ActionListener is
       * probably the most common. */
      this.button.addActionListener(ac);
      
      this.checkbox1.addActionListener(ac);
      this.checkbox2.addActionListener(ac);
      this.checkbox3.addActionListener(ac);
      
      this.radio1.addActionListener(ac);
      this.radio2.addActionListener(ac);
      this.radio3.addActionListener(ac);
      this.radio1.addItemListener(ic);
      this.radio2.addItemListener(ic);
      this.radio3.addItemListener(ic);
      
      this.toggle.addItemListener(ic);
   }
	
   /** A controller for buttons and radio buttons. */
   private class ActionController extends Object implements ActionListener
   {
      public void actionPerformed(ActionEvent evt)
      {  AbstractButton src = (AbstractButton) evt.getSource();
      
         String info = " not selected";
         if (src.isSelected())
         {  info = " selected";
         }
         model.addHistory("ButtonView", this, src, "actionPerformed",
               src.getText() + info);
      }
   }
	

   /** A controller for checkboxes. */
   /*
   private class CheckboxController extends Object implements ActionListener
   {
      public void actionPerformed(ActionEvent evt)
      {  JCheckBox src = (JCheckBox) evt.getSource();
         String info = " not selected";
         if (src.isSelected())
         {  info = " selected";
         }
         model.addHistory("ButtonView", this, src, "actionPerformed",
               src.getText() + info);
      }
   }
   */

   /** A controller for radio buttons. */
   private class ItemController extends Object implements ItemListener
   {
      public void itemStateChanged(ItemEvent evt)
      {  String state = "";
         switch (evt.getStateChange())
         {  case ItemEvent.SELECTED:
               state = " SELECTED";
               break;

            case ItemEvent.DESELECTED:
               state = " DESELECTED";
               break;

            default:
               state = "";
               break;
         }
      
         AbstractButton src = (AbstractButton) evt.getSource();
         ButtonView.this.model.addHistory("ButtonView", this, evt.getSource(),
               "itemStateChanged", src.getText() + state);
      }
   }
}
