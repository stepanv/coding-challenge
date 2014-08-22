package net.uvavru.amazon;


import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by stepan on 22.8.2014.
 */
public class SingleLinkedListTest {

    private <T extends Number> void assertAll(SingleLinkedList<T> list, T[] array) {
        for (T item : array) {
            assertEquals(list.get(item.intValue()).toString(), item.toString());
        }

        assertEquals(list.size, array.length);
    }

    @Test
    public void constructionTest() {
        Integer[] intArray = new Integer[] {0, 1, 2};
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>(intArray);
        assertAll(list, intArray);

        intArray  = new Integer[] {};
        list = new SingleLinkedList<Integer>(intArray);
        assertAll(list, intArray);

        intArray  = new Integer[] {0};
        list = new SingleLinkedList<Integer>(intArray);
        assertAll(list, intArray);

    }

    @Test
    public void iterativeConstructionTest() {
        Integer[] intArray = new Integer[] {0, 1, 2};
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
        list.add(0);
        assertEquals(list.size, 1);
        assertEquals((int)list.last.data, 0);
        assertEquals((int) list.first.data, 0);

        list.add(1);
        assertEquals(list.size, 2);
        assertEquals((int)list.last.data, 1);
        assertEquals((int) list.first.next.data, 1);

        list.add(2);
        assertEquals(list.size, 3);
        assertEquals((int)list.last.data, 2);
        assertEquals((int) list.first.next.next.data, 2);

        assertAll(list, intArray);

    }

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void zeroLength() {
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();

        assertNull(list.getLast());
        assertNull(list.getFirst());

        thrown.expect(IndexOutOfBoundsException.class);
        list.get(0);
    }

    @Test
    public void oneLength() {
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>(new Integer[] {1});

        assertEquals(list.getFirst(), list.getLast());

        assertEquals((int)list.first.data, 1);
        assertEquals((int)list.last.data, 1);
        assertEquals((int)list.get(0), 1);
        assertEquals((int) list.getFirst(), 1);

        thrown.expect(IndexOutOfBoundsException.class);
        list.get(1);
    }

    @Test
    public void complexPlayTest() {
        Integer[] intArray = new Integer[] {0, 1};
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>(intArray);
        assertAll(list, intArray);

        list.set(0, 2);
        assertEquals((int)list.first.data, 2);
        assertEquals((int)list.first.next.data, 1);
        assertEquals((int)list.last.data, 1);
        assertEquals(list.first.next.data, list.last.data);
        assertEquals((int)list.get(0), 2);
        assertEquals((int)list.get(1), 1);
        assertEquals((int)list.getFirst(), 2);
        assertEquals((int)list.getLast(), 1);

        list.set(1, 3);
        assertEquals((int)list.first.data, 2);
        assertEquals((int)list.first.next.data, 3);
        assertEquals((int) list.last.data, 3);
        assertEquals(list.first.next.data, list.last.data);
        assertEquals((int)list.get(0), 2);
        assertEquals((int)list.get(1), 3);

        list.add(4);
        assertEquals((int)list.first.data, 2);
        assertEquals((int)list.first.next.data, 3);
        assertEquals((int)list.first.next.next.data, 4);
        assertEquals((int) list.last.data, 4);
        assertEquals(list.first.next.next.data, list.last.data);
        assertEquals((int)list.get(0), 2);
        assertEquals((int)list.get(1), 3);
        assertEquals((int)list.get(2), 4);
        assertEquals((int)list.getFirst(), 2);
        assertEquals((int)list.getLast(), 4);

        thrown.expect(IndexOutOfBoundsException.class);
        list.get(3);
    }
}
