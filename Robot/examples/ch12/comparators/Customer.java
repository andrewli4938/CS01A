

/** Information about a specific customer.
 *
 * @author Byron Weber Becker */
public class Customer 
{
   private String name;
   private String addr;
	
   /** Construct a new customer object.
    * @param aName the customer's name
    * @param anAddress the customer's billing address */
   public Customer(String aName, String anAddress)
   {  super();
      this.name = aName;
      this.addr = anAddress;
   }
	
   /** Get the customer's address. */
   public String getAddress()
   {  return this.addr;
   }
	
   /** Get the customer's name */
   public String getName()
   {  return this.name;
   }
}
