package sprites;

import animation.GameLevel;
import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;


/**.
 * author: Eyal Styskin
 * Class name:Block
 * class operation:
 */
public class NameIndicator implements Sprite {
    private String name;

    /**
     * .
     * Function Operation:sets the name of the level (String type member)
     *
     * @param s - String type (name of level)
     */
    public NameIndicator(String s) {
        this.name = s;
    }

    /**
     * .
     * function name: drawOn
     * Function Operation: draw a string on top of the screen,
     * that displays the name of the level
     *
     * @param d - the surface to draw on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(550, 15, "Level Name: " + this.name, 15);
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
     * Function Operation: adds this NameIndicator Sprite to the game
     * (to the SpritesCollection)
     *
     * @param g - the game animation
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}

