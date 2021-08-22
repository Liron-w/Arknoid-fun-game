//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 2.0
 * @since 2020-05-01
 */

package geometry;

import java.util.List;

/**
 * this class support in a line.
 * A line (actually a line-segment) connects two points -- a start point and an end point.
 * Lines have lengths, and may intersect with other lines.
 * It can also tell if it is the same as another line segment.
 */
public class Line {
    //fields
    private Point start;
    private Point end;

    // constructors

    /**
     * constructor with configurable the start point and the end point of the line.
     *
     * @param start - the start point of the line.
     * @param end   - the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor with configurable the values of the start point and the end point of the line.
     *
     * @param x1 - the x value of the start point of the line.
     * @param y1 - the y value of the start point of the line.
     * @param x2 - the x value of the end point of the line.
     * @param y2 - the y value of the end point of the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        //create the start point and the end point
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * the method return the length of the line.
     * <p>
     *
     * @return double - the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * the method returns the middle point of the line.
     * <p>
     *
     * @return point - the middle point of the line
     */
    public Point middle() {
        double middleX = (this.start.getX() + this.end.getX()) / 2;
        double middleY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * the method returns the start point of the line.
     * <p>
     *
     * @return point - the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * the method returns the end point of the line.
     * <p>
     *
     * @return point - the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * the method returns true if the lines intersect, false otherwise.
     * <p>
     *
     * @param other - the line in front of it is checking intersection
     * @return - true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        Point intersection = intersectionWith(other);
        return intersection != null;
    }

    /**
     * the method returns true if this line is only point, false otherwise.
     * <p>
     *
     * @return - true if this line is only point, false otherwise
     */
    private boolean isThisOnlyPoint() {
        return this.start.equals(this.end);
    }

    /**
     * the method calculate the intersection point in the case that this line is only point.
     * <p>
     *
     * @param other - the line in front of it is checking intersection
     * @param m2    - the slope of the other line
     * @param b2    - the intersection with y axis of the other line
     * @return - the intersection point if there is in the domain, else - return null
     */
    private Point calcThisOnlyPointCase(Line other, double m2, double b2) {
        double xPoint, yPoint;
        if (this.start.getY() == m2 * this.start.getX() + b2) {
            //this point is on the other line
            xPoint = this.start.getX();
            yPoint = this.start.getY();
            if (!isInDomain(xPoint, this.start.getX(), this.end.getX(), other.start.getX(), other.end.getX())
                    || !isInDomain(yPoint, this.start.getY(), this.end.getY(), other.start.getY(), other.end.getY())) {
                return null;
            }
            return new Point(xPoint, yPoint);
        } else {
            //this point is not on the other line
            return null;
        }
    }

    /**
     * the method returns true if the other line is only point, false otherwise.
     * <p>
     *
     * @param other - the line in front of it is checking intersection.
     * @return - true if the other line is only point, false otherwise
     */
    private boolean isOtherOnlyPoint(Line other) {
        return other.start.equals(other.end);
    }

    /**
     * the method calculate the intersection point in the case that the other line is only point.
     * <p>
     *
     * @param other - the line in front of it is checking intersection
     * @param m1    - the slope of this line
     * @param b1    - the intersection with y axis of this line
     * @return - the intersection point if there is in the domain, else - return null
     */
    private Point calcOtherIsOnlyPointCase(Line other, double m1, double b1) {
        double xPoint, yPoint;
        if (other.start.getY() == m1 * other.start.getX() + b1) {
            //the other point is on this line
            xPoint = other.start.getX();
            yPoint = other.start.getY();
            if (!isInDomain(xPoint, this.start.getX(), this.end.getX(), other.start.getX(), other.end.getX())
                    || !isInDomain(yPoint, this.start.getY(), this.end.getY(), other.start.getY(), other.end.getY())) {
                return null;
            }
            return new Point(xPoint, yPoint);
        }
        return null;
    }

