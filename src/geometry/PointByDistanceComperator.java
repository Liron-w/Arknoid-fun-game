//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-05-01
 */

package geometry;

import java.util.Comparator;

/**
 * this class compare two points by the distance from other point (myPoint).
 */
public class PointByDistanceComperator implements Comparator {

    //field
    private Point myPoint;

    //constructor:

    /**
     * contructor that get the point from which we checked the distances.
     *
     * @param myPoint - the point from which we checked the distances
     */
    public PointByDistanceComperator(Point myPoint) {
        this.myPoint = myPoint;
    }

    /**
     * this method compare two points by the distance from other point (myPoint).
     *
     * @param p1 - the first point that compared
     * @param p2 - the second point that compared
     * @return 1- if the first distance from the first point is bigger,
     * (-1)- if the distance from the second point is bigger,
     * 0 - if the distances are equals.
     */
    public int compare(Object p1, Object p2) {
        double distance1 = this.myPoint.distance((Point) p1);
        double distance2 = this.myPoint.distance((Point) p2);

        if (distance1 < distance2) {
            return -1;
        } else if (distance1 > distance2) {
            return 1;
        } else {
            return 0;
        }

    }
}
