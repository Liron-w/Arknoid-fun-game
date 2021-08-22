//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-05-28
 */

package hitlisteners;


import game.GameLevel;
import information.Counter;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * a HitListener called BallRemover that will be in charge of removing balls, and updating an availabe-balls counter.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter ballsCounter;

    /**
     * constructor that create the ball remover by the game and the counter.
     *
     * @param game        - the game.
     * @param removedBall - the counter of the ball.
     */
    public BallRemover(GameLevel game, Counter removedBall) {
        this.game = game;
        this.ballsCounter = removedBall;
    }


    /**
     * Blocks that are hit should be removed from the game.
     *
     * @param beingHit - the block that hitten.
     * @param hitter   - the ball that hit on the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.ballsCounter.decrease(1);
    }
}
