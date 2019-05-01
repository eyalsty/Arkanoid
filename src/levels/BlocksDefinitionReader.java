package levels;

import geometry.Point;
import geometry.Rectangle;
import interfaces.BlockCreator;
import sprites.Block;

import java.awt.Color;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * .
 * Author: Eyal Styskin
 * in charge of reading a block-definitions file and returning a BlocksFromSymbolsFactory object
 */
public class BlocksDefinitionReader {

    /**
     * .
     * reading the file, analyzing the the blocks definition file and all the block's characteristics
     *
     * @param reader Reader linked to a file
     * @return - BlockSymbolsFactory of specific level
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        try {
            BufferedReader bufferedReader = new BufferedReader(reader);
            List<String> lines = new ArrayList<String>();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (line.equals("\n") || line.equals("")) {
                    continue;
                }
                lines.add(line);
            }

            int spacersIndex = SpecificLevel.findIndex(lines,
                    "spacers definitions");
            List<String> spacersList = lines.subList(spacersIndex + 1, lines.size());
            Map<String, Integer> spacers = BlocksDefinitionReader
                    .spacersMap(spacersList);

            List<String> blocksList = lines.subList(0, spacersIndex);
            Map<String, BlockCreator> blockCreators = BlocksDefinitionReader
                    .blocksMap(blocksList);

            BlocksFromSymbolsFactory symbolsFactory = new BlocksFromSymbolsFactory(spacers, blockCreators);
            return symbolsFactory;
        } catch (Exception e) {
            System.out.println("cant read from files");
        }
        return null;
    }

    /**
     * .
     * creating a map for keys: the blocks's symbols and the fitting BlockCreator
     *
     * @param lines - all the lines of the specific level without spacers definitions
     * @return Map of symbols and blockCreators
     */
    public static Map<String, BlockCreator> blocksMap(List<String> lines) {
        Map<String, BlockCreator> blockCreators = new TreeMap<String, BlockCreator>();
        ArrayList<String> defaults = new ArrayList<String>();
        try {
            for (int j = 0; j < lines.size(); ++j) {
                if (lines.get(j).startsWith("default ")) {
                    String fullDefault = lines.get(j);
                    String[] tempDef = fullDefault.split(" ");
                    for (int i = 0; i < tempDef.length; ++i) {
                        defaults.add(tempDef[i]);
                    }
                    break;
                }
            }
            if (defaults.size() == 0) {
                defaults = null;
            }
            int blockIndex = SpecificLevel.findIndex(lines, "block definitions");
            for (int i = blockIndex + 1; i < lines.size(); ++i) {

                String fullLine = lines.get(i);
                String symbol = fullLine.substring("bdef symbol:".length(), "bdef symbol:".length() + 1);
                String infoLine = fullLine.substring("bdef symbol:".length() + 2);
                String[] splited = infoLine.split(" ");
                int width = BlocksDefinitionReader.findNumb(splited, defaults, "width:");
                int hitPoints = BlocksDefinitionReader.findNumb(splited, defaults, "hit_points:");
                int height = BlocksDefinitionReader.findNumb(splited, defaults, "height:");
                Color stroke = BlocksDefinitionReader.findStroke(splited, defaults);

                Map<Integer, BlockFiller> blocksHits = BlocksDefinitionReader.blockColors(
                        splited, defaults);
                BlockCreator newBlock = new BlockCreator() {
                    @Override
                    public Block create(int xpos, int ypos) {
                        Block block = new Block(new Rectangle(new Point(xpos, ypos), width, height));
                        block.setHitPoints(hitPoints);
                        block.setColorsMap(blocksHits);
                        block.setStroke(stroke);
                        return block;
                    }
                };
                blockCreators.put(symbol, newBlock);
            }
        } catch (Exception e) {
            System.out.println("cant find block info-blocksDefinitionReader");
        }
        return blockCreators;
    }

