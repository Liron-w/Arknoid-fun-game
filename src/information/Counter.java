//i.d 206505588
/**
 * @author Liron Vaitzman <lironv4@gmail.com>
 * @version 1.0
 * @since 2020-05-01
 */
package information;

/**
 * a simple class that is used for counting things.
 */
public class Counter {

    private int counter;

    /**
     * constructor that created counter and update it to be 0.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * add number to current count.
     *
     * @param number - the number that we add to the current count.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number - the number that we add to the current count.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * get current count.
     *
     * @return the current count.
     */
    public int getValue() {
        return this.counter;
    }
}