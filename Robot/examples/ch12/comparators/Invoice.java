import java.text.NumberFormat;
import becker.util.Test;
import java.io.*;


/** An invoice is sent to a customer to request payment for items sold.
 *
 * @author Byron Weber Becker */
public class Invoice extends Object implements Cloneable
{	
   private LineItem[] items = new LineItem[10];
   private int numItems = 0;
   private Customer customer;
   private NumberFormat money = NumberFormat.getCurrencyInstance();

   /** Construct a new invoice object. 
    * @param aCustomer The customer being billed */
   public Invoice(Customer aCustomer)
   {  super();
      this.customer = aCustomer;
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
   {  this.printAddresses(out);
   
      double total = 0.0;
      for (int i = 0; i < this.numItems; i++)
      {  this.items[i].print(out);
         total = total + this.items[i].calculateAmount();
      }
   	
      out.println(
            "                                                           "
                  + "Total: " + this.money.format(total));
   }
   
   /** Print the company and customer addresses. */
   private void printAddresses(PrintWriter out)
   {  // printing company and name side-by-side is hard.  Stack them vertically
      // instead.  A better alternative would be to output HTML and let a
      // browser format the text. 
      out.println("Computers To You");
      out.println("1 Byte Way");
      out.println("Waterloo, Ontario  N2G 3H4");
      out.println();
      out.println(this.customer.getName());
      out.println(this.customer.getAddress());
      out.println();
   }
   
   /** Make a copy of this invoice. */
   public Object clone()
   {  try 
      {  Invoice inv = (Invoice) super.clone();
         inv.items = new LineItem[this.numItems];
         for (int i = 0; i < this.numItems; i++)
         {  inv.items[i] = (LineItem) this.items[i].clone();
         }
         // customer is immutable; doesn't need cloning.
         return inv;
      } catch (CloneNotSupportedException e) 
      {  throw new Error("Should never happen.");
      }
   }
   	
   public static void main(String[] args)
   {  // test the class
      // construct the invoice from the various parts
      Customer cust = new Customer("Byron Weber Becker", 
            "122 Nomad Street\nWaterloo, Ontario  N2L 3G1");
      Invoice inv = new Invoice(cust);
      inv.addLineItem(new GoodsLineItem(3, "Desktop computers", 1750.00));
      inv.addLineItem(new GoodsLineItem(1, "Premium office suite", 750.00));
      inv.addLineItem(
            new ServiceLineItem(3, "Computer service contracts", 5.95, 12));
      inv.addLineItem(
            new ConsultingLineItem("printer installation", 0.75, 75.00));
      inv.addLineItem(
            new ConsultingLineItem("local area network wiring", 5.0, 75.00));
   	
   	try 
      {  
         PrintWriter out = new PrintWriter("testInvoice1.txt");
         inv.print(out);
         out.close();
      }
      catch (FileNotFoundException ex) 
      {  ex.printStackTrace();
      } 
   	
      // test cloning
      Invoice copy = (Invoice) inv.clone();
      Test.ckIsNotNull("not null", copy);
      Test.ckEquals("not identical", false, copy == inv);
      Test.ckEquals("items not same", false, copy.items[0] == inv.items[0]);
      
   }
}
