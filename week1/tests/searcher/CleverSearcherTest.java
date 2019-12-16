package searcher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CleverSearcherTest extends SearcherTest {

    /**
     *  Implements createSearcher to return CleverSearcher,
     *  so that the CleverSearcher can be tested using same tests as SimpleSearcher
     *  (by tests inherited from SearcherTest)
     * @author Mateusz Larkowski
     * @version December 2019
     */


    protected Searcher createSearcher(int[] array, int index) throws IndexingError {
        return new CleverSearcher(array,index);
    }


}