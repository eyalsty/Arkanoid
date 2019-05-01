package listeners;

import animation.GameLevel;
import interfaces.HitListener;
import others.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * author: Eyal Styskin
 * Class name: BlockRemover
 * class operation:implements HitListener. this class uses the listener pattern
 * that will be in charge of removing blocks, and updating an availabe - blocks counter.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**.
     * Function Operation: Constructor for the members
     *
     * @param game          - GameLevel type - the game current being played
     * @param removedBlocks - pointer to the Counter removedBlocks object in the game
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.gameLevel = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * function name: hitEvent
     * Function Operation: in charge of removing block from game.
     * if block's hit points goes down to 0, he disappearing from the game.
     *
     * @param beingHit - Block that was hit by ball
     * @param hitter   - the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(this.gameLevel);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
        }
    }
}