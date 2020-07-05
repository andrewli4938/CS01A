import java.io.*;
import java.net.*;
import java.util.Scanner;

/** Read a web server's access log. Write selected records to a file.
 *
 * @author Byron Weber Becker */
public class ReadServerRecords
{
   public static void main(String[] args)
   {  // Open the files.
      Scanner in = null;
      PrintWriter out = null;
      try 
      {  in = new Scanner(new File("server_log.txt"));  
      	out = new PrintWriter("largeFiles.txt");
      } catch (FileNotFoundException ex) 
      {  System.out.println(ex.getMessage());
      	System.out.println("in " + System.getProperty("user.dir"));
         System.exit(1);
      }

      // Read and process each record.
      while (in.hasNextLine())
      {  ServerRecord sr = new ServerRecord(in);
         if (sr.getSize() >= 100000)
         {  sr.write(out);
         }

      }
      
      // Close the file.
      in.close();
      out.close();
   }
}
