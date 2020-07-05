import becker.util.IModel;
import becker.util.IView;
import java.util.ArrayList;
import becker.util.Test;


/** A class implementing a version of "Nim."  There is a (virtual) pile of
 * tokens.  Two players take turns removing 1, 2 or 3 tokens.  The player
 * who takes the last token wins the game.
 *
 * @author Byron Weber Becker */
public class NimModel extends Object implements IModel
{
   private ArrayList<IView> views = new ArrayList<IView>();

   // limit randomly generated pile sizes and how many tokens can be removed at once
   public static final int MIN_PILESIZE = 10;
   public static final int MAX_PILESIZE = 20;
   public static final int MAX_REMOVE = 3; 
   
   private int pileSize;
   private Player whoseTurn;
   private Player winner = Player.NOBODY;

   /** Construct a new instance of the game of Nim. */
   public NimModel()
   {  this(NimModel.random(MIN_PILESIZE, MAX_PILESIZE),
            NimModel.chooseRandomPlayer());
   }
   
   /** We need a way to create a non-random game for testing purposes. */
   private NimModel(int pileSize, Player next)
   {  super();
      this.pileSize = pileSize;
      this.whoseTurn = next;
   }
   
   /** Choose a player at random.
    * @returns @returns Player.RED or Player.BLACK with 50% probability for each  */
   private static Player chooseRandomPlayer()
   {  if (Math.random() < 0.5)
      {  return Player.RED;
      } else
      {  return Player.BLACK;
      }
   }
   
   /** Generate a random number between two bounds. */
   private static int random(int lower, int upper)
   {  return (int) (Math.random() * (upper - lower + 1)) + lower;
   }
   
   /** Get the current size of the pile. 
    * @returns the current size of the pile */
   public int getPileSize()
   {  return this.pileSize;
   }
   
   /** Get the next player to move.
    * @returns Either Player.RED or Player.BLACK if the game has not yet been won,
    *  or Player.NOBODY if the game has been won. */
   public Player getWhoseTurn()
   {  return this.whoseTurn;
   }
   
   /** Get the winner of the game.
    * @returns Either Player.RED or Player.BLACK if the game has already been won;
    *   Player.NOBODY if the game is still in progress. */
   public Player getWinner()
   {  return this.winner;
   }
   
   /** Is the game over?
    * @returns true if the game is over; false otherwise. */
   public boolean gameOver()
   {  return this.pileSize == 0;
   }
   
   /** Remove one, two or three tokens from the pile.  Ignores any attempts to
    * take too many or too few tokens.  Otherwise, remove howMany tokens from 
    * the pile and update whose turn is next.
    * @param howMany How many tokens to remove.
    * @throws IllegalStateException if the game has already been won */
   public void removeTokens(int howMany)
   {  if (this.gameOver())
      {  throw new IllegalStateException("The game has already been won.");
      }
      
      if (this.isLegalMove(howMany))
      {  this.pileSize = this.pileSize - howMany;
         if (this.gameOver())
         {  this.winner = this.whoseTurn;
            this.whoseTurn = Player.NOBODY;
         } else
         {  this.whoseTurn = NimModel.otherPlayer(this.whoseTurn);
         }
         this.updateAllViews();  
      }    
   }
   
   // is howMany a legal number of tokens to take?
   private boolean isLegalMove(int howMany)
   {  return howMany >= 1 && howMany <= MAX_REMOVE && howMany <= this.pileSize;
   }
   
   // return the other player
   private static Player otherPlayer(Player who)
   {  if (who == Player.RED)
      {  return Player.BLACK;
      } else
      {  assert who == Player.BLACK;
         return Player.RED;
      }
   }

   /** Add a view to display information about this model.
    * @param view The view to add. */
   public void addView(IView view)
   {  this.views.add(view);
   }
   
   /** Remove a view that has been displaying information about this model.
    * @param view The view to remove. */
   public void removeView(IView view)
   {  this.views.remove(view);
   }
   
   /** Inform all the views currently displaying information about this model
    * that the model has changed and their display may need changing too. */
   public void updateAllViews()
   {  for (IView view : this.views)
      {  view.updateView();
      }
   }
   
   /** Test the class. */
   public static void main(String[] args)
   {  System.out.println("Testing NimModel");
      NimModel nim = new NimModel(5, Player.RED);
      Test.ckEquals("pilesize", 5, nim.getPileSize());
      Test.ckEquals("winner", Player.NOBODY, nim.getWinner());
      Test.ckEquals("next", Player.RED, nim.getWhoseTurn());
      
      nim.removeTokens(3);
      Test.ckEquals("pilesize1", 2, nim.getPileSize());
      Test.ckEquals("winner1", Player.NOBODY, nim.getWinner());
      Test.ckEquals("next1", Player.BLACK, nim.getWhoseTurn());
      
      nim.removeTokens(1);
      Test.ckEquals("pilesize1", 1, nim.getPileSize());
      Test.ckEquals("winner1", Player.NOBODY, nim.getWinner());
      Test.ckEquals("next1", Player.RED, nim.getWhoseTurn());
      
      nim.removeTokens(1);
      Test.ckEquals("pilesize1", 0, nim.getPileSize());
      Test.ckEquals("winner1", Player.RED, nim.getWinner());
      Test.ckEquals("next1", Player.NOBODY, nim.getWhoseTurn());
      
      System.out.println("\nTesting randomness of constructor.");
      NimModel.testRandomInit(10000);
      
      System.out.println("\nTesting exceptions in removeTokens.");
      nim = new NimModel(2, Player.RED);
      try 
      {  nim.removeTokens(5);
         Test.fail("Expected IllegalArgumentException (remove more than max)");
      } catch (IllegalArgumentException ex) 
      {  Test.pass("Caught IllegalArgumentException (remove more than max)");
      }
      
      try 
      {  nim.removeTokens(3);
         Test.fail("Expected IllegalArgumentException (remove more than pile)");
      } catch (IllegalArgumentException ex) 
      {  Test.pass("Caught IllegalArgumentException (remove more than pile)");
      }
      nim.removeTokens(2); // win the game
      try 
      {  nim.removeTokens(1);
         Test.fail("Expected IllegalStateException (already won)");
      } catch (IllegalStateException ex) 
      {  Test.pass("Caught IllegalStateException (already won)");
      }
      
      System.out.println( "\nDone testing.  " + Test.getNumErrors()
            + " failures.");
   }
   
   /** A helper method to test the constructor for random initialization.
    * @param iter The number of iterations to use */
   private static void testRandomInit(final int iter)
   {  int[] size = new int[MAX_PILESIZE - MIN_PILESIZE + 1];
      int[] player = new int[2];
      
      for (int i = 0; i < iter; i++)
      {  NimModel nim = new NimModel();
         size[nim.getPileSize() - MIN_PILESIZE]++;
         player[nim.getWhoseTurn().ordinal()]++;
      }
      for (int i = MIN_PILESIZE; i <= MAX_PILESIZE; i++)
      {  Test.ckIsBetween("random pile size " + i, iter / size.length * 0.80,
               size[i - MIN_PILESIZE], iter / size.length * 1.20);
      }
      Test.ckIsBetween("red players", iter * 0.45, player[Player.RED.ordinal()],
            iter * 0.55);
      Test.ckIsBetween("black players", iter * 0.45,
            player[Player.BLACK.ordinal()], iter * 0.55);
   }

}
