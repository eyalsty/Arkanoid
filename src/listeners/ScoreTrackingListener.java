package listeners;
import interfaces.HitListener;
import others.Counter;
import sprites.Ball;
import sprites.Block;
/**
 * author: Eyal Styskin
 * Class name: ScoreTrackingListener
 * class operation:implements HitListener. this class uses the listener pattern
 * that will be in charge of tracking after the hits and updating the game's score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**.
     * Function Operation:Constructor- receives a pointer to the Counter type
     * score counter from the game.
     * @param scoreCounter - pointer to the Counter of the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * function name: hitEvent
     * Function Operation: this method in charge of updating the score according to the
     * kind of hit. for normal hit =  + 5 points, for block destroying = + 15 points.
     * @param beingHit - the block was hit
     * @param hitter - the ball hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(10);
        }
    }
}