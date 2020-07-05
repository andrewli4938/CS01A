import java.util.Scanner;

/*
 * CharacterCodes.java 1.0 
 * Copyright 2003 by Byron Weber Becker.  All rights reserved.
 */

/** Translate characters into their integer equivalents. 
 * @author Byron Weber Becker */
public class CharacterCodes extends Object
{
	public static void main(String[] args) 
	{
		System.out.println("Type a line of text to show the Unicode encoding.");
		System.out.println("Type \"quit\" to end.");
		
		Scanner in = new Scanner(System.in);
		while (true)
		{	System.out.print("> ");
			String input = in.nextLine();
			
			if (input.equals("quit"))
			{	break;
			}
			
			for(int i=0; i<input.length(); i++)
			{	char asChar = input.charAt(i);
				int asInt = input.charAt(i);
				System.out.println("" + asChar + "    (" + asInt + ")");
			}
		}
		
	}
}
