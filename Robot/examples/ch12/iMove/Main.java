import becker.robots.*;


public class Main 
{
   public static void main(String[] args)
   {  City c = new City();
      
      IMove[] movers = new IMove[3];
      
      movers[0] = new LeftDancer(c, 1, 1, Direction.EAST);
      movers[1] = new RightDancer(c, 3, 1, Direction.EAST);
      movers[2] = new Banner(80, 165, 40, "Robot Parade");
      
      for (int numMoves = 0; numMoves < 2; numMoves++)
      {  for (int i = 0; i < movers.length; i++)
         {  movers[i].move();
         }
         becker.util.Utilities.sleep(500);
      }
   }
}

