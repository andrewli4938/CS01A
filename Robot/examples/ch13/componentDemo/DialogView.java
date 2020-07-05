import becker.util.IView;
import becker.gui.FormLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Container;
import java.awt.Color;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;


/** A view that includes buttons that display various kinds of dialog boxes.
 * Each dialog box logs events with the model.  The events are then displayed 
 * in another view.
 *
 * @author Byron Weber Becker */
public class DialogView extends JPanel implements IView
{
   private DemoModel model;

   private JButton showOptionPane = new JButton("showMessageDialog()");
   private JButton showGetInputPane = new JButton("showInputDialog()");
   private JButton showCustom = new JButton("Custom Dialog");
   private JButton colorChooser = new JButton("Color Chooser");
   private JButton fileChooser = new JButton("File Chooser");

   /** Construct the view.
    * @param aModel The model logging the events. */
   public DialogView(DemoModel aModel)
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

   /** Layout the components in the view. */
   private void layoutView()
   {  this.setLayout(new FormLayout());
      this.add(new JLabel("JOptionPane:", SwingConstants.RIGHT));
      this.add(this.showOptionPane);
      this.add(new JLabel("JOptionPane:", SwingConstants.RIGHT));
      this.add(this.showGetInputPane);
      this.add(new JLabel("JDialog:", SwingConstants.RIGHT));
      this.add(this.showCustom);
      this.add(new JLabel("JColorChooser:", SwingConstants.RIGHT));
      this.add(this.colorChooser);
      this.add(new JLabel("JFileChooser:", SwingConstants.RIGHT));
      this.add(this.fileChooser);
   }

   /** Register controllers for the buttons. */
   private void registerControllers()
   {  this.showOptionPane.addActionListener(new ShowOptionPaneController());
      this.showGetInputPane.addActionListener(new ShowGetInputPaneController());
      this.showCustom.addActionListener(new ShowCustomController());
      this.colorChooser.addActionListener(new ShowColorController());
      this.fileChooser.addActionListener(new ShowFileController());
   }
	
   /** Show a JOptionPane to show a message. */
   private class ShowOptionPaneController extends Object implements ActionListener
   {
      public void actionPerformed(ActionEvent evt)
      {  JOptionPane.showMessageDialog(DialogView.this,
               "Here is a 'JOptionPane.showMessageDialog'.");
         model.addHistory("DialogView", this, evt.getSource(),
               "JOptionPane.showMessageDialog",
               "JOptionPane message dialog shown");
      }
   }
   

   /** Show a JOptionPane to get some input. */
   private class ShowGetInputPaneController extends Object implements ActionListener
   {
      public void actionPerformed(ActionEvent evt)
      {  String input = JOptionPane.showInputDialog("Enter a string:");
         model.addHistory("DialogView", this, evt.getSource(),
               "JOptionPane.showInputDialog", input);
      }
   }
   

   /** Show a custom dialog box. */
   private class ShowCustomController extends Object implements ActionListener
   {
      public void actionPerformed(ActionEvent evt)
      {  // Get the JFrame that is the "parent" of this view.  The dialog uses
         // it to center the dialog, etc.      
         Container parent = DialogView.this;
         while (!(parent instanceof JFrame))
         {  parent = parent.getParent();
         }
      
         CustomDialog dlg = new CustomDialog((JFrame) parent);
         boolean ok = dlg.showDialog();
         model.addHistory("DialogView", this, evt.getSource(), "ok=" + ok,
               dlg.getInput());
      }
   }


   /** Show a color chooser. */
   private class ShowColorController extends Object implements ActionListener
   {
      public void actionPerformed(ActionEvent evt)
      {  Color newColor = JColorChooser.showDialog(DialogView.this,
               "Choose a Color", Color.WHITE);
         if (newColor != null)
         {  DialogView.this.model.addHistory("DialogView", this, evt.getSource(),
                  "actionPerformed", "new color = " + newColor.toString());
         } else
         {  DialogView.this.model.addHistory("DialogView", this, evt.getSource(),
                  "actionPerformed", "cancelled");
         }
      }
   }


   /** Show a file chooser. */
   private class ShowFileController extends Object implements ActionListener
   {
      public void actionPerformed(ActionEvent evt)
      {  // start the chooser in the current directory;  show only java files
         JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
         chooser.addChoosableFileFilter(
               new FileExtensionFilter(".java", "Java Source Files"));
               
         if (chooser.showOpenDialog(DialogView.this)
               == JFileChooser.APPROVE_OPTION)
         {  DialogView.this.model.addHistory("DialogView", this, evt.getSource(),
                  "showOpenDialog", chooser.getSelectedFile().getAbsolutePath());  
         } else
         {  DialogView.this.model.addHistory("DialogView", this, evt.getSource(),
                  "showOpenDialog", "cancelled");
         }
      }
   }
   

   /** A class used to filter out some files so that JFileChooser only shows
    * files with a specified extension. 
    * 
    * @author Byron Weber Becker */
   private class FileExtensionFilter extends FileFilter
   {
      private String ext;
      private String descr;

      /** Accept files ending with the given extension.
       *  @param extension The extension to accept (e.g. ".jpg")
       *  @parem description A description of the file accepted */
      public FileExtensionFilter(String extension, String description)
      {  super();
         this.ext = extension.toLowerCase();
         this.descr = description;
      }
   
      /** Decide whether or not the given file should be displayed.  In our
       *  case, include directories and files with a name ending in the specified
       *  extension.
       *  @param f A description of one file */
      public boolean accept(File f)
      {  if (f.isDirectory())
         {  return true;
         }
      
         return f.getName().toLowerCase().endsWith(this.ext);
      }
   
      /** Return the description of the files accepted. */
      public String getDescription()
      {  return this.descr;
      }
   }

}
