
import becker.robots.*;

/** A program to test a FastTurnBot.
 * @author Byron Weber Becker  */
public class Main extends Object
{
   public static void main(String[] args)
   {  City cairo = new City();
      FastTurnBot speedy = new FastTurnBot(cairo, 1, 1, Direction.EAST);

		//speedy.setSpeed(20);
      speedy.turnLeft();
      speedy.move();
      speedy.turnLeft();
      speedy.turnLeft();
      speedy.turnLeft();
      speedy.turnLeft();
      speedy.turnLeft();
      speedy.move();
   }
}
