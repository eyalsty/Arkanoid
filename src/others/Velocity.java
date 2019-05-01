package others;
import geometry.Point;

/**
 * .
 * author: Eyal Styskin
 * Class name: Velocity
 * class operation: this class give the ball speed and direction.
 * it has two members: the dx (progress on X-avis) and dy (progress on Y-axis),
 */

public class Velocity {
    //members
    private double dx;
    private double dy;

    /**
     * .
     * Function Operation: constructor - setting the 'dx' and 'dy' members
     *
     * @param dx - progress on X-avis
     * @param dy - progress on Y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * .
     * function name: getDx
     * Function Operation: return the value (double) of the member 'dx'
     *
     * @return this dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * .
     * function name: getDy
     * Function Operation: return the value (double) of the member 'dy'
     *
     * @return this dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * .
     * function name: applyToPoint
     * Function Operation: this method add the velocity (dx and dy) to the point
     * that being sent to the method and returning the new point with the new
     * x and y values.
     *
     * @param p - Point type object to be changed
     * @return newP - the new point after the addition of the dx and dy
     */
    public Point applyToPoint(Point p) {
        double x = p.getX() + dx;
        double y = p.getY() + dy;
        y = (double) Math.round(y * 100000d) / 100000d;
        Point newP = new Point(x, y);
        return newP;
    }

    /**
     * .
     * function name: fromAngleAndSpeed
     * Function Operation: the method receive an angle (in degrees), turn it into radians, and
     * then calculates the Velocity together with the speed.
     *
     * @param angle - angle in degrees from 0 to 360
     * @param speed - the movement speed
     * @return Velocity - new Velocity type object
     */


    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * (Math.sin(Math.toRadians(angle))); // calculate the dx
        dx = (double) Math.round(dx * 100000d) / 100000d;
        double dy = (-1) * speed * (Math.cos(Math.toRadians(angle))); // calculate the dy
        dy = (double) Math.round(dy * 100000d) / 100000d;
        return new Velocity(dx, dy);
    }
}

