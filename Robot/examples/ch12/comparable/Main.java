import java.io.*;
import java.util.Comparator;


public class Main
{
	public static void main(String[] args)
   {  LineItem[] items = new LineItem[6];
      items[0] = new GoodsLineItem(3, "Office suite", 500.00);
      items[5] = new GoodsLineItem(3, "Computers", 1750.00);
      items[1] = new ServiceLineItem(1, "Internet connection", 5.95, 12);
      items[2] = new ConsultingLineItem("Hook up printer", 1, 75.00);
      items[3] = new ConsultingLineItem("Install network", 3.5, 75.00);
      items[4] = new GoodsLineItem(5, "Computers", 1750.00);
   	
      Utilities.sort(items);
      //java.util.Arrays.sort(items, new UnitCostComparator());
      
      // The following line may cause the compiler to give a warning.
      // Dealing with it is beyond the scope of this example.
      java.util.Arrays.sort(items, new LineItemAmountComparator());
   	   	
      PrintWriter out = new PrintWriter(System.out);
      for (int i = 0; i < items.length; i++)
      {  items[i].print(out);
      }
      out.flush();
   }
}

/** Compare two line items using the value calculated by calculateAmount. */
class LineItemAmountComparator extends Object implements Comparator
{	public int compare(Object obj1, Object obj2)
	{	double amt1 = ((LineItem)obj1).calculateAmount();
		double amt2 = ((LineItem)obj2).calculateAmount();
		if (amt1 < amt2)			{	return -1;} 
		else if (amt1 > amt2)	{	return 1; } 
		else							{	return 0; }
	}
}


class UnitCostComparator extends Object implements Comparator
{	public int compare(Object obj1, Object obj2)
	{	double uCost1 = ((LineItem)obj1).getUnitCost();
		double uCost2 = ((LineItem)obj2).getUnitCost();
		
		if (uCost1 < uCost2)			{	return -1;	}
		else if (uCost1 > uCost2)	{	return 1;	}
		else 								{	return 0;	} 
	}
}

class LineItemDescrTotalComparator extends Object implements Comparator
{	public int compare(Object obj1, Object obj2)
	{	LineItem li1 = (LineItem)obj1;
		LineItem li2 = (LineItem)obj2;
		
		int result = li1.getDescription().compareTo(li2.getDescription());
		if (result == 0)	// use secondary key
		{	double amt1 = li1.calculateAmount();
			double amt2 = li2.calculateAmount();
			if (amt1 < amt2)			// descending order
			{	result = 1;
			} else if (amt1 > amt2)	
			{	result = -1;		
			}
		} 
		return result;
	}
}
