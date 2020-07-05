import java.util.Comparator;

public class Utilities extends Object
{

   /*
    public static void sort(ICompare[] items)
    {	for(int firstUnsorted = 0; firstUnsorted<items.length - 1; firstUnsorted++)
    {	// find index of "smallest" item
    int extremeIndex = firstUnsorted;
    for(int i=firstUnsorted+1; i<items.length; i++)
    {	if (items[i].isLessThan(items[extremeIndex]))
    {	extremeIndex = i;
    }
    }
    
    // swap the "smallest" item with the one at firstUnsorted
    ICompare temp = items[extremeIndex];
    items[extremeIndex] = items[firstUnsorted];
    items[firstUnsorted] = temp;
    }
    }
    */
   
   /** Sort an array of comparable objects. */
   public static void sort(Comparable[] a)
   {  for (int firstUnsorted = 0; firstUnsorted < a.length - 1; firstUnsorted++)
      {  // find the index of extreme ("smallest") unsorted element
         int extremeIndex = firstUnsorted;
         for (int i = firstUnsorted + 1; i < a.length; i++)
         {  // The following line may cause the compiler to give a warning.
         	// Dealing with it is beyond the scope of this example.
         	if (a[i].compareTo(a[extremeIndex]) < 0)
            {  extremeIndex = i;
            }
         }
					
         // swap the extreme unsorted element with the element at firstUnsorted
         Comparable temp = a[extremeIndex];
         a[extremeIndex] = a[firstUnsorted];
         a[firstUnsorted] = temp;
      }
   }
   
   /** Sort an array of objects using a comparator. */
   public static void sort(Object[] a, Comparator c)
   {  for (int firstUnsorted = 0; firstUnsorted < a.length - 1; firstUnsorted++)
      {  // find the index of extreme ("smallest") unsorted element
         int extremeIndex = firstUnsorted;
         for (int i = firstUnsorted + 1; i < a.length; i++)
         {  // The following line may cause the compiler to give a warning.
         	// Dealing with it is beyond the scope of this example.
         	if (c.compare(a[i], a[extremeIndex]) < 0)
            {  extremeIndex = i;
            }
         }
					
         // swap the extreme unsorted element with the element at firstUnsorted
         Object temp = a[extremeIndex];
         a[extremeIndex] = a[firstUnsorted];
         a[firstUnsorted] = temp;
      }
   }

}
