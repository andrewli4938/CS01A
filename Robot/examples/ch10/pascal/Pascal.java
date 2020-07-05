import java.text.NumberFormat;


/** An object representing Pascal's triangle.  The first row of the 
 * triangle has length 1 and the value of the sole element is 1.  Each succeeding
 * row is 1 element longer than the previous.  The first and last elements are
 * both 1.  The remaining elements are the sum of two elements from the
 * previous row, as illustrated:
 *                         1
 *                        1 1
 *                       1 2 1
 *                      1 3 3 1
 *                     1 4 6 4 1
 *
 * @author Byron Weber Becker */
public class Pascal extends Object
{
	// a 2D array to hold the triangle -- each row of the
	// array is the same length as the corresponding row of the triangle
	private int[][] pascal;
	
	/** Construct a new triangle.
	 * @param numRows The number of rows in the triangle */
	public Pascal(int numRows)
	{	super();
	
		this.pascal = new int[numRows][];
		
   	for(int r=0; r<numRows; r++)
   	{	pascal[r] = new int[r+1];
   		for(int c=0; c<r+1; c++)
   		{	if (c == 0 || c == r)
   			{	pascal[r][c] = 1;
   			} else
   			{	pascal[r][c] = pascal[r-1][c-1] + pascal[r-1][c];
   			}
   		}
   	}
	}
	
	/** Print this triangle on System.out. */
	public void print()
	{	for(int r=0; r<pascal.length; r++)
		{	for(int c=0; c<pascal[r].length; c++)
			{	System.out.print(pascal[r][c]);
			}
			System.out.println();
		}   	
	}
	
	/** Print this triangle on System.out formatted as shown in the 
	 * class documentation comment. */
	public void printFormatted()
	{	int rows = this.pascal.length;
		for(int r=0; r<rows; r++)
		{	for(int i=0; i<(rows-r)/2; i++)
				System.out.print("      ");
			if (r%2 == 1)
				System.out.print("   ");
			for(int c=0; c<pascal[r].length; c++)
			{	System.out.printf("%6d", pascal[r][c]);
			}
			System.out.println();
		}   	
	}
	
	/** Verify that each rows sums to 2^n where n is the row number.
	 * @returns true if every row meets the criteria; false otherwise */
	public boolean rowsSumToPowers()
	{	int rows = this.pascal.length;
		for(int n=0; n<rows; n++)
		{	int sum = 0;
			for(int c=0; c<this.pascal[n].length; c++)
			{	sum += this.pascal[n][c];
			}
			if (sum != (int)Math.pow(2, n))
			{	return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args)
   {  
   	Pascal triangle = new Pascal(10);
   	triangle.print();
   	triangle.printFormatted();
   	
   	System.out.println ("Rows sum to 2^n: " + triangle.rowsSumToPowers());
   	
   	
   }
}
