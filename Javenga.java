import java.util.*;

/*
 * Project name: Javenga
 * Creator: Surya Duraivenkatesh
 * Version: 1, 05/13/2023
 * Description: Javenga is the popular game, Jenga, made in Java. It is created under a 2D interface. The unstable nature 
 * of a Jenga tower is demonstrated randomizing the stability of the blocks.
 * How to run:
 * javac Javenga.java
 * java Javenga
 */

/* Possible extensions: 
 * Create this game without the command-line format using Swing
 * Create difficulty levels and randomize the stability of the tower accordingly
 * Create a way for the player to play against the computer which uses the minimax algorithm
 * Create a way for the player to play with longer rows
 * One-time-use skips for each player *Code provides the pathway to easily implement this
 * Make it harder by not allowing people to pick from the top three rows
 * Make the x-axis customizable
 * Print warnings about tower stability using the getStability() method within the Tower class
 * Allow users to set the default height by using the enter key
 * Be more restrictive and traditional on the rules of the game
 */

public class Javenga 
{
    static final int defaultHeight = 18; // Default height in the original Jenga game, final since this shouldn't be changed
    static int playerCount, selectedHeight, finderTemp = 1; // finderTemp is a helper variable used in the getPlayerNameFromPlayerNumber method
    static HashMap<String, Boolean> players = new HashMap<>(); // HashMap with the player name, and whether or not their skip is used (using a HashMap for the players makes it easy to implement skips, if that extension is chosen)
    static Scanner scanner = new Scanner(System.in); 
    static String playerNameStorage; // playerNameStorage is a helper variable used in the getPlayerNameFromPlayerNumber method
    static Tower tower; // This is the tower where the main game is played in
    static int xinput, yinput; // xinput and yinput of the player(s) during the game

    /**
     * Prints the rules of Javenga.
     */
    public static void printRules()
    {
        System.out.println("Welcome to Javenga (Jenga in Java), player(s)! Here are the rules: ");
        System.out.println("1. The Goal: Don't be the one to let the tower collapse! The winner is the last person to successfully play before the collapse, and the loser is the one who let the tower collapse. ");
        System.out.println("2. The Differences: This is in 2D, so we can still have 18 (or how many rows you selected) rows like normal Jenga, but you'll only be pulling blocks from one side. Also, you can pick from the top three rows.");
        System.out.println("3. How To Move: Each time a player is prompted to move, the tower will be shown, and using the guides on the top and bottom of the tower, they will enter the x- and y- coordinates of the block they wish to remove. ");
        System.out.println("4. Tips: Rows 1 to 5 are more unstable than rows 6 to the selected height. Removing ANY row fully will cause the tower to collapse, including the very top one. Be careful! Have fun! ");
    }

