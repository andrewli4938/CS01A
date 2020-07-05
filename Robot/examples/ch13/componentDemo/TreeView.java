import becker.util.IView;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;


/** A view using an instance of JTree.
 * It logs events with the model.  The events are then displayed 
 * in another view.
 *
 * @author Byron Weber Becker */
public class TreeView extends JPanel implements IView
{
   private DemoModel model;

   private JTree tree;

   /** Construct the view.
    * @param aModel The model logging the events. */
   public TreeView(DemoModel aModel)
   {  super();
      this.model = aModel;
		
      this.tree = new JTree(this.buildTopNode());
		
      this.layoutView();
      this.registerControllers();
      this.model.addView(this);
      this.updateView();
   }

   /** Update the view with information from the model.  When the view
    * does not actually display information from the model, updateView may
    * be empty.  */
   public void updateView()
   {}	

   /** Lay out the components in this view.  Put the tree in a scroll pane
    * because it can get large when it's completely expanded. */
   private void layoutView()
   {  this.setLayout(new BorderLayout());
      JScrollPane scroller = new JScrollPane(this.tree);
      this.add(scroller, BorderLayout.CENTER);
   }

   /** Register controllers with the tree. */
   private void registerControllers()
   {  this.tree.addTreeSelectionListener(new TreeSelectController());
      this.tree.addTreeExpansionListener(new TreeExpandController());
   }
	
   /** Build the top level of the tree. */
   private DefaultMutableTreeNode buildTopNode()
   {  DefaultMutableTreeNode node = new DefaultMutableTreeNode(
            "Swing Components");
      node.add(this.buildContainers());
      node.add(this.buildControls());
      node.add(this.buildUneditableInfo());
      node.add(this.buildFormatedInfo());
      
      return node;
   }
   
   /** Build the containers branch. */
   private DefaultMutableTreeNode buildContainers()
   {  DefaultMutableTreeNode node = new DefaultMutableTreeNode("Containers");
      node.add(this.buildTopLevelContainers());
      node.add(this.buildGeneralContainers());
      
      return node;
   }
   
   /** Build the top-level containers branch. */
   private DefaultMutableTreeNode buildTopLevelContainers()
   {  DefaultMutableTreeNode node = new DefaultMutableTreeNode(
            "Top-Level Containers");
      node.add(new DefaultMutableTreeNode("JApplet"));
      node.add(new DefaultMutableTreeNode("JDialog"));
      node.add(new DefaultMutableTreeNode("JFrame"));
      
      return node;
   }
   
   /** Build the general containers branch. */
   private DefaultMutableTreeNode buildGeneralContainers()
   {  DefaultMutableTreeNode node = new DefaultMutableTreeNode(
            "General Purpose Containers");
      node.add(new DefaultMutableTreeNode("JPanel"));
      node.add(new DefaultMutableTreeNode("JScrollPane"));
      node.add(new DefaultMutableTreeNode("JSplitPane"));
      node.add(new DefaultMutableTreeNode("JTabbedPane"));
      node.add(new DefaultMutableTreeNode("JToolBar"));
      
      return node;
   }
   
   /** Build the controls branch. */
   private DefaultMutableTreeNode buildControls()
   {  DefaultMutableTreeNode node = new DefaultMutableTreeNode("Controls");
      node.add(new DefaultMutableTreeNode("JButton"));
      node.add(new DefaultMutableTreeNode("JCheckBox"));
      node.add(new DefaultMutableTreeNode("JRadioButton"));
      node.add(new DefaultMutableTreeNode("JComboBox"));
      node.add(new DefaultMutableTreeNode("JList"));
      node.add(new DefaultMutableTreeNode("JMenu"));
      node.add(new DefaultMutableTreeNode("JSlider"));
      node.add(new DefaultMutableTreeNode("JSpinner"));
      node.add(new DefaultMutableTreeNode("JTextField"));
      node.add(new DefaultMutableTreeNode("JTextArea"));
      node.add(new DefaultMutableTreeNode("JPasswordField"));
      node.add(new DefaultMutableTreeNode("JFormattedTextField"));
      
      return node;
   }
   
   /** Build the uneditable info displays branch. */
   private DefaultMutableTreeNode buildUneditableInfo()
   {  DefaultMutableTreeNode node = new DefaultMutableTreeNode(
            "Uneditable Information Display");
      node.add(new DefaultMutableTreeNode("JLabel"));
      node.add(new DefaultMutableTreeNode("JProgressBar"));
      node.add(new DefaultMutableTreeNode("JToolTip"));
      
      return node;
   }
   
   /** Build the highly formated info branch. */
   private DefaultMutableTreeNode buildFormatedInfo()
   {  DefaultMutableTreeNode node = new DefaultMutableTreeNode(
            "Highly Formatted Information Display");
      node.add(new DefaultMutableTreeNode("JColorChooser"));
      node.add(new DefaultMutableTreeNode("JFileChooser"));
      node.add(new DefaultMutableTreeNode("JTable"));
      node.add(new DefaultMutableTreeNode("JTree"));
      node.add(new DefaultMutableTreeNode("JTextPane"));
      node.add(new DefaultMutableTreeNode("JEditorPane"));
      
      return node;
   }
   
   /** A controller for selecting items in the tree. */
   private class TreeSelectController extends Object implements TreeSelectionListener
   {
   
      public void valueChanged(TreeSelectionEvent e)
      {  DefaultMutableTreeNode node = (DefaultMutableTreeNode)
               TreeView.this.tree.getLastSelectedPathComponent();

         if (node == null)
         {  return;
         }

         String info = (String) node.getUserObject();
         if (node.isLeaf())
         {  info += " (no children)";
         } else
         {  info += " (has children)";
         }
    
         TreeView.this.model.addHistory("TreeView", this, e.getSource(),
               "valueChanged", info);
      }
   }
   

   /** What to do when the tree expands or collapses. */
   private class TreeExpandController extends Object implements TreeExpansionListener
   {
      public void treeExpanded(TreeExpansionEvent evt)
      {  DefaultMutableTreeNode node = (DefaultMutableTreeNode) evt.getPath().getLastPathComponent();
         String comp = (String) node.getUserObject();
         TreeView.this.model.addHistory("TreeView", this, evt.getSource(),
               "treeExpanded", comp);
      }
      
      public void treeCollapsed(TreeExpansionEvent evt)
      {  DefaultMutableTreeNode node = (DefaultMutableTreeNode) evt.getPath().getLastPathComponent();
         String comp = (String) node.getUserObject();
         TreeView.this.model.addHistory("TreeView", this, evt.getSource(),
               "treeCollapsed", comp);
      }
   }
}
