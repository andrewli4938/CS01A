import becker.util.*;
import becker.xtras.gasPump.IMeter;
   
/** Measure the volume of fuel sold and calculate the amount owed by the
* customer, given the current fuel cost.
*
* @author Byron Weber Becker */
public class Meter extends Object implements IMeter
{
   private double unitCost;            // unit cost
   private double volumeSold = 0.0; // volume sold
   private int octane;              // octane rating
   private String label;               // marketing label
   private ViewList views = new ViewList();   
   
   /** Construct a new Meter object.
   *  @param unitCost The cost for one unit (liter or gallon) of gas 
   *  @param octaneRating An integer related to the "performance" of the fuel;  usually 
   *           between 87 and 93.
   *  @param theLabel A label for the fuel such as "Gold" or "Ultra". */
   public Meter(double unitCost, int octaneRating, 
                     String theLabel)
   {  super();
      this.unitCost = unitCost;
      this.octane = octaneRating;
      this.label = theLabel;
   }  
      
   /** Get the cost per unit of fuel.
   *  @return cost per unit of fuel */
   public double getUnitCost()
   {  return this.unitCost;
   }
   
   /** Get the octane rating of the fuel.
   *  @return octane rating (typically between 87 and 93) */
   public int getOctane()
   {  return this.octane;
   }
   
   /** Get the label for this meter's fuel.  For example, "Gold" or "Ultra".
   *  @return this meter's fuel label */
   public String getLabel()
   {  return this.label;
   }
   
   /** Pump some fuel into a tank.  This method is called
   *  repeatedly while the "handle" on the pump is pressed.
   *  @param howMuch How much fuel was pumped since the last time this 
   *           method was called. */
   public void pump(double howMuch)
   {  this.volumeSold = this.volumeSold + howMuch;
      this.views.updateAllViews();  
   }
   
   /** Get the volume of fuel sold to this customer.
   *  @return volume of fuel sold */
   public double getVolumeSold()
   {  return this.volumeSold;
   }  
   
   /** Calculate the total cost of fuel sold to this customer.
   *  @return price/unit * number of units sold */
   public double calcTotalCost()
   {  double tCost = this.unitCost * this.volumeSold;
      return tCost;
   }
      
   /** Reset the meter for a new customer. */
   public void reset()
   {  this.volumeSold = 0;
      this.views.updateAllViews();  
   }
   
   public void addView(IView aView)
   {  this.views.addView(aView);
   }
      
   // Test the class.
   public static void main(String[] args)
   {  Test tester = new Test();
      Meter m1 = new Meter(1.109, 87, "Regular");
      tester.ckEquals("unit cost", 1.109, m1.getUnitCost());
      tester.ckEquals("octane", 87, m1.getOctane());
      tester.ckEquals("label", "Regular", m1.getLabel());

      Meter m2 = new Meter(1.149, 89, "Ultra");
      tester.ckEquals("unit cost", 1.149, m2.getUnitCost());
      tester.ckEquals("octane", 89, m2.getOctane());
      tester.ckEquals("label", "Ultra", m2.getLabel());
      
      tester.ckEquals("volSold", 0.0, m2.getVolumeSold());
      m2.pump(0.02);
      m2.pump(0.03);
      m2.pump(0.01);
      tester.ckEquals("volSold", 0.06, m2.getVolumeSold());
      tester.ckEquals("totCost", 0.06*1.149, m2.calcTotalCost());
      m2.reset();
      tester.ckEquals("after reset", 0.0, m2.getVolumeSold());
      tester.ckEquals("after reset", 0.0, m2.calcTotalCost());
   }
}
