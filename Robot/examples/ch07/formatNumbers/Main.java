
import java.text.NumberFormat;

public class Main extends Object
{
   public static void main(String[] args)
   {
      NumberFormat money = NumberFormat.getCurrencyInstance();
      
      double carPrice = 12225.00;
      double taxRate = 0.15;
      
      System.out.println("No formatting:");
      System.out.println("Car:   " + carPrice);
      System.out.println("Tax:   " + carPrice * taxRate);
      System.out.println("Total: " + carPrice * (1.0 + taxRate));
      
      System.out.println("\nWith formatting:");
      System.out.println("Car:   " + money.format(carPrice));
      System.out.println("Tax:   " + money.format(carPrice * taxRate));
      System.out.println("Total: " + money.format(carPrice * (1.0 + taxRate)));
      
      System.out.println("\nIn columns:");
      System.out.printf("%-10s %10s%n", "Car:", money.format(carPrice));
      System.out.printf("%-10s %10s%n", "Tax:", money.format(carPrice * taxRate));
      System.out.printf("%-10s %10s%n", "Total:", money.format(carPrice * (1.0 + taxRate)));
      
      System.out.println("\nFloating point precision:");
      System.out.println(Math.PI);
      System.out.printf("%10.4f%n", Math.PI);
      System.out.printf("%-10.4f%n", Math.PI);
      System.out.printf("%10d%n", 314);
   }
}