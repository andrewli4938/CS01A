import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/** A component that displays a pile of tokens and allows the user to select a number of 
*  them.  It informs registered listeners when tokens have been selected.  Allows the
*  client to change the number of tokens in the pile.
*
*  @author Byron Weber Becker */
public class PileComponent2 extends JComponent 
{  // store the controllers to inform when a selection takes place
   private ArrayList<ActionListener> actionListeners = 
                  new ArrayList<ActionListener>();

   // information for painting the component
   private int numTokens = 0;
   private int maxTokens;
   
   private Rectangle selection = null; // selected area
   private int numSelected = 0;        // # tokens in selected area
   
   /** Create a new component.
   *  @param max The maximum number of tokens that can be displayed. */
   public PileComponent2(int max)
   {  super();
      this.maxTokens = max;
      this.setMinimumSize(new Dimension(40, 60));
      this.setPreferredSize(new Dimension(60, 90));
      
      // add the mouse controllers
      this.addMouseListener(new MListener());
      this.addMouseMotionListener(new MMListener());
   }
   
   /** Add an action listener to our list of listeners. */
   public void addActionListener(ActionListener listener)
   {  this.actionListeners.add(listener);
   }
   
   /** Set the size of the pile.
   *  @param num The new pile size.  0 <= num <= maxTokens */
   public void setPileSize(int num)
   {  if (num < 0 || num > this.maxTokens)
      {  throw new IllegalArgumentException("too many/few tokens");
      }
      this.numTokens = num;
      this.selection = null;
      this.numSelected = 0;
      this.repaint();
   }
   
   /** Paint the component. */
   public void paintComponent(Graphics g)
   {  // values to use in painting.
      int width = this.getWidth();
      int height = this.getHeight();
      int tokenDia = Math.min(width, height/this.maxTokens);
      int tokenLeft = width/2 - tokenDia;
      
      // Erase the existing images
      g.setColor(this.getBackground());
      g.fillRect(0, 0, width, height);
      
      // draw the selection rectangle, if there is one
      g.setColor(Color.BLACK);
      if (this.selection != null)
      {  Rectangle sel = this.selection;
         g.drawRect(sel.x, sel.y, sel.width, sel.height);
      }
      
      // Draw the tokens.  Detect which ones are selected.  Count them
      // and color them differently.
      this.numSelected = 0;
      for (int i = 0; i < this.numTokens; i++)
      {  int top = height - (i + 1) * tokenDia;
         if (this.selection != null && 
               this.selection.contains(tokenLeft+tokenDia/2,
                                     top + tokenDia / 2))
         {  this.numSelected++;
            g.setColor(Color.YELLOW);
         } else
         {  g.setColor(Color.BLACK);
         }
      
         g.fillOval(tokenLeft, top, tokenDia, tokenDia);
      }
   }
   
   /** Get the number of tokens currently selected.
   *  @returns the number of tokens currently selected */
   public int getNumSelected()
   {  return this.numSelected;
   }
   
   /** A helper method to inform all listeners that a selection has been made. */
   private void handleEvent()
   {  ActionEvent evt = new ActionEvent(
                     this, ActionEvent.ACTION_PERFORMED, "");
      for (ActionListener al : this.actionListeners)
      {  al.actionPerformed(evt);
      }
   }
      
   /** Adjust the selection's size.  Account for a user clicking dragging up or left as well 
   *  as down and right.  Rectangles are always defined with (x,y) in the upper left. */
   private void adjustSelectionSize(Point mPos)
   {  this.selection.setSize(mPos.x - this.selection.x,
                              mPos.y - this.selection.y);
      this.repaint(); 
   }
   
   /**   Listen for mouse events within the pile. */
   private class MListener extends Object implements MouseListener
   {
      /** A mousePressed event signals the beginning of a selection. */
      public void mousePressed(MouseEvent e)
      {  PileComponent2.this.selection = 
                        new Rectangle(e.getPoint());
      }

      /** a mouseReleased event signals the end of a selection.  Finish up the
      *  selection and inform the listeners. */
      public void mouseReleased(MouseEvent e)
      {  PileComponent2.this.adjustSelectionSize(
                                 e.getPoint());
         PileComponent2.this.handleEvent();
      }

      public void mouseClicked(MouseEvent e)    {}
      public void mouseEntered(MouseEvent e)    {}
      public void mouseExited(MouseEvent e)     {}
   }
   
   /**   Listen for mouse events within the pile. */
   private class MMListener extends Object 
               implements MouseMotionListener
   {
      /** The bounds of the selection's rectangle changed.  Adjust it. */
      public void mouseDragged(MouseEvent e)
      {  PileComponent2.this.adjustSelectionSize(
                                 e.getPoint());
      }

      public void mouseMoved(MouseEvent e)      {}
   }
}
