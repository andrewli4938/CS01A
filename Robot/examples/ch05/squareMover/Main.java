import becker.robots.*;

/** Move a robot around a square.
 @author Byron Weber Becker */
public class Main extends Object
{
   public static void main(String[] args)
   {  City square = new City("Square.txt");
      SquareMover karel = new SquareMover(square, 0, 0, Direction.EAST);

      karel.moveAroundSquare();
   }
}
 