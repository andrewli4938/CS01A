import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.File;
import java.util.Scanner;
import becker.gui.*;

   
   // NOTE:  This class is not expected to compile until appropriate changes
   // have been made as part of the Programming Projects.
   


public class GraphicalUI extends JFrame
{
   public GraphicalUI(LogExplorer aModel)
   {  super("LogExplorer");
      
      GraphicalView view = new GraphicalView(aModel);
      this.setContentPane(view);
      this.setSize(500, 500);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
   }
}


class GraphicalView extends JPanel implements LogExplorerView
{
   private LogExplorer model;
   private DefaultTableModel data = new DefaultTableModel(new Object[] {  "Client Hostname",
      "URL", "Size", "Code"
   }, 0);
   
   private JButton process = new JButton("Process Records");
   private JTextField serverLogName = new JTextField(20);
   private JTextField searchHost = new JTextField(20);
   private JLabel count = new JLabel("0");
   private JIntField minSize = new JIntField(10);
	
   public GraphicalView(LogExplorer aModel)
   {  super();
      this.model = aModel;
      this.layoutView();
      this.registerControllers();
      this.model.addView(this);
   }
	
   /** Display one record.
    * @param sr The record to display. */
   public void displayRecord(ServerRecord sr)
   {  this.data.addRow(new Object[] {  sr.getClientHostname(), sr.getURL(),
         sr.getSize(), sr.getCompletionCode()
      });
   }

   /** Display the number of records that met the criteria.
    * @param count The number of records that met the criteria. */
   public void displayCount(int count)
   {	this.count.setText("" + count);
   }
   
   private void layoutView()
   {  JPanel controls = new JPanel();
      controls.setLayout(new FormLayout());	
      controls.add(new JLabel("Server Log:"));
      controls.add(this.serverLogName);
      controls.add(new JLabel("Search Client Hostname for:"));
      controls.add(this.searchHost);
      controls.add(new JLabel("Minimum File Size:"));
      controls.add(this.minSize);
  		
      controls.add(this.process);
      
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      this.add(controls);
      
      JTable table = new JTable(this.data);
      JScrollPane scroller = new JScrollPane(table);
      this.add(scroller);
      
      JPanel count = new JPanel();
      count.add(new JLabel("Count:"));
      count.add(this.count);
      
      this.add(count);
   }
   
   private void registerControllers()
   {	this.process.addActionListener(new ProcessController());
   }
   
   private class ProcessController implements ActionListener
   {	public void actionPerformed(ActionEvent evt)
   	{	model.setSearchHost(searchHost.getText().trim());
   		model.setMinSize(minSize.getInt());
   		try 
		   {	Scanner s = new Scanner(new File(serverLogName.getText().trim()));
		   	model.processFile(s);  
		   	serverLogName.setBackground(Color.WHITE);
		   }
		   catch (Exception ex) 
		   {	serverLogName.setBackground(Color.RED);
		   }
   		
   	}
   }
}
