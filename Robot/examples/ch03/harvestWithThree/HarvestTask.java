import becker.robots.*;

/** Harvest a field of things using three robots.
 *  
 *  @author Byron Weber Becker */
public class HarvestTask extends Object
{
   public static void main(String[] args)
   {
      City stLouis = new City("Field.txt");
      Harvester mark = new Harvester(stLouis, 1, 0, Direction.EAST);
      Harvester lucy = new Harvester(stLouis, 3, 0, Direction.EAST);
      Harvester greg = new Harvester(stLouis, 5, 0, Direction.EAST);
				
      mark.move();
      mark.harvestTwoRows();
      mark.move();
	
      lucy.move();
      lucy.harvestTwoRows();
      lucy.move();
	
      greg.move();
      greg.harvestTwoRows();
      greg.move();
   }
}

