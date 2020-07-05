import java.text.NumberFormat;
import java.util.Scanner;

/** Invoice the customer consulting provided.
 *
 * @author Byron Weber Becker */
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
   
   /** Construct a consulting line item with information read from a file. */
   public ConsultingLineItem(Scanner in)
   {  super(in);
      this.hours = in.nextDouble();
      in.nextLine();
   }
	
	/** Calculate the total amount owing due to this line item. */
   public double calcAmount()
   {  return this.getQuantity() * this.getUnitCost() * this.hours;
   }
	
	/** Get the description of the consulting work represented by this line item. */
   public String getDescription()
   {  return super.getDescription() + " (" + this.hours + "hrs)";
   }

}
