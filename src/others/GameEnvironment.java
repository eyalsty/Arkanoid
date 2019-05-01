package others;

import java.util.ArrayList;
import geometry.Point;
import geometry.Line;
import interfaces.Collidable;

/**
 * .
 * author: Eyal Styskin
 * Class name:GameEnvironment
 * class operation: the class holds an Array List of  Collidable (interface) type.
 * it allows to add new collidable type object, and also can return the information about the closest Collision
 * on the Ball's trajectory line with other collidable object.
 */

public class GameEnvironment {
    private ArrayList<Collidable> collidables = new ArrayList<Collidable>();

    /**
     * function name: addCollidable
     * Function Operation: the function get collidable type object created in the Game class and adds it to the
     * collidables ArrayList.
     * @param c - new collidable type object
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**.
     *  * author: Eyal Styskin
     * function name: removeCollidables
     * Function Operation:removed the give Collidable object from the collidables list
     * @param c - Collidable object to be removed
     */
    public void removeCollidables(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * function name: getClosestCollision
     * Function Operation: the method gets a trajectory line, it's start is the cetner of the ball, and the end
     * being calculated according to the ball's velocity. this method check for a collision with any collidable object,
     * (checks for intersection of the trajectory Line with any line of the rectangles).
     * @param trajectory - the Line shows the path of the ball
     * @return CollisionInfo type object with information about the collision Point and the object (or NULL)
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        ArrayList<Collidable> newCollidables = new ArrayList<Collidable>(this.collidables); // update current list
        ArrayList<CollisionInfo> infos = new ArrayList<>(); // create new Array List of "Collision Info" type
        for (int i = 0; i < newCollidables.size(); ++i) { //check intersection of Line with all collidable objects
            Point col = trajectory.closestIntersectionToStartOfLine(newCollidables.get(i).getCollisionRectangle());
            if (col != null) { //if intersection occurred create new CollisionInfo object
                CollisionInfo info = new CollisionInfo(col, newCollidables.get(i));
                infos.add(info); // add the new object to the ArrayList
            }
        }
        if (infos.size() == 0) { //if no collisions occurred return null
            return null;
        } else { // if collision was found - we search for the closest one to the Line's starting Point.
            CollisionInfo info = infos.get(0);
            Point closestP = info.collisionPoint();
            double distance = closestP.distance(trajectory.start());
            for (int j = 1; j < infos.size(); ++j) {
                if (infos.get(j).collisionPoint().distance(trajectory.start()) < distance) {
                    info = infos.get(j);
                    closestP = infos.get(j).collisionPoint();
                    distance = closestP.distance(trajectory.start());
                }
            }
            return info; // return closest intersection's info
        }
    }
}