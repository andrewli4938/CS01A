import becker.robots.*;

/** A kind of Harvester robot that harvests fields with 6
 * things per row rather than just 5.
 *
 * @author Byron Weber Becker */
public class LongRowHarvester extends Harvester
{	
	public LongRowHarvester(City city, int str, int ave, Direction dir)
	{	super(city, str, ave, dir);
	}
	
	/** Override the harvestOneRow method to harvest the longer row. */
	public void harvestOneRow()
	{	super.harvestOneRow();		// harvest first 5 intersections
		this.move();					// harvest one more
		this.harvestIntersection();	
	}
}
