
import java.util.HashSet;
import java.util.Scanner;
import java.io.*;


/** Count the number of unique "words" in a file.  Does not take punctuation
 * into account.
 *
 * @author Byron Weber Becker */
public class SetExample extends Object
{
   public static void main(String[] args)
   {  HashSet<String> words = new HashSet<String>();
   	
   	/*  
   	// Count the words in a file
      try 
      {  Scanner in = new Scanner(new File("Hamlet.txt"));
         while (in.hasNext())
         {  String s = in.next();
            words.add(s.toLowerCase());
         }
      } catch (FileNotFoundException ex) 
      {  System.out.println(ex.getMessage());
         System.exit(1);
      }
      */
       
      words.add("to");
      words.add("be");
      words.add("or");
      words.add("not");
      words.add("to");
      words.add("be");

      for (String s : words)
      {  System.out.print(s + "  ");
      }
       
      System.out.println(words.size() + " unique words");
    
   }
}
