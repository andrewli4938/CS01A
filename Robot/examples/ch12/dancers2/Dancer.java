import becker.robots.*;

public abstract class Dancer extends RobotSE
{
   public Dancer(City c, int str, int ave, Direction dir)
   {  super(c, str, ave, dir);
   }
   
   public abstract void pirouette();
}
