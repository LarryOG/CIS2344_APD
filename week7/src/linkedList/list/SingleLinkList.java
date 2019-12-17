package linkedList.list;

import javafx.scene.control.Alert;
import linkedList.node.ListNode;
import linkedList.node.SingleLinkNode;

/**
 * A complete implementation of the List interface.
 *
 * @param <T> the type of object stored in the list.
 * 
 * @author Mateusz Larkowski
 * @version December 2019
 */
public  class SingleLinkList<T> extends BasicList<SingleLinkNode<T>,T> implements List<T> {

    /**
     * A helper method to access a node at a specified index.
     *
     * @param index the index of the node to be accessed.
     * @throws ListAccessError if there is no node with the given index.
     * @return Node at given index
     */
    SingleLinkNode<T> getNode(int index) throws ListAccessError {
        if (isEmpty()) {
            throw new ListAccessError("Cannot get node.  List is empty.");
        }
        if (index < 0) {
            throw new ListAccessError("Cannot get node.  Negative index.");
        }

        SingleLinkNode<T> currentNode = getRoot();
        while (index != 0 && currentNode != null) {
            currentNode = currentNode.getNext();
            index--;
        }
        if (currentNode == null) {
            throw new ListAccessError("Cannot get node.  Not enough nodes in the list.");
        }
        return currentNode;
    }

    /**
     * Access the value at a given index.
     *
     * @param index the index of the value to be accessed.
     * @throws ListAccessError if there is no value with the given index.
     * @return Value at given index
     */
    public T get(int index) throws ListAccessError {
        return getNode(index).getValue();
    }

    /**
     * Remove the node at given position
     * @param index the index of the entry to be removed.
     * @return value of removed node
     * @throws ListAccessError if there i no value at given index.
     */
    public T remove(int index) throws ListAccessError {
        if (isEmpty()) {
            throw new ListAccessError("Cannot remove node.  List is empty.");
        }else if (index < 0) {
            throw new ListAccessError("Cannot remove node.  Negative index.");
        }

        SingleLinkNode<T> node = null;
        try {
            node = getNode(index);
            SingleLinkNode<T> prevNode = getNode(index-1);
            prevNode.setNext(node.getNext());
        } catch (ListAccessError listAccessError) {
            throw new ListAccessError("Cannot remove node.  Not enough nodes in the list.");
        }
        return node.getValue();
    }

    /**
     * Add node at specified position.
     * @param index the index at which the new entry should be added.
     * @param value the value to be added.
     * @throws ListAccessError if index is out of bounds.
     */
    public void add(int index, T value) throws ListAccessError {
        if (index < 0) {
            throw new ListAccessError("Cannot add node.  Negative index.");
        }else if (index == 0) {
            SingleLinkNode<T> addNode = new SingleLinkNode<T>(value,getRoot());
            setRoot(addNode);
        }else {
            try {
                SingleLinkNode<T> nodeToAdd = new SingleLinkNode<>(value);
                SingleLinkNode<T> prevNode = getNode(index-1);
                SingleLinkNode<T> nextNode = prevNode.getNext();
                prevNode.setNext(nodeToAdd);
                nodeToAdd.setNext(nextNode);
            } catch (ListAccessError listAccessError) {
                throw new ListAccessError("Cannot add node at given position.  Not enough nodes in the list.");
            }
        }
    }


}
