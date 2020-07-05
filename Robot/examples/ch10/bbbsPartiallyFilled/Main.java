

/*
 * Main.java 1.0 
 * Copyright 2003 by Byron Weber Becker.  All rights reserved.
 */
import java.util.Comparator;


public class Main extends Object
{
   public static void main(String[] args) 
   {
      System.out.println("Big Brother/Big Sister");
      BigBroBigSis bbbs = new BigBroBigSis("bbbs.txt");


		int[] ages = bbbs.getAgeCounts();
		for(int i=0; i<ages.length; i++)
		{	if (ages[i] > 0)
			{	System.out.println ("There are " + ages[i] + " participants that are " + i + " years old.");
			}
		}
   }	
}
