
import java.awt.*;
import javax.swing.*;


public class DemoNullLayout extends JPanel
{
   public DemoNullLayout()
   {  super();
      this.layoutView();
   }

   private void layoutView()
   {  this.setLayout(null);
      JButton b1 = new JButton("Button1");
      JButton b2 = new JButton("Button2");
      b1.setBounds(10, 20, 150, 50);
      b2.setBounds(40, 60, 100, 30);
      this.add(b2);
      this.add(b1);
   }
   
}
