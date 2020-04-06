package com.nhlstenden.queues;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Iterator;

public class QueueTest {
    static int AQUEUE = 1;
    static int LQUEUE = 2;

    // Change to LQUEUE to test linked-list queue.
    static int type = LQUEUE;

    @Test
    void testIterator()
    {
        Queue<Object> q = createQueue();

        q.enqueue("a");
        q.enqueue("b");
        q.enqueue("c");
        q.enqueue("d");
        q.enqueue("e");

        Iterator<Object> it = q.iterator();
        int i = 1;

        while(it.hasNext())
        {
            i++;
            it.next();
        }

        assertEquals(5, i);
    }

    @Test
    void testLength()
    {
        Queue<Object> q = createQueue();

        q.enqueue("a");
        q.enqueue("b");
        q.enqueue("c");
        q.enqueue("d");
        q.enqueue("e");
        assertEquals(5, q.length());

        q.dequeue();
        assertEquals(4, q.length());

        q.remove("c");
        assertEquals(3, q.length());

        q.clear();

        q.enqueue("a");
        q.enqueue("b");
        q.enqueue("c");
        q.enqueue("d");
        q.enqueue("e");

        assertTrue(q.remove("a"));
        assertEquals(4, q.length());

        q.remove("e");
        assertEquals(3, q.length());

        q.remove("b");
        q.remove("c");
        q.remove("d");

        assertEquals(0, q.length());
        assertTrue(q.isEmpty());

        assertEquals(null, q.frontValue());
        q.enqueue("abcdefg");
        assertEquals("abcdefg", q.frontValue());
        q.enqueue("1");
        q.enqueue("2");
        q.enqueue("3");
        q.dequeue();
        assertEquals("1", q.frontValue());
        q.remove("1");
        assertEquals("2", q.frontValue());

        q.clear();
        assertFalse(q.remove("2"));
        assertFalse(q.remove("gfdgsfdgsdgf"));
    }


    // Factory method to create new queue.
    private Queue<Object> createQueue() {
        if (type == AQUEUE)
            return new AQueue<Object>(5);
        if (type == LQUEUE)
            return new LQueue<Object>();
        return null;
    }
}
