package net.uvavru.amazon;

/**
 * The advanced single linked list data structure providing basic operations which are required
 * especially for testing.
 * <p/>
 * Created by stepan on 22.8.2014.
 *
 * @param <T> The associated data with this list.
 */
public class AdvancedSingleLinkedList<T> extends SingleLinkedList<T> {

    /**
     * The size of the list
     */
    int size = 0;

    /**
     * Default constructor which creates empty list
     */
    public AdvancedSingleLinkedList() {
    }

    /**
     * Creates a linked list from the provided array.
     *
     * @param array The array to create single linked list data structure from.
     */
    public AdvancedSingleLinkedList(T[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        // the length of the array is 1 at least

        first = new Node<T>(array[0]);
        last = first;
        Node previousNode = first;
        size = 1;

        // use the rest from the array to initialize this list with
        for (; size < array.length; size++) {
            last = new Node<T>(array[size]);
            previousNode.next = last;

            previousNode = last;
        }
    }

    /**
     * Fetches the node at the given index.
     * <p/>
     * If the index is out of bounds, IndexOutOfBoundsException is thrown.
     * <p/>
     * Note that the complexity of this method is O(n)
     *
     * @param index The index where to get the node.
     * @return The Node instance (never null)
     */
    Node<T> node(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " greater or equal to the size of list");
        }

        Node<T> desired = first;
        for (int i = 0; i < index; i++) {
            desired = desired.next;
        }
        return desired;

    }

    /**
     * Gets the value at the beginning of this list.
     *
     * @return Value or null if empty or associated data is null
     */
    public T getFirst() {
        return first != null ? first.data : null;
    }

    /**
     * Gets the value at the end of this list.
     *
     * @return Value or null if empty or associated data is null
     */
    public T getLast() {
        return last != null ? last.data : null;
    }

    /**
     * Gets the value at the specified index.
     * <p/>
     * If index is out of bounds, IndexOutOfBoundsException is thrown.
     *
     * @param index
     * @return
     */
    public T get(int index) {
        Node<T> node = node(index);

        return node != null ? node.data : null;
    }

    /**
     * Sets the value at the specified index.
     * <p/>
     * If index is out of bounds, IndexOutOfBoundsException is thrown.
     *
     * @param index where to set the value
     * @param value value to set
     */
    public void set(int index, T value) {
        Node<T> node = node(index);
        node.data = value;
    }

    /**
     * Adds the value at the end of this list.
     * If this list is empty, it is initialized with one item.
     *
     * @param value the value to add
     */
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

    /**
     * Gets the size of this list.
     *
     * @return the size
     */
    public int size() {
        return size;
    }

    /**
     * Removes the value at the given index.
     * <p/>
     * If index is out of bounds, IndexOutOfBoundsException is thrown.
     *
     * @param index Where to remove the value in this list.
     */
    public T remove(int index) {
        Node<T> removalNode;
        if (index == 0 && size > 0) {

            // just move to the next
            removalNode = first;
            first = first.next;

        } else {

            if (index >= size || index < 0) {
                throw new IndexOutOfBoundsException("Index " + index + " greater or equal to the size of list");
            }

            Node<T> previousNode = node(index - 1);
            removalNode = previousNode.next;
            previousNode.next = previousNode.next.next;

            if (index == size - 1) {
                // removing the last node
                last = previousNode;
            }
        }

        size--;
        return removalNode.data;
    }

}
