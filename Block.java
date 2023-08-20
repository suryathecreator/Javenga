/*
 * Blocks:
 * - Two types of Blocks: Blocks that randomize their stability when created and Blocks with a given initial stability
 * - Third Block constructor is for the Air class
 * - Extends the abstract Wood class, which contains the shared methods between Air, Block, Row, and Tower.
 */

public class Block extends Wood
{
    private double blockStability; // the stability of the Block
    private int x, y; // the positional coordinates of the Block
    
    /**
     * A Block with a randomized stability.
     * 
     * @param xcoord the x-coordinate of the Block
     * @param ycoord the y-coordinate of the Block
     */
    public Block(int xcoord, int ycoord)
    {
        this.blockStability = Math.random();
        this.x = xcoord;
        this.y = ycoord;
    }

    /**
     * A Block with a custom stability.
     * 
     * @param bstability the stability of the Block
     * @param xcoord the x-coordinate of the Block
     * @param ycoord the y-coordinate of the Block
     */
    public Block(double bstability, int xcoord, int ycoord) 
    {
        this.blockStability = bstability;
        this.x = xcoord;
        this.y = ycoord;
    }

    /**
     * An Air block with no stability.
     * @param xcoord the x-coordinate of the Air block
     * @param ycoord the y-coordinate of the Air block
     * @param isAir not used, but the presence of the boolean parameter indicates that an Air block needs to be constructed
     */
    public Block(int xcoord, int ycoord, boolean isAir)
    {
        this.blockStability = 0;
        this.x = xcoord;
        this.y = ycoord;
    }

    /**
     * Getting the stability of the Block.
     * 
     * 
     * @return the stability of the Block
     */
    @Override public double getStability() // Overrides the abstract method from the abstract Wood class
    {
        return this.blockStability;
    }

    /**
     * Getting the x-coordinate of the Block. 
     * 
     * @return the x-coordinate of the Block
     */
    public int getXCoord()
    {
        return this.x;
    }

    /**
     * Getting the y-coordinate of the Block.
     * 
     * @return the y-coordinate of the Block
     */
    @Override public int getY() // Overrides the abstract method from the abstract Wood class
    {
        return this.y;
    }
}
