package interfaces;

import others.Velocity;
import sprites.Block;

import java.util.List;

/**
 * author: Eyal Styskin
 * Interface name:LevelInformation
 * Interface operation: contains all the details needed
 * for a new level to create.
 */
public interface LevelInformation {
    /**
     * Function Name: numberOfBalls
     * Function Operation: sets the number of balls in the game.
     * @return - Integer (balls number)
     */
    int numberOfBalls();

    /**
     * Function Name: initialBallVelocities
     * Function Operation: The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return List of velocities
     */
    List<Velocity> initialBallVelocities();

    /**.
     * Function Name: paddleSpeed
     * Function Operation: return the paddle's speed
     * @return - Integer, paddle's movement speed
     */
    int paddleSpeed();

    /**.
     * Function Name: paddleWidth
     * Function Operation: return the paddle's width
     * @return - Integer, paddle's width
     */
    int paddleWidth();
    /**
     * Function Name: levelName
     * Function Operation: the level name will be displayed at the top of the screen.
     * @return String type - level name
     */
    String levelName();

    /**.
     * Function Name: initialBallVelocities
     * Function Operation:Returns a new Sprite with the background of the level
     * @return Sprite type Background
     */
    Sprite getBackground();

    /**
     * Function Name: blocks
     * Function Operation:The Blocks that make up this level, each block contains.
     * its size, color and location.
     * @return - List of blocks
     */
    List<Block> blocks();

    /**
     * Function Name: numberOfBlocksToRemove
     * Function Operation: Number of levels that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return integer - number of blocks
     */
    int numberOfBlocksToRemove();
}
