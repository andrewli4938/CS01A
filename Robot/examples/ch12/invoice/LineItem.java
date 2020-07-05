import java.text.NumberFormat;
import java.io.PrintWriter;
import java.util.Scanner;

/** A line item is one kind of thing provided by the company for the customer.
 *
 *	@author Byron Weber Becker */
public abstract class LineItem extends Object implements Cloneable
{
   private int quantity;
   private String description;
   private double unitCost;
   private static final NumberFormat money = NumberFormat.getCurrencyInstance();

	/** Construct a new line item.
	 * @param aQuantity	The number of things provided to the customer.
	 *	@param aDescr		A description of the things.
	 *	@param unitCost	The cost of each of the things. */
   public LineItem(int aQuantity, String aDescr, double aUnitCost)
   {  super();
      this.quantity = aQuantity;
      this.description = aDescr;
      this.unitCost = aUnitCost;
   }
   
   /** Construct a line item, initializing instance variables with values
    *	read from a file. */
   public LineItem(Scanner in)
   {  super();
      this.quantity = in.nextInt();
      this.unitCost = in.nextDouble();
      this.description = in.nextLine();
   }
	
	/** Read a line item from a file.
	 * @return A subclass of LineItem as specified in the file. */
   public static LineItem read(Scanner in)
   {  String subclass = in.nextLine();
      if (subclass.equals("GoodsLineItem"))
      {  return new GoodsLineItem(in);
      } else if (subclass.equals("ServicesLineItem"))
      {  return new ServicesLineItem(in);
      } else if (subclass.equals("ConsultingLineItem"))
      {  return new ConsultingLineItem(in);
      } else
      {  throw new Error("Unknown subclass: " + subclass + ".");
      }
   }

	/** Calculate the total amount owing due to this line item. */
	public abstract double calcAmount();
	
	/** Print this line item. */
   public void print(PrintWriter out)
   {  out.printf("%3d %-50s%10s%10s%n",
               this.getQuantity(), 
               this.getDescription(), 
               this.money.format(this.unitCost),
               this.money.format(this.calcAmount()));
   }
		
	/** Get the number of things for this line item. */
   public int getQuantity()
   {  return this.quantity;
   }
	
	/** Get the cost of each thing represented by this line item. */
   public double getUnitCost()
   {  return this.unitCost;
   }
	
	/** Get the description of the things represented by this line item. */
   public String getDescription()
   {  return this.description;
   }
		
   /** Make a duplicate of this line item. */
   public Object clone()
   {  try 
      {  return super.clone();
      } catch (CloneNotSupportedException e) 
      {  throw new Error("Should never happen.");
      }
   }
   
}
