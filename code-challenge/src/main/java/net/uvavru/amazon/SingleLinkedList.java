package net.uvavru.amazon;

/**
 * The core Single Linked List data structure with separated implementation of functionality
 * specified in the Amazon's assignment. The purpose of such extraction is to provide an easy
 * orientation in the core functionality.
 * <p/>
 * Unless sub-classed, this class does not provide a way to modify the list (except for reverse
 * actions).
 * <p/>
 * This data structure does not intentionally implement any of common <code>java.util</code>
 * collection interfaces because it was not simply an assignment.
 * <p/>
 * Created by stepan on 22.8.2014.
 *
 * @param <T> The type of content to use with this list data structure.
 */
public class SingleLinkedList<T> implements AmazonAssignment {

    /**
     * The first Node of the linked list
     */
    Node<T> first;
    /**
     * The last Node of the linked list declared for convenience reasons
     */
    Node<T> last;

    @Override
    public void reverseIteratively() {
        if (first == null || first.next == null) {
            return;
        }

        // there are at least 2 nodes

        Node previousNode = first;
        Node currentNode = first.next;
        previousNode.next = null;
        last = first;

        while (currentNode != null) {
            Node<T> nextNode = currentNode.next;
            currentNode.next = previousNode;

            previousNode = currentNode;
            currentNode = nextNode;
        }

        first = previousNode;
    }

    /**
     * A helper method to swap the 'pointers' to the first and the last node.
     */
    private void swapFirstLast() {
        Node<T> swap = last;
        last = first;
        first = swap;
    }

    @Override
    public void reverseRecursively() {
        if (first == null || first == last) {
            return;
        }

        swapFirstLast();

        reverse(last);

        // fix the last
        last.next = null;
    }

    /**
     * The recursion method itself.
     * <p/>
     * The idea of this recursion is at first to iterate by using recursion to the last pair of Nodes
     * and then to make the next of the current one point to the current one.
     *
     * @param node The node to which the next node should point to.
     */
    private void reverse(Node<T> node) {
        if (node.next.next != null) {
            reverse(node.next);
        }

        // next of the next one should point at current node
        node.next.next = node;
    }

    /**
     * Package local (and therefore testable) class that represents a single node in the
     * Single Linked List data structure.
     *
     * @param <T> The associated data with this node (as well as with the whole list).
     */
    static class Node<T> {
        /**
         * The next node, can be null
         */
        Node<T> next;
        /**
         * Associated data
         */
        T data;

        /**
         * Creates the node.
         *
         * @param data The associated data.
         */
        public Node(T data) {
            this.data = data;
        }

    }
}