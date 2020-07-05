import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** Read a web server's log record by record.  Print those records that contain the
 *  substring "bwbecker".
 *
 * @author Byron Weber Becker */
public class ReadServerLog
{
   public static void main(String[] args)
   {  // Open the file.
      Scanner in = null;
      try 
      {  File file = new File("server_log.txt");
      	in = new Scanner(file);  
      } catch (FileNotFoundException ex) 
      {  System.out.println(ex.getMessage());
      	System.out.println("in " + System.getProperty("user.dir"));
         System.exit(1);
      }

      // Read and process each record.
      while (in.hasNextLine())
      {  String record = in.nextLine();
         if (record.indexOf("bwbecker") > 0) // author's web pages
         {  System.out.println(record);
         }
      }
      
      // Close the file.
      in.close();
   }
}
