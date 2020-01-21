package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstTraversal<T> extends AdjacencyGraph<T> implements Traversal<T>  {

    private List<T> traversal = new ArrayList<>();
    private Stack<T> stack = new Stack<>();
    private List<T> visited = new ArrayList<>();



    @Override
    /**
     * Perform a depth first traversal of the graph and return  a list of nodes
     * in the order in which they are visited.
     * @return a depth first traversal of the graph
     */
    public List<T> traverse() throws GraphError {

        int noOfNodes = getNoOfNodes();
        for (int i=0; i<noOfNodes; i++) {
            T node = getUnvisitedNode();
            stack.push(node);
            visited.add(node);
            while (!stack.isEmpty()){
                traversal.add(stack.pop());
                for (int x=0; x < traversal.size(); x++){
                    T toDo = traversal.get(x);
                    for (T neighbour: getNeighbours(toDo)){
                        if (!visited.contains(neighbour)) {
                            stack.push(neighbour);
                        }
                    }
                }

            }

        }
        return traversal;

    }



    /**
     * Get the next "unvisited" node.
     * @return a node that has not yet been visited or return null if no such node exists
     */
    private T getUnvisitedNode() {
        for (T node: getNodes()) {
            if (!visited.contains(node)) {
                return node;
            }
        }
        return null;
    }

}
