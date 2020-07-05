import becker.robots.*;

/**
 * CS1A - Assignment 3 - "The Maze" <br>
 * Quarter: Spring 2020 <br>
 * <br>
 * MazeBot class contains all of the methods 
 * that are used in the main method. <br>
 * 
 * The navigateMaze function uses methods that are <br>
 * designed to follow the right side wall. <br>
 * 
 * 
 * @author Anna Zhavoronkova
 * @author Andrew Li
 */
class MazeBot extends RobotSE
{
    
    private int totalMoves = 0; // holds the value of the amount of total moves made
    private int movesWest = 0; // holds the value of moves West
    private int movesEast = 0; // holds the value of moves East
    private int movesSouth = 0; // holds the value of moves South
    private int movesNorth = 0; // holds the value of moves North
    private int thingsPut = 0; // holds the value of things put in total

    public MazeBot(City theCity, int str, int ave, Direction dir, int numThings)
    {
        super(theCity, str, ave, dir, numThings);
    }

    // Keeps track of moves in each direction by incrementing instance variables each move
    private void countDirection()
    {
        if (this.getDirection() == Direction.EAST)
        {
            ++movesEast;
        } else if (this.getDirection() == Direction.WEST)
        {
            ++movesWest;
        } else if (this.getDirection() == Direction.SOUTH)
        {
            ++movesSouth;
        } else
        {
            ++movesNorth;
        }
    }

    /**
     * Method overrides move() method in superclass. MazeBot move() method only
     * moves if the front is clear, to prevent crashes, and also puts a Thing before
     * moving intersections. Keeps track of the moves made in each direction as well as total
     * moves.
     */
    public void move()
    {
        if (this.frontIsClear())
        {
            this.putThing();

            super.move();

            ++totalMoves;
            this.countDirection();
        }
    }

    // Following methods print the number of moves in each direction
    private void printNumSpacesTotal()
    {
        System.out.println("Total number of moves TOTAL: " + totalMoves);
    }

    private void printNumSpacesEast()
    {
        System.out.println("Total number of moves EAST: " + movesEast);
    }

    private void printNumSpacesWest()
    {
        System.out.println("Total number of moves WEST: " + movesWest);
    }

    private void printNumSpacesSouth()
    {
        System.out.println("Total number of moves SOUTH: " + movesSouth);
    }

    private void printNumSpacesNorth()
    {
        System.out.println("Total number of moves NORTH: " + movesNorth);
    }

    private void printNumThingsPut()
    {
        System.out.println("Total number of Things put down: " + thingsPut);
    }

    /**
     * This method overrides putThing() method in superclass. Only puts a Thing down
     * if MazeRobot has Thing(s) in its backpack. Also increments thingsPut instance
     * variable that keeps track of total things put down.
     */
    public void putThing()
    {
        if (!this.canPickThing())
        {
            if (this.countThingsInBackpack() > 0)
            {
                super.putThing();
                ++thingsPut;
            }
        }
    }

    // turns right and checks if there is a wall on the right side. If there is a wall, 
    // rightIsClear() will return false, otherwise, it will return true. 
    private boolean rightIsClear()
    {
        this.turnRight();
        return this.frontIsClear();
    }

    // turns left and checks if there is a wall on the left side. If there is a wall, 
    // left IsClear() will return false, otherwise, it will return true. 
    private boolean leftIsClear()
    {
        this.turnLeft();
        return this.frontIsClear();
    }  
    
    // Moves along the right side wall by continuously checks if each
    // intersection / side is clear
    private void nestedCheckIntersection()
    {
        if (!this.rightIsClear())
        {
            this.turnLeft();

            if (!this.frontIsClear())
            {
                if (!this.leftIsClear())
                {
                    this.turnLeft();
                    this.move();

                } else
                {
                    this.move();
                }

            } else
            {
                this.move();
            }

        } else
        {
            this.move();
        }
    }

    /**
     * This method is used to print values of each instance variable.
     */
    public void printEverything()// Or printTotalNumberOfSpacesMoved(),
    // whichever you decide
    {
        this.printNumSpacesNorth();
        this.printNumSpacesEast();
        this.printNumSpacesSouth();
        this.printNumSpacesWest();
        this.printNumSpacesTotal();
        this.printNumThingsPut();
    }

    // The isAtEndSpot() method below is what's called a 'helper method' It
    // exists just to make another command (in this case, NavigateMaze) easier
    // to understand. It does this by replacing some code that otherwise would
    // be in NavigateMaze with it's name, and doing that work here, instead.
    // Declaring it "private" means that only the MazeBot is allowed to call
    // upon it.
    private boolean isAtEndSpot()
    {
        return getAvenue() == 9 && getStreet() == 10;
    }

    
    // THIS IS THE ONE MAIN METHOD WILL USE TO DO EVERYTHING (ALTHOUGH IT CAN USE
    // OTHER METHODS LIKE isAtEndSpot, ETC)
    /**
     * This method is created to be called from the main method to navigate the maze.
     * It includes a while loop that keeps prompting the robot to navigate through
     * the maze until the robot reaches the end location. After the checking statement 
     * inside the while loop returns false, navigateMaze() prints out the amount of moves 
     * made in each direction, moves made total, and things put down by the robot.
     */
    public void navigateMaze()
    {
        // While your robot hasn't yet reached the 'end spot', keep navigating
        // through the Maze and doing its thing
        while (!isAtEndSpot())
        {
            // TODO: The robot will navigate the maze until it reaches the end spot.
            // What will you have the robot do at each step?
            this.nestedCheckIntersection();
        }

        // TODO: After completing Maze, print total number of spaces moved and how
        // many times robot moved East, South, West, North.
        this.printEverything();
    }

}
