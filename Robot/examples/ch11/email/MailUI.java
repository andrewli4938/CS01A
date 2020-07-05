import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import becker.util.*;
import java.util.*;


/** A primitive user interface for an email program.
 *
 * @author Jack Rehder; Byron Weber Becker */
public class MailUI extends JFrame implements IObserver
{
   private JButton[] buttons = new JButton[5];
   
   private JTextArea msg = new JTextArea(10, 30);
   
   // Inbox
   private MailboxModel inBox;
   private JTable inHeaders = new JTable();
   private EmailTableModel inTableModel;
   
   // Outbox
   private MailboxModel outBox;
   private JTable outHeaders = new JTable();
   private EmailTableModel outTableModel;
   
   // Currently selected mailbox.  These are aliases for one of in* or out*
   private MailboxModel currBox;
   private JTable currHeaders;
   private EmailTableModel currTableModel;
   
   private ReplyDialog replyDialog = null;
   private NewMsgDialog sendDialog = null;
   
   private JTextField commands = new JTextField();

   public MailUI(MailboxModel inbox, MailboxModel outbox)
   {  super();
      
      this.inBox = inbox;
      this.outBox = outbox;
      
      this.layoutView();
      this.registerListeners();
      
      this.inBox.addObserver(this);
      this.outBox.addObserver(this);
   }
   
   /** Set up a table to display headers from a mailbox. */
   private JScrollPane setupTableHeader(JTable t, EmailTableModel etm, String name)
   {  t.setModel(etm);
      t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      
      // Ask to be notified of selection changes.
      ListSelectionModel rowSM = t.getSelectionModel();
      rowSM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      rowSM.addListSelectionListener(new MsgSelectionListener());

      JScrollPane sp = new JScrollPane(t,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
      sp.setBorder(BorderFactory.createTitledBorder(name));
      return sp;
   }
   
   private void layoutView()
   {  this.setSize(900, 400);
		
      JPanel cmds = this.createCommandPanel();
      
      this.inTableModel = new EmailTableModel(this.inBox,
            EmailTableModel.MailBoxType.INBOX);
      this.outTableModel = new EmailTableModel(this.outBox,
            EmailTableModel.MailBoxType.OUTBOX);
      
      JScrollPane in = this.setupTableHeader(this.inHeaders, this.inTableModel,
            "In Box");
      JScrollPane out = this.setupTableHeader(this.outHeaders,
            this.outTableModel, "Out Box");
      Box headers = Box.createVerticalBox();
      headers.add(in);
      headers.add(out);

      JScrollPane msgScroller = new JScrollPane(this.msg,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  

      JSplitPane center = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, headers,
            msgScroller);
      
      JPanel contents = new JPanel();
      contents.setLayout(new BorderLayout());
      contents.add(cmds, BorderLayout.NORTH);
      contents.add(center, BorderLayout.CENTER);

      this.currBox = this.inBox;
      this.currHeaders = this.inHeaders;
      this.currTableModel = this.inTableModel;
      // this.currHeaders.getSelectionModel().set
            
      this.setContentPane(contents);
      this.setVisible(true);
      center.setDividerLocation(0.5);
   }

   /** Create a panel for the command buttons. */   
   private JPanel createCommandPanel()
   {  this.buttons[0] = new JButton("New");
      this.buttons[1] = new JButton("Reply");
      this.buttons[2] = new JButton("Delete");
      this.buttons[3] = new JButton("Save");
      this.buttons[4] = new JButton("Quit");
      
      JPanel grid = new JPanel();
      grid.setLayout(new GridLayout(1, this.buttons.length));
      for (JButton b : this.buttons)
      {  grid.add(b);
      }
      JPanel cmds = new JPanel();
      cmds.add(grid); // to prevent gridlayout from stretching vertically
      return cmds;
   }
   
   private void registerListeners()
   {  this.addWindowListener(new WindowCloseListener());
   
      ButtonListener bListener = new ButtonListener();
      for (JButton b : this.buttons)
      {  b.addActionListener(bListener);
      }
            
   }
   
   public void update(Object theObserved, Object changeInfo)
   {  if (changeInfo == null)
      {  // just added the view
         return;
      }
   
      ChangeInfo ci = (ChangeInfo) changeInfo;
      int row = ci.getRow();
      switch (ci.getType())
      {  case SELECT_ROW:
         MailboxModel mm = (MailboxModel) theObserved;
         if (mm == this.inBox)
         {  this.currBox = this.inBox;
            this.currHeaders = this.inHeaders;
            this.currTableModel = this.inTableModel;
         } else
         {  this.currBox = this.outBox;
            this.currHeaders = this.outHeaders;
            this.currTableModel = this.outTableModel;
         }
         this.msg.setText(mm.getMessage(row));
         break;
            
      case UNSELECT_ROW:
         ListSelectionModel lsm = (ListSelectionModel) theObserved;
         lsm.clearSelection();
         this.msg.setText("");
         break;
            
      case ADD:
         if (theObserved == this.inBox)
         {  this.inTableModel.fireTableRowsInserted(row, row);  
         } else
         {  this.outTableModel.fireTableRowsInserted(row, row);
         }
         break;
            
      case DELETE:
         this.currTableModel.fireTableRowsDeleted(row, row);
         this.msg.setText("");
         break;
            
      case REPLACE_ALL:
         this.currTableModel.fireTableStructureChanged();
         break;
      }  
   }
   
   private void endSession()
   {  this.inBox.save();
      this.outBox.save();
      System.exit(0);
   }

   /** Reply to the currently selected message. */
   private void replyToMessage()
   {  int row = this.currHeaders.getSelectedRow();
      if (row >= 0)
      {  if (this.replyDialog == null)
         {  this.replyDialog = new ReplyDialog(this);
         }	
         if (this.replyDialog.showDialog())
         {  this.currBox.replyToMessage(row, this.replyDialog.getReply(),
                  this.outBox);
         }
      }
   }
   
   private void sendMessage()
   {  if (this.sendDialog == null)
      {  this.sendDialog = new NewMsgDialog(this);
      }
      NewMsgDialog dlg = this.sendDialog;
      if (dlg.showDialog())
      {  this.outBox.sendMessage(dlg.getRecipient(), dlg.getSubject(),
               dlg.getBody());
      }
   }

   /** Save the currently selected message to a user-specified file. */
   private void saveMessage()
   {  JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
      int result = fc.showSaveDialog(this);
      if (result == fc.APPROVE_OPTION && this.currHeaders.getSelectedRow() >= 0)
      {  this.currBox.saveMessage(this.currHeaders.getSelectedRow(),
               fc.getSelectedFile().getPath());
      }
   }
   
   private void deleteMessage()
   {  int row = this.currHeaders.getSelectedRow();
      if (row >= 0)
      {  currBox.deleteMessage(row);
      }
   }
   
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {  String cmd = e.getActionCommand();
         if (cmd.equalsIgnoreCase("Quit"))
         {  endSession();
         } else if (cmd.startsWith("New"))
         {  sendMessage();
         } else if (cmd.startsWith("Delete"))
         {  deleteMessage();
         } else if (cmd.startsWith("Reply"))
         {  replyToMessage();
         } else if (cmd.startsWith("Save"))
         {  saveMessage();
         } else
         {  throw new Error("Unknown command: " + cmd);
         }
         
      }
   }
   

