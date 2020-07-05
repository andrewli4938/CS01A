
import javax.swing.*;


public class LayoutFrame extends JFrame
{
   private static int xPos = 10;
   private static int yPos = 10;
   private static final int OFFSET = 50;

   public LayoutFrame(String title, JPanel contents)
   {  super(title);
      this.setContentPane(contents);
      this.setSize(300, 130);
      this.setLocation(xPos, yPos);
      xPos += OFFSET;
      yPos += OFFSET;
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
   }

}
