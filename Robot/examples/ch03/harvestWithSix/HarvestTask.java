import becker.robots.*;

public class HarvestTask extends Object
{
	public static void main (String[] args)
	{
		City stLouis = new City("Field.txt");
		RowHarvester hr1 = new RowHarvester(stLouis, 1, 0, Direction.EAST);
      RowHarvester hr2 = new RowHarvester(stLouis, 2, 0, Direction.EAST);
      RowHarvester hr3 = new RowHarvester(stLouis, 3, 0, Direction.EAST);
      RowHarvester hr4 = new RowHarvester(stLouis, 4, 0, Direction.EAST);
      RowHarvester hr5 = new RowHarvester(stLouis, 5, 0, Direction.EAST);
      RowHarvester hr6 = new RowHarvester(stLouis, 6, 0, Direction.EAST);

      hr1.harvestRow();
      hr2.harvestRow();
      hr3.harvestRow();
      hr4.harvestRow();
      hr5.harvestRow();
      hr6.harvestRow();
	}
}

