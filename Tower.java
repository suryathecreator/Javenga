/*
 * Towers:
 * - Two types of Towers: Towers that have the default Jenga height and Towers that have a custom height
 * - Extends the abstract Wood class, which contains the shared methods between Air, Block, Row, and Tower.
 */

import java.util.*;

public class Tower extends Wood // Print tower stability
{
    private int height; // the height of the Tower
    private double towerStability; // the stability of the Tower, which is the sum of the stabilities of the rows within the Tower
    private List<Row> rows = new ArrayList<Row>(); // a List containing all the rows within the Tower

    /**
     * Creating a new Tower with the default Jenga height of 18.
     */
    public Tower()
    {
        this.height = 18;
        for (int i = 0; i < 5; i++)
        {
            rows.add(new Row(i));
        }
        for (int i = 5; i < 18; i++)
        {
            rows.add(new Row(new Block(1 - Math.random()/2, 0, i), new Block(1 - Math.random()/2, 1, i), new Block(1 - Math.random()/2, 2, i), i));
        }
    }

    /**
     * Creating a new Ttower with a custom height.
     * 
     * @param height custom height of the Tower being created
     */
    public Tower(int height)
    {
        this.height = height;
        for (int i = 0; i < 5; i++)
        {
            rows.add(new Row(i));
        }
        for (int i = 5; i < height; i++)
        {
            rows.add(new Row(new Block(1 - Math.random()/2, 0, i), new Block(1 - Math.random()/2, 1, i), new Block(1 - Math.random()/2, 2, i), i));
        }
    }

    /**
     * Getting the height of the Tower.
     * 
     * @return the height of the Tower
     */
    @Override public int getY() // Overrides the abstract method from the abstract Wood class
    {
        return this.height;
    }

    /**
     * Getting a List that contains the rows within the Tower.
     * 
     * @return the List of rows within the Tower
     */
    public List<Row> getRows()
    {
        return this.rows;
    }

    /**
     * Getting the stability of the Tower, which is the sum of the stabilities of all the rows within the Tower.
     * 
     * @return the stability of the Tower
     */
    @Override public double getStability() // Overrides the abstract method from the abstract Wood class
    {
        for (Row r : rows)
        {
            towerStability += r.getStability();
        }
        return towerStability;
        /* implement: totals all stability in tower, in game maybe publish warning */
    }

    /**
     * Removes a block from the Tower.
     * 
     * @return true if the removal was successful, false elsewise
     */
    public boolean removeBlock(int x, int y)
    {
        if ((x < 0) || (x > 2) || (y < 0) || (y > (height - 1))) return false;
        if (rows.get(y).removeBlock(x, y)) return true;
        return false;
        /* return true if successful, return false if error of not having that in the grid or the block is already removed */
    }

    /**
     * Checks if the Tower is collapsing. Threshold for which aTtower is considered as "collapsing" can be modified.
     * 
     * @return true if any row crosses the "collapsing" threshold, false elsewise
     */
    public boolean collapsing()
    {
        for (Row r : rows)
        {
            if (r.getStability() <= 0.75)
                return true;
        }
        return false;
        /* implement, checks if tower will collapse */
    }

    /**
     * Prints the Tower.
     * 
     * @param toBePrinted the Tower that will be printed
     */
    public static void printTower(Tower toBePrinted) 
    {
        if (toBePrinted.getY() > 9)
        {
            for (int i = toBePrinted.getY(); i >= 10; i--)
            {
                System.out.print(i + " ");
                for (int j = 0; j <=2; j++)
                {
                    if (toBePrinted.getRows().get(i - 1).getBlocks().get(j) instanceof Air) System.out.print("  ");
                    else System.out.print("- ");
                }
                System.out.println();
            }
            for (int i = 9; i >= 1; i--)
            {
                System.out.print(i + "  ");
                for (int j = 0; j <=2; j++)
                {
                    if (toBePrinted.getRows().get(i - 1).getBlocks().get(j) instanceof Air) System.out.print("  ");
                    else System.out.print("- ");
                }
                System.out.println();
            }
        }
        else
        {
            for (int i = toBePrinted.getY(); i >= 1; i--)
            {
                System.out.print(i + " ");
                for (int j = 0; j <=2; j++)
                {
                    if (toBePrinted.getRows().get(i - 1).getBlocks().get(j) instanceof Air) System.out.print("  ");
                    else System.out.print("- ");
                }
                System.out.println();
            }
        }
        if (toBePrinted.getY() > 9) System.out.println("   1 2 3");
        else System.out.println("  1 2 3");

        /* implement, prints tower with labels for x / y axis, make sure to -1 input */ // FIX Default height to be enter, and stability.
    }

    /**
     * Basic animation for when the Tower is collapsing.
     */
    public static void towerCollapseAnimation()
    {
        System.out.println("- - - ");
        System.out.println("- - - ");
        System.out.println("- - - ");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" F a l l i n g . . . ");
        System.out.println("      ");
        System.out.println("      ");
        System.out.println("      ");
        System.out.println("      ");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("      ");
        System.out.println(" C o l l a p s i n g . . . ");
        System.out.println("      ");
        System.out.println("- - - ");
        System.out.println("- - - ");
        System.out.println("- - - ");
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("- - - ");
        System.out.println("- - - ");
        System.out.println("- - - ");
        System.out.println("      ");
        System.out.println("      ");
        System.out.println("      ");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" G a m e  o v e r . . . ");
        System.out.println(" |  | ");
        System.out.println("   0  ");
        System.out.println("  --  ");
        System.out.println("      ");
        System.out.println("      ");
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("- - - ");
        System.out.println("- - - ");
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("- - - - - - - - - - - - - - -");
        System.out.println("- - - - - - - - - - - - - - - - - -");
        System.out.println("      ");
        System.out.println("      ");
        System.out.println("      ");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" B l o c k s  a r e  f a l l i n g . . . ");
        System.out.println("      ");
        System.out.println("      ");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        /* implement */
    }
}
