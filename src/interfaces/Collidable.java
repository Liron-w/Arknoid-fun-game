//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-05-01
 */
package interfaces;


import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Ball;

/**
 * The Collidable interface will be used by things that can be collided with.
 * In this assignment, this means the Blocks and the Paddle.
 * we need to know their location and size (to know if we are colliding with it or not).
 * Second, we need to know what happens when the collision occurs.
 */

public interface Collidable {

    /**
     * the method return the "collision shape" of the object.
     *
     * @return - the collision rectangle
     */
    Rectangle getCollisionRectangle();


    /**
     * the method Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  - the collision point between the collided shape and the ball
     * @param currentVelocity - the current velocity of the ball
     * @param hitter          - the ball that make the hitting.
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
