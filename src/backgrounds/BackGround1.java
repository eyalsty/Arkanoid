package backgrounds;

import animation.GameLevel;
import biuoop.DrawSurface;
import interfaces.Sprite;
import java.awt.Color;

/**
 * .
 * author: Eyal Styskin
 * Class name: BackGround1
 * class operation: Sprite Type.
 * Draws the Background of level 1 on the given DrawSurface
 */
public class BackGround1 implements Sprite {

    /**
     * .
     * Function Name:drawOn
     * Function Operation: draws the background
     *
     * @param d - the surface to draw on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLUE);
        d.drawLine(400, 40, 400, 140);
        d.drawLine(400, 180, 400, 280);
        d.drawLine(280, 160, 380, 160);
        d.drawLine(420, 160, 520, 160);
        d.drawCircle(400, 160, 100);
        d.drawCircle(400, 160, 70);
        d.drawCircle(400, 160, 40);
    }

    /**
     * Function Operation: nothing.
     * @param dt - does nothing
     */
    public void timePassed(double dt) {
    }

    /**
     * Function Operation: nothing.
     *
     * @param g - the game animation
     */

    public void addToGame(GameLevel g) {
    }

}
