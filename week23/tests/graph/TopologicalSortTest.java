package graph;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 * Test class for topological sorts.  Subclasses of this class should be defined for each implementation of
 * topological sorts.  If the test method defined here is well defined it should be inheritable by all such subclasses.
 * </p>
 * <p>
 * The test method is currently only a stub.  The implementation is to be completed.
 * </p>
 * <p>
 * The class also defines a method for constructing a copy of the graph used in lecture 11 as an example.
 * </p>
 *
 * @version March 2020
 * @author Hugh Osborne
 */
class TopologicalSortTest {
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }

    /**
     * Given an empty (topological sort) graph will add nodes and links to create the graph
     * used as an example in the topological sort lecture (week 11).
     * An empty graph of the required type should be created and passed to this method.
     * @param graph the initial (empty) graph.
     * @return the graph now populated with the nodes and links from the example graph from the lecture.
     * @throws GraphError this shouldn't happen as the graph is correctly constructed.
     */
    public TopologicalSort<Integer> buildLectureGraph(TopologicalSort<Integer> graph) throws GraphError {
        graph.add(0);
        graph.add(1);
        graph.add(2);
        graph.add(3);
        graph.add(4);
        graph.add(5);
        graph.add(6);
        graph.add(7);
        graph.add(8);
        graph.add(9);
        graph.add(0, 1);
        graph.add(0, 5);
        graph.add(1, 7);
        graph.add(3, 2);
        graph.add(3, 4);
        graph.add(3, 8);
        graph.add(6, 0);
        graph.add(6, 1);
        graph.add(6, 2);
        graph.add(8, 7);
        graph.add(8, 4);
        return graph;
    }

    /**
     * Test whether the (topological sort) graph does create a correct topological sort.
     * @param graph the graph to be tested.
     * @param <T> the type of node in the graph to be tested.
     * @throws GraphError if the test method is incorrect, or if the graph is incorrectly constructed.
     */
    public <T> void testGraph(TopologicalSort<T> graph) throws GraphError
    {
        // Get the graph's topological sort
        List<T> traversal = graph.getSort();
        // Put your tester code here...
        // It should check that the traversal has the correct properties
        // required of a topological sort for the given graph.
        // See the topological sort lecture notes (week 11) for ideas on
        // what some of these properties might be.
    }
}