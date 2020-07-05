
/** Methods required by the model to display itself in a view.
 *
 * @author Byron Weber Becker */
public interface LogExplorerView
{
	/** Display one record.
	 * @param sr The record to display. */
	public void displayRecord(ServerRecord sr);

	/** Display the number of records that met the criteria.
	 * @param count The number of records that met the criteria. */
	public void displayCount(int count);
}
