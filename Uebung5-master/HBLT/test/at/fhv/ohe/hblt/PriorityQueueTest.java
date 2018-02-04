package at.fhv.ohe.hblt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

    /**
     * Test for the Priority Queue
     *
     * Created by Oliver H on 05.05.2017.
     */
class PriorityQueueTest {


        @Test
        void add() {
            PriorityQueue<Integer> queue = new PriorityQueue<>(Integer::compareTo);

            queue.enque(3);
            queue.enque(4);
            queue.enque(2);
            queue.enque(0);
            queue.enque(1);

            int check = 0;
            while (queue.hasNext()) {
                assertTrue(queue.deque() == check++);
            }

        }




}