package boundedBuffer;

import semaphore.Semaphore;
import semaphore.SemaphoreError;

/**
 * Completes the implementation of buffers in {@link AbstractBuffer}, implementing the {@link #put(Object)} and
 * {@link #get()} methods.
 *
 * @param <T>  The type of object stored in this buffer.
 *
 * @author Hugh Osborne
 * @version February 2020
 */
public class Buffer<T> extends AbstractBuffer<T> {
    /**
     * The value of the semaphore is the number of spaces currently available in the buffer.
     * It should be impossible to add elements to the buffer when this semaphore reaches zero.
     */
    private Semaphore noOfSpaces;
    /**
     * The value of the semaphore is the number of elements currently in the buffer.
     * It should be impossible to take elements from the buffer when this semaphore reaches zero.
     */
    private Semaphore noOfElements;
    /**
     * This semaphore is used to control access to the critical section.
     */
    private Semaphore criticalSection;

    /**
     * The constructor sets the size of the buffer, and initialises the semaphores
     *
     * @param size the size of the buffer
     */
    public Buffer(int size) {
        super(size);
        noOfSpaces = new Semaphore("noOfSpaces",size); // all spaces in the buffer are initially empty
        noOfElements = new Semaphore("noOfElements",0);   // there are initially no elements in the buffer
        criticalSection = new Semaphore("criticalSection",1);     // allow at most one put or get action at a time
        open();
    }

    /**
     * Provide protected access to the buffer to allow an item to be added.
     * @param item the data item to be added to the buffer
     * @return true iff the put succeeded.  A put might fail if the buffer has closed between the
     *         put request, and the actual put.
     * @throws BufferError if it is not possible to add an item to the buffer due to an interrupted exception.
     * @throws SemaphoreError if any of the semaphores exceeds their limit.  This exception would probably
     *         arise as a consequence of an error in the implementation of semaphores.
     */
    public boolean put(T item) throws BufferError, SemaphoreError {
        boolean succeeded = false;
        try {
            noOfSpaces.poll();  // is there space in the buffer?
            criticalSection.poll();   // is the buffer available?
            succeeded = putItem(item);    // add the data item
            criticalSection.vote();   // make the buffer available again
            noOfElements.vote(); // there is now one more element in the buffer
        } catch (InterruptedException ie) {
            throw new BufferError("Buffer: Data item " + item + " could not be added to the buffer.\n" +
                    "\t" + ie.getMessage());
        }
        return succeeded;
    }

    /**
     * Provide protected access to the buffer to allow an item to be retrieved.
     * @return the data item that has been in the buffer longest or null if the retrieval failed because, e.g.., the
     * buffer has closed.
     * @throws BufferError if it was not possible to retrieve an item from the buffer due to an interrupted exception.
     * @throws SemaphoreError if any of the semaphores exceeds their limit.  This exception would probably
     *         arise as a consequence of an error in the implementation of semaphores.
     */
    public T get() throws BufferError, SemaphoreError {
        T item;
        try {
            noOfElements.poll(); // is there at least one data item in the buffer?
            criticalSection.poll();   // is the buffer available?
            item = getItem(); // add the data item
            criticalSection.vote();   // make the buffer available again
            noOfSpaces.vote();  // there is now one more space in the buffer
        } catch (InterruptedException ie) {
            throw new BufferError("Buffer: Data item could not be retrieved from the buffer.\n" +
                    "\t" + ie.getMessage());
        }
        return item;
    }

    /**
     * Close the buffer.
     * @throws SemaphoreError if any of the semaphores exceed their limits.  This exception would probably
     *         arise as a consequence of an error in the implementation of semaphores.
     */
    public synchronized void close() throws SemaphoreError {
        super.close();
        noOfSpaces.vote();
        noOfElements.vote();
        criticalSection.vote();
    }
}
