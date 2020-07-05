import java.util.Scanner;

public class ReadConsole
{
   public static void main(String[] args)
   {  Scanner cin = new Scanner(System.in);
   
      System.out.print("Enter an integer: ");
      int a = cin.nextInt();
      System.out.print("Enter an integer: ");
      int b = cin.nextInt();
   	
      System.out.println(a + " * " + b + " = " + a * b);
   }
}
