import javax.swing.*;

/** A "cloud" that passes over a robot city.
* 
* @author Byron Weber Becker */
public class Banner extends JDialog implements IMove
{  private int x;
   private int y;
   private int deltaX;
   
   /** Display a message in a floating window. 
   * @param initX The initial x position of the banner.
   * @param initY The initial y position of the banner.
   * @param moveX The distance to move.
   * @param msg The msg to display. */
   public Banner(int initX, int initY, int moveX, String msg)
   {  super();
      this.deltaX = moveX;
      this.x = initX;
      this.y = initY;
      this.setSize(20, 60);
      this.setLocation(this.x, this.y);
      this.setAlwaysOnTop(true);
      this.setContentPane(new JLabel(msg));
      this.setVisible(true);
      
   }
   
   /** Move the banner. */
   public void move()
   {  this.x += this.deltaX;
   this.setLocation(this.x, this.y); 
   }
}
