
public class MainVer3
{

   public static void main(String[] args)
   {  String quotation = "To be, or not to be:  that is the question";
   	String lowerQuote = quotation.toLowerCase();
      String vowels = "aeiou";
		
      int counter = 0;	// Count the number of vowels.
      // Loop over each letter in the quotation.
      for (int index = 0; index < lowerQuote.length(); index++)
      {  // Examine one letter in the quotation.
         char ch = lowerQuote.charAt(index);
         if (vowels.indexOf(ch) >= 0)
         {  counter += 1;
         }
      }
		
      System.out.println("There are " + counter + " vowels.");
   }
}
