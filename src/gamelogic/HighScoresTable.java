package gamelogic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**.
 * Author: Eyal Styskin
 * Class object holds table of the highest scores ever made in the game
 */
public class HighScoresTable implements Serializable {
    private List<ScoreInfo> scores;
    private int tableCapacity;

    /**.
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     *
     * @param size - integer - max size of table
     */
    public HighScoresTable(int size) {
        this.scores = new ArrayList<ScoreInfo>(size);
        this.tableCapacity = 5;
    }

    /**.
     * Add a high-score
     *
     * @param score - the score of the game
     */
    public void add(ScoreInfo score) {
        int rank = this.getRank(score.getScore());
        if (rank > tableCapacity) {
            return;
        }
        scores.add(rank - 1, score);
        if (this.size() > tableCapacity) {
            this.scores.remove(tableCapacity);
        }
    }

    /**.
     * Return table size.
     *
     * @return - integer - number of scoreInfo elements in array
     */
    public int size() {
        return this.scores.size();
    }

    /**.
     * Return the current high scores.
     * The list is sorted such that the highest
     * scores come first.
     *
     * @return ArrayList of ScoreInfo elements
     */
    public List<ScoreInfo> getHighScores() {
        return this.scores;
    }

    /**.
     * return the rank of the current score: where will it be on the list if added?
     * Rank 1 means the score will be highest on the list. Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not be added to the list.
     *
     * @param score - integer - score of the played game
     * @return int - rank
     */
    public int getRank(int score) {
        for (int i = 0; i < this.scores.size(); ++i) {
            if (score > this.scores.get(i).getScore()) {
                return i + 1;
            }
        }
        return this.scores.size() + 1;
    }

    /**.
     * return the capacity of the table
     * @return this table capacity - integer
     */
    public int getTableCapacity() {
        return this.tableCapacity;
    }

    /**.
     * Clears the table
     */

    public void clear() {
        this.scores.clear();
    }
    /**.
     * Load table data from file. Current table data is cleared.
     *
     * @param filename - File of Highscores
     * @throws IOException - Exception cant read the file
     */
    public void load(File filename) throws IOException {
        this.clear();
        HighScoresTable table;
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(filename));
            table = (HighScoresTable) objectInputStream.readObject();
            for (int i = 0; i < table.size(); ++i) {
                this.scores.add(table.getHighScores().get(i));
            }
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + filename.getName());
            return;
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + filename.getName());
            return;
        } catch (IOException e) { // Some other problem
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
            return;
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename.getName());
                return;
            }
        }
    }

    /**.
     * Save table data to the specified file.
     *
     * @param filename - File of highScores
     * @throws IOException - Exception cant read the file
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename.getName());
            }
        }
    }

    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param filename - File off highScores
     * @return HighScores table
     */

    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable highScoresTable = new HighScoresTable(5);
        try {
            highScoresTable.load(filename);
        } catch (IOException e) { // Some other problem
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
        }
        return highScoresTable;
    }
}

