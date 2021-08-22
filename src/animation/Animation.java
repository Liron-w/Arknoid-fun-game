//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */
package animation;

import biuoop.DrawSurface;

/**
 * the interface Animation is in charge of the logic of the animation - draw frame and stop the animation.
 */
public interface Animation {

    /**
     * doOneFrame(DrawSurface) is in charge of the logic of the animation in one frame.
     *
     * @param d - the draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * shouldStop() is in charge of stopping condition.
     *
     * @return true if it should stop, otherwise - false
     */
    boolean shouldStop();
}
