package boundedBuffer;


/**
 * A simple buffer error class.
 * 
 * @author Hugh Osborne 
 * @version January 2020
 */
public class BufferError extends Exception
{
    public BufferError() {
        super("Buffer error: no further information");
    }
    
    public BufferError(String message) {
        super("Buffer error: " + message);
    }
}
