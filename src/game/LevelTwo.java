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
 * level 2- "Wide Easy".
 */
public class LevelTwo implements LevelInformation {
    /**
     * default constructor.
     */
    public LevelTwo() {

    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        int angle = 300;
        for (int i = 0, j = 300; i < this.numberOfBalls(); i++) {
            velocities.add(Velocity.fromAngleAndSpeed(j % 360, 5));
            j += 12;
            if (j == 360) {
                j += 12;
            }
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Point(20, 40), 760, 580, Color.WHITE);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        double myWidth = 50.6;
        Color[] colors = {Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW, Color.GREEN,
                Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE, Color.PINK, Color.PINK, Color.CYAN, Color.CYAN};
        for (int i = 1; i <= this.numberOfBlocksToRemove(); i++) {
            Point upperLeftPoint = new Point(780 - i * myWidth, 250);
            blocks.add(new Block(upperLeftPoint, myWidth + 0.5, 40, colors[i - 1]));

        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
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
