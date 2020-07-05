
import becker.util.Test;


public class Meter2 extends Object
{
   private double unitCost;
	
   public Meter2(double unitCost)
   {  super();
      this.unitCost = unitCost;
   }	
	
   public double getUnitCost()
   {  return this.unitCost;
   }
	
   public static void main(String[] args)
   {  Meter2 m1 = new Meter2(2.00);
      Test.ckEquals("unit cost", 2.00, m1.getUnitCost());
      Meter2 m2 = new Meter2(1.99);
      Test.ckEquals("unit cost", 1.99, m2.getUnitCost());
   }
}
