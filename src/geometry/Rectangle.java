//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-05-01
 */

package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * this class support in a rectangle.
 * blocks have created by location, width and height.
 */
public class Rectangle {
    //fields
    private Point upperLeft;
    private double width;
    private double height;

    // constructors

    /**
     * constructor that create a new rectangle with location and width/height.
     *
     * @param upperLeft - the upper left point of the rectangle
     * @param width     - the with of the rectangle
     * @param height    - the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * constructor that create a new rectangle with location of the upper left ant bottom right points.
     *
     * @param upperLeft   - the upper left point of the rectangle
     * @param bottomRight - the bottom right point of the rectangle
     */
    public Rectangle(Point upperLeft, Point bottomRight) {
        this.upperLeft = upperLeft;
        this.width = bottomRight.getX() - upperLeft.getX();
        this.height = bottomRight.getY() - upperLeft.getY();
    }

    /**
     * the method Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line - the line in front of it the intersections points with the rectangle checked.
     * @return a list with the intersection points between this rectangle and the line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point bottomLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point bottomRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);

        Line[] rectangleSides = new Line[4];
        rectangleSides[0] = new Line(this.upperLeft, upperRight);
        rectangleSides[1] = new Line(this.upperLeft, bottomLeft);
        rectangleSides[2] = new Line(bottomLeft, bottomRight);
        rectangleSides[3] = new Line(bottomRight, upperRight);

        List<Point> pointList = new ArrayList<>();
        for (int i = 0; i < rectangleSides.length; i++) {
            if (line.isIntersecting(rectangleSides[i])) {
                pointList.add(line.intersectionWith(rectangleSides[i]));
            }
        }
        return pointList;
    }


    /**
     * the method return the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * the method return the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * the method returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}