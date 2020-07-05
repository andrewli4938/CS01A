
import becker.util.DateTime;
	
/** Calculate Luke's age, in days, as of the current date.
 *
 * @author Byron Weber Becker */
public class Main extends Object
{	public static void main(String[] args) 
	{
		DateTime lukesBD = new DateTime(1990, 10, 1);
		DateTime today = new DateTime();
		
		long daysOld = lukesBD.daysUntil(today);
		System.out.println("Luke is " + daysOld + " days old.");
	}	
}
