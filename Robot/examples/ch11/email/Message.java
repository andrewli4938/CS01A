

/** Store one message in the mail program.  
 *
 * @author Jack Rehder; Byron Weber Becker */
public class Message extends Object
{
   public String sender;
   public String recipient;
   public Date date;
   public String subject;
   public String body = "";

   public Message()
   {}

   public void setDate(int y, int m, int d)
   {  this.date = new Date(y, m, d);
   }
   
   public void setDate(Date d)
   {  this.date = d;
   }
   
   public Date getDate()
   {  return this.date;
   }

   public void setSubject(String aSubject)
   {  this.subject = aSubject;
   }

   public String getSubject()
   {  return this.subject;
   }

   public void setBody(String aBody)
   {  this.body = aBody;
   }

   public String getBody()
   {  return this.body;
   }
}
