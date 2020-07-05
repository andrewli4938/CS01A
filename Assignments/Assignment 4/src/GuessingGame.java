import java.util.Random;
import java.util.Scanner;

/**
 * CS1A, Assignment 4, "Guessing Game" <br>
 * Quarter: Spring 2020 <br>
 * GuessingGame class includes the methods that can be used in the main method
 * and in the "do it all" method<br>
 * which is called playGuessingGame(). playGuessingGame() uses most of the
 * methods in the GuessingGame class to run the game. <br>
 * Start by guessing 0. If the hint says greater, add 32. If it says less,
 * subtract 32. Kepp dividing these numbers by two. ie if your second guess's
 * hint is greater, you increase your number by 16. For example, if i was
 * following this stratagey i might start with 0, get greater and guess 32, get
 * greater and guess 48,get greater and guess 56, get greater and guess 60 get
 * less and guess 58, get less and guess 57. This strategy has a l00% sucess
 * rate, assuming you dont mess up
 * 
 * @author Andrew L.
 * @author Evan P.
 */
public class GuessingGame extends Object
{
    // all of the instance variables
    private static final int MAX_POSSIBLE_GUESS = 64; // This is a constant
    private static final int MIN_POSSIBLE_GUESS = -64;
    private int guessesLeft = 8;
    private int userGuess;
    private int index = 0;
    int winOrLose = 0;

    // Array used to hold all of the user guesses
    int[] guessArr = new int[8];

    Random randomNumberGenerator = new Random();

    /**
     * Generates a random number for the user to guess in the game.
     */
    public int getRandomNumber()
    {
        int max = MAX_POSSIBLE_GUESS - MIN_POSSIBLE_GUESS;
        int zeroToMax = randomNumberGenerator.nextInt(max + 1);
        return zeroToMax + MIN_POSSIBLE_GUESS;
    }

    private int secretNumber = this.getRandomNumber();

    /**
     * Prints the instructions for the user on how to play the game.
     */
    public void printRules()
    {
        System.out.println("Welcome!\nRules:");
        System.out.println("1.) You have 8 guesses");
        System.out.println("2.) If you guess the wrong number, a hint will be displayed");
        System.out.println("3.) Your guess must be between -64 and 64");
        System.out.println("Good luck!");
    }

    /**
     * Acts as a filter for what gets stored in the array, and also calls the method
     * that stores <br>
     * the user choice into the array.
     */
    public void isGuessNum()
    {
        if (winOrLose == 0)
        {
            System.out.println("\nEnter a whole number between -64 and 64");

            userGuess = this.getUserInput();
        }
        if (!this.isWithinRange(userGuess))
        {
            System.out.println("You did not enter a valid integer. Try again.");
        }
        if (userGuess != secretNumber)
        {
            if (!this.hasBeenGuessed())
            {
                if (userGuess != secretNumber)
                {
                    if (guessesLeft > 0)
                    {
                        this.storeArray();
                        ++index;
                    } else
                    {
                        this.userLose();
                    }
                }
            } else
            {
                System.out.println("This number has already been guessed. Try again.");
                ++guessesLeft;
            }

        }
        if (userGuess == secretNumber)
        {
            System.out.println("You win!");

            winOrLose = 2;
        }
    }

    /**
     * Prints out all of the user's previous guesses for reference
     */
    public void printPrevious()
    {
        System.out.print("Previous guess(es): ");
        for (int i = 0; i < 8 - guessesLeft; ++i)
        {
            System.out.print(guessArr[i] + ", ");

        }
        System.out.println();
    }

    /**
     * Simple method that stores the user's guess into a specific index in the
     * array. <br>
     * It is called in isGuessNum()
     */
    public void storeArray()
    {
        guessArr[index] = userGuess;
    }

    /**
     * This method will only return the user's entered value if it is within the
     * required range. <br>
     * Otherwise, the method will return 65, which can act as in indicator that the
     * number is out of the required range.
     */
    public int getUserInput()
    {
        Scanner keyboard = new Scanner(System.in);

        if (keyboard.hasNextInt())
        {

            int x = keyboard.nextInt();
            if (this.isWithinRange(x))
            {
                --guessesLeft;
                return x;
            } else if (x == secretNumber)
            {
                return x;
            } else if (guessesLeft == 0)
            {
                return x;
            } else
            {
                return 65;
            }

        } else
        {
            keyboard.nextLine();
            return 65;
        }

    }

    /**
     * Simple method that prints out the "You lose" menu, and also has an indicator
     * that the user has lost.
     */
    public void userLose()
    {
        System.out.println("You lose!");
        winOrLose = 1;

    }

    // You may want to create a boolean method to see if the number has been guessed
    // previously and will return true if it has
    /**
     * This function determines if the user has already guessed the number that was
     * entered.
     */
    public boolean hasBeenGuessed()
    {
        if ( userGuess == guessArr[ 0 ] )
            {
                return false;
            }
        for (int i = 0; i < 8 - guessesLeft; ++i)
        {
            
            if (userGuess == guessArr[i])
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Resets all of the instance variables to their original values, so in the main
     * method the program can run <br>
     * from the beginning if the user chooses to play again.
     */
    public void resetAll()
    {
        this.guessesLeft = 8;
        this.userGuess = 0;
        this.index = 0;
        this.winOrLose = 0;
        this.guessArr = new int[8];
    }

    /**
     * Determines if the number entered is within proper range.
     */
    public boolean isWithinRange(int num)
    {
        return num > -64 && num < 64;
    }

    /**
     * Gives the user a hint to how large the random number is.
     */
    public void giveHint()
    {
        if (userGuess < secretNumber)
        {
            System.out.println("Hint: The correct number is GREATER than your guess");
        } else
        {
            System.out.println("Hint: The correct number is LESS than your guess");

        }
    }

    /**
     * Prints the user's current guess.
     */
    public void printInput()
    {
        System.out.println("You guessed: " + userGuess);
    }

    /**
     * This function prints the number of guesses the player has left
     */
    public void printGuessesLeft()
    {
        System.out.println("You have " + guessesLeft + " guesses left.");
    }

    /**
     * This is a boolean to make sure that the user is still playing the game, and
     * has <br>
     * not lost or won the game yet. If the user wins or loses, isPlaying will
     * return false, <br>
     * and will return true if the user is still playing. <br>
     */
    public boolean isPlaying()
    {
        if (winOrLose == 0)
        {
            return true;
        }
        if (winOrLose == 1 || winOrLose == 2)
        {
            return false;
        }
        return false;
    }

    /**
     * This function coordinates most of the other functions and tells them when to
     * run
     * 
     */
    public void playGuessingGame()
    {
        // Feel free to move this into another method

        if (winOrLose == 0)

        {

            this.isGuessNum();

        }
        if (winOrLose == 0)

        {

            this.printInput();

            this.giveHint();

            this.printPrevious();

            this.printGuessesLeft();
        }
        if (winOrLose == 1)

        {

            return;

        }
        if (winOrLose == 2)

        {

            return;
        }
        // You might create a loop here that will check numberGuesses 8 times and print
        // the guess number and call the
        // pertinent method (e.g., isGuessNum)

        // You might call and capture results from method (e.g., playGame) to see if
        // user wants to play again and if
        // so return it. Example: int playAgain = this.playGame();

        return; // if you want to end the game early & go directly back to main,
                // you can use a "return;" statement like this one (e.g., return playAgain;)
    }

}
