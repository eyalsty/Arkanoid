package levels;

import interfaces.BlockCreator;
import sprites.Block;

import java.util.Map;

/**
 * Author:Eyal Styskin.
 * the class keeps all the definitions of the symbols (with widths) and
 * all the blocks (According to its symbol
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * constructor.
     * @param spacerWidths - Map of spacers symbols and their widths
     * @param blockCreators - map of block symbols and their matching blocks
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths,
                                    Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }
    /**
     * returns true if 's' is a valid space symbol.
     * @param s - String to check for symbol
     * @return - true or false
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }
    /**
     * returns true if 's' is a valid block symbol.
     * @param s - String to check for symbol
     * @return - true or false
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }
    /**
     * Return a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos).
     * @param s - the symbol of the block
     * @param xpos - its x value
     * @param ypos - its y value
     * @return - new Block after creation
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }
    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     * @param s - symbol of spacer
     * @return - the spacer's width
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}
