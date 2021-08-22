//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-05-01
 */
package sprites;

import biuoop.DrawSurface;
import game.GameLevel;
import information.Counter;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The ScoreIndicator will be in charge of displaying the current score.
 */
public class ScoreIndicator implements Sprite {

    private Counter scoreCounter;

    /**
     * construncor that created ScoreIndicator by scoreCounter.
     *
     * @param scoreCounter - a score counter.
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }


    /**
     * this method draw the sprite to the screen.
     *
     * @param d - the draw surface
     */
    public void drawOn(DrawSurface d) {
        String showScore = "Score: " + this.scoreCounter.getValue();
        d.setColor(Color.BLACK);
        d.drawText(350, 16, showScore, 19);
    }

    /**
     * get the score indicator.
     * @return - the score indicator.
     */
    public Counter getScoreCounter() {
        return scoreCounter;
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
     * remove the sprite object from the game.
     *
     * @param game - the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}



