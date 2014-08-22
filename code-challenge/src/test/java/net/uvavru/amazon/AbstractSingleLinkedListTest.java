package net.uvavru.amazon;

import static org.junit.Assert.assertEquals;

/**
 * Created by stepan on 22.8.2014.
 */
public class AbstractSingleLinkedListTest {
    <T extends Number> void assertAll(SingleLinkedList<T> list, T[] array) {
        for (int i = 0; i < array.length; i++) {
            assertEquals(list.get(i), array[i]);
        }

        assertEquals(list.size, array.length);
    }
}
