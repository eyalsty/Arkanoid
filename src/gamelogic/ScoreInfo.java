package gamelogic;

import java.io.Serializable;

/**.
 * Author: Eyal Styskin
 * Class operation: holds scoreInfo of an player (its name and score)
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    /**.
     * constructor -
     * @param name the name of the player
     * @param score - this player's score
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**.
     * returns this name
     * @return - this name String
     */
    public String getName() {
        return this.name;
    }

    /**.
     * return this score
     * @return integer - this score
     */
    public int getScore() {
        return this.score;
    }
}
