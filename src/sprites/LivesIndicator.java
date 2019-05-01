package sprites;

import animation.GameLevel;
import biuoop.DrawSurface;

import interfaces.Sprite;
import others.Counter;

import java.awt.Color;

/**
 * author: Eyal Styskin
 * Class name:LivesIndicator
 * class operation: Sprite type, sits on the top of the screen and indicates
 * the number of lives the player remains.
 */
public class LivesIndicator implements Sprite {
    private Counter lives;

    /**.
     * Function Operation: constructor - sets the lives member
     * @param lives - pointer of Counter type to the number of lives
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }

    /**.
     * function name: drawOn
     * Function Operation: draw a string on top of the screen,
     * that says how much lives the player remain
     * @param d - the surface to draw on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        String s = Integer.toString(this.lives.getValue());
        d.drawText(100, 15, "Lives: " + s, 15);
    }

    /**.
     * function name: timePassed
     * Function Operation:nothing
     * @param dt - does nothing
     */
    public void timePassed(double dt) {

    }

    /**.
     * function name: addToGame
     * Function Operation: adds this LivesIndicator Sprite to the game
     * (to the SpritesCollection)
     * @param g - the game animation
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}

