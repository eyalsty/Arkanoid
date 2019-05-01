package gamelogic;


import animation.HighScoresAnimation;
import animation.AnimationRunner;
import animation.EndScreen;
import animation.GameLevel;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;
import levels.LevelSpecificationReader;
import others.Counter;
import sprites.LivesIndicator;
import sprites.ScoreIndicator;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * author: Eyal Styskin
 * Class name: GameFlow
 * class operation: class in charge of the GameFlow.
 * moving from one level to another, and remembers the score and number of lives.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private HighScoresTable highScoresTable;
    private boolean isWon = false;
    private boolean isToAdd = false;
    private LevelSpecificationReader levelSpecification;

    /**
     * .
     * Function Operation: Constructor- sets the members of the class
     *
     * @param ar - KeyboardSensor Type
     * @param ks - the AnimationRunner from main class
     * @param levelSpecification  - LevelSpecificationReader Object
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, LevelSpecificationReader levelSpecification) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.highScoresTable = new HighScoresTable(5);
        this.levelSpecification = levelSpecification;
    }

    /**
     * Function Name:runLevels
     * Function Operation: creates new Counters for the score and for the number
     * of lives. then runs every level according to the order in the list of the
     * LevelInformation.
     * @param filePath - String of the filePath
     */

    public void runLevels(String filePath) {
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(filePath);
            Reader reader = new InputStreamReader(is);
            List<LevelInformation> levels = this.levelSpecification.fromReader(reader);
            Counter score = new Counter(); //score
            ScoreIndicator scoreIndicator = new ScoreIndicator(score);
            Counter numOfLives = new Counter(); //lives
            LivesIndicator livesIndicator = new LivesIndicator(numOfLives);
            numOfLives.increase(7);
            for (LevelInformation levelInfo : levels) { //run levels

                GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                        this.animationRunner, numOfLives, score);
                level.addSprite(levelInfo.getBackground());  // adds the levels background
                scoreIndicator.addToGame(level);
                livesIndicator.addToGame(level);
                level.initialize();
                //stop logic
                while (level.getBlocksNum().getValue() != 0 && level.getNumOfLives().getValue() != 0) {
                    level.playOneTurn();
                }
                if (levelInfo == levels.get(levels.size() - 1) //reached last level and won
                        && level.getBlocksNum().getValue() == 0
                        && level.getNumOfLives().getValue() != 0) {
                    this.isWon = true;
                    break;
                }
                if (level.getNumOfLives().getValue() == 0) { //out of lives and lost
                    this.isWon = false;
                    break;
                }
            }


            File file = new File("highscores.txt");
            if (!file.exists()) {
                this.isToAdd = true;
            } else {
                this.highScoresTable = HighScoresTable.loadFromFile(file);
                if (this.highScoresTable.getRank(score.getValue())
                        <= this.highScoresTable.getTableCapacity()) {
                    this.isToAdd = true;
                } else {
                    this.isToAdd = false;
                }
            }
            if (isToAdd) {
                DialogManager dialog = this.animationRunner.getGui().getDialogManager();
                String name = dialog.showQuestionDialog("Name", "What is your name?", "");
                int maxLengthName = 12;
                if (name.length() > maxLengthName) {
                    name = name.substring(0, 12);
                }
                this.highScoresTable.add(new ScoreInfo(name, score.getValue()));
                try {
                    this.highScoresTable.save(file);
                } catch (IOException e) {
                    System.out.println("Failed saving file");
                }
            }
            if (this.isWon) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                        "space", new EndScreen(true, score)));
            } else {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                        "space", new EndScreen(false, score)));
            }
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                    "space", new HighScoresAnimation(this.highScoresTable)));
        } catch (Exception e) {
            System.out.println("cant load file-GameFlow");
        }
    }
}
