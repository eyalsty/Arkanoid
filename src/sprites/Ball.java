package sprites;

import animation.GameLevel;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Line;
import interfaces.Sprite;
import others.CollisionInfo;
import others.GameEnvironment;
import others.Velocity;

import java.awt.Color;

/**
 * .
 * author: Eyal Styskin
 * Class name:Ball
 * class operation: create a Ball type object that has a center Point, size (radius),
 * color, Velocity ( angle and speed - dx/dy) and a gameEnvironment that holds a collection
 * of objects that the ball can collide at.
 * The ball class implements the sprite interface.
 */

public class Ball implements Sprite {
    //members
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private Double dt;
    private Point border1;
    private Point border2;


    /**
     * .
     * Function Operation: constructor - set the Point center, the radius and the color members
     *
     * @param center - Point type object, has a x and y values
     * @param r      - the radius of the ball (integer)
     * @param color  - the color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * .
     * function name: getX
     * Function Operation: return the x value of the center point, cast it from double to int.
     *
     * @return x value of the center (integer)
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * .
     * function name: getY
     * Function Operation: return the y value of the center point, cast it from double to int.
     *
     * @return y value of the center (integer)
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * .
     * function name: getSize
     * Function Operation: return the radius(member) of the ball.
     *
     * @return radius of ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * .
     * function name: getColor
     * Function Operation: return the color(member) of the ball.
     *
     * @return color of ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    // draw the ball on the given DrawSurface

    /**
     * .
     * function name: drawOn
     * Function Operation: draw a circle(ball) at this center's coordinates according to the radius
     * and the color on the surface.
     *
     * @param surface - the surface on which we draw the circles and lines
     */
    public void drawOn(DrawSurface surface) {
        int x = (int) this.center.getX();
        int y = (int) this.center.getY();
        surface.setColor(this.color);
        surface.fillCircle(x, y, this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle(x, y, this.radius);
    }

    /**
     * .
     * function name: setVelocity
     * Function Operation: change the velocity member of this ball to the new velocity received
     * as an argument.
     *
     * @param v - the velocity (speed and angle)
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * .
     * function name: setVelocity
     * Function Operation: set the velocity - create new(member) according to the dx and dy
     * arguments that this method receive
     *
     * @param dx - the promotion on X-axis.
     * @param dy - the promotion on Y-axis.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * .
     * function name: getVelocity
     * Function Operation: return the velocity member of this ball
     *
     * @return velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * .
     * function name: setBound
     * Function Operation: set the members border1 and border2 of this ball.
     * the method receive arguments for 2 new points and create the two new members of
     * this ball.
     *
     * @param x1 - x value of first border.
     * @param y1 - y value of first border.
     * @param x2 - x value of second border.
     * @param y2 - y value of second border.
     */
    public void setBound(double x1, double y1, double x2, double y2) {
        this.border1 = new Point(x1, y1);
        this.border2 = new Point(x2, y2);
    }

    /**
     * sets the dt member of this ball.
     * @param fps - frames per second
     */
    public void setDt(double fps) {
        this.dt = fps;
    }

    /**
     * .
     * function name: moveOneStep
     * Function Operation: this method changes the values of the center of the ball
     * so on the drawing surface it will look like the ball moved.
     * if the ball hitting any coliidable object it will change it direction.
     */
    public void moveOneStep() {
        Velocity newVelocity = new Velocity(this.velocity.getDx() * this.dt, this.velocity.getDy() * this.dt);
        Line trajectory = new Line(this.center, newVelocity.applyToPoint(this.center));
        CollisionInfo info = this.gameEnvironment.getClosestCollision(trajectory);
        if (info == null) { //no collisions
            this.center = trajectory.end(); // move the ball to the end of the line
        } else { // collision occurred
            this.velocity = info.collisionObject().hit(this, info.collisionPoint(), this.velocity);
            if (this.center.getX() > info.collisionObject().getCollisionRectangle().getUpperLeft().getX() //in paddle
                    && this.center.getX() < info.collisionObject().getCollisionRectangle().getDownRight().getX()
                    && this.center.getY() < info.collisionObject().getCollisionRectangle().getUpperLeft().getY()
                    && this.center.getY() > info.collisionObject().getCollisionRectangle().getDownRight().getY()) {
                this.center = info.collisionPoint(); // move the ball out of the paddle
            }
            if (info.collisionObject() instanceof Paddle) {
                this.center = newVelocity.applyToPoint(this.center);
            }
            if (this.center.getX() > 775 && this.velocity.getDx() > 0) {
                this.center = new Point(773, this.center.getY());
                this.velocity = new Velocity(this.velocity.getDx() * (-1), this.velocity.getDy());
            }
            if (this.center.getX() < 25 && this.velocity.getDx() < 0) {
                this.center = new Point(27, this.center.getY());
                this.velocity = new Velocity(this.velocity.getDx() * (-1), this.velocity.getDy());
            }
        }
    }



        /*
        Velocity newVelocity = new Velocity(this.velocity.getDx() * dt, this.velocity.getDy() * dt);
        Line trajectory = new Line(this.center, newVelocity.applyToPoint(this.center));
        CollisionInfo info = this.gameEnvironment.getClosestCollision(trajectory);
        if (info != null) {
            this.velocity = info.collisionObject().hit(this, info.collisionPoint(), this.velocity);
            if (info.collisionObject() instanceof Paddle) {
                this.center = newVelocity.applyToPoint(this.center);
            }
        } else {
            this.center = newVelocity.applyToPoint(this.center);
        }

        if (this.center.getX() > 775 && this.velocity.getDx() > 0) {
            this.center = new Point(773,this.center.getY());
            this.velocity = new Velocity(newVelocity.getDx() *(-1),newVelocity.getDy());
        }
        if (this.center.getX() < 25 && this.velocity.getDx() < 0) {
            this.center = new Point(27,this.center.getY());
            this.velocity = new Velocity(newVelocity.getDx() *(-1),newVelocity.getDy());

         */

    /**
     * function name: addToGame
     * Function Operation: Sprite's interface method, add the new created ball to the sprites array,
     * and set the game GameEnvironment member of the ball.
     *
     * @param g - gameEnvironment object, consists a collection of objects that the ball can collide in
     */
    public void addToGame(GameLevel g) {
        this.gameEnvironment = g.getGameEnvironment();
        g.addSprite(this);
    }

    /**
     * function name: addToGame
     * Function Operation: a method from the Sprite Interface, make the ball know that time has Passed and
     * it has to move - calls the method "move one step".
     *
     * @param fps - frames per second
     */
    public void timePassed(double fps) {
        this.setDt(fps);
        this.moveOneStep();
    }

    /**
     * .
     * function name: removeFromGame
     * Function Operation: remove this ball from the sprites in the current game
     *
     * @param g - current game to remove the ball from
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}



