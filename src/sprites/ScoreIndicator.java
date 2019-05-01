package sprites;

import animation.GameLevel;
import biuoop.DrawSurface;
import interfaces.Sprite;
import others.Counter;

import java.awt.Color;

/**
 * .
 * author: Eyal Styskin
 * Class name:ScoreIndicator
 * class operation: Sprite sits on the top of the screen and shows
 * player his score
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * .
     * Function Operation:Constructor - sets the score member
     *
     * @param score - the Counter type score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * .
     * function name: drawOn
     * Function Operation: draw a string on top of the screen,
     * that says display the updated score of the current game
     * that being played.
     *
     * @param d - the surface to draw on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.BLACK);
        d.drawRectangle(0, 0, 800, 20); //draw black borders around the filled rectangle
        d.setColor(Color.BLACK);
        String s = Integer.toString(this.score.getValue());
        d.drawText(350, 15, "Score: " + s, 15);
    }

    /**
     * .
     * function name: timePassed
     * Function Operation:nothing
     * @param dt - does nothing
     */
    public void timePassed(double dt) {
    }

    /**
     * .
     * function name: addToGame
     * Function Operation: adds this ScoreIndicator Sprite to the game
     * (to the SpritesCollection)
     *
     * @param g - the game animation
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
