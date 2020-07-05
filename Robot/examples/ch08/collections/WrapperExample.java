
import java.util.*;


/** Help the user find out if a number is prime or not.
 *
 * @author Byron Weber Becker */
public class WrapperExample extends Object
{
   public static void main(String[] args)
   {  HashSet<Integer> primes = new HashSet<Integer>();
   	
      // The prime numbers we know. 
      primes.add(2);
      primes.add(3);
      primes.add(5);
      primes.add(7);
      primes.add(11);
      primes.add(13);
   	
      // Help the user classify numbers
      Scanner in = new Scanner(System.in);
      System.out.print("Enter a number: ");
      int num = in.nextInt();
      
      if (primes.contains(num))
      {  System.out.println(num + " is prime.");
      } else if (num <= 13)
      {  System.out.println(num + " is not prime.");
      } else
      {  System.out.println(
               num + " might be prime; it's too big for me to know.");
      }
   }
}
