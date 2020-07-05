import becker.util.IView;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JCheckBox;


/** A view that includes a custom component, SketchPad, that listens
 * for mouse events.  The mouse events are shown in the sketchpad and also
 * logged with the model.  Two checkboxes allow motion and drag events (many of
 * which are generated) to be filtered out.
 *
 * @author Byron Weber Becker */
public class MouseView extends JPanel implements IView
{
   private DemoModel model;
   
   private SketchPad sketchpad;
   private JCheckBox showMoves = new JCheckBox("Show Motion Events");
   private JCheckBox showDrags = new JCheckBox("Show Drag Events");

   /** Construct the view.
    * @param aModel The model logging the events. */
   public MouseView(DemoModel aModel)
   {  super();
      this.model = aModel;
      
      this.sketchpad = new SketchPad(this.model);
      this.layoutView();
      this.registerControllers();
      //this.model.addView(this);
      //this.updateView();
   }

   /** Update the view with information from the model.  When the view
    * does not actually display information from the model, updateView may
    * be empty.  */
   public void updateView()
   {}	
   
   /** Lay out the components in the view. */
   private void layoutView()
   {  JPanel controls = new JPanel();
      controls.add(this.showMoves);
      controls.add(this.showDrags);
      
      this.setLayout(new BorderLayout());
      this.add(controls, BorderLayout.NORTH);
      this.add(this.sketchpad, BorderLayout.CENTER);
      
      this.showMoves.setSelected(this.sketchpad.isShowingMoves());
      this.showDrags.setSelected(this.sketchpad.isShowingDrags());
   }

   /** Register the controllers for the components. */
   private void registerControllers()
   {  this.showMoves.addActionListener(new MovesController());
      this.showDrags.addActionListener(new DragsController());
   }

   /** A controller to optionally suppress mouse motion events. */
   private class MovesController extends Object implements ActionListener
   {
      public void actionPerformed(ActionEvent evt)
      {  MouseView.this.sketchpad.setShowMouseMoves(
               MouseView.this.showMoves.isSelected());
         String msg = "";
         if (MouseView.this.showMoves.isSelected())
         {  msg = "mouse motion events enabled";
         } else
         {  msg = "mouse motion events suppressed";
         }
         MouseView.this.model.addHistory("MouseView", this, evt.getSource(),
               "actionPerformed", msg);
      }
   }


   /** A controller to optionally suppress mouse drag events. */
   private class DragsController extends Object implements ActionListener
   {
      public void actionPerformed(ActionEvent evt)
      {  MouseView.this.sketchpad.setShowMouseDrags(
               MouseView.this.showDrags.isSelected());
         String msg = "";
         if (MouseView.this.showDrags.isSelected())
         {  msg = "mouse drag events enabled";
         } else
         {  msg = "mouse drag events suppressed";
         }
         MouseView.this.model.addHistory("MouseView", this, evt.getSource(),
               "actionPerformed", msg);
      }
   }
}
