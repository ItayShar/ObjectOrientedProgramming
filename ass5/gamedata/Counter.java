//208005587 Itay Sharfer
package gamedata;
/**
 * The Counter class is used for counting.
 */
public class Counter {
    private int value;

    /**
     * The Counter method constructs a new counter instance with a 0 value.
     */
    public Counter() {
        this.value = 0;
    }

    /**
     * The Counter method constructs a new counter instance with a given number value.
     * @param number - a number that will be set as value upon construction.
     */
    public Counter(int number) {
        this.value = number;
    }

    /**
     * The increase method increases the current value of the instance by a given number.
     * @param number - a given number that the counter value will get increased by.
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * The decrease method decreases the current value of the instance by a given number.
     * @param number - a given number that the counter value will get decreased by.
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * The setValue method sets the instance's value to a given number.
     * @param number - the number which will be assigned as the new value.
     */
    public void setValue(int number) {
        this.value = number;
    }

    /**
     * The getValue method returns the instance value member.
     * @return - the instance value member.
     */
    public int getValue() {
        return this.value;
    }

}
