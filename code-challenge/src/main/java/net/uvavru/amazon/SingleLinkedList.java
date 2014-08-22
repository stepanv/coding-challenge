package net.uvavru.amazon;

/**
 * Created by stepan on 22.8.2014.
 */
public class SingleLinkedList<T> {

    Node<T> first;
    Node<T> last;

    int size = 0;

    public SingleLinkedList() {

    }

    public SingleLinkedList(T[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        first = new Node<T>(array[0]);
        last = first;
        Node previousNode = first;
        size = 1;

        for (; size < array.length; size++) {
            last = new Node<T>(array[size]);
            previousNode.next = last;

            previousNode = last;
        }
    }

    Node<T> node(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " greater or equal to the size of list");
        }

        Node<T> desired = first;
        for (int i = 0; i < index; i++) {
            desired = desired.next;
        }
        return desired;

    }

    public T getFirst() {
        return first != null ? first.data : null;
    }

    public T get(int index) {
        Node<T> node = node(index);

        return node != null ? node.data : null;
    }

    public T getLast() {
        return last != null ? last.data : null;
    }

    public void setFirst(T value) {
        first = new Node<T>(value);
    }

    public void set(int index, T value) {
        Node<T> node = node(index);
        node.data = value;
    }

    public void add(T value) {
        Node<T> node = new Node<T>(value);

        if (last == null) {
            assert first == null;

            first = node;
            last = first;
        } else {
            assert first != null;

            last.next = node;
            last = node;
        }

        size++;
    }


    static class Node<T> {
        Node<T> next;
        T data;

        public Node(T data) {
            this.data = data;
        }

    }

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

    private void swapFirstLast() {
        Node<T> swap = last;
        last = first;
        first = swap;
    }

    public void reverseRecursively() {
        if (first == null || first == last) {
            return;
        }

        swapFirstLast();

        reverse(last);

        // fix the last
        last.next = null;
    }

    private void reverse(Node<T> node) {
        if (node.next.next != null) {
            reverse(node.next);
        }

        // next of the next one should point at current node
        node.next.next = node;
    }

}
