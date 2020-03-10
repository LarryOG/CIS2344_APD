package semaphore;
/**
 * A local Semaphore class.
 * For the exercises using Java's Semaphore class leave this class as a subclass
 * of java.util.concurrent.Semaphore.
 * To test your implementation of unbounded semaphores call your unbounded semaphore
 * class e.g. UnboundedSemaphore and then change this class to be a subclass of your
 * unbounded semaphore class (i.e. Semaphore extends UnboundedSemaphore).
 * Similarly for the three implementations of bounded semaphore.  To test each of them
 * simply change this class to be a subclass of the relevant bounded semaphore class
 * and then run the buffer system.
 * Similarly for the three types of bounded semaphore.  You will then also need to edit
 * this code to use the constructor that calls the constructor of the superclass with 
 * two parameters.
 * 
 * @author Hugh Osborne
 * @version January 2020
 */
public class Semaphore extends LocalSemaphore
//public class Semaphore extends UnboundedSemaphore
//public class Semaphore extends AbsorbingSemaphore
//public class Semaphore extends CrashingSemaphore
//public class Semaphore extends BlockingSemaphore
//public class Semaphore extends SemaphoreSemaphore
{
    /**
     * A generic constructor, with a limit value for bounded semaphores.
     * For unbounded semaphores, such as this one, the limit parameter is
     * ignored.
     * @param name the name of this semaphore
     * @param initialValue the initial value for this semaphore
     * @param limit limit value for the semaphore
     */
    public Semaphore(String name,int initialValue,int limit) {
        super(name,initialValue,limit);
    }

    /**
     * A semaphore with no (explict) upper limit.
     *
     * @param name the name of this semaphore
     * @param initialValue the initial value for this semaphore
     */
    public Semaphore(String name,int initialValue) {
        super(name,initialValue);
    }
}
