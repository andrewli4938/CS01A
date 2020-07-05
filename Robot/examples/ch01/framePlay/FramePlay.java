import javax.swing.*;      // use JFrame, JPanel, JButton, JTextArea

public class FramePlay
{
   public static void main(String[] args)
   {  // declare the objects to show
      JFrame frame = new JFrame();
      JPanel contents = new JPanel();
      JButton saveButton = new JButton("Save");
      JTextArea textDisplay = new JTextArea(5, 10);

      // set up the contents
      contents.add(saveButton);
      contents.add(textDisplay);

		// set the frame's contents to display the panel
      frame.setContentPane(contents);

      // set up and show the frame
      frame.setTitle("FramePlay");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocation(250, 100);
      frame.setSize(150, 200);
      frame.setVisible(true);
   }
}

