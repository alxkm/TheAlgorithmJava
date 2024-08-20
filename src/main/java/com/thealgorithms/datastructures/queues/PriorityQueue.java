package com.thealgorithms.datastructures.queues;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * This class implements a PriorityQueue.
 *
 * <p>
 * A priority queue adds elements into positions based on their priority. So the
 * most important elements are placed at the front/on the top. In this example I
 * give numbers that are bigger, a higher priority. Queues in theory have no
 * fixed size but when using an array implementation it does.
 * <p>
 * Additional contributions made by: PuneetTri(https://github.com/PuneetTri)
 */
class PriorityQueue<T> {

    /**
     * The max size of the queue
     */
    private int maxSize;

    /**
     * The array for the queue
     */
    private T[] queueArray;

    /**
     * How many items are in the queue
     */
    private int nItems;

    /**
     * The comparator for priority
     */
    private Comparator<? super T> comparator;

    /**
     * Default Constructor
     */
    @SuppressWarnings("unchecked")
    public PriorityQueue() {
        /* If capacity is not defined, default size of 11 would be used
         * capacity=max+1 because we can't access 0th element of PQ, and to
         * accommodate (max)th elements we need capacity to be max+1.
         * Parent is at position k, child at position (k*2, k*2+1). If we
         * use position 0 in our queue, its child would be at:
         * (0*2, 0*2+1) -> (0,0). This is why we start at position 1.
         */
        int size = 11; // Default value of 11
        maxSize = size + 1;
        queueArray = (T[]) new Object[maxSize];
        nItems = 0;
        this.comparator = null;
    }

    /**
     * Parameterized Constructor
     *
     * @param size Size of the queue
     */
    @SuppressWarnings("unchecked")
    public PriorityQueue(int size) {
        maxSize = size + 1;
        queueArray = (T[]) new Object[maxSize];
        nItems = 0;
        this.comparator = null;
    }

    /**
     * Parameterized Constructor with Comparator
     *
     * @param size       Size of the queue
     * @param comparator Comparator for custom priority
     */
    @SuppressWarnings("unchecked")
    public PriorityQueue(int size, Comparator<? super T> comparator) {
        maxSize = size + 1;
        queueArray = (T[]) new Object[maxSize];
        nItems = 0;
        this.comparator = comparator;
    }

    /**
     * Helper function for the max-heap implementation of PQ
     * Function would help promote child node to their correct position
     *
     * @param pos Position of newly added element at the bottom
     */
    private void swim(int pos) {
        // Check if parent is smaller than child node
        while (pos > 1 && compare(queueArray[pos / 2], queueArray[pos]) < 0) {
            // In such case swap value of child with parent
            swap(pos, pos / 2);
            pos = pos / 2; // Jump to position of parent node
        }
        // Promotion of child node will go on until it becomes smaller than the parent
    }

    /**
     * Helper function for the max-heap implementation of PQ
     * Function would help demote parent node to their correct position
     *
     * @param pos Position of element at the top
     */
    private void sink(int pos) {
        // Check if node's position is that of parent node
        while (2 * pos <= nItems) {
            int current = 2 * pos; // Jump to the position of child node
            // Compare both the children for the greater one
            if (current < nItems && compare(queueArray[current], queueArray[current + 1]) < 0) {
                current++;
            }
            // If the parent node is greater, sink operation is complete. Break the loop
            if (compare(queueArray[pos], queueArray[current]) >= 0) {
                break;
            }

            // If not exchange the value of parent with child
            swap(pos, current);
            pos = current; // Exchange parent position to child position in the array
        }
    }

    /**
     * Inserts an element in its appropriate place
     *
     * @param value Value to be inserted
     */
    public void insert(T value) {
        // Print overflow message if the capacity is full
        if (isFull()) {
            throw new RuntimeException("Queue is full");
        } else {
            queueArray[++nItems] = value;
            swim(nItems); // Swim up the element to its correct position
        }
    }

    /**
     * Dequeue the element with the max priority from PQ
     *
     * @return The element removed
     */
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        } else {
            T max = queueArray[1]; // By definition of our max-heap, value at queueArray[1] pos is the greatest

            // Swap max and last element
            swap(1, nItems);
            queueArray[nItems--] = null; // Nullify the last element from the priority queue
            sink(1); // Sink the element in order

            return max;
        }
    }

    /**
     * Checks what's at the front of the queue
     *
     * @return element at the front of the queue
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queueArray[1];
    }

    /**
     * Returns true if the queue is empty
     *
     * @return true if the queue is empty
     */
    public boolean isEmpty() {
        return nItems == 0;
    }

    /**
     * Returns true if the queue is full
     *
     * @return true if the queue is full
     */
    public boolean isFull() {
        return nItems == maxSize - 1;
    }

    /**
     * Returns the number of elements in the queue
     *
     * @return number of elements in the queue
     */
    public int getSize() {
        return nItems;
    }

    /**
     * Swaps two elements in the queue array
     *
     * @param i First position
     * @param j Second position
     */
    private void swap(int i, int j) {
        T temp = queueArray[i];
        queueArray[i] = queueArray[j];
        queueArray[j] = temp;
    }

    /**
     * Compares two elements using the comparator if available, otherwise using natural ordering
     *
     * @param o1 First element
     * @param o2 Second element
     * @return Comparison result
     */
    @SuppressWarnings("unchecked")
    private int compare(T o1, T o2) {
        if (comparator == null) {
            return ((Comparable<? super T>) o1).compareTo(o2);
        } else {
            return comparator.compare(o1, o2);
        }
    }
}
