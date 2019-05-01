package interfaces;
import animation.GameLevel;
import biuoop.DrawSurface;

/**
 * .
 * author: Eyal Styskin
 * Interface name:Sprite
 * Interface operation: Interface of objects that can be drawn on screen.
 * Sprites can be drawn on the screen, and can be notified that time has passed
 * (so that they know to change their position / shape / appearance / etc).
 * the sprites include blocks, the paddle and balls.
 */
public interface Sprite {

    /**
     * .
     * function name: drawOn
     * Function Operation: draw the sprite to the screen
     *
     * @param d - the surface to draw on
     */
    void drawOn(DrawSurface d);


    /**
     * .
     * function name: timePassed
     * Function Operation: notify the sprite that time has passed and it needs to move/
     * change its direction (some sprites don't do anything when time passes).
     * @param dt - frames per second
     */
    void timePassed(double dt);

    /**
     * .
     * function name: addToGame
     * Function Operation: add the sprite to the new created game
     *
     * @param g - the game animation
     */
    void addToGame(GameLevel g);
}
