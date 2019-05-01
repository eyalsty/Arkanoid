package animation;

import biuoop.DrawSurface;
import gamelogic.HighScoresTable;
import interfaces.Animation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Author: Eyal Styskin
 * Class operation: responsible for displaying the highScoreTable as an animation.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;

    /**
     * .
     * Constructor - receives the HighScoreTable object, and sets it as a member
     *
     * @param scores - the highscoreTable
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
    }

    /**
     * .
     * loading a backGround image, drawing titles, and showing the top 5 highscores
     *
     * @param d  - Drawsurface to draw on
     * @param dt - not used here
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.white); // CHANGED
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(240, 130, "High Scores", 40);
        d.drawText(240, 131, "High Scores", 40);
        d.drawText(180, 210, "Player Name", 30);
        d.drawText(520, 210, "Score", 30);
        d.drawLine(180, 219, 650, 219);
        d.drawLine(180, 220, 650, 220);
        d.drawLine(180, 221, 650, 221);
        d.drawText(195, 555, "Press Space To Continue", 30);
        d.drawText(195, 556, "Press Space To Continue", 30);
        d.drawText(195, 557, "Press Space To Continue", 30);

        for (int i = 0; i < this.scores.size(); ++i) {
            d.drawText(180, 250 + (30 * i), Integer.toString(i + 1) + "."
                    + this.scores.getHighScores().get(i).getName(), 25);
            String score = Integer.toString(this.scores.getHighScores().get(i).getScore());
            d.drawText(520, 250 + (30 * i), score, 25);
        }
    }

    /**
     * .
     * indicates if the HighScore animation should stop
     *
     * @return - Boolean -  true or false
     */
    public boolean shouldStop() {
        return false;
    }
}
