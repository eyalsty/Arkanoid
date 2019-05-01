package animation;

import biuoop.Sleeper;
import biuoop.DrawSurface;
import biuoop.GUI;
import interfaces.Animation;

/**
 * .
 * author: Eyal Styskin
 * Class name: AnimationRunner
 * class operation: The AnimationRunner takes an Animation object and runs it.
 * we implement the task-specific information in the Animation object,
 * and run it using the loop in the AnimationRunner class.
 */

public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Function Operation: constructor - setting the GUI, framesPerSecond and Sleeper
     * members.
     */

    public AnimationRunner() {
        this.gui = new GUI("Arkanoid Game", 800, 600);
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * Function Name:getGui
     * Function Operation: getter - getting the GUI member
     * members.
     *
     * @return - GUI type
     */

    public GUI getGui() {
        return gui;
    }

    /**
     * Function Name:run
     * Function Operation: runs the game (with doOneFrame method of the animation),
     * until shouldStop method returns true.
     *
     * @param animation - Animation type animation, runs it
     */

    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d, 1.0 / framesPerSecond);
            if (animation.shouldStop()) {
                return;
            }
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
