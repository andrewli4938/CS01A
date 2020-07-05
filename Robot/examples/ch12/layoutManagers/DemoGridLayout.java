
import java.awt.*;
import javax.swing.*;

public class DemoGridLayout extends JPanel
{
   private JButton one = new JButton("One");
   private JButton two = new JButton("Two");
   private JButton three = new JButton("Three");
   private JButton four = new JButton("Four");
   private JButton five = new JButton("Five");
   private JButton six = new JButton("Six");
   
   public DemoGridLayout()
   {  super();
      this.layoutView();
   }

   private void layoutView()
   {  // Set the layout strategy to a grid with 2 rows and 3 columns.
      GridLayout strategy = new GridLayout(2, 3);
      this.setLayout(strategy);
      
      // Add the components.
      this.add(this.one);
      this.add(this.two);
      this.add(this.three);
      this.add(this.four);
      this.add(this.five);
      this.add(this.six);
   }
}
