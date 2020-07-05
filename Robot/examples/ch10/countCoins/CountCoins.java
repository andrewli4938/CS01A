import java.util.Scanner;


public class CountCoins extends Object
{
public static void main(String[] args)
{  int EARLIEST = 1850;
   int LATEST = 2008;
   int[] ages = new int[LATEST - EARLIEST + 1];
	  
   Scanner in = new Scanner(System.in);
   while (true)
   {  System.out.print("Enter a mint year or -1 to exit: ");
      int yr = in.nextInt();
      if (yr == -1)
      {  break;
      }
		
      ages[yr - EARLIEST]++;
   }
	
   for (int i=0; i<ages.length; i++)
   {  System.out.println(ages[i] + " coins minted in " + (i + EARLIEST));
   }
}
}
