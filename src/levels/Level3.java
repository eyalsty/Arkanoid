package levels;

import backgrounds.BackGround3;
import geometry.Point;
import geometry.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import others.Velocity;
import sprites.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * Class name: Level3
 * class operation: Implements LevelInformation, contains all the information
 * about Level 3:
 * number of balls and velocities, paddle's speed and width,
 * level's name, it's background, the blocks and number of blocks needed to remove.
 */


public class Level3 implements LevelInformation {
    /**
     * function name:numberOfBalls
     * Function Operation: returns the number of balls the game plays.
     *
     * @return - Integer
     */
    public int numberOfBalls() {
        return 2;
    }

    /**
     * function name:initialBallVelocities
     * Function Operation: Creates new list of velocities (velocity for each ball).
     *
     * @return List of Velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        velocities.add(Velocity.fromAngleAndSpeed(330, 7));
        velocities.add(Velocity.fromAngleAndSpeed(30, 7));
        return velocities;
    }

    /**
     * function name:paddleSpeed
     * Function Operation: return the paddle's speed.
     *
     * @return - Integer
     */
    public int paddleSpeed() {
        return 8;
    }

    /**
     * function name:paddleWidth
     * Function Operation: return the paddle's width.
     *
     * @return - Integer
     */
    public int paddleWidth() {
        return 100;
    }

    /**
     * .
     * function name:levelName
     * Function Operation: return String with the level's name
     *
     * @return - String type (name of level)
     */
    public String levelName() {
        return new String("Green 3");
    }

    /**
     * function name:getBackground
     * Function Operation: creates new Background Sprite for the current level and
     * returns it.
     *
     * @return Sprite - the Background for this level
     */
    public Sprite getBackground() {
        return new BackGround3();
    }

    /**
     * function name:blocks
     * Function Operation: Creates new list of blocks for this level.
     *
     * @return - List<Block> type
     */
    public List<Block> blocks() {
        List<Block> list = new ArrayList<Block>();
        double bLength = 50;
        for (int j = 0; j < 10; ++j) { //row 1
            Block b = new Block(new Rectangle(new Point(775 - bLength - j * bLength, 180),
                    bLength, 20));
            b.setHitPoints(2);
            list.add(b);
        }
        for (int j = 0; j < 9; ++j) { //row 2
            Block b = new Block(new Rectangle(new Point(775 - bLength - j * bLength, 200),
                    bLength, 20));
            b.setHitPoints(1);
            list.add(b);
        }
        for (int j = 0; j < 8; ++j) { //row 3
            Block b = new Block(new Rectangle(new Point(775 - bLength - j * bLength, 220),
                    bLength, 20));
            b.setHitPoints(1);
            list.add(b);
        }
        for (int j = 0; j < 7; ++j) { //row 4
            Block b = new Block(new Rectangle(new Point(775 - bLength - j * bLength, 240),
                    bLength, 20));
            b.setHitPoints(1);
            list.add(b);
        }
        for (int j = 0; j < 6; ++j) { //row 5
            Block b = new Block(new Rectangle(new Point(775 - bLength - j * bLength, 260),
                    bLength, 20));
            b.setHitPoints(1);
            list.add(b);
        }
        return list;
    }

    /**
     * function name:numberOfBlocksToRemove
     * Function Operation: returns the number of blocks needed to be removed
     * in order to "Clear" this level.
     *
     * @return - Integer
     */
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
