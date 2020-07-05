package com.learningwithrobots.bwbecker.io;

import java.util.Scanner;
import java.io.*;

/** A set of useful static methods for interacting with a user via the console.
 *
 * @author Byron Weber Becker */
public class Prompt extends Object
{  
   private static final Scanner in = new Scanner(System.in);
   
   /** Prompt the user to enter an integer.
    * @param prompt The prompting message for the user.
    * @return The integer entered by the user. */
   public static int forInt(String prompt)
   {  while (true)
      {  System.out.print(prompt);
         if (Prompt.in.hasNextInt())
         {  int answer = Prompt.in.nextInt();
            Prompt.in.nextLine(); // consume remaining input
            return answer;
         } else
         {  String input = Prompt.in.nextLine();
            System.out.println("Error: " + input
                  + " not recognized as an integer such as '10' or '-3'.");
         }
      }
   }
   
   /** Prompt the user for a file to use as input.
    * @param prompt The prompting message for the user.
    * @return A File object representing a file that exists and is readable. */
   public static File forInputFile(String prompt)
   {  while (true)
      {  System.out.print(prompt);
         String name = in.nextLine().trim();
         File f = new File(name);
         if (!f.exists())
         {  System.out.println("Error: " + name + " does not exist.");
         } else if (f.isDirectory())
         {  System.out.println("Error: " + name + " is a directory.");
         } else if (!f.canRead())
         {  System.out.println("Error: " + name + " is not readable.");
         } else
         {  return f;
         }
      }
   }
   
   /** Prompt the user for a file to use as input.
    * @param prompt The prompting message for the user.
    * @return A Scanner object ready to read the file specified by the user. */
   public static Scanner forInputScanner(String prompt)
   {  try 
      {  return new Scanner(Prompt.forInputFile(prompt));
      } catch (FileNotFoundException ex) 
      {  // Shouldn't happen, given the work we do in forInputFile.
         System.out.println(ex.getMessage());
         System.exit(1);
      }
      return null; // for the compiler
   }
 
   /** Prompt the user to enter a boolean value.
    * @param prompt The prompting message for the user.
    * @return The value entered by the user. */
   public static boolean forBoolean(String prompt)
   {  while (true)
      {  System.out.print(prompt);
         if (Prompt.in.hasNextBoolean())
         {  boolean answer = Prompt.in.nextBoolean();
            Prompt.in.nextLine(); // consume remaining input
            return answer;
         } else
         {  String input = Prompt.in.next();
            System.out.println(
                  "Error: " + input
                  + " not is a boolean.  Enter 'true' or 'false'.");
         }
      }
   }

   /** Prompt for a non-empty string. */
   public static String forString(String prompt)
   {  System.out.print(prompt);
   	String answer = Prompt.in.nextLine().trim();
   	while (answer.length() == 0)
   	{	System.out.print("Please enter a non-empty string: ");
   		answer = Prompt.in.nextLine().trim();
   	}
   	return answer;
   }
  
   
   /** Return the rest of the current line of input. */
   public static String nextLine()
   {  return Prompt.in.nextLine();
   }
   
   public static void main(String[] args)
   {  System.out.println("1. forInt");
      System.out.println("2. forBoolean");   
      System.out.println("3. forString");
      System.out.println("4. forInputFile");
      System.out.println("0. Quit");
         
      while (true)
      {  int which = Prompt.forInt("Menu Selection: ");
         if (which == 1)
         {  System.out.println(Prompt.forInt("forInt: "));
         } else if (which == 2)
         {  System.out.println(Prompt.forBoolean("forBoolean: "));
         } else if (which == 3)
         {  System.out.println(Prompt.forString("forString: "));
         } else if (which == 4)
         {  System.out.println(Prompt.forInputFile("forInputFile: "));
         } else if (which == 0)
         {  break;
         } else
         {  System.out.println("Unrecognized command: " + which);
         }
      }
   }
}
