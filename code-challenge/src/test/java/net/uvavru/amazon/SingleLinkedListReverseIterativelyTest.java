package net.uvavru.amazon;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * Created by stepan on 22.8.2014.
 */
public class SingleLinkedListReverseIterativelyTest extends AbstractSingleLinkedListTest {

    private static interface ReverseAction<T> {
        void reverse(SingleLinkedList<T> list);
    }

    private static class ReverseIterativelyAction<T> implements ReverseAction<T> {

        @Override
        public void reverse(SingleLinkedList<T> list) {
            list.reverseIteratively();
        }
    }

    private static class ReverseRecursivelyAction<T> implements ReverseAction<T> {

        @Override
        public void reverse(SingleLinkedList<T> list) {
            list.reverseRecursively();
        }
    }


    @Test
    public void simpleReverseIterativelyTest() {
        simpleReverse(new ReverseIterativelyAction());
    }

    @Test
    public void simpleReverseRecursivelyTest() {
        simpleReverse(new ReverseRecursivelyAction());
    }

    private void simpleReverse(ReverseAction action) {
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
        list.reverseIteratively();

        list.add(1);
        action.reverse(list);

        assertEquals((int) list.first.data, 1);
        assertEquals((int) list.last.data, 1);

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
    }


    private void doubleReverseCheck(SingleLinkedList<Integer> list, Integer[] intArray, ReverseAction action) {
        action.reverse(list);
        Collections.reverse(Arrays.asList(intArray));
        assertAll(list, intArray);

        // reverse back
        action.reverse(list);
        Collections.reverse(Arrays.asList(intArray));
        assertAll(list, intArray);
    }

    @Test
     public void doubleReverseIterativelyTest() {
        doubleReverse(new ReverseIterativelyAction());
    }

    @Test
    public void doubleReverseRecursivelyTest() {
        doubleReverse(new ReverseRecursivelyAction());
    }

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
