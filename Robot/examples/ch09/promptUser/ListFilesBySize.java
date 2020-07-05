import com.learningwithrobots.bwbecker.io.Prompt;
import com.learningwithrobots.bwbecker.webServer.ServerRecord;
import java.util.Scanner;

/*
 * NOTE:  This version of ListFilesBySize is set up for section 9.6,
 * Constructing a Library.  If you are running it as part of section 9.4,
 * Interacting with Users, you will need to:
 *		a) copy Prompt.java and ServerRecord.java to the same directory as 
 *       ListFilesBySize.java, and
 *    b) remove the first two lines (importing those two classes)


/** List files in a user-specified web server log that meet a minimum size criteria.
 * Report on the number of files that are printed.
 *
 * @author Byron Weber Becker */
public class ListFilesBySize 
{
   public static void main(String[] args)
   {  // Prompt for the file to process.
   	Scanner in = Prompt.forInputScanner("Name of the web server log: ");
      
      // Get the minimum size from the user.
      int minSize = Prompt.forInt("Minimum served file size: ");
      
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
      System.out.println(count + " files served were at least " + minSize + " bytes.");
   }
}
