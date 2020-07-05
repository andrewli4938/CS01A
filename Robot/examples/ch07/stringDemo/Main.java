import becker.robots.*;

public class Main
{
   public static void main(String[] args)
   {  String greeting = "Hello";
      String name = "karel";

      System.out.println(greeting + ", " + name + "!");
      System.out.println("Did you know that 2*PI = " + 2*Math.PI + "?");

      City c = new City();
      Robot karel = new Robot(c, 1, 2, Direction.SOUTH);
      System.out.println("c=" + c);
   }
}

