import becker.robots.*;

/** An experiment in understanding a program.
 *	
 *  @author Byron Weber Becker */
public class Longer
{
   public static void main(String[] args)
   {  City austin = new City();
      Robot lisa = new Robot(austin, 3, 3, Direction.EAST);

      lisa.move();
      lisa.move();
      lisa.move();
      lisa.turnLeft();
      lisa.turnLeft();
      lisa.turnLeft();
      lisa.move();
      lisa.move();
      lisa.move();
      lisa.turnLeft();
      lisa.turnLeft();
      lisa.move();
      lisa.move();
      lisa.move();
      lisa.turnLeft();
      lisa.move();
      lisa.move();
      lisa.move();
      lisa.turnLeft();
      lisa.turnLeft();
   }
}
