//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-05-01
 */
package information;


import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * a SpriteCollection will hold a collection of sprites.
 */
public class SpriteCollection {

    //field
    private List<Sprite> spriteCollection;

    //constructors:

    /**
     * create sprite objects collection in array list.
     */
    public SpriteCollection() {
        this.spriteCollection = new ArrayList<>();
    }

    /**
     * create sprite objects collection from array list.
     *
     * @param spriteCollection - list of sprites.
     */
    public SpriteCollection(List<Sprite> spriteCollection) {
        this.spriteCollection = spriteCollection;
    }

    /**
     * add a sprite object to the list.
     *
     * @param s - the sprite object.
     */
    public void addSprite(Sprite s) {
        this.spriteCollection.add(s);
    }


    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.spriteCollection.size(); i++) {
            Sprite sprite = this.spriteCollection.get(i);
            sprite.timePassed();
        }
        /*
        for (Sprite sprite : this.spriteCollection) {
            sprite.timePassed();
        }
         */
    }


    /**
     * call drawOn(d) on all sprites.
     *
     * @param d - the draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.spriteCollection) {
            sprite.drawOn(d);
        }
    }

    /**
     * remove the sprite object from the sprite list.
     *
     * @param s - the sprite object.
     */
    public void removeSprite(Sprite s) {
        this.spriteCollection.remove(s);
    }
}