package levels;

import animation.GameLevel;
import biuoop.DrawSurface;
import interfaces.LevelInformation;
import interfaces.Sprite;
import others.Velocity;
import sprites.Block;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**.
 * analyzing a single level
 */
public class SpecificLevel {
    private String levelName;
    private int paddleSpeed;
    private int paddleWidth;
    private int blockStartX;
    private int blockStartY;
    private int rowHeight;
    private int blocksNum;
    private List<Velocity> initialVelocities;
    private Sprite backGround;
    private List<Block> blocks;

    /**.
     * constructor - sets all the members of the level
     * @param lines - all the lines of the level
     */
    public SpecificLevel(ArrayList<String> lines) {
        this.levelName = findString(lines, "level_name:");
        this.paddleSpeed = findNumber(lines, "paddle_speed:");
        this.paddleWidth = findNumber(lines, "paddle_width:");
        this.blockStartX = findNumber(lines, "blocks_start_x:");
        this.blockStartY = findNumber(lines, "blocks_start_y:");
        this.rowHeight = findNumber(lines, "row_height:");
        this.blocksNum = findNumber(lines, "num_blocks:");
        this.initialVelocities = findVelocities(lines, "ball_velocities:");
        this.blocks = findBlocks(lines, "block_definitions:");
        this.backGround = findBackGround(lines, "background:");
    }

    /**.
     * creating the LevelInformation according to all the members of the class
     * @return a LevelInformation
     */
    public LevelInformation getLevel() {
        LevelInformation newLevel = new LevelInformation() {
            @Override
            public int numberOfBalls() {
                return initialVelocities.size();
            }

            @Override
            public List<Velocity> initialBallVelocities() {
                return initialVelocities;
            }

            @Override
            public int paddleSpeed() {
                return paddleSpeed;
            }

            @Override
            public int paddleWidth() {
                return paddleWidth;
            }

            @Override
            public String levelName() {
                return levelName;
            }

            @Override
            public Sprite getBackground() {
                return backGround;
            }

            @Override
            public List<Block> blocks() {
                return blocks;
            }

            @Override
            public int numberOfBlocksToRemove() {
                return blocksNum;
            }
        };
        return newLevel;
    }

    /**.
     * searching for the blocks arrangement of the screen
     * @param lines - all the lines of the specific level
     * @param searchVal - "block_definitions:"
     * @return - List of Blocks we found
     */
    public List<Block> findBlocks(List<String> lines, String searchVal) {
        List<Block> foundBlocks = new ArrayList<>();
        try {
            String blocksPath = findString(lines, searchVal);
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(blocksPath);
            Reader reader = new InputStreamReader(is);
            BlocksFromSymbolsFactory factory = BlocksDefinitionReader.fromReader(reader);
            int startIndex = findIndex(lines, "START_BLOCKS");
            int endIndex = findIndex(lines, "END_BLOCKS");

            int rowIndex = 0;

            for (int j = startIndex + 1; j < endIndex; ++j) {
                String currLine = lines.get(j);

                int dynamicX = this.blockStartX;

                for (int i = 0; i < currLine.length(); ++i) {
                    String sign = currLine.substring(i, i + 1);
                    int dynamicY = rowIndex * this.rowHeight + this.blockStartY;

                    if (factory.isSpaceSymbol(sign)) {
                        dynamicX += factory.getSpaceWidth(sign);

                    } else {
                        Block block = factory.getBlock(sign, dynamicX, dynamicY);
                        dynamicX += block.getCollisionRectangle().getWidth();
                        foundBlocks.add(block);
                    }
                }
                rowIndex++;
            }
        } catch (
                Exception e) {
            System.out.println("cant read file- specificLevel");
        }
        return foundBlocks;
    }

    /**.
     * searching for a number
     * @param lines - all the lines of the specific level
     * @param searchVal - that generic things we search for
     * @return - integer
     */
    public static int findNumber(ArrayList<String> lines, String searchVal) {
        int number = -1;
        try {
            int index = findIndex(lines, searchVal);
            String lineToRead = lines.get(index);
            number = Integer.parseInt(
                    lineToRead.substring(searchVal.length()));
        } catch (Exception e) {
            System.out.println("cant find number");
        }
        return number;
    }

