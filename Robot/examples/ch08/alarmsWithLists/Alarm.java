import becker.util.DateTime;
import becker.util.Utilities;

/** An Alarm represents a time when someone or something needs to be interrupted.
*
* @author Byron Weber Becker */
public class Alarm extends Object
{
	private DateTime when;
	private boolean hasRung = false;
	private String msg = "";
	
	/** Construct a new Alarm for today at the given time.
	* 	@param hr	the hour the alarm should "ring"
	* 	@param min	the minute of the hour that the alarm should "ring"
	* 	@param msg	the message the alarm gives */
	public Alarm(int hr, int min, String msg)
	{	super();
		this.when = new DateTime();
		this.when.setTime(hr, min, 0);		// the text has a deliberate bug; fixed here
		this.msg = msg;
	}

	/** Is it time for this alarm to ring?
	* 	@param currTime the current time, as determined by the calling clock
	* 	@return true if time for the alarm; false otherwise. */
	public boolean isTimeToRing(DateTime currTime)
	{	return !this.hasRung && this.when.isBefore(currTime);
	}

	/** Alert the user. */
	public void ring()
	{	this.when.setFormatInclude(DateTime.TIME_ONLY);
		this.when.setFormatLength(DateTime.SHORT);
		String time = this.when.format();
		System.out.println(time + ": " + this.msg);
		this.hasRung = true;
	}
}
