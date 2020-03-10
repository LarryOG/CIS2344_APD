package boundedBuffer;

import semaphore.*;

import java.util.Arrays;

/**
 * An implementation of buffers using semaphores to protect access to the buffer.
 *
 * @param <T> the type of object stored in the buffer.
 *
 * @author Hugh Osborne
 * @version January 2020
 */
public abstract class AbstractBuffer<T>
{
    /**
     * The buffer.
     */
    private T[] buffer;    // the buffer
    /**
     * Index to the current position in the array where the next item should be added.
     * I.e. the index of the "first" empty space in the buffer.
     */
    private int putIndex;
    /**
     * Index to the current position in the array where the next item should be removed.
     * I.e. the index of the "first" non-empty space in the buffer.
     */
    private int getIndex;
    /**
     * The number of elements currently in the buffer.
     */
    private int elements;
    /**
     * Is the buffer currently open?
     */
    private boolean isOpen = false; // is the buffer open for business

    /**
     * The constructor sets the size of the buffer, and initialises the buffer pointers.
     * @param size the size of the buffer
     */            
	public AbstractBuffer(int size) {
        buffer = (T[]) new Object[size];     // Note: it is not straightforward to create arrays of generics in Java.
                                             // Create an array of Objects instead, and cast to an array of the generic type.
        putIndex = 0;                  // start filling the buffer from index 0
        getIndex = 0;                  // start retrieving data from index 0
        elements = 0;                  // initially no elements in the buffer
    }

    /**
     * Open the buffer.
     */
    public synchronized void open() {
        isOpen = true;
    }

    /**
     * Close the buffer.
     * @throws SemaphoreError if any of the semaphores exceed their limits.  This exception would probably
     *         arise as a consequence of an error in the implementation of semaphores.
     */
    public synchronized void close() throws SemaphoreError {
        isOpen = false;
    }

    /**
     * Check if the buffer is open.
     * @return (the buffer is open)
     */
    public synchronized boolean isOpen() {
        return isOpen;
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
    public abstract boolean put(T item) throws BufferError, SemaphoreError;
    
    /**
     * Provide protected access to the buffer to allow an item to be retrieved.
     * @return the data item that has been in the buffer longest or null if the retrieval failed because, e.g.., the
     * buffer has closed.
     * @throws BufferError if it was not possible to retrieve an item from the buffer due to an interrupted exception.
     * @throws SemaphoreError if any of the semaphores exceeds their limit.  This exception would probably
     *         arise as a consequence of an error in the implementation of semaphores.
     */
    public abstract T get() throws BufferError, SemaphoreError;
    
    /**
     * Add a data item to the buffer.
     * @param item the data item to be added to the buffer
     * @return true iff the item was successfully added. A putItem() might fail if the buffer has closed between the
     *         put request, and the actual put.
     * @throws BufferError if the buffer is already full
     */
    boolean putItem(T item) throws BufferError {
        if (!isOpen()) {
            // can't add to a closed buffer, but may still be calls of putItem - ignore them
            return false;
        }
        if (elements >= buffer.length) {
            throw new BufferError("Buffer:\tCannot add " + item + " to buffer - buffer is full.");
        }
        buffer[putIndex%buffer.length] = item; // put the data item in the buffer
        putIndex++;               // and increase the put index
        elements++;
        //System.out.println("Buffer:\t\t" + item + " added, now " + elements + " item" + (elements == 1 ? "" : "s") + " in the buffer.");
        System.out.println("\tBuffer:\t\t" + item + " added, buffer is now " + Arrays.toString(buffer).replaceAll("null","_") + ".");
        return true;
    }
    
    /**
     * Retrieve a data item from the buffer.
     * @return datum the data item that has been in the buffer longest, or null if the buffer has closed.
     * @throws BufferError if the buffer is empty.
     */
    T getItem() throws BufferError {

        if (!isOpen()) {
            // can't retrieve from a closed buffer, but may still be calls of getItem - ignore them
            return null;
        }
        if (elements <= 0) {
            throw new BufferError("Buffer: Cannot get data item from buffer - buffer is empty.");
        }
        T item = buffer[getIndex%buffer.length]; // get the oldest data item in the buffer
        buffer[getIndex%buffer.length] = null;
        getIndex++; // and increase the get index
        elements--;
        //System.out.println("Buffer:\t\t" + item + " removed, now " + elements + " item" + (elements == 1 ? "" : "s") + " in the buffer.");
        System.out.println("\tBuffer:\t\t" + item + " removed, buffer is now " + Arrays.toString(buffer).replaceAll("null","_") + ".");
        if (getIndex > putIndex) { // check that we haven't overtaken the producer
            throw new BufferError("Buffer: The get index has overtaken the put index.");
        }
        return item;
    }    
}
