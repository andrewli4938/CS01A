import java.text.NumberFormat;
import becker.util.Test;
import java.io.*;
import java.util.*;


/** An invoice is sent to a customer to request payment for items sold.
 *
 * @author Byron Weber Becker */
public class Invoice extends Object implements Cloneable
{	
   private LineItem[] items = new LineItem[1];
   private int numItems = 0;
   private Customer customer;
   private NumberFormat money = NumberFormat.getCurrencyInstance();

   /** Construct a new invoice object. 
    * @param aCustomer The customer being billed */
   public Invoice(Customer aCustomer)
   {  super();
      this.customer = aCustomer;
   }
   
   /** Read an invoice from a file. */
   public Invoice(Scanner in)
   {  this.customer = new Customer(in);

      // Read and construct the line items, putting them in the array.
      while (in.hasNextLine())
      {  /* String subclass = in.nextLine();
          if (subclass.equals("GoodsLineItem"))
          {  this.addLineItem(new GoodsLineItem(in));
          } else if (subclass.equals("ServicesLineItem"))
          {  this.addLineItem(new ServicesLineItem(in));
          } else if (subclass.equals("ConsultingLineItem"))
          {  this.addLineItem(new ConsultingLineItem(in));
          } else
          {  throw new Error("Unknown subclass: " + subclass + ".");
          }
          */
         this.addLineItem(LineItem.read(in));
      }
   }

   public void addLineItem(LineItem item)
   {  if (this.numItems == this.items.length)
      {  LineItem[] temp = new LineItem[this.items.length * 2];
         for (int i = 0; i < this.numItems; i++)
         {  temp[i] = this.items[i];
         }
         this.items = temp;
      }
      this.items[this.numItems] = item;
      this.numItems++;
   }
   
   /** Print the invoice to the given file.
    * @param out The file where the invoice should be printed. */
   public void print(PrintWriter out)
   {  this.printCompanyAddress(out);
      this.printCustomerAddress(out);
      this.printColumnHeaders(out);
		
      double totalAmountBilled = 0.0;
      for (int i=0; i<this.numItems; i++)
      {  LineItem item = this.items[i];
         item.print(out);							// polymorphism
         double amt = item.calcAmount();		// polymorphism
         totalAmountBilled = totalAmountBilled + amt;
      }
				
      this.printTotal(out, totalAmountBilled);
   }
   
   /** Print column headers for the invoice. */
   private void printColumnHeaders(PrintWriter out)
   {	out.printf("%3s  %-50s%10s%10s%n",
               "Qty", 
               "Description", 
               "U. Cost",
               "Amount");
   }
   
   /** Print the total of all the line items for the invoice. */
   private void printTotal(PrintWriter out, double total)
   {	out.printf("%64s%10s%n", "Total:", this.money.format(total));
   }
   
   /** Sort the line items in this invoice. */
   private void sortLineItems()
   {  Comparator c = new Comparator()
      {
         public int compare(Object a, Object b)
         {  LineItem la = (LineItem) a;
            LineItem lb = (LineItem) b;
   					
            return la.getDescription().compareTo(lb.getDescription());
         }
      };
      
   	// The following line may cause the compiler to give a warning.
      // Dealing with it is beyond the scope of this example.
      Arrays.sort(this.items, 0, this.numItems, c);
   			
   }
   
   /** Print the company address. */
   private void printCompanyAddress(PrintWriter out)
   {  out.println("Computers To You");
      out.println("1 Byte Way");
      out.println("Waterloo, Ontario  N2G 3H4");
      out.println();
   }
      
   /** Print the customer address. */
   private void printCustomerAddress(PrintWriter out)
   {   out.println(this.customer.getName());
      out.println(this.customer.getAddress());
      out.println();
   }
   
   /** Make a copy of this invoice. */
   public Object clone()
   {  try 
      {  Invoice copy = (Invoice) super.clone();
         copy.items = new LineItem[this.numItems];
         for (int i = 0; i < this.numItems; i++)
         {  copy.items[i] = (LineItem) this.items[i].clone();
         }
         // customer is immutable; doesn't need cloning.
         return copy;
      } catch (CloneNotSupportedException e) 
      {  throw new Error("Should never happen.");
      }
   }
   	
   public static void main(String[] args) throws FileNotFoundException
   {  // test the class
      // construct the invoice from the various parts
      Customer cust = new Customer("Byron Weber Becker", 
            "122 Nomad Street\nWaterloo, Ontario  N2L 3G1");
      Invoice inv = new Invoice(cust);
      inv.addLineItem(new GoodsLineItem(3, "Desktop computers", 1750.00));
      inv.addLineItem(new GoodsLineItem(1, "Premium office suite", 750.00));
      inv.addLineItem( new ServicesLineItem(3, "Computer service contracts",
            5.95, 12));
      inv.addLineItem( new ConsultingLineItem("printer installation", 0.75,
            75.00));
      inv.addLineItem( new ConsultingLineItem("local area network wiring", 5.0,
            75.00));
   	
      PrintWriter out = new PrintWriter("testInvoice1.txt");
      inv.print(out);
      out.close();
   	
      // test cloning
      Invoice copy = (Invoice) inv.clone();
      Test.ckIsNotNull("not null", copy);
      Test.ckEquals("not identical", false, copy == inv);
      Test.ckEquals("items not same", false, copy.items[0] == inv.items[0]);
      
   }
}
