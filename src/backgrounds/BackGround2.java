package backgrounds;

import animation.GameLevel;
import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * Class name: BackGround2
 * class operation: Sprite Type.
 * Draws the Background of level 1 on the given DrawSurface
 */

public class BackGround2 implements Sprite {

    /**
     * .
     * Function Operation: draws the background
     *
     * @param d - the surface to draw on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(239, 231, 176));
        for (int i = 0; i < 80; ++i) {
            d.drawLine(150, 150, 0 + i * 8, 220);
        }
        d.fillCircle(150, 150, 60);
        d.setColor(new Color(236, 215, 73));
        d.fillCircle(150, 150, 50);
        d.setColor(new Color(255, 225, 24));
        d.fillCircle(150, 150, 40);
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
