import javax.swing.*;
import java.awt.Color;


public class Main
{

   public static void main(String[] args)
   {  ArtComponent myArt = new ArtComponent();
  		JPanel contents = new JPanel();
  		//contents.setBackground(Color.LIGHT_GRAY);
  		contents.add(myArt);

      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(contents);
      frame.pack();
      frame.setVisible(true);
   }
}
