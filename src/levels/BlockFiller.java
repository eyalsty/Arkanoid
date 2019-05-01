package levels;

import biuoop.DrawSurface;
import geometry.Rectangle;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Color;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * .
 * Author: Eyal Styskin
 * Operation: fills the block with the necessary : image/color (regular/ selected from RGB)
 */
public class BlockFiller {
    private Image image;
    private Color color;

    /**
     * .
     * constructor - receives color and sets the color member accordingly
     *
     * @param color - Color type
     */
    public BlockFiller(Color color) {
        this.color = color;
    }

    /**
     * Constructor - checks the filler Description String and according to that, set the member.
     * if its an image or a color
     *
     * @param fillerDesc - String with block filler description
     */
    public BlockFiller(String fillerDesc) {
        if (fillerDesc.contains("image")) {
            try {
                if (fillerDesc.contains("fill-")) {
                    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(fillerDesc.substring(
                            "fill-k:image(".length(), fillerDesc.length() - 1));
                    this.image = ImageIO.read(is);
                    this.color = null;
                    return;
                } else {
                    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(fillerDesc.substring(
                            "fill:image(".length(), fillerDesc.length() - 1));
                    this.image = ImageIO.read(is);
                    this.color = null;
                    return;
                }
            } catch (Exception e) {
                System.out.println("can't load image-BlockFiller");
            }
        } else if (fillerDesc.contains("RGB")) {
            String rgbDesc = new String();
            if (fillerDesc.contains("-")) {
                rgbDesc = fillerDesc.substring("fill-3:color(RGB(".length(), fillerDesc.length() - 2);
            } else {
                rgbDesc = fillerDesc.substring("fill:color(RGB(".length(), fillerDesc.length() - 2);
            }
            String[] colorVals = rgbDesc.split(",");
            ArrayList<Integer> colorNumbs = new ArrayList<Integer>();
            for (int i = 0; i < colorVals.length; ++i) {
                colorNumbs.add(Integer.parseInt(colorVals[i]));
            }
            this.color = new Color(colorNumbs.get(0),
                    colorNumbs.get(1), colorNumbs.get(2));
            this.image = null;
            return;
        } else {
            Map<String, Color> colors = BlockFiller.createColorMap();
            String colorFound = null;
            if (fillerDesc.contains("fill-")) {
                colorFound = fillerDesc.substring(
                        "fill-k:color(".length(), fillerDesc.length() - 1);
            } else {
                colorFound = fillerDesc.substring(
                        "fill:color(".length(), fillerDesc.length() - 1);
            }
            this.color = colors.get(colorFound);
            this.image = null;
        }
    }

    /**
     * Static method that creates a map of key:Strings with colors and values: Color type colors.
     *
     * @return a Map<String,Color>
     */
    public static Map<String, Color> createColorMap() {
        Map<String, Color> colors = new TreeMap<String, Color>();
        colors.put("black", Color.black);
        colors.put("blue", Color.blue);
        colors.put("cyan", Color.cyan);
        colors.put("gray", Color.gray);
        colors.put("lightGray", Color.lightGray);
        colors.put("green", Color.green);
        colors.put("orange", Color.orange);
        colors.put("pink", Color.pink);
        colors.put("red", Color.red);
        colors.put("white", Color.white);
        colors.put("yellow", Color.yellow);
        return colors;
    }

    /**
     * .
     * draws the block according to its members
     *
     * @param d         - the drawsurface to draw on
     * @param rectangle - the rectangle to fill in
     */
    public void drawBlock(DrawSurface d, Rectangle rectangle) {
        if (this.image == null) {
            d.setColor(this.color);
            d.fillRectangle((int) rectangle.getUpperLeft().getX(),
                    (int) rectangle.getUpperLeft().getY(),
                    (int) rectangle.getWidth(), (int) rectangle.getHeight());
        } else {
            d.drawImage((int) rectangle.getUpperLeft().getX(),
                    (int) rectangle.getUpperLeft().getY(), this.image);
        }
    }

    /**
     * .
     * return the Color member
     *
     * @return this color
     */
    public Color getColor() {
        return this.color;
    }

}

