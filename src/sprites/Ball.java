//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 3.0
 * @since 2020-05-30
 */
package sprites;

import biuoop.DrawSurface;
import game.GameEnvironment;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import information.CollisionInfo;
import interfaces.Sprite;

import java.awt.Color;

/**
 * this class support in a Ball (actually, a circle).
 * Balls have size (radius), color, and location (a Point).
 * Balls also know how to draw themselves on a DrawSurface.
 */
public class Ball implements Sprite {
    //fields
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment environment;

    // constructor

    /**
     * constructor with configurable the center of the ball, the radius and the color.
     *
     * @param center - the center point of the ball.
     * @param r      - the radius of the ball
     * @param color  - the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);

    }

    /**
     * constructor with configurable the center point of the ball, the radius, the color and the game environment.
     *
     * @param center      - the center point of the ball.
     * @param r           - the radius of the ball.
     * @param color       - the color of the ball.
     * @param environment - the game environment of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment environment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.environment = environment;
    }

    /**
     * constructor with configurable the center point of the ball, the radius and the color.
     *
     * @param x     - the x value of the center point of the ball.
     * @param y     - the y value of the center point of the ball.
     * @param r     - the radius of the ball
     * @param color - the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * constructor with configurable the center point of the ball, the radius, the color and the game environment.
     *
     * @param x           - the x value of the center point of the ball.
     * @param y           - the y value of the center point of the ball.
     * @param r           - the radius of the ball
     * @param color       - the color of the ball
     * @param environment - the game environment of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment environment) {
        this(new Point(x, y), r, color, environment);
    }

    // accessors

    /**
     * the method send the x value of the center point of the ball.
     * <p>
     *
     * @return int - the x value of the center point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * the method send the y value of the center point of the ball.
     * <p>
     *
     * @return int - the y value of the center point
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * the method send the length of the radius of the ball.
     * <p>
     *
     * @return int - the length of the radius
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * the method send the color of the ball.
     * <p>
     *
     * @return int - the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * the method draw the ball on the given DrawSurface.
     * <p>
     *
     * @param surface - the given DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        if (this.color == null) {
            this.color = Color.BLACK;
        }
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * the method set the velocity of the ball by dx and dy that given.
     * <p>
     *
     * @param dx - the changed in x
     * @param dy - the canged of the ball in y
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * the method return the velocity of the ball.
     * <p>
     *
     * @return velocity - the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * the method update the velocity of the ball by the velocity that given.
     * <p>
     *
     * @param v - velocity that given
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * the method give the game environment of the ball.
     *
     * @return the game environment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }

    /**
     * the method update the game environment of the ball.
     *
     * @param environment1 - game environment.
     */
    public void setGameEnvironment(GameEnvironment environment1) {
        this.environment = environment1;
    }

    /**
     * the method move the ball to "almost" the hit point, but just slightly before it.
     *
     * @param tarjectory     - the tarjectory line of the ball
     * @param collisionPoint - the collision point between the ball and the block
     * @param rectangle      - the rectangle of the block
     * @return the new center
     */
    public Point newCenter(Line tarjectory, Point collisionPoint, Rectangle rectangle) {
        Point upperLeft = rectangle.getUpperLeft();
        Point upperRight = new Point(rectangle.getUpperLeft().getX() + rectangle.getWidth(),
                rectangle.getUpperLeft().getY());
        Point bottomLeft = new Point(rectangle.getUpperLeft().getX(),
                rectangle.getUpperLeft().getY() + rectangle.getHeight());
        Point bottomRight = new Point(rectangle.getUpperLeft().getX() + rectangle.getWidth(),
                rectangle.getUpperLeft().getY() + rectangle.getHeight());

        Line up = new Line(upperLeft, upperRight);
        Line bottom = new Line(bottomLeft, bottomRight);
        Line left = new Line(upperLeft, bottomLeft);
        Line right = new Line(bottomRight, upperRight);

        Point newCenter = collisionPoint;

        //if it hits is from below or above, it should change the vertical direction
        if (up.isThePointOnTheLine(collisionPoint) || bottom.isThePointOnTheLine(collisionPoint)) {
            newCenter = new Point(tarjectory.end().getX(), 2 * collisionPoint.getY() - tarjectory.end().getY());
        }

        //if it is hit from the sides, it should change its horizontal direction.
        if (left.isThePointOnTheLine(collisionPoint) || right.isThePointOnTheLine(collisionPoint)) {
            newCenter = new Point(2 * collisionPoint.getX() - tarjectory.end().getX(), tarjectory.end().getY());
        }
        return newCenter;
    }


