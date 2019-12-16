package searcher;

import arrayGenerator.ArrayGenerator;
import arrayGenerator.CleverRandomListingGenerator;
import timer.Timer;


/**
 * A implementation of timer Class created to time clever searchers findElement() method.
 *
 * @author Mateusz Larkowski
 * @version December 2018
 */


public class CleverSearcherTimer extends CleverSearcher implements Timer {

    // All timings will be done with an index of 5
    private final static int K = 5;

    CleverSearcherTimer(int[] array) {
        super(array, K);
    }


    /**
     *
     * @param size the size of the task to be timed. i.e size of array for clever searcher
     * @return CleverSearcher for generated array of required size
     */

    @Override
    public Timer getTimer(int size) {
        ArrayGenerator generator = new CleverRandomListingGenerator(size);
        return new CleverSearcherTimer(generator.getArray());
    }


    /**
     * We are timing findElement method in a scenario when indexing errors never occur.
     */
    @Override
    public void timedMethod() {
        try {
            findElement();
        }catch (IndexingError indexingError){

        }

    }

    /**
     * Finish execution if runtime exceeds maximum time
     * @return maximum allowed runtime i.e 3
     */
    @Override
    public long getMaximumRuntime() {
        return 3;
    }


    /**
     * Size of the smallest array we will be timing our method on.
     * @return minimum array size i.e 10
     */
    @Override
    public int getMinimumTaskSize() {
        return 10;
    }

    /**
     * Size of the biggest array we will be timing our method on.
     * N.B. if the runtime of the method exceeds allowed above limit, the process will be terminated
     * @return maximum array size i.e 1000000000
     */

    @Override
    public int getMaximumTaskSize() {
        return 1000000000;
    }

    /**
     * Runs timingSequence method specified by timer interface
     * @param args not used
     * @throws IndexingError won't occur
     */
    public static void main(String[] args) throws IndexingError {
        CleverSearcherTimer timer = new CleverSearcherTimer(null);
        timer.timingSequence();
    }
}

