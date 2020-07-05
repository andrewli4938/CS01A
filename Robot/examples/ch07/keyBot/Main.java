
import becker.robots.*;


/** Run a program where the user can direct the robot using the keyboard.
 *
 * WARNING:  The robot will not respond unless
 *  1. the robot has been started by clicking the start button, and
 *  2. the city has been selected by clicking on it.
 *
 * @author Byron Weber Becker */
public class Main extends Object
{
	public static void main(String[] args) 
	{	City sanfrancisco = new City();
		KeyBot karel = new KeyBot(sanfrancisco, 1, 1, Direction.EAST);
	}	
}
