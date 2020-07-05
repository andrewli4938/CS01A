import becker.util.IView;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;


/** A view using a table to display events logged with the model.  Special
 * care is taken to always show the last row of the table as it is updated
 * by the program.
 *
 * @author Byron Weber Becker */
public class TableView extends JPanel implements IView
{
   private DemoModel model;

   private JTable history;
   private HistoryTableModelAdapter historyTM;

   /** Construct the view.
    * @param aModel The model logging the events. */
   public TableView(DemoModel aModel)
   {  super();
      this.model = aModel;
		
      this.historyTM = new HistoryTableModelAdapter(this.model);
      this.history = new JTable(this.historyTM);
		
      this.layoutView();
      this.registerControllers();
      this.model.addView(this);
      this.updateView();
   }

   /** Update the view with information from the model.  In this case
    * the row of new data is actually added in the table model.  This here
    * to ensure that it shows in the table.  */
   public void updateView()
   {  this.historyTM.fireTableRowsInserted(this.model.getItemCount(),
            this.model.getItemCount());
      TableView.ensureRowVisible(this.history, this.model.getItemCount() - 1);
   }	
	
   /** Ensure that the specified row of the given table is visible. */
   private static void ensureRowVisible(JTable table, int row)
   {  JScrollPane jsp = (JScrollPane) SwingUtilities.getAncestorOfClass(
            JScrollPane.class, table.getParent());
      JViewport jvp = jsp.getViewport();
      int portHeight = jvp.getSize().height;
      int height = table.getRowHeight();
      int celltop = table.getCellRect(row, 0, true).y;
      int position = celltop - portHeight + table.getRowHeight()
            + table.getRowMargin();
      if (position >= 0)
      {  jvp.setViewPosition(new Point(0, position));
      }
   }

   /** Layout the table in a scroll pane so the user can scroll through
    * the whole thing.  */
   private void layoutView()
   {  // allow only one row to be selected at a time
      this.history.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
   
      JScrollPane tableScroller = new JScrollPane(this.history);
      tableScroller.setPreferredSize(
            new Dimension(DemoMain.WIDTH, DemoMain.HEIGHT / 4));
      tableScroller.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      this.setLayout(new GridLayout(1, 1));
      this.add(tableScroller);
   }

   /** Register controllers on the table. */
   private void registerControllers()
   {  ListSelectionModel rowSM = this.history.getSelectionModel();
      rowSM.addListSelectionListener(new TableSelectionController());
   }
   
   private class TableSelectionController extends Object implements ListSelectionListener
   {
      public void valueChanged(ListSelectionEvent e)
      {  //Ignore extra messages.
         if (e.getValueIsAdjusting())
         {  return;
         }

         ListSelectionModel lsm = (ListSelectionModel) e.getSource();
         String info = "";
         if (lsm.isSelectionEmpty())
         { // no rows selected
         } else
         {  info = "selected row " + lsm.getMinSelectionIndex(); 
            TableView.this.model.addHistory("TableView", this, e.getSource(), "valueChanged", info);
         }
         
      }
   }

	
   /** A JTable gets its information from the model via a TableModel.  It's a 
    * class implementing some very specific methods called by the table to 
    * get the information it needs.  AbstractTableModel already implements the
    * majority of the methods needed by the table. */
   private class HistoryTableModelAdapter extends AbstractTableModel
   {
      private DemoModel model;

      public HistoryTableModelAdapter(DemoModel aModel)
      {  super();
         this.model = aModel;
      }
      
      /** Call this when a row is inserted by the program itself.  It 
       * will inform the table that there is more data to show. */
      public void fireTableRowsInserted(int firstRow, int lastRow)
      {  super.fireTableRowsInserted(firstRow, lastRow);
      }
   
      /** Get the value to display in the given row (starting at 0) and
       * column (also starting at 0). */
      public Object getValueAt(int row, int col)
      {  switch (col)
         {  case 0:
               return this.model.getView(row);

            case 1:
               return this.model.getListener(row);

            case 2:
               return this.model.getMethod(row);

            case 3:
               return this.model.getOtherInfo(row);

            default:
               assert false;
         }
         return null; // for the compiler
      }
   
      /** How many columns are there? */
      public int getColumnCount()
      {  return 4;
      }
   
      /** How many rows are there -- ask the model to find out. */
      public int getRowCount()
      {  return this.model.getItemCount();
      }
   
      /** What name should be displayed in the table's header for the given
       * column? */
      public String getColumnName(int col)
      {  switch (col)
         {  case 0:
               return "View";

            case 1:
               return "Listener";

            case 2:
               return "Method";

            case 3:
               return "Other information";

            default:
               assert false;
         }
         return ""; // for the compiler
      }
   }

}
