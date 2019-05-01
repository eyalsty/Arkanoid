package levels;

import backgrounds.BackGround1;
import geometry.Point;
import geometry.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import others.Velocity;
import sprites.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * Class name: Level1
 * class operation: Implements LevelInformation, contains all the information
 * about Level 1:
 * number of balls and velocities, paddle's speed and width,
 * level's name, it's background, the blocks and number of blocks needed to remove.
 */
public class Level1 implements LevelInformation {

    /**
     * function name:numberOfBalls
     * Function Operation: returns the number of balls the game plays.
     *
     * @return - Integer
     */
    public int numberOfBalls() {
        return 1;
    }

    /**
     * function name:initialBallVelocities
     * Function Operation: Creates new list of velocities (velocity for each ball).
     *
     * @return List of Velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        velocities.add(Velocity.fromAngleAndSpeed(360, 8));

        return velocities;
    }

    /**
     * function name:paddleSpeed
     * Function Operation: return the paddle's speed.
     *
     * @return - Integer
     */
    public int paddleSpeed() {
        return 10;
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
        String s = new String("Direct Hit");
        return s;
    }

    /**
     * function name:getBackground
     * Function Operation: creates new Background Sprite for the current level and
     * returns it.
     *
     * @return Sprite - the Background for this level
     */
    public Sprite getBackground() {
        return new BackGround1();
    }

    /**
     * function name:blocks
     * Function Operation: Creates new list of blocks for this level.
     *
     * @return - List<Block> type
     */
    public List<Block> blocks() {
        List<Block> list = new ArrayList<Block>();
        Block block1 = new Block(new Rectangle(
                new Point(385, 145), 30, 30)); //up block
        block1.setHitPoints(1);
        list.add(block1);
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
        return 1;
    }
}
