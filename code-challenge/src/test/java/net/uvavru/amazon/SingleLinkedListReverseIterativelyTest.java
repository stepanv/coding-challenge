package net.uvavru.amazon;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by stepan on 22.8.2014.
 */
public class SingleLinkedListReverseIterativelyTest extends AbstractSingleLinkedListTest {
    @Test
    public void simpleReverseTest() {
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
        list.reverseIteratively();

        list.add(1);
        list.reverseIteratively();

        assertEquals((int) list.first.data, 1);
        assertEquals((int) list.last.data, 1);

        list.add(2);
        list.reverseIteratively();

        assertEquals((int) list.first.data, 2);
        assertEquals((int) list.first.next.data, 1);
        assertEquals((int) list.last.data, 1);
        assertEquals(list.first.next.data, list.last.data);
        assertEquals((int) list.get(0), 2);
        assertEquals((int) list.get(1), 1);
        assertEquals((int) list.getFirst(), 2);
        assertEquals((int) list.getLast(), 1);

        // reverse back
        list.reverseIteratively();

        list.add(3);
        list.reverseIteratively();

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

    private void doubleReverse(SingleLinkedList<Integer> list, Integer[] intArray) {
        list.reverseIteratively();
        Collections.reverse(Arrays.asList(intArray));
        assertAll(list, intArray);

        // reverse back
        list.reverseIteratively();
        Collections.reverse(Arrays.asList(intArray));
        assertAll(list, intArray);
    }

    @Test
    public void doubleReverseTest() {

        Integer[] intArray = new Integer[]{0, 1, 4, 656, 213, 11, -34, 43, -1};
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>(intArray);

        doubleReverse(list, intArray);

        intArray = new Integer[]{};
        list = new SingleLinkedList<Integer>(intArray);

        doubleReverse(list, intArray);

        intArray = new Integer[]{1};
        list = new SingleLinkedList<Integer>(intArray);

        doubleReverse(list, intArray);
        intArray = new Integer[]{1, null, 3};
        list = new SingleLinkedList<Integer>(intArray);

        doubleReverse(list, intArray);
    }


}
