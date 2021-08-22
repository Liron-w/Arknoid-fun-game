//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-05-30
 */
package interfaces;

import sprites.Ball;
import sprites.Block;

/**
 * Objects that want to be notified of hit events, should implement the HitListener interface.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit - the object that hitten.
     * @param hitter   - the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}