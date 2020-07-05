
import becker.util.Test;

/** Represent a person.
 *
 * @author Byron Weber Becker */
public class Person extends Object
{	
	private String name;
	private Gender gender;
	
	/** Construct a person. */
	public Person(String aName, Gender aGender)
	{	super();
		this.name = aName;
		this.gender = aGender;
	}
	
	/** Get this person's gender. */
	public Gender getGender()
	{	return this.gender;
	}
	
	// Test the Person class
   public static void main(String[] args)
   {	Person juan = new Person("Juan", Gender.MALE);
   	Test.ckEquals("gender", Gender.MALE, juan.getGender());
   	
   	// The above line uses the more correct form, Test.ckEquals, rather than creating a 
   	// Test object.  See section 7.5.
   }	
}
