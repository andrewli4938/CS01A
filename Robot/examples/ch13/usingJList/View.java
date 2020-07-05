import becker.util.IView;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;


public class View extends JPanel implements IView
{  // private Object model;
   private JList list;

   public View(Object aModel)
   {  super();
      // this.model = aModel;
      this.layoutView();
      this.registerControllers();
      // this.model.addView(this);
      this.updateView();
   }

   public void updateView()
   {// statements to update the components in the view»
   }	

   private void layoutView()
   {  //this.setLayout(new BorderLayout());
   	this.list = new JList(new String[] {  "Red", "Green", "Blue", "Yellow",
         "Orange", "Pink", "Black"});
      //JScrollPane scrollpane  = new JScrollPane(this.list);
      //this.add(scrollpane, BorderLayout.CENTER);
      this.add(this.list);
   }

   private void registerControllers()
   {	this.list.addListSelectionListener(new ListController());
   }
   
   private class ListController extends Object implements ListSelectionListener
   {	public void valueChanged(ListSelectionEvent evt)
   	{	//if (!evt.getValueIsAdjusting())
   		{	System.out.println("selected " + View.this.list.getSelectedValue());
   		}
   	}
   }
}


