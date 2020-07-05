import becker.robots.*;


/** A LimitedBot can carry or hold only a limited number of things.  The 
 * actual limit set when the robot is constructed.
 *
 * @author Byron Weber Becker */
public class LimitedBot extends Robot
{
   private int maxHold;			// Maximum # of things this robot can hold.
   private int numHeld = 0;		// Number of things currently held by this robot.
	
   /** Construct a new LimitedBot.
    * @param aCity	This robot's city
    * @param aStr		This robot's initial street.
    * @param anAve	This robot's initial avenue.
    * @param aDir		This robot's initial direction.
    * @param maxCanHold	The maximum number of things this robot can carry/hold. */
   public LimitedBot(City aCity, int aStr, int anAve, Direction aDir,
         int maxCanHold)
   {  super(aCity, aStr, anAve, aDir);
      this.maxHold = maxCanHold;
   }

   /** Pick up a thing.  If the robot is already holding the maximum number 
    * of things, it breaks. */
   public void pickThing()
   {  if (this.numHeld == this.maxHold)
      {  this.breakRobot("Tried to pick up too many things.");
      } else
      {  super.pickThing();
         this.numHeld = this.numHeld + 1;
      }
   }
	
   /** Put down one thing. */
   public void putThing()
   {  super.putThing();
      this.numHeld = this.numHeld - 1;
   }
}

