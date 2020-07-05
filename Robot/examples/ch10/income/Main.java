import java.util.Scanner;
import java.io.*;

public class Main extends Object
{
	public static void main(String[] args)
   {  
   	Scanner in = null;
	   try
	   {  in = new Scanner(new File("income.txt"));
	   } catch (FileNotFoundException ex)
	   {  System.err.println(ex.getMessage());
	      System.err.println("in " + System.getProperty("user.dir"));
	   }
   	
   	BBBSIncome income = new BBBSIncome(in);
   	in.close();
   	
   	income.printIncomeChart();
   	System.out.println ("Total income: " + income.getTotalIncome());
   	System.out.println ("Total income from individuals: " + income.getTotalByCategory(2));
   }
}
