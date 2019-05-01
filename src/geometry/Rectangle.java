package geometry;
import java.util.ArrayList;

/**
 * .
 * author: Eyal Styskin
 * Class name:Rectangle
 * class operation: the class creates a new object, in shape of a rectangle. it has 4 sides and 4 corners, and width
 * and height. the rectangle is the shape of all blocks and the paddle.
 */

public class Rectangle {
    private Point upperLeft;
    private Point upperRight;
    private Point downRight;
    private Point downLeft;
    private double width;
    private double height;
    private Line upSide;
    private Line downSide;
    private Line leftSide;
    private Line rightSide;

    /**
     * .
     * function name: Rectangle
     * Function Operation: constructor - creates a new Rectangle with all of its member.
     * the method uses the upper left point, width and height that being delivered to calculate and create all its
     * member : all four sides, all four corners and width, height
     *
     * @param upperLeft - Point type object (has x,y values) indicating the upper left corner of the rectangle
     * @param width     - the length of the rectangle
     * @param height    - the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.downRight = new Point(upperLeft.getX() + width, height + upperLeft.getY());
        this.downLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.height = height;
        this.width = width;
        this.upSide = new Line(upperLeft, upperRight);
        this.downSide = new Line(downLeft, downRight);
        this.leftSide = new Line(upperLeft, downLeft);
        this.rightSide = new Line(upperRight, downRight);
    }

    /**
     * .
     * function name: intersectionPoints
     * Function Operation: the method creates new ArrayList that consists intersection points between the
     * line and the sides of the rectangle.
     *
     * @param line - trajectory, its starting point is the center of the ball and the end calculated according to the
     *             velocity.
     * @return intersections - list of intersection points
     */
    public java.util.List intersectionPoints(Line line) {
        ArrayList intersections = new ArrayList();
        Point inter1 = line.intersectionWith(upSide); // check intersection with the upper line of the rectangle
        if (inter1 != null) {
            intersections.add(inter1);
        }
        Point inter2 = line.intersectionWith(downSide); // check intersection with the lower line of the rectangle
        if (inter2 != null) {
            intersections.add(inter2);
        }
        Point inter3 = line.intersectionWith(leftSide); // check intersection with the left line of the rectangle
        if (inter3 != null) {
            intersections.add(inter3);

        }
        Point inter4 = line.intersectionWith(rightSide); // check intersection with the right line of the rectangle
        if (inter4 != null) {
            // if (inter4 != inter1 && inter4 != inter2) {
            intersections.add(inter4);
            //}
        }
        if (intersections.isEmpty()) { //if the list is empty = no intersections and return null
            return null;
        }
        return intersections;
    }

    /**
     * .
     * function name: intersectionPoints
     * Function Operation: getter for the width of the rectangle
     *
     * @return this.width - double type value
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * .
     * function name: getHeight
     * Function Operation: getter for the Height of the rectangle
     *
     * @return this.height - double type value
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * .
     * function name: getUpperLeft
     * Function Operation: getter for the Upper Left point of the rectangle
     *
     * @return this.upperLeft - Point type (x,y) , upper left corner of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * .
     * function name: getDownRight
     * Function Operation: getter for the down right point of the rectangle
     *
     * @return this.downRight - Point type (x,y) , down right corner of the rectangle
     */
    public Point getDownRight() {
        return this.downRight;
    }

}
