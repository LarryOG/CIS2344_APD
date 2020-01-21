package graph;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstTraversal<T> extends AdjacencyGraph<T> implements Traversal<T>  {

    private List<T> traversal = new ArrayList<T>();
    private List<T> backtrack = new ArrayList<T>();
    private List<T> visited = new ArrayList<T>();



    @Override
    /**
     * Perform a depth first traversal of the graph and return  a list of nodes
     * in the order in which they are visited.
     * @return a depth first traversal of the graph
     */
    public List<T> traverse() throws GraphError {


        T node = getUnvisitedNode();



        return traversal;
    }




    private T getUnvisitedNode() {
        for (T node: getNodes()) {
            if (!visited.contains(node)) {
                return node;
            }
        }
        return null;
    }

}
