
import javax.swing.*;

public class GridLayoutMain
{
   public static void main(String[] args)
   {  JPanel p = new DemoGridLayout();
   
      JFrame f = new JFrame("GridLayout");
      f.setContentPane(p);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.pack();
      f.setVisible(true);
   }
}
