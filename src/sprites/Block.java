//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 3.0
 * @since 2020-05-30
 */

package sprites;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this class support in a Block.
 * blocks have created by rectangle and color.
 * in our game, blocks are Collidables and Sprites.
 * blocks also know how to draw themselves on a DrawSurface.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //fields
    private Rectangle rectangle;
    private java.awt.Color color;
    private List<HitListener> hitListeners;

    // constructors

    /**
     * constructor that create a new block from rectangle and color.
     *
     * @param rectangle - the location, width and height of the block
     * @param color     - the color of the block
     */
    public Block(Rectangle rectangle, java.awt.Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * constructor that create a new block from a location,width and height of the rectangle and color.
     *
     * @param upperLeft - the upper left corner of the block location
     * @param width     - the width of the block
     * @param height    - the height of the block
     * @param color     - the color of the block
     */
    public Block(Point upperLeft, double width, double height, java.awt.Color color) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }


    /**
     * the method return the "collision shape" of the object.
     *
     * @return the rectangle of this block
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }


    /**
     * the method Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  - the collision point between the block and the ball
     * @param currentVelocity - the current velocity of the ball
     * @param hitter          - the ball that make the hitting.
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Point upperLeft = this.rectangle.getUpperLeft();
        Point upperRight = new Point(this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth(),
                this.rectangle.getUpperLeft().getY());
        Point bottomLeft = new Point(this.rectangle.getUpperLeft().getX(),
                this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight());
        Point bottomRight = new Point(this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth(),
                this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight());

        //the lines of the rectangle of the block
        Line up = new Line(upperLeft, upperRight);
        Line bottom = new Line(bottomLeft, bottomRight);
        Line left = new Line(upperLeft, bottomLeft);
        Line right = new Line(bottomRight, upperRight);

        //if the collision point is on one of the corners - the velocity should changed in the both directions
        if ((up.isThePointOnTheLine(collisionPoint) || bottom.isThePointOnTheLine(collisionPoint))
                && (left.isThePointOnTheLine(collisionPoint) || right.isThePointOnTheLine(collisionPoint))) {
            currentVelocity.setVelocity(new Velocity(-1 * dx, -1 * dy));
        }


        //if it hits is from below or above, it should change the vertical direction.
        if (up.isThePointOnTheLine(collisionPoint) || bottom.isThePointOnTheLine(collisionPoint)) {
            currentVelocity.setVelocity(new Velocity(dx, -1 * dy));
        }

        //if it is hit from the sides, it should change its horizontal direction.
        if (left.isThePointOnTheLine(collisionPoint) || right.isThePointOnTheLine(collisionPoint)) {
            currentVelocity.setVelocity(new Velocity(-1 * dx, dy));
        }
        //notify about the hit
        this.notifyHit(hitter);
        //if there was no hit to any of the block's sides
        return currentVelocity;
    }

    /**
     * the method draw the blocks on the draw surface.
     *
     * @param surface - the draw surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        if (this.color == null) {
            this.color = Color.BLACK;
        }
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }


    /**
     * If we wanted to have animated blocks (for example, blocks that change their color over time,
     * or have a different graphics effect) we could use the timePassed method to implement this behavior.
     * for now, we do nothing.
     */
    @Override
    public void timePassed() {

    }

    /**
     * a Block is both a sprite and a collidable, this method add it to the game.
     *
     * @param g - the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * a Block is both a sprite and a collidable, this method remove it from the game.
     *
     * @param game - the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }


    /**
     * Add hl as a listener to hit events.
     *
     * @param hl - hit listener
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl - hit listener
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify all listeners about a hit event.
     *
     * @param hitter - the ball that hit.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}

