import becker.util.Test;
import becker.util.DateTime;


/** Represent a person.
 *
 * @author Byron Weber Becker */
public class Person extends Object
{
   private String name;								// person's name
   private String mother;							// person's mother's name
   private String father;							// person's father's name
   private DateTime birth;							// birth date
   private DateTime death = null;				// death date (null if still alive)
		
   /** Represent a person who is still alive. */
   public Person(String aName, String mom, String dad, int bYear, int bMonth, int bDay)
   {  this(aName, mom, dad, bYear, bMonth, bDay, 0, 0, 0);
   }	
   
   /** Represent a person who has died. */
   public Person(String aName, String mom, String dad, 
         int bYear, int bMonth, int bDay, int dYear, int dMonth, int dDay)
   {  super();
      this.name = aName;
      this.mother = mom;
      this.father = dad;
      
      this.birth = new DateTime(bYear, bMonth, bDay);
      if (dYear > 0)
      {  this.death = new DateTime(dYear, dMonth, dDay);
      }
   }	
   
   public int daysLived()
   {  DateTime endDate = this.death;
      if (this.death == null)
      {  endDate = new DateTime();
      } 
      return this.birth.daysUntil(endDate);
   }

   public String getFather()
   {  return this.father;
   }
   
   public String getMother()
   {  return this.mother;
   }
   
   public String getName()
   {  return this.name;
   }
   
   /** Set the death date to a new value. */
   public void setDeathDate(int dYear, int dMonth, int dDay)
   {  this.death = new DateTime(dYear, dMonth, dDay);
   }
   
   public int daysSinceDeath()
   {  DateTime today = new DateTime();
      return (int) this.death.daysUntil(today);
   }
   
   // Test the class.
   public static void main(String[] args)
   {  Person p = new Person("Joseph Becker", "Jacob B. Becker",
            "Elizabeth Unruh", 1900, 6, 14);
            
      p.setDeathDate(1901, 6, 14);
      Test.ckEquals("exactly 1 year", 365, p.daysLived());
      p.setDeathDate(1901, 6, 13);
      Test.ckEquals("1 year less a day", 364, p.daysLived());
      p.setDeathDate(1902, 6, 15);
      Test.ckEquals("2 years plus a day", 2*365 + 1, p.daysLived());
      
      // not dead yet
      DateTime yesterday = new DateTime();
      yesterday.addDays(-1);
      p = new Person("Joseph Becker", "Jacob B. Becker", "Elizabeth Unruh",
            yesterday.getYear(), yesterday.getMonth(), yesterday.getDay());
      Test.ckEquals("Not dead yet", 1, p.daysLived());
      
      System.out.print("Days since death (expect exception):");
      System.out.println(p.daysSinceDeath());
   }
}
