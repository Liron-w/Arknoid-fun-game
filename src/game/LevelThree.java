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
 * level 3- "Green 3".
 */
public class LevelThree implements LevelInformation {
    /**
     * default constructor.
     */
    public LevelThree() {

    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        velocities.add(Velocity.fromAngleAndSpeed(30, 4));
        velocities.add(Velocity.fromAngleAndSpeed(330, 4));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 15;
    }

    @Override
    public int paddleWidth() {
        return 70;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Point(20, 40), 760, 580, new Color(0, 153, 0));
    }

    @Override
    public List<Block> blocks() {
        int numOfRows = 5;
        int widthOfBlock = 55;
        int heightOfBlock = 25;
        List<Block> blocks = new ArrayList<Block>();
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        int l = 10;
        for (int i = 0; i < numOfRows; i++) {
            for (int j = l, r = 1; j > 0; j--, r++) {
                Point upperLeftPoint = new Point(780 - r * widthOfBlock, 125 + i * heightOfBlock);
                blocks.add(new Block(upperLeftPoint, widthOfBlock, heightOfBlock, colors[i]));
            }
            l--;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
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
