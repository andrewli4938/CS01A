import java.util.Arrays;
import java.util.Scanner;


/** Sort the strings read from a file. 
 *
 * @author Byron Weber Becker */
public class Sort
{
   public static void main(String[] args)
   {  // Get the strings from the user.
      Scanner in = new Scanner(System.in);
      System.out.print("How many strings: ");
      int num = in.nextInt();
      in.nextLine();
	   	   
      String[] strings = new String[num];
      for (int i=0; i<num; i++)
      {  strings[i] = in.nextLine();
      }	   
	   
      // Sort the strings.
      Arrays.sort(strings);
	   
      // Display the sorted list of strings.
      System.out.println("The sorted strings:");
      for (int i=0; i<strings.length; i++)
      {  System.out.println(strings[i]);
      }
   }
}
