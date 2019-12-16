package genericMethods;



import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SwapTest {


    private void testSwapStrings(int index1,int index2)  {


        String[] array = new String[]{"Adam","Benny","Cindy","Daniel","Eddy","Fiona","Greg","Hanna","Ida","John"};

        String value1 = array[index1];
        String value2 = array[index2];

        String[] testerArray = Arrays.copyOf(array,array.length);
        testerArray[index1] = value2;
        testerArray[index2] = value1;

        assertTrue(Arrays.equals(testerArray,Swap.swapElements(array,index1,index2)));

    }

    private void testSwapDouble(int index1,int index2)  {


        Double[] array = new Double[]{0.1,1.1,2.1,3.1,4.1,5.1,6.1,7.1,8.1,9.1};

        Double value1 = array[index1];
        Double value2 = array[index2];

        Double[] testerArray = Arrays.copyOf(array,array.length);
        testerArray[index1] = value2;
        testerArray[index2] = value1;

        assertTrue(Arrays.equals(testerArray,Swap.swapElements(array,index1,index2)));

    }



    private void testSwapInteger(int index1,int index2) {


        Integer[] array = new Integer[]{0,1,2,3,4,5,6,7,8,9};

        Integer value1 = array[index1];
        Integer value2 = array[index2];

        Integer[] testerArray = Arrays.copyOf(array,array.length);
        testerArray[index1] = value2;
        testerArray[index2] = value1;

        assertTrue(Arrays.equals(testerArray,Swap.swapElements(array,index1,index2)));

    }


    private void testSwapCharacter(int index1,int index2)  {


        Character[] array = new Character[]{0,'b',2,'d',4,'f',6,'h',8,'j'};


        Character value1 = array[index1];
        Character value2 = array[index2];

        Character[] testerArray = Arrays.copyOf(array,array.length);
        testerArray[index1] = value2;
        testerArray[index2] = value1;

        assertTrue(Arrays.equals(testerArray,Swap.swapElements(array,index1,index2)));

    }



    @Test
    void test0n1Strings() {
        testSwapStrings(0,1);
    }

    @Test
    void test1n7Strings(){
        testSwapStrings(1,7);
    }

    @Test
    void test2n9Strings() {
        testSwapStrings(2,9);
    }

    @Test
    void test0n0Strings(){
        testSwapStrings(0,0);
    }


    @Test
    void test0n1Double(){
        testSwapDouble(0,1);
    }

    @Test
    void test1n7Double(){
        testSwapDouble(1,7);
    }

    @Test
    void test2n9Double(){
        testSwapDouble(2,9);
    }

    @Test
    void test0n0Double(){
        testSwapDouble(0,0);
    }

    @Test
    void test2n3Integer(){
        testSwapInteger(2,3);
    }

    @Test
    void test1n4Integer(){
        testSwapInteger(1,4);
    }

    @Test
    void test2n9Integer(){
        testSwapInteger(2,9);
    }

    @Test
    void test3n3Integer(){
        testSwapInteger(2,2);
    }

    @Test
    void test0n9Character(){
        testSwapCharacter(0,9);
    }

    @Test
    void test7n1Character(){
        testSwapCharacter(7,1);
    }

    @Test
    void test6n5Character(){
        testSwapCharacter(5,6);
    }

    @Test
    void test9n3Character()  {
        testSwapCharacter(9,3);
    }

    @Test
    void testMultipleObjectArraySwap() {

        Object[] array = new Object[]{1,'a',2.5,"string",new int[]{1,2,3,4,5,6}};
        Object[] array2 = new Object[]{new int[]{1,2,3,4,5,6},'a',2.5,"string",1};

        assertTrue(Arrays.equals(Swap.swapElements(array,0,4),array2));
    }

    @Test
    void testNegativeIndexException() {

        assertThrows(ArrayIndexOutOfBoundsException.class,()-> testSwapCharacter(9,-2));

    }

    @Test
    void testNullArrayException() {

        Object[] array = null;
        assertThrows(NullPointerException.class,()-> Swap.swapElements(array,1,2));

    }


    @Test
    void testHighIndexException() {

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> testSwapCharacter(9, 29));
    }
    }