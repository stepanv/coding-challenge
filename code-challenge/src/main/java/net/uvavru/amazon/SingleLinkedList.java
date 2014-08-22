package net.uvavru.amazon;

/**
 * Created by stepan on 22.8.2014.
 */
public class SingleLinkedList<T> {

    private Node<T> first;
    private int size = 0;

    public SingleLinkedList() {

    }
    public SingleLinkedList(T[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        first = new Node<T>(array[0]);
        Node previousNode = first;
        size = 1;

        for (; size < array.length ; size++) {
            Node node = new Node<T>(array[size]);
            previousNode.setNext(node);

            previousNode = node;
        }
    }

}
