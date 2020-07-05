import java.util.Scanner;
import java.io.*;
import becker.util.DateTime;


public class Inventory1 extends Object
{
   private int quantity;
   private String partID;
   private String description;
   private double price;
	
   public Inventory1(Scanner in)
   {  super();
      this.quantity = in.nextInt();
      this.partID = in.next();
      this.description = in.next();
      this.price = in.nextDouble();
      in.nextLine();
   }
	
   public String getDescription()
   {  return this.description;
   }
	
   public static void main(String[] args)
   {  Scanner in = null;
      try
      {  in = new Scanner(new File("inventory1.txt"));
      } catch (FileNotFoundException ex)
      {  System.err.println(ex.getMessage());
         System.err.println("in " + System.getProperty("user.dir"));
      }
	   
      while (in.hasNextLine())
      {  Inventory1 ir = new Inventory1(in);
         System.out.println(ir.getDescription());
      }
	   
      in.close();
   }
}
