package net.uvavru.amazon;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * The test class for the basic functionality of the  SingleLinkedList data structure.
 * <p/>
 * Created by stepan on 22.8.2014.
 */
public class SingleLinkedListTest extends AbstractSingleLinkedListTest {

    /**
     * Tests a construction test of the data structure by using arrays.
     */
    @Test
    public void constructionTest() {
        Integer[] intArray = new Integer[]{0, 1, 2};
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>(intArray);
        assertAll(list, intArray);

        intArray = new Integer[]{};
        list = new SingleLinkedList<Integer>(intArray);
        assertAll(list, intArray);

        intArray = new Integer[]{0};
        list = new SingleLinkedList<Integer>(intArray);
        assertAll(list, intArray);

    }

    /**
     * Tests a construction test of the data structure by using iterative additions.
     */
    @Test
    public void iterativeConstructionTest() {
        Integer[] intArray = new Integer[]{0, 1, 2};
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
        list.add(0);
        assertEquals(list.size, 1);
        assertEquals((int) list.last.data, 0);
        assertEquals((int) list.first.data, 0);

        list.add(1);
        assertEquals(list.size, 2);
        assertEquals((int) list.last.data, 1);
        assertEquals((int) list.first.next.data, 1);

        list.add(2);
        assertEquals(list.size, 3);
        assertEquals((int) list.last.data, 2);
        assertEquals((int) list.first.next.next.data, 2);

        assertAll(list, intArray);

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test zero length list as an edge case.
     */
    @Test
    public void zeroLength() {
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();

        assertNull(list.getLast());
        assertNull(list.getFirst());

        thrown.expect(IndexOutOfBoundsException.class);
        list.get(0);
    }

    /**
     * Test one length list as an edge case.
     */
    @Test
    public void oneLength() {
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>(new Integer[]{1});

        assertEquals(list.getFirst(), list.getLast());

        assertEquals((int) list.first.data, 1);
        assertEquals((int) list.last.data, 1);
        assertEquals((int) list.get(0), 1);
        assertEquals((int) list.getFirst(), 1);

        thrown.expect(IndexOutOfBoundsException.class);
        list.get(1);
    }

    /**
     * Test two length list as an edge case.
     */
    @Test
    public void twoLength() {
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>(new Integer[]{1, 2});

        assertNotEquals(list.getFirst(), list.getLast());

        assertEquals((int) list.first.data, 1);
        assertEquals((int) list.first.next.data, 2);
        assertEquals((int) list.last.data, 2);
        assertEquals(list.first.next.data, list.last.data);
        assertEquals((int) list.get(0), 1);
        assertEquals((int) list.get(1), 2);
        assertEquals((int) list.getFirst(), 1);
        assertEquals((int) list.getLast(), 2);

        thrown.expect(IndexOutOfBoundsException.class);
        list.get(2);
    }

    /**
     * Complex test of the SingleLinkedList data structure with deep integrity checks.
     */
    @Test
    public void complexPlayTest() {
        // create the array
        Integer[] intArray = new Integer[]{0, 1};
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>(intArray);
        assertAll(list, intArray);

        // change the first item
        list.set(0, 2);
        assertEquals((int) list.first.data, 2);
        assertEquals((int) list.first.next.data, 1);
        assertEquals((int) list.last.data, 1);
        assertEquals(list.first.next.data, list.last.data);
        assertEquals((int) list.get(0), 2);
        assertEquals((int) list.get(1), 1);
        assertEquals((int) list.getFirst(), 2);
        assertEquals((int) list.getLast(), 1);

        // change the second item
        list.set(1, 3);
        assertEquals((int) list.first.data, 2);
        assertEquals((int) list.first.next.data, 3);
        assertEquals((int) list.last.data, 3);
        assertEquals(list.first.next.data, list.last.data);
        assertEquals((int) list.get(0), 2);
        assertEquals((int) list.get(1), 3);

        // add 3rd item
        list.add(4);
        assertEquals((int) list.first.data, 2);
        assertEquals((int) list.first.next.data, 3);
        assertEquals((int) list.first.next.next.data, 4);
        assertEquals((int) list.last.data, 4);
        assertEquals(list.first.next.next.data, list.last.data);
        assertEquals((int) list.get(0), 2);
        assertEquals((int) list.get(1), 3);
        assertEquals((int) list.get(2), 4);
        assertEquals((int) list.getFirst(), 2);
        assertEquals((int) list.getLast(), 4);

        // try to get 4th item with expected exception
        thrown.expect(IndexOutOfBoundsException.class);
        list.get(3);
    }
}
