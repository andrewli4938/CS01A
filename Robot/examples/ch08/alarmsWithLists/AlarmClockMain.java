import becker.util.DateTime;

/** Run the alarm clock with today's alarm's.
 *
 * @author Byron Weber Becker */
public class AlarmClockMain extends Object
{
   public static void main(String[] args)
   {  AlarmClock clock = new AlarmClock(false);

		clock.setAlarm(10, 30, "Coffee break");
		clock.setAlarm(11, 00, "Call Amy");
		clock.setAlarm(17, 30, "Turn off the computer and get a life!");
      
      clock.run(1);
   }
}
