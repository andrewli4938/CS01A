import becker.robots.*;

/** A class of robot that can harvest a field of things.  The field must be 5 things wide 
 *  and 6 rows high.
 *
 *	 @author Byron Weber Becker */
public class Harvester extends RobotSE
{
   /** Construct a new Harvester robot.
    *  @param aCity The city where the robot will be created.
    *  @param str The robot's initial street.
    *  @param ave The robot's initial avenue.
    *  @param dir The robot's initial direction, one of {Direction.NORTH, SOUTH, EAST, WEST}. */
   public Harvester(City aCity, int str, int ave, Direction dir)
   {	super(aCity, str, ave, dir);
   }
   
   /** Harvest a field of things.  The robot is assumed to be on the north-west corner 
    *  of the field. */
   public void harvestField()
   {  this.harvestTwoRows();
		this.positionForNextHarvest();
		this.harvestTwoRows();
		this.positionForNextHarvest();
		this.harvestTwoRows();
   }

   /** Harvest two rows of the field, returning to the same avenue but one street 
    *  farther south. */
	protected void harvestTwoRows()
	{  this.harvestOneRow();
		this.goToNextRow();
		this.harvestOneRow();
	}

   /** Harvest one row of five things. */
	protected void harvestOneRow()
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

   /** Go one row south and face west.  The robot must be facing east.  */
	private void goToNextRow()
	{  this.turnRight();
		this.move();
		this.turnRight();
	}


   /** Go one row south and face east.  The robot must be facing west.  */
	private void positionForNextHarvest()
	{  this.turnLeft();
		this.move();
		this.turnLeft();
	}

   /** Harvest the things on one intersection. */
	protected void harvestIntersection()
	{  this.pickThing();
	}
}
