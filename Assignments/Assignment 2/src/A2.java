import becker.robots.*;
import java.util.Random;

/**
 * 
 * @author Jean Applys Cherizol and Andrew Li CS1A 2020 Spring Assignment 2
 */
public class A2 extends Robot
{
    // construct new type of robot named A2
    public A2(City city, int st, int ave, Direction dir, int num)
    {
        super(city, st, ave, dir, num);
    }

    // method for A2 to turn right
    public void turnRight()
    {
        this.turnLeft();
        this.turnLeft();
        this.turnLeft();
    }

    // method for A2 to turn around 180 degrees
    public void turnAround()
    {
        this.turnLeft();
        this.turnLeft();
    }

    // method for A2 to continue moving forward until there is a wall directly
    // in front of the robot
    public void moveToWall()
    {
        while (this.frontIsClear())
        {
            this.move();
        }

    }

    // method for A2 to return to the starting point of the city and face in the
    // same direction
    public void returnToStart()
    {
        this.turnAround();
        this.moveToWall();
        this.turnRight();
        this.moveToWall();
        this.turnRight();
    }

    // method for A2 to do pick up each Thing at the end of each tunnel, put them in
    // the last
    // tunnel, and return back to the starting point
    public void doEverything()
    {
        int counter = 0;
        while (counter < 4)
        {
            this.moveToWall();
            this.pickThing();
            this.turnAround();
            this.moveToWall();
            this.turnLeft();
            this.move();
            this.turnLeft();
            counter++;

        }

        while (this.countThingsInBackpack() > 0)
        {
            this.move();
            this.putThing();
        }

        this.returnToStart();

    }

    public static void main(String[] args)
    {

        City wallville = new City(6, 12);
        A2 bob = new A2(wallville, 1, 2, Direction.EAST, 0);

        A2.BuildCity(wallville); // this calls the "BuildCity" method, below

        // TODO: * * * Your code to race around the race track goes here! * * *
        // HINT: Several while loops would work well for this particular exercise.
        // One possible solution would be to start with a while loop (for checking the
        // count)
        // that contains two nested while loops used to check whether the front is clear
        // before performing some actions. Jumping through this initial while loop might
        // use additional while loops to continue checking whether the front is clear
        // before performing other actions in order to complete the tasks of the maze.

        bob.doEverything();

    }

    /////////////////////////////////////////////////////////////////////////////////////////
    // No need to touch any of the code below.
    // All it does is construct the maze in the city.
    /////////////////////////////////////////////////////////////////////////////////////////
    public static void BuildCity(City wallville)
    {
        // Width and height must be at least 2 (each)
        // Feel free to change these numbers, and see how your racetrack changes

        Random randomNumberGenerator = new Random();
        int top = 1;
        int left = 2;
        int height = 4;
        int width = 7 + randomNumberGenerator.nextInt(4);

        int streetNumber = top;
        while (streetNumber <= height)
        {
            if (streetNumber == 1)
            {
                // the topmost line:
                new Wall(wallville, streetNumber, left, Direction.NORTH);
            } else if (streetNumber == height)
            {
                // generate the 'holding spot' thing at the bottom:

                // the corner:
                new Wall(wallville, streetNumber + 1, left, Direction.WEST);
                new Wall(wallville, streetNumber + 1, left, Direction.SOUTH);
                int spotNum = left + 1;
                int counter = 0;
                while (counter < height)
                {
                    new Wall(wallville, streetNumber + 1, spotNum, Direction.NORTH);
                    new Wall(wallville, streetNumber + 1, spotNum, Direction.SOUTH);
                    // Uncomment the next line for a 'final state' picture (i.e., the second picture
                    // in the assignment)
                    // new Thing(wallville, streetNumber + 1, spotNum);
                    ++spotNum;
                    ++counter;
                }
                new Wall(wallville, streetNumber + 1, spotNum, Direction.WEST);
            }

            // the most western, vertical line:
            new Wall(wallville, streetNumber, left, Direction.WEST);
            // the most eastern, vertical line:
            new Wall(wallville, streetNumber, width, Direction.EAST);
            // the Thing at the end of the tunnel
            new Thing(wallville, streetNumber, width);

            int aveNum = left + 1;
            while (aveNum <= width)
            {
                new Wall(wallville, streetNumber, aveNum, Direction.NORTH);
                new Wall(wallville, streetNumber, aveNum, Direction.SOUTH);
                ++aveNum;
            }

            ++streetNumber;
        }
    }
}
