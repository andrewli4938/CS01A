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
   private Person[] persons;		// the list of bigs and littles
	
   /** Construct a new object by reading all the bigs and littles from a file.
    * @param fileName the name of the file storing the information for bigs and littles */
   public BigBroBigSis(String fileName)
   {  super();
	
      // count the number of Persons in the file
      int count = 0;
      Scanner in = this.openFile(fileName);
      while (in.hasNextLine())
      {  Person p = new Person(in);
         count++;
      }
      in.close();
		
      // allocate an array to hold each object we read
      this.persons = new Person[count];
		
      // Read the data, storing a reference to each object in the array
      in = this.openFile(fileName);
      for (int i=0; i<count; i++)
      {  this.persons[i] = new Person(in);	
      }
      in.close();

   }
	
   /** Swap the elements at indices a and b. */
   private void swap(int a, int b)
   {  Person temp = this.persons[a];
      this.persons[a] = this.persons[b];
      this.persons[b] = temp;
   }

   /** Calculate the average age of persons in the array. */
   public double calcAverageAge()
   {  int sumAges = 0;			// role: gatherer
      for (int i=0; i<this.persons.length; i++)
      {  Person p = this.persons[i];
         sumAges = sumAges + p.getAge();
      }
      return (double) sumAges/this.persons.length;
   }

   /** Find the average age of the "littles". */
   public double getAverageLittleAge()
   {  int sumAges = 0;				// role: gatherer
      int numLittles = 0;			// role: stepper
      for (Person p : this.persons)
      {  if (p.getRole() == Role.LITTLE)
         {  sumAges = sumAges + p.getAge();
            numLittles = numLittles + 1;
         }
      }
      return (double) sumAges/numLittles;
   }

   /** Search for the named person.
    * @param name The name of the person to search for.
    * @returns The first matching person object;  null if there is none. */
   public Person search(String name)
   {  for (int i=0; i<this.persons.length; i++)
      {  Person p = this.persons[i];
      	if (p.getName().equals(name))
         {  return p;			// success!  Exit the loop
         }
      }
      return null;				// failure
   }
	
	/** Search for the first person object matching the given name 
	* @param name The name of the person to find (the key) */
	public Person searchAlt(String name)
	{	int i = 0;				// role: stepper
		while (i < this.persons.length && 
					!this.persons[i].getName().equalsIgnoreCase(name))
		{	i++;
		}
	
		if (i == this.persons.length)
		{	return null;			// failure:  got to the end without finding it
		} else 
		{	return this.persons[i];	// success
		}
	}
	
   /** Find the oldest person in the list. 
    * (Assumes there is at least one person in the array. */
   public Person findOldestPerson()
   {  Person oldestSoFar = this.persons[0];	// role: most-wanted holder
		
      for (Person currentPerson : this.persons)
      {  if (currentPerson.getAge() > oldestSoFar.getAge())
         {  oldestSoFar = currentPerson;
         }
      }
      return oldestSoFar;
   }

	
   /** Sort the list of persons in alphabetical order by name. */
   public void sort()
   {  for (int firstUnsorted=0; firstUnsorted<this.persons.length-1; firstUnsorted++)
      {  int extremeIndex = this.findExtreme(firstUnsorted);
         this.swap(firstUnsorted, extremeIndex);
      }
   }
	
   /** Find the extreme element in the unsorted portion of the array.
    * @param indexToStart the smallest index in the unsorted portion of the array
    * @return the index of the extreme element. */
   private int findExtreme(int indexToStart)
   {  int indexBestSoFar = indexToStart;
      String nameBestSoFar = this.persons[indexBestSoFar].getName();
      for (int i=indexToStart+1; i<this.persons.length; i++)
      {  String currPersonName = this.persons[i].getName();
         if (currPersonName.compareTo(nameBestSoFar) < 0)
         {  indexBestSoFar = i;
            nameBestSoFar = this.persons[i].getName();
         }
      }
      return indexBestSoFar;
   }
	
   /** Sort the persons array in increasing order by age */
   public void sortByAge()
   {  for (int firstUnsorted=0; firstUnsorted<this.persons.length-1; firstUnsorted++)
      {  // find the youngest unsorted person
         int extremeIndex = firstUnsorted;
         for (int i=firstUnsorted + 1; i<this.persons.length; i++)
         {  if (this.persons[i].getAge() < this.persons[extremeIndex].getAge())
            {  extremeIndex = i;
            }
         }
			
         // swap the youngest unsorted person with the person at firstUnsorted
         Person temp = this.persons[extremeIndex];
         this.persons[extremeIndex] = this.persons[firstUnsorted];
         this.persons[firstUnsorted] = temp;
      }
   }

	
   /** Extract a subset of all the persons who have the given gender and role.
    * @param g The gender of all members of the subset
    * @param r The role of all members of the subset */
   public Person[] extractSubset(Gender g, Role r)
   {  int ssSize = this.countSubset(g, r);
      Person[] subset = new Person[ssSize];
      this.fillSubset(subset, g, r);
      return subset;
   }
	
   /** Count the number of persons matching the given gender and role.
    *  @param g The gender of persons to be included in the subset
    *  @param r The role of the persons to be included in the subset.  */
   private int countSubset(Gender g, Role r)
   {  // To be completed as an exercise.
      return 0;
   }
   
   /** Fill the subset array with Person objects matching the given gender and role.
    * @param subset  The array to fill with elements belonging to the subset.
    *  @param g The gender of persons to be included in the subset
    *  @param r The role of the persons to be included in the subset.  */
   private void fillSubset(Person[] subset, Gender g, Role r)
   {  int ssPos = 0;    // position within the subset
      int arrPos = 0;   // position within the array
      while (ssPos < subset.length)
      {  Person p = this.persons[arrPos];
         if (p.getGender() == g && p.getRole() == r)
         {  subset[ssPos] = p;
            ssPos++;
         }
         arrPos++;
      }
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
	
	
   /** Find the most compatible person for p.  
    * @param p The person to find a match for
    * @returns The most compatible person for p; null if there isn't one 
    * (e.g. everyone else is already matched) */
   public Person findMostCompatible(Person p)
   {  Person mostCompatibleSoFar = null;
      double compatScore = -1.0;		// less than any valid compatibility score
      for (int i=1; i<this.persons.length; i++)
      {  if (this.persons[i].getCompatibility(p) > compatScore)
         {  mostCompatibleSoFar = this.persons[i];
            compatScore = mostCompatibleSoFar.getCompatibility(p);
         }
      }
      return mostCompatibleSoFar;
   }
	
   /** Find the oldest person in the list. 
    * (Assumes there is at least one person in the array. */
   public Person findFirstBirthday()
   {  Person oldestSoFar = this.persons[0];	// role: most-wanted holder
      DateTime oldestBirthdate = oldestSoFar.getBirthdate();	// role: follower
		
      for (int i=1; i<this.persons.length; i++)
      {  Person currentPerson = this.persons[i];	// role: temporary
         DateTime currentBirthdate = currentPerson.getBirthdate();	// role: follower
         if (currentBirthdate.isBefore(oldestBirthdate))
         {  oldestSoFar = currentPerson;
            oldestBirthdate = currentBirthdate;
         }
      }
      return oldestSoFar;
   }
		
   /** List all the bigs and littles on System.out. */
   public void listAll()
   {  for (int i=0; i<this.persons.length; i++)
      {  System.out.println(this.persons[i].toString());
      }	
   }
	
   /** List, on System.out, all the compatible persons.
    * @param p The person to be compatible with
    * @param minCompatibility The minimum acceptable compatibility score */
   public void listCompatible(Person p, double minCompatibility)
   {  for (int i = 0; i<this.persons.length; i++)
      {  Person other = this.persons[i];
         if (other.getCompatibility(p) > minCompatibility)
         {  System.out.println(other.getCompatibility(p) + "  " + other);
         } 
      }
   }
	
   /** Write the entire list of bigs and littles to a file.
    * @param out The already open output file. */
   public void write(PrintWriter out)
   {  for (int i = 0; i<this.persons.length; i++)
      {  this.persons[i].write(out);
      }
   }
	
   /** Count the number of people of the given characteristics. */
   public int count(Gender g)
   {  int count = 0;
      for (int i=0; i<this.persons.length; i++)
      {  if (this.persons[i].getGender() == g)
         {  count++;
         }
      }
      return count;
   }
	
   /** Sort the list of persons in alphabetical order by name. */
   
   /*
    public void sort()
    {	for(int firstUnsorted=0; firstUnsorted<this.persons.length-1; firstUnsorted++)
    {	// find min in unsorted part of the list
    int min = firstUnsorted;
    for(int i=firstUnsorted+1; i<this.persons.length; i++)
    {	if (this.persons[i].getName().compareTo(this.persons[min].getName()) < 0)
    {	min = i;
    }
    }
    
    // swap
    Person temp = this.persons[firstUnsorted];
    this.persons[firstUnsorted] = this.persons[min];
    this.persons[min] = temp;
    }
    }
    */
	
   /** Sort the list of persons according to a caller-specified criteria.
    * @param comparator An object implementing the Comparator interface
    *    that is used to define the ordering of the person objects */
    /*
   public void sort(Comparator<Person> comparator)
   {  for (int firstUnsorted=0; firstUnsorted<this.persons.length-1; firstUnsorted++)
      {  // find min in unsorted part of the list
         int min = firstUnsorted;
         for (int i=firstUnsorted+1; i<this.persons.length; i++)
         {  if (comparator.compare(this.persons[i], this.persons[min]) < 0)
            {  min = i;
            }
         }
			
         // swap
         Person temp = this.persons[firstUnsorted];
         this.persons[firstUnsorted] = this.persons[min];
         this.persons[min] = temp;
      }
   }
	*/
	
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
      Test.ckEquals("numPersons", 6, bbbs.persons.length);
      Test.ckEquals("name of 1st person", "Kenneth A Parsons",
            bbbs.persons[0].getName());
            
      Person[] maleLittles = bbbs.extractSubset(Gender.MALE, Role.LITTLE);
      Test.ckEquals("size of subset", 2, maleLittles.length);
      Test.ckEquals("name 1", "Kenneth A Parsons", maleLittles[0].getName());
      Test.ckEquals("name 2", "Roydyn A. Clayton", maleLittles[1].getName());
   }
}
