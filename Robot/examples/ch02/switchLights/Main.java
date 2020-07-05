import becker.robots.*;

/** Construct a city with two lamp objects, one on and one off.  
 *  Instruct a robot to turn the first one off and the other one on.
 *
 *  @author Byron Weber Becker 
 */ 
public class Main
{
   public static void main(String[] args)
   {  // Construct the initial situation.
   	City paris = new City();
      Lamp lamp1 = new Lamp(paris, 1, 1);
      Lamp lamp2 = new Lamp(paris, 1, 2);
      Flasher flash = new Flasher(paris, 1, 3);
      Streetlight sLight = new Streetlight(paris, 1, 4, Direction.SOUTHEAST);
      SwitchBot switcher = new SwitchBot(paris, 1, 0, Direction.EAST);

		// Turn one lamp on and the other off.
      lamp1.turnOn();
      lamp2.turnOff();
      flash.turnOn();
      sLight.turnOff();

		// Use the robot to switch the lamps.
      switcher.switchLights();
   }
}
 
