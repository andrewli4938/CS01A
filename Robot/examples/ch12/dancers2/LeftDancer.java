import becker.robots.*;


/** LeftDancers dance to the left as they move forward.
 *
 * @author Byron Weber Becker */
public class LeftDancer extends Dancer
{
   public LeftDancer(City c, int str, int ave, Direction dir)
   {  super(c, str, ave, dir);
      this.setLabel("L");
   }
   
   /** Dance to the left. */
   public void move()
   {  this.turnLeft();
      super.move();
      this.turnRight();
      super.move();
      this.turnRight();
      super.move();
      this.turnLeft();
   }
   
   public void pirouette()
   {  this.turnLeft();
   	this.turnLeft();
   	this.turnLeft();
   	this.turnLeft();
   }
}
