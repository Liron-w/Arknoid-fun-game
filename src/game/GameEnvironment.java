//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-05-01
 */
package game;

import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.PointByDistanceComperator;
import geometry.Rectangle;
import information.CollisionInfo;
import interfaces.Collidable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * The GameEnvironment class will be a collection of such things.
 * The ball will know the game environment, and will use it to check for collisions and direct its movement.
 */
public class GameEnvironment {
    private List<Collidable> collidables;
    private DrawSurface drawSurface;

    /**
     * constructor that create new arrayList for the collidables in the game.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * constructor that get the list of collidables objects.
     *
     * @param collidables - the list of collidables.
     */
    public GameEnvironment(List<Collidable> collidables) {
        this.collidables = collidables;
    }


    /**
     * add the given collidable to the environment.
     *
     * @param c - a collidable object
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);

    }

    /**
     * the method return the list of collidables objects.
     *
     * @return the list of collidables objects.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    //

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory - the line of the moving.
     * @return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> collisionPoints = new ArrayList<>();
        List shapes = new ArrayList();
        //add only the collision points and the collidable shapes in the trajectory to lists
        for (int i = 0; i < this.collidables.size(); i++) {
            Collidable shape = this.collidables.get(i);
            Rectangle rectangle = shape.getCollisionRectangle();

            Point check = trajectory.closestIntersectionToStartOfLine(rectangle);
            if (check != null) {
                shapes.add(shape);
                collisionPoints.add(check);
            }
        }
        //if there is no collision
        if (collisionPoints.isEmpty() || shapes.isEmpty()) {
            return null;
        }
        //else - there is collision
        //copy the points to a new list and sort them by the distance to the start point
        List<Point> collisionPointsSort = new ArrayList<>();
        collisionPointsSort.addAll(collisionPoints);

        PointByDistanceComperator distanceSort = new PointByDistanceComperator(trajectory.start());
        Collections.sort(collisionPointsSort, distanceSort);

        //the index of the closest point in the origin list is the first point in the sort list
        int i = collisionPoints.indexOf(collisionPointsSort.get(0));
        CollisionInfo collisionInfo = new CollisionInfo(collisionPoints.get(i), (Collidable) shapes.get(i));
        return collisionInfo;
    }

    /**
     * the method return the draw surface of the game.
     *
     * @return the draw surface of the game.
     */
    public DrawSurface getDrawSurface() {
        return this.drawSurface;
    }

    /**
     * the method update the draw surface of the game by the draw surface that given.
     *
     * @param d - the draw surface that given.
     */
    public void setDrawSurface(DrawSurface d) {
        this.drawSurface = d;
    }

    /**
     * remove the collidable object from the collidable list.
     *
     * @param c - the collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

}
