import becker.robots.*;

/** An experiment in understanding a program.
 @author Byron Weber Becker
*/
public class Shorter
{
   public static void main(String[] args)
   {  City austin = new City();
      ExperimentRobot lisa = new ExperimentRobot(austin, 3, 2, Direction.SOUTH);

      lisa.move3();
      lisa.turnRight();
      lisa.move3();
      lisa.turnAround();
      lisa.move3();
      lisa.turnLeft();
      lisa.move3();
      lisa.turnAround();
   }
}
