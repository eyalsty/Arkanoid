package sprites;

import animation.GameLevel;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import levels.BlockFiller;
import others.Velocity;

import java.util.ArrayList;
import java.awt.Color;
import java.util.List;
import java.util.Map;


/**
 * .
 * author: Eyal Styskin
 * Class name:Block
 * class operation: the class "Block" implements the collidable and Sprite interfaces and has its methods.
 * the created block object is a Rectangle that has color, row number and a hitcount(how many times it needs to
 * be hit).
 * the block has the ability to change the direction (and velocity) of the ball that hits him, and to be drawn on the
 * given surface.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private ArrayList<HitListener> hitListeners = new ArrayList<HitListener>();
    private Rectangle rectangle;
    private int hitPoints;
    private Map<Integer, BlockFiller> colorsMap;
    private Color stroke;

    /**
     * Function Operation: constructor - creates new block from the rectangle class and sets its color.
     *
     * @param rectangle - Rectangle type rectangle to create the block
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * Setting the stroke member of this block.
     * if not stroke its null
     *
     * @param strokeColor - the Color of the Stroke
     */
    public void setStroke(Color strokeColor) {
        this.stroke = strokeColor;
    }

    /**
     * sets the colorsMap member of this block.
     *
     * @param colors - map of integers (hitpoints) and Colors
     */
    public void setColorsMap(Map<Integer, BlockFiller> colors) {
        this.colorsMap = colors;
    }

    /**
     * .
     * function name: setHitPoints
     * Function Operation: set the hitPoints ("lives) member of this block
     *
     * @param newHitPoints - Integer with number of hit points block has
     */
    public void setHitPoints(int newHitPoints) {
        this.hitPoints = newHitPoints;
    }

    /**
     * .
     * function name: getHitPoints
     * Function Operation:getter - return this block's hit points number
     *
     * @return - this.hit points (integer)
     */
    public int getHitPoints() {
        return this.hitPoints;
    }

    /**
     * .
     * function name: getCollisionRectangle
     * Function Operation: getter for this rectangle
     *
     * @return this.rectangle - the member
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * .
     * function name: addHitListener
     * Function Operation: adds a new HitListener to the list of hitListeners that
     * this block has
     *
     * @param hl - new HitListener
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * .
     * function name: removeHitListener
     * Function Operation: remove the given hitListener from the hitListeners list
     * of this block.
     *
     * @param hl - HitListener to be removed
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * .
     * function name: notifyHit
     * Function Operation: update the HitListeners list and call all the hitEvent methods
     * of the hitListeners of this block
     *
     * @param hitter - the ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners); // update current list
        for (HitListener hl : listeners) {  // Notify all listeners about a hit event
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * .
     * function name: hit
     * Function Operation: this method gets the collision Point and the current Velocity of the ball.
     * it checks the location of the collision according to this block (rectangle) and according to that,
     * the velocity of the ball is being changed
     *
     * @param collisionPoint  - the Point where the collision happened
     * @param currentVelocity - the current velocity of the ball
     * @param hitter          - the ball that hits the block
     * @return currentVelocity - a new Velocity, after the change because of the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.hitPoints--;
        this.notifyHit(hitter);
        if (collisionPoint.getY() == this.rectangle.getDownRight().getY()
                || collisionPoint.getY() == this.rectangle.getUpperLeft().getY()) { //hit upper or lower block
            currentVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));
        } else if (this.epsilonCheckDy(collisionPoint, currentVelocity)) { // check for an epsilon difference
            currentVelocity = new Velocity(currentVelocity.getDx(), Math.round(currentVelocity.getDy()) * (-1));
        }
        if (collisionPoint.getX() == this.rectangle.getDownRight().getX()
                || collisionPoint.getX() == this.rectangle.getUpperLeft().getX()) { //hit right or left block
            currentVelocity = new Velocity(Math.round(currentVelocity.getDx()) * (-1), currentVelocity.getDy());
        } else if (this.epsilonCheckDx(collisionPoint, currentVelocity)) { // check for an epsilon difference
            currentVelocity = new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy()); //change direct
        }
        return currentVelocity;
    }

    /**
     * function name: epsilonCheckDy
     * Function Operation: a helper method for the "hit" method.
     * this method checks if the difference between the collision point and the upper/ lower blocks are very close but
     * not equal.
     *
     * @param collisionPoint  - the collision point the two lines
     * @param currentVelocity - the current ball's velocity
     * @return true/ false boolean value
     */
    private boolean epsilonCheckDy(Point collisionPoint, Velocity currentVelocity) {
        if (this.rectangle.getDownRight().getY() - collisionPoint.getY() < 0.001
                && this.rectangle.getDownRight().getY() - collisionPoint.getY() != 0
                && currentVelocity.getDy() < 0) { // check epsilon difference and direction
            return true;
        }
        if (collisionPoint.getY() - this.rectangle.getUpperLeft().getY() < 0.001
                && collisionPoint.getY() - this.rectangle.getUpperLeft().getY() != 0
                && currentVelocity.getDy() > 0) { // check epsilon difference and direction
            return true;
        }
        return false;
    }

    /**
     * function name: epsilonCheckDx
     * Function Operation:a helper method for the "hit" method.
     * this method checks if the difference between the collision point and the right/ left blocks are very
     * close but not equal.
     *
     * @param collisionPoint  - the collision point the two lines
     * @param currentVelocity - the current ball's velocity
     * @return true/ false boolean value
     */
    private boolean epsilonCheckDx(Point collisionPoint, Velocity currentVelocity) {
        if (this.rectangle.getDownRight().getX() - collisionPoint.getX() < 0.001
                && this.rectangle.getDownRight().getX() - collisionPoint.getX() != 0
                && currentVelocity.getDx() < 0) { // check epsilon difference and direction
            return true;
        }
        if (collisionPoint.getX() - this.rectangle.getUpperLeft().getX() < 0.001
                && collisionPoint.getX() - this.rectangle.getUpperLeft().getX() != 0
                && currentVelocity.getDx() > 0) { // check epsilon difference and direction
            return true;
        }
        return false;
    }

    /**
     * .
     * function name: drawOn
     * Function Operation: sprite's interface method. this method draws the block (a rectangle) on the given
     * drawsurface according to its color member. also, the method draws the number of "life" the block has,
     * when it reaches 0 it turns to 'X'.
     *
     * @param surface - a given draw surface on which we draw the block and the game
     */

    public void drawOn(DrawSurface surface) {
        int i1 = (int) this.rectangle.getUpperLeft().getX();
        int i2 = (int) this.rectangle.getUpperLeft().getY();
        int i3 = (int) this.rectangle.getWidth();
        int i4 = (int) this.rectangle.getHeight();
        int hits = this.hitPoints;
        if (this.colorsMap.containsKey(hits)) {
            this.colorsMap.get(hits).drawBlock(surface, this.rectangle);
        } else {
            this.colorsMap.get(1).drawBlock(surface, this.rectangle);
            BlockFiller filler = (BlockFiller) this.colorsMap.values().toArray()[0];
            filler.drawBlock(surface, this.rectangle);
        }
        if (this.stroke != null) {
            surface.setColor(this.stroke);
            surface.drawRectangle(i1, i2, i3, i4); //draw borders around the filled rectangle
        }
    }

    /**
     * .
     * function name: addToGame
     * Function Operation: add the block to the game: to the sprites collection and to the collidables
     * collection
     *
     * @param g - the game object that owns the sprites and collidables collection
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * .
     * function name: removeFromGame
     * Function Operation: remove this block from the sprites and collidables
     * in the current game
     *
     * @param game - - current game to remove the block from
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }


    /**
     * function name: timePassed
     * Function Operation:sprite's interface method, currently does nothing.
     * @param dt - does nothing
     */
    public void timePassed(double dt) {
    }
}
