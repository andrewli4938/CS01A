import becker.robots.*;

/** A robot to harvest one row of things.
 @author Byron Weber Becker */
public class RowHarvester extends Robot
{
   /** Construct a new RowHarvester robot.
       @param aCity The City where the robot will reside.
       @param street The robot's initial street.
       @param avenue The robot's initial avenue.
       @param dir The robot's initial direction. */
	public RowHarvester(City aCity, int street, int avenue, Direction dir)
	{	super(aCity, street, avenue, dir);
	}

   /** Harvest one row of five things. The robot begins just before
       the row and ends just after it.*/
   public void harvestRow()
   {  this.move();
      this.harvestIntersection();
      this.move();
      this.harvestIntersection();
      this.move();
      this.harvestIntersection();
      this.move();
      this.harvestIntersection();
      this.move();
      this.harvestIntersection();
      this.move();
   }

   /** Harvest the things on one intersection. */
	private void harvestIntersection()
	{  this.pickThing();
	}

} 
 