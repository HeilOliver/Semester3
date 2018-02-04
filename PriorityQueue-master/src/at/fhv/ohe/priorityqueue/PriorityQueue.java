package at.fhv.ohe.priorityqueue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Describes a Priority Queue that be sorted throw a given Comparator
 *
 * Created by Oliver H on 05.05.2017.
 */
public class PriorityQueue <T> {
    private static final int DEFAULT_START_CAPACITY = 10;

    private Comparator<T> _comparator;
    private int _capacity = 10;
    private int _size = 0;

    private Object[] _items = new Object[_capacity];
    
    /**
     * Creates an PriorityQueue with given {@code Comparator<T>}.
     *
     * @param comparator - The comparator for the priority
     */
    public PriorityQueue(Comparator<T> comparator) {
        this(comparator, DEFAULT_START_CAPACITY);
    }

    /**
     * Creates an PriorityQueue with given {@code Comparator<T>} and capacity.
     *
     * @param comparator - The comparator for the priority
     * @param capacity - The given start capacity
     */
    public PriorityQueue(Comparator<T> comparator, int capacity) {
        _comparator = comparator;
        _capacity = capacity;
    }

    /**
     * Returns the left child index of the given parent index.
     *
     * @param parentIndex - The given index
     * @return {@code int} - The left child index
     */
    private int getLeftChildIndex(int parentIndex){
        return 2 * parentIndex + 1;
    }

    /**
     * Returns the right child index of the given parent index.
     *
     * @param parentIndex - The given index
     * @return {@code int} - The right child index
     */
    private int getRightChildIndex(int parentIndex){
        return 2 * parentIndex + 2;
    }

    /**
     * Returns the parent index of the given child index.
     *
     * @param childIndex - The given index
     * @return {@code int} - The parents index
     */
    private int getParentIndex(int childIndex){
        return (childIndex - 1) / 2;
    }

    /**
     * Returns if the given index have a left child in the heap.
     *
     * @param index - The given index
     * @return {@code true} - If the given index has a left child
     */
    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < _size;
    }

    /**
     * Returns if the given index have a right child in the heap.
     *
     * @param index - The given index
     * @return {@code true} - If the given index has a right child
     */
    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < _size;
    }

    /**
     * Returns if the given index have a parent in the heap.
     *
     * @param index - The given index
     * @return {@code true} - If the given index has a parent
     */
    private boolean hasParants(int index) {
        return getParentIndex(index) >= 0;
    }

    /**
     * Return the left child item of the given index.
     *
     * @param index - The index
     * @return the left child item
     */
    @SuppressWarnings("unchecked")
    private T leftChild(int index) {
        return (T) _items[getLeftChildIndex(index)];
    }

    /**
     * Return the right child item of the given index.
     *
     * @param index - The index
     * @return the right child item
     */
    @SuppressWarnings("unchecked")
    private T rightChild(int index) {
        return (T) _items[getRightChildIndex(index)];
    }

    /**
     * Return the parent item of the given index.
     *
     * @param index - The index
     * @return the parent item
     */
    @SuppressWarnings("unchecked")
    private T parent(int index) {
        return (T) _items[getParentIndex(index)];
    }

    /**
     * Swap to items in the heap.
     *
     * @param indexOne - The first index of the heap
     * @param indexTwo - The second index of the heap
     */
    private void swap(int indexOne, int indexTwo) {
        Object temp = _items[indexOne];
        _items[indexOne] = _items[indexTwo];
        _items[indexTwo] = temp;
    }

    /**
     * Check if the capacity of the array needs to be increase
     *
     */
    private void ensureCapacity() {
        if (_size == _capacity) {
            _items = Arrays.copyOf(_items,_capacity* 2);
            _capacity *= 2;
        }
    }

    /**
     * Sort the heap in upwards direction. Restores the integrity of the heap.
     *
     */
    @SuppressWarnings("unchecked")
    private void heapifyUp() {
        int index = _size - 1;

        while (hasParants(index) && _comparator.compare(parent(index), (T) _items[index]) < 0) {
            swap(getParentIndex(index),index);
            index = getParentIndex(index);
        }
    }

    /**
     * Sort the heap in downwards direction. Restores the integrity of the heap.
     *
     */
    @SuppressWarnings("unchecked")
    private void heapifyDown() {
        int index = 0;

        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && _comparator.compare(leftChild(index), rightChild(index)) < 0 ){
                smallerChildIndex = getRightChildIndex(index);
            }

            if (_comparator.compare((T) _items[index], (T) _items[smallerChildIndex]) > 0) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }

    /**
     * Add a new element to the PriorityQueue.
     *
     * @param item - The element that should be added
     */
    public void add(T item) {
        ensureCapacity();
        _items[_size++] = item;
        heapifyUp();
    }

    /**
     * Return the next element with the highest priority and delete it from the Priority Queue
     *
     * @return - The element with the highest priority
     * @throws NoSuchElementException if the PriorityQueue has no more elements
     */
    @SuppressWarnings("unchecked")
    public T next() {
        if (_size == 0) {
            throw new NoSuchElementException();
        }
        Object item = _items[0];
        _items[0] = _items[_size - 1];
        _size --;
        heapifyDown();
        return (T) item;
    }

    /**
     * Returns {@code true} if the PriorityQueue has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the PriorityQueue has more elements
     */
    public boolean hasNext() {
        return _size > 0;
    }

}
