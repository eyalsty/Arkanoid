package listeners;

import animation.GameLevel;
import interfaces.HitListener;
import others.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * author: Eyal Styskin
 * Class name: BallRemover
 * class operation:implements HitListener. this class uses the listener pattern
 * that will be in charge of removing balls, and updating an availabe - balls counter.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**.
     * Function Operation:Constructor - creates this class's members
     * @param game - GameLevel type - the game current being played
     * @param removedBalls - pointer to the Counter removedBalls object in the game
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * function name:hitEvent
     * Function Operation: in charge of removing the ball from the game.
     * this method being called when a ball hits the block that under the paddle,
     * which means the ball fell from the game.
     * @param beingHit - the death-block under the paddle
     * @param hitter - the ball that fell and needs to be removed from game
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }

}
