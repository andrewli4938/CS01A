import java.text.NumberFormat;


public class ServiceLineItem extends LineItem 
{
   private int numMonths;
   private static final NumberFormat money = NumberFormat.getCurrencyInstance();
	
   public ServiceLineItem(int aQuantity, String aDescr, double aMthlyCost, int aNumMonths)
   {  super(aQuantity, aDescr, aMthlyCost);
      this.numMonths = aNumMonths;
   }

   public double calculateAmount()
   {  return super.calculateAmount() * this.numMonths;
   }
	
   public String getDescription()
   {  return super.getDescription() + " (" + this.numMonths + " months)";
   }
}
