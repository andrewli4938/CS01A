
import java.awt.*;
import javax.swing.*;


public class DemoBoxLayout extends JPanel
{
   public DemoBoxLayout()
   {  super();
      this.layoutView();
   }

   private void layoutView()
   {  this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      this.add(new JButton("First"));
      this.add(new JTextField("Second"));
      this.add(new JButton("Third"));
   }
   
}
