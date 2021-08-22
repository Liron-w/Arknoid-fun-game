//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-04-03
 */
package geometry;

/**
 * this class support in a Velocity.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {

    //fields
    private double dx;
    private double dy;

    /**
     * constructor with configurable the x value and the y value of the point.
     *
     * @param dx - the change in position on the `x` axes.
     * @param dy - the change in position on the `y` axes.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * the method gets angle and speed and return the velocity by the change in position on the `x` and the `y` axes.
     * <p>
     *
     * @param angle - the angle of the velocity
     * @param speed - the speed of the velocity
     * @return velocity - the change in position on the `x` and the `y` axes.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
       /* double dx = speed * Math.cos(Math.toRadians(angle));
        double dy = -1 * speed * Math.sin(Math.toRadians(angle));
        return new Velocity(dx, dy);
        */
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -1 * speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * the method return the change in position on the `x` axes.
     * <p>
     *
     * @return double - the change in position on the `x` axes.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * the method return the change in position on the `y` axes.
     * <p>
     *
     * @return double - the change in position on the `y` axes.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * the method take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * <p>
     *
     * @param p - point with position (x,y).
     * @return point with position (x+dx, y+dy).
     */
    //
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * the methos update the velocity by the velocity that given.
     *
     * @param v - the velocity that given.
     */
    public void setVelocity(Velocity v) {
        this.dy = v.getDy();
        this.dx = v.getDx();
    }

    /**
     * the method give the speed.
     *
     * @return the speed
     */
    public double getSpeed() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }
}

