
import javax.swing.*;

/** Create a stick figure and display it in a frame. 
 *
 * @author Byron Weber Becker */
public class DrawingMain extends Object
{
	public static void main(String[] args)
	{  // Declare the objects to show.
		JFrame rectFrame = new JFrame("drawRect");
		JPanel rectContents = new JPanel();
		RectComponent rect = new RectComponent();
		
		// Add the stick figure to the contents.
		rectContents.add(rect);
		
		// Display the contents in a frame.
		rectFrame.setContentPane(rectContents);
		rectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rectFrame.setLocation(250, 100);
		rectFrame.pack();
		rectFrame.setVisible(true);
		
		
		// Declare the objects to show.
		JFrame ovalFrame = new JFrame("drawOval");
		JPanel ovalContents = new JPanel();
		OvalComponent oval = new OvalComponent();
		
		// Add the stick figure to the contents.
		ovalContents.add(oval);
		
		// Display the contents in a frame.
		ovalFrame.setContentPane(ovalContents);
		ovalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ovalFrame.setLocation(500, 100);
		ovalFrame.pack();
		ovalFrame.setVisible(true);
		
		
		// Declare the objects to show.
		JFrame lineFrame = new JFrame("drawLine");
		JPanel lineContents = new JPanel();
		LineComponent line = new LineComponent();
		
		// Add the stick figure to the contents.
		lineContents.add(line);
		
		// Display the contents in a frame.
		lineFrame.setContentPane(lineContents);
		lineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lineFrame.setLocation(250, 400);
		lineFrame.pack();
		lineFrame.setVisible(true);
		
		
		// Declare the objects to show.
		JFrame stringFrame = new JFrame("drawString");
		JPanel stringContents = new JPanel();
		StringComponent string = new StringComponent();
		
		// Add the stick figure to the contents.
		stringContents.add(string);
		
		// Display the contents in a frame.
		stringFrame.setContentPane(stringContents);
		stringFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		stringFrame.setLocation(500, 400);
		stringFrame.pack();
		stringFrame.setVisible(true);
	}
}
