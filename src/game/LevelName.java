//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-06-12
 */
package game;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The LevelName will be in charge of displaying the current level name.
 */
public class LevelName implements Sprite {

    private String levelName;
    private Color color;

    /**
     * define the level name by the string that given.
     *
     * @param levelName - the string of the level name.
     */
    public LevelName(String levelName) {
        this.levelName = levelName;
        this.color = Color.BLACK;
    }


    /**
     * this method draw the sprite to the screen.
     *
     * @param d - the draw surface
     */
    public void drawOn(DrawSurface d) {
        String name = "Level Name: " + this.levelName;
        d.setColor(this.color);
        d.drawText(500, 16, name, 19);
    }


    /**
     * this method notify the sprite that time has passed.
     */
    public void timePassed() {
    }

    /**
     * add the sprite object to the game.
     *
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * remove the sprite object to the game.
     *
     * @param g - the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
