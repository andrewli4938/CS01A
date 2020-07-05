
import javax.swing.*;
import becker.util.Utilities;

/** Test a thermometer component.
 *
 * @author Byron Weber Becker */
public class Main extends Object
{

   public static void main(String[] args)
   {  // Create three thermometer components.
      Thermometer t0 = new Thermometer();
      Thermometer t1 = new Thermometer();
      Thermometer t2 = new Thermometer();

      // Create a panel to hold the thermometers.
      JPanel contents = new JPanel();
      contents.add(t0);
      contents.add(t1);
      contents.add(t2);

      // Set up the frame.
      JFrame f = new JFrame();
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setContentPane(contents);
      f.pack();
      f.setVisible(true);

      // Set the temperature of each thermometer.
      t0.setTemperature(0);
      t1.setTemperature(30);
      t2.setTemperature(50);

      // Animate the first thermometer
      // Discussed in section 6.7.3
      for(int i=t0.MIN_TEMP; i<=t0.MAX_TEMP; i++)
      {  t0.setTemperature(i);
         Utilities.sleep(50);
      }
      
   }
}
