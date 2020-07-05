
import java.util.Scanner;
import java.io.PrintWriter;
import becker.util.DateTime;
import java.text.NumberFormat;
import becker.util.Test;


/** Model a person participating in Big Brother/Big Sister.
 * @author Byron Weber Becker */
class Person extends Object
{  private String name;			// person's name
   private DateTime birthdate;// person's date of birth
   private Gender gender;		// one of Gender.{MALE, FEMALE}
   private Role role;		   // one of Role.{BIG, LITTLE}
   private String pairName = "";	// name of the person this person is paired with
									
   private double likesCrafts = 0.0;
   private double likesGames = 0.0;
   private double likesOutdoors = 0.0;
   private double likesSports = 0.0;
	
   /** Construct a person object using information found in a text file.
    * The format is the same as output by the write() method.
    * @param in an open input file. */
   public Person(Scanner in)
   {  super();
      this.name = in.nextLine();
      this.birthdate = new DateTime(in);
      this.gender = Gender.valueOf(Gender.class, in.next());
      this.role = Role.valueOf(Role.class, in.next());
      in.nextLine();
      
      this.likesCrafts = in.nextDouble();
      this.likesGames = in.nextDouble();
      this.likesOutdoors = in.nextDouble();
      this.likesSports = in.nextDouble();
      in.nextLine();
      
      this.pairName = in.nextLine();
   }
	
   /** @return This person's name */
   public String getName()
   {  return this.name;
   }
	
   /** @return This person's age in years */
   public int getAge()
   {  DateTime today = new DateTime();
      int yrs = today.getYear() - this.birthdate.getYear();
      if (today.getMonth() > this.birthdate.getMonth()
            || today.getMonth() == this.birthdate.getMonth()
                  && today.getDay() > this.birthdate.getDay())
      {  yrs--;
      }
      return yrs;
   }
	
   /** @return This person's birthdate */
   public DateTime getBirthdate()
   {  return this.birthdate;
   }
	
   /** @return This person's gender, one of Gender{Male, Female}. */
   public Gender getGender()
   {  return this.gender;
   }
	
   /** @return This person's role, one of Role.{BIG, LITTLE} */
   public Role getRole()
   {  return this.role;
   }
	
   /** @return The name of the person this person is paired with or the
    *  empty string if not yet paired. */
   public String getPairName()
   {  return this.pairName;
   }
	
   /** @return true if this person is paired with someone else; false otherwise */
   public boolean isPaired()
   {  return !this.pairName.equals("");
   }
	
   /** Pair this person with p.
    * @param p The person to pair this person with. */
   public void pairWith(Person p)
   {  this.pairName = p.name;
      p.pairName = this.name;
   }
	
   /** Get the compatibility score for this person and person p.  Returns 0.0
    * if either person is already paired, or if both are littles or bigs, or if
    * they have different genders.
    * @param p a potential pair for this person 
    * @return a number between 0.0 and 1.0 where 0.0 is not at all compatible
    *  and 1.0 is very compatible. */
   public double getCompatibility(Person p)
   {  if (this.isPaired() || p.isPaired() || this.role == p.role
            || this.gender != p.gender)
      {  return 0.0;
      }
      return (this.likesCrafts * p.likesCrafts + this.likesGames * p.likesGames
            + this.likesOutdoors * p.likesOutdoors
            + this.likesSports * p.likesSports)
            /4.0;
   }
	
   /** Represent this person as a string.
    * @return the representation as a string. */
   public String toString()
   {	
      String p = "Name: " + this.name + " (" + this.gender + ")\n"
            + "Birthdate: " + this.birthdate + " (" + this.role + ")\n";
				 
      if (this.isPaired())
      {  p = p + "Paired with: " + this.pairName + "\n";
      }
		
      p = p + this.likesCrafts + " " + this.likesGames + " "
            + this.likesOutdoors + " " + this.likesSports + "\n";
      return p;
   }
	
   /** Write this person to a file.
    * @param out The open output file. */
   public void write(PrintWriter out)
   {  out.println(this.name);
      out.println(this.birthdate.toString() + " " + this.gender + " "
            + this.role);
      NumberFormat nf = NumberFormat.getNumberInstance();
      nf.setMaximumFractionDigits(1);
      nf.setMinimumFractionDigits(1);
      out.println(nf.format(this.likesCrafts) + " " + nf.format(this.likesGames)
            + " " + nf.format(this.likesOutdoors) + " "
            + nf.format(this.likesSports));
      out.println(this.pairName);
   }
	
   public static void main(String[] args)
   {  System.out.println("Testing Person");
		
      Scanner in = new Scanner( "Kenneth A Parsons\n"
            + "1997/8/7 MALE LITTLE\n" + "0.9 0.3 0.6 0.5\n" + "\n"
            + "Beth A Reyburn\n" + "1993/8/27 FEMALE LITTLE\n"
            + "0.3 0.0 0.1 0.8\n" + "Kathleen A Waller\n"
            + "Kathleen A Waller\n" + "1979/5/4 FEMALE BIG\n"
            + "0.7 0.4 0.5 0.5\n" + "Beth A Reyburn\n");
      Person p0 = new Person(in);
      Test.ckIsNotNull("p0 not null", p0);
      Test.ckEquals("p0 name", "Kenneth A Parsons", p0.getName());
      Test.ckEquals("p0 birthdate", new DateTime(1997, 8, 7), p0.getBirthdate());
      Test.ckEquals("p0 gender", Gender.MALE, p0.getGender());
      Test.ckEquals("p0 role", Role.LITTLE, p0.getRole());
      Test.ckEquals("p0 is paired", false, p0.isPaired());
		
      Person p1 = new Person(in);
      Test.ckIsNotNull("p1 not null", p1);
      Test.ckEquals("p1 name", "Beth A Reyburn", p1.getName());
      Test.ckEquals("p1 birthdate", new DateTime(1993, 8, 27), p1.getBirthdate());
      Test.ckEquals("p1 gender", Gender.FEMALE, p1.getGender());
      Test.ckEquals("p1 role", Role.LITTLE, p1.getRole());
      Test.ckEquals("p1 is paired", true, p1.isPaired());
      Test.ckEquals("p1 pair", "Kathleen A Waller", p1.getPairName());
      Person p2 = new Person(in);
		
      Test.ckEquals("compatability -- wrong genders", 0.0,
            p0.getCompatibility(p1));
      Test.ckEquals("compatability -- already paired", 0.0,
            p1.getCompatibility(p2));
		
      // undo the pairing
      p1.pairName = "";
      p2.pairName = "";
      Test.ckEquals("compatability", 0.165, p1.getCompatibility(p2));
		
      // pair them again
      p1.pairWith(p2);
      Test.ckEquals("p1 is paired", true, p1.isPaired());
      Test.ckEquals("p1 pair", "Kathleen A Waller", p1.getPairName());
      Test.ckEquals("p2 pair", "Beth A Reyburn", p2.getPairName());
		
   }
}
