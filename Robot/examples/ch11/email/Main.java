import java.io.FileNotFoundException;

/** Run a prototype email program with an "in box" and a "sent mail box".
 *
 * @author Byron Weber Becker */
public class Main extends Object
{
   public static final String INBOX = "inbox.mail";
   public static final String OUTBOX = "sent.mail";

   public static void main(String[] args) throws FileNotFoundException
   {  MailboxModel inbox = new MailboxModel(INBOX,
            "Jack Rehder <jnrehder@hotmail.com>");
      MailboxModel outbox = new MailboxModel(OUTBOX,
            "Jack Rehder <jnrehder@hotmail.com>");
   
      MailUI ui = new MailUI(inbox, outbox);
   }
}
