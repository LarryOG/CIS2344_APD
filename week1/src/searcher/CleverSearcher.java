package searcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;



/**
 * Implements the find (kth) element method by ...
 *
 * @author Mateusz Larkowski
 * @version December 2019
 */


public class CleverSearcher extends Searcher {

    CleverSearcher (int[] array, int k) {
        super(array, k);
    }




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
