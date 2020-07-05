
import java.awt.*;
import javax.swing.*;


public class DemoGridBagLayout extends JPanel
{
   public DemoGridBagLayout()
   {  super();
      this.layoutView();
   }

   private void layoutView()
   {  this.setLayout(new GridBagLayout());
      GridBagConstraints gc = new GridBagConstraints();
      gc.fill = gc.BOTH;
      gc.gridwidth = 1;
      gc.gridheight = 3;
      gc.weightx = 0.5;
      gc.weighty = 0.5;
      this.add(new JButton("One"), gc);
      gc.fill = gc.NONE;
      gc.gridwidth = 1;
      gc.gridheight = 1;
      gc.gridx = 1;
      gc.gridy = 0;
      gc.anchor = gc.WEST;
      this.add(new JTextField("Two"), gc);
      gc.gridy++;
      gc.anchor = gc.CENTER;
      this.add(new JButton("Three"), gc);
      gc.gridy++;
      gc.anchor = gc.EAST;
      this.add(new JButton("Four"), gc);
   }

}
