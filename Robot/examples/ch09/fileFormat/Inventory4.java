import java.util.Scanner;
import java.io.*;
import becker.util.DateTime;


public class Inventory4 extends Object
{
   private int quantity;
   private String partID;
   private String description;
   private double price;
   private String distributor;
	
   public Inventory4(Scanner in)
   {  super();
      this.quantity = in.nextInt();
      this.partID = in.next();
      this.price = in.nextDouble();
      this.description = in.next();
      this.distributor = in.next();
      in.nextLine();
   }
   
   public String getDescription()
   {  return this.description;
   }
   
   public String getDistributor()
   {  return this.distributor;
   }
 
   public static void main(String[] args)
   {  Scanner in = null;
      try
      {  in = new Scanner(new File("inventory4.txt"));
         in.useDelimiter(":");
      } catch (FileNotFoundException ex)
      {  System.err.println(ex.getMessage());
         System.err.println("in " + System.getProperty("user.dir"));
      }
	   
      while (in.hasNextLine())
      {  Inventory4 ir = new Inventory4(in);
         System.out.println(ir.getDescription() + "\t\t" + ir.getDistributor());
      }
	   
      in.close();
   }
}
