
import becker.robots.*;


public class Main extends Object
{
	
   public static void main(String[] args)
   {  City calgary = new City();
      LimitedBot pat = new LimitedBot(calgary, 1, 1, Direction.EAST, 3);
      Thing t1 = new Thing(calgary, 1, 1);
      Thing t2 = new Thing(calgary, 1, 2);
      Thing t3 = new Thing(calgary, 1, 3);
      Thing t4 = new Thing(calgary, 1, 4);
   	
      //while(pat.canPickThing())
      for(int i=0; i<3; i = i + 1)
      {  pat.pickThing();
         pat.move();
      }
   }	
}
