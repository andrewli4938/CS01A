import java.io.*;
import java.util.Scanner;

public class ReadFileMain 
{
   public static void main(String[] args) throws FileNotFoundException
   {  Scanner in = new Scanner(new File("invoiceFile.txt"));
      PrintWriter out = new PrintWriter(System.out);
   
      Invoice inv = new Invoice(in);
      in.close();
      
      inv.print(out);
      out.close();
   }
}
