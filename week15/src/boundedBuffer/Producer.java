package boundedBuffer;

import java.util.Random;
import semaphore.*;

/**
 * A simple producer class.
 *
 * @author Hugh Osborne
 * @version January 2020
 */

public class Producer<T> extends Thread
{
    /**
     * The buffer used by this producer.
     */
    private AbstractBuffer<T> buffer;
    /**
     * The factory used by this producer to build items to be added to the buffer.
     */
    private Factory<T> factory;
    /**
     * Determines this producer's speed.
     */
    private int delay;

    /**
     * Create a producer using the specified buffer and factory.
     * @param buffer the buffer used by this producer.
     * @param factory the factory used by this producer to build items to be added to the buffer.
     */
    public Producer(AbstractBuffer<T> buffer, Factory<T> factory) {
        this.buffer = buffer;
        this.factory = factory;
        setName("Producer");
    }
    
    /**
     * Set the delay for this producer.  The producer will wait for a length of time
     * after each buffer access.
     * @param delay the maximum length of time, in seconds, that the producer will wait.
     */
    public void setDelay(double delay) {
        this.delay = (int) (delay*1000);
    }

    /**
     * Run this producer.
     * As long as the buffer is open, repeatedly build a new item to be added to the buffer, and then (attempt to) add
     * it to the buffer.
     */
    public void run() {
        try {
            while (buffer.isOpen()) { // as long as the buffer is open
                T item = factory.make();
                System.out.println("\tProducer:\ttrying to add " + item + " to the buffer.");
                if (buffer.put(item)) { // put was successful
                    System.out.println("\tProducer:\tsuccessfully added " + item + " to the buffer.");
                }
                try {
                    sleep(new Random().nextInt(delay));
                } catch (InterruptedException ie) {}
            }
            System.out.println("\tProducer:\thas finished.");
        } catch (BufferError be) {
            System.out.println(be.getMessage());
        } catch (SemaphoreError sle) {
            System.out.println(sle.getMessage());
        }
    }
}
