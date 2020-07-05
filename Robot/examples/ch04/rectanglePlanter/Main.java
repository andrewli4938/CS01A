/* REMINDER:  This sample solution is provided with the requirement that you
 * will NOT reproduce or distribute it.
 *
 * Copyright 2006 by Byron Weber Becker 
 */
 
import becker.robots.*;


public class Main
{
   public static void main(String[] args)
   {	City field = new City();
   
      RectanglePlanter karel = new RectanglePlanter(field, 1, 1, Direction.EAST, 50);
      RectanglePlanter lindy = new RectanglePlanter(field, 0, 0, Direction.EAST, 50);
      
      karel.plantRect(4, 3);
      lindy.plantRect(7, 5);
   }
}