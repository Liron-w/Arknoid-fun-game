//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-05-01
 */
package geometry;

/**
 * this class support in a point.
 * A point has an x and a y value, and can measure the distance to other points, and if it is equal to another point.
 */
public class Point {
    //fields
    private double x;
    private double y;

    /**
     * constructor with configurable the x value and the y value of the point.
     *
     * @param x - the x value of the point.
     * @param y - the y value of the point.
     */
    // constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * the method calculate the distance of this point to the other point.
     * <p>
     *
     * @param other - the second point
     * @return double - the distance between the points.
     */
    public double distance(Point other) {
        double d = Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
        return d;
    }

    /**
     * the method check if the points are equal.
     * <p>
     *
     * @param other - the second point
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }

    /**
     * the method return the x value of the point.
     * <p>
     *
     * @return double - the x value of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * the method return the y value of the point.
     * <p>
     *
     * @return double - the y value of the point.
     */
    public double getY() {
        return this.y;
    }
}

