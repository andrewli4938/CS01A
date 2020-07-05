import javax.swing.*;
import java.awt.*;

/** Create a stick figure and display it in a frame. */
public class Main extends Object
{
	public static void main(String[] args)
	{  // create the stick figure
		StickFigure sf = new StickFigure();
		
		// set up a panel for the contents
		JPanel contents = new JPanel();
		contents.add(sf);
		
		// display in a frame
		JFrame frame = new JFrame();
		frame.setContentPane(contents);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
