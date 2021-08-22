//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */
package game;

import geometry.Point;
import geometry.Velocity;
import interfaces.Sprite;
import sprites.Block;
import java.util.List;

/**
 * The LevelInformation interface specifies the information required to fully describe a level.
 */
public interface LevelInformation {
    /**
     * the number of balls in the level.
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * @return a list with the velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * the paddle speed.
     * @return - the paddle speed.
     */
    int paddleSpeed();

    /**
     * the paddle width.
     * @return - the paddle width.
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     * @return - the level name will be displayed
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return - a sprite with the background of the level
     */

    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     * @return the list of the blocks.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * @return - the number of blocks that should be removed
     */
    int numberOfBlocksToRemove();

    /**
     * list with the balls center.
     * @return - a list with the balls center.
     */
    List<Point> ballsCenter();

}