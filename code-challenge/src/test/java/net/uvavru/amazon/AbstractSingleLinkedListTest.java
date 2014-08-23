package net.uvavru.amazon;

import static org.junit.Assert.assertEquals;

/**
 * Common base for the SingleLinkedList test classes.
 * <p/>
 * Created by stepan on 22.8.2014.
 */
public class AbstractSingleLinkedListTest {
    <T extends Number> void assertAll(SingleLinkedList<T> list, T[] array) {

        for (int i = 0; i < array.length; i++) {
            assertEquals(list.get(i), array[i]);

            if (i < array.length - 1) {
                assertEquals(list.node(i).next.data, array[i + 1]);
                assertEquals(list.node(i).next.data, list.node(i + 1).data);
            }
        }

        assertEquals(list.size, array.length);
    }
}