    /**
     * Checks if an input is numerical. Helps with exception handling.
     * 
     * @param input the number to be validated
     * @return true if number is numerical, false elsewise
     */
    public static boolean isNumeric(String input)
    {
        int parsedInt;
        if (input == null) return false;
        try {
            parsedInt = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Helps enforce a numerical input.
     * 
     * @return a string that is guaranteed to be numerical
     */
    public static String numericEnforcer()
    {
        String x = scanner.nextLine();
        while (!isNumeric(x))
        {
            System.out.print("Please enter an integer! ");
            x = scanner.nextLine();
        }
        return x;
    }

    /**
     * Registers the number of players.
     */
    public static void registerNumberOfPlayers()
    {
        System.out.print("Hello, player(s)! How many of you are there? "); // in this version of Jenga, any number of players can play
        playerCount = Integer.valueOf(numericEnforcer()); 
        while (playerCount <= 0)
        {
            System.out.print("That's not right! Please enter a positive number: ");
            playerCount = Integer.valueOf(numericEnforcer());
        }
    }

    /**
     * Inputs the players into the HashMap players.
     */    
    public static void registerPlayers()
    {
        System.out.println("Time to get your names!"); 
        for (int i = 1; i <= playerCount; i++)
        {
            System.out.print("Player " + i + ", what is your name? ");
            players.put(scanner.nextLine(), true); // no exceptions to catch here, a person's name can be anything within the game
        }
    }

    /**
     * Registering the height that the player(s) would like to play with.
     */
    public static void registerHeight()
    {
        System.out.println("Now, player(s), it's time to select the height you'd like to play with! ");
        System.out.print("The default height is 18, and you can enter 0 to select this. Else, enter your custom, positive-numbered height. "); // any height that is positive is permitted in this version of Jenga
        selectedHeight = Integer.valueOf(numericEnforcer());
        while (selectedHeight < 0)
        {
            System.out.print("That's not right! Please enter a positive number, or 0 for the default height: ");
            selectedHeight = Integer.valueOf(numericEnforcer());
        }
        if (selectedHeight == 0) selectedHeight = defaultHeight;
    }

    /**
     * The entire introduction sequence.
     */
    public static void introduction()
    {
        registerNumberOfPlayers();
        System.out.println();
        registerPlayers();
        System.out.println();
        registerHeight();
        System.out.println();
        printRules();
        System.out.println();
        System.out.println("Ready to play? I sure am! We're gonna go player by player, starting with the first one.");
    }

    /**
     * Gets the name of the player from the player number, using the HashMap.
     * 
     * @param playerNumber the player number of the player's name that is to be returned
     * @return name associated with the inputted player number
     */
    public static String getPlayerNameFromPlayerNumber(int playerNumber)
    {
        players.forEach
        (
            (key, value) 
            -> 
            {
                if (finderTemp == playerNumber) playerNameStorage = key;
                finderTemp++;
            }
        );
        finderTemp = 1;
        return playerNameStorage;
    }

    /**
     * The sequence the plays when a player needs to move.
     * 
     * @param i the player number of the player that needs to move
     */
    public static void move(int i)
    {
        System.out.println();
        System.out.println("Time for " + getPlayerNameFromPlayerNumber(i) + "'s turn!");
        Tower.printTower(tower);
        System.out.print("What is the x-coordinate (1 to 3) of what you'd like to remove? ");  
        /**
         * The code is designed to check if the coordinate pair is valid, not individual coordinates, since
         * the coordinate validation is performed to check for out-of-grid coordinates and Air coordinates
         * at the same time using the removeBlock method within the Tower class.
         * */ 
        xinput = (Integer.valueOf(numericEnforcer()) - 1);
        System.out.print("What is the y-coordinate (1 to " + tower.getY() + ") of the block you'd like to remove? ");
        yinput = (Integer.valueOf(numericEnforcer()) - 1);

        while (!tower.removeBlock(xinput, yinput))
        {
            if ((xinput < 0) || (xinput > 2) || (yinput < 0) || (yinput > (selectedHeight - 1)))
            {
                System.out.println("Coordinates out of grid! Try again! ");
            }
            else
            {
                System.out.println("You tried to remove air. Try again!");
            }
            System.out.print("What is the x-coordinate (1 to 3) of what you'd like to remove? ");
            xinput = (Integer.valueOf(numericEnforcer()) - 1);
            System.out.print("What is the y-coordinate (1 to " + tower.getY() + ") of the block you'd like to remove? ");
            yinput = (Integer.valueOf(numericEnforcer()) - 1);
        }
    }

    /**
     * The entire end-game sequence once it is determined that the tower is collapsing.
     * 
     * @param i the current player at the time the end-game sequence is initiated
     */
    public static void endGame(int i)
    {
        Tower.printTower(tower);
        System.out.println();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Tower.towerCollapseAnimation();
        System.out.println("The tower collapsed!");
        if (playerCount == 1)
            System.out.println("You're the winner and the loser, " + getPlayerNameFromPlayerNumber(playerCount) + "!");
        else
        {
            if (i == 1)
                System.out.println("The winner of the game is: " + getPlayerNameFromPlayerNumber(playerCount));
            else
                System.out.println("The winner of the game is: " + getPlayerNameFromPlayerNumber(i - 1));
            System.out.println("The loser of the game is: " + getPlayerNameFromPlayerNumber(i));
        }
        System.out.println("Thanks for playing! ");
    }

    /**
     * The method where the game is played.
     */
    public static void game()
    {
        tower = new Tower(selectedHeight);
        while (true) // The game is set so that it must eventually collapse. Due to this, an infinite loop has been set so that the game will keep going till this point is reached.
        {
            for (int i = 1; i <= playerCount; i++)
            {
                move(i);
                if (tower.collapsing()) 
                {
                    endGame(i);
                    return;
                }
            }
        }

    }

    /**
     * The main method.
     * 
     * @param args[] not used in this program
     */
    public static void main(String args[]) // lots of helper methods help keep the introduction, game, and main methods clean
    {
        introduction();
        game();
        scanner.close();
    }
}
