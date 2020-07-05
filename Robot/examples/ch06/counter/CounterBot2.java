import becker.robots.*;

public class CounterBot2 extends RobotSE
{
   public CounterBot2(City city, int str, int ave, Direction dir)
   {  super(city, str, ave, dir);
   }
   
   public int numIntersectionsWithThings()
   {  int intersections = 0;
      while(true)
      {  if (this.canPickThing())
         {  intersections = intersections + 1;
         }
         if (!this.frontIsClear()) {  break;  }
         this.move();
      }
      return intersections;
   }
}
