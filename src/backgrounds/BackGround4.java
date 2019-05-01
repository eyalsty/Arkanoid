package backgrounds;

import animation.GameLevel;
import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * author: Eyal Styskin
 * Class name: BackGround1
 * class operation: Sprite Type.
 * Draws the Background of level 1 on the given DrawSurface
 */


public class BackGround4 implements Sprite {

    /**
     * .
     * Function Name:drawOn
     * Function Operation: draws the background
     *
     * @param d - the surface to draw on
     */

    public void drawOn(DrawSurface d) {
        d.setColor(new Color(23, 136, 208));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.WHITE);
        for (int i = 0; i < 10; ++i) {
            d.drawLine(90 + (8 * i), 450, 70 + (8 * i), 600);
            d.drawLine(620 + (8 * i), 530, 590 + (8 * i), 600);
        }
        d.setColor(new Color(204, 204, 204));
        d.fillCircle(75, 440, 20);
        d.fillCircle(95, 460, 25);
        d.fillCircle(590, 520, 20);
        d.fillCircle(615, 550, 25);
        d.setColor(new Color(187, 187, 187));
        d.fillCircle(115, 430, 30);
        d.fillCircle(635, 520, 30);
        d.setColor(new Color(170, 170, 170));
        d.fillCircle(135, 455, 25);
        d.fillCircle(155, 450, 35);
        d.fillCircle(655, 550, 30);
        d.fillCircle(675, 535, 30);
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
