//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-05-30
 */

package hitlisteners;

import information.Counter;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * a HitListener called ScoreTrackingListener - update the counter when blocks are being hit and removed.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor that create the score tracking listener by the score counter.
     *
     * @param scoreCounter - a score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Blocks that are hit should be removed from the game.
     *
     * @param beingHit - the block that hitten.
     * @param hitter   - the ball that hit on the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
