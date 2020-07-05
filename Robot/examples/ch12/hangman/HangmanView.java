/* REMINDER:  This sample solution is provided with the requirement that you
 * will NOT reproduce or distribute it.
 *
 * Copyright 2006 by Byron Weber Becker 
 */

import becker.xtras.hangman.*;
import javax.swing.*;
import java.awt.*;


/** Layout the view for the game of hangman.
 *
 * @author Byron Weber Becker */
public class HangmanView extends JPanel
{
   public HangmanView()
   {  super();
	
      this.layoutView();
   }
	
   /** Layout the view in a JPanel managed by BorderLayout. */
   private void layoutView()
   {  JPanel hangman = this;     // use same name as Figure 12-22
      hangman.setLayout(new BorderLayout());
	   
      // South
      JLabel phrase = new JLabel("GO FLY A KITE");
      hangman.add(phrase, BorderLayout.SOUTH);
		
      // Center
      JComponent gallows = new GallowsView(new SampleHangman());
      hangman.add(gallows, BorderLayout.CENTER);
				
      // East -- controls
      JPanel buttons = this.buttonsPanel();
      hangman.add(buttons, BorderLayout.EAST);
   }
	
   /** Layout and return a subpanel with all the buttons. */
   private JPanel buttonsPanel()
   {  JPanel letters = new JPanel();
      letters.setLayout(new GridLayout(13, 2));
      for (char ch='A'; ch<='Z'; ch++)
      {  letters.add(new JButton("" + ch));
      }
      
      return letters;
   }
	
   public static void main(String[] args)
   {  HangmanView view = new HangmanView();
   	
      JFrame f = new JFrame("Hangman Layout");
      f.setContentPane(view);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.pack();
      f.setVisible(true);
   }
}
