package interfaces;

import sprites.Block;

/**
 * Interface of BlockCreator type objects, they own the method create (in x and y position)
 * that creates new block. and being used as anonymous classes
 */
public interface BlockCreator {

    /**
     * Create a block at the specified location.
     * @param xpos - x value of top left point of block
     * @param ypos - y value of left top point of block
     * @return - new Block object
     */
    Block create(int xpos, int ypos);
}
