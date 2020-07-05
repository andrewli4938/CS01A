import becker.robots.*;
import becker.robots.icons.*;
import java.awt.Color;

/** A Lamp is a special kind of thing which appears differently depending
    on whether it is "on" or "off".

 @author Byron Weber Becker */
public class Lamp extends Light
{
   /** Construct a new Lamp.
    *  @param aCity the City where the Lamp will reside.
    *  @param aStreet the initial street.
    *  @param anAvenue the initial avenue. 
    **/
	public Lamp(City aCity, int aStreet, int anAvenue)
	{	super(aCity, aStreet, anAvenue);
      this.turnOff();
	}

   
   // Turn the lamp on.     (Version 2)
   public void turnOn()
   {  Color onColor = new Color(255, 255, 200);
      CircleIcon onIcon = new CircleIcon(onColor);
      onIcon.setSize(0.75);
      onIcon.setTransparency(0.5);
      this.setIcon(onIcon);
   }
   

   // Turn the lamp off.
   public void turnOff()
   {  Color offColor = new Color(0, 0, 0);
      CircleIcon offIcon = new CircleIcon(offColor);
      offIcon.setSize(0.25);
      this.setIcon(offIcon);
   }
}
