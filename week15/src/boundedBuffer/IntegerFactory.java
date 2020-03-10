package boundedBuffer;


/**
 * A Factory that builds Integers.  The default initial Integer has value zero.  The value of the
 * Integer produced increments for each Integer produced.
 * 
 * @author Hugh Osborne
 * @version January 2020
 */
public class IntegerFactory implements Factory<Integer>
{
    /**
     * The value of the next integer to be constructed by this factory.
     */
    private int counter;

    /**
     * Start this integer factory with the initial value at zero.
     */
    public IntegerFactory() {
        counter = 0;
    }

    /**
     * Start this integer factory with the specified initial value
     * @param n the initial value for this integer factory.
     */
    public IntegerFactory(int n) {
        counter = n;
    }

    /**
     * Make a new integer.
     * Increase the value to be produced by the next call of make by one.
     * @return a new integer with the current value.
     */
    public Integer make() {
        Integer item = new Integer(counter);
        counter++;
        return item;
    }
}
