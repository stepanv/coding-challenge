package net.uvavru.amazon;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The advanced single linked list data structure providing basic operations which are required
 * especially for testing.
 * <p/>
 * Created by stepan on 22.8.2014.
 *
 * @param <T> The associated data with this list.
 */
public class AdvancedSingleLinkedList<T> extends SingleLinkedList<T> implements Collection<T> {

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

    @Override
    public boolean add(T value) {
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
        return true;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        // O(n*n) complexity .. lets pretend we do not care

        if (c == null) {
            throw new NullPointerException("Argument cannot be null!");
        }

        for (Object o : c) {
            if (contains(o)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Argument cannot be null!");
        }
        if (c == null || c.size() == 0) {
            return false;
        }
        for (T item : c) {
            this.add(item);

        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // O(n*n) complexity again .. might be better with some heuristics
        boolean modified = false;
        for (Object o : c) {
            modified = remove(o);
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Argument cannot be null!");
        }

        Iterator<T> iterator = iterator();
        boolean modified = false;

        while (iterator.hasNext()) {
            T item = iterator.next();
            if (c.contains(item)) {
                continue;
            }
            iterator.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    /**
     * Gets the size of this list.
     *
     * @return the size
     */
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            for (T item : this) {
                if (item == null) {
                    return true;
                }
            }
        } else {
            for (T item : this) {
                if (o.equals(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new SingleLinkedListIterator<T>(this);
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        for (T item : this) {
            array[index++] = item;
        }
        return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> E[] toArray(E[] array) {
        if (array.length < size) {
            array = (E[]) java.lang.reflect.Array.newInstance(
                    array.getClass().getComponentType(), size);
        }
        Object[] result = array;
        int index = 0;
        for (T item : this) {
            result[index++] = item;
        }

        if (array.length > size) {
            array[size] = null;
        }

        return array;
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

    @Override
    public boolean remove(Object o) {
        boolean modified = false;
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            T item = iterator.next();
            if ((o == null && item == null) || (o != null && o.equals(item))) {
                iterator.remove();
                modified = true;
            }
        }

        return modified;
    }

    static class SingleLinkedListIterator<T> implements Iterator<T> {
        private final AdvancedSingleLinkedList<T> list;
        private Node<T> previousNode = null;
        private Node<T> currentNode = null;

        SingleLinkedListIterator(AdvancedSingleLinkedList<T> list) {
            this.list = list;
            previousNode = null;
        }

        @Override
        public boolean hasNext() {
            if (list.first == null) {
                return false;
            }
            if (previousNode == null) {
                if (currentNode == null) {
                    return list.first != null;
                } else {
                    return currentNode.next != null;
                }
            }

            if (previousNode.next == null) {
                return false;
            }

            return previousNode.next.next != null;
        }

        @Override
        public T next() {
            if (list.first == null) {
                throw new NoSuchElementException("Empty collection");
            }
            if (previousNode == null) {
                if (currentNode == null) {
                    // at the beginning
                    // do not move the previous
                    currentNode = list.first;
                    return currentNode.data;
                } else {
                    previousNode = list.first;
                    if (previousNode.next == null) {
                        throw new NoSuchElementException("Already at the end of the collection!");
                    }
                    return previousNode.next.data;
                }
            }

            // move to another one
            if (previousNode.next == null) {
                throw new IllegalStateException("Argh!");
            }
            if (previousNode.next.next == null) {

                throw new IllegalStateException("Already at the end of the collection!");
            }
            previousNode = previousNode.next;

            return previousNode.next.data;
        }

        @Override
        public void remove() {
            if (list.first == null) {
                // empty list
                return;
            }
            if (previousNode == null) {
                // at the beginning

                // do not modify this iterator

                // modify the list ds
                if (list.first == list.last) {
                    // removing the last one
                    list.last = null;
                }
                list.first = list.first.next;
                list.size--;

                return;
            }
            Node<T> removalNode = previousNode.next;
            assert list.first != removalNode;

            if (removalNode == null) {
                throw new NoSuchElementException("Already at the end of the collection!");
            }

            if (list.last == removalNode) {
                // removing the last node
                list.last = previousNode;
            }

            // remove it
            previousNode.next = previousNode.next.next;

            // modify the ds
            list.size--;

            // do not modify the iterator
        }
    }

    /**
     * Inspired in AbstractCollection
     *
     * @return Friendly string
     */
    @Override
    public String toString() {
        Iterator<T> iterator = iterator();
        if (!iterator.hasNext()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (; ; ) {
            T item = iterator.next();
            sb.append(item == this ? "(this Collection)" : item);
            if (!iterator.hasNext()) {
                return sb.append(']').toString();
            }
            sb.append(',').append(' ');
        }
    }
}
