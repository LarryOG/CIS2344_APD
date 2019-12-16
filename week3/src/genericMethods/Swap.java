package genericMethods;


import java.util.Arrays;

public class Swap {


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
