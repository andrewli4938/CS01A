
import java.util.Scanner;


/** Test the performance of inserting into a partially filled array vs.
 * inserting into an array that is repeatedly grown by 1 vs. an array that
 * is doubled when full.
 * Use integers so we have as little non-array overhead as possible.
 *
 * @author Byron Weber Becker */
public class TestInsertions extends Object
{  private static final int GROUP_SIZE = 10000;
   private Scanner in = new Scanner(System.in);
   
   /* Test inserting into a partially filled array.  num*GROUP_SIZE insertions
    * are made, reporting the time after each GROUP_SIZE insertions. */
   private void testPFA(int num)
   {
      long pfaStart = System.currentTimeMillis();
      int[] pfa = new int[num*GROUP_SIZE];
      int size = 0;
      for (int n=0; n<num; n++)
      {  for (int i=0; i<GROUP_SIZE; i++)
         {  pfa[size] = i;
            size++;
         }
         
         System.out.println("PFA: " + size + ":  "
               + (System.currentTimeMillis() - pfaStart)/1000.0);
      }   	
   }
	
   /* Test inserting into an array that grows by one each time.  num*GROUP_SIZE insertions
    * are made, reporting the time after each GROUP_SIZE insertions. */
   private void testGrowByOne(int num)
   {
      long incStart = System.currentTimeMillis();
      int[] inc = new int[0];
      int s = 0;			// size of the array
      for (int n=0; n<num; n++)
      {  for (int i=0; i<GROUP_SIZE; i++)
         {  int[] larger = new int[s + 1];
         	
            for (int j=0; j<s; j++)
            {  larger[j] = inc[j];
            }
            inc = larger;
            inc[s] = i;
            s++;
         }
         
         System.out.println("INC: " + s + ":  "
               + (System.currentTimeMillis() - incStart)/1000.0);
      }   	
   }
	
   /* Test inserting into a partially filled array that grows by doubling.  
    * num*GROUP_SIZE insertions
    * are made, reporting the time after each GROUP_SIZE insertions. */
   private void testGrowPFA(int num)
   {
   	long pfaStart = System.currentTimeMillis();
      int[] pfa = new int[1];
      int size = 0;
      for (int n=0; n<num; n++)
      {  for (int i=0; i<GROUP_SIZE; i++)
         {  if (size == pfa.length)
         	{	// enlarge it
         		int[] larger = new int[pfa.length * 2];
         		for(int j=0; j<pfa.length; j++)
         		{	larger[j] = pfa[j];
         		}
         		pfa = larger;
         	}
         	pfa[size] = i;
            size++;
         }
         
         System.out.println("G_PFA: " + size + ":  "
               + (System.currentTimeMillis() - pfaStart)/1000.0);
      }   	

   }
	
   private int getNum()
   {  System.out.print("Number of insertions (in " + GROUP_SIZE + "'s): ");
      return this.in.nextInt();

   }

   public static void main(String[] args)
   {  
   	
      TestInsertions t = new TestInsertions();
      int num = t.getNum();
     	
      t.testPFA(num);
      System.out.println ("\n\n");
      t.testGrowPFA(num);
      System.out.println ("\n\n");
      t.testGrowByOne(num);

   }
}
