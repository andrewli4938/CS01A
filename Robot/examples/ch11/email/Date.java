import becker.util.DateTime;

/** Represent a date.  We're just concerned with dates, not time, so override
 * DateTime to focus on that.
 *
 * @author Byron Weber Becker */
public class Date extends DateTime
{
   /** Construct one date object corresponding to the given year/month/day. */
   public Date(int year, int month, int day)
   {  super(year, month, day);
   }

   /** Construct a date object corresponding to the date it was created. */
   public Date()
   {  super();
   }

   /** Determine whether two dates are equivalent. */
   public boolean isEquivalent(Date other)
   {  return (this.getDay() == other.getDay()
            && this.getMonth() == other.getMonth()
            && this.getYear() == other.getYear());
   }

   /** Return a numeric string corresponding to this date. */
   public String numeric()
   {  return  "" + this.getYear() + " " + this.getMonth() + " " + this.getDay();
   }
}
