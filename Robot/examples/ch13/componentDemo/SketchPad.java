import becker.util.IView;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;


/** Sketchpad instances listen for mouse events and a) log them to the model
 * and b) construct rectangles to show how they can be used. 
 *
 * JComponent is extended rather than JPanel because this component will not be
 * holding other components.
 *
 * There are two different listeners for mouse events. One, MouseListener, is
 * for the mouse entering or exiting the component and for clicks.  The second,
 * MouseMotionListener, is for mouse movements.  There can be A LOT of these
 * events, so don't use it unless you need it.
 *
 * @author Byron Weber Becker */
public class SketchPad extends JComponent implements IView
{
   private DemoModel model;
   
   // start and end points for the rectangle to draw
   private Point start = new Point();
   private Point end = new Point();
   // are we currently drawing?  If so, just draw an outline.  If not, fill
   // the rectangle.
   private boolean drawing = false;
   
   // is logging enabled or suppressed?
   private boolean showMouseMoved = false;
   private boolean showMouseDragged = false;

   /** Construct a new instance.
    * @param aModel the model where events are logged */
   public SketchPad(DemoModel aModel)
   {  super();
      this.model = aModel;
      this.layoutView();
      this.registerControllers();
      this.model.addView(this);
      this.updateView();
   }
   
   /** Are we showing mouse motion events? */
   public boolean isShowingMoves()
   {  return this.showMouseMoved;
   }
   
   /** Should we show mouse motion events?  Showing them all can be kind of 
    * overwhelming. */
   public void setShowMouseMoves(boolean show)
   {  this.showMouseMoved = show;
   }
   
   /** Are we showing mouse drag events? */
   public boolean isShowingDrags()
   {  return this.showMouseDragged;
   }
   
   /** Should we show mouse drag events?  Showing them all can be kind of 
    * overwhelming. */
   public void setShowMouseDrags(boolean show)
   {  this.showMouseDragged = show;
   }

   /** If what we drew depended on the model, we would call repaint() here. */
   public void updateView()
   {} 
   
   /** Paint the component. */
   public void paintComponent(Graphics g)
   {  // erase component
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, this.getWidth(), this.getHeight());
      
      // which point is in the upper left corner of the rectangle?
      int x1 = this.start.x;
      int x2 = this.end.x;
      if (x1 > x2)
      {  int temp = x1;
         x1 = x2;
         x2 = temp;
      }
      int y1 = this.start.y;
      int y2 = this.end.y;
      if (y1 > y2)
      {  int temp = y1;
         y1 = y2;
         y2 = temp;
      }
      
      g.setColor(this.getForeground());
      if (this.drawing)
      {  g.drawRect(x1, y1, x2 - x1, y2 - y1);
      } else 
      {  g.fillRect(x1, y1, x2 - x1, y2 - y1);
      }
   }

   /** Lay out the view.  These may or may not be needed, depending on the
    * layout manager for the container this is in. */
   private void layoutView()
   {  this.setMinimumSize(new Dimension(75, 75));
      this.setPreferredSize(new Dimension(100, 100));
   }

   /** REgister the mouse listeners. */
   private void registerControllers()
   {  this.addMouseListener(new MListener());
      this.addMouseMotionListener(new MMListener());
   }

   /** A controller for mouse events such as clicks and entering or exiting
    * the component. */
   private class MListener extends Object implements MouseListener
   {
      public void mouseClicked(MouseEvent e)
      {  model.addHistory("SketchPad", this, e.getSource(), "mouseClicked",
               "" + e.getPoint());
      }

      public void mousePressed(MouseEvent e)
      {  model.addHistory("SketchPad", this, e.getSource(), "mousePressed",
               "" + e.getPoint());
         SketchPad.this.start = e.getPoint();
         SketchPad.this.end = e.getPoint();
         SketchPad.this.drawing = true;
         SketchPad.this.repaint();
      }

      public void mouseReleased(MouseEvent e)
      {  model.addHistory("SketchPad", this, e.getSource(), "mouseReleased",
               "" + e.getPoint());
         SketchPad.this.drawing = false;
         SketchPad.this.repaint();
      }

      public void mouseEntered(MouseEvent e)
      {  model.addHistory("SketchPad", this, e.getSource(), "mouseEntered",
               "" + e.getPoint());
         SketchPad.this.setCursor(
               Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
      }

      public void mouseExited(MouseEvent e)
      {  model.addHistory("SketchPad", this, e.getSource(), "mouseExited",
               "" + e.getPoint());
      }
   }


   /** A controller for mouse motion events. */
   private class MMListener extends Object implements MouseMotionListener
   {
   
      public void mouseDragged(MouseEvent e)
      {  if (SketchPad.this.showMouseDragged)
         {  model.addHistory("SketchPad", this, e.getSource(), "mouseDragged",
                  "" + e.getPoint());
         }
         SketchPad.this.end = e.getPoint();
         SketchPad.this.repaint();
      }

      public void mouseMoved(MouseEvent e)
      {  if (SketchPad.this.showMouseMoved)
         {  model.addHistory("SketchPad", this, e.getSource(), "mouseMoved",
                  "" + e.getPoint());
         }
      }
   }
}