    /**
     * the method check if the two lines is the same line but when one is over the second is start.
     * and then there is intersection point
     * <p>
     *
     * @param other - the line in front of it is checking intersection
     * @param m1    - the slope of this line
     * @param b1    - the intersection with y axis of this line
     * @param m2    - the slope of the other line
     * @param b2    - the intersection with y axis of the other line
     * @return - true if The lines don't unite but continue one another, otherwise - false.
     */
    private boolean isContinuesLines1case(Line other, double m1, double b1, double m2, double b2) {
        return (((m1 == m2 && b1 == b2) && (this.start.equals(other.end) && !(this.end.equals(other.start))))
                || ((m1 == m2 && b1 == b2) && (this.start.equals(other.start) && !(this.end.equals(other.end)))));
    }

    /**
     * the method calculate the intersection point in the case that  The lines don't unite but continue one another.
     * <p>
     * * @param other - the line in front of it is checking intersection
     *
     * @param m1 - the slope of this line
     * @param b1 - the intersection with y axis of this line
     * @param m2 - the slope of the other line
     * @param b2 - the intersection with y axis of the other line
     * @return - the intersection point
     */
    private Point calcContinuesLinesCase1(Line other, double m1, double b1, double m2, double b2) {
        double xPoint, yPoint;
        xPoint = this.start.getX();
        yPoint = this.start.getY();
        if (this.start.equals(other.start)) {
            //if the lines ones come together
            if ((isInDomain(this.end.getX(), this.start.getX(), this.end.getX(), other.start.getX(), other.end.getX()))
                    || (isInDomain(other.end.getX(), this.start.getX(), this.end.getX(), other.start.getX(),
                    other.end.getX()))) {
                return null;
            }
            return new Point(xPoint, yPoint);
        }
        if (this.start.equals(other.end)) {
            if ((isInDomain(this.end.getX(), this.start.getX(), this.end.getX(), other.start.getX(), other.end.getX()))
                    || (isInDomain(other.start.getX(), this.start.getX(), this.end.getX(), other.start.getX(),
                    other.end.getX()))) {
                return null;
            }
            return new Point(xPoint, yPoint);
        }
        return new Point(xPoint, yPoint);
    }

    /**
     * the method check if the two lines is the same line but when one is over the second is start.
     * and then there is intersection point
     * <p>
     *
     * @param other - the line in front of it is checking intersection
     * @param m1    - the slope of this line
     * @param b1    - the intersection with y axis of this line
     * @param m2    - the slope of the other line
     * @param b2    - the intersection with y axis of the other line
     * @return - true if The lines don't unite but continue one another, otherwise - false.
     */
    private boolean isContinuesLines2case(Line other, double m1, double b1, double m2, double b2) {
        return (((m1 == m2 && b1 == b2) && (this.end.equals(other.start) && !(this.start.equals(other.end))))
                || ((m1 == m2 && b1 == b2) && (this.end.equals(other.end) && !(this.start.equals(other.start)))));
    }

    /**
     * the method calculate the intersection point in the case that  The lines don't unite but continue one another.
     * <p>
     *
     * @param other - the line in front of it is checking intersection
     * @param m1    - the slope of this line
     * @param b1    - the intersection with y axis of this line
     * @param m2    - the slope of the other line
     * @param b2    - the intersection with y axis of the other line
     * @return - the intersection point
     */
    private Point calcContinuesLinesCase2(Line other, double m1, double b1, double m2, double b2) {
        double xPoint, yPoint;
        xPoint = this.end.getX();
        yPoint = this.end.getY();
        if (this.end.equals(other.start)) {
            //if the lines ones come together
            if ((isInDomain(this.start.getX(), this.start.getX(), this.end.getX(), other.start.getX(),
                    other.end.getX())) || (isInDomain(other.start.getX(), this.start.getX(), this.end.getX(),
                    other.start.getX(), other.end.getX()))) {
                return null;
            }
            return new Point(xPoint, yPoint);
        }
        if (this.end.equals(other.end)) {
            if ((isInDomain(this.start.getX(), this.start.getX(), this.end.getX(), other.start.getX(),
                    other.end.getX())) || (isInDomain(other.end.getX(), this.start.getX(), this.end.getX(),
                    other.start.getX(), other.end.getX()))) {
                return null;
            }
            return new Point(xPoint, yPoint);
        }
        return new Point(xPoint, yPoint);
    }

