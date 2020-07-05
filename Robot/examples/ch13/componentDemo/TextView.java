import becker.util.IView;
import becker.gui.FormLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.DateFormat;
import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;


/** A view that includes components that are instances JTextField, JPasswordField,
 * JFormattedTextField, JTextArea, and JEditorPane.
 * Each component logs events with the model.  The events are then displayed 
 * in another view.
 *
 * @author Byron Weber Becker */
public class TextView extends JPanel implements IView
{
   private DemoModel model;

   private JEditorPane editorPane;
   private JTextField text = new JTextField(8);
   private JPasswordField pass = new JPasswordField(8);
   private JFormattedTextField numField = new JFormattedTextField(
         NumberFormat.getIntegerInstance());
   private JFormattedTextField dateField = new JFormattedTextField(
         DateFormat.getDateInstance(DateFormat.SHORT));
   private JTextArea tArea = new JTextArea(6, 15);

   private static final String URL = "file:editorPaneContents.html";
	
   /** Construct the view.
    * @param aModel The model logging the events. */
   public TextView(DemoModel aModel)
   {  super();
      this.model = aModel;
		
      try
      {  // load the contents of the editor pane from an html file
         URL contents = TextView.class.getResource(URL);
         this.editorPane = new JEditorPane(URL);
      } catch (IOException e)
      {  System.out.println("Error reading '" + URL + "'.");
         System.out.println(e.getMessage());
         this.editorPane = new JEditorPane();
      }
		
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

   /** Lay out the components in the view. */
   private void layoutView()
   {  this.editorPane.setEditable(false);
      this.numField.setPreferredSize(this.text.getPreferredSize());
      this.dateField.setPreferredSize(this.text.getPreferredSize());
      this.dateField.setText("12/31/2008");
    	   
      JPanel fields = new JPanel(new FormLayout());
      fields.add(new JLabel("JTextField:", SwingConstants.RIGHT));
      fields.add(this.text);
      fields.add(new JLabel("JPasswordField:", SwingConstants.RIGHT));
      fields.add(this.pass);
      fields.add(new JLabel("JFormattedTextField:", SwingConstants.RIGHT));
      fields.add(this.numField);
      fields.add(new JLabel("JFormattedTextField:", SwingConstants.RIGHT));
      fields.add(this.dateField);
      fields.add(new JLabel("JTextArea:", SwingConstants.RIGHT));
      fields.add(this.tArea);
   
      JPanel editor = new JPanel(new BorderLayout());
      editor.add(new JLabel("JEditorPane:"), BorderLayout.NORTH);
      editor.add(new JScrollPane(this.editorPane), BorderLayout.CENTER);
      
      this.setLayout(new BorderLayout());
      this.add(fields, BorderLayout.WEST);
      this.add(editor, BorderLayout.CENTER);
   }

   /** Register controllers with the components. */
   private void registerControllers()
   {  TextFieldActionController tfac = new TextFieldActionController();
      this.text.addActionListener(tfac);
      this.pass.addActionListener(tfac);
      this.numField.addActionListener(tfac);
      this.dateField.addActionListener(tfac);
      
      TextFieldFocusController fc = new TextFieldFocusController();
      this.text.addFocusListener(fc);
      this.pass.addFocusListener(fc);
      this.numField.addFocusListener(fc);
      this.dateField.addFocusListener(fc);
      this.tArea.addFocusListener(fc);
      this.editorPane.addFocusListener(fc);
      
      this.editorPane.addHyperlinkListener(new HyperlinkController());
   }
   
   private class TextFieldActionController extends Object implements ActionListener
   {
      public void actionPerformed(ActionEvent evt)
      {  JTextComponent tf = (JTextComponent) evt.getSource();
         TextView.this.model.addHistory("TextView", this, tf, "actionPerformed",
               tf.getText());
      }
   }
   

   /** Focus is gained or lost when the user clicks in another component or
    * tabs to another component. */
   private class TextFieldFocusController extends Object implements FocusListener
   {
      public void focusGained(FocusEvent evt)
      {  JTextComponent tf = (JTextComponent) evt.getSource();
         TextView.this.model.addHistory("TextView", this, tf, "focusGained",
               tf.getText());
      }
      
      public void focusLost(FocusEvent evt)
      {  JTextComponent tf = (JTextComponent) evt.getSource();
         TextView.this.model.addHistory("TextView", this, tf, "focusLost",
               tf.getText());
      }
   }
   

   /** Control what happens when a hyperlink is clicked in the editor pane. */
   private class HyperlinkController implements HyperlinkListener
   {
      public void hyperlinkUpdate(HyperlinkEvent e)
      {  if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
         {  JEditorPane pane = (JEditorPane) e.getSource();
            try
            {  pane.setPage(e.getURL());
               TextView.this.model.addHistory("TextView", this, pane,
                     "hyperlinkUpdate", e.getURL().toString());
            } catch (Throwable t)
            {// do nothing
            }
         } else
         {}
         
      }
   }

}
