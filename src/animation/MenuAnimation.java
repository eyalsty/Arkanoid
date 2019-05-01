package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Menu;


import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * as generic MenuAnimation that implements the Menu<T> interface.
 * displays the menu of the game according to the tasks its getting
 *
 * @param <T> - This is used to specify the return type expected from the menu.
 *            we allow the menu to be used with different return types
 */
public class MenuAnimation<T> implements Menu<T> {
    private String title;
    private T status;
    private ArrayList<String> keys;
    private ArrayList<String> messages;
    private ArrayList<T> returnVals;
    private boolean stop;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private List<Boolean> isSubMenu;
    private Menu<T> subMenu;

    /**
     * .
     * consturctor -
     *
     * @param menuTitle - the title of the menu to be printed
     * @param k         - KeyboardSensor
     * @param runner    - AnimationRunner object
     */
    public MenuAnimation(String menuTitle, KeyboardSensor k, AnimationRunner runner) {
        this.title = menuTitle;
        this.keys = new ArrayList();
        this.messages = new ArrayList();
        this.returnVals = new ArrayList();
        this.stop = false;
        this.keyboardSensor = k;
        this.animationRunner = runner;
        this.isSubMenu = new ArrayList();
        this.subMenu = null;
        this.clearStatus();
    }

    /**
     * .
     * adds an choosing option to the menu
     *
     * @param key       - the String needs to be pressed
     * @param message   - String - message to be displayed
     * @param returnVal - the Task type
     */
    public void addSelection(String key, String message, T returnVal) {
        this.keys.add(key);
        this.messages.add(message);
        this.returnVals.add(returnVal);
        this.isSubMenu.add(false);
    }

    /**
     * .
     * loads background from Resources Stream, also displays the options can be chosen
     *
     * @param d  - Drawsurface to draw on
     * @param dt - does nothing
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.WHITE);
        d.drawText(100, 100, this.title, 40);
        d.drawText(100, 101, this.title, 40);
        d.drawText(100, 102, this.title, 40);

        for (int i = 0; i < this.messages.size(); ++i) {
            String text = "(" + this.keys.get(i) + ")" + this.messages.get(i);
            d.drawText(100, 200 + (30 * i), text, 25);
        }
        for (int i = 0; i < this.keys.size(); ++i) {
            if (this.keyboardSensor.isPressed(this.keys.get(i))) {
                if (this.isSubMenu.get(i)) {
                    this.animationRunner.run(this.subMenu);
                    this.status = subMenu.getStatus();
                    this.stop = true;
                    subMenu.clearStatus();
                } else {
                    this.status = this.returnVals.get(i);
                    this.stop = true;
                }
                break;
            }
        }
    }

    /**
     * .
     * returns the current status that was chosen to run
     *
     * @return this status
     */
    public T getStatus() {
        return this.status;
    }

    /**
     * .
     * indicates if the program should stop
     *
     * @return this boolean
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * .
     * resets the status and isStop members
     */
    public void clearStatus() {
        this.status = null;
        this.stop = false;
    }

    /**
     * .
     * adds new sub Menu to the main menu
     *
     * @param key     - string to be pressed
     * @param message - message shows next to the key
     * @param sub     - the sub menu
     */
    public void addSubMenu(String key, String message, Menu<T> sub) {
        this.keys.add(key);
        this.messages.add(message);
        this.returnVals.add(null);
        this.isSubMenu.add(true);
        this.subMenu = sub;
    }
}
