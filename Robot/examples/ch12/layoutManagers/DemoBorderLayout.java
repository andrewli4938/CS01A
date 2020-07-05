
import java.awt.*;
import javax.swing.*;


public class DemoBorderLayout extends JPanel
{  private JButton b1 = new JButton("North");
   private JTextField f1 = new JTextField("South");
   private JButton b2 = new JButton("East");
   private JButton b3 = new JButton("W");
   private JTextField f2 = new JTextField("Center");

   public DemoBorderLayout()
   {  super();
      this.layoutView();
   }

   private void layoutView()
   {  this.setLayout(new BorderLayout());
      this.add(this.b1, BorderLayout.NORTH);
      this.add(this.f1, BorderLayout.SOUTH);
      this.add(this.b2, BorderLayout.EAST);
      this.add(this.b3, BorderLayout.WEST);
      this.add(this.f2, BorderLayout.CENTER);
   }
   
}
