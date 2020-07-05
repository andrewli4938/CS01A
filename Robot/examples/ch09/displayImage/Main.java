
import javax.swing.*;
import java.io.File;


/** A program testing the operation of JFileChooser. 
 * 
 * @author Byron Weber Becker */
public class Main extends Object
{
   public static void main(String[] args)
   {  // create the dialog box and set up filters for what it displays
      JFileChooser chooser = new JFileChooser();
      chooser.addChoosableFileFilter(new FileExtensionFilter(".jpg", "jpg Graphics Files"));
      chooser.addChoosableFileFilter(new FileExtensionFilter(".gif", "gif Graphics Files"));
      
      // show the dialog
      int result = chooser.showOpenDialog(null);
      if (result == JFileChooser.APPROVE_OPTION)
      {  JComponent imageComp = new ImageComponent(chooser.getSelectedFile().getPath());
         
         JFrame f = new JFrame("Image");
         f.setContentPane(imageComp);
         f.setSize(500, 500);
         f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         f.setVisible(true);
      } else 
      {  System.out.println("No file selected.");
      } 
   
   }
}
