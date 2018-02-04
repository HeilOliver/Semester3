package at.fhv.ohe.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Describes a iterator that only returns even numbers
 *
 * Created by Oliver H on 06.05.2017.
 */
public class EvenIterator implements Iterator<Integer>{
    private Iterator<Integer> _iterator;
    private Integer _nextItem;

    /**
     * Creates an even iterator with a given list
     *
     * @param list - The list for the iterator
     */
    public EvenIterator(List<Integer> list) {
        _iterator = list.iterator();
        getNext();
    }

    /**
     * search for the next item that matches the condition
     */
    private void getNext() {
        Integer temp;
        _nextItem = null;

        while (_iterator.hasNext()) {
            temp = _iterator.next();
            if (temp % 2 == 0) {
                _nextItem = temp;
                break;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return _nextItem != null;
    }

    @Override
    public Integer next() {
        if (_nextItem == null) {
            throw new NoSuchElementException();
        }
        Integer temp = _nextItem;
        getNext();
        return temp;
    }
}
