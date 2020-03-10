package semaphore;


/**
 * A semaphore interface that matches the method names used in the lecture.
 *
 * @author Hugh Osborne
 * @version January 2020
 */

public interface SemaphoreInterface
{
    /**
     * Check if the value of the semaphore is greater than zero.
     * <ul>
     *     <li>If it is, decrease it by one and continue.</li>
     *     <li>If it is not, wait for a signal from a {@link #vote()}, which may allow you to continue.</li>
     * </ul>
     * If the current thread:
     * <ul>
     *  <li>has its interrupted status set on entry to this method; or</li>
     *  <li>is interrupted while waiting for a permit,</li>
     * </ul>
     * then InterruptedException is thrown and the current thread's interrupted status is cleared.
     *
     * @throws InterruptedException if the current thread is interrupted.
     */
    public void poll() throws InterruptedException;

    /**
     * Check if any processes are waiting on this semaphore.
     * <ul>
     *     <li>If so, wake one of the waiting processes.</li>
     *     <li>If not, increase the value of this semaphore by one.</li>
     * </ul>
     *
     * @throws InterruptedException (for blocking bounded semaphores) The current thread was interrupted.
     * If this is an implementation of a blocking bounded semaphore, then the semaphore may wait on a vote when it has
     *      reached its maximum value.  If it does, an InterruptedException could be raised, and the current thread's
     *      interrupted status cleared, if the current thread:
     *      <ul>
     *          <li>has its interrupted status set on entry to this method; or</li>
     *          <li>is interrupted while waiting for a permit,</li>
     *      </ul>
     * @throws SemaphoreError (for crashing bounded semaphores) Increasing the semaphore's value would take it
     *          beyond the semaphore's maximum permitted value.
     */
    public void vote() throws InterruptedException, SemaphoreError;

    /**
     * Get this semaphore's name.
     * @return the name of this semaphore.
     */
    public String getName();
}
