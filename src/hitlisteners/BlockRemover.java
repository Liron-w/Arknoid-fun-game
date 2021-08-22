//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-05-30
 */
package hitlisteners;


import game.GameLevel;
import information.Counter;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;


/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count of the number of blocks
 * that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor that create the ball remover by the game and the counter.
     *
     * @param game          - the game.
     * @param removedBlocks - the counter of the ball.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     *
     * @param beingHit - the block that hitten.
     * @param hitter   - the ball that hit on the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(game);
        this.remainingBlocks.decrease(1);
    }
}
