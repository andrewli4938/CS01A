import becker.robots.*;

public class Main
{
   public static void main(String[] args)
   {  // Construct the initial situation.
   	City paris = new City();
      Lamp lamp1 = new Lamp(paris, 1, 1);
      Lamp lamp2 = new Lamp(paris, 2, 1);
      Robot lampMover = new Robot(paris, 1, 0, Direction.EAST);

		// Turn one lamp on and the other off.
      lamp1.turnOn();
      lamp2.turnOff();

		// Use the robot to move one of the lamps.
      lampMover.move();
      lampMover.pickThing();
      lampMover.move();
      lampMover.putThing();
      lampMover.move();
   }
}
 
