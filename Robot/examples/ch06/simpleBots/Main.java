

/** A main method to test the SimpleBot and related classes.
 *
 * @author Byron Weber Becker */
public class Main extends Object
{
   public static void main(String[] args)
   {  SimpleCity newYork = new SimpleCity();
      SimpleBot karel = new SimpleBot();
      SimpleBot sue = new SimpleBot();
      
      newYork.add(karel, 2);
      newYork.add(sue, 2);
      
      newYork.waitForStart();    // Wait for the user to press the start button.

		for(int i=0; i<4; i = i+1)
      {	karel.move();
      	karel.move();
      	karel.turnLeft();
      }
      
      sue.move();
   }
}

