import becker.robots.*;


public class LeftDancer extends RobotSE implements IMove
{
   public LeftDancer(City c, int str, int ave, Direction dir)
   {  super(c, str, ave, dir);
   }
   
   public void move()
   {  this.turnLeft();
      super.move();
      this.turnRight();
      super.move();
      this.turnRight();
      super.move();
      this.turnLeft();
   }
}
