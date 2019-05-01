package geometry;
/**
 * .
 * author: Eyal Styskin
 * Class name: Line
 * class operation: Create a new Line type object. every line is a segment, and has a starting Point
 * and a ending point (each point with x and y values.
 */

public class Line {

    //members
    private Point start;
    private Point end;

    /**
     * .
     * Function Operation: constructor - setting the 'start' and 'end' members.
     * that left point will be set as the start point, and the right is end,
     * if same x value = the lower is the start.
     *
     * @param start - Point type, the beginning of the line
     * @param end   - Point type, the end of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * .
     * Function Operation: constructor - setting the 'start' and 'end' members.
     * x1 and y1, will set one Point and x2,y2 will set the second Point.
     * that left point will be set as the start point, and the right is end,
     * if same x value = the lower is the start.
     *
     * @param x1 - x value of the first point
     * @param y1 - y value of the first point
     * @param x2 - x value of the second point
     * @param y2 - y value of the second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * .
     * function name:length
     * Function Operation: send the start Point and the end Point to the method "distance"
     * in the Point class in order to calculate the length of the Line.
     *
     * @return double - Line length
     */
    public double length() {
        return start.distance(end);
    }


    /**
     * .
     * function name: start
     * Function Operation: a method to return the (Point type) start of the line
     *
     * @return this line's starting point
     */
    public Point start() {
        return this.start;
    }

    /**
     * .
     * function name: end
     * Function Operation: a method to return the (Point type) end of the line
     *
     * @return this line's ending point
     */
    public Point end() {
        return this.end;
    }

