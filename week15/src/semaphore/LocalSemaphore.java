package semaphore;


/**
 * A wrapper for Java's Semaphore class, effectively renaming java.util.semaphore's acquire() and release()
 * methods to match the poll() and vote() methods from the lecture.
 * 
 * @author Hugh Osborne 
 * @version January 2020
 */
public class LocalSemaphore extends java.util.concurrent.Semaphore implements SemaphoreInterface
{
    /**
     * This semaphore's name.
     */
    private String name;
    
    /**
     * A generic constructor, with a limit value for bounded semaphores.
     * For unbounded semaphores, such as this one, the limit parameter is
     * ignored.
     * @param name the name of this semaphore
     * @param initialValue the initial value for this semaphore
     * @param limit limit value for the semaphore (ignored, as this type of semaphore has no upper limit)
     */
    public LocalSemaphore(String name,int initialValue,int limit) {
        this(name,initialValue);
    }
    
    /**
     * A semaphore with no (explict) upper limit.
     *
     * @param name the name of this semaphore
     * @param initialValue the initial value for this semaphore
     */
    public LocalSemaphore(String name,int initialValue) {
        super(initialValue);
        this.name = name;
    }
    
    /**
     * Get the name of this semaphore.
     * @return the name of this semaphore
     */
    public String getName() {
        return name;
    }

    /**
     * Acquire this semaphore.
     * If the current thread:
     * <ul>
     *  <li>has its interrupted status set on entry to this method; or</li>
     *  <li>is interrupted while waiting for a permit,</li>
     * </ul>
     * then InterruptedException is thrown and the current thread's interrupted status is cleared.
     *
     * @throws InterruptedException if the current thread is interrupted.
     */
    public void poll() throws InterruptedException {
        super.acquire();
    }
    
    /**
     * Release this semaphore.
     *
     * @throws SemaphoreError (shouldn't happen: this should only happen for crashing bounded semaphores, where
     * increasing the semaphore's value would take it beyond the semaphore's maximum permitted value.
     */
    public void vote() throws SemaphoreError {
        super.release();
    }
    
}
