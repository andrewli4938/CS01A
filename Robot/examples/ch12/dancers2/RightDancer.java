import becker.robots.*;


/** RightDancers dance to the right as they move forward.
 *
 * @author Byron Weber Becker */
public class RightDancer extends Dancer
{
   public RightDancer(City c, int str, int ave, Direction dir)
   {  super(c, str, ave, dir);
      this.setLabel("R");
   }
   
   /** Dance to the right. */
   public void move()
   {  this.turnRight();
      super.move();
      this.turnLeft();
      super.move();
      this.turnLeft();
      super.move();
      this.turnRight();
   }
   
   public void pirouette()
   {  this.turnRight();
   	this.turnRight();
   	this.turnRight();
   	this.turnRight();
   }
}
