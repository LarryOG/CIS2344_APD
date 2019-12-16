package genericMethods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// To be a complete model answer this test suite should probably include more tests, and should
// definitely be better documented.

class MaxTest {
    /**
     * A selection of tests for max
     */

   @Test
    void testMaxIntegerFullRange() {
        Integer[] array = {3,8,2,9,6,5,1};
        assertEquals(new Integer(9), Max.max(array,0,7));
    }


    @Test
    void testMaxIntegerPartialRange() {
        Integer[] array = {3,8,2,9,6,5,1};
        assertEquals(new Integer(8), Max.max(array,0,3));
    }

    @Test
    void testMaxStringFullRange() {
        String[] array = {"antelope","a","aardvark"};
        assertEquals("antelope", Max.max(array,0,3));
    }

    @Test
    void testMaxStringPartialRange() {
        String[] array = {"antelope","a","aardvark"};
        assertEquals("aardvark", Max.max(array,1,3));
    }

    // Test errors

    @Test
    void testNullArrayException() {
       assertThrows(NullPointerException.class,()->
            Max.max(null,1,4));
    }

    @Test
    void testNegativeIndexException() {
       Integer[] array = {3,8,2,9,6,5,1};
       assertEquals(new
               Integer(8), Max.max(array,0,3));
       assertThrows(ArrayIndexOutOfBoundsException.class,()->
            Max.max(array,-1,4));
    }

    @Test
    void testHighIndexException() {
        Integer[] array = {3,8,2,9,6,5,1};
        assertEquals(new Integer(8), Max.max(array,0,3));
        assertThrows(ArrayIndexOutOfBoundsException.class,()->
                Max.max(array,2,8));
    }

    @Test
    void testIndicesReversedException() {
        Integer[] array = {3,8,2,9,6,5,1};
        assertEquals(new Integer(8), Max.max(array,0,3));
        assertThrows(ArrayIndexOutOfBoundsException.class,()->
                Max.max(array,5,2));
    }

    @Test
    void testNegativeIndicesEqualException() {
        Integer[] array = {3,8,2,9,6,5,1};
        assertEquals(new Integer(8), Max.max(array,0,3));
        assertThrows(ArrayIndexOutOfBoundsException.class,()->
                Max.max(array,4,4));
    }

    /**
     * Create own comparable class to test max
     * Class "boxes" two comparables
     */
    private class Box<A extends Comparable<A>,B extends Comparable<B>> implements Comparable<MaxTest.Box<A,B>> {
        private A first;
        private B second;

        Box(A first, B second) {
            this.first = first;
            this.second = second;
        }

        A getFirst() {
            return first;
        }

        B getSecond() {
            return second;
        }

        /**
         * In comparisons first entry takes precedence over second
         */
        public int compareTo(MaxTest.Box<A,B> box) {
            if (getFirst().compareTo(box.getFirst())!=0) {
                return getFirst().compareTo(box.getFirst());
            } else {
                return getSecond().compareTo(box.getSecond());
            }
        }

        /**
         * Need to define equality as well
         */
        public boolean equals(Object object) {
            if (!(object instanceof MaxTest.Box<?,?>)) {
                return false;
            }
            MaxTest.Box<?,?> box = (MaxTest.Box<?,?>) object;
            return getFirst().equals(box.getFirst()) && getSecond().equals(box.getSecond());
        }

    }

    @Test
    void testBox1() {
        @SuppressWarnings("unchecked") // can't create array of Box<String,Integer>[], so create Box[] instead
                MaxTest.Box<String,Integer>[] array = new MaxTest.Box[5];
        array[0] = new MaxTest.Box<>("antelope", 7);
        array[1] = new MaxTest.Box<>("aardwolf", 7);
        array[2] = new MaxTest.Box<>("aardvark", 7);
        array[3] = new MaxTest.Box<>("aardvark", 9);
        array[4] = new MaxTest.Box<>("aardvark", 7);
        assertEquals(new MaxTest.Box<>("antelope", 7), Max.max(array,0,5));
    }

    @Test
    void testBox2() {
        @SuppressWarnings("unchecked") // can't create array of Box<String,Integer>[], so create Box[] instead
                MaxTest.Box<String,Integer>[] array = new MaxTest.Box[5];
        array[0] = new MaxTest.Box<>("aardvark", 8);
        array[1] = new MaxTest.Box<>("aardvark", 3);
        array[2] = new MaxTest.Box<>("aardvark", 1);
        array[3] = new MaxTest.Box<>("aardvark", 9);
        array[4] = new MaxTest.Box<>("aardvark", 5);
        assertEquals(new MaxTest.Box<>("aardvark", 9), Max.max(array,0,5));
    }

    @Test
    void testBox3() {
        @SuppressWarnings("unchecked") // can't create array of Box<String,Integer>[], so create Box[] instead
                MaxTest.Box<String,Integer>[] array = new MaxTest.Box[5];
        array[0] = new MaxTest.Box<>("aardvark", 5);
        array[1] = new MaxTest.Box<>("aardvark", 5);
        array[2] = new MaxTest.Box<>("aardvark", 5);
        array[3] = new MaxTest.Box<>("aardvark", 5);
        array[4] = new MaxTest.Box<>("aardvark", 5);
        assertEquals(new MaxTest.Box<>("aardvark", 5), Max.max(array,0,5));
    }
}