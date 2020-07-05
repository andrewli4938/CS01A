import java.util.Scanner;

/** Information about a specific customer.
 *
 * @author Byron Weber Becker */
public class Customer 
{
   private String name;
   private String address;
	
   /** Construct a new customer object.
    * @param aName the customer's name
    * @param anAddress the customer's billing address */
   public Customer(String aName, String anAddress)
   {  super();
      this.name = aName;
      this.address = anAddress;
   }
   
   public Customer(Scanner in)
   {  this.name = in.nextLine();
      this.address = in.nextLine();
      this.address += "\n" + in.nextLine();
   }
	
   /** Get the customer's address. */
   public String getAddress()
   {  return this.address;
   }
	
   /** Get the customer's name */
   public String getName()
   {  return this.name;
   }
}
