
public class MainVer2 extends Object
{
   public static void main(String[] args)
   {  String quotation = "To be, or not to be:  that is the question.";
			
      int counter = 0;	// Count number of o's.
      // Loop over each letter in the quotation.
      for (int index = 0; index < quotation.length(); index++)
      {  // Examine one letter in the quotation.
         char ch = quotation.charAt(index);
         if (ch == 'o')
         {  counter += 1;
         }
      }
		
      System.out.println("There are " + counter + " occurrences of 'o'.");
   }
}
