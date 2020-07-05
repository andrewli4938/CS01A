import java.util.Scanner;


/** Invoice the customer for 1 or more identical goods (computers, keyboards, etc.).
 *
 * @author Byron Weber Becker */
public class GoodsLineItem extends LineItem 
{
   /** Construct a goods line item.
    * @param aQuantity The number of things provided to the customer. 
    * @param aDescr Description of the things provided.
    * @param aUnitCost The cost of each individual thing provided. */
   public GoodsLineItem(int aQuantity, String aDescr, double aUnitCost)
   {  super(aQuantity, aDescr, aUnitCost);
   }
   
   /** Construct a goods line item with information read from a file. */
   public GoodsLineItem(Scanner in)
   {  super(in);
   }
   
	/** Calculate the total amount owing due to this line item. */
   public double calcAmount()
   {  return this.getQuantity() * this.getUnitCost();
   }
}
