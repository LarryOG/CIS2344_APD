package binaryTree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 *  A selection of tests for sorted binary tree implementation.
 * @author Mateusz Larkowski
 * @version December 2019
 */

class BinaryTreeTest {



    @Test
     void testIsEmpty () {
        BinaryTree tree = new BinaryTree();
        assertTrue(tree.isEmpty());
    }

    @Test
    void testInsertRoot () {
        BinaryTree tree = new BinaryTree();
        tree.insert(10);
        assertEquals(tree.traverse(),new BinaryTree(10).traverse());
    }

    @Test
    void testInsertTraverse1 () {
        BinaryTree tree = new BinaryTree(10);
        tree.insert(11);
        assertEquals(tree.traverse(),new BinaryTree(10,new BinaryTree(),new BinaryTree(11)).traverse());
    }

    @Test
    void testInsertTraverse2 () {
        BinaryTree tree = new BinaryTree(10,new BinaryTree(6),new BinaryTree(15));
        tree.insert(11);
        List test = new ArrayList();
        test.add(6);
        test.add(10);
        test.add(11);
        test.add(15);

        assertEquals(tree.traverse(),test);
    }

    @Test
    void testInsertTraverse3 () {
        BinaryTree tree = new BinaryTree(10,new BinaryTree(6),new BinaryTree(15));
        tree.insert(11);
        tree.insert(2);
        tree.insert(5);
        tree.insert(4);
        tree.insert(9);
        List test = new ArrayList();
        test.add(2);
        test.add(4);
        test.add(5);
        test.add(6);
        test.add(9);
        test.add(10);
        test.add(11);
        test.add(15);

        assertEquals(tree.traverse(),test);
    }
    @Test
    void testGetValue () {
        BinaryTree tree = new BinaryTree(10);
        assertEquals(tree.getValue(),10);
    }

    @Test
    void testGetValueNullPointerException () throws NullPointerException {
        BinaryTree tree = new BinaryTree();
        assertThrows(NullPointerException.class, ()-> tree.getValue());
    }

    @Test
    void testContains () {
        BinaryTree tree = new BinaryTree(10,new BinaryTree(6),new BinaryTree(15));
        assertTrue(tree.contains(6));
    }
}