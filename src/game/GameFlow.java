//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */
package game;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import information.Counter;
import sprites.ScoreIndicator;

import java.util.List;

/**
 * This class will be in charge of creating the different levels, and moving from one level to the next.
 */
public class GameFlow {

    private AnimationRunner ar;
    private KeyboardSensor ks;
    private ScoreIndicator scoreIndicator;

    /**
     * constructor that create the game low by the animation runner and the keyboard sensor.
     *
     * @param ar - the animation runner.
     * @param ks - the keyboard sensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.ar = ar;
        this.ks = ks;
        this.scoreIndicator = new ScoreIndicator(new Counter());
    }

    /**
     * support for moving from one level to the next.
     *
     * @param levels - the list of the levels that should be run.
     */
    public void runLevels(List<LevelInformation> levels) {
        int i;
        for (i = 0; i < levels.size(); i++) {

            GameLevel level = new GameLevel(levels.get(i), this.ar, this.ks, scoreIndicator);
            level.initialize();

            while (level.getBallsCounter().getValue() > 0 && level.getRemainingBlocks().getValue() > 0) {
                level.run();
            }
            if (level.getRemainingBlocks().getValue() == 0) {
                this.scoreIndicator.getScoreCounter().increase(100);
            }
            if (level.getBallsCounter().getValue() == 0) {
                //this.ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY, new PauseScreen()));
                //this.ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY, new PauseScreen()));
                this.ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY,
                        new EndScreen(false, this.scoreIndicator.getScoreCounter())));
                break;
            }

        }
        if (i == levels.size()) {
            this.ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY,
                    new EndScreen(true, this.scoreIndicator.getScoreCounter())));

            //this.ar.run(new EndScreen(true, this.ks, this.scoreIndicator.getScoreCounter()));
        }
    }
}
