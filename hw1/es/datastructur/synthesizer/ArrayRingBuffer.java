package es.datastructur.synthesizer;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    private class ArrayRingBufferInterator<T> implements Iterator<T> {

        private int count = fillCount;

        private int index = first;

        /**
         * Return whether it can be iterated
         */
        @Override
        public boolean hasNext() {
            return count != 0;
        }

        /**
         * Return the next item
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T temp = (T) rb[index];
            index++;
            count--;
            if (index > rb.length - 1) {
                index = 0;
            }
            return temp;
        }
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Return the capacit of the array
     */
    @Override
    public int capacity() {
        return rb.length;
    }

    /**
     * Return number of items currently in the buffer
     */
    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (fillCount >= capacity()) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last] = x;
        last++;
        fillCount++;
        if (last > capacity() - 1) {
            last = 0;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        T temp = rb[first];
        first++;
        fillCount--;
        if (first > capacity() - 1) {
            first = 0;
        }
        return temp;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        return rb[first];
    }

    /**
     * Return the iterator of the queue
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferInterator();
    }

    /**
     * Return whether two queue is the same
     */
    @Override
    public boolean equals(Object o) {
        ArrayRingBuffer temp = (ArrayRingBuffer) o;
        if (this.fillCount() != temp.fillCount()) {
            return false;
        }
        Iterator iter1 = this.iterator();
        Iterator iter2 = temp.iterator();
        while (iter1.hasNext()) {
            if (!String.valueOf(iter1.next()).equals(String.valueOf(iter2.next()))) {
                return false;
            }
        }
        return true;
    }
}

