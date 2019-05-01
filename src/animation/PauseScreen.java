package animation;

import biuoop.DrawSurface;
import interfaces.Animation;

import java.awt.Color;


/**
 * .
 * author: Eyal Styskin
 * Class name: PauseScreen
 * class operation: We start running the PauseScreen animation In the game,
 * instead of the Game one when we identify the key p being pressed.
 * The Game animation will resume as soon as we will return from the PauseScreen animation
 * by pressing space button.
 */
public class PauseScreen implements Animation {
    /**
     * Function Name: doOneFrame
     * Function Operation: draws texts on the drawsurface that the game was paused.
     * also, waits for the user to press the Space Key button to resume the game
     *
     * @param d  - the DrawSurface to draw on.
     * @param dt - frames per second - does nothing here
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.green);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.drawText(195, 555, "Game Paused - Press Space To Continue", 30);
        d.drawText(195, 556, "Game Paused - Press Space To Continue", 30);
        d.drawText(195, 557, "Game Paused - Press Space To Continue", 30);
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 10);
    }

    /**
     * Function Name: shouldStop
     * Function Operation: tells the animation when to stop.
     *
     * @return boolean - true or false
     */
    public boolean shouldStop() {
        return false;
    }
}
