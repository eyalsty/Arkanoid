package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import interfaces.Animation;
import others.SpriteCollection;

import java.awt.Color;


/**
 * .
 * author: Eyal Styskin
 * Class name: CountdownAnimation
 * class operation: The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) secods, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private SpriteCollection gameScreen;
    private double duration;
    private double countFrom;
    private int counter;
    private boolean stop;


    /**
     * Function Operation: constructor - setting the gameScreen, duration,
     * countFrom,counter and stop members.
     *
     * @param numOfSeconds - double type number of seconds
     * @param countFrom    - integer the number to count from
     * @param gameScreen   - SpriteCollection (all the sprites and background in the level)
     */

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.gameScreen = gameScreen;
        this.duration = numOfSeconds;
        this.countFrom = countFrom;
        this.counter = countFrom;
        this.stop = false;
    }


    /**
     * receive a DrawSurface, is in charge of the logic of the
     * animation, displays countdown from a given number before the game starts.
     * @param d - Drawsurface to draw on
     * @param dt - the frames per second (1/60)
     */

    public void doOneFrame(DrawSurface d, double dt) {
        this.gameScreen.drawAllOn(d);
        Sleeper sleeper = new Sleeper();

        d.setColor(Color.WHITE);
        d.drawText(d.getWidth() / 2, (d.getHeight() / 2) + 50, Integer.toString(this.counter), 80);
        if (this.counter != this.countFrom) {
            sleeper.sleepFor((long) ((this.duration * 1000 / this.countFrom)));
        }
        this.counter--;
        if (this.counter < 0) {
            d.drawText(d.getWidth() / 2, (d.getHeight() / 2) + 50, "GO!", 80);
            this.stop = true;
        }
    }

    /**
     * Function Name: shouldStop
     * Function Operation: if the counter member smaller then 0, stop the animation.
     *
     * @return boolean - true or false
     */
    public boolean shouldStop() {
        if (this.counter < 0) {
            return this.stop;
        }
        return false;
    }
}

