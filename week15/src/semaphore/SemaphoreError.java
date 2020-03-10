package semaphore;


/**
 * Report bounded semaphores exceeding their limits.
 * Used for crashing bounded semaphores.
 * 
 * @author Hugh Osborne
 * @version January 2020
 */
public class SemaphoreError extends Exception
{
    public SemaphoreError(SemaphoreInterface semaphore)
    {   
        super();
    }

    public SemaphoreError(String message) {
        super(message);
    }
}
