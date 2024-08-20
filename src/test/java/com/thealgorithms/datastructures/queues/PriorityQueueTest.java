package com.thealgorithms.datastructures.queues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.NoSuchElementException;

class PriorityQueueTest {

    @Test
    void testInsertAndRemove() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(5);
        pq.insert(3);
        pq.insert(5);
        pq.insert(1);

        assertEquals(5, pq.remove());
        assertEquals(3, pq.remove());
        assertEquals(1, pq.remove());
    }

    @Test
    void testInsertWithCustomComparator() {
        PriorityQueue<String> pq = new PriorityQueue<>(5, Comparator.reverseOrder());
        pq.insert("apple");
        pq.insert("banana");
        pq.insert("cherry");

        assertEquals("apple", pq.remove());
        assertEquals("banana", pq.remove());
        assertEquals("cherry", pq.remove());
    }

    @Test
    void testPeek() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(5);
        pq.insert(10);
        pq.insert(20);
        pq.insert(5);

        assertEquals(20, pq.peek());
        pq.remove();
        assertEquals(10, pq.peek());
    }

    @Test
    void testIsEmpty() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(5);
        assertTrue(pq.isEmpty());
        pq.insert(10);
        assertFalse(pq.isEmpty());
    }

    @Test
    void testIsFull() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(2);
        pq.insert(10);
        pq.insert(20);
        assertTrue(pq.isFull());
    }

    @Test
    void testRemoveFromEmptyQueue() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(5);
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, pq::remove);
    }

    @Test
    void testPeekFromEmptyQueue() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(5);
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, pq::peek);
    }
}
