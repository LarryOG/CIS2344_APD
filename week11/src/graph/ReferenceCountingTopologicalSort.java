package graph;

import java.util.*;

public class ReferenceCountingTopologicalSort<T> extends AdjacencyGraph<T> implements TopologicalSort<T> {
    private List<T> sort;

    @Override
    public List<T> getSort() throws GraphError {
        List<T> listOfNodes = new ArrayList<>();
        List<Integer> listOfNeigboursNumbers = new ArrayList<>();


        sort = new ArrayList<T>();
        for (T node: getNodes()) {
            listOfNodes.add(node);
            int x =0;
            for (T neighbour: getNeighbours(node)) {
                x++;
            }
            listOfNeigboursNumbers.add(x);
        }

        int index = listOfNeigboursNumbers.indexOf(Collections.min(listOfNeigboursNumbers));
        sort.add(listOfNodes.get(index));





        return sort;
    }



}
