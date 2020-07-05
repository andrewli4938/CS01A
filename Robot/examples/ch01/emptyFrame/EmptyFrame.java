import javax.swing.*;      // use JFrame

public class EmptyFrame
{
   public static void main(String[] args)
   {  // declare the object
      JFrame frame = new JFrame();
      
      // invoke its services
      frame.setTitle("EmptyFrame");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocation(250, 100);
      frame.setSize(150, 200);
      frame.setVisible(true);
   }
}

