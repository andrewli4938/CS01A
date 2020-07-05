
public class MainVer1 extends Object
{
   public static void main(String[] args)
   {  String quotation = "To be, or not to be:  that is the question.";
	
      // Loop over each letter in the quotation.
      for (int index = 0; index < quotation.length(); index++)
      {  // Examine one letter in the quotation.
         char ch = quotation.charAt(index);
         System.out.println(ch);
      }
   }
}
