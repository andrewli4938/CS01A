
import javax.swing.filechooser.FileFilter;
import java.io.File;


/** A class used to filter out some files so that JFileChooser only shows
 * files with a specified extension. 
 * 
 * @author Byron Weber Becker */
public class FileExtensionFilter extends FileFilter
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
    *  case, include directories as well as files with a name ending in the specified
    *  extension.
    *  @param f A description of one file.
    *	 @return True if the file should be displayed to the user; false otherwise. */
   public boolean accept(File f)
   {  return f.isDirectory() || f.getName().toLowerCase().endsWith(this.ext);
   }
	
   /** Return the description of the files accepted. 
    *	@return A description of the files this filter accepts.*/
   public String getDescription()
   {  return this.descr;
   }
}
