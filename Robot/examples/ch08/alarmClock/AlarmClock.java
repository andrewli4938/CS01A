import becker.util.DateTime;
import becker.util.Utilities;

/** Maintain a set of up to four alarms.  Keep time and ring alarms at the appropriate times.
*
* @author Byron Weber Becker */
public class AlarmClock extends Object
{
	// Allow up to four alarms.
	private Alarm alarm1 = null;
	private Alarm alarm2 = null;
	private Alarm alarm3 = null;
	private Alarm alarm4 = null;
	
	// Count the alarms left to be rung.
	private int numAlarmsLeft = 0;
	// Make time pass more quickly when testing.
	private final boolean TESTING;
	
	/** Construct a new alarm clock. 
	*	@param test When true, the run method makes "time" pass more quickly for testing. */
	public AlarmClock(boolean test)
	{	super();
		this.TESTING = test;
	}

	/** Run the clock for one day, ringing any alarms at the appropriate times.  
	* 	@param secPerSec The speed with which the clock should run (for testing purposes).
	*		Each second of real time advances this clock the given number of seconds.  With
	*		a value of 3600 one "day" takes about 24 seconds of elapsed time. */
	public void run(int secPerSec)
	{	DateTime currTime = new DateTime();
		
		while (this.numAlarmsLeft > 0)
		{	if (this.TESTING)
			{	currTime.addSeconds(secPerSec);
			} else
			{	currTime = new DateTime();
			}
			
			this.checkAndRingAlarms(currTime);
			Utilities.sleep(1000); // sleep one second real time
		}
	}
	
	// Check each alarm.  Ring it if it's time.
	private void checkAndRingAlarms(DateTime currTime)
	{	this.checkOneAlarm(this.alarm1, currTime);
		this.checkOneAlarm(this.alarm2, currTime);
		this.checkOneAlarm(this.alarm3, currTime);
		this.checkOneAlarm(this.alarm4, currTime);
	}

	// Check one alarm.  Ring it if it's time.
	private void checkOneAlarm(Alarm alarm, DateTime currTime)
	{	if (alarm != null && alarm.isTimeToRing(currTime))
		{	alarm.ring();
			this.numAlarmsLeft--;
		}
	}

	/** Set an alarm to ring at the given time today.  A maximum of four alarms may be set.
	* 	@param hr 	The hour the alarm should ring.
	* 	@param min 	The minute of the hour the alarm should ring.
	* 	@param msg 	Why the alarm is being set */
	public void setAlarm(int hr, int min, String msg)
	{	Alarm theAlarm = new Alarm(hr, min, msg);
		if (this.alarm1 == null)
		{	this.alarm1 = theAlarm;
		} else if (this.alarm2 == null)
		{	this.alarm2 = theAlarm;
		} else if (this.alarm3 == null)
		{	this.alarm3 = theAlarm;
		} else if (this.alarm4 == null)
		{	this.alarm4 = theAlarm;
		} else
		{	System.out.println("Too many alarms.");
		}
		
		this.numAlarmsLeft++;
	}

	// For testing
	public static void main(String[] args)
	{	
		
		// Test using accelerated time.
		AlarmClock clock = new AlarmClock(true);
		      
      // Use times just after "now" for testing in real time.
      clock.setAlarm(10, 30, "Coffee break");
		clock.setAlarm(11, 00, "Call Amy");
		clock.setAlarm(17, 30, "Turn off the computer and get a life!");
		
		clock.run(3600);  
		
		
		// Test in real time
		// This additional testing is not in the textbook.
		AlarmClock clock2 = new AlarmClock(false);
		
      DateTime now = new DateTime();
      
      // Use times just after "now" for testing in real time.
      clock2.setAlarm(now.getHour(), now.getMinute()+1, "Coffee break");
      clock2.setAlarm(now.getHour(), now.getMinute()+2, "Call Amy");
      clock2.setAlarm(now.getHour(), now.getMinute()+3, "Turn off the computer and get a life!");
		
		clock2.run(1);
	}
}
