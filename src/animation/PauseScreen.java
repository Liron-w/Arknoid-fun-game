//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */
package animation;

import biuoop.DrawSurface;

/**
 * an option to pause the game when pressing the p key.
 * this is simple animation, that will display a screen with the message paused -- press space to continue
 * until a key is pressed.
 */
public class PauseScreen implements Animation {

    private boolean stop;

    /**
     * constructor - initial the boolean var to false.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * doOneFrame(DrawSurface) is in charge of the logic of the animation in one frame.
     *
     * @param d - the draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        //if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }

    /**
     * shouldStop() is in charge of stopping condition.
     *
     * @return true if it should stop, otherwise - false
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}