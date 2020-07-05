import becker.robots.*;

/** A program to harvest a field of things 5 columns wide and 6 rows high.
 *
 *  @author Byron Weber Becker */
public class HarvestTask
{
	public static void main (String[] args)
	{
		City stLouis = new City("Field.txt");
		stLouis.showThingCounts(true);
		Harvester mark = new Harvester (stLouis, 1, 0, Direction.EAST);
		
		mark.move ();
      mark.harvestField();
		mark.move ();
	}
}

