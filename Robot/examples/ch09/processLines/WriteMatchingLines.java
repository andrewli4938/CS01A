import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/** Read a web server's access log record by record.  Write those
 * records that contain the substring "bwbecker" to a file.
 *
 * @author Byron Weber Becker */
public class WriteMatchingLines
{
   public static void main(String[] args)
   {  // Open the files.
      Scanner in = null;
      PrintWriter out = null;
      try 
      {  in = new Scanner(new File("server_log.txt")); 
      	out = new PrintWriter("bwbecker.txt"); 
      } catch (FileNotFoundException ex) 
      {  System.out.println(ex.getMessage());
      	System.out.println("in " + System.getProperty("user.dir"));
         System.exit(1);
      }

      // Read and process each record.
      while (in.hasNextLine())
      {  String record = in.nextLine();
         if (record.indexOf("bwbecker") > 0)
         {  out.println(record);
         }

      }
      
      // Close the files.
      in.close();
      out.close();
   }
}
