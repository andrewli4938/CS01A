import java.text.NumberFormat;
import java.io.PrintWriter;
import java.util.Scanner;
import becker.util.Test;


public class LineItem extends Object implements Comparable
{
   private int quantity;
   private String description;
   private double unitCost;
   private static final NumberFormat money = NumberFormat.getCurrencyInstance();

   public LineItem(int aQuantity, String aDescr, double aUnitCost)
   {  super();
      this.quantity = aQuantity;
      this.description = aDescr;
      this.unitCost = aUnitCost;
   }
	
   public double calculateAmount()
   {  return this.quantity * this.unitCost;
   }
	
   public void print(PrintWriter out)
   {  out.printf("%3d %-50s%10s%10s%n",
               this.getQuantity(), 
               this.getDescription(), 
               this.money.format(this.unitCost),
               this.money.format(this.calculateAmount()));
   }
		
   public int getQuantity()
   {  return this.quantity;
   }
	
   public double getUnitCost()
   {  return this.unitCost;
   }
	
   public String getDescription()
   {  return this.description;
   }
   
   public int compareTo(Object o)
   {	LineItem item = (LineItem)o;
   	return this.description.compareTo(item.description);
   }
}
