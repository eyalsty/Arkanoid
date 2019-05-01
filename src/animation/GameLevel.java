package animation;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import levels.BlockFiller;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import others.Counter;
import others.GameEnvironment;
import others.SpriteCollection;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import sprites.NameIndicator;


import java.awt.Color;
import java.util.Map;
import java.util.TreeMap;

/**
 * .
 * author: Eyal Styskin
 * Class name:GameLevel
 * class operation: the class implements Animation, this class in charge of the gameplay.
 * its initializing the game, create all the sprites and collidable objects in the given
 * level, playing the turn, and counting the number of blocks/lives/balls and score.
 */

public class GameLevel implements Animation {
    private Counter blocksNum;
    private Counter ballsNum;
    private Counter score;
    private Counter numOfLives;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private biuoop.GUI gui;
    private AnimationRunner runner;
    private boolean running;
    private Paddle paddle;
    private KeyboardSensor keyboard;
    private LevelInformation info;

    /**
     * .
     * Function Operation: Constructor - creates all the members of the class,
     * according to the given parameters
     *
     * @param levelInformation - all the information needed on the played level
     * @param ks               - the keyboardSensor used
     * @param ar               - AnimationRunner type to run animations
     * @param numOfLives       - number of lives the player has
     * @param score            - the player's score
     */

    public GameLevel(LevelInformation levelInformation,
                     KeyboardSensor ks, AnimationRunner ar,
                     Counter numOfLives, Counter score) {
        this.info = levelInformation;
        this.blocksNum = new Counter();
        this.ballsNum = new Counter();
        this.score = score;
        this.numOfLives = numOfLives;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.runner = ar;
        this.keyboard = ks;
        this.gui = ar.getGui();
    }

    /**
     * .
     * function name: addCollidable
     * Function Operation: adds the object given to the collidables list
     *
     * @param c - new collidable type object to be added
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * .
     * function name: addSprite
     * Function Operation: adds the object given to the Sprites list
     *
     * @param s - new collidable type object to be added
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * .
     * function name: removeCollidable
     * Function Operation: removes the collidable object from the collidables list.
     *
     * @param c - Collidable type object
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidables(c);
    }

    /**
     * .
     * function name: removeSprite
     * Function Operation: removes the Sprite object from the Sprites list.
     *
     * @param s - Sprite type object
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * .
     * function name: getGameEnvironment
     * Function Operation: getter for this GameEnvironment
     *
     * @return this.environment - the member
     */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }

    /**
     * .
     * function name: getBlocksNum
     * Function Operation: getter for this blocksNum member
     *
     * @return Counter type of the blocks number
     */
    public Counter getBlocksNum() {
        return blocksNum;
    }

    /**
     * .
     * function name: getNumOfLives
     * Function Operation: getter for this numOfLives member
     *
     * @return - Counter type of the lives number
     */
    public Counter getNumOfLives() {
        return numOfLives;
    }

    /**
     * .
     * function name: getGui
     * Function Operation: getter for this GUI member
     *
     * @return - GUI type - this gui
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * .
     * function name: initialize
     * Function Operation: Initialize a new GameLevel: create the Blocks(in rows) ,
     * paddle, listeners: for block,ball and score. all according to the LevelInfo
     * (current level played) that set in the constructor.
     */
    public void initialize() {
        this.blocksNum.increase(this.info.numberOfBlocksToRemove());
        double padSpeed = this.info.paddleSpeed();
        this.paddle = (new Paddle(new Rectangle(
                new Point(400 - this.info.paddleWidth() / 2
                        , 570), this.info.paddleWidth(), 20), Color.orange, padSpeed));
        this.paddle.addToGame(this);

        BlockRemover blockRemover = new BlockRemover(this, this.blocksNum); //removes blocks
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score); //counts score
        NameIndicator nameIndicator = new NameIndicator(this.info.levelName());
        nameIndicator.addToGame(this);
        this.borderBlocks(); // create border blocks and add them to game
        for (int i = 0; i < this.info.blocks().size(); ++i) {
            Block b = this.info.blocks().get(i);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTrackingListener);
            b.addToGame(this);
        }
    }

    /**
     * Create the Blocks the borders the game screen, sets its characteristics and
     * adds it to the game.
     */
    public void borderBlocks() {
        Block block1 = new Block(new Rectangle(new Point(0, 20), 800, 25)); //up block
        Block block2 = new Block(new Rectangle(new Point(0, 20), 25, 580)); //left block
        Block block3 = new Block(new Rectangle(new Point(775, 20), 25, 580)); //right block
        BlockFiller blockFiller = new BlockFiller(Color.gray);
        Map<Integer, BlockFiller> bordersMap = new TreeMap<Integer, BlockFiller>();
        bordersMap.put(1, blockFiller);
        block1.setColorsMap(bordersMap);
        block2.setColorsMap(bordersMap);
        block3.setColorsMap(bordersMap);
        block1.setStroke(Color.BLACK);
        block2.setStroke(Color.BLACK);
        block3.setStroke(Color.BLACK);
        block1.addToGame(this);
        block2.addToGame(this);
        block3.addToGame(this);
        Block deathBlock = new Block(new Rectangle(
                new Point(0, 600), 800, 10)); //down block (death)
        deathBlock.setColorsMap(bordersMap);
        deathBlock.addToGame(this);
        BallRemover ballRemover = new BallRemover(this, this.ballsNum); //removes balls
        deathBlock.addHitListener(ballRemover);
    }

    /**
     * .
     * function name: createBallsOnTopOfPaddle
     * Function Operation: creates the balls on top of the paddle, according to
     * the number and velocities in the levelInfo.
     */
    private void createBallsOnTopOfPaddle() {
        for (int i = 0; i < this.info.numberOfBalls(); ++i) {
            Ball ball = new Ball(new Point(400, 564), 5, Color.WHITE); //first ball
            ball.setVelocity(this.info.initialBallVelocities().get(i));
            ball.addToGame(this);
            this.ballsNum.increase(1);
        }
    }

    /**
     * function name: playOneTurn
     * Function Operation: plays one turn of the game. creates the balls, runs the countdown
     * animation and runs the logic of the game.
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle(); //create balls on top of paddle
        this.runner.run(new CountdownAnimation(3, 3, this.sprites)); // countdown before turn starts.
        this.running = true;

        this.runner.run(this);
    }

    /**
     * function name: shouldStop
     * Function Operation: returns the boolean member, if the game
     * should be stopped.
     *
     * @return - this.running - boolean type if to stop
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * function name: doOneFrame
     * Function Operation: draws all the sprites and plays them.
     * allows to pause the game if "p" pressed, and orders to finish the turn,
     * if the player looses.
     *
     * @param d - Drawsurface type - to draw number on it
     * @param dt - frames per second
     */

    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(
                    this.keyboard, "space", new PauseScreen()));
        }
        if (this.blocksNum.getValue() == 0) { // if out of blocks
            this.score.increase(100);
            this.paddle.moveToMiddle(this.info);
            this.running = false;
        }
        if (this.ballsNum.getValue() == 0) { //// if out of balls
            this.getNumOfLives().decrease(1);
            this.paddle.moveToMiddle(this.info);
            this.running = false;
        }
    }
}
