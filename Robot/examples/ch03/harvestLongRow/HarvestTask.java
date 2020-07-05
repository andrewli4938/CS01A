import becker.robots.*;

/** A program to harvest a field of things that has longer rows than usual.
 *
 *  @author Byron Weber Becker */
public class HarvestTask extends Object
{
	public static void main (String[] args)
	{
		City stLouis = new City("LongRowField.txt");
		LongRowHarvester mark = new LongRowHarvester (stLouis, 1, 0, Direction.EAST);
		
		mark.move ();
      mark.harvestField();
		mark.move ();
	}
}

