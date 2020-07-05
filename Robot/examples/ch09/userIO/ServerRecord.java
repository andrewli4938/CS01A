import java.util.Scanner;
import becker.util.DateTime;

/** Read and represent one server log record.
 *
 * @author Byron Weber Becker */
public class ServerRecord extends Object
{
   private final String ipAddress;
   private final String hostname;
   private final DateTime when;
   private final String cmd;
   private final String url;
   private final int completeCode;
   private final int size;
   
   /** Read one web server record from an open file. Leave the input cursor
    * positioned to read the next record.  Example input:
    *
    * 66.249.64.4 crawl-66-249-64-4.googlebot.com 2005/8/19@1:08:50 GET /~bwbecker/  200 6964
    * 66.249.71.3 crawl-66-249-71-3.googlebot.com 2005/8/19@1:09:10 GET /journals/JIS/VOL6/Broughan/broughan25.tex  304 -
    *
    * The last part of the first example is the size of the information returned.
    * The second example had an error and the size is reported as "-".  This class
    * reads and interprets the "-" as 0.
    *
    * @param in An open file from which to read one record.  The input cursor must
    * be positioned immediately before the record.
    */
   public ServerRecord(Scanner in)
   {  super();
      this.ipAddress = in.next();
      this.hostname = in.next();
      this.when = new DateTime(in);
      this.cmd = in.next();
      this.url = in.next();
      this.completeCode = in.nextInt();
      if (in.hasNextInt())
      {  this.size = in.nextInt();
      } else
      {  this.size = 0;
      }
      // Get ready to read the next record
      in.nextLine();
   }
   
   /** Represent the record as a string. */
   public String toString()
   {  return this.ipAddress + " " + this.hostname + " " + this.when.toString() + " " + this.cmd + " " + 
         this.url + " " + this.completeCode + " " + this.size;
   }
   
   /** Does the url contain the specified string? */
   public boolean urlContains(String target)
   {  return this.url.indexOf(target) >= 0;
   }
   
   /** Does the hostname contain the given string? */
   public boolean hostnameContains(String search)
   {	return this.hostname.indexOf(search) >= 0;
   }
   
   /** Return the ip address. */
   public String getIPAddress()
   {  return this.ipAddress;
   }
   
   /** Return the client's host name. */
   public String getClientHostname()
   {  return this.hostname;
   }
   
   /** Return the date and time the web server served the request. */
   public DateTime getWhen()
   {  return new DateTime(this.when);
   }
   
   /** Return the command made to the web server. */
   public String getCommand()
   {  return this.cmd;
   }
   
   /** Get the requested url. */
   public String getURL()
   {  return this.url;
   }
   
   /** Get the completion code for the request. */
   public int getCompletionCode()
   {  return this.completeCode;
   }
   
   /** Get the size of the returned information.  If there was an error
    * in serving the request, return 0. */
   public int getSize()
   {  return this.size;
   }
}
