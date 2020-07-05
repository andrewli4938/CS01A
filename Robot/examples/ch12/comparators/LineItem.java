import java.text.NumberFormat;
import java.io.PrintWriter;
import becker.util.Test;


public class LineItem extends Object implements Cloneable
{
   private int quantity;
   private String descr;
   private double uCost;
   private static final String BLANKS = "                                                                              ";
   private static final NumberFormat money = NumberFormat.getCurrencyInstance();

   public LineItem(int aQuantity, String aDescr, double aUnitCost)
   {  super();
      this.quantity = aQuantity;
      this.descr = aDescr;
      this.uCost = aUnitCost;
   }
	
   public double calculateAmount()
   {  return this.quantity * this.uCost;
   }
	
   public void print(PrintWriter out)
   {  out.print(this.padLeft("" + this.getQuantity(), 3) + " ");
      out.print(this.padRight(this.getDescription(), 50) + " ");
      out.print(this.padLeft(this.money.format(this.uCost), 10));
      out.print(this.padLeft(this.money.format(this.calculateAmount()), 10));
      out.println();
   }
		
   public int getQuantity()
   {  return this.quantity;
   }
	
   public double getUnitCost()
   {  return this.uCost;
   }
	
   public String getDescription()
   {  return this.descr;
   }
	
   /** Pad the string s with blanks on the right so the string is the given width. */
   private String padRight(String s, int width)
   {  int add = Math.max(Math.min(width - s.length(), BLANKS.length()), 0);
		
      return s + BLANKS.substring(0, add);	
   }
	
   /** Pad the string s with blanks on the left so the string is the given width. */
   private String padLeft(String s, int width)
   {  int add = Math.max(Math.min(width - s.length(), BLANKS.length()), 0);
		
      return BLANKS.substring(0, add) + s;	
   }
	
   /** Make a duplicate of this object */
   public Object clone()
   {  try 
      {  return super.clone();
      } catch (CloneNotSupportedException e) 
      {  throw new Error("Should never happen.");
      }
   }
   
   public static void main(String[] args)
   {  LineItem li = new LineItem(10, "a line item", 5.95);
      LineItem c = (LineItem) li.clone();
      Test.ckEquals("quant", li.quantity, c.quantity);
      Test.ckEquals("descr", li.descr, c.descr);
      Test.ckEquals("uCost", li.uCost, c.uCost);
      Test.ckEquals("class", li.getClass().getName(), c.getClass().getName());
   	
      ServiceLineItem sli = new ServiceLineItem(10, "a SLI", 5.95, 12);
      ServiceLineItem sc = (ServiceLineItem) sli.clone();
      Test.ckEquals("quant", sli.getQuantity(), sc.getQuantity());
      Test.ckEquals("descr", sli.getDescription(), sc.getDescription());
      Test.ckEquals("uCost", sli.getUnitCost(), sc.getUnitCost());
      Test.ckEquals("class", sli.getClass().getName(), sc.getClass().getName());
   	
   }
}
