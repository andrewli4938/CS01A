import becker.util.IModel;
import becker.util.IView;
import java.util.ArrayList;


/** Record a history of interactions between the user and the user interface.
 * 
 * @author Byron Weber Becker */
public class DemoModel extends Object implements IModel
{  
   // a list of the views observing this model.
   private ArrayList<IView> views = new ArrayList<IView>();

   // the history list of interactions.
   private ArrayList<HistoryItem> history = new ArrayList<HistoryItem>();

   /** Construct the model. */
   public DemoModel()
   {  super();
   }
	
   /** Add a new event to the history. 
    * @param viewName The view where the event originated.
    * @param listener The listener than was called with the event
    * @param source The source (component) that generated the event.
    * @param method The method within the listener that was called
    * @param moreInfo More information about what happened. */
   public void addHistory(String viewName, Object listener, Object source, String method, String moreInfo)
   {  this.history.add(
            new HistoryItem(viewName, listener.getClass().getName(), 
            source.getClass().getName(), method, moreInfo));
      this.updateAllViews();
   }
	
   /** Get the name of the view for event i in the history. */
   public String getView(int i)
   {  return this.history.get(i).viewName;
   }
	
   /** Get the name of the listener for event i in the history. */
   public String getListener(int i)
   {  return this.history.get(i).listener;
   }
	
   /** Get the source for event i in the history. */
   public String getSource(int i)
   {  return this.history.get(i).source;
   }

   /** Get the name of the listener method for event i in the history. */
   public String getMethod(int i)
   {  return this.history.get(i).method;
   }

   /** Get any other information recorded for event i in the history. */
   public String getOtherInfo(int i)
   {  return this.history.get(i).otherInfo;
   }
	
   /** Get the number of items in the history. */
   public int getItemCount()
   {  return this.history.size();
   }
	
   /** Add a new view to this model. */
   public void addView(IView view)
   {  this.views.add(view);
   }
	
   /** Remove a view from this model. */
   public void removeView(IView view)
   {  this.views.remove(view);
   }
	
   /** Inform all views that an update is required. */
   public void updateAllViews()
   {  for (IView view : this.views)
      {  view.updateView();
      }
   }
	
   /** Record information about one history item.  This is a very simple class 
    * used only internally to DemoModel so public instance variables are 
    * probably OK. There's no processing that can be done with them. */
   private class HistoryItem extends Object
   {
      public String viewName;
      public String listener;
      public String source;
      public String method;
      public String otherInfo;
		
      public HistoryItem(String view, String listener, String src, String meth, String info)
      {  super();
         this.viewName = view;
         this.listener = listener;
         this.source = src;
         this.method = meth;
         this.otherInfo = info;
      }
   }
}
