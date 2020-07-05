
import becker.robots.*;


/** "Drive" the robot using the keys "m" for move, "r" for right,
 * and "l" for left.
 * @author Byron Weber Becker */
public class KeyBot extends RobotSE
{
   public KeyBot(City c, int str, int ave, Direction dir)
   {  super(c, str, ave, dir);
   }
		
	/** Override the keyTyped method to actually respond to the keys. */
   protected void keyTyped(char key)
   {  if (key == 'm' || key == 'M')
      {  this.move();
      } else if (key == 'r' || key == 'R')
      {  this.turnRight();
      } else if (key == 'l' || key == 'L')
      {  this.turnLeft();	// Watch out!  The above test uses a 
      }							// lowercase 'L', not a "one".
   }
}
