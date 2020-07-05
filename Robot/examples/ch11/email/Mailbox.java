import java.util.Scanner;
import java.io.*;
import becker.util.*;

/** A mailbox holds messages for an email program.
 *
 * @author Jack Rehder, Byron Weber Becker */
public class Mailbox extends Object
{
   public String currentMboxFile = "";
   public String owner; // who's mailbox is this?
   
   public int size = 0; // number of messages
   public Message[] msgs = new Message[5];

   public static final String[] months = {
      "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
      "Nov", "Dec"};

   public static final String[] days = {
      "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
      "Saturday" };

   public Mailbox(String filename, String ownerInfo) throws FileNotFoundException
   {  super();
      this.owner = ownerInfo;

      this.currentMboxFile = filename;
      Scanner in = new Scanner(new File(filename));

      while (in.hasNextLine())
      {  Message msg = new Message();    // start a new message
      	in.next();					// skip From: tag
         msg.sender = in.nextLine().trim();
         in.next(); 					// skip To: tag
         msg.recipient = in.nextLine().trim();
         in.next(); 					// skip Date: tag
         msg.setDate(in.nextInt(), in.nextInt(), in.nextInt());
         in.next(); 					// skip Subject: tag
         msg.setSubject(in.nextLine().trim());
            
         String body = "";
         while (true)
         {  String line = in.nextLine();
            if (line.equals("EOM"))
            {  break;
            }
            body = body + line + "\n";
         }
         msg.setBody(body);
         this.addMessage(msg);
      }
      in.close();
   }
   
   public void sendMessage(String recipient, String subject, String body)
   {  Message m = new Message();
      m.recipient = recipient;
      m.setSubject(subject);
      m.date = new Date();
      m.setBody(body);
      m.sender = this.owner;
      this.addMessage(m);
      PrintWriter out = this.openOutputFile("outbox.txt");
      out.println( "From: " + m.sender + "\nTo: " + m.recipient
            + "\nDate: " + m.getDate().numeric() + "\nSubject: "
            + m.getSubject() + "\n" + m.getBody());
      out.close();
   }

   public void deleteMessage(int n)
   {  for (int i = n; i < this.size - 1; i++)
      {  this.msgs[i] = this.msgs[i + 1];
      }
      this.size--;
   }

   public void saveMessage(int msgNum, String filename)
   {  PrintWriter out = this.openOutputFile(filename);
      out.println( "From: " + this.msgs[msgNum].sender + "\nTo: "
            + this.msgs[msgNum].recipient + "\nDate: "
            + this.msgs[msgNum].getDate().numeric() + "\nSubject: "
            + this.msgs[msgNum].getSubject() + "\n"
            + this.msgs[msgNum].getBody());
      out.close();
   }

   public void replyToMessage(int i, String body, 
   								Mailbox outBox)
   {  Message reply = new Message();

      reply.setDate(new Date());
      String sender = this.msgs[i].recipient;
      reply.sender = sender;
      String recipient = this.msgs[i].sender;
      reply.recipient = recipient;
      String subject = this.msgs[i].getSubject();
      reply.setSubject("Re: " + subject);
      Date d = this.msgs[i].getDate();
      String who = this.msgs[i].sender;
      String BEGIN = "**On " + d.numeric() + ", "
            + who.substring(0, who.indexOf('<')).trim() 
            + " wrote:\n";
      String END = "**end original message**\n\n";
      String origMsg = this.msgs[i].getBody();
      String replyBody = BEGIN + origMsg + END + body;
      reply.setBody(replyBody);
      PrintWriter out = this.openOutputFile("outbox.txt");
      out.println( "From: " + reply.sender + "\nTo: " + reply.recipient
            + "\nDate: " + reply.getDate().numeric() + "\nSubject: "
            + reply.getSubject() + "\n" + reply.getBody());
      out.close();
      outBox.addMessage(reply);
   }

   public void save()
   {  PrintWriter out = this.openOutputFile(this.currentMboxFile);
      for (int i = 0; i < this.size; i++)
      {  Message m = this.msgs[i];
         out.print("From: " + m.sender + "\nTo: " + m.recipient + "\nDate: "
               + m.getDate().numeric() + "\nSubject: " + m.getSubject() + "\n"
               + m.getBody() + "EOM\n");
      }
      out.close();
   }

   public int getSize()
   {  return this.size;
   }

   public String getMessage(int i)
   {  return "From: " + this.msgs[i].sender + "\nTo: " + this.msgs[i].recipient
            + "\nDate: " + this.msgs[i].getDate().numeric() + "\nSubject: "
            + this.msgs[i].getSubject() + "\n" + this.msgs[i].getBody();
   }

   public void addMessage(Message m)
   {  if (this.size == this.msgs.length)
      {  growArray();
      }
      this.msgs[this.size] = m;
      this.size++;
   }

   public void growArray()
   {  Message[] temp = new Message[this.msgs.length * 2];
      for (int i = 0; i < this.msgs.length; i++)
      {  temp[i] = this.msgs[i];
      }
      this.msgs = temp;
   }
     
   public String makeDateString(Date d)
   {  if (d.isEquivalent(new Date()))
      {  return "Today";
      }
      int month = d.getMonth();
      int year = d.getYear();
      int day = d.getDay();
      String dayOfWeek = this.dayOfWeek(year, month, day);
      return dayOfWeek + ", " + months[month - 1] + " " + day + " " + year;
   }
   
   public String dayOfWeek(int year, int month, int day) 
   {  int a = (int) Math.floor((14 - month) / 12);
      int y = year - a;
      int m = month + 12 * a - 2;
      int d = (day + y + (int) Math.floor(y / 4) - (int) Math.floor(y / 100)
            + (int) Math.floor(y / 400) + (int) Math.floor((31 * m) / 12))
            % 7;
             
      return this.days[d];
   }
   
   public PrintWriter openOutputFile(String filename)
   {  try 
      {  return new PrintWriter(filename);
      }
      catch (Exception ex) 
      {  ex.printStackTrace();
         System.exit(1);
      }
      return null;
   }
   
   /** Get info to display in the user interface. */
   public String getHeaderInfo(int msg, int col)
   {  switch (col)
      {  case 0:
         return this.msgs[msg].sender;
            
      case 1:
         return this.makeDateString(this.msgs[msg].date);
            
      case 2:
         return this.msgs[msg].getSubject();
            
      case 3:
         return this.msgs[msg].recipient;
            
      default:
         throw new IllegalArgumentException("undefined column (" + col + ").");
      }
   }
}
