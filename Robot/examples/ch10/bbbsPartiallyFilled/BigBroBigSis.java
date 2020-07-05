import java.io.*;
import java.util.Scanner;
import becker.util.DateTime;
import becker.util.Test;


/** A list of the "bigs" and "littles" associated with a Big Brother/Big Sister 
 * program.  "Bigs" are the Big Brothers and Big Sisters;  "littles" are 
 * the Little Brothers and Sisters they are (potentially) paired with.
 * @author Byron Weber Becker */
public class BigBroBigSis extends Object
{	
   private Person[] persons = new Person[1];		// the list of bigs and littles
   private int size;                            // actual number of persons
	
   /** Construct a new object by reading all the bigs and littles from a file.
    * @param fileName the name of the file storing the information for bigs and littles */
   public BigBroBigSis(String fileName)
   {  super();
	
      // Read the data, adding each person to the array
      Scanner in = this.openFile(fileName);
      while (in.hasNextLine())
      {  this.add(new Person(in));	
      }
      in.close();

   }
	
   /** Add a person to the the list of persons. */
   public void add(Person p)
   {  if (this.persons.length == this.size)
      {  // the array is full -- grow it
         Person[] larger = new Person[this.size * 2];
         for (int i=0; i<this.size; i++)
         {  larger[i] = this.persons[i];
         }
         this.persons = larger;
      }
      this.persons[this.size] = p;
      this.size++;
   }
   
   /** Find the number of participants in each age group. 
    * @return a filled array where a[i] is the number of people i years old */
   public int[] getAgeCounts()
   {	int[] ageCounters = new int[200];
   	for(int i=0; i<this.size; i++)
   	{	int age = this.persons[i].getAge();
   		ageCounters[age]++;
   	}
   	return ageCounters;
   }
	
	
	/** Open the named data file. */
	private Scanner openFile(String fileName)
	{  try 
      {  File f = new File(fileName);
         return new Scanner(f);
      }
      catch (FileNotFoundException ex) 
      {  System.out.println(ex.getMessage());
         System.out.println("working directory = " + System.getProperty("user.dir"));
         System.exit(1);
         return null;
      }
	}
	
   public static void main(String[] args)
   {  System.out.println("Testing BigBroBigSis");
      BigBroBigSis bbbs = new BigBroBigSis("test.txt");
      Test.ckIsNotNull("persons not null", bbbs.persons);
      Test.ckEquals("numPersons", 6, bbbs.size);
      Test.ckEquals("size of array", 8, bbbs.persons.length);
      Test.ckEquals("name of 1st person", "Kenneth A Parsons",
            bbbs.persons[0].getName());
            
      // array initializer
      bbbs.persons = new Person[] {
         new Person("Byron"), new Person("Ann"), new Person("Luke"),
         new Person("Joel")
      };
      Test.ckIsNotNull("persons not null", bbbs.persons);
      Test.ckEquals("size of array", 4, bbbs.persons.length);
      Test.ckEquals("name of 1st person", "Byron", bbbs.persons[0].getName());
   }
}
