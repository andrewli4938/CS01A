
import javax.swing.JFileChooser;
import java.io.File;


/** A program testing the operation of JFileChooser. 
 * 
 * @author Byron Weber Becker */
public class Main extends Object
{
   public static void main(String[] args)
   {  System.out.println("Ready to get a file name.");
   	
   	// create the dialog box and set up filters for what it displays
      JFileChooser chooser = new JFileChooser();
      chooser.addChoosableFileFilter(new FileExtensionFilter(".txt", "Text Files"));
      chooser.addChoosableFileFilter(new FileExtensionFilter(".jpg", "jpg Graphics Files"));
      chooser.addChoosableFileFilter(new FileExtensionFilter(".gif", "gif Graphics Files"));
      
      // show the dialog
      int result = chooser.showOpenDialog(null);
      if (result == JFileChooser.APPROVE_OPTION)
      {  System.out.println("You chose '" + chooser.getSelectedFile().getPath());
      } else 
      {  System.out.println("No file selected.");
      } 
   
   }
}
