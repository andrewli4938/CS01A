import becker.robots.*;

// A new kind of robot that can turn around, turn right and move forward
// three intersections at a time.
// author: Byron Weber Becker
public class ExperimentRobot extends Robot
{
   // Construct a new ExperimentRobot.
   public ExperimentRobot(City theCity, int avenue, int street, Direction aDirection)
   {  super(theCity, avenue, street, aDirection);
   }

   //Turn the robot around so it faces the opposite direction. 
   public void turnAround()
   {  this.turnLeft();
      this.turnLeft();
   }

   //Move the robot forward three times.
   public void move3()
   {  this.move();
      this.move();
      this.move();
   }

   //Turn the robot 90 degrees to the right by turning around and then left by 90 degrees.
   public void turnRight()
   {  this.turnAround();
      this.turnLeft();
   }
}
