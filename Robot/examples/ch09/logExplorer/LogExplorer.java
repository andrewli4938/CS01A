import java.io.*;
import java.util.Scanner;


/** Explore a web server log by displaying/counting records meeting user specified criteria.
 *
 * 	@author Byron Weber Becker */
public class LogExplorer extends Object
{	
   // Search criteria
   private String searchHost = ""; // String to find in hostname.
   private int minSize = 0; // Minimum size of returned page.
   
   private boolean done = false; // Received quit command yet?
   private boolean displayNum = true; // Display number of matching records?
   private boolean displayRec = true; // Display each matching record?
	
   /** Create a new explorer object;  displays all log records by default. */
   public LogExplorer()
   {  super();
   }

   /** Interpret the commands entered by the user. */
   public void cmdInterpreter()
   {  this.displayHelp();
      Scanner cin = new Scanner(System.in);
      while (!this.done)
      {  System.out.print("> ");
         String line = cin.nextLine();
         this.executeCmd(line);
      }
   }
   
   /** Execute one line entered by the user.
    * @param cmdLine The one line of command and optional arguments to execute. */
   private void executeCmd(String cmdLine)
   {  Scanner line = new Scanner(cmdLine);
      if (line.hasNext())
      {  String cmd = line.next();
         if (cmd.equals("host") && line.hasNext())
         {  this.searchHost = line.next();
         } else if (cmd.equals("host"))
         {  this.searchHost = "";
         } else if (cmd.equals("min") && line.hasNextInt())
         {  this.minSize = line.nextInt();
         } else if (cmd.equals("p") && line.hasNext())
         {  this.processFile(line.next());
         } else if (cmd.equals("q"))
         {  this.done = true;
         } else if (cmd.equals("help"))
         {  this.displayHelp();
         } else if (cmd.equals("dr") && line.hasNextBoolean())
         {  this.displayRec = line.nextBoolean();
         } else if (cmd.equals("dn") && line.hasNextBoolean())
         {  this.displayNum = line.nextBoolean();
         } else
         {  System.out.println("Command '" + line + "' not recognized.");
         }
      }
   }
	
   /** Process a log file via the specified Scanner object.  Display and count each record that
    *  matches the criteria set previously. 
    * @param in A scanner for the input file to process. */
   private void processFile(Scanner in)
   {  int count = 0;
      while (in.hasNextLine())
      {  ServerRecord sr = new ServerRecord(in);
         if (this.includeRecord(sr))
         {  if (this.displayRec)
            {  this.displayRecord(sr);
            }
            count++;
         }
      }
      if (this.displayNum)
      {  this.displayCount(count);
      }
   }
   
   /** Process the specified file.
    * @param fName The name of the file to process. */
   private void processFile(String fName)
   {  Scanner in = null;
      try
      {  in = new Scanner(new File(fName));
         this.processFile(in);
         in.close();
      } catch (FileNotFoundException ex)
      {  System.err.println(
               "Can't find file " + System.getProperty("user.dir") + "/" + fName
               + ".");
      }
   }
	
   /** Determine whether a record should be included in the report. Include records that meet 
    *	ALL the specified criteria.  If a criterion wasn't set (for example, no client host name was
    *	specified), we need a way to ignore it, typically with an "or" condition.  */
   private boolean includeRecord(ServerRecord sr)
   {  return (this.searchHost.length() == 0
            || sr.hostnameContains(this.searchHost)) 
                  && sr.getSize() >= this.minSize;
   }
   
   /** Display one record to the user.
    * @param sr The record to display. */
   private void displayRecord(ServerRecord sr)
   {  System.out.printf("%-15s %s%n", sr.getIPAddress(), sr.getClientHostname());
      System.out.printf("  %5d%10d%5s %s%n", sr.getCompletionCode(),
            sr.getSize(), sr.getCommand(), sr.getURL());
   }
   
   /** Display the number of matching records.
    * @param count The number of matching records to display. */
   private void displayCount(int count)
   {  System.out.println();
      System.out.println(count + " records met the search criteria.");
   } 
   
   /** Display a help message. */
   private void displayHelp()
   {  final String helpFmt = " %-14s %s%n";
      final PrintStream out = System.out;
      out.println("General Commands:");
      out.printf(helpFmt, "q", "Quit");
      out.printf(helpFmt, "help", "Display this help message");
      out.printf(helpFmt, "p <string>", "Process specified file");
      out.println();
      out.println("Commands that affect which records are included:");
      out.printf(helpFmt, "host <string>", "Hostnames including...");
      out.printf(helpFmt, "host", "All client hostnames");
      out.printf(helpFmt, "url <string>", "Requested URLs including...");
      out.printf(helpFmt, "url", "All URLs");
      out.printf(helpFmt, "min <int>", "Served pages with a minimum size");
      out.println();
      out.println("Commands that affect how records are displayed:");
      out.printf(helpFmt, "dn <boolean>", "Display number of records");
      out.printf(helpFmt, "dr <boolean>", "Display records");
      out.println();
   }
}
