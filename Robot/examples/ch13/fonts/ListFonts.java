import java.awt.Font;
import java.awt.GraphicsEnvironment;


/** List the font names available on the current system.
 *
 * @author Byron Weber Becker */
public class ListFonts extends Object
{  public static void main(String[] args)
   {  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      Font[] names = ge.getAllFonts();
		
      for (Font f : names)
      {  System.out.println(f.getName());
      }
   }
}
