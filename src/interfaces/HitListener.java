package interfaces;

import sprites.Ball;
import sprites.Block;

/**.
 * author: Eyal Styskin
 * Interface name:Animation
 * Interface operation:
 */
public interface HitListener {
    //
    // The hitter parameter is the Ball that's doing the hitting.

    /**
     * Function Name:hitEvent
     * Function Operation: This method is called whenever the beingHit.
     * object is hit.The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit - Block the was hit
     * @param hitter - the Ball type hits the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
