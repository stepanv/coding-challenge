package net.uvavru.amazon;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test class for the recursive actions of the SingleLinkedList data structure.
 * <p/>
 * Created by stepan on 22.8.2014.
 */
public class SingleLinkedListReverseTest extends AbstractSingleLinkedListTest {

    /**
     * An abstraction for reverse action so that the test code is not unnecessarily duplicated.
     *
     * @param <T>
     */
    private static interface ReverseAction<T> {
        void reverse(SingleLinkedList<T> list);
    }

    /**
     * The iterative action class for the SingleLinkedLists.
     *
     * @param <T> The type of data in the list
     */
    private static class ReverseIterativelyAction<T> implements ReverseAction<T> {

        @Override
        public void reverse(SingleLinkedList<T> list) {
            list.reverseIteratively();
        }
    }

    /**
     * The recursive action class for the SingleLinkedLists.
     *
     * @param <T> The type of data in the list
     */
    private static class ReverseRecursivelyAction<T> implements ReverseAction<T> {

        @Override
        public void reverse(SingleLinkedList<T> list) {
            list.reverseRecursively();
        }
    }

    /**
     * Runs a deep integrity test while reversing a list iteratively.
     */
    @Test
    public void simpleReverseIterativelyTest() {
        simpleReverse(new ReverseIterativelyAction());
    }

    /**
     * Runs a deep integrity test while reversing a list recursively.
     */
    @Test
    public void simpleReverseRecursivelyTest() {
        simpleReverse(new ReverseRecursivelyAction());
    }

    /**
     * Reverses the list and does a deep integrity test.
     *
     * @param action The reverse action
     */
    private void simpleReverse(ReverseAction action) {
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
        list.reverseIteratively();

        list.add(1);
        action.reverse(list);

        assertEquals((int) list.first.data, 1);
        assertEquals((int) list.last.data, 1);
        assertNull(list.last.next);

        list.add(2);
        action.reverse(list);

        assertEquals((int) list.first.data, 2);
        assertEquals((int) list.first.next.data, 1);
        assertEquals((int) list.last.data, 1);
        assertEquals(list.first.next.data, list.last.data);
        assertEquals((int) list.get(0), 2);
        assertEquals((int) list.get(1), 1);
        assertEquals((int) list.getFirst(), 2);
        assertEquals((int) list.getLast(), 1);
        assertNull(list.last.next);

        // reverse back
        action.reverse(list);

        list.add(3);
        action.reverse(list);

        assertEquals((int) list.first.data, 3);
        assertEquals((int) list.first.next.data, 2);
        assertEquals((int) list.first.next.next.data, 1);
        assertEquals((int) list.last.data, 1);
        assertEquals(list.first.next.next.data, list.last.data);
        assertEquals((int) list.get(0), 3);
        assertEquals((int) list.get(1), 2);
        assertEquals((int) list.get(2), 1);
        assertEquals((int) list.getFirst(), 3);
        assertEquals((int) list.getLast(), 1);
        assertNull(list.last.next);

        // 4 and more items should be WLOG covered by the 3 items test
    }

    /**
     * Checks the list against the array,
     * reverses both and check them and then reverses them back.
     *
     * @param list     List to check
     * @param intArray An array that corresponds with the list
     * @param action   The reverse action
     */
    private void doubleReverseCheck(SingleLinkedList<Integer> list, Integer[] intArray, ReverseAction action) {
        assertAll(list, intArray);

        action.reverse(list);
        Collections.reverse(Arrays.asList(intArray));
        assertAll(list, intArray);

        // reverse back
        action.reverse(list);
        Collections.reverse(Arrays.asList(intArray));
        assertAll(list, intArray);
    }

    /**
     * Tests a iterative reverse of the list by using various arrays
     */
    @Test
    public void doubleReverseIterativelyTest() {
        doubleReverse(new ReverseIterativelyAction());
    }

    /**
     * Tests a recursive reverse of the list by using various arrays
     */
    @Test
    public void doubleReverseRecursivelyTest() {
        doubleReverse(new ReverseRecursivelyAction());
    }

    /**
     * The common test for double reverse
     *
     * @param action How to reverse the list
     */
    private void doubleReverse(ReverseAction action) {

        Integer[] intArray = new Integer[]{0, 1, 4, 656, 213, 11, -34, 43, -1};
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>(intArray);

        doubleReverseCheck(list, intArray, action);

        intArray = new Integer[]{};
        list = new SingleLinkedList<Integer>(intArray);

        doubleReverseCheck(list, intArray, action);

        intArray = new Integer[]{1};
        list = new SingleLinkedList<Integer>(intArray);

        doubleReverseCheck(list, intArray, action);
        intArray = new Integer[]{1, null, 3};
        list = new SingleLinkedList<Integer>(intArray);

        doubleReverseCheck(list, intArray, action);
    }

}
