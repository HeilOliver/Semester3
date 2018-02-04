package at.fhv.ohe.iterator;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for EvenIterator
 *
 * Created by Oliver H on 06.05.2017.
 */
class EvenIteratorTest {

    @Test
    void next() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(0); //
        list.add(1);
        list.add(2); //
        list.add(3);
        list.add(4); //
        list.add(5);
        list.add(6); //

        Iterator<Integer> evenIterator = new EvenIterator(list);

        int count = 0;
        while (evenIterator.hasNext()) {
            assertTrue(evenIterator.next() == count);
            count += 2;
        }
        assertFalse(evenIterator.hasNext());
    }

    @Test
    void exceptionTest() {
        LinkedList<Integer> list = new LinkedList<>();

        Iterator<Integer> evenIterator = new EvenIterator(list);

        try {
            evenIterator.next();
            fail("1");
        } catch (NoSuchElementException e) {
        }

        list.add(0);
        list.add(2);
        list.add(4);

        try {
            evenIterator.next();
            fail("2");
        } catch (Exception e) {
        }
    }

}