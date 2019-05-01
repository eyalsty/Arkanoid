package others;

import geometry.Point;
import interfaces.Collidable;

/**
 * .
 * author: Eyal Styskin
 * Class name:CollisionInfo
 * class operation: the class holds information about a collision between the ball and a collidable type object.
 * it holds two member: Collision Point (type Point), and the object the collision happened with (Collidable type)
 */


public class CollisionInfo {
    //members
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * function operation: Constructor, set the collisionPoint and collisionObject members.
     *
     * @param collisionPoint  - Point type (has x,y values) Intersection Point of the ball's line and the rectangle
     * @param collisionObject - a rectangle, Collidable type that ball's line intersect with.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * .
     * function name:collisionPoint
     * function operation: return the collision Point
     *
     * @return this.collisionPoint - the collision point member
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * .
     * function name:collisionObject
     * function operation: return the collision Object
     *
     * @return - this.collisionObject - the collision object member
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
