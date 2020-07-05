
import becker.robots.*;


/** A FastTurnBot turns left very quickly relative to its normal speed.
 * 
 *  @author Byron Weber Becker  */
public class FastTurnBot extends RobotSE
{
	/** Construct a new FastTurnBot.
	 * @param aCity The city in which the robot appears.
	 * @param aStreet The street on which the robot appears.
	 * @param anAvenue The avenue on which the robot appears.
	 * @param aDirection The direction the robot initially faces. */
   public FastTurnBot(City aCity, int aStreet, int anAvenue, Direction aDirection)
   {  super(aCity, aStreet, anAvenue, aDirection);
   }

	/** Turn 90 degrees to the left, but do it more quickly than normal. */
   public void turnLeft()
   {  this.setSpeed(this.getSpeed()*10);
      super.turnLeft();
      this.setSpeed(this.getSpeed()/10);
   }
   
   
   /** Original approach to overriding turnLeft. */
//   public void turnLeft()   
//   {  this.setSpeed(20);      
//      this.turnLeft();     
//      this.setSpeed(2);   
//   }
}
