/*
 * Air blocks:
 * - A block becomes air once it has been taken out of the tower
 * - An air block is just a regular block with 0 stability
 * - Extends the Block class, which contains all the methods that Air would use
 */

public class Air extends Block
{
    /**
     * An Air block that has 0 stability.
     * 
     * @param xcoord the x-coordinate of the Air block
     * @param ycoord the y-coordinate of the Air block
     */
    public Air(int xcoord, int ycoord)
    {
        super(xcoord, ycoord, true); // Calls the constructor in Block.java that initializes the Air block's stability to 0
    }
}
