import java.util.Scanner;
import java.io.*;

/** A command interpreter for the LogExplorer program.
 *
 * @author Byron Weber Becker */
public class CmdInterpreter extends Object implements LogExplorerView
{
   
   // NOTE:  This class is not expected to compile until appropriate changes
   // have been made as part of the Programming Projects.
   
   private LogExplorer model;
   
   
   /** Construct the command interpreter.
    * @param aModel The model for which this is the user interface. */
   public CmdInterpreter(LogExplorer aModel)
   {  super();
      this.model = aModel;
      this.model.addView(this);
   }
   

}
