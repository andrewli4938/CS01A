import becker.robots.*;

/** A class of robot which goes around squares.
 *
 *	@author Byron Weber Becker */
public class SquareMover extends Robot
{
	public SquareMover(City theCity, int str, int ave, Direction dir)
	{	super(theCity, str, ave, dir);
	}

   /** Move around a square by traversing each of its four sides. */
   public void moveAroundSquare()
   {  for(int side=0; side<4; side++)
      {  this.moveAlongSide();
      }
   }

   /** Move along one side of the square by moving 5 times. */
   protected void moveAlongSide()
   {  for(int moves=0; moves<5; moves++)
      {  this.move();
      }
      this.turnRight();
   }

   /** Turn right by turning left three times. */
   protected void turnRight()
   {  for(int turns=0; turns<3; turns++)
      {  this.turnLeft();
      }
   }
} 