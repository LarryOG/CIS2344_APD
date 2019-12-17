package linkedList.list;

import arrayGenerator.generator.IntegerArrayGenerator;
import arrayGenerator.scope.IntegerScope;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A set of tests for SingleLinkedList
 * There is no test for get() method, because it has been actively used in both add() and remove() methods
 * @author Mateusz Larkowski
 * @version December 2019
 */
class SingleLinkListTest {

    @Test
    void testAdd() throws ListAccessError {
        SingleLinkList<Integer> integerSingleLinkList = new SingleLinkList<>();
        integerSingleLinkList.add(0,1);
        integerSingleLinkList.add(0,2);
        integerSingleLinkList.add(1,3);
        integerSingleLinkList.add(2,4);
        integerSingleLinkList.add(2,5);
        integerSingleLinkList.add(3,6);
        integerSingleLinkList.add(6,7);
        integerSingleLinkList.add(0,8);
        integerSingleLinkList.add(5,9);
        integerSingleLinkList.add(9,10);
    }

    @Test
    void testRemove() throws ListAccessError{
        SingleLinkList<Integer> list1 = new SingleLinkList<>();
        list1.add(0,1);
        list1.add(1,2);
        list1.add(2,3);
        list1.add(3,4);
        list1.add(4,5);

        list1.remove(1);

        SingleLinkList<Integer> list2 = new SingleLinkList<>();
        list2.add(0,1);
        list2.add(1,3);
        list2.add(2,4);
        list2.add(3,5);

        assertEquals(list2.toString(),list1.toString());
    }


    @Test
    void testHighIndexAddException(){
        SingleLinkList<Integer> list1 = new SingleLinkList<>();
        assertThrows(ListAccessError.class,()->list1.add(4,1));
    }

    @Test
    void testHighIndexRemoveException() {
        SingleLinkList<Integer> list1 = new SingleLinkList<>();
        assertThrows(ListAccessError.class,()->list1.remove(4));
    }

    /**
     * Populate the list with 100000 random Integers
     * @throws ListAccessError
     */
    @Test
    void testBigAmount() throws ListAccessError{
        IntegerArrayGenerator generator = new IntegerArrayGenerator(new IntegerScope(1,999));
        Integer[] array = generator.getArray(10000);
        SingleLinkList <Integer> testList = new SingleLinkList<>();
        for (int i=0; i<array.length; i++) {
            Integer value = array[i];
            testList.add(i,value);
        }

    }

}