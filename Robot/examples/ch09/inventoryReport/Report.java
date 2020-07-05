import java.io.*;
import java.util.Scanner;

/** Reformat the inventory.
 * 
 * @author Byron Weber Becker */
public class Report
{
	public static void main(String[] args)
   {  Scanner in = null;
	   try
	   {  in = new Scanner(new File("inventory.txt"));
	   } catch (FileNotFoundException ex)
	   {  System.err.println(ex.getMessage());
	      System.err.println("in " + System.getProperty("user.dir"));
	   }
	   
	   System.out.printf("%-15s%5s%8s%10s%n", "Description", "Quant", "Cost", "Value");
	   while (in.hasNextLine())
	   {	int quantity = in.nextInt();
	   	String partNum = in.next();
	   	String description = in.next();
	   	double cost = in.nextDouble();
	   	in.nextLine();
	   	
	   	// Print in a different order, including a calculated value.  
	   	System.out.printf("%-15s%5d%8.2f%10.2f%n", description, quantity, cost, quantity * cost);
	   }
	   
	   in.close();
   }
}