   private class MsgSelectionListener implements ListSelectionListener
   {
      public void valueChanged(ListSelectionEvent e)
      {  // Ignore extra messages.
         if (e.getValueIsAdjusting())
         {  return;
         }

         ListSelectionModel lsm = (ListSelectionModel) e.getSource();
         if (lsm.isSelectionEmpty())
         {// no rows are selected
         } else
         {  int selectedRow = lsm.getMinSelectionIndex();
            if (lsm == currHeaders.getSelectionModel())
            {  // selected a new message in the currently selected table
               update(currBox,
                     new ChangeInfo(ChangeType.SELECT_ROW, selectedRow));
            } else if (lsm == inHeaders.getSelectionModel())
            {  // previous selection was in outHeaders; new one in inHeaders
               update(outHeaders.getSelectionModel(),
                     new ChangeInfo(ChangeType.UNSELECT_ROW,
                     outHeaders.getSelectedRow()));
               update(inBox, new ChangeInfo(ChangeType.SELECT_ROW, selectedRow));
            } else if (lsm == outHeaders.getSelectionModel())
            {  // previous selection was in inHeaders;  new one is in outHeaders
               update(inHeaders.getSelectionModel(),
                     new ChangeInfo(ChangeType.UNSELECT_ROW,
                     inHeaders.getSelectedRow()));
               update(outBox, new ChangeInfo(ChangeType.SELECT_ROW, selectedRow));
            } else
            {  throw new Error("Unknown selection source.");
            }            
         }
      }
   }


   private class WindowCloseListener extends WindowAdapter
   {
      public void windowClosing(WindowEvent e)
      {  endSession();
      }
   }
   
}


/** This class is here to add the observer pattern.  We want this UI to
 * follow the patterns set out in the GUI chapter (just in case students
 * look at it!) but without needing them to understand observers (because
 * they WILL look at Mailbox).
 */
class MailboxModel extends Mailbox implements IObservable
{	
   private ArrayList<IObserver> observers = new ArrayList<IObserver>();
	
   public MailboxModel(String filename, String ownerInfo) throws FileNotFoundException
   {  super(filename, ownerInfo);
   }
	
   public void addMessage(Message m)
   {  super.addMessage(m);
      this.updateAllObservers(new ChangeInfo(ChangeType.ADD, this.getSize()));
   }

   public void deleteMessage(int rowIndex)
   {  super.deleteMessage(rowIndex);
      this.updateAllObservers(new ChangeInfo(ChangeType.DELETE, rowIndex));
   }
   
   // methods implementing IModel
   public void updateAllObservers(ChangeInfo ci)
   {  if (this.observers != null)
      {  // called during initialization before observers is allocated
         for (IObserver anObserver : this.observers)
         {  anObserver.update(this, ci);
         }
      }
   }
	
