package semaphore;


/**
 * Report bounded semaphores exceeding their limits.
 * Used for crashing bounded semaphores.
 * 
 * @author Hugh Osborne
 * @version January 2020
 */
public class SemaphoreLimitError extends Exception
{
    public SemaphoreLimitError(SemaphoreInterface semaphore)
    {   
        super(Thread.currentThread().getName() + " caused " + semaphore.getName() + " to exceed its upper limit");
    }
}
