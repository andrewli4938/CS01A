import java.util.Scanner;


/** List files in a user-specified web server log that meet a minimum size criteria.
 * Report on the number of files that are printed.
 *
 * @author Byron Weber Becker */
public class ListFilesBySize 
{
   public static void main(String[] args)
   {  // Prompt for the file to process.
      Scanner in = Prompt.forInputScanner("Server log to process: ");
      
      // Get the minimum size from the user.
      int minSize = Prompt.forInt("Minimum file size: ");
      
      // Process the files.
      int count = 0;
      while (in.hasNextLine())
      {  ServerRecord sr = new ServerRecord(in);
         if (sr.getSize() >= minSize)
         {  System.out.println(sr.toString());
            count++;
         }
      }
      
      // Close the input file and report the count.
      in.close();
      System.out.println(count + " files are at least " + minSize + " bytes.");
   }
}