    /**
     * .
     * function name: slope
     * Function Operation: method to calculate the slope of the line according to the x and y values
     * of the starting and ending point.
     *
     * @return double - the slope of the line
     */
    public double slope() {
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * .
     * function name:notParallelY
     * Function Operation: method that we use when we try to find the intersection point of two
     * not-Parallel to the Y-axis line segments. the method calculates the intersection point using
     * the equations of the lines, and return the point or null (if no intersection).
     *
     * @param other - the second Line
     * @return interP - Point of intersection or null
     */

    public Point notParallelY(Line other) {
        double m1 = this.slope(); // calculate both slopes
        double b1 = this.end.getY() - (m1 * this.end.getX());
        double m2 = other.slope();
        double b2 = other.end.getY() - (m2 * other.end.getX());
        double interX = (-1) * (b1 - b2) / (m1 - m2);
        interX = (double) Math.round(interX * 100000d) / 100000d;
        /**
         * we need to check that the intersection point is in the line's segments bounds,
         * because it is not a non ending lines, it has start and an end point.
         */
        if (interX < (double) Math.round(this.start.getX() * 100000d) / 100000d
                ||
                interX > (double) Math.round(this.end.getX() * 100000d) / 100000d) {
            return null;
        }
        if (interX < (double) Math.round(other.start.getX() * 100000d) / 100000d
                || interX > (double) Math.round(other.end.getX() * 100000d) / 100000d) {
            return null;
        }
        double interY = (m1 * interX) + b1; // find the y value of the intersection point.
        Point interP = new Point(interX, interY); // create new point object using the values we found.
        return interP;
    }

    /**
     * .
     * author: Eyal Styskin
     * function name: parallelY
     * Function Operation: method that we use when we try to find the intersection point of two
     * line segments when one of them parllel to the Y-axis.
     * the method calculates the intersection point by entering the x value of the parallel line
     * in to the line equation of the second, and so we get the intersection Point.
     * afterwards, we check if this point is in the segments bounds.
     *
     * @param par   - line, it is the line that parallel
     *              to the Y-axis and has no slope
     * @param other - the second line, not parallel, and has a slope.
     * @return interP - the point of intersection or null(if no intersection)
     */
    public Point parallelY(Line par, Line other) {
        double interY = (other.slope() * (par.start.getX() - other.start.getX()) + other.start.getY());
        interY = (double) Math.round(interY * 100000d) / 100000d;
        if (interY < par.start.getY() || interY > par.end.getY()) { // if out of parallel-to-Y-axis line bounds
            return null;
        }
        //if the intersection is left to the other line starting point or right to the end.
        if (par.start.getX() < other.start.getX() || par.start.getX() > other.end.getX()) {
            return null;
        }
        Point interP = new Point(par.start.getX(), interY);
        return interP;
    }

    /**
     * .
     * function name:parallelCheck
     * Function Operation: check if the line is parallel to the Y-axis by comparing the x values
     * of the starting point and the ending point.
     *
     * @return true ( if same x value) / false ( different values)
     */
    public boolean parallelCheck() {
        if (this.start.getX() == this.end.getX()) { // if same x value, it means its parallel to Y-axis.
            return true;
        }
        return false;
    }

    /**
     * .
     * function name: isIntersecting
     * Function Operation: A method that uses the helper method "intersectionWith".
     * if the 'intersectionWith' method returns a Point that is not null, it means there is an
     * intersection between the lines/
     *
     * @param other - the compared (second) Lines
     * @return true (if there is intersection) / false (if not intersection)
     */
    public boolean isIntersecting(Line other) {
        if (intersectionWith(other) == null) { //if returned null there is no intersection point
            return false;
        }
        return true; // if any other value returned there is intersection point
    }

    /**
     * .
     * function name: intersectionWith
     * Function Operation: The main method for finding the intersection point of two line segments.
     * it uses the helping methods :'parallelY','parallelCheck','notParallelY','slope'.
     * the method checks both lines segments and by using their Line Equations we find the intersection
     * point or null if there is not.
     *
     * @param other - the second Line (that we check intersection with)
     * @return interP - the intersection point/ null
     */
    public Point intersectionWith(Line other) {
        Point p1 = new Point(this.start.getX(), this.start.getY());
        Line l1 = new Line(p1, new Point(this.end.getX(), this.end.getY()));
        l1.intersectionHelper();
        Point p2 = new Point(other.start.getX(), other.start.getY());
        Line l2 = new Line(p2, new Point(other.end.getX(), other.end.getY()));
        l2.intersectionHelper();
        if (l1.parallelCheck() && l2.parallelCheck()) { // if both lines parallel to Y-axis (same slope)
            return null;
        }
        if (l1.parallelCheck()) { // if first line parallel to Y-axis - check intersection
            Point interP = parallelY(l1, l2);
            return interP;
        } else if (l2.parallelCheck()) { // if second line parallel to Y-axis - check intersection
            Point interP = parallelY(l2, l1);
            return interP;
        } else {
            if (l1.slope() == l2.slope()) { //same slope and not intersecting for sure.
                return null;
            }
            Point interP = l1.notParallelY(l2); // check the intersection of the not Parallel lines
            return interP;
        }
    }

    /**.
     * function name: intersectionHelper
     * Function Operation: arrange the line with the starting and ending point so it will be easier to check
     * the intersection of two lines
     * @return Line - after re- arranging of the start and the end point
     */
    public Line intersectionHelper() {
        Point p1 = new Point(this.start.getX(), this.start.getY());
        Point p2 = new Point(this.end.getX(), this.end.getY());
        if (start.getX() == end.getX()) { // if the line segments are parallel to Y-axis.
            if (start.getY() < end.getY()) { // set the lower y value point to be the start, and second to the end
                this.start = p1;
                this.end = p2;
            } else {
                this.start = p2;
                this.end = p1;
            }
        } else if (Math.min(start.getX(), end.getX()) == start.getX()) { //set the left point to be the start
            this.start = p1;
            this.end = p2;
        } else {
            this.start = p2;
            this.end = p1;
        }
        return this;
    }


    /**
     * .
     * function name: equals
     * Function Operation: check if the two compared lines are equal
     *
     * @param other - the second Line (that we check equalization with)
     * @return true - if equal / false - if different lines
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other)) && ((this.start.equals(other))));
    }

    /**
     * function name: closestIntersectionToStartOfLine
     * Function Operation: Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     * @param rect - a rectangle to check intersection with this line
     * @return Point - intersection point/ Null
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect.intersectionPoints(this) == null) { // empty list
            return null;
        }
        if (rect.intersectionPoints(this).size() == 1) { //only 1 intersection point
            return (Point) rect.intersectionPoints(this).get(0);
        } // search for closest point to the starting point
        Point closest = (Point) rect.intersectionPoints(this).get(0);
        double closestDistance = closest.distance(this.start);
        for (int i = 1; i < rect.intersectionPoints(this).size(); ++i) {
            Point pi = (Point) rect.intersectionPoints(this).get(i);
            if (pi.distance(this.start) < closestDistance) {
                closest = pi;
                closestDistance = pi.distance(this.start);
            }
        }
        return closest;
    }
}