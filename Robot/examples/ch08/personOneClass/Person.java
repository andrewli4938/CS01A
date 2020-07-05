import becker.util.Test;
import becker.util.DateTime;

public class Person extends Object
{
   private String name;								// person's name
   private String mother;							// person's mother's name
   private String father;							// person's father's name
   private int birthYr, birthMth, birthDay;	// birth date
   private int deathYr, deathMth, deathDay;	// death date (0's if still alive)
		
   /** Construct a new person with the given information. */
   public Person(String aName, String mom, String dad, int bYear, int bMonth, int bDay)
   {  this(aName, mom, dad, bYear, bMonth, bDay, 0, 0, 0);
   }	
		
   /** Construct a new person with the given information. */
   public Person(String aName, String mom, String dad, 
   					int bYear, int bMonth, int bDay, int dYear, int dMonth, int dDay)
   {  super();
      this.name = aName;
      this.mother = mom;
      this.father = dad;
      
      this.birthYr = bYear;
      this.birthMth = bMonth;
      this.birthDay = bDay;
   	   	
      this.deathYr = dYear;
      this.deathMth = dMonth;
      this.deathDay = dDay;
   }	
   
   public int daysLived()
   {  DateTime birth = new DateTime(this.birthYr, this.birthMth, this.birthDay);
   	DateTime death = new DateTime(this.deathYr, this.deathMth, this.deathDay);
   	return (int)birth.daysUntil(death);
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
   
   public void setDeathDate(int dYear, int dMonth, int dDay)
   {  this.deathYr = dYear;
      this.deathMth = dMonth;
      this.deathDay = dDay;
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
   }
}
