import java.util.*;


/** An electronic  telephone book.
 *
 * @author Byron Weber Becker */
public class MapExample extends Object
{
   public static void main(String[] args)
   {  // Create the mapping between names and phone numbers.
      TreeMap<String, String> phoneBook = new TreeMap<String, String>();
      
      // Insert the phone numbers.
      phoneBook.put("Sue", "578-3948");
      phoneBook.put("Fazila", "886-4957");
      phoneBook.put("Jo", "1-604-329-1023");
      phoneBook.put("Don", "578-3948");
      phoneBook.put("Rama", "886-9521");
      
      // Print the list.
      for (String k : phoneBook.keySet())
      {  System.out.println(k + " = " + phoneBook.get(k));
      }
   
      // Repeatedly ask the user for a name until "done" is entered.
      // Scanner is discussed in detail in Chapter 9.
      Scanner in = new Scanner(System.in);
      while (true)
      {  System.out.print("Enter a name or 'done': ");
         String name = in.next();
         
         if (name.equalsIgnoreCase("done"))
         {  break; // Break out of the loop.
         }
         
         System.out.println(name + ": " + phoneBook.get(name));
      }
   }
}
