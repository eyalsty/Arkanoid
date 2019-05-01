package animation;

import biuoop.DrawSurface;
import interfaces.Animation;
import others.Counter;

import java.awt.Color;


/**
 * .
 * author: Eyal Styskin
 * Class name: EndScreen
 * class operation: Once the game is over (either the player run out of lives
 * or managed to clear all the levels), we will display the End screen that
 * contains its final score.
 * persists until the space key is pressed.
 */

public class EndScreen implements Animation {
    private boolean victory;
    private Counter score;

    /**
     * Function Operation:constructor - sets the members of thie class.
     *
     * @param victory - boolean type, says if won or lost
     * @param score   - Counter type - contains the score of the played game
     */

    public EndScreen(boolean victory, Counter score) {
        this.victory = victory;
        this.score = score;
    }

    /**
     * function name: doOneFrame
     * Function Operation: receive a DrawSurface, is in charge of the logic of the
     * animation, displays a screen with Win/Game Over announcement and the player's
     * score.
     *
     * @param d  - Drawsurface type - to draw number on it
     * @param dt - not used here
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.victory) {
            d.setColor(Color.white);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.black);
            d.drawText(650, 530, "Score:" + Integer.toString(score.getValue()), 30);
            d.drawText(650, 531, "Score:" + Integer.toString(score.getValue()), 30);
            d.drawText(650, 532, "Score:" + Integer.toString(score.getValue()), 30);
        } else {
            d.setColor(Color.BLACK);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.white);
            d.drawText(485, 530, "Game Over. Your score is " + Integer.toString(score.getValue()), 22);
            d.drawText(485, 531, "Game Over. Your score is " + Integer.toString(score.getValue()), 22);
        }
    }

    /**
     * .
     * return false.
     *
     * @return false
     */
    public boolean shouldStop() {
        return false;
    }
}

