package boundedBuffer;

import java.util.Random;
import semaphore.*;

/**
 * A simple consumer class.
 *
 * @author Hugh Osborne
 * @version January 2020
 */

public class Consumer<T> extends Thread
{
    /**
     * The buffer used by this consumer.
     */
    private AbstractBuffer<T> buffer;
    /**
     * Determines this consumer's speed.
     */
    private int delay;

    /**
     * Construct a consumer using the specified buffer.
     * @param buffer the buffer used by this consumer.
     */
    public Consumer(AbstractBuffer<T> buffer) {
        this.buffer = buffer;
        setName("Consumer");
    }
    
    /**
     * Set the delay for this consumer.  The consumer will wait for a length of time
     * after each buffer access.
     * @param delay the maximum length of time, in seconds, that the consumer will wait.
     */
    public void setDelay(double delay) {
        this.delay = (int) (delay*1000);
    }

    /**
     * Run this consumer.
     * As long as the buffer is open repeatedly (attempt to) remove an item from the buffer.
     */
    public void run() {
        try {
            while (buffer.isOpen()) {
            	System.out.println("\tConsumer:\ttrying to retrieve data item.");
                T item = buffer.get();
                if (item != null) { // get was successful
                    System.out.println("\tConsumer:\tsuccessfully retrieved " + item + " from the buffer.");
                }
                try {
                    sleep(new Random().nextInt(delay));
                } catch (InterruptedException ie) {}
            }
            System.out.println("\tConsumer:\thas finished.");
        } catch (BufferError be) {
            System.out.println(be.getMessage());
        } catch (SemaphoreError sle) {
            System.out.println(sle.getMessage());
        }
    }
}
