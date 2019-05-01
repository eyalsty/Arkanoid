package backgrounds;

import animation.GameLevel;
import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * author: Eyal Styskin
 * Class name: BackGround3
 * class operation: Sprite Type.
 * Draws the Background of level 3 on the given DrawSurface
 */

public class BackGround3 implements Sprite {

    /**
     * .
     * Function Name:drawOn
     * Function Operation: draws the background
     *
     * @param d - the surface to draw on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(42, 130, 21));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(46, 42, 41));
        d.fillRectangle(60, 450, 80, 150);
        d.setColor(new Color(62, 58, 57));
        d.fillRectangle(90, 410, 20, 40);
        d.setColor(new Color(78, 74, 73));
        d.fillRectangle(97, 200, 6, 210);
        d.setColor(new Color(200, 127, 124));
        d.fillCircle(100, 190, 10);
        d.setColor(new Color(200, 66, 70));
        d.fillCircle(100, 190, 6);
        d.setColor(Color.WHITE);
        d.fillCircle(100, 190, 2);

        int windowLength = 80 / 11;
        d.setColor(Color.WHITE);
        for (int i = 0; i < 5; ++i) {
            for (int j = 1; j < 6; ++j) {
                d.fillRectangle(60 + windowLength + (2 * i * windowLength),
                        450 + (j * windowLength) + ((j - 1) * 20), windowLength, 20);
            }
        }
    }

    /**
     * Function Name:timePassed
     * Function Operation: nothing.
     * @param dt - does nothing
     */
    public void timePassed(double dt) {
    }

    /**
     * Function Name:addToGame
     * Function Operation: nothing.
     *
     * @param g - the game animation
     */
    public void addToGame(GameLevel g) {
    }
}
