import becker.robots.*;

/** A robot that switches lamps on and off.  
 *
 * @author Byron Weber Becker */
public class SwitchBot extends Robot
{
   /** Construct the new robot. */
   public SwitchBot(City aCity, int aStreet, int anAvenue, Direction aDirection)
   {  super(aCity, aStreet, anAvenue, aDirection);
   }
   
   /** Switch every other lamp off and the remaining lamps on. */
   public void switchLights()
   {  this.move();
      this.examineLights().next().turnOff();
      this.move();
      this.examineLights().next().turnOn();
      this.move();
      this.examineLights().next().turnOff();
      this.move();
      this.examineLights().next().turnOn();
      this.move();
      
   }
}
