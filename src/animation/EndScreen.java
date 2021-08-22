//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */
package animation;


import biuoop.DrawSurface;
import information.Counter;

/**
 * Once the game is over (the player died or he managed to clear all the levels), we will display the final score.
 * If the game ended with the player dying (i.e. all balls fall off the screen), the end screen should display the
 * message "Game Over. Your score is X" (X being the final score).
 * If the game ended by clearing all the levels, the screen should display "You Win! Your score is X".
 * The "end screen" should persist until the space key is pressed.
 */
public class EndScreen implements Animation {
    private boolean win;
    private Counter scoreCounter;
    private boolean stop;

    /**
     * constructor that define the end screen by the counter and the information if the player win or loose.
     *
     * @param win          - the if the player win, otherwise - false.
     * @param scoreCounter - the score counter.
     */
    public EndScreen(boolean win, Counter scoreCounter) {
        this.win = win;
        this.scoreCounter = scoreCounter;
        this.stop = false;
    }

    /**
     * doOneFrame(DrawSurface) is in charge of the logic of the animation in one frame.
     *
     * @param d - the draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (win) {
            d.drawText(d.getWidth() / 5, d.getHeight() / 2, "You Win! Your score is "
                    + this.scoreCounter.getValue(), 40);
        } else {
            d.drawText(d.getWidth() / 5, d.getHeight() / 2, "Game Over. Your score is "
                    + this.scoreCounter.getValue(), 40);
            this.stop = true;

        }

        //if (this.keyboardSensor.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
