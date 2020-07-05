import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.HashMap;
import becker.util.DateTime;
import javax.swing.JFileChooser;


/** Format a web server log to be easier to read. 
 *
 * Note:  This program uses a feature of the Scanner class that is not covered
 * in the textbook.
 * 
 * Sample input line:
 *  66.249.71.57 - - [19/Aug/2005:01:08:16 -0400] "GET /undergrad/handbook/programs/plans/bcs_dho.shtml HTTP/1.0" 200 14035
 *
 * @author Byron Weber Becker */
public class FormatLog
{
   private Scanner in;
   private PrintWriter out;
   private static final HashMap<String, Integer> months = new HashMap<String, Integer>();
   private static final HashMap<String, String> clientHostnames = new HashMap<String, String>();
   private static final String HOSTS_FILE = "hosts.txt";
	
   public FormatLog(String inFile, String outFile)
   {  super();
      try 
      {  this.in = new Scanner(new File(inFile));  
         this.out = new PrintWriter(outFile);
      } catch (IOException ex) 
      {  System.err.println(ex.getMessage());
         System.exit(1);
      }
      
      this.initMonthsMap();
      this.readClientHostMap();
   }
	
   /** Read a date/time from the file and use it to construct and return a
    * DateTime object.
    *
    * Format: [19/Aug/2005:01:08:16 -0400]
    *
    * This method uses regular expressions, a feature of Scanner that is
    * not covered in the textbook.  See the online documentation. */
   private DateTime readDate(Scanner in)
   {  String date = in.next();
      in.next(); // skip timezone
	   
      Scanner din = new Scanner(date).useDelimiter("[/:\\[]");
      int day = din.nextInt();
      String month = din.next();
      int year = din.nextInt();
      int hour = din.nextInt();
      int minute = din.nextInt();
      int second = din.nextInt();
	   
      return new DateTime(year, this.months.get(month), day, hour, minute,
            second);
   }

   private void format()
   {  int line = 1;
      while (this.in.hasNextLine())
      {  String url = "";
         try 
         {  String ip = this.in.next();
            
            // Skip clientID, username
            this.in.next();
            this.in.next();
            
            DateTime date = this.readDate(this.in);
            
            String cmd = this.in.next(); // "GET
            cmd = cmd.substring(1);
            
            url = this.in.next(); // /undergrad...
            
            // skip protocol
            this.in.next();
            
            String rest = this.in.nextLine().trim();
            
            String host = this.clientHostnames.get(ip);
            if (host == null)
            {  host = this.getClientHostName(ip);
               this.clientHostnames.put(ip, host);
               System.out.print("C");
            } else
            {  System.out.print("F");
            }
			
            out.println(
                  ip.trim() + " " + host.trim() + " " + date.toString() + " " + cmd + " "
                  + url + " " + rest);
			
         } catch (Exception ex) 
         {  System.err.println(ex.getMessage() + "line: " + line);
            System.err.println("  url: " + url);
            System.err.println(" rest: " + this.in.nextLine());
         }
         line++;
      }
      this.in.close();
      this.out.close();
   }
   
   private String getClientHostName(String ip)
   {  try 
      {  return InetAddress.getByName(ip).getHostName();
      } catch (Exception ex) 
      {  return ip;
      }
   }
   
   private void initMonthsMap()
   {  this.months.put("Jan", 1);
      this.months.put("Feb", 2);
      this.months.put("Mar", 3);
      this.months.put("Apr", 4);
      this.months.put("May", 5);
      this.months.put("Jun", 6);
      this.months.put("Jul", 7);
      this.months.put("Aug", 8);
      this.months.put("Sep", 9);
      this.months.put("Oct", 10);
      this.months.put("Nov", 11);
      this.months.put("Dec", 12);
   }
   
   /** Read the client hosts found from a previous run of the program so
    * we don't have to look them up again. */
   private void readClientHostMap()
   {  Scanner in;
      try 
      {  in = new Scanner(new File(HOSTS_FILE));
         while (in.hasNextLine())
         {  this.clientHostnames.put(in.next(), in.nextLine().trim());
         }
         in.close();
      } catch (Exception ex) 
      {// doesn't exist;  must be program's first run
      }
   }
   
   /** Read the client host names we've found so we don't have to look them up
    * again the next time we run the program. */
   public void writeClientHostMap()
   {  PrintWriter out;
      try 
      {  out = new PrintWriter(HOSTS_FILE);
         for (String key : this.clientHostnames.keySet())
         {  out.println(key + " " + this.clientHostnames.get(key));
         }
         out.close();
      } catch (FileNotFoundException ex) 
      {  ex.printStackTrace();
         System.exit(0);
      }
   }
   
   public static void main(String[] args)
   {  System.out.println("Note:  This process may take a long time and there");
   	System.out.println("may be times when it pauses while waiting for a");
   	System.out.println("response from a domain name lookup.  It also requires");
   	System.out.println("an active network connection.  It will print ");
   	System.out.println("DONE when it is done.");
   	System.out.println();
   
   	JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
   	
      int openResult = chooser.showOpenDialog(null);
      if (openResult == JFileChooser.APPROVE_OPTION)
      {  String openPath = chooser.getSelectedFile().getPath();
   		
         int saveResult = chooser.showSaveDialog(null);
         if (saveResult == JFileChooser.APPROVE_OPTION)
         {  String savePath = chooser.getSelectedFile().getPath();
   		
            FormatLog formatter = new FormatLog(openPath, savePath);
            formatter.format();
            formatter.writeClientHostMap();
         }
      }
      System.out.println("DONE");
   }
}
