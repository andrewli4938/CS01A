import java.text.NumberFormat;


public class ConsultingLineItem extends LineItem 
{
   private double hours;
	
   /** Construct a consulting line item.
    * @param aDescr Description of the work.  It will be prepended with "Consulting re: "
    * @param numHours The number of hours worked.
    * @param costPerHr The cost per hour worked. */
   public ConsultingLineItem(String aDescr, double numHours, double costPerHr)
   {  super(1, "Consulting re: " + aDescr, costPerHr);
      this.hours = numHours;
   }
	
   public double calculateAmount()
   {  return super.calculateAmount() * this.hours;
   }
	
   public String getDescription()
   {  return super.getDescription() + " (" + this.hours + "hrs)";
   }

}
