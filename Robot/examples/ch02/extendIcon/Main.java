
import becker.robots.*;

/** A class demonstrating a robot with a custom icon.
 @author Byron Weber Becker */
public class Main extends Object
{
   public static void main(String[] args)
   {  City waterloo = new City(0, 0, 3, 3, 90);
   	
      ArmRobot karel = new ArmRobot(waterloo, 2, 2, Direction.NORTH);

		karel.move();
		karel.turnLeft();
		karel.move();
		karel.turnLeft();
		karel.move();
		karel.turnLeft();
		karel.move();
		karel.turnLeft();
		
   }
}
 