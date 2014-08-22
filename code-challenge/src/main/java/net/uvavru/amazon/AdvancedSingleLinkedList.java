package net.uvavru.amazon;

/**
 * The single linked list data structure providing
 * <p/>
 * Created by stepan on 22.8.2014.
 *
 * @param <T>
 */
public class AdvancedSingleLinkedList<T> extends SingleLinkedList<T> {


    int size = 0;

    public AdvancedSingleLinkedList() {

    }

    public AdvancedSingleLinkedList(T[] array) {
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


}