    /**
     * the method check if the two lines is have the same slope.
     * <p>
     *
     * @param m1 - the slope of this line
     * @param m2 - the slope of the other line
     * @return - true if The slopes equals, otherwise - false.
     */
    private boolean isTheSlopeEqual(double m1, double m2) {
        return m1 == m2;
    }

    /**
     * the method check if this line is vertical line (x=number).
     * <p>
     *
     * @param m1 - the slope of this line
     * @return - true if The slopes is infinity, otherwise - false.
     */
    private boolean isVerticalLineCase1(double m1) {
        return Double.isInfinite(m1);
    }

    /**
     * the method calculate the intersection point in the case that this line is vertical line.
     * <p>
     *
     * @param other - the line in front of it is checking intersection
     * @param m2    - the slope of the other line
     * @param b2    - the intersection with y axis of the other line
     * @return - the intersection point if there is in the domain, else - return null
     */
    private Point calcVerticalLineCase1(Line other, double m2, double b2) {
        double xPoint, yPoint;
        //if this line is : x=number
        xPoint = this.start.getX();
        yPoint = m2 * xPoint + b2;
        if (!isInDomain(xPoint, this.start.getX(), this.end.getX(), other.start.getX(), other.end.getX())
                || !isInDomain(yPoint, this.start.getY(), this.end.getY(), other.start.getY(), other.end.getY())) {
            return null;
        }
        return new Point(xPoint, yPoint);
    }

    /**
     * the method check if the other line is vertical line (x=number).
     * <p>
     *
     * @param m2 - the slope of the other line
     * @return - true if The slopes is infinity, otherwise - false.
     */
    private boolean isVerticalLineCase2(double m2) {
        //if other line is : x=number
        return Double.isInfinite(m2);
    }

    /**
     * the method calculate the intersection point in the case that the other line is vertical line.
     * <p>
     *
     * @param other - the line in front of it is checking intersection
     * @param m1    - the slope of this line
     * @param b1    - the intersection with y axis of this line
     * @return - the intersection point if there is in the domain, else - return null
     */
    private Point calcVerticalLineCase2(Line other, double m1, double b1) {
        double xPoint, yPoint;
        //if this line is : x=number
        xPoint = other.start.getX();
        yPoint = m1 * xPoint + b1;
        if (!isInDomain(xPoint, this.start.getX(), this.end.getX(), other.start.getX(), other.end.getX())
                || !isInDomain(yPoint, this.start.getY(), this.end.getY(), other.start.getY(), other.end.getY())) {
            return null;
        }
        return new Point(xPoint, yPoint);
    }

    /**
     * the method check if there is intersection point between the two lines in the range.
     * <p>
     *
     * @param other - the line in front of it is checking intersection
     * @param m1    - the slope of this line
     * @param b1    - the intersection with y axis of this line
     * @param m2    - the slope of the other line
     * @param b2    - the intersection with y axis of the other line
     * @return - the intersection point if there is , else - return null
     */
    private Point calcLinesIntersectionCase(Line other, double m1, double b1, double m2, double b2) {
        double xPoint, yPoint;
        //the both lines is in the form : y=m*x+b
        xPoint = (b2 - b1) / (m1 - m2);
        yPoint = m1 * xPoint + b1;
        if (!isInDomain(xPoint, this.start.getX(), this.end.getX(), other.start.getX(), other.end.getX())
                || !isInDomain(yPoint, this.start.getY(), this.end.getY(), other.start.getY(), other.end.getY())) {
            return null;
        }
        return new Point(xPoint, yPoint);
    }

