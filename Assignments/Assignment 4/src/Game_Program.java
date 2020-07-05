import java.util.Scanner;

/**
 * CS1A, Assignment 4, "Guessing Game" <br>
 * Quarter: Spring 2020<br>
 * The Game_Program class includes the main method, which is where
 * playGuessingGame() method is called. <br>
 * In the main method, there are multiple loops that keep the game going
 * continuously until the user enters 0 <br>
 * to quit the game. When the user chooses to exit the game, the program is
 * terminated using <br>
 * System.exit( int status ), and the JVM closes.
 * 
 * @author Andrew L.
 * @author Evan P.
 */
public class Game_Program extends Object
{
    public static void main(String[] args)
    {
        GuessingGame gg = new GuessingGame(); // feel free to add arguments to the constructor, if it helps...        
        boolean playing = true;

        gg.printRules();
        Scanner scan = new Scanner(System.in);
        while (playing)
        {
            // playing guess game
            while (gg.isPlaying())
            {
                gg.playGuessingGame();
            }

            // play next game
            System.out.println("1) Enter '1' to play again.\n" + "2) Enter '0' to quit.");
            if (scan.hasNextInt())
            {
                int yesOrNo = scan.nextInt();
                if (yesOrNo == 0)
                {
                    System.out.println("Exiting game... ");
                    
                    playing = false;
                } else if (yesOrNo == 1)
                {
                    System.out.println("Play again!");
                    gg.printRules();
                    gg.resetAll();
                } else
                {
                    System.out.println("Please enter a valid choice.");
                }

            }
        }
    }
}
