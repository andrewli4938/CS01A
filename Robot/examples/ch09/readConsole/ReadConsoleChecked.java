import java.util.Scanner;

public class ReadConsoleChecked
{
   public static void main(String[] args)
   {  Scanner cin = new Scanner(System.in);
   
      int a = 0;
      while (true)
      {  System.out.print("Enter an integer: ");
         if (cin.hasNextInt())
         {  a = cin.nextInt();
            cin.nextLine();		// consume the newline
            break;
         } else
         {  String next = cin.nextLine();	// consume the error
            System.out.println(next + " is not an integer such as 10 or -3.");
         }
      }
      
      int b = 0;
      while (true)
      {  System.out.print("Enter an integer: ");
         if (cin.hasNextInt())
         {  b = cin.nextInt();
            cin.nextLine();		// consume the newline
            break;
         } else
         {  String next = cin.nextLine();	// consume the error
            System.out.println(next + " is not an integer such as 10 or -3.");
         }
      }
   	
      System.out.println(a + " * " + b + " = " + a * b);
   }
}
