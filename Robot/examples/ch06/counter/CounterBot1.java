import becker.robots.*;

public class CounterBot1 extends RobotSE
{
   private int intersections = 0;

   public CounterBot1(City city, int str, int ave, Direction dir)
   {  super(city, str, ave, dir);
   }

   public int numIntersectionsWithThings()
   {  while(true)
      {  if (this.canPickThing())
         {  this.intersections = this.intersections + 1;
         }
         if (!this.frontIsClear())   {  break;  }
         this.move();
      }
      return this.intersections;
   }

 }
