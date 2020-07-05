
import javax.swing.*;

public class DemoBox extends JPanel
{
   public DemoBox()
   {  super();
      this.layoutView();
   }

   private void layoutView()
   {  this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      Box b = Box.createVerticalBox();
      b.add(new JButton("One"));
      b.add(Box.createVerticalGlue());
      b.add(new JButton("Two"));
      b.add(Box.createVerticalStrut(10));
      b.add(new JButton("Three"));
      
      this.add(b);
   }
   
}
