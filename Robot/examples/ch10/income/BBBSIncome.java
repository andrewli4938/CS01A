import java.util.Scanner;

/** Demonstrate a 2D array.
 *
 * @author Byron Weber Becker */
public class BBBSIncome extends Object
{
	// income by month (row) and source (column)
   private int[][] income;
		
	/** Read the income data from a file.
	 * @param in The open file containing the data. */
   public BBBSIncome(Scanner in)
   {  super();
   	// get the size
   	int rows = in.nextInt();
   	int cols = in.nextInt();
   	in.nextLine();
   	
   	// allocate the array
   	this.income = new int[rows][cols];
   	
   	// fill the array
   	for (int r=0; r<this.income.length; r++)
      {  for (int c=0; c<this.income[r].length; c++)
      	{	this.income[r][c] = in.nextInt();
      	}
      	in.nextLine();
      }
   }
		
	/** Print the income chart. */
   public void printIncomeChart()
   {  for (int r=0; r<this.income.length; r++)
      {  for (int c=0; c<this.income[r].length; c++)
         {  System.out.print(this.income[r][c] + "\t");
         }
         System.out.println();
      }
   }
   
   /** Calculate the total income for the year. */
   public int getTotalIncome()
   {  int total = 0;
   	for (int r=0; r<this.income.length; r++)
      {  for (int c=0; c<this.income[r].length; c++)
         {  total = total + this.income[r][c];
         }
      }
      return total;
   }
   
   /** Calculate the total income for a given category for the year.
    * @param columnNum the index of the column containing the desired category */
   public int getTotalByCategory(int columnNum)
   {	int total = 0;
   	for(int r=0; r<this.income.length; r++)
   	{	total = total + this.income[r][columnNum];
   	}
   	return total;
   }
}
