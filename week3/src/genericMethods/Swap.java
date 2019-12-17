package genericMethods;


import java.util.Arrays;

/**
 * Provide a static method that will swap two entries in an array
 *
 * @author Mateusz Larkowski
 * @version December 2019
 *
 */

public class Swap {

    /**
     * Swap two elements of an array.
     * @param array the array to be searched.
     * @param x position of 1st element
     * @param y position of 2nd element
     * @return array with swapped entries.
     */

    public static <E> E[] swapElements(E[] array, int x, int y) {

        E [] swappedArray = Arrays.copyOf(array,array.length);

        if (x < 0 || x > array.length) {
            throw new ArrayIndexOutOfBoundsException("x must be between [0," + array.length + "] inclusive");
        }else  if (y < 0 || y > array.length){
            throw new ArrayIndexOutOfBoundsException("y must be between [0," + array.length + "] inclusive");
        }

        E xEntry = array[x];
        E yEntry = array[y];
        array[x] = yEntry;
        array[y] = xEntry;

        return array;
    }


}
