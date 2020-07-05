import java.util.Scanner;
import java.io.*;
import becker.util.DateTime;


public class Inventory2 extends Object
{
   private int quantity;
   private String partID;
   private String description;
   private double price;
	
   public Inventory2(Scanner in)
   {  super();
      this.quantity = in.nextInt();
      this.partID = in.next();
      this.price = in.nextDouble();
      this.description = in.nextLine().trim();
   }
	
   public String getDescription()
   {  return this.description;
   }
	
   public static void main(String[] args)
   {  Scanner in = null;
      try
      {  in = new Scanner(new File("inventory2.txt"));
      } catch (FileNotFoundException ex)
      {  System.err.println(ex.getMessage());
         System.err.println("in " + System.getProperty("user.dir"));
      }
	   
      while (in.hasNextLine())
      {  Inventory2 ir = new Inventory2(in);
         System.out.println(ir.getDescription());
      }
	   
      in.close();
   }
}
