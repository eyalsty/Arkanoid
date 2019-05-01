package interfaces;

import biuoop.DrawSurface;

/**
 * author: Eyal Styskin
 * Interface name:Animation
 * Interface operation: Animation types run the game, allows different
 * options like playing,pausing and showing different animations.
 */
public interface Animation {
    /**
     * Function Name: doOneFrame
     * Function Operation: plays one frame of the animation
     * and draws the necessary things on the drawsurface.
     * @param d - Drawsurface to draw on
     * @param dt - frames per second
     */
    void doOneFrame(DrawSurface d, double dt);

    /**.
     * Function Name: shouldStop
     * Function Operation: indicates the animation when to stop
     * @return - boolean type true or false (to stop or not to)
     */
    boolean shouldStop();
}