    /**
     * the method check if there is intersection point between the two lines in the range.
     * <p>
     *
     * @param other - the line in front of it is checking intersection
     * @return - the intersection point if there is in the domain, else - return null
     */
    public Point intersectionWith(Line other) {
        //this line: y=m1*x+b1 , other line: y=m2*x+b2
        double m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX()); //the slope
        double m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        double b1 = -1 * m1 * this.start.getX() + this.start.getY();
        double b2 = -1 * m2 * other.start.getX() + other.start.getY();
        //checks:
        if (isThisOnlyPoint()) {
            return calcThisOnlyPointCase(other, m2, b2);
        } else if (isOtherOnlyPoint(other)) {
            return calcOtherIsOnlyPointCase(other, m1, b1);
        } else if (isContinuesLines1case(other, m1, b1, m2, b2)) {
            return calcContinuesLinesCase1(other, m1, b1, m2, b2);
        } else if (isContinuesLines2case(other, m1, b1, m2, b2)) {
            return calcContinuesLinesCase2(other, m1, b1, m2, b2);
        } else if (isTheSlopeEqual(m1, m2)) {
            return null; //if the slopes are equal - the lines will never intersection
        } else if (isVerticalLineCase1(m1)) {
            return calcVerticalLineCase1(other, m2, b2);
        } else if (isVerticalLineCase2(m2)) {
            return calcVerticalLineCase2(other, m1, b1);
        } else {
            return calcLinesIntersectionCase(other, m1, b1, m2, b2);
        }
    }

    /**
     * the method check if the intersection point between the two lines is in the range.
     * <p>
     *
     * @param x           - the x value of the intersection point
     * @param xThisStart  - the x value of the start point of this line
     * @param xThisEnd    - the x value of the end point of this line
     * @param xOtherStart - the x value of the start point of the other line
     * @param xOtherEnd   - the x value of the end point of the other line
     * @return true - if the point is in the domain, false - if the point isn't in the domain
     */
    public boolean isInDomain(double x, double xThisStart, double xThisEnd, double xOtherStart, double xOtherEnd) {
        double epsilon = Math.pow(10, -5);
        return ((x + epsilon >= xThisStart && x - epsilon <= xThisEnd)
                || (x - epsilon <= xThisStart && x + epsilon >= xThisEnd))
                && ((x + epsilon >= xOtherStart && x - epsilon <= xOtherEnd)
                || (x - epsilon <= xOtherStart && x + epsilon >= xOtherEnd));

        /*
        return ((x >= xThisStart && x <= xThisEnd) || (x <= xThisStart && x >= xThisEnd))
                && ((x >= xOtherStart && x <= xOtherEnd) || (x <= xOtherStart && x >= xOtherEnd));

         */
    }

    /**
     * the method check if the lines are equals - the start points equals and the end points equal.
     * <p>
     * check if the value of x and y in the points
     *
     * @param other - the line in front of it is checking equal
     * @return - rue is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end));
    }

    //

    /**
     * the method check what is the closest intersection point between the start of this line and the rectangle.
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect - the rectangle that checked
     * @return - the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List intersectionsPoint = rect.intersectionPoints(this);

        if (intersectionsPoint.isEmpty()) {
            return null;
        } else if (intersectionsPoint.size() == 1) {
            return (Point) intersectionsPoint.get(0);
        } else { //there is 2 points in the list
            if (this.start.distance((Point) intersectionsPoint.get(0))
                    < this.start.distance((Point) intersectionsPoint.get(1))) {
                return (Point) intersectionsPoint.get(0);
            }
            return (Point) intersectionsPoint.get(1);
        }
    }

    /**
     * the method check if the point is in the range of this line.
     *
     * @param point - the point that checked
     * @return - true if the point is in the range of this line, otherwise - return false.
     */
    public boolean isThePointOnTheLine(Point point) {
        return this.isInDomain(point.getX(), this.start.getX(), this.end.getX(), this.start.getX(), this.end.getX())
                && this.isInDomain(point.getY(), this.start.getY(), this.end.getY(), this.start.getY(),
                this.end.getY());
    }


}

