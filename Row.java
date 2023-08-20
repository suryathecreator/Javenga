/*
 * Rows:
 * - Two types of Rows: Rows that randomize the stabilities of the blocks within it when created and Rows with blocks that have pre-determined stabilities
 * - Extends the abstract Wood class, which contains the shared methods between Air, Block, Row, and Tower.
 */

import java.util.*;

public class Row extends Wood
{
    private double rowStability; // the stability of the Row, which is the sum of the stabilities of the blocks within the Row
    private int y; // the y-coordinate of the Row
    private List<Block> blocks = new ArrayList<Block>(); // the List containing the blocks within the Row

    /**
     * A Row with blocks that have randomized stabilities.
     * 
     * @param ycoord the y-coordinate of the Row
     */
    public Row(int ycoord)
    {
        this.blocks.add(new Block(0, ycoord));
        this.blocks.add(new Block(1, ycoord));
        this.blocks.add(new Block(2, ycoord));
        this.y = ycoord;
        this.rowStability = blocks.get(0).getStability() + blocks.get(1).getStability() + blocks.get(2).getStability();
    }

    /**
     * A Row with blocks that have custom stabilities.
     * 
     * @param left the left block
     * @param middle the middle block
     * @param right the right block
     * @param ycoord the y-coordinate of the Row
     */
    public Row(Block left, Block middle, Block right, int ycoord)
    {
        this.blocks.add(left);
        this.blocks.add(middle);
        this.blocks.add(right);
        this.y = ycoord;
        this.rowStability = left.getStability() + middle.getStability() + right.getStability();
    }

    /**
     * Getting the stability of the Row.
     * 
     * @return the stability of the Row, which is the stability of all the blocks within the Row summed up
     */
    @Override public double getStability() // Overrides the abstract method from the abstract Wood class
    {
        rowStability = blocks.get(0).getStability() + blocks.get(1).getStability() + blocks.get(2).getStability();
        return this.rowStability;
    }

    /**
     * Getting the y-coordinate of the Row.
     * 
     * @return the y-coordinate of the Row
     */
    @Override public int getY() // Overrides the abstract method from the abstract Wood class
    {
        return this.y;
    }

    /**
     * Getting the blocks in the row.
     * 
     * @return a List containing all the blocks within the Row
     */
    public List<Block> getBlocks()
    {
        return this.blocks;
    }

    /**
     * Removing a block from the Row. Sets the coordinate that is now blank equal to an Air block.
     * 
     * @param x the x-coordinate of the block that the user would like to remove
     * @param y the y-coordinate of the block that the user would like to remove
     * @return true if the removal was successful, false elsewise (implies that the coordinates inputted were out of the grid, or the user tried to remove air)
     */
    public boolean removeBlock(int x, int y)
    {
        for (int i = 0; i <=2; i++)
        {
            if ((x == blocks.get(i).getXCoord()) && (y == blocks.get(i).getY()))
            {
                if (blocks.get(i) instanceof Air)
                {
                    return false;
                }
                blocks.set(i, new Air(x, y));
                return true;
            }
        }
        return false;
    }
}
