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

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * level 4- "Final Four".
 */
public class LevelFour implements LevelInformation {
    /**
     * default constructor.
     */
    public LevelFour() {

    }

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        velocities.add(Velocity.fromAngleAndSpeed(330, 5));
        velocities.add(Velocity.fromAngleAndSpeed(360, 5));
        velocities.add(Velocity.fromAngleAndSpeed(30, 5));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 17;
    }

    @Override
    public int paddleWidth() {
        return 70;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Point(20, 40), 760, 580, new Color(51, 153, 255));
    }

    @Override
    public List<Block> blocks() {
        int numOfRows = 5;
        int numOfBlocks = 15;
        double widthOfBlock = 50.6;
        int heightOfBlock = 20;

        List<Block> blocks = new ArrayList<Block>();
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 1; j <= numOfBlocks; j++) {
                Point upperLeftPoint = new Point(780 - j * widthOfBlock, 125 + i * heightOfBlock);
                blocks.add(new Block(upperLeftPoint, widthOfBlock + 0.5, heightOfBlock, colors[i]));
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }

    @Override
    public List<Point> ballsCenter() {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            points.add(new Point(400, 550));
        }
        return points;
    }
}
