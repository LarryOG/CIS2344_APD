package searcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;



/**
 * Implements the find (kth) element method by sorting and indexing with the help of auxiliary array.
 *
 * @author Mateusz Larkowski
 * @version December 2019
 */


public class CleverSearcher extends Searcher {

    CleverSearcher (int[] array, int k) {
        super(array, k);
    }


    /**
     * Find the kth largest element in an array of ints using the "clever"
     * solution from the lecture
     *
     * <ul>
     *     <li> Load first k elements of array into auxiliary array </li>
     *     <li> Sort auxiliary array</li>
     *     <li> For every remaining element of given array, if its bigger than the smallest entry of auxiliary array</li>
     *     <li> Replace the smallest entry of auxiliary array</li>
     *     <li> Sort auxiliary array</li>
     *     <li> Return the smallest entry of the auxiliary array</li>
     * </ul>
     *
     * @return kth largest element of array
     */



    public int findElement() throws IndexingError{
        int[] array = getArray();
        int k = getIndex();
        if (k <= 0 || k > array.length) {
            throw new IndexingError();
        }

        int[] auxArray = Arrays.copyOf(array, k);
        Arrays.sort(auxArray);

        for (int i = k; i < array.length; i++) {
            if (array[i] > auxArray[0]) {
                auxArray[0] = array[i];
                Arrays.sort(auxArray);
            }
        }
        return auxArray[0];
    }






}
