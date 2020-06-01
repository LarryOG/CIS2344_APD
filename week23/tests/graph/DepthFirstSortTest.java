package graph;

/**
 * <p>
 * A test class for depth first topological sorts.  This class inherits the test method testGraph from the
 * TopologicalSortTest class, and uses it to define specific test methods for specific graphs.
 * </p>
 * <p>
 * The class currently only contains one test.  There clearly should be more.
 * </p>
 *
 * @version March 2020
 * @author Hugh Osborne.
 */
class DepthFirstSortTest extends TopologicalSortTest
{
    /**
     * Test the example graph from lecture 11.
     *
     * @throws GraphError This shouldn't happen, as the graph is well constructed.  If it does happen it will
     * probably be due to an error in the testGraph method.
     */
    public void testLectureGraphDepthFirst() throws GraphError {
        TopologicalSort<Integer> lectureGraph = new DepthFirstSort<Integer>();
        lectureGraph = buildLectureGraph(lectureGraph);
        testGraph(lectureGraph);
    }
}