    /**
     * .
     * find Stroke definition of blocks, if no stroke returns null
     *
     * @param splited  - specific line splited by " "(space) to an array
     * @param defaults - ArrayList of the defaults in the block definitions file
     * @return - Color of stroke, and null if no Color
     */
    public static Color findStroke(String[] splited, ArrayList<String> defaults) {
        Map<String, Color> colors = BlockFiller.createColorMap();
        for (int i = 0; i < splited.length; ++i) {
            if (splited[i].contains("stroke:")) {
                if (splited[i].contains("RGB")) {
                    String colorStr = splited[i].substring("stroke:color(RGB(".length(), splited[i].length() - 2);
                    String[] colorVals = colorStr.split(",");
                    ArrayList<Integer> colorNumbs = new ArrayList<Integer>();
                    for (int j = 0; j < colorVals.length; ++j) {
                        colorNumbs.add(Integer.parseInt(colorVals[j]));
                    }
                    Color color = new Color(colorNumbs.get(0), colorNumbs.get(1), colorNumbs.get(2));
                    return color;
                } else {
                    String colorStr = splited[i].substring("stroke:color(".length(), splited[i].length() - 1);
                    Color color = colors.get(colorStr);
                    return color;
                }
            }
        }
        if (defaults == null) {
            return null;
        }
        for (int k = 0; k < defaults.size(); ++k) {
            if (defaults.get(k).contains("stroke:")) {
                if (defaults.get(k).contains("RGB")) {
                    String colorStr = defaults.get(k).substring("stroke:color(RGB(".length(),
                            defaults.get(k).length() - 2);
                    String[] colorVals = colorStr.split(",");
                    ArrayList<Integer> colorNumbs = new ArrayList<Integer>();
                    for (int z = 0; z < colorVals.length; ++z) {
                        colorNumbs.add(Integer.parseInt(colorVals[z]));
                    }
                    Color color = new Color(colorNumbs.get(0), colorNumbs.get(1), colorNumbs.get(2));
                    return color;
                } else {
                    String colorStr = defaults.get(k).substring("stroke:color(".length(),
                            defaults.get(k).length() - 1);
                    Color color = colors.get(colorStr);
                    return color;
                }
            }
        }
        return null;
    }

    /**.
     * creates map on keys: number of hits , and values: BlockFillers
     *
     * @param splited  - specific line splited by " "(space) to an array
     * @param defaults - ArrayList of the defaults in the block definitions file
     * @return - Map of hitspoints and BlockFillers objects
     */
    public static Map<Integer, BlockFiller> blockColors(String[] splited,
                                                        ArrayList<String> defaults) {
        Map<Integer, BlockFiller> blocks = new TreeMap<>();
        for (int i = 0; i < splited.length; ++i) {
            if (splited[i].contains("fill")) {
                BlockFiller blockFiller = new BlockFiller(splited[i]);
                if (splited[i].contains("-")) {
                    int hitPoints = Integer.parseInt(splited[i].substring(
                            "fill-".length(), "fill-".length() + 1));
                    blocks.put(hitPoints, blockFiller);
                } else {
                    blocks.put(1, blockFiller);
                }
            }
        }
        if ((blocks.isEmpty()) && (defaults != null)) {
            for (int i = 0; i < defaults.size(); ++i) {
                if (defaults.get(i).contains("fill")) {
                    BlockFiller blockFiller = new BlockFiller(defaults.get(i));
                    if (defaults.get(i).contains("-")) {
                        int hitPoints = Integer.parseInt(defaults.get(i).substring(
                                "fill-".length(), "fill-".length() + 1));
                        blocks.put(hitPoints, blockFiller);
                    } else {
                        blocks.put(1, blockFiller);
                    }
                }
            }
        }
        return blocks;
    }

    /**.
     * searching for a specific number in the line
     *
     * @param splited   - specific line splited by " "(space) to an array
     * @param defaults  - ArrayList of the defaults in the block definitions file
     * @param searchVal the thing being searched
     * @return - Integer - the number was looking for
     * @throws Exception - if no number found
     */
    public static Integer findNumb(String[] splited, ArrayList<String> defaults,
                                   String searchVal) throws Exception {
        int value = -1;
        for (int i = 0; i < splited.length; ++i) {
            if (splited[i].contains(searchVal)) {
                value = Integer.parseInt(splited[i].substring(searchVal.length()));
                return value;
            }
        }
        if (defaults == null) {
            throw new Exception("no number");
        }
        for (int j = 0; j < defaults.size(); ++j) {
            if (defaults.get(j).contains(searchVal)) {
                value = Integer.parseInt(defaults.get(j).substring(searchVal.length()));
            }
        }
        return value;
    }

    /**.
     * searching for spacers symbols
     *
     * @param lines - specific level's block definition splited to lines in list
     * @return - map of keys: symbols , and values: the width of the spacer
     */
    public static Map<String, Integer> spacersMap(List<String> lines) {
        Map<String, Integer> spacers = new TreeMap<String, Integer>();
        try {
            for (int i = 0; i < lines.size(); ++i) {
                if (lines.get(i).equals("\n")) {
                    continue;
                }
                String subString = lines.get(i).substring("sdef symbol:".length());
                String[] splittedSubstring = subString.split(" ");
                int width = Integer.parseInt(splittedSubstring[1].substring("width:".length()));
                spacers.put(splittedSubstring[0], width);
            }
        } catch (Exception e) {
            System.out.println("cant find spacers definitions");
        }
        return spacers;
    }
}
