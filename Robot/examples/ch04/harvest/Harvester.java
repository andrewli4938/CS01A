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
		this.goToNextRow();
		this.harvestTwoRows();
		this.goToNextRow();
		this.harvestTwoRows();
   }

   /** Harvest two rows of the field, returning to the same avenue but one street 
    *  farther south. */
	public void harvestTwoRows()
	{  this.harvestOneRow();
		this.goToNextRow();
		this.harvestOneRow();
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

   /** Go one row south.  The robot must be on either avenue 1 or avenue 5.  */
	public void goToNextRow()
	{  if (this.getAvenue() == 5)
		{	this.turnRight();
			this.move();
			this.turnRight();
		}
		if (this.getAvenue() == 1)
		{	this.turnLeft();
			this.move();
			this.turnLeft();
		}
	}


   /** Harvest one intersection. */
	public void harvestIntersection()
	{  while (this.canPickThing())
		{this.pickThing();
		}
	}
}