    /**
     * the method move the ball to "almost" the hit point, but just slightly before it.
     *
     * @param tarjectory     - the tarjectory line of the ball
     * @param collisionPoint - the collision point between the ball and the block
     * @return the new center
     */
    public Point newCenter(Line tarjectory, Point collisionPoint) {
        double xPoint;
        double yPoint;
        if (tarjectory.start().getX() > collisionPoint.getX()) {
            xPoint = collisionPoint.getX() + 0.7 * this.radius;
        } else if (tarjectory.start().getX() < collisionPoint.getX()) {
            xPoint = collisionPoint.getX() - 0.7 * this.radius;
        } else { // tarjectory.start().getX() == collisionPoint.getX()
            xPoint = collisionPoint.getX();
        }
        if (tarjectory.start().getY() > collisionPoint.getY()) {
            yPoint = collisionPoint.getY() + 0.7 * this.radius;
        } else if (tarjectory.start().getY() < collisionPoint.getY()) {
            yPoint = collisionPoint.getY() - 0.7 * this.radius;
        } else { // tarjectory.start().getY() == collisionPoint.getY()
            yPoint = collisionPoint.getY();
        }
        return new Point(xPoint, yPoint);

        /*double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        double speed = Math.sqrt(dx*dx+dy*dy);
        double ratio = speed / this.radius;
        double newDx = dx / ratio;
        double newDy = dy / ratio;
        Point newCenter = new Point(collisionPoint.getX()+newDx, collisionPoint.getY()+newDy);
        return newCenter;*/
        //this.velocity = new Velocity(newDx, newDy);

        //xPoint = 2 * (this.radius + collisionPoint.getX()) - this.getX();
        //xPoint = 2 * (collisionPoint.getX() - this.radius) - this.getX();
        //yPoint = 2 * (collisionPoint.getY() + this.radius) - this.getY();
        // yPoint = 2 * (collisionPoint.getY() - this.radius) - this.getY();
/*
        Point p;
        if((this.center.getX() + this.radius >= collisionPoint.getX()) && (this.getY() - this.radius >=
        collisionPoint.getY())){
            p = new Point(collisionPoint.getX()+this.radius, collisionPoint.getY() + this.radius);
        }
        if((this.center.getX() + this.radius <= collisionPoint.getX()) && (this.getY() + this.radius <=
        collisionPoint.getY())){
            p = new Point(collisionPoint.getX()+this.radius, collisionPoint.getY() - this.radius);
        }
        if((this.center.getX() + this.radius <= collisionPoint.getX()) && (this.getY() - this.radius >=
         collisionPoint.getY())){
            p = new Point(collisionPoint.getX()-this.radius, collisionPoint.getY() +this.radius);
        }
        else{//((this.center.getX() + this.radius <= collisionPoint.getX()) && (this.getY() + this.radius <=
        collisionPoint.getY())){
            p = new Point(collisionPoint.getX()+this.radius, collisionPoint.getY() + this.radius);
        }
        return p;*/
    }


    /**
     * the method move the ball on step.
     * compute the ball trajectory (the trajectory is "how the ball will move
     * without any obstacles" -- its a line starting at current location, and
     * ending where the velocity will take the ball if no collisions will occur).
     * Check (using the game environment) if moving on this trajectory will hit anything.
     * If no, then move the ball to the end of the trajectory.
     * Otherwise (there is a hit):
     * move the ball to "almost" the hit point, but just slightly before it.
     * notify the hit object (using its hit() method) that a collision occurred.
     * update the velocity to the new velocity returned by the hit() method.
     * <p>
     */
    public void moveOneStep() {
        Point startTrajectory = this.center;
        Point endTrajectory = new Point(this.center.getX() + this.velocity.getDx(),
                this.center.getY() + this.velocity.getDy());
        Line tarjectory = new Line(startTrajectory, endTrajectory);
        CollisionInfo info = this.environment.getClosestCollision(tarjectory);
        if (info != null) {
            while (info != null) {
                this.center = newCenter(tarjectory, info.collisionPoint());
                //this.center = newCenter(tarjectory, info.collisionPoint(),
                // info.collisionObject().getCollisionRectangle());
                this.velocity = info.collisionObject().hit(this, info.collisionPoint(), this.velocity);
                startTrajectory = this.center;
                endTrajectory = new Point(this.center.getX() + this.velocity.getDx(),
                        this.center.getY() + this.velocity.getDy());
                tarjectory = new Line(startTrajectory, endTrajectory);
                info = this.environment.getClosestCollision(tarjectory);
            }
            return;
        }
        //else: info = null , there is no collision
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * a Ball id sprite, this method add it to the game.
     *
     * @param g - the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * a Block is both a sprite and a collidable, this method remove it from the game.
     *
     * @param game - the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
