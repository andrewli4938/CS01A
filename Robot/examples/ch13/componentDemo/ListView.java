import becker.util.IView;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


/** A view that includes components that are instances JList and JComboBox.
 * Each component logs events with the model.  The events are then displayed 
 * in another view.
 *
 * @author Byron Weber Becker */
public class ListView extends JPanel implements IView
{
   private DemoModel model;

   // Java keywords to put in our lists.
   private static final String[] keywords = new String[] {
      "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
      "class", "const", "continue", "default", "do", "double", "else", "extends",
      "false", "final", "finally", "float", "for", "goto", "if", "implements",
      "import", "instanceof", "int", "interface", "long", "native", "new",
      "null", "package", "private", "protected", "public", "return", "short",
      "static", "strictfp", "super", "switch", "synchronized", "this", "throw",
      "throws", "transient", "true", "try", "void", "volatile", "while"
   };
   private JList list;
   private JComboBox combobox = new JComboBox();
	
   /** Construct the view.
    * @param aModel The model logging the events. */
   public ListView(DemoModel aModel)
   {  super();
      this.model = aModel;
      this.layoutView();
      this.registerControllers();
      //this.model.addView(this);
      //this.updateView();
   }

   /** Update the view with information from the model.  When the view
    * does not actually display information from the model, updateView may
    * be empty.  */
   public void updateView()
   {}	

   /** Set up the combobox for use */
   private JComboBox setupComboBox(String[] items)
   {  return new JComboBox(items);
   }
   
   /** Set up the list for use. */
   private JList setupList(String[] items)
   {  JList list = new JList(items);
      // allow multiples to be selected
      list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      return list;
   }
   
   /** Lay out the ocmponents in the view. */
   private void layoutView()
   {  this.combobox = this.setupComboBox(this.keywords);
      this.list = this.setupList(this.keywords);
            
      // put the list in a scroll pane so we can scroll through it
      JScrollPane listScroller = new JScrollPane(this.list);

      this.setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints(); 
      gbc.gridy = 0; // start with row 0
      gbc.anchor = GridBagConstraints.NORTHWEST; // anchor in northwest corner of cell
      gbc.weightx = .1;
      
      // labels
      this.add(new JLabel("JComboBox", SwingConstants.LEFT), gbc);
      this.add(new JLabel("JList", SwingConstants.LEFT), gbc);
      
      // components
      gbc.gridy++;
      this.add(this.combobox, gbc);
      gbc.fill = GridBagConstraints.BOTH;
      gbc.weightx = .9;
      gbc.weighty = 1;
      this.add(listScroller, gbc);
   }

   /** Register the controllers. */
   private void registerControllers()
   {  this.list.addListSelectionListener(new ListController());
      this.combobox.addActionListener(new ComboBoxController());
   }

   /** A controller for the combobox. */
   private class ComboBoxController extends Object implements ActionListener
   {
      public void actionPerformed(ActionEvent evt)
      {  JComboBox src = (JComboBox) evt.getSource();
         model.addHistory("ButtonView", this, src, "actionPerformed",
               src.getSelectedItem() + " selected");
      }
   }
   

   /** A controller for hte list. */
   private class ListController extends Object implements ListSelectionListener
   {
      public void valueChanged(ListSelectionEvent evt) 
      {  if (evt.getValueIsAdjusting() == false) 
         {  Object[] selected = ListView.this.list.getSelectedValues();
            String s = "";
            for (Object o : selected)
            {  s += o.toString() + " ";
            }
            
            ListView.this.model.addHistory("ListView", this, evt.getSource(),
                  "valueChanged", s);
         }
      }
   }
}
