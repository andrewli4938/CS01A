import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.Box;


/** A customized dialog box.
 *
 * @author Byron Weber Becker */
class CustomDialog extends JDialog
{
   private JTextArea input = new JTextArea(40, 100);
   private JButton okButton = new JButton("OK");
   private JButton cancelButton = new JButton("Cancel");
   private boolean ok = false;
   
   /** Construct the dialog box. 
    * @param parent The JFrame parent of this dialog box.  Nothing can
    * be done with the parent until the dialog is closed. */
   public CustomDialog(JFrame parent)
   {  super(parent, "Custom Dialog:", true);
   
      this.layoutDialog();
      this.registerControllers();
   }
   
   /** Layout the components in the dialog's frame. */
   private void layoutDialog()
   {  Box south = Box.createHorizontalBox();
      south.add(this.okButton);
      south.add(this.cancelButton);
      
      JPanel contents = new JPanel(new BorderLayout());
      contents.add(south, BorderLayout.SOUTH);
      contents.add(this.input, BorderLayout.CENTER);
      this.setContentPane(contents);
      
      this.setSize(350, 400);
   }
   
   /** Register the controllers used in the dialog. */
   private void registerControllers()
   {  ButtonController bc = new ButtonController();
      this.okButton.addActionListener(bc);
      this.cancelButton.addActionListener(bc);
   }
   
   /** Show the dialog.  This method "blocks" -- that is stops execution in
    * this thread until one of the buttons is clicked.  It them resumes
    * executing here and returns to the caller. */
   public boolean showDialog()
   {  this.ok = false; // set to true by okButton action
      this.input.setText("");
      super.setVisible(true); // blocks until hidden in action listener
      return this.ok;
   }
   
   /** Get the input entered by the user. */
   public String getInput()
   {  return this.input.getText();
   }
   
   /** The controller used by both buttons.  The only difference is that
    * if the OK button is clicked, it remembers that fact to return later. */
   private class ButtonController implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {  if (e.getSource() == okButton)
         {  CustomDialog.this.ok = true;
         }
         CustomDialog.this.setVisible(false);
      }
   }
}
