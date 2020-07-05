import becker.util.IView;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;


/** A view that organizes sub-views.
 *
 * @author Byron Weber Becker */
public class DemoView extends JPanel implements IView
{
   //private DemoModel model;
	
   /** Construct the view.
    * @param aModel The model logging the events. */
   public DemoView(DemoModel aModel)
   {  super();
      //this.model = aModel;
		
      this.layoutView(aModel);
      //this.registerControllers();
      
      //this.model.addView(this);
   }

   /** Update the view with information from the model.  When the view
    * does not actually display information from the model, updateView may
    * be empty.  */
   public void updateView()
   {}
   
   /** Layout the view as a series of tabs in a tabbed pane. */
   private void layoutView(DemoModel model)
   {  JTabbedPane tabs = new JTabbedPane();
      tabs.addTab("Controls", this.controlsTab(model));
      tabs.addTab("Text", this.textTab(model)); 
      tabs.addTab("Mouse & Dialogs", this.miscTab(model));  
      tabs.addTab("Images", this.imageTab(model));  

      JPanel tableView = new TableView(model);
      tableView.setBorder(BorderFactory.createTitledBorder("JTable"));
      
      JPanel center = new JPanel(new BorderLayout());
      center.add(tabs, BorderLayout.CENTER);
      center.add(tableView, BorderLayout.SOUTH);
      
      // toolbar must go in a container managed by a borderlayout.  Everything
      // except the toolbar goes in the center section.
      this.setLayout(new BorderLayout());
      this.add(new ToolbarView(model), BorderLayout.NORTH);
      this.add(center, BorderLayout.CENTER);
   }

   /** layout the tab for the controls. */
   private JPanel controlsTab(DemoModel model)
   {  JPanel buttons = new ButtonView(model);
      buttons.setBorder(BorderFactory.createTitledBorder("Buttons"));
      JPanel list = new ListView(model);
      list.setBorder(BorderFactory.createTitledBorder("Lists"));
      JPanel values = new ValueView(model);
      values.setBorder(BorderFactory.createTitledBorder("Values"));
      JPanel trees = new TreeView(model);
      trees.setBorder(BorderFactory.createTitledBorder("Trees"));
      
      Box right = Box.createVerticalBox();
      right.add(values);
      right.add(trees);
      
      JPanel panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
      panel.add(buttons);
      panel.add(list);
      panel.add(right);
      return panel;
   }

   /** layout the tab for the text-related components. */
   private JPanel textTab(DemoModel model)
   {  JPanel editors = new TextView(model);
      editors.setBorder(BorderFactory.createTitledBorder("Editors"));
      return editors;
   }

   /** layout the tab for the mouse and dialogs. */
   private JPanel miscTab(DemoModel model)
   {  JPanel dialogs = new DialogView(model);   
      dialogs.setBorder(BorderFactory.createTitledBorder("Dialogs"));
      JPanel mouse = new MouseView(model);
      mouse.setBorder(BorderFactory.createTitledBorder("Mouse Events"));
      
      JPanel panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
      panel.add(dialogs);
      panel.add(mouse);
      return panel;
   }
   
   /** layout the tab for the image example. */
   private JPanel imageTab(DemoModel model)
   {  JPanel images = new ImageView(model);
      images.setBorder(BorderFactory.createTitledBorder("Images"));
      return images;
   }
   
   /*
    private void registerControllers()
    {}
    */
	
}
