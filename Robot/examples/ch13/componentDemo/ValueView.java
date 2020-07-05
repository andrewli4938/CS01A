
import becker.util.IView;
import becker.gui.FormLayout;
import java.util.Calendar;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


/** A view that includes components that are instances of JSlider, JSpinner,
 * and JProgressBar. Each component logs events with the model.  The events 
 * are then displayed in another view.
 *
 * @author Byron Weber Becker */
public class ValueView extends JPanel implements IView
{
   private DemoModel model;

   private static final int PROGRESS_MAX = 100;
   private static final int SLIDER_MAX = 100;

   private JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, SLIDER_MAX,
         SLIDER_MAX / 2);
   private JSpinner dateSpinner = new JSpinner();
   private JSpinner listSpinner = new JSpinner();
   private JProgressBar bar = new JProgressBar(JProgressBar.HORIZONTAL, 0,
         PROGRESS_MAX);

   /** Construct the view.
    * @param aModel The model logging the events. */
   public ValueView(DemoModel aModel)
   {  super();
      this.model = aModel;
      this.layoutView();
      this.registerControllers();
      this.model.addView(this);
      this.updateView();
   }

   /** Update the view with information from the model.  When the view
    * does not actually display information from the model, updateView may
    * be empty.  */
   public void updateView()
   {  this.bar.setValue(Math.min(this.model.getItemCount(), PROGRESS_MAX));
   }	

   /** Lay out the components for this view. */
   private void layoutView()
   {  // Set up spinner for dates
      SpinnerDateModel dateModel = new SpinnerDateModel();
      dateModel.setCalendarField(Calendar.DAY_OF_WEEK);
      this.dateSpinner.setModel(dateModel);
	
      // Set up spinner for integers
      SpinnerListModel listModel = new SpinnerListModel(
            new String[] {
         "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
         "Saturday"});
      this.listSpinner.setModel(listModel);
      
      // listSpinner isn't wide enough by default
      this.listSpinner.setPreferredSize(this.dateSpinner.getPreferredSize());
   
      // show the % completed on the progress bar
      this.bar.setStringPainted(true);
		
      //this.setLayout(new SpringLayout());
      this.setLayout(new FormLayout());
      this.add(new JLabel("JSlider:", SwingConstants.RIGHT));
      this.add(this.slider);
      this.add(new JLabel("JProgressBar:", SwingConstants.RIGHT));
      this.add(this.bar);
      this.add(new JLabel("JSpinner:", SwingConstants.RIGHT));
      this.add(this.dateSpinner);
      this.add(new JLabel("JSpinner:", SwingConstants.RIGHT));
      this.add(this.listSpinner);
   }

   /** Register controllers for the components we use. */
   private void registerControllers()
   {  this.slider.addChangeListener(new SliderController());
      this.dateSpinner.addChangeListener(new SpinnerController());
      this.listSpinner.addChangeListener(new SpinnerController());
   }
	
   /** A controller for the spinners. */
   private class SpinnerController extends Object implements ChangeListener
   {
      public void stateChanged(ChangeEvent e) 
      {  JSpinner src = (JSpinner) e.getSource();
         ValueView.this.model.addHistory("ValueView", this, e.getSource(),
               "stateChanged", src.getValue().toString()); 
      }
   }
	

   /** A controller for the slider. */
   private class SliderController extends Object implements ChangeListener
   {
      public void stateChanged(ChangeEvent e) 
      {  JSlider slider = (JSlider) e.getSource();
         if (!slider.getValueIsAdjusting())
         {  ValueView.this.model.addHistory("ValueView", this, e.getSource(),
                  "stateChanged", "value = " + ValueView.this.slider.getValue()); 
         }
      }
   }
}
