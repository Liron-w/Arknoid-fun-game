//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-05-01
 */
package information;

import geometry.Point;
import interfaces.Collidable;

/**
 * this class should hold the information about the collision - the collision point an the collision object.
 */
public class CollisionInfo {

    //fields
    private Point collisionPoint;
    private Collidable collisionObject;

    // constructors

    /**
     * constructor that create a new "collision information" by the collision point and the collision object.
     *
     * @param collisionPoint  - the collision point between the ball and the collision object
     * @param collisionObject - the object that the ball collision with
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * the method return the point at which the collision occurs.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }


    /**
     * the method return the collidable object involved in the collision.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}

