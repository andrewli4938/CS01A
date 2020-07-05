import javax.swing.*;

/** Create a stick figure and display it in a frame. 
 *
 * @author Byron Weber Becker */
public class Main extends Object
{
	public static void main(String[] args)
	{  // Declare the objects to show.
		JFrame frame = new JFrame();
		JPanel contents = new JPanel();
		StickFigure stickFig = new StickFigure();

		// Add the stick figure to the contents.
		contents.add(stickFig);

		
		// Display the contents in a frame.
		frame.setContentPane(contents);
		frame.setTitle("Stick Figure");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(250, 100);
		frame.pack();
		frame.setVisible(true);
	}
}
