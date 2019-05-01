package levels;

import backgrounds.BackGround2;
import geometry.Point;
import geometry.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import others.Velocity;
import sprites.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class name: Level2
 * class operation: Implements LevelInformation, contains all the information
 * about Level 2:
 * number of balls and velocities, paddle's speed and width,
 * level's name, it's background, the blocks and number of blocks needed to remove.
 */

public class Level2 implements LevelInformation {

    /**
     * function name:numberOfBalls
     * Function Operation: returns the number of balls the game plays.
     *
     * @return - Integer
     */
    public int numberOfBalls() {
        return 10;
    }

    /**
     * function name:initialBallVelocities
     * Function Operation: Creates new list of velocities (velocity for each ball).
     *
     * @return List of Velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        for (int i = 1; i < 6; ++i) {
            velocities.add(Velocity.fromAngleAndSpeed(360 + i * 10, 8));
            velocities.add(Velocity.fromAngleAndSpeed(360 - i * 10, 8));
        }
        return velocities;
    }

    /**
     * function name:paddleSpeed
     * Function Operation: return the paddle's speed.
     *
     * @return - Integer
     */
    public int paddleSpeed() {
        return 5;
    }

    /**
     * function name:paddleWidth
     * Function Operation: return the paddle's width.
     *
     * @return - Integer
     */

    public int paddleWidth() {
        return 600;
    }

    /**
     * .
     * function name:levelName
     * Function Operation: return String with the level's name
     *
     * @return - String type (name of level)
     */
    public String levelName() {
        String s = new String("Wide Easy");
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
        return new BackGround2();
    }

    /**
     * function name:blocks
     * Function Operation: Creates new list of blocks for this level.
     *
     * @return - List<Block> type
     */
    public List<Block> blocks() {
        List<Block> list = new ArrayList<Block>();
        double width = 50;
        for (int i = 0; i < 15; ++i) {
            Color c = new Color(0, 0, 0);
            Block block = new Block(new Rectangle(
                    new Point(25 + width * i, 220), width, 20));
            switch (i) {
                case 0:
                case 1:
                    break;
                case 2:
                case 3:
                    break;
                case 4:
                case 5:
                    break;
                case 6:
                case 7:
                case 8:
                    break;
                case 9:
                case 10:
                    break;
                case 11:
                case 12:
                    break;
                case 13:
                default:
                    break;
            }
            block.setHitPoints(1);
            list.add(block);
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
        return 15;
    }

}
