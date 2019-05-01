package levels;

import backgrounds.BackGround4;
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
 * Class name: Level4
 * class operation: Implements LevelInformation, contains all the information
 * about Level 4:
 * number of balls and velocities, paddle's speed and width,
 * level's name, it's background, the blocks and number of blocks needed to remove.
 */


public class Level4 implements LevelInformation {
    /**
     * function name:numberOfBalls
     * Function Operation: returns the number of balls the game plays.
     *
     * @return - Integer
     */
    public int numberOfBalls() {
        return 3;
    }

    /**
     * function name:initialBallVelocities
     * Function Operation: Creates new list of velocities (velocity for each ball).
     *
     * @return List of Velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        velocities.add(Velocity.fromAngleAndSpeed(330, 8));
        velocities.add(Velocity.fromAngleAndSpeed(360, 8));
        velocities.add(Velocity.fromAngleAndSpeed(30, 8));
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
        return new String("Final Four");
    }

    /**
     * function name:getBackground
     * Function Operation: creates new Background Sprite for the current level and
     * returns it.
     *
     * @return Sprite - the Background for this level
     */
    public Sprite getBackground() {
        return new BackGround4();
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
        for (int i = 0; i < 7; ++i) {
            Color c = new Color(0, 0, 0);
            switch (i) {
                case 0:
                    c = Color.gray;
                    break;
                case 1:
                    c = Color.red;
                    break;
                case 2:
                    c = Color.yellow;
                    break;
                case 3:
                    c = Color.green;
                    break;
                case 4:
                    c = Color.white;
                    break;
                case 5:
                    c = Color.pink;
                    break;
                default:
                    c = Color.cyan;
                    break;
            }
            for (int j = 0; j < 15; ++j) {
                Block b = new Block(new Rectangle(new Point(
                        25 + j * width, 100 + i * 20), width, 20));
                if (i == 0) {
                    b.setHitPoints(2);
                } else {
                    b.setHitPoints(1);
                }
                list.add(b);
            }
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
        return 105;
    }
}
