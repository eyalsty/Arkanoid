package gamelogic;

import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.MenuAnimation;
import interfaces.Menu;
import interfaces.Task;
import levels.LevelSpecificationReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;

/**
 * author: Eyal Styskin
 * Class name: Ass5Game
 * class operation: main class to run the assignment.
 */
public class Ass6Game {
    /**
     * Function Name:main
     * Function Operation: create new list of levels and an AnimationRunner.
     * When arguments not being entered, the game starts with four levels that run one
     * after the other. When run with additional arguments, the arguments are being
     * treated as a list of level numbers to run, in the specified order.
     * also any argument which is not a number, or not in the levels range is ignored.
     *
     * @param args - numbers from 1 to 4 , represent levels order
     */
    public static void main(String[] args) {
        Reader reader = null;
        if (args.length == 0) {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
            reader = new InputStreamReader(is);
        } else {
            try {
                File file = new File(args[0]);
                if (file.exists()) {
                    reader = new FileReader(file);
                }
            } catch (FileNotFoundException e) {
                System.out.println("input file in args not found");
            }

        }

        LineNumberReader lineReader = new LineNumberReader(reader);

        final AnimationRunner animationRunner = new AnimationRunner();

        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>("Choose Difficulty",
                animationRunner.getGui().getKeyboardSensor(), animationRunner);
        while (true) {
            try {
                String line = lineReader.readLine();
                if (line == null) {
                    break;
                } else if (lineReader.getLineNumber() % 2 != 0) {
                    String[] keyVal = line.split(":");
                    String levelSetPath = lineReader.readLine();

                    Task<Void> levelSet = new Task<Void>() {
                        @Override
                        public Void run() {
                            LevelSpecificationReader reader = new LevelSpecificationReader();
                            GameFlow gameFlow = new GameFlow(animationRunner,
                                    animationRunner.getGui().getKeyboardSensor(), reader);

                            gameFlow.runLevels(levelSetPath);
                            return null;
                        }
                    };
                    subMenu.addSelection(keyVal[0], keyVal[1], levelSet);
                }
            } catch (IOException e) {
                System.out.println("cant read from file");
            }
        }


        Task<Void> highScores = new Task<Void>() {
            public Void run() {
                File file = new File("highscores.txt");
                HighScoresTable table = null;
                if (file.exists()) {
                    table = HighScoresTable.loadFromFile(file);
                } else {
                    table = new HighScoresTable(5);
                }
                animationRunner.run(new KeyPressStoppableAnimation(animationRunner.getGui().getKeyboardSensor(),
                        "space", new HighScoresAnimation(table)));
                return null;
            }
        };

        Task<Void> quit = new Task<Void>() {
            public Void run() {
                animationRunner.getGui().close();
                return null;
            }
        };

        Menu<Task<Void>> mainMenu = new MenuAnimation<>("Arkanoid",
                animationRunner.getGui().getKeyboardSensor(), animationRunner);
        mainMenu.addSubMenu("s", "start new game", subMenu);
        mainMenu.addSelection("h", "show highscores", highScores);
        mainMenu.addSelection("q", "quit", quit);
        while (true) {
            animationRunner.run(mainMenu);
            // wait for user selection
            Task<Void> task = mainMenu.getStatus();
            task.run();
            mainMenu.clearStatus();
        }
    }
}


