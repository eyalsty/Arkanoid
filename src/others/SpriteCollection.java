package others;
import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;

/**
 * .
 * author: Eyal Styskin
 * Class name:SpriteCollection
 * class operation: the class holds an Array List of  sprites (interface) type.
 * it allows to add new Sprite type object, notify all the Sprites in the Array list the time has passed,
 * so each sprite should behave according to his timePassed method, and also draw all the sprite on a given surface.
 */

public class SpriteCollection {
    private ArrayList<Sprite> sprites = new ArrayList<Sprite>();


    /**
     * function name: addSprite
     * Function Operation:  the function get Sprite type object created in the Game class and adds it to the
     * sprites ArrayList.
     * @param s - add the new sprite to the sprites ArrayList
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**.
     * function name: removeSprite
     * Function Operation:remov the given Sprite object from the Sprites list
     * @param s - Sprite object to be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * function name: notifyAllTimePassed
     * Function Operation: activate the "timePassed" method for every sprite in the Sprites ArrayList.
     * @param dt - fps
     */
    public void notifyAllTimePassed(double dt) {
        ArrayList<Sprite> newSprites = new ArrayList<Sprite>(this.sprites); // update current list
        for (int i = 0; i < newSprites.size(); ++i) {
            newSprites.get(i).timePassed(dt);
        }
    }

    /**
     * function name: drawAllOn
     * Function Operation: activate the "drawOn" method for every sprite in the Sprites ArrayList.
     * to draw the sprite on the given surface
     * @param d - draw surface created in the "Game" class
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < sprites.size(); ++i) {
            sprites.get(i).drawOn(d);
        }
    }
}
