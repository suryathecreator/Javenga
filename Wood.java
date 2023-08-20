/*
 * Wood:
 * - Wood is an abstract class which contains the shared methods between Air, Block, Row, and Tower.
 */

public abstract class Wood {
    /**
     * Returns the stability of the object in question.
     */
    protected abstract double getStability();

    /**
     * Returns the "Y" for the object in question. The definition of "Y" varies by object.
     */
    protected abstract int getY();
}
