import becker.util.DateTime;
import becker.util.Utilities;
import java.util.ArrayList;

/** Maintain a set alarms.  Keep time and ring alarms at the appropriate times.
*
* @author Byron Weber Becker */
public class AlarmClock extends Object
{
	// A list of alarms.
	private ArrayList<Alarm> alarms = new ArrayList<Alarm>();
	
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
	{	for(Alarm anAlarm : this.alarms)
		{	this.checkOneAlarm(anAlarm, currTime);
		}
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
		this.alarms.add(theAlarm);
		
		this.numAlarmsLeft++;
	}

	// For testing
	public static void main(String[] args)
	{	AlarmClock clock = new AlarmClock(true);
		
		clock.setAlarm(10, 30, "Coffee break");
		clock.setAlarm(11, 00, "Call Amy");
		clock.setAlarm(17, 30, "Turn off the computer and get a life!");
		
		clock.run(3600); 
	}
}
