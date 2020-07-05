import becker.robots.*;


/** A class of robot which can harvest a field of things.  The
 field must be 5 things wide and 6 rows high.
 @author Byron Weber Becker */
public class Harvester extends RobotSE
{

   /** Construct a new Harvester robot.
    @param aCity the city where the robot will be created.
    @param str the robot's initial street
    @param ave the robot's initial avenue
    @param dir the robot's initial direction, one of {Direction.NORTH, SOUTH, EAST, WEST}. */
   public Harvester(City aCity, int str, int ave, Direction dir)
   {  super(aCity, str, ave, dir);
   }
   
   /** Harvest a field of things.  The robot is assumed to be on the
    north-west corner of the field. */
   public void harvestField()
   {  this.harvestTwoRows();
      this.positionForNextHarvest();
      this.harvestTwoRows();
      this.positionForNextHarvest();
      this.harvestTwoRows();
   }

   /** Harvest two rows of the field, returning to the same avenue
    but one street farther south. */
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

   /** Position the robot for the next harvest by moving one street
    south and facing west. */
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
