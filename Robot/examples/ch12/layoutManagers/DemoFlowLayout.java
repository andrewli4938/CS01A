
import java.awt.*;
import javax.swing.*;


public class DemoFlowLayout extends JPanel
{
   public DemoFlowLayout()
   {  super();
      this.layoutView();
   }

   private void layoutView()
   {  this.setLayout(new FlowLayout());
      this.add(new JButton("  One  "));
      this.add(new JTextField("  Two  ", 7));
      this.add(new JLabel("  Three  "));
      this.add(new JCheckBox("  Four  "));

   }
   
}
