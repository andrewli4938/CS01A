import java.util.Scanner;
import becker.util.Test;

/** Invoice the customer for 1 or more identical service contracts.
 *
 * @author Byron Weber Becker */
public class ServicesLineItem extends LineItem 
{
   private int numMonths;
	
	/** Construct a new line item for services provided.
	 * @param aQuantity	The number of service contracts provided to the customer.
	 *	@param aDescr		A description of the services provided.
	 *	@param aMthlyCost	The monthly cost of each service contract. 
	 * @param aNumMonths	The number of months the service contract lasts. */
   public ServicesLineItem(int aQuantity, String aDescr, double aMthlyCost, int aNumMonths)
   {  super(aQuantity, aDescr, aMthlyCost);
      this.numMonths = aNumMonths;
   }

   /** Construct a service line item with information read from a file. */
   public ServicesLineItem(Scanner in)
   {  super(in);
      this.numMonths = in.nextInt();
      in.nextLine();
   }
   
	/** Calculate the total amount owing due to this line item. */
   public double calcAmount()
   {  return this.getQuantity() * this.getUnitCost() * this.numMonths;
   }
	
	/** Get the description of the services represented by this line item. */
   public String getDescription()
   {  return super.getDescription() + " (" + this.numMonths + " months)";
   }
   
   
   public static void main(String[] args)
   {  ServicesLineItem li = new ServicesLineItem(10, "a line item", 5.95, 12);
      ServicesLineItem c = (ServicesLineItem) li.clone();
      Test.ckEquals("quant", li.getQuantity(), c.getQuantity());
      Test.ckEquals("descr", li.getDescription(), c.getDescription());
      Test.ckEquals("uCost", li.getUnitCost(), c.getUnitCost());
      Test.ckEquals("numMonths", li.numMonths, c.numMonths);
      Test.ckEquals("class", li.getClass().getName(), c.getClass().getName());
   }
}
