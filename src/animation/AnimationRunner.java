//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */
package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The AnimationRunner takes an Animation object and runs it.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * constructor thar create AnimationRunner by the gui and the number of frames per second.
     *
     * @param gui             - the gui.
     * @param framesPerSecond - the number of frames per second.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new Sleeper();

    }


    /**
     * Run the game -- start the animation loop.
     *
     * @param animation - the animation that we running.
     */
    public void run(Animation animation) {

        int millisecondsPerFrame = 1000 / this.framesPerSecond;

        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }

            /*
            if (this.ballsCounter.getValue() <= 0) {
                break;
            }
            if (this.remainingBlocks.getValue() <= 0) {
                this.score.increase(100);
                break;
            }
            gui.close();
            return;

             */
        }


    }
}