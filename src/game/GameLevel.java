//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */

package game;

import animation.Animation;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.AnimationRunner;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import hitlisteners.BallRemover;
import hitlisteners.BlockRemover;
import hitlisteners.ScoreTrackingListener;
import information.Counter;
import information.SpriteCollection;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.Sprite;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import sprites.ScoreIndicator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this class create a game object, initializes and runs it.
 * The game should include a paddle (which is controlled by the left and right arrows), two balls, and blocks.
 */
public class GameLevel implements Animation {

    //fields
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter ballsCounter;
    private ScoreIndicator score;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation levelInformation;


    /**
     * constructor that create a game by the level information, the animation runner, the keyboard sensor.
     * and the score indicator.
     *
     * @param levelInformation - the level information
     * @param ar               - the animation runner.
     * @param ks               - the keyboard sensor
     * @param sc               - the score indicator.
     */

    public GameLevel(LevelInformation levelInformation, AnimationRunner ar, KeyboardSensor ks, ScoreIndicator sc) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.score = sc;
        this.remainingBlocks = new Counter();
        this.ballsCounter = new Counter();
        this.runner = ar;
        this.keyboard = ks;
        this.levelInformation = levelInformation;
    }

    /**
     * add a collidable object to the collidable list.
     *
     * @param c - the collidable object
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add a sprite object to the sprite list.
     *
     * @param s - the prite object
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * return the ball counter.
     *
     * @return - the ball counter.
     */
    public Counter getBallsCounter() {
        return ballsCounter;
    }

    /**
     * return the remaining blocks counter.
     *
     * @return - the remaining blocks counter.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * create the blocks that be used as the screen borders.
     * the bottom of the screen will function as a "death region".
     */
    public void createBorders() {
        Block scoreBorder = new Block(new Point(0, 0), 800, 20, Color.WHITE);
        Block upperBorder = new Block(new Point(0, 20), 800, 20, Color.GRAY);
        Block bottomBorder = new Block(new Point(20, 600), 760, 20, Color.GRAY);
        bottomBorder.addHitListener(new BallRemover(this, this.ballsCounter));
        Block leftBorder = new Block(new Point(0, 20), 20, 580, Color.GRAY);
        Block rightBorder = new Block(new Point(780, 20), 20, 580, Color.GRAY);

        upperBorder.addToGame(this);
        bottomBorder.addToGame(this);
        leftBorder.addToGame(this);
        rightBorder.addToGame(this);

    }


    /**
     * Initialize a new game: create the Blocks, Balls and Paddle and add them to the game.
     */

    public void initialize() {
        //create blocks in the borders
        this.createBorders();

        //create balls - add the function in run
        this.addSprite(this.levelInformation.getBackground());

        LevelName levelName = new LevelName(this.levelInformation.levelName());
        levelName.addToGame(this);

        this.score.addToGame(this);

        //create paddle
        Paddle paddle = new Paddle(new Point(400 - this.levelInformation.paddleWidth() / 2, 565),
                this.levelInformation.paddleWidth(), 15, levelInformation.paddleSpeed(),
                new Color(255, 204, 51), keyboard);
        paddle.addToGame(this);

        HitListener blockCouner = new BlockRemover(this, this.remainingBlocks);
        HitListener scoreCounter = new ScoreTrackingListener(this.score.getScoreCounter());

        List<Block> blockList = new ArrayList<Block>(this.levelInformation.blocks());
        //add blocks to the game
        for (int b = 0; b < blockList.size(); b++) {
            blockList.get(b).addToGame(this);
            blockList.get(b).addHitListener(blockCouner);
            this.remainingBlocks.increase(1);
            blockList.get(b).addHitListener(scoreCounter);
        }

        this.createBallsOnTopOfPaddle(); // or a similar method
    }


    /**
     * draw the blocks that used as the screen borders.
     *
     * @param d - the draw surface
     */
    public void drawFrame(DrawSurface d) {
        d.setColor(new Color(0, 0, 153));
        d.fillRectangle(20, 20, 760, 580);
    }


    /**
     * Run the game -- start the animation loop.
     */
    public void run() {

        this.runner.run(new CountdownAnimation(2, 3, this.sprites)); // countdown before turn starts.
        // use our runner to run the current animation -- which is one turn of the game.
        this.running = true;
        this.runner.run(this);

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.environment.setDrawSurface(d); //need to check
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        if (this.ballsCounter.getValue() <= 0) {
            this.running = false;
            return;
        }
        if (this.remainingBlocks.getValue() <= 0) {
            // this.score.getScoreCounter().increase(100);
            this.running = false;
            return;
        }
    }

    /**
     * remove the collidable object from the collidable list.
     *
     * @param c - the collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove the sprite object from the sprite list.
     *
     * @param s - the sprite object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

@Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * create the balls on the top of the paddle from the level information.
     */
    public void createBallsOnTopOfPaddle() {
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(levelInformation.ballsCenter().get(i), 5, Color.WHITE, this.environment);
            ball.setVelocity(levelInformation.initialBallVelocities().get(i));
            ball.addToGame(this);
            this.ballsCounter.increase(1);

        }
    }

}
