import javax.swing.*;

/** Create a stick figure and display it in a frame. */
public class Main extends Object
{
	public static void main(String[] args)
	{  // create the stick figure
		StickFigurePair sfp = new StickFigurePair();
		
		// set up a panel for the contents
		JPanel contents = new JPanel();
		contents.add(sfp);
		
		// display in a frame
		//JFrame frame = new JFrame("Offset Stick Figure");
		JFrame frame = new JFrame("Stick Figure Pair");
		frame.setContentPane(contents);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
