/** Set up the LogExplorer program.
 *
 * @author Byron Weber Becker */
public class Main
{
   
   public static void main(String[] args)
   {  LogExplorer explorer = new LogExplorer();
   
      if (Prompt.forBoolean("Graphical interface [true, false]: "))
      {  //GraphicalUI ui = new GraphicalUI(explorer);
         System.out.println("Graphical view needs uncommenting in Main.");
      } else
      {
         //CmdInterpreter cmd = new CmdInterpreter(explorer);
         //cmd.cmdInterpreter();
         explorer.cmdInterpreter();
      }
   }
}
