import becker.robots.*;

/** A robot to harvest one row of things.
 @author Byron Weber Becker */
public class ThreadedRowHarvester extends Robot implements Runnable
{
   /** Construct a new RowHarvester robot.
       @param aCity the City where the robot will reside.
       @param avenue the robot's initial avenue.
       @param street the robot's initial street.
       @param dir the robot's initial direction. */
	public ThreadedRowHarvester(City aCity, int street, int avenue, Direction dir)
	{	super(aCity, street, avenue, dir);
	}

   /** Harvest one row of five things. */
   public void harvestOneRow()
   {  this.harvestIntersection();
      this.move();
      this.harvestIntersection();
      this.move();
      this.harvestIntersection();
      this.move();
      this.harvestIntersection();
      this.move();
      this.harvestIntersection();
   }

   /** Harvest the things on one intersection. */
	private void harvestIntersection()
	{  this.pickThing();
	}

   /** What the robot does after its thread is started. */
   public void run()
   {  this.move();
      this.harvestOneRow();
      this.move();
   }

}
