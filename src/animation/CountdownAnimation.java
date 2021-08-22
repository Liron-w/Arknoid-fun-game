//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */
package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import information.SpriteCollection;

import java.awt.Color;


/**
 * The CountdownAnimation will display the given gameScreen, for numOfSeconds seconds, and on top of them it will show.
 * a countdown from countFrom back to 1, where each number will appear on the screen for (numOfSeconds / countFrom)
 * seconds, before it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {

    private SpriteCollection gameScreen;
    private Sleeper sleeper;
    private boolean running;
    private int countFrom;
    private int countRemain;
    private double numOfSeconds;


    /**
     * constructor of count-down animation.
     *
     * @param numOfSeconds - number of seconds that the countdown will be displayed on the screenץ
     * @param countFrom    - the number from him we count down.
     * @param gameScreen   - the screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;
        this.sleeper = new Sleeper();
        this.running = false;
        this.countFrom = countFrom;
        this.countRemain = countFrom;
    }

    /**
     * doOneFrame(DrawSurface) is in charge of the logic of the animation in one frame.
     *
     * @param d - the draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        long startTime = System.currentTimeMillis(); // timing
        gameScreen.drawAllOn(d);
        d.setColor(Color.RED);
        if (this.countRemain > 0) {
            d.drawText(385, 300, Integer.toString(this.countRemain), 40);
            this.countRemain--;
        } else {
            d.drawText(385, 300, Integer.toString(this.countRemain), 40);
            this.running = true;
        }
        if (this.countRemain == 2) {
            return;
        }

        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = (long) (this.numOfSeconds * 1000) / this.countFrom - usedTime;
        if (milliSecondLeftToSleep > 0) {
            this.sleeper.sleepFor(milliSecondLeftToSleep);
        }
    }

    /**
     * shouldStop() is in charge of stopping condition.
     *
     * @return true if it should stop, otherwise - false
     */
    @Override
    public boolean shouldStop() {
        return this.running;
    }
}
