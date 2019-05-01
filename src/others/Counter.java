package others;

/**
 * author: Eyal Styskin
 * Class name:Counter
 * class operation: acts as a Counter object that allows to counter lives/score and other things.
 * supports increasing , decreasing.
 */
public class Counter {
    private int value = 0;

    /**
     * add number to current count.
     *
     * @param number - integer - the value to add to the current value
     */
    public void increase(int number) {
        this.value = value + number;
    }

    /**
     * subtract number from current count.
     *
     * @param number - integer- the number to substract from current value
     */
    public void decrease(int number) {
        this.value = value - number;
    }

    /**
     * getter for current count.
     *
     * @return - this.value (integer)
     */
    public int getValue() {
        return this.value;
    }
}