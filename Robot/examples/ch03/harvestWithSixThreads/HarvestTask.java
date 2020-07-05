import becker.robots.*;

public class HarvestTask extends Object
{
	public static void main (String[] args)
	{
		City stLouis = new City("Field.txt");
		ThreadedRowHarvester hr1 = new ThreadedRowHarvester(stLouis, 1, 0, Direction.EAST);
		ThreadedRowHarvester hr2 = new ThreadedRowHarvester(stLouis, 2, 0, Direction.EAST);
		ThreadedRowHarvester hr3 = new ThreadedRowHarvester(stLouis, 3, 0, Direction.EAST);
		ThreadedRowHarvester hr4 = new ThreadedRowHarvester(stLouis, 4, 0, Direction.EAST);
		ThreadedRowHarvester hr5 = new ThreadedRowHarvester(stLouis, 5, 0, Direction.EAST);
		ThreadedRowHarvester hr6 = new ThreadedRowHarvester(stLouis, 6, 0, Direction.EAST);

      Thread t1 = new Thread(hr1);
      t1.start();
      Thread t2 = new Thread(hr2);
      t2.start();
      Thread t3 = new Thread(hr3);
      t3.start();
      Thread t4 = new Thread(hr4);
      t4.start();
      Thread t5 = new Thread(hr5);
      t5.start();
      Thread t6 = new Thread(hr6);
      t6.start();
	}
}

