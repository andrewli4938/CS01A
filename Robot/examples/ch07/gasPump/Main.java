import becker.xtras.gasPump.*;

	
/** Run a gas pump with a graphical user interface.
 *
 * @author Byron Weber Becker */
public class Main extends Object
{
   public static void main(String[] args) 
   {  // Create three meters for the pump.
   	Meter silver = new Meter(1.109, 87, "Silver");
      Meter gold = new Meter(1.149, 89, "Gold");
      Meter platinum = new Meter(1.199, 93, "Platinum");
		
		// Create the graphical user interface.	
      GasPumpGUI gui = new GasPumpGUI(silver, gold, platinum, "Liter");
   }	
}
