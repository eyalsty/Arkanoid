package geometry;

/**
 * .
 * author: Eyal Styskin
 * Class name:Point
 * class operation: a class to create an object of type Point that has x and y values.
 */


public class Point {

    //members
    private double x;
    private double y;

    /**
     * .
     * <p>
     * Function Operation: constructor - setting the x and y members
     *
     * @param x - the double value of the member x
     * @param y - the double value of the member y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * .
     * function name:distance
     * Function Operation: calculates the distance between two points and return it.
     *
     * @param other - the second point to which the distance is measured
     * @return double integer
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy)); //Mathematical formula for calculating distance
    }

    /**
     * .
     * function name:equals
     * Function Operation: check if the the two point are the same and have same x and y values/
     *
     * @param other - the second point that being checked      * @return boolean - true or false
     * @return true - if equal , false - if difference point
     */
    public boolean equals(Point other) {
        return ((this.x == other.getX()) && (this.y == other.getY()));
    }

    /**
     * .
     * function name:getY
     * Function Operation: returns this point's y value
     *
     * @return this point's y value
     */
    public double getY() {
        return this.y;
    }

    /**
     * .
     * function name:setY
     * Function Operation: set new y value to this point
     *
     * @param yVal - new double type y value
     */
    public void setY(double yVal) {
        this.y = yVal;
    }

    /**
     * .
     * function name:getX
     * Function Operation: returns this point's x value
     *
     * @return this point x value
     */
    public double getX() {
        return this.x;
    }

    /**
     * .
     * function name:setX
     * Function Operation: set new x value to the point
     *
     * @param xVal - new double type x value
     */
    public void setX(double xVal) {
        this.x = xVal;
    }
}
