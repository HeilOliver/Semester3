package at.fhv.ohe.example;

import at.fhv.ohe.priorityqueue.PriorityQueue;

/**
 * A little example for the PriorityQueue
 * <p>
 * Created by Oliver H on 05.05.2017.
 */
public class Runme {

    public static void main(String[] args) {
        // Creates an PriorityQueue with default capacity
        PriorityQueue<Integer> _test = new PriorityQueue<>((o1, o2) -> o1 < o2 ? 1 : o1.equals(o2) ? 0 : -1);
        // Or this with a given capacity
        PriorityQueue<String> _test2 = new PriorityQueue<>(String::compareToIgnoreCase, 10);

        // Add some items
        _test.add(5);
        _test.add(17);
        _test.add(20);
        _test.add(15);
        _test.add(8);
        _test.add(8);
        _test.add(1);
        _test.add(99);
        _test.add(67);
        _test.add(9);
        _test.add(2);

        System.out.println("Test 1 with Integers:");
        // return all items with the given priority
        while (_test.hasNext()) {
            System.out.println(_test.next());
        }

        // lets try with strings
        _test2.add("alexa");
        _test2.add("siri");
        _test2.add("cortana");
        _test2.add("okGoogle");
        _test2.add("javis");
        _test2.add("bixby");

        System.out.println("\n\nTest 2 with Strings:");
        // return all items with the given priority
        while (_test2.hasNext()) {
            System.out.println(_test2.next());
        }
    }

}
