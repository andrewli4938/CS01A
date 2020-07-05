import java.util.Scanner;
import java.io.PrintWriter;
import becker.util.DateTime;

/** Represent one server log record.
*
* @author Byron Weber Becker */
public class ServerRecord extends Object
{
	private String ipAddress;
	private String hostName;
	private DateTime when;
	private String cmd;
	private String url;
	private int completionCode;
	private int size = 0;
	private boolean error = true;	// Assume an error until proven otherwise.	
	
	/** Construct an object representing one server record using information read from a file.
	*	@param in An open file, positioned at the beginning of the next record. */
	public ServerRecord(Scanner in)
	{	super();
		this.ipAddress = in.next();
		this.hostName = in.next();
		this.when = new DateTime(in);
		this.cmd = in.next();
		this.url = in.next();
		this.completionCode = in.nextInt();
		if (in.hasNextInt())
		{	this.size = in.nextInt();
			this.error = false;
		} 
		
		// Get ready to read the next record
		in.nextLine();
	}
	
	/** Write the record to a file in the same format it was read.
	 *  @param out An open output file. */
	public void write(PrintWriter out)
	{	out.print(this.ipAddress + " " + this.hostName + " ");
		out.print(this.when.toString() + " " + this.cmd + " ");
		out.print(this.url + " " + this.completionCode + " ");
		if (this.error)
		{	out.print("-");
		} else
		{	out.print(this.size);
		}
		out.println();
	}
	
	public String toString()
	{	return this.ipAddress + " " + this.hostName + " " + 
					 this.when.toString() + " " + this.cmd + " " + 
					 this.url + " " + this.completionCode + " " +
					 this.size;
	}
	
	public boolean urlContains(String target)
	{	return this.url.indexOf(target) >= 0;
	}
	
	public int getSize()
	{	return this.size;
	}
}
