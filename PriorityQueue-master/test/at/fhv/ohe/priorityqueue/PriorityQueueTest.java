package at.fhv.ohe.priorityqueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for the Priority Queue
 *
 * Created by Oliver H on 05.05.2017.
 */
class PriorityQueueTest {
    private PriorityQueue<Integer> _testQueue;

    @BeforeEach
    void beforeEach() {
        _testQueue = new PriorityQueue<>((o1, o2) -> o1 < o2 ? 1 : o1.equals(o2) ? 0 : -1);
    }

    @Test
    void enqueue_deque_empty_Test() {
        _testQueue.add(3);
        _testQueue.add(4);
        _testQueue.add(2);
        _testQueue.add(0);
        _testQueue.add(1);

        int check = 0;
        while (_testQueue.hasNext()) {
            assertTrue(_testQueue.next() == check++);
        }

    }
}