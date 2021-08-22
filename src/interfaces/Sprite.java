//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-05-01
 */
package interfaces;

import biuoop.DrawSurface;


/**
 * In computer graphics and games, a Sprite is a game object that can be drawn to the screen.
 * (and which is not just a background image).
 * Sprites can be drawn on the screen, and can be notified that time has passed.
 */
public interface Sprite {
    /**
     * this method draw the sprite to the screen.
     *
     * @param d - the draw surface
     */
    void drawOn(DrawSurface d);

    /**
     * this method notify the sprite that time has passed.
     */
    void timePassed();


}
