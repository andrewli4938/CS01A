
import java.awt.Graphics2D;
import java.awt.Color;
import becker.util.Utilities;


/** A first try at the SimpleBot class.  These robots are always constructed
 * on street 4, avenue 2.  There is no way to tell which way they are
 * facing and they can only move east.
 *
 * @author Byron Weber Becker */
public class SimpleBot extends Paintable
{
   private int street = 4;
   private int avenue = 2;

   /** Construct a new Robot at (4, 2). */
   public SimpleBot()
   {  super();
   }

   /** Paint the robot at its current location. */
   public void paint(Graphics2D g)
   {  g.setColor(Color.BLACK);
      g.fillOval(this.avenue * 50, this.street * 50, 50, 50);
   }

   /** Move the robot 1 intersection east. */
   public void move()
   {  this.avenue = this.avenue + 1;
      Utilities.sleep(400);
   }
   
   /** Turn the robot 90 degrees to the left. */
   public void turnLeft()
   {	
   }
}
