
import java.io.*;
import java.util.Comparator;
import java.util.Arrays;

public class Main 
{
	public static void main(String[] args)
   {  
   	LineItem[] items = new LineItem[5];
   	items[0] = new GoodsLineItem(5, "computers", 1750.00);
   	items[1] = new ServiceLineItem(1, "internet connection", 5.95, 12);
   	items[2] = new ConsultingLineItem("hook up printer", 1, 75.00);
   	items[3] = new ConsultingLineItem("install network", 3.5, 75.00);
   	items[4] = new GoodsLineItem(1, "office suite", 500.00);
   	
   	
   	//sort(items, new LineItemDecreasingTotalCostComparator());
		// The following line may cause the compiler to give a warning.
      // Dealing with it is beyond the scope of this example.
     	Arrays.sort(items, new LineItemDecreasingTotalCostComparator());
   	   	
   	PrintWriter out = new PrintWriter(System.out);
   	for(int i=0; i<items.length; i++)
   	{	items[i].print(out);
   	}
   	out.flush();
   	

   }
   
   private static void sort(Object[] items, Comparator comp)
   {	for(int firstUnsorted = 0; firstUnsorted<items.length - 1; firstUnsorted++)
   	{	// find index of "smallest" item
   		int extremeIndex = firstUnsorted;
   		for(int i=firstUnsorted+1; i<items.length; i++)
   		{	// The following line may cause the compiler to give a warning.
         	// Dealing with it is beyond the scope of this example.
         	if (comp.compare(items[i], items[extremeIndex]) < 0)
   			{	extremeIndex = i;
   			}
   		}
   	
   	// swap the "smallest" item with the one at firstUnsorted
   	Object temp = items[extremeIndex];
   	items[extremeIndex] = items[firstUnsorted];
   	items[firstUnsorted] = temp;
   	}
   }
}


class LineItemDecreasingTotalCostComparator implements Comparator
{	
	public int compare(Object o1, Object o2)
	{	double cost1 = ((LineItem)o1).calculateAmount();
		double cost2 = ((LineItem)o2).calculateAmount();
		
		if (cost1 < cost2)
		{	return 1;
		} else if (cost1 > cost2)
		{	return -1;
		} else
		{	return 0;
		}
		
	}
}

class LineItemAlphaComparator implements Comparator
{	
	public int compare(Object o1, Object o2)
	{	LineItem l1 = (LineItem)o1;
		LineItem l2 = (LineItem)o2;
		
		return l1.getDescription().compareToIgnoreCase(l2.getDescription());
	}
}