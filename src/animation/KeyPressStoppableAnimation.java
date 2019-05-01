package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**.
 * Author: Eyal Styskin
 * Class operation: Decorator for animations that wait for a key press
 */

public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean isStop;
    private boolean isAlreadyPressed;

    /**.
     * Constructor - sets its member
     *
     * @param sensor    - sensor for Keyboard press
     * @param key       - the String needs to be pressed
     * @param animation - the Animation to decorate
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
    }

    /**
     * runs the doOneFrame method of the animation (in the member), ,and checks for key pressing.
     * if the key pressed for the first time, stops the running of the animation
     *
     * @param d  - Drawsurface to draw on
     * @param dt - does nothing here
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.keyboardSensor.isPressed(this.key)) {
            if (isAlreadyPressed) {
                return;
            } else {
                this.isStop = true;
            }
        } else {
            isAlreadyPressed = false;
        }
    }

    /**
     * .
     * indicates the animation to stop
     *
     * @return - Boolean - true (stop) or false (continue)
     */
    public boolean shouldStop() {
        return this.isStop;
    }

}