    /**.
     * searching for background description
     * @param lines - all the lines of the specific level
     * @param searchVal - that generic things we search for
     * @return - Sprite type background
     */
    public Sprite findBackGround(ArrayList<String> lines, String searchVal) {
        try {
            String randBackground = findString(lines, searchVal);
            if (randBackground.contains("image")) {
                String imagePath = randBackground.substring(6, randBackground.length() - 1);
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(imagePath);
                Image image = ImageIO.read(is);
                Sprite imageSprite = new Sprite() {
                    @Override
                    public void drawOn(DrawSurface d) {
                        d.drawImage(0, 0, image);
                    }

                    @Override
                    public void addToGame(GameLevel g) {
                    }

                    @Override
                    public void timePassed(double dt) {
                    }
                };
                return imageSprite;
            } else if (randBackground.contains("RGB")) {
                String colorDesc = randBackground.substring(10, randBackground.length() - 2);
                String[] colorVals = colorDesc.split(",");
                ArrayList<Integer> colorNumbs = new ArrayList<Integer>();
                for (int i = 0; i < colorVals.length; ++i) {
                    colorNumbs.add(Integer.parseInt(colorVals[i]));
                }
                Sprite colorRGB = new Sprite() {
                    @Override
                    public void drawOn(DrawSurface d) {
                        d.setColor(new Color(colorNumbs.get(0),
                                colorNumbs.get(1), colorNumbs.get(2)));
                        d.fillRectangle(0, 0, 800, 600);
                    }

                    @Override
                    public void addToGame(GameLevel g) {
                    }

                    @Override
                    public void timePassed(double dt) {
                    }
                };
                return colorRGB;
            } else {
                Map<String, Color> colors = BlockFiller.createColorMap();
                String colorFound = randBackground.substring(6, randBackground.length() - 1);
                Color color = colors.get(colorFound);
                Sprite regularColor = new Sprite() {
                    @Override
                    public void drawOn(DrawSurface d) {
                        d.setColor(color);
                        d.fillRectangle(0, 0, 800, 600);
                    }

                    @Override
                    public void timePassed(double dt) {
                    }

                    @Override
                    public void addToGame(GameLevel g) {
                    }
                };
                return regularColor;
            }
        } catch (Exception e) {
            System.out.println("cant find background");
        }
        return null;
    }

    /**.
     *searching for velocities
     * @param lines - all the lines of the specific level
     * @param searchString - that generic things we search for
     * @return - ArrayList of velocities
     */
    public ArrayList<Velocity> findVelocities(ArrayList<String> lines, String searchString) {
        ArrayList<Velocity> velocities = new ArrayList<Velocity>();
        try {
            int index = findIndex(lines, searchString);
            String fullLine = lines.get(index);
            String toRead = fullLine.substring(searchString.length());
            String[] splitedVelocities = toRead.split(" ");
            for (int i = 0; i < splitedVelocities.length; ++i) {
                String[] singleVelocity = splitedVelocities[i].split(",");
                Double angle = Double.parseDouble(singleVelocity[0]);
                Double speed = Double.parseDouble(singleVelocity[1]);
                velocities.add(Velocity.fromAngleAndSpeed(angle, speed));
            }
        } catch (Exception e) {
            System.out.println("not found index");
        }
        return velocities;
    }

    /**
     *
     * @param lines - all the lines of the specific level
     * @param searchVal - that generic things we search for
     * @return integer - index of the thing we search for
     * @throws Exception - if index not found
     */
    public static int findIndex(List<String> lines, String searchVal) throws Exception {
        for (int i = 0; i < lines.size(); ++i) {
            if (lines.get(i).contains(searchVal)) {
                return i;
            }
        }
        throw new Exception("not found index");
    }

    /**
     *
     * @param lines - all the lines of the specific level
     * @param searchString - that generic things we search for
     * @return the String we found
     */
    public static String findString(List<String> lines, String searchString) {
        String info = null;
        try {
            int index = findIndex(lines, searchString);
            String fullLine = lines.get(index);
            info = fullLine.substring(searchString.length());
        } catch (Exception e) {
            System.out.println("not found index");
        }
        return info;
    }
}


