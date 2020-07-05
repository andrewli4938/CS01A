import becker.robots.*;


public class RightDancer extends RobotSE implements IMove
{
   public RightDancer(City c, int str, int ave, Direction dir)
   {  super(c, str, ave, dir);
   }
   
   public void move()
   {  this.turnRight();
      super.move();
      this.turnLeft();
      super.move();
      this.turnLeft();
      super.move();
      this.turnRight();
   }
}
