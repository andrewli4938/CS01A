import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Color;

/** A component that displays a pile of tokens.  Allow the client to change the 
 *  number of tokens in the pile.
 *
 * @author Byron Weber Becker */
public class PileComponent extends JComponent 
{
   private int numTokens = 0;
   private int maxTokens;
   
   /** Create a new component.
    * @param max The maximum number of tokens that can be displayed. */
   public PileComponent(int max)
   {  super();
      this.maxTokens = max;
      this.setMinimumSize(new Dimension(40, 60));
      this.setPreferredSize(new Dimension(60, 90));
   }
   
   /** Reset the size of the pile.
    * @param num The new pile size.  0 <= num <= maxTokens */
   public void setPileSize(int num)
   {  if (num < 0 || num > this.maxTokens)
      {  throw new IllegalArgumentException("too many/few tokens");
      }
      this.numTokens = num;
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
      
      // Draw the tokens.
      g.setColor(Color.BLACK);
      for (int i = 0; i < this.numTokens; i++)
      {  int top = height - (i + 1) * tokenDia;
         g.fillOval(tokenLeft, top, tokenDia, tokenDia);
      }
   }
}