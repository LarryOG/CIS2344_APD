package semaphore;


/**
 * Blocking binary semaphores.
 * 
 * @author Hugh Osborne 
 * @version January 2020
 */
public class BinarySemaphore extends BlockingSemaphore
{
    /**
     * This is a binary semaphore, so the limiting value is predefined as being one.
     * @param name the name of this semaphore
     * @param initialValue the initial value for this semaphore
     * @throws SemaphoreError if the initial value is not zero or one (i.e. is negative, or greater than the limit).
     */
    public BinarySemaphore(String name,int initialValue) throws SemaphoreError
    {
        super(name,initialValue,1);
    }
}
