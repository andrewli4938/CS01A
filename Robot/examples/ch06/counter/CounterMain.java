import becker.robots.*;

public class CounterMain extends Object
{
   public static void main(String[] args)
   {
      City turin = new City();
      CounterBot1 alan = new CounterBot1(turin, 1, 0, Direction.EAST);
      Thing t1 = new Thing(turin, 1, 2);
      Thing t2 = new Thing(turin, 1, 3);
      Thing t3 = new Thing(turin, 1, 5);
      Wall w1 = new Wall(turin, 1, 5, Direction.EAST);
      
      CounterBot2 anna = new CounterBot2(turin, 2, 0, Direction.EAST);
      Thing t4 = new Thing(turin, 2, 2);
      Thing t5 = new Thing(turin, 2, 3);
      Thing t6 = new Thing(turin, 2, 5);
      Wall w2 = new Wall(turin, 2, 5, Direction.EAST);

      System.out.print(alan.numIntersectionsWithThings());
      System.out.println(" intersections with Things before the wall.");

      System.out.print(anna.numIntersectionsWithThings());
      System.out.println(" intersections with Things before the wall.");
   }
}