   public void removeObserver(IObserver o)
   {  this.observers.remove(o);	
   }
	
   public void addObserver(IObserver o)
   {  this.observers.add(o);
      o.update(this, null);
   }
   
   public void removeObservers()
   {  this.observers.clear();
   }
}


enum ChangeType
{
   ADD, DELETE, REPLACE_ALL, SELECT_ROW, UNSELECT_ROW
}


class ChangeInfo extends Object
{
   private ChangeType type;
   private int row;
   
   public ChangeInfo(ChangeType ct, int theRow) 
   {  super();
      this.type = ct;
      this.row = theRow;
   }
   
   public ChangeType getType()
   {  return this.type;
   }
   
   public int getRow()
   {  return this.row;
   }
}


class EmailTableModel extends AbstractTableModel
{
   public enum MailBoxType 
   {
      INBOX, OUTBOX
   }
   
   private Mailbox model;
   private MailBoxType type;
   
   private static final int NUM_COLUMNS = 3;
   // column names:  1st half are for INBOX, second half are for OUTBOX
   private static final String[] colNames = {
      "Sender", "Date", "Subject", "Recipient", "Date", "Subject"};
 
   public EmailTableModel(Mailbox mbox, MailBoxType type)
   {  super();
      this.model = mbox;
      this.type = type;
      assert this.model != null;
   }
	
   public int getColumnCount()
   {  return NUM_COLUMNS;
   }
	
   public int getRowCount()
   {  return this.model.getSize();
   }
	
   public Object getValueAt(int rowIndex, int colIndex)
   {  if (colIndex == 0 && this.type == MailBoxType.OUTBOX)
      {  colIndex += NUM_COLUMNS; // get recipient
      }
      return this.model.getHeaderInfo(rowIndex, colIndex);
   }
	
   public boolean isCellEditable(int rowIndex, int colIndex)
   {  return false;
   }
	
   public String getColumnName(int colIndex)
   {  if (this.type == MailBoxType.OUTBOX)
      {  colIndex += NUM_COLUMNS;
      }
   
      return this.colNames[colIndex];
   }
}


/** A dialog box to get the user's reply to an email message. */
class ReplyDialog extends JDialog
{
   private JTextArea input = new JTextArea(40, 100);
   private JButton okButton = new JButton("OK");
   private JButton cancelButton = new JButton("Cancel");
   private boolean ok = false;
	
   public ReplyDialog(JFrame parent)
   {  super(parent, "Reply to message:", true);
	
      Container contents = this.getContentPane();
      contents.add(this.input, BorderLayout.CENTER);
		
      JPanel south = new JPanel();
      south.add(this.okButton);
      south.add(this.cancelButton);
		
      ButtonListener bl = new ButtonListener();
      this.okButton.addActionListener(bl);
      this.cancelButton.addActionListener(bl);
		
      contents.add(south, BorderLayout.SOUTH);
      this.setSize(350, 400);
   }
	
   public boolean showDialog()
   {  this.ok = false; // set to true by okButton action
      this.input.setText("");
      super.setVisible(true); // blocks until hidden in action listener
      return this.ok;
   }
	
   public String getReply()
   {  return this.input.getText();
   }
	
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {  if (e.getSource() == okButton)
         {  ok = true;
         }
         setVisible(false);
      }
   }
}


/** A dialog box to get a new email message. */
class NewMsgDialog extends JDialog
{
   private JTextField recipient = new JTextField(40);
   private JTextField subject = new JTextField(40);
   private JTextArea body = new JTextArea(40, 100);
   private JButton okButton = new JButton("Send");
   private JButton cancelButton = new JButton("Cancel");
   private boolean ok = false;
   
   public NewMsgDialog(JFrame parent)
   {  super(parent, "Reply to message:", true);
   
      this.recipient.setText("");
      this.subject.setText("");
      this.body.setText("");
      
      JPanel north = new JPanel(new GridLayout(2, 1));
      north.add(this.recipient);
      north.add(this.subject);

      JPanel south = new JPanel();
      south.add(this.okButton);
      south.add(this.cancelButton);
      
      Container contents = this.getContentPane();
      contents.add(north, BorderLayout.NORTH);
      contents.add(this.body, BorderLayout.CENTER);
      contents.add(south, BorderLayout.SOUTH);
      
      ButtonListener bl = new ButtonListener();
      this.okButton.addActionListener(bl);
      this.cancelButton.addActionListener(bl);
      
      this.setSize(350, 400);
   }
   
   public boolean showDialog()
   {  this.ok = false; // set to true by okButton action
      super.setVisible(true); // blocks until hidden in action listener
      return this.ok;
   }
   
   public String getBody()
   {  return this.body.getText();
   }
   
   public String getRecipient()
   {  return this.recipient.getText();
   }
   
   public String getSubject()
   {  return this.subject.getText();
   }
   
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {  if (e.getSource() == okButton)
         {  ok = true;
         }
         setVisible(false);
      }
   }
}
