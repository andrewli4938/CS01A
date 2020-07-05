

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

      System.out.println("Oldest = " + bbbs.findOldestPerson());
      System.out.println("Num FEMALE = " + bbbs.count(Gender.FEMALE));
      System.out.println("Num MALE = " + bbbs.count(Gender.MALE));

      /*
       bbbs.sort(new AgeComparator());
       bbbs.listAll();
       */
   
      /*
       Person[] ss = bbbs.extractSubset(Gender.FEMALE, Role.BIG);
       for(int i=0; i<ss.length; i++)
       {	System.out.println (ss[i]);
       }
       */
   }	
}


class AgeComparator extends Object implements Comparator
{
   public int compare(Object person1, Object person2)
   {  Person p1 = (Person) person1;
      Person p2 = (Person) person2;
      if (p1.getBirthdate().isBefore(p2.getBirthdate()))
      {  return -1;
      } else if (p1.getBirthdate().isAfter(p2.getBirthdate()))
      {  return 1;
      } else
      {  return 0;
      }
   }
}
