import java.util.Scanner;
import java.io.*;
import becker.util.DateTime;


public class Inventory3 extends Object
{
   private int quantity;
   private String partID;
   private String description;
   private double price;
   private String distributor;
	
   public Inventory3(Scanner in)
   {  super();
      this.quantity = in.nextInt();
      this.partID = in.next();
      this.price = in.nextDouble();
      this.description = "";
      String token = in.next();
      while (!token.equals(":"))
      {  this.description += " " + token;
         token = in.next();
      }
      this.distributor = in.nextLine().trim();
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
      {  in = new Scanner(new File("inventory3.txt"));
      } catch (FileNotFoundException ex)
      {  System.err.println(ex.getMessage());
         System.err.println("in " + System.getProperty("user.dir"));
      }
	   
      while (in.hasNextLine())
      {  Inventory3 ir = new Inventory3(in);
         System.out.println(ir.getDescription() + "\t\t" + ir.getDistributor());
      }
	   
      in.close();
   }
}
