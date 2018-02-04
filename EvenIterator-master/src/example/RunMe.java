package example;

import at.fhv.ohe.iterator.EvenIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A little Example for the Even Iterator
 *
 * Created by Oliver H on 06.05.2017.
 */
public class RunMe {


    public static void main(String[] args) {
        // First of all you need a List
        //LinkedList<Integer> list = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();


        // Now add some Items to the list. The Even Iterator only works with
        // Integer values so we fill it with them
        list.add(0); //
        list.add(1);
        list.add(2); //
        list.add(3);
        list.add(4); //
        list.add(5);
        list.add(8);

        // Now create a new Even Iterator
        Iterator<Integer> evenIterator = new EvenIterator(list);

        // You see - the iterator only print even numbers. Odd numbers will be skipped.
        evenIterator.forEachRemaining((System.out::println));
    }
}